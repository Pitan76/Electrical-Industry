package net.pitan76.eleind.fabric;

import net.pitan76.eleind.ElectricalIndustry;
import net.fabricmc.api.ModInitializer;
import net.pitan76.eleind.fabric.compat.RebornEnergyRegister;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;

public class ElectricalIndustryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new ElectricalIndustry();

        // BlockEntityType登録後にEnergyStorageを登録しないといけない。allRegister()が終わった後に登録する
        if (PlatformUtil.isModLoaded("team_reborn_energy")) {
            RebornEnergyRegister.init();
        }
    }
}