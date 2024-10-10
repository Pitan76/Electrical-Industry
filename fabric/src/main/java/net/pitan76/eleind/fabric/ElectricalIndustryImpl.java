package net.pitan76.eleind.fabric;

import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.eleind.fabric.compat.RebornEnergyRegister;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;

public class ElectricalIndustryImpl {
    public static void registerEnergyStorage() {
        if (isLoadedTeamRebornEnergy()) {
            RebornEnergyRegister.init();
        }
    }

    public static void clearEnergyStorage() {
        if (isLoadedTeamRebornEnergy()) {
            RebornEnergyRegister.clearEnergyStorage();
        }
    }

    public static void removeEnergyStorage(IEnergyStorage storage) {
        if (isLoadedTeamRebornEnergy()) {
            RebornEnergyRegister.removeEnergyStorage(storage);
        }
    }

    public static boolean isLoadedTeamRebornEnergy() {
        return PlatformUtil.isModLoaded("team_reborn_energy");
    }
}
