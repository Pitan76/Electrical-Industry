package net.pitan76.eleind.api.energy.util;

import net.pitan76.eleind.api.energy.IEnergyStorage;

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
        return from.canExtractEnergy(maxAmount) && to.canInsertEnergy(maxAmount);
    }

    public static boolean canTransfer(IEnergyStorage from, IEnergyStorage to) {
        return canTransfer(from, to, from.getMaxOutputEnergy());
    }
}
