package net.pitan76.eleind.screen;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.registry.result.SupplierResult;
import net.pitan76.mcpitanlib.guilib.GuiRegistry;

import static net.pitan76.eleind.ElectricalIndustry._id;
import static net.pitan76.eleind.ElectricalIndustry.registry;

public class ScreenHandlers {
    public static SupplierResult<ScreenHandlerType<FuelGeneratorScreenHandler>> FUEL_GENERATOR_SCREEN_HANDLER;

    public static void init() {
        FUEL_GENERATOR_SCREEN_HANDLER = register("fuel_generator", new SimpleScreenHandlerTypeBuilder<>(FuelGeneratorScreenHandler::new));
    }

    public static <T extends ScreenHandler> SupplierResult<ScreenHandlerType<T>> register(String id, SimpleScreenHandlerTypeBuilder<T> builder) {
        return GuiRegistry.register(registry, _id(id), builder);
    }
}
