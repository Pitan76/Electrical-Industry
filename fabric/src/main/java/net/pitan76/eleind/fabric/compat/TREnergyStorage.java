package net.pitan76.eleind.fabric.compat;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;
import net.pitan76.eleind.Config;
import net.pitan76.eleind.api.energy.IEnergyStorage;
import team.reborn.energy.api.EnergyStorage;

public class TREnergyStorage extends SnapshotParticipant<Long> implements EnergyStorage {

    public static final double CONVERSION_RATE = Config.reborn_energy_conversion_rate;

    private final IEnergyStorage storage;

    public TREnergyStorage(IEnergyStorage storage) {
        this.storage = storage;
    }

    public IEnergyStorage getStorage() {
        return storage;
    }

    public long getUsableCapacity() {
        return (long) (getStorage().getUsableCapacity() / CONVERSION_RATE);
    }

    @Override
    public boolean supportsInsertion() {
        return getStorage().canInsertEnergy();
    }

    @Override
    public boolean supportsExtraction() {
        return getStorage().canExtractEnergy();
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        if (maxAmount < getUsableCapacity()) {
            updateSnapshots(transaction);
            return (long) (getStorage().insertEnergy((long) (maxAmount * CONVERSION_RATE)) / CONVERSION_RATE);
        }
        if (maxAmount > 0) {
            updateSnapshots(transaction);
            return (long) (getStorage().insertEnergy((long) (getUsableCapacity() * CONVERSION_RATE)) / CONVERSION_RATE);
        }

        return 0;
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        if (maxAmount < getAmount()) {
            updateSnapshots(transaction);
            return (long) (getStorage().extractEnergy((long) (maxAmount * CONVERSION_RATE)) / CONVERSION_RATE);
        }
        if (getAmount() > 0) {
            updateSnapshots(transaction);
            return (long) (getStorage().extractEnergy(getStorage().getEnergyStored()) / CONVERSION_RATE);
        }
        return 0;
    }

    @Override
    public long getAmount() {
        return (long) (getStorage().getEnergyStored() / CONVERSION_RATE);
    }

    @Override
    public long getCapacity() {
        return (long) (getStorage().getCapacityEnergy() / CONVERSION_RATE);
    }

    @Override
    protected Long createSnapshot() {
        return getStorage().getEnergyStored();
    }

    @Override
    protected void readSnapshot(Long snapshot) {
        getStorage().setEnergyStored(snapshot);
    }
}