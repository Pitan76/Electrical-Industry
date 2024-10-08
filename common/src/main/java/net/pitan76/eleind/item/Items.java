package net.pitan76.eleind.item;

import net.minecraft.item.Item;
import net.pitan76.eleind.block.Blocks;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.util.ItemUtil;

import static net.pitan76.eleind.ElectricalIndustry._id;
import static net.pitan76.eleind.ElectricalIndustry.registry;

public class Items {

    public static final CompatibleItemSettings STANDARD_ITEM_SETTINGS = CompatibleItemSettings.of()
            .addGroup(ItemGroups.EI_GROUP);

    public static RegistryResult<Item> EXAMPLE_ITEM;
    public static RegistryResult<Item> EXAMPLE_BLOCK;

    public static void init() {
        EXAMPLE_ITEM = registry.registerItem(_id("example_item"), () -> new ExampleItem(STANDARD_ITEM_SETTINGS));
        EXAMPLE_BLOCK = registry.registerItem(_id("example_block"), () -> ItemUtil.ofBlock(Blocks.EXAMPLE_BLOCK.getOrNull(), STANDARD_ITEM_SETTINGS));
    }
}
