package net.pitan76.eleind.block;

import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import static net.pitan76.eleind.ElectricalIndustry._id;
import static net.pitan76.eleind.ElectricalIndustry.registry;

public class Blocks {

    public static RegistryResult<Block> FUEL_GENERATOR;

    public static void init() {
        FUEL_GENERATOR = registry.registerBlock(_id("generator"), () -> new FuelGeneratorBlock(CompatibleBlockSettings.of(CompatibleMaterial.STONE)));
    }
}
