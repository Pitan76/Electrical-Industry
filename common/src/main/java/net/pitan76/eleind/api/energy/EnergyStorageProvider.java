package net.pitan76.eleind.api.energy;

public interface EnergyStorageProvider extends IEnergyStorage {
    IEnergyStorage getEnergyStorage();

    @Override
    default long getEnergyStored() {
        return getEnergyStorage().getEnergyStored();
    }

    @Override
    default void setEnergyStored(long energy) {
        getEnergyStorage().setEnergyStored(energy);
    }

    @Override
    default long getCapacityEnergy() {
        return getEnergyStorage().getCapacityEnergy();
    }

    @Override
    default long getMaxInputEnergy() {
        return getEnergyStorage().getMaxInputEnergy();
    }

    @Override
    default long getMaxOutputEnergy() {
        return getEnergyStorage().getMaxOutputEnergy();
    }

    @Override
    default boolean canExtractEnergy() {
        return getEnergyStorage().canExtractEnergy();
    }

    @Override
    default boolean canInsertEnergy() {
        return getEnergyStorage().canInsertEnergy();
    }
}
