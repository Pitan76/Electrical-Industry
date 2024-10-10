package net.pitan76.eleind.block.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.eleind.api.state.IBlockEntityActiveHolder;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.guilib.api.block.entity.BlockEntityWithContainer;

import java.util.Optional;

public abstract class MachineBlockEntityWithContainer extends BlockEntityWithContainer implements IEnergyStorage, IBlockEntityActiveHolder {
    public MachineBlockEntityWithContainer(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    @Override
    public void writeNbt(WriteNbtArgs args) {
        super.writeNbt(args);
        writeEnergyNbt(args);
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        super.readNbt(args);
        readEnergyNbt(args);
    }

    @Override
    public void setActive(boolean active) {
        Optional<BlockState> state = getOptionalBlockState();
        if (!state.isPresent()) return;

        setActive(getWorld(), getPos(), active);
    }

    @Override
    public boolean isActive() {
        Optional<BlockState> state = getOptionalBlockState();
        return state.filter(this::isActive).isPresent();

    }

    public Optional<BlockState> getOptionalBlockState() {
        if (getCachedState() != null)
            return Optional.of(getCachedState());

        if (getWorld() == null)
            return Optional.empty();

        return Optional.ofNullable(getWorld().getBlockState(getPos()));
    }
}
