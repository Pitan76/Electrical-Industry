package net.pitan76.eleind.api.state;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.PropertyUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public interface IActiveHolder {
    default boolean isActive(BlockState state) {
        return PropertyUtil.contains(state, EIProperties.ACTIVE) &&
                PropertyUtil.get(state, EIProperties.ACTIVE);
    }

    default void setActive(World world, BlockPos pos, boolean active) {
        BlockState state = WorldUtil.getBlockState(world, pos);
        WorldUtil.setBlockState(world, pos, PropertyUtil.with(state, EIProperties.ACTIVE, active));
    }
}
