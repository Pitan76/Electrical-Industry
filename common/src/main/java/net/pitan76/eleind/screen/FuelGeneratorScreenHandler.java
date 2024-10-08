package net.pitan76.eleind.screen;

import net.minecraft.inventory.Inventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.eleind.block.entity.FuelGeneratorBlockEntity;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.util.InventoryUtil;
import net.pitan76.mcpitanlib.guilib.api.container.ExtendedBlockEntityContainerGui;

public class FuelGeneratorScreenHandler extends ExtendedBlockEntityContainerGui<FuelGeneratorBlockEntity> {

    protected final Inventory inventory;

    public FuelGeneratorScreenHandler(CreateMenuEvent e) {
        this(e, InventoryUtil.createSimpleInventory(1));
    }

    public FuelGeneratorScreenHandler(CreateMenuEvent e, Inventory inventory) {
        this(ScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER.get(), e, inventory);
    }

    protected FuelGeneratorScreenHandler(ScreenHandlerType<?> type, CreateMenuEvent e, Inventory inventory) {
        super(type, e);
        this.inventory = inventory;

        addPlayerMainInventorySlots(e.playerInventory, 8, 142);
        addPlayerHotbarSlots(e.playerInventory, 8, 84);

        addNormalSlot(inventory, 0, 80, 35);
    }

    @Override
    public void receiveSync(PacketByteBuf buf) {
        long energy = PacketByteUtil.readLong(buf);
        this.blockEntity.setEnergyStored(energy);
    }
}
