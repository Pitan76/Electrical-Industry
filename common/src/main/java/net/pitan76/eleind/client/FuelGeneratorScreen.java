package net.pitan76.eleind.client;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.pitan76.eleind.screen.FuelGeneratorScreenHandler;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawBackgroundArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawForegroundArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawMouseoverTooltipArgs;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.guilib.GuiTextures;
import net.pitan76.mcpitanlib.guilib.api.render.PartsRenderer;
import net.pitan76.mcpitanlib.guilib.api.screen.ContainerGuiScreen;

import static net.pitan76.eleind.ElectricalIndustry._id;

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

        int burnTime = this.handler.blockEntity.burnTime;
        int maxBurnTime = this.handler.blockEntity.maxBurnTime;

        if (burnTime > 0 && maxBurnTime > 0)
            PartsRenderer.drawBurningBar(args.drawObjectDM, x + backgroundWidth / 2 - 8, y + 35 + 16, burnTime, maxBurnTime);
        else
            callDrawTexture(args.drawObjectDM, GuiTextures.BASE_FURNACE_BACKGROUND, x + backgroundWidth / 2 - 8, y + 35 + 16, 0, 166, 16, 16);

        drawEnergyBar(args);
    }

    protected void drawEnergyBar(DrawBackgroundArgs args) {
        if (this.handler.blockEntity.getCapacityEnergy() == 0) return;

        PartsRenderer.drawBottom2TopProgressBar(args.drawObjectDM, x + 20, y + 16, 0, 0, 14, 0, 14, 46, this.handler.blockEntity.getEnergyStored(), this.handler.blockEntity.getCapacityEnergy(), _id("textures/container/ei_parts.png"));
    }

    @Override
    public void callDrawMouseoverTooltip(DrawMouseoverTooltipArgs args) {
        super.callDrawMouseoverTooltip(args);

        int x = args.mouseX;
        int y = args.mouseY;

        // Energy amount
        if (isPointInRegion(this.x + 20, this.y + 16, 14, 46, x, y)) {
            // TODO: add tooltip draw helper to MCPitanLib
            args.drawObjectDM.getContext().drawTooltip(this.textRenderer, TextUtil.literal(this.handler.blockEntity.getEnergyStored() + " / " + this.handler.blockEntity.getCapacityEnergy() + " FE"), x, y);
        }

    }

    public static boolean isPointInRegion(int x, int y, int width, int height, int pointX, int pointY) {
        return pointX >= x && pointX < x + width && pointY >= y && pointY < y + height;
    }
}
