package net.pitan76.eleind.block.base;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.ActionResult;
import net.pitan76.eleind.block.entity.base.MachineBlockEntityWithExtendedContainer;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;

public abstract class MachineBlock extends ExtendBlock implements ExtendBlockEntityProvider {
    public MachineBlock(CompatibleBlockSettings settings) {
        super(settings);
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
}
