package net.pitan76.eleind.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.pitan76.eleind.ElectricalIndustry;
import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.eleind.api.state.EIProperties;
import net.pitan76.eleind.api.state.IActiveHolder;
import net.pitan76.eleind.block.entity.base.MachineBlockEntityWithExtendedContainer;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;
import net.pitan76.mcpitanlib.api.util.PropertyUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

public abstract class MachineBlock extends ExtendBlock implements ExtendBlockEntityProvider, IActiveHolder {

    public static DirectionProperty FACING = PropertyUtil.horizontalFacing();
    public static BooleanProperty ACTIVE = EIProperties.ACTIVE;
    public static BooleanProperty POWERED = PropertyUtil.powered();

    public MachineBlock(CompatibleBlockSettings settings) {
        super(settings);
        setNewDefaultState(getNewDefaultState().with(FACING, Direction.NORTH).with(ACTIVE, false).with(POWERED, false));
    }

    public ActionResult onRightClick(BlockUseEvent e) {
        if (e.isSneaking()) return e.pass();
        if (e.isClient()) return e.success();

        if (!e.hasBlockEntity()) return e.pass();

        BlockEntity blockEntity = e.getBlockEntity();
        if (!(blockEntity instanceof MachineBlockEntityWithExtendedContainer)) return e.pass();

        e.player.openExtendedMenu((MachineBlockEntityWithExtendedContainer) blockEntity);
        return e.success();
    }

    @Override
    public void onStateReplaced(StateReplacedEvent e) {
        e.spawnDropsInContainer();
        if (e.hasBlockEntity()) {
            BlockEntity blockEntity = e.getBlockEntity();
            if (blockEntity instanceof IEnergyStorage) {
                ElectricalIndustry.removeEnergyStorage((IEnergyStorage) blockEntity);
            }
        }

        super.onStateReplaced(e);
    }

    @Override
    public @Nullable BlockState getPlacementState(PlacementStateArgs args) {
        return args.withBlockState(FACING, args.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void appendProperties(AppendPropertiesArgs args) {
        args.addProperty(FACING);
        args.addProperty(ACTIVE);
        args.addProperty(POWERED);
        super.appendProperties(args);
    }

    @Override
    public boolean isActive(BlockState state) {
        return state.get(ACTIVE);
    }

    @Override
    public void setActive(World world, BlockPos pos, boolean active) {
        BlockState state = WorldUtil.getBlockState(world, pos);
        world.setBlockState(pos, PropertyUtil.with(state, ACTIVE, active));
    }

    public Direction getFacing(BlockState state) {
        return state.get(FACING);
    }

    public boolean isPowered(BlockState state) {
        return state.get(POWERED);
    }
}
