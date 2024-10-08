package net.pitan76.eleind.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.eleind.block.Blocks;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder;

import java.util.function.Supplier;

import static net.pitan76.eleind.ElectricalIndustry._id;
import static net.pitan76.eleind.ElectricalIndustry.registry;

public class BlockEntities {

    public static RegistryResult<BlockEntityType<?>> FUEL_GENERATOR;

    public static void init() {
        FUEL_GENERATOR = register("fuel_generator", () -> BlockEntityTypeBuilder.create(FuelGeneratorBlockEntity::new, Blocks.GENERATOR.getOrNull()));
    }

    public static <T extends BlockEntity> RegistryResult<BlockEntityType<?>> register(String id, Supplier<BlockEntityTypeBuilder<T>> builder) {
        return registry.registerBlockEntityType(_id(id), () -> builder.get().build());
    }
}
