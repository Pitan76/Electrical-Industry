package net.pitan76.eleind.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.eleind.block.entity.FuelGeneratorBlockEntity;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.util.InventoryUtil;
import net.pitan76.mcpitanlib.guilib.api.container.ExtendedBlockEntityContainerGui;

public class FuelGeneratorScreenHandler extends ExtendedBlockEntityContainerGui<FuelGeneratorBlockEntity> {

    protected final PlayerInventory playerInventory;
    protected final Inventory inventory;

    public FuelGeneratorScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        super(ScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER.get(), new CreateMenuEvent(syncId, inventory), buf);
        this.inventory = InventoryUtil.createSimpleInventory(1);
        this.playerInventory = inventory;

        initSlots();
    }
//
//    public FuelGeneratorScreenHandler(CreateMenuEvent e) {
//        this(e, InventoryUtil.createSimpleInventory(1));
//    }

    public FuelGeneratorScreenHandler(CreateMenuEvent e, Inventory inventory, FuelGeneratorBlockEntity blockEntity) {
        this(ScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER.get(), e, inventory);
        this.blockEntity = blockEntity;
    }

    protected FuelGeneratorScreenHandler(ScreenHandlerType<?> type, CreateMenuEvent e, Inventory inventory) {
        super(type, e);
        this.inventory = inventory;
        this.playerInventory = e.playerInventory;

        initSlots();
    }

    public void initSlots() {
        addPlayerMainInventorySlots(this.playerInventory, 8, 84);
        addPlayerHotbarSlots(this.playerInventory, 8, 142);
        addNormalSlot(this.inventory, 0, 80, 35);
    }

    @Override
    public ItemStack quickMoveOverride(Player player, int index) {
        return super.quickMoveOverride(player, index);
    }

    @Override
    public void receiveSync(PacketByteBuf buf) {
        long energy = PacketByteUtil.readLong(buf);
        int burnTime = PacketByteUtil.readInt(buf);
        int maxBurnTime = PacketByteUtil.readInt(buf);

        this.blockEntity.setEnergyStored(energy);
        this.blockEntity.burnTime = burnTime;
        this.blockEntity.maxBurnTime = maxBurnTime;
    }
}
