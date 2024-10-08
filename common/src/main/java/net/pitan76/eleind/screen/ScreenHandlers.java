package net.pitan76.eleind.screen;

import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.registry.result.SupplierResult;

import static net.pitan76.eleind.ElectricalIndustry._id;
import static net.pitan76.eleind.ElectricalIndustry.registry;

public class ScreenHandlers {
    public static SupplierResult<ScreenHandlerType<FuelGeneratorScreenHandler>> FUEL_GENERATOR_SCREEN_HANDLER;

    public static void init() {
        FUEL_GENERATOR_SCREEN_HANDLER = register("fuel_generator", FuelGeneratorScreenHandler::new);
    }

    public static <T extends ScreenHandler> SupplierResult<ScreenHandlerType<T>> register(String id, ExtendedScreenHandlerBuilder<T> builder) {
        return SupplierResult.of(registry.registerScreenHandlerTypeSavingGenerics(_id(id), () -> MenuRegistry.ofExtended(builder::create)));
    }

    @FunctionalInterface
    public interface ExtendedScreenHandlerBuilder<T extends ScreenHandler> {
        T create(int syncId, PlayerInventory inventory, PacketByteBuf buf);
    }
}
