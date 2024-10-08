package net.pitan76.eleind.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;
import net.pitan76.eleind.api.energy.EnergyStorageProvider;
import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.eleind.api.energy.SimpleEnergyStorage;
import net.pitan76.eleind.api.energy.util.EnergyUtil;
import net.pitan76.eleind.block.entity.base.MachineBlockEntityWithExtendedContainer;
import net.pitan76.eleind.screen.FuelGeneratorScreenHandler;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.network.v2.ServerNetworking;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.core.registry.FuelRegistry;
import net.pitan76.mcpitanlib.guilib.api.NetworkDefines;
import net.pitan76.mcpitanlib.guilib.api.block.entity.ExtendedBlockEntityWithContainer;
import net.pitan76.mcpitanlib.guilib.api.container.ExtendedBlockEntityContainerGui;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class FuelGeneratorBlockEntity extends MachineBlockEntityWithExtendedContainer implements EnergyStorageProvider {

    public int burnTime = 0;

    public static SimpleEnergyStorage.Builder energyStorageBuilder =
            new SimpleEnergyStorage.Builder().capacity(10_000).maxInput(0).maxOutput(500);

    private final IEnergyStorage energyStorage = energyStorageBuilder.build();

    public FuelGeneratorBlockEntity(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public FuelGeneratorBlockEntity(TileCreateEvent e) {
        this(BlockEntities.FUEL_GENERATOR.getOrNull(), e);
    }

    @Override
    public void tick(TileTickEvent<ExtendedBlockEntityWithContainer> e) {
        super.tick(e);
        if (isClient() || isFullEnergy()) return;

        ItemStack stack = getStack(0);

        if (isBurning()) {
            burnTime--;
            addEnergyStored(generateEnergyAmountOnTick());
        } else {
            startBurn(stack);
        }

        EnergyUtil.transferNearby(this, getEnergyStored());
    }

    @Override
    public void writeNbt(WriteNbtArgs args) {
        super.writeNbt(args);
        NbtUtil.putInt(args.nbt, "burnTime", burnTime);
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        super.readNbt(args);
        burnTime = NbtUtil.getInt(args.nbt, "burnTime");
    }

    public long generateEnergyAmountOnTick() {
        return 1;
    }

    public boolean isBurning() {
        return burnTime > 0;
    }

    public void startBurn(int time) {
        burnTime = time;
    }

    public boolean startBurn(ItemStack stack) {
        if (stack.isEmpty()) return false;

        int time = FuelRegistry.get(stack);
        if (time == 0) return false;

        ItemStackUtil.decrementCount(stack, 1);
        startBurn(time);
        return true;
    }

    @Override
    public IEnergyStorage getEnergyStorage() {
        return energyStorage;
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
        return new FuelGeneratorScreenHandler(e, this, this);
    }
}
