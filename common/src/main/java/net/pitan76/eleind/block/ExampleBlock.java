package net.pitan76.eleind.block;

import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class ExampleBlock extends ExtendBlock {

    public ExampleBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    @Override
    public ActionResult onRightClick(BlockUseEvent e) {
        if (e.isSneaking()) return super.onRightClick(e);
        if (e.isClient()) return ActionResult.SUCCESS;

        Player player = e.getPlayer();
        player.sendMessage(TextUtil.literal("The block was right-clicked!"));

        return ActionResult.SUCCESS;
    }
}
