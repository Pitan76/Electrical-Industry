package net.pitan76.eleind.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.pitan76.eleind.client.ElectricalIndustryClient;

public class ElectricalIndustryClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ElectricalIndustryClient.init();
    }
}
