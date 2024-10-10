package net.pitan76.eleind.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.pitan76.eleind.block.entity.base.MachineBlockEntityWithExtendedContainer;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;
import net.pitan76.mcpitanlib.api.util.PropertyUtil;
import org.jetbrains.annotations.Nullable;

public abstract class MachineBlock extends ExtendBlock implements ExtendBlockEntityProvider {

    public DirectionProperty FACING = PropertyUtil.horizontalFacing();

    public MachineBlock(CompatibleBlockSettings settings) {
        super(settings);
        setNewDefaultState(getNewDefaultState().with(FACING, Direction.NORTH));
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
        super.onStateReplaced(e);
    }

    @Override
    public @Nullable BlockState getPlacementState(PlacementStateArgs args) {
        return args.withBlockState(FACING, args.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void appendProperties(AppendPropertiesArgs args) {
        args.addProperty(FACING);
        super.appendProperties(args);
    }
}
