package net.pitan76.eleind.client;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.pitan76.eleind.screen.FuelGeneratorScreenHandler;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawBackgroundArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawForegroundArgs;
import net.pitan76.mcpitanlib.guilib.api.screen.ContainerGuiScreen;

public class FuelGeneratorScreen extends ContainerGuiScreen<FuelGeneratorScreenHandler> {
    public FuelGeneratorScreen(FuelGeneratorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void initOverride() {
        super.initOverride();
    }

    @Override
    protected void drawForegroundOverride(DrawForegroundArgs args) {
        super.drawForegroundOverride(args);
    }

    @Override
    public void drawBackgroundOverride(DrawBackgroundArgs args) {
        super.drawBackgroundOverride(args);
    }
}
