package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRose extends WorldGenerator
{
    private Block flower;
    private IBlockState field_175915_b;

    public WorldGenRose(Block p_i45632_1_, BlockFlower.EnumFlowerType p_i45632_2_)
    {
        this.setGeneratedBlock(p_i45632_1_, p_i45632_2_);
    }

    public void setGeneratedBlock(Block p_175914_1_, BlockFlower.EnumFlowerType p_175914_2_)
    {
        this.flower = p_175914_1_;
        this.field_175915_b = p_175914_1_.getDefaultState();
    }

    public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos1 = p_180709_3_.add(p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8), p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8));

            if (worldIn.isAirBlock(blockpos1) && (!worldIn.provider.getHasNoSky() || blockpos1.getY() < 255) && worldIn.getBlockState(blockpos1.down()).getBlock().equals(Blocks.grass))
            {
                worldIn.setBlockState(blockpos1, this.field_175915_b, 2);
            }
        }

        return true;
    }
}