package net.pitan76.eleind.item;

import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

import static net.pitan76.eleind.ElectricalIndustry._id;
import static net.pitan76.eleind.ElectricalIndustry.registry;

public class ItemGroups {

    public static CreativeTabBuilder EI_GROUP = CreativeTabBuilder.create(_id("main"))
            .setIcon(() -> ItemStackUtil.create(Items.FUEL_GENERATOR.get()));

    public static void init() {
        registry.registerItemGroup(EI_GROUP);
    }
}
