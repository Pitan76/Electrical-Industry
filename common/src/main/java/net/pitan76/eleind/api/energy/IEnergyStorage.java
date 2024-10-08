package net.pitan76.eleind.api.energy;

import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;

public interface IEnergyStorage {

    /**
     * get the amount of energy stored
     * @return the amount of energy stored
     */
    long getEnergyStored();

    /**
     * set the amount of energy stored
     * @param energy the amount of energy stored
     */
    void setEnergyStored(long energy);

    /**
     * get the amount of energy that can be stored
     * @return the amount of energy that can be stored
     */
    long getCapacityEnergy();

    /**
     * get the maximum amount of energy that can be stored
     * @return the maximum amount of energy that can be stored
     */
    long getMaxInputEnergy();

    /**
     * get the maximum amount of energy that can be extracted from the storage
     * @return the maximum amount of energy that can be extracted from the storage
     */
    long getMaxOutputEnergy();

    /**
     * allow energy to be extracted from the storage?
     * @return true if energy can be extracted from the storage
     */
    boolean canExtractEnergy();

    /**
     * allow energy to be inserted into the storage?
     * @return true if energy can be inserted into the storage
     */
    boolean canInsertEnergy();

    /**
     * insert energy into the storage
     * @param amount the amount of energy to insert
     * @param simulate if true, the insertion will be simulated
     * @param check if true, the insertion will be checked
     * @return the amount of energy that was accepted by the storage
     */
    default long insertEnergy(long amount, boolean simulate, boolean check) {
        if (check && !canInsertEnergy(amount)) return 0;

        long inserted = Math.min(getCapacityEnergy() - getEnergyStored(), Math.min(getMaxInputEnergy(), amount));

        if (!simulate) {
            addEnergyStored(inserted);
        }

        return inserted;
    }

    /**
     * extract energy from the storage
     * @param amount the amount of energy to extract
     * @param simulate if true, the extraction will be simulated
     * @param check if true, the extraction will be checked
     * @return the amount of energy that was extracted from the storage
     */
    default long extractEnergy(long amount, boolean simulate, boolean check) {
        if (check && !canExtractEnergy(amount)) return 0;

        long extracted = Math.min(getEnergyStored(), Math.min(getMaxOutputEnergy(), amount));

        if (!simulate) {
            addEnergyStored(-extracted);
        }

        return extracted;
    }

    default long insertEnergy(long amount, boolean simulate) {
        return insertEnergy(amount, simulate, true);
    }

    default long extractEnergy(long amount, boolean simulate) {
        return extractEnergy(amount, simulate, true);
    }

    default long insertEnergy(long amount) {
        return insertEnergy(amount, false);
    }

    default long extractEnergy(long amount) {
        return extractEnergy(amount, false);
    }

    default void addEnergyStored(long amount) {
        addEnergyStored(amount, true);
    }

    /**
     * add energy to the storage
     * @param amount the amount of energy to add
     * @param check if true, the addition will be checked
     */
    default void addEnergyStored(long amount, boolean check) {
        if (check && !canInsertEnergy(amount)) return;
        setEnergyStored(getEnergyStored() + amount);
    }

    default long removeEnergyStored(long amount) {
        return removeEnergyStored(amount, true);
    }

    /**
     * remove energy from the storage
     * @param amount the amount of energy to remove
     * @param check if true, the removal will be checked
     * @return the amount of energy that was removed from the storage
     */
    default long removeEnergyStored(long amount, boolean check) {
        if (check && !canExtractEnergy(amount)) return 0;
        long energy = getEnergyStored();
        long energyRemoved = Math.min(energy, amount);
        energy -= energyRemoved;
        setEnergyStored(energy);
        return energyRemoved;
    }

    /**
     * use energy from the storage
     * @param amount the amount of energy to use
     * @return true if the energy was used
     */
    default boolean useEnergy(long amount) {
        if (!canExtractEnergy(amount)) return false;
        removeEnergyStored(amount);
        return true;
    }

    /**
     * check if the energy storage is full
     * @return true if the energy storage is full
     */
    default boolean isFullEnergy() {
        return getEnergyStored() >= getCapacityEnergy();
    }

    /**
     * check if the energy storage is empty
     * @return true if the energy storage is empty
     */
    default boolean isEmptyEnergy() {
        return getEnergyStored() <= 0;
    }

    default boolean isNotEmptyEnergy() {
        return !isEmptyEnergy();
    }

    default boolean isNotFullEnergy() {
        return !isFullEnergy();
    }

    default boolean canInsertEnergy(long amount) {
        return getEnergyStored() + amount <= getCapacityEnergy();
    }

    default boolean canExtractEnergy(long amount) {
        return getEnergyStored() - amount >= 0;
    }

    default void writeEnergyNbt(NbtCompound nbt) {
        nbt.putLong("energy", getEnergyStored());
    }

    default void readEnergyNbt(NbtCompound nbt) {
        setEnergyStored(nbt.getLong("energy"));
    }

    default void writeEnergyNbt(net.minecraft.nbt.NbtCompound nbt) {
        NbtUtil.putLong(nbt, "energy", getEnergyStored());
    }

    default void readEnergyNbt(net.minecraft.nbt.NbtCompound nbt) {
        setEnergyStored(NbtUtil.getLong(nbt, "energy"));
    }

    default void writeEnergyNbt(WriteNbtArgs args) {
        writeEnergyNbt(args.getNbt());
    }

    default void readEnergyNbt(ReadNbtArgs args) {
        readEnergyNbt(args.getNbt());
    }
}
