package net.pitan76.eleind.client;

import net.pitan76.eleind.screen.ScreenHandlers;
import net.pitan76.mcpitanlib.guilib.GuiRegistry;

import static net.pitan76.eleind.ElectricalIndustry.MOD_ID;

public class ElectricalIndustryClient {
    public static void init() {
        GuiRegistry.register(MOD_ID, ScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER.get(), FuelGeneratorScreen::new);
    }
}
