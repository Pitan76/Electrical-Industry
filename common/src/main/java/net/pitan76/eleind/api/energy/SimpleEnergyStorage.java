package net.pitan76.eleind.api.energy;

public class SimpleEnergyStorage implements IEnergyStorage {
    private long stored = 0;
    private final long capacity;
    private final long maxInput;
    private final long maxOutput;
    private final boolean canExtract;
    private final boolean canInsert;

    public SimpleEnergyStorage(long capacity, long maxInput, long maxOutput, boolean canExtract, boolean canInsert) {
        this.capacity = capacity;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
        this.canExtract = canExtract;
        this.canInsert = canInsert;
    }

    public SimpleEnergyStorage(long defaultStored, long capacity, long maxInput, long maxOutput, boolean canExtract, boolean canInsert) {
        this(capacity, maxInput, maxOutput, canExtract, canInsert);
        this.stored = defaultStored;
    }

    public static SimpleEnergyStorage of(long capacity) {
        return new SimpleEnergyStorage(capacity, capacity / 4, capacity / 4, true, true);
    }

    public static SimpleEnergyStorage of(long capacity, long maxInput, long maxOutput, boolean canExtract, boolean canInsert) {
        return new SimpleEnergyStorage(capacity, maxInput, maxOutput, canExtract, canInsert);
    }

    public static SimpleEnergyStorage of(long defaultStored, long capacity, long maxInput, long maxOutput, boolean canExtract, boolean canInsert) {
        return new SimpleEnergyStorage(defaultStored, capacity, maxInput, maxOutput, canExtract, canInsert);
    }

    public static SimpleEnergyStorage ofInputOnly(long capacity, long maxInput) {
        return new SimpleEnergyStorage(capacity, maxInput, 0, false, true);
    }

    public static SimpleEnergyStorage ofOutputOnly(long capacity, long maxOutput) {
        return new SimpleEnergyStorage(capacity, 0, maxOutput, true, false);
    }

    public static SimpleEnergyStorage of(long capacity, long maxInput, long maxOutput) {
        if (maxInput == 0 && maxOutput == 0) return of(capacity, 0, 0, false, false);
        if (maxInput == 0) return ofOutputOnly(capacity, maxOutput);
        if (maxOutput == 0) return ofInputOnly(capacity, maxInput);

        return of(capacity, maxInput, maxOutput, true, true);
    }

    @Override
    public long getEnergyStored() {
        return stored;
    }

    @Override
    public void setEnergyStored(long energy) {
        this.stored = energy;
    }

    @Override
    public long getCapacityEnergy() {
        return capacity;
    }

    @Override
    public long getMaxInputEnergy() {
        return maxInput;
    }

    @Override
    public long getMaxOutputEnergy() {
        return maxOutput;
    }

    @Override
    public boolean canExtractEnergy() {
        return canExtract;
    }

    @Override
    public boolean canInsertEnergy() {
        return canInsert;
    }

    public static class Builder {
        private long defaultStored;
        private long capacity;
        private long maxInput;
        private long maxOutput;
        private boolean canExtract;
        private boolean canInsert;

        public Builder defaultStored(long defaultStored) {
            this.defaultStored = defaultStored;
            return this;
        }

        public Builder capacity(long capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder maxInput(long maxInput) {
            canInsert(maxInput > 0);
            this.maxInput = maxInput;
            return this;
        }

        public Builder maxOutput(long maxOutput) {
            canExtract(maxOutput > 0);
            this.maxOutput = maxOutput;
            return this;
        }

        public Builder canExtract(boolean canExtract) {
            this.canExtract = canExtract;
            return this;
        }

        public Builder canInsert(boolean canInsert) {
            this.canInsert = canInsert;
            return this;
        }

        public SimpleEnergyStorage build() {
            return of(defaultStored, capacity, maxInput, maxOutput, canExtract, canInsert);
        }
    }

}
