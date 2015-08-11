package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDragonEggRed extends Block
{
    private final String name = "DragonEggRed";
    public BlockDragonEggRed()
    {
        super(Material.dragonEgg);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if (worldIn.isRemote)
        {
            return true;
        }
    	else
    	{
	    	EntityGoodDragon var6 = new EntityGoodDragon(worldIn);
			var6.setLocationAndAngles((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
			worldIn.spawnEntityInWorld(var6);
			worldIn.setBlockToAir(pos);
			return true;
    	}
    }

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
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public Block idDropped(int i, Random random, int j)
    {
        return mod_Rediscovered.DragonEggRed;
    }
    
    public String getName()
    {
    	return name;
    }
}