package net.pitan76.eleind.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class ExampleItem extends ExtendItem {

    public ExampleItem(CompatibleItemSettings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> onRightClick(ItemUseEvent e) {
        if (e.isClient()) return TypedActionResult.success(e.stack);

        Player player = e.getUser();
        player.sendMessage(TextUtil.literal("The item was right-clicked!"));

        return TypedActionResult.success(e.stack);
    }

    @Override
    public ActionResult onRightClickOnBlock(ItemUseOnBlockEvent e) {
        if (e.isClient()) return ActionResult.SUCCESS;

        Player player = e.getPlayer();
        player.sendMessage(TextUtil.literal("The item was right-clicked on a block!"));

        return ActionResult.SUCCESS;

    }
}
