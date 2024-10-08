package net.pitan76.eleind.screen;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.eleind.block.entity.FuelGeneratorBlockEntity;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.guilib.api.container.ExtendedBlockEntityContainerGui;

public class FuelGeneratorScreenHandler extends ExtendedBlockEntityContainerGui<FuelGeneratorBlockEntity> {

    public FuelGeneratorScreenHandler(CreateMenuEvent e) {
        super(ScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER.get(), e);
    }

    protected FuelGeneratorScreenHandler(ScreenHandlerType<?> type, CreateMenuEvent e) {
        super(type, e);
    }

    @Override
    public void receiveSync(PacketByteBuf buf) {
        long energy = PacketByteUtil.readLong(buf);
        this.blockEntity.setEnergyStored(energy);
    }
}
