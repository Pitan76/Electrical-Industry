package net.pitan76.eleind.fabric;

import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.eleind.fabric.compat.RebornEnergyRegister;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;

public class ElectricalIndustryImpl {
    public static void registerEnergyStorage() {
        if (PlatformUtil.isModLoaded("team_reborn_energy")) {
            RebornEnergyRegister.init();
        }
    }

    public static void clearEnergyStorage() {
        if (PlatformUtil.isModLoaded("team_reborn_energy")) {
            RebornEnergyRegister.clearEnergyStorage();
        }
    }

    public static void removeEnergyStorage(IEnergyStorage storage) {
        if (PlatformUtil.isModLoaded("team_reborn_energy")) {
            RebornEnergyRegister.removeEnergyStorage(storage);
        }
    }
}
