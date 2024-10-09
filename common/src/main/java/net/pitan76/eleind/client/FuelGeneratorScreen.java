package net.pitan76.eleind.client;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.pitan76.eleind.screen.FuelGeneratorScreenHandler;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawBackgroundArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawForegroundArgs;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.guilib.GuiTextures;
import net.pitan76.mcpitanlib.guilib.api.render.PartsRenderer;
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
        drawText(args.drawObjectDM, TextUtil.literal(percentage + "%"), 78, 24);
    }

    @Override
    public void drawBackgroundOverride(DrawBackgroundArgs args) {
        super.drawBackgroundOverride(args);
        callDrawTexture(args.drawObjectDM, GuiTextures.BASE_FURNACE_BACKGROUND, x + backgroundWidth / 2 - 8, y + 35 + 16, 0, 166, 16, 16);

        int burnTime = this.handler.blockEntity.burnTime;
        int maxBurnTime = this.handler.blockEntity.maxBurnTime;

        if (burnTime > 0 && maxBurnTime > 0) {

            PartsRenderer.drawBurningBar(args.drawObjectDM, x + backgroundWidth / 2 - 8, y + 35 + 16, burnTime, maxBurnTime);

            //int burnProgress = (int) ((double) burnTime / maxBurnTime * 16);
            //callDrawTexture(args.drawObjectDM, GuiTextures.BASE_FURNACE_BACKGROUND, x + backgroundWidth / 2 - 8, y + 35 + 32 - burnProgress, 0, 166 + 32 - burnProgress, 16, burnProgress);
        }

        PartsRenderer.drawBottom2TopProgressBar(args.drawObjectDM, x + 20, y + 16, 0, 0, 0, 48, 16, 48, this.handler.blockEntity.getEnergyStored(), this.handler.blockEntity.getCapacityEnergy(), CompatIdentifier.of("minecraft", "textures/block/lava_still.png"));
    }
}
