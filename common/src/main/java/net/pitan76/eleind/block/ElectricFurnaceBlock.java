package net.pitan76.eleind.block;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.eleind.block.base.MachineBlock;
import net.pitan76.eleind.block.entity.ElectricFurnaceBlockEntity;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;

public class ElectricFurnaceBlock extends MachineBlock {

    public static final CompatMapCodec<ElectricFurnaceBlock> CODEC = CompatMapCodec.createCodecOfExtendBlock(ElectricFurnaceBlock::new);

    public ElectricFurnaceBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    @Override
    public CompatMapCodec<? extends ElectricFurnaceBlock> getCompatCodec() {
        return CODEC;
    }

    @Override
    public BlockEntity createBlockEntity(TileCreateEvent e) {
        return new ElectricFurnaceBlockEntity(e);
    }

    @Override
    public boolean isTick() {
        return true;
    }

}
