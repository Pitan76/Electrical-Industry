package net.pitan76.eleind;

import net.pitan76.eleind.block.Blocks;
import net.pitan76.eleind.block.entity.BlockEntities;
import net.pitan76.eleind.item.ItemGroups;
import net.pitan76.eleind.item.Items;
import net.pitan76.eleind.screen.ScreenHandlers;
import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class ElectricalIndustry extends CommonModInitializer {
    public static final String MOD_ID = "eleind";
    public static final String MOD_NAME = "Electrical Industry";

    public static ElectricalIndustry INSTANCE;
    public static CompatRegistryV2 registry;

    @Override
    public void init() {
        INSTANCE = this;
        registry = super.registry;

        ItemGroups.init();
        Blocks.init();
        Items.init();
        BlockEntities.init();
        ScreenHandlers.init();
    }

    // ----
    /**
     * @param path The path of the id
     * @return The id
     */
    public static CompatIdentifier _id(String path) {
        return CompatIdentifier.of(MOD_ID, path);
    }

    @Override
    public String getId() {
        return MOD_ID;
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }
}