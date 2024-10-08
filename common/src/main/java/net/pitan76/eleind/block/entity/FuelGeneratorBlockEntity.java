package net.pitan76.eleind.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.pitan76.eleind.api.energy.EnergyStorageProvider;
import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.eleind.api.energy.SimpleEnergyStorage;
import net.pitan76.eleind.block.entity.base.MachineBlockEntityWithExtendedContainer;
import net.pitan76.eleind.screen.FuelGeneratorScreenHandler;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;

public class FuelGeneratorBlockEntity extends MachineBlockEntityWithExtendedContainer implements EnergyStorageProvider {

    public static SimpleEnergyStorage.Builder energyStorageBuilder =
            new SimpleEnergyStorage.Builder().capacity(10_000).maxInput(500).maxOutput(500);

    public FuelGeneratorBlockEntity(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public FuelGeneratorBlockEntity(TileCreateEvent e) {
        this(BlockEntities.FUEL_GENERATOR.getOrNull(), e);
    }

    @Override
    public IEnergyStorage getEnergyStorage() {
        return energyStorageBuilder.build();
    }

    @Override
    public void sync(Player player, PacketByteBuf buf) {
        PacketByteUtil.writeLong(buf, this.getEnergyStored());
    }

    @Override
    public int getDefaultInvSize() {
        return 1;
    }

    @Override
    public ScreenHandler createMenu(CreateMenuEvent e) {
        return new FuelGeneratorScreenHandler(e);
    }
}
