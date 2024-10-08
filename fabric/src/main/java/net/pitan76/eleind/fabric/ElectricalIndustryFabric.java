package net.pitan76.eleind.fabric;

import net.pitan76.eleind.ElectricalIndustry;
import net.fabricmc.api.ModInitializer;

public class ElectricalIndustryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new ElectricalIndustry();
    }
}