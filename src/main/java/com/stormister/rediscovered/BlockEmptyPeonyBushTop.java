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
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEmptyPeonyBushTop extends Block
{
	private final String name = "EmptyPeonyBushTop";
	
    protected BlockEmptyPeonyBushTop(Material p_i45395_1_)
    {
        super(p_i45395_1_);
        this.setTickRandomly(true);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        float f = 0.2F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    protected BlockEmptyPeonyBushTop()
    {
        this(Material.plants);
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean isFullCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean isNormalCube()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
    }

    /**
     * is the block grass, dirt or farmland
     */
    protected boolean canPlaceBlockOn(Block ground)
    {
        return ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.farmland;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
        this.checkAndDropBlock(worldIn, pos);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos);
    }

    /**
     * checks if the block can stay, if not drop as item
     */
    protected void checkAndDropBlock(World world, BlockPos pos)
    {
        if (!this.canBlockStay(world, pos))
        {
        	world.setBlockToAir(pos);
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World p_149718_1_, BlockPos pos)
    {
        return  p_149718_1_.getBlockState(pos.down()).equals(mod_Rediscovered.EmptyPeonyBush.getDefaultState());
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(mod_Rediscovered.EmptyPeonyBush);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(mod_Rediscovered.EmptyPeonyBush);
    }
    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
    	worldIn.setBlockToAir(pos.down());
    }
    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
    	worldIn.setBlockToAir(pos.down());
    }


    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
    }
    
    public String getName()
    {
    	return name;
    }
}