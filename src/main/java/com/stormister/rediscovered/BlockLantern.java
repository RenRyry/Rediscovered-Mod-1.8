package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockLantern extends Block
{
	private final String name = "Lantern";
	
    public BlockLantern()
    {
        super(Material.air);
        this.setLightLevel(1.0f);
        this.setHardness(0.1f);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setTickRandomly(true);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public void updateTick(World w, BlockPos pos, IBlockState state, Random rand)
    {
        if (w.getBlockState(pos) == mod_Rediscovered.Lantern)
        {
            w.setBlockState(pos, Blocks.air.getDefaultState());
        }
    }

    @Override
    public boolean isAir(IBlockAccess world, BlockPos pos)
    {
        if (world.getBlockState(pos).equals(this))
        {
            return true;
        }

        return false;
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return null;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
    
    public String getName()
    {
    	return name;
    }
    
}
