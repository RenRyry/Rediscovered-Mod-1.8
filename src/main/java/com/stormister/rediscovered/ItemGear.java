package com.stormister.rediscovered;

import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemGear extends Item
{
	private final String name = "ItemGear";
    public ItemGear()
    {
        super();
        GameRegistry.registerItem(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.getBlockState(pos).getBlock().getMaterial().isSolid())
        {
            return false;
        }
        else
        {
            pos = pos.offset(side);

            if (!playerIn.canPlayerEdit(pos, side, stack))
            {
                return false;
            }
            else
            {
                worldIn.setBlockState(pos, mod_Rediscovered.Gear.getDefaultState().withProperty(BlockGear.FACING, side), 3);
                
                --stack.stackSize;

                return true;
            }
        }
    }
    
    public String getName()
    {
    	return name;
    }
}