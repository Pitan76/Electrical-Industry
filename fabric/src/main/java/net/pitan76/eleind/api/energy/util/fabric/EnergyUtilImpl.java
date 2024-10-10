package net.pitan76.eleind.api.energy.util.fabric;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.eleind.fabric.compat.RebornEnergyRegister;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;

import static net.pitan76.eleind.ElectricalIndustry.isLoadedTeamRebornEnergy;

public class EnergyUtilImpl {
    public static boolean canTransferOther(BlockEntity from, BlockEntity to, long maxAmount) {
        if (!isLoadedTeamRebornEnergy()) return false;

        EnergyStorage trFrom = null;
        EnergyStorage trTo = null;

        if (from instanceof IEnergyStorage) trFrom = RebornEnergyRegister.getEnergyStorage((IEnergyStorage) from);
        if (from instanceof PowerAcceptorBlockEntity) trFrom = ((PowerAcceptorBlockEntity) from).getSideEnergyStorage(null);

        if (to instanceof IEnergyStorage) trTo = RebornEnergyRegister.getEnergyStorage((IEnergyStorage) to);
        if (to instanceof PowerAcceptorBlockEntity) trTo = ((PowerAcceptorBlockEntity) to).getSideEnergyStorage(null);

        if (trFrom == null || trTo == null) return false;
        
        return trFrom.supportsExtraction() && trTo.supportsInsertion() && trFrom.getAmount() - maxAmount >= 0 && trTo.getAmount() + maxAmount <= trTo.getCapacity();
    }

    protected static long transferOther(BlockEntity from, BlockEntity to, long maxAmount) {
        if (!isLoadedTeamRebornEnergy()) return 0;

        EnergyStorage trFrom = null;
        EnergyStorage trTo = null;

        if (from instanceof IEnergyStorage) trFrom = RebornEnergyRegister.getEnergyStorage((IEnergyStorage) from);
        if (from instanceof PowerAcceptorBlockEntity) trFrom = ((PowerAcceptorBlockEntity) from).getSideEnergyStorage(null);

        if (to instanceof IEnergyStorage) trTo = RebornEnergyRegister.getEnergyStorage((IEnergyStorage) to);
        if (to instanceof PowerAcceptorBlockEntity) trTo = ((PowerAcceptorBlockEntity) to).getSideEnergyStorage(null);

        if (trFrom == null || trTo == null) return 0;

        return EnergyStorageUtil.move(
                trFrom,
                trTo,
                maxAmount,
                null
        );
    }

    public static boolean isTeamRebornEnergyStorage(BlockEntity blockEntity) {
        if (!isLoadedTeamRebornEnergy()) return false;

        return blockEntity instanceof PowerAcceptorBlockEntity;
    }
}
