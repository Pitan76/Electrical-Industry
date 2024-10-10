package net.pitan76.eleind;

import net.pitan76.easyapi.config.JsonConfig;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;

import java.io.File;

public class Config {
    public static File configFile = new File(PlatformUtil.getConfigFolderAsFile(), "eleind.json");
    public static JsonConfig config = new JsonConfig(configFile);

    public static double reborn_energy_conversion_rate = 1.0;

    public static void init() {
        if (!configFile.getParentFile().exists())
            configFile.getParentFile().mkdirs();
        if (!configFile.exists()) {
            config.setDouble("reborn_energy_conversion_rate", 1.0);
            save();
        }

        load();

        reborn_energy_conversion_rate = config.getDoubleOrCreate("reborn_energy_conversion_rate", 1.0);
    }

    public static void load() {
        config.load(configFile);
    }

    public static void save() {
        config.save(configFile);
    }
}
