package net.pitan76.eleind.client;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.pitan76.eleind.screen.FuelGeneratorScreenHandler;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawBackgroundArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawForegroundArgs;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.guilib.GuiTextures;
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

        double percentage = (double) this.handler.blockEntity.getEnergyStored() / this.handler.blockEntity.getCapacityEnergy() * 100;

        percentage = Math.floor(percentage * 10) / 10;

        drawText(args.drawObjectDM, TextUtil.literal(percentage + "%"), 80, 12);
    }

    @Override
    public void drawBackgroundOverride(DrawBackgroundArgs args) {
        super.drawBackgroundOverride(args);
        callDrawTexture(args.drawObjectDM, GuiTextures.BASE_FURNACE_BACKGROUND, 0, 0, 0, 166, 16, 16);
    }
}
