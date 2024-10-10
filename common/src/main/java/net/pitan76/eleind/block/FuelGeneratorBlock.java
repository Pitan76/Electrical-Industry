package net.pitan76.eleind.block;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.eleind.block.base.MachineBlock;
import net.pitan76.eleind.block.entity.FuelGeneratorBlockEntity;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;

public class FuelGeneratorBlock extends MachineBlock {

    public static final CompatMapCodec<FuelGeneratorBlock> CODEC = CompatMapCodec.createCodecOfExtendBlock(FuelGeneratorBlock::new);

    public FuelGeneratorBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    @Override
    public CompatMapCodec<? extends FuelGeneratorBlock> getCompatCodec() {
        return CODEC;
    }

    @Override
    public BlockEntity createBlockEntity(TileCreateEvent e) {
        return new FuelGeneratorBlockEntity(e);
    }

    @Override
    public boolean isTick() {
        return true;
    }

}
