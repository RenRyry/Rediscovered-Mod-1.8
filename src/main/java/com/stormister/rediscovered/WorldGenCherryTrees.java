package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCherryTrees extends WorldGenAbstractTree
{
    private final int minTreeHeight;
    private final boolean vinesGrow;
    private final int metaWood;
    private final int metaLeaves;
    private static final String __OBFID = "CL_00000438";

    public WorldGenCherryTrees(boolean p_i2027_1_)
    {
        this(p_i2027_1_, 5, 0, 0, false);
    }

    public WorldGenCherryTrees(boolean p_i2028_1_, int p_i2028_2_, int p_i2028_3_, int p_i2028_4_, boolean p_i2028_5_)
    {
        super(p_i2028_1_);
        this.minTreeHeight = p_i2028_2_;
        this.metaWood = p_i2028_3_;
        this.metaLeaves = p_i2028_4_;
        this.vinesGrow = p_i2028_5_;
    }

    public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_)
    {
        int i = p_180709_2_.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (p_180709_3_.getY() >= 1 && p_180709_3_.getY() + i + 1 <= 256)
        {
            byte b0;
            int l;

            for (int j = p_180709_3_.getY(); j <= p_180709_3_.getY() + 1 + i; ++j)
            {
                b0 = 1;

                if (j == p_180709_3_.getY())
                {
                    b0 = 0;
                }

                if (j >= p_180709_3_.getY() + 1 + i - 2)
                {
                    b0 = 2;
                }

                for (int k = p_180709_3_.getX() - b0; k <= p_180709_3_.getX() + b0 && flag; ++k)
                {
                    for (l = p_180709_3_.getZ() - b0; l <= p_180709_3_.getZ() + b0 && flag; ++l)
                    {
                        if (j >= 0 && j < 256)
                        {
                            if (!this.isReplaceable(worldIn, new BlockPos(k, j, l)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = p_180709_3_.down();
                Block block1 = worldIn.getBlockState(down).getBlock();
                boolean isSoil = block1.canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, (BlockCherrySapling)mod_Rediscovered.CherrySapling);

                if (isSoil && p_180709_3_.getY() < 256 - i - 1)
                {
                    block1.onPlantGrow(worldIn, down, p_180709_3_);
                    b0 = 3;
                    byte b1 = 0;
                    int i1;
                    int j1;
                    int k1;
                    int l1;
                    BlockPos blockpos1;

                    for (l = p_180709_3_.getY() - b0 + i; l <= p_180709_3_.getY() + i; ++l)
                    {
                        i1 = l - (p_180709_3_.getY() + i);
                        j1 = b1 + 1 - i1 / 2;

                        for (k1 = p_180709_3_.getX() - j1; k1 <= p_180709_3_.getX() + j1; ++k1)
                        {
                            l1 = k1 - p_180709_3_.getX();

                            for (int i2 = p_180709_3_.getZ() - j1; i2 <= p_180709_3_.getZ() + j1; ++i2)
                            {
                                int j2 = i2 - p_180709_3_.getZ();

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || p_180709_2_.nextInt(2) != 0 && i1 != 0)
                                {
                                    blockpos1 = new BlockPos(k1, l, i2);
                                    Block block = worldIn.getBlockState(blockpos1).getBlock();

                                    if (block.isAir(worldIn, blockpos1) || block.isLeaves(worldIn, blockpos1) || block.getMaterial() == Material.vine)
                                    {
                                        this.func_175905_a(worldIn, blockpos1, mod_Rediscovered.CherryLeaves, this.metaLeaves);
                                    }
                                }
                            }
                        }
                    }
                    for (l = 0; l < i; ++l)
                    {
                        BlockPos upN = p_180709_3_.up(l);
                        Block block2 = worldIn.getBlockState(upN).getBlock();

                        if (block2.isAir(worldIn, upN) || block2.isLeaves(worldIn, upN) || block2.getMaterial() == Material.vine)
                        {
                            this.func_175905_a(worldIn, p_180709_3_.up(l), mod_Rediscovered.CherryLog, this.metaWood);
                        }
                    }
                    
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    private void func_175923_a(World worldIn, BlockPos p_175923_2_, int p_175923_3_)
    {
        this.func_175905_a(worldIn, p_175923_2_, Blocks.vine, p_175923_3_);
        int j = 4;

        for (p_175923_2_ = p_175923_2_.down(); worldIn.getBlockState(p_175923_2_).getBlock().isAir(worldIn, p_175923_2_) && j > 0; --j)
        {
            this.func_175905_a(worldIn, p_175923_2_, Blocks.vine, p_175923_3_);
            p_175923_2_ = p_175923_2_.down();
        }
    }
}