package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAbsorb extends Block
{
	private final String name = "Sponge";
	
    public BlockAbsorb()
    {
        super(Material.sponge);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate()
    {
        return 5;
    }

    public byte getRadius(World world, BlockPos pos)
    {
        return (byte)6;
    }

    public int makeStill(World world, int i, int j, int k)
    {
        Material material = world.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial();

        if (material == Material.water && !world.getBlockState(new BlockPos(i, j, k)).getBlock().equals(Blocks.water))
        {
            world.setBlockState(new BlockPos(i, j, k), Blocks.water.getDefaultState(), 0);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int absorbBlock(World world, int i, int j, int k)
    {
        Material material = world.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial();

        if (material == Material.water)
        {
            world.setBlockToAir(new BlockPos(i, j, k));
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Called whenever the block is removed.
     */
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
    	super.breakBlock(world, pos, state);
        modifyWorld(world, pos, false);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        modifyWorld(world, pos, true);
        world.scheduleUpdate(pos, this, tickRate());
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        modifyWorld(world, pos, true);
        world.scheduleUpdate(pos, this, tickRate());
    }

    public void modifyWorld(World world, BlockPos pos, boolean flag)
    {
        byte byte0 = getRadius(world, pos);
        int l = 0;

        for (int i1 = pos.getX() - byte0; i1 <= pos.getX() + byte0; i1++)
        {
            for (int j1 = pos.getY() - byte0; j1 <= pos.getY() + byte0; j1++)
            {
                for (int k1 = pos.getZ() - byte0; k1 <= pos.getZ() + byte0; k1++)
                {
                    if (k1 > pos.getZ() - byte0 && k1 < pos.getZ() + byte0 && j1 > pos.getY() - byte0 && j1 < pos.getY() + byte0 && i1 > pos.getX() - byte0 && i1 < pos.getX() + byte0 && flag)
                    {
                        l += absorbBlock(world, i1, j1, k1);
                        continue;
                    }

                    if (flag)
                    {
                        l += makeStill(world, i1, j1, k1);
                        continue;
                    }

                    if (!flag)
                    {
                        world.notifyBlockOfStateChange(new BlockPos(i1, j1, k1), this);
                    }
                }
            }
        }
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }
     
    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(mod_Rediscovered.Sponge);
    }
    
    public String getName()
    {
    	return name;
    }
}
