package net.pitan76.eleind.fabric.compat;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.eleind.api.energy.IEnergyStorage;
import net.pitan76.eleind.block.entity.BlockEntities;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import team.reborn.energy.api.EnergyStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RebornEnergyRegister {

    private static final Map<IEnergyStorage, TREnergyStorage> energyStorageMap = new HashMap<>();

    public static boolean isInitialized = false;

    public static void init() {
        if (isInitialized) return;
        isInitialized = true;

        List<RegistryResult<BlockEntityType<?>>> tileTypes = BlockEntities.getBookingEnergyStorageBlockEntity();
        for (RegistryResult<BlockEntityType<?>> result : tileTypes) {
            EnergyStorage.SIDED.registerForBlockEntity((blockEntity, dir) -> {
                if (!(blockEntity instanceof IEnergyStorage)) return null;
                IEnergyStorage storage = (IEnergyStorage) blockEntity;

                return getEnergyStorage(storage);
            }, result.getOrNull());
        }
    }

    public static TREnergyStorage getEnergyStorage(IEnergyStorage storage) {
        if (!energyStorageMap.containsKey(storage)) {
            TREnergyStorage trStorage = new TREnergyStorage(storage);
            energyStorageMap.put(storage, trStorage);
            return trStorage;
        }

        return energyStorageMap.get(storage);
    }

    public static void removeEnergyStorage(IEnergyStorage storage) {
        energyStorageMap.remove(storage);
    }

    public static void clearEnergyStorage() {
        energyStorageMap.clear();
    }
}