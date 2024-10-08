package net.pitan76.eleind.block.base;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.guilib.api.block.entity.ExtendedBlockEntityWithContainer;

public abstract class MachineBlock extends ExtendBlock implements ExtendBlockEntityProvider {
    public MachineBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    public ActionResult onRightClick(BlockUseEvent e) {
        if (e.isSneaking()) return e.pass();
        if (!e.isClient()) return e.success();

        if (!e.hasBlockEntity()) return e.pass();

        BlockEntity blockEntity = e.getBlockEntity();
        if (!(blockEntity instanceof ExtendedBlockEntityWithContainer)) return e.pass();

        e.player.openGuiScreen((ExtendedBlockEntityWithContainer) blockEntity);
        return e.success();
    }
}
