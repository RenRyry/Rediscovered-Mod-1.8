package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLanternPhys extends Block
{
	private final String name = "LanternPhys";
	
	public BlockLanternPhys(String texture)
    {
        super(Material.ground);
        this.setLightLevel(1.0f);
        this.setHardness(0.1f);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        this.setTickRandomly(true);
        this.useNeighborBrightness = true;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World w, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(w, pos);
        return super.getCollisionBoundingBox(w, pos, state);
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return mod_Rediscovered.ItemLantern;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(mod_Rediscovered.ItemLantern);
    }
    
    public String getName()
    {
    	return name;
    }
}
