package net.pitan76.eleind.block.entity;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.pitan76.eleind.api.energy.EnergyStorageProvider;
import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.eleind.api.energy.SimpleEnergyStorage;
import net.pitan76.eleind.api.energy.util.EnergyUtil;
import net.pitan76.eleind.block.entity.base.MachineBlockEntityWithExtendedContainer;
import net.pitan76.eleind.screen.ElectricFurnaceScreenHandler;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.util.BlockEntityUtil;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.guilib.api.block.entity.ExtendedBlockEntityWithContainer;

public class ElectricFurnaceBlockEntity extends MachineBlockEntityWithExtendedContainer implements EnergyStorageProvider {

    public static final int DEFAULT_COOK_TIME = 200;

    public int cookTime;
    public int cookTimeTotal;

    public static SimpleEnergyStorage.Builder energyStorageBuilder =
            new SimpleEnergyStorage.Builder().capacity(10_000).maxInput(500).maxOutput(0);

    private final IEnergyStorage energyStorage = energyStorageBuilder.build();

    public ElectricFurnaceBlockEntity(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public ElectricFurnaceBlockEntity(TileCreateEvent e) {
        this(BlockEntities.FUEL_GENERATOR.getOrNull(), e);
    }

    @Override
    public void tick(TileTickEvent<ExtendedBlockEntityWithContainer> e) {
        super.tick(e);
        if (isClient()) return;

        if (!isEmptyEnergy() && canOutput()) {
            ItemStack stack = getInputStack();

            if (stack.isEmpty()) { //&& AbstractFurnaceBlockEntity.canUseAsFuel()) {
                cookTime = 0;
                cookTimeTotal = 0;
                setActive(false);
            } else {
                if (isCooking()) {
                    cookTime -= 1;
                    removeEnergyStored(getConsumingEnergyAmountOnTick());
                } else {

                    //boolean success = startCook();
                    //setActive(success);
                    //if (!success)
                    //    cookTimeTotal = 0;
                }
            }
        }

        EnergyUtil.transferNearby(this, getEnergyStored());
        BlockEntityUtil.markDirty(this);
    }

    @Override
    public void writeNbt(WriteNbtArgs args) {
        super.writeNbt(args);
        NbtUtil.putInt(args.nbt, "cookTime", cookTime);
        NbtUtil.putInt(args.nbt, "cookTimeTotal", cookTimeTotal);
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        super.readNbt(args);
        cookTime = NbtUtil.getInt(args.nbt, "cookTime");
        cookTimeTotal = NbtUtil.getInt(args.nbt, "cookTimeTotal");
    }

    public ItemStack getInputStack() {
        return getStack(0);
    }

    public ItemStack getOutputStack() {
        return getStack(1);
    }

    public boolean canOutput() {
        ItemStack stack = getOutputStack();
        if (stack.isEmpty()) return true;
        if (!ItemStackUtil.areItemsEqual(stack, getOutputStack())) return false;

        return stack.getCount() + 1 <= stack.getMaxCount();
    }

    public long getConsumingEnergyAmountOnTick() {
        return 1;
    }

    public boolean isCooking() {
        return cookTime > 0;
    }

    public void startCook(int time) {
        cookTime = time;
        cookTimeTotal = time;
    }

    public void startCook() {
        startCook(DEFAULT_COOK_TIME);
    }

    @Override
    public IEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    @Override
    public void sync(Player player, PacketByteBuf buf) {
        PacketByteUtil.writeLong(buf, this.getEnergyStored());
        PacketByteUtil.writeInt(buf, this.cookTime);
        PacketByteUtil.writeInt(buf, this.cookTimeTotal);
    }

    @Override
    public int getDefaultInvSize() {
        return 2;
    }

    @Override
    public ScreenHandler createMenu(CreateMenuEvent e) {
        return new ElectricFurnaceScreenHandler(e, this, this);
    }
}
