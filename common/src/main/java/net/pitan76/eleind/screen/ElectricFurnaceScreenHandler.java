package net.pitan76.eleind.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.eleind.block.entity.ElectricFurnaceBlockEntity;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.util.InventoryUtil;
import net.pitan76.mcpitanlib.guilib.api.container.ExtendedBlockEntityContainerGui;

public class ElectricFurnaceScreenHandler extends ExtendedBlockEntityContainerGui<ElectricFurnaceBlockEntity> {

    protected final PlayerInventory playerInventory;
    protected final Inventory inventory;

    public ElectricFurnaceScreenHandler(CreateMenuEvent e, PacketByteBuf buf) {
        super(ScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER.get(), e, buf);
        this.inventory = InventoryUtil.createSimpleInventory(2);
        this.playerInventory = e.playerInventory;

        initSlots();
    }

    public ElectricFurnaceScreenHandler(CreateMenuEvent e, Inventory inventory, ElectricFurnaceBlockEntity blockEntity) {
        this(ScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER.get(), e, inventory);
        this.blockEntity = blockEntity;
    }

    protected ElectricFurnaceScreenHandler(ScreenHandlerType<?> type, CreateMenuEvent e, Inventory inventory) {
        super(type, e);
        this.inventory = inventory;
        this.playerInventory = e.playerInventory;

        initSlots();
    }

    public void initSlots() {
        addPlayerMainInventorySlots(this.playerInventory, 8, 84);
        addPlayerHotbarSlots(this.playerInventory, 8, 142);
        addNormalSlot(this.inventory, 0, 44, 35);
        addNormalSlot(this.inventory, 1, 116, 35);
    }

    @Override
    public ItemStack quickMoveOverride(Player player, int index) {
        return super.quickMoveOverride(player, index);
    }

    @Override
    public void receiveSync(PacketByteBuf buf) {
        long energy = PacketByteUtil.readLong(buf);
        int cookTime = PacketByteUtil.readInt(buf);
        int cookTimeTotal = PacketByteUtil.readInt(buf);

        this.blockEntity.setEnergyStored(energy);
        this.blockEntity.cookTime = cookTime;
        this.blockEntity.cookTimeTotal = cookTimeTotal;
    }
}
