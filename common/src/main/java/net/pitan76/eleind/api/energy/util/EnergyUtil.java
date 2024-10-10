package net.pitan76.eleind.api.energy.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.math.PosUtil;

public class EnergyUtil {
    public static long transfer(IEnergyStorage from, IEnergyStorage to, long maxAmount) {
        if (!canTransfer(from, to, maxAmount)) return 0;

        long extracted = from.extractEnergy(maxAmount, true, false);
        long inserted = to.insertEnergy(extracted, true, false);

        if (extracted != inserted) return 0;

        from.extractEnergy(extracted, false, false);
        to.insertEnergy(inserted, false, false);

        return inserted;
    }

    public static long transfer(IEnergyStorage from, IEnergyStorage to) {
        return transfer(from, to, from.getMaxOutputEnergy());
    }

    public static boolean canTransfer(IEnergyStorage from, IEnergyStorage to, long maxAmount) {
        return from.canExtractEnergy() && to.canInsertEnergy() && from.canExtractEnergy(maxAmount) && to.canInsertEnergy(maxAmount);
    }

    public static boolean canTransfer(IEnergyStorage from, IEnergyStorage to) {
        return canTransfer(from, to, from.getMaxOutputEnergy());
    }

    @Deprecated
    public static boolean transfer(BlockEntity from, BlockEntity to) {
        if (!(from instanceof IEnergyStorage) || !(to instanceof IEnergyStorage)) return false;
        return transfer((IEnergyStorage) from, (IEnergyStorage) to) > 0;
    }

    public static long transfer(BlockEntity from, BlockEntity to, long maxAmount) {
        if (isTeamRebornEnergyStorage(from) || isTeamRebornEnergyStorage(to)) {
            if (canTransferOther(from, to, maxAmount)) {
                return transferOther(from, to, maxAmount);
            }
        }

        if (!(from instanceof IEnergyStorage) || !(to instanceof IEnergyStorage)) return 0;
        return transfer((IEnergyStorage) from, (IEnergyStorage) to, maxAmount);
    }

    @Deprecated
    public static boolean canTransfer(BlockEntity from, BlockEntity to) {
        if (!(from instanceof IEnergyStorage) || !(to instanceof IEnergyStorage)) return false;
        return canTransfer((IEnergyStorage) from, (IEnergyStorage) to);
    }

    public static boolean canTransfer(BlockEntity from, BlockEntity to, long maxAmount) {
        if (isTeamRebornEnergyStorage(from) || isTeamRebornEnergyStorage(to)) {
            return canTransferOther(from, to, maxAmount);
        }

        if (!(from instanceof IEnergyStorage) || !(to instanceof IEnergyStorage)) return false;

        return canTransfer((IEnergyStorage) from, (IEnergyStorage) to, maxAmount);
    }

    @ExpectPlatform
    public static boolean canTransferOther(BlockEntity from, BlockEntity to, long maxAmount) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static long transferOther(BlockEntity from, BlockEntity to, long maxAmount) {
        throw new AssertionError();
    }

    public static boolean transferNearby(BlockEntity from, long maxAmount) {
        World world = from.getWorld();
        BlockPos pos = from.getPos();

        if (world == null) return false;

        BlockPos[] nearPositions = PosUtil.getNeighborPoses(pos);
        for (BlockPos nearPos : nearPositions) {
            if (!WorldUtil.hasBlockEntity(world, nearPos)) continue;

            BlockEntity to = WorldUtil.getBlockEntity(world, nearPos);
            if (to == null) continue;

            if (canTransfer(from, to, maxAmount)) {
                transfer(from, to, maxAmount);
                return true;
            }
        }

        return false;
    }

    @ExpectPlatform
    public static boolean isTeamRebornEnergyStorage(BlockEntity blockEntity) {
        throw new AssertionError();
    }
}
