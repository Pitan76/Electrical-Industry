package net.pitan76.eleind.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.eleind.block.Blocks;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder;

import java.util.ArrayList;
import java.util.List;

import static net.pitan76.eleind.ElectricalIndustry._id;
import static net.pitan76.eleind.ElectricalIndustry.registry;

public class BlockEntities {

    private static List<RegistryResult<BlockEntityType<?>>> bookingEnergyStorageBlockEntity = new ArrayList<>();

    public static RegistryResult<BlockEntityType<?>> FUEL_GENERATOR;
    public static RegistryResult<BlockEntityType<?>> ELECTRIC_FURNACE;

    public static void init() {
        FUEL_GENERATOR = register("fuel_generator", BlockEntityTypeBuilder.create(FuelGeneratorBlockEntity::new, Blocks.FUEL_GENERATOR.getOrNull()));
        ELECTRIC_FURNACE = register("electric_furnace", BlockEntityTypeBuilder.create(ElectricFurnaceBlockEntity::new, Blocks.ELECTRIC_FURNACE.getOrNull()));
    }

    public static <T extends BlockEntity> RegistryResult<BlockEntityType<?>> register(String id, BlockEntityTypeBuilder<T> builder) {
        return registry.registerBlockEntityType(_id(id), builder::build);
    }

    public static <T extends BlockEntity> RegistryResult<BlockEntityType<?>> registerWithEnergyStorage(String id, BlockEntityTypeBuilder<T> builder) {
        RegistryResult<BlockEntityType<?>> result = register(id, builder);

        bookingEnergyStorageBlockEntity.add(result);
        return result;
    }

    public static List<RegistryResult<BlockEntityType<?>>> getBookingEnergyStorageBlockEntity() {
        if (bookingEnergyStorageBlockEntity == null)
            throw new IllegalStateException("Already used. This method can only be called once.");

        List<RegistryResult<BlockEntityType<?>>> copy = bookingEnergyStorageBlockEntity;
        bookingEnergyStorageBlockEntity = null;
        return copy;
    }
}
