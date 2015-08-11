package com.stormister.rediscovered;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemLantern extends Item
{
	private final String name = "ItemLantern";
	private Block block = mod_Rediscovered.LanternPhys;
    public ItemLantern()
    {
        super();
        this.setCreativeTab(CreativeTabs.tabTools);
        this.canRepair = false;
        this.setMaxStackSize(1);
        this.setMaxDamage(32);
        GameRegistry.registerItem(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }

    @Override
    public void onUpdate(ItemStack i, World w, Entity e, int slot, boolean par5)
    {
        // Extinguish the lantern if it is not on the hotbar
        if (e instanceof EntityPlayer)
        {
            EntityPlayer p = (EntityPlayer) e;
            ItemStack blah = new ItemStack(mod_Rediscovered.ItemLantern, 1);
            if (slot >=0 && i.equals(blah))
            {
                p.inventory.mainInventory[slot] = new ItemStack(mod_Rediscovered.ItemLantern, 1);
            }
        }
    }
    
    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (block == Blocks.snow_layer && ((Integer)iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1)
        {
            side = EnumFacing.UP;
        }
        else if (!block.isReplaceable(worldIn, pos))
        {
            pos = pos.offset(side);
        }

        if (!playerIn.canPlayerEdit(pos, side, stack))
        {
            return false;
        }
        else if (stack.stackSize == 0)
        {
            return false;
        }
        else
        {
            if (worldIn.canBlockBePlaced(this.block, pos, false, side, (Entity)null, stack))
            {
                IBlockState iblockstate1 = this.block.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, 0, playerIn);

                if (worldIn.setBlockState(pos, iblockstate1, 3))
                {
                    iblockstate1 = worldIn.getBlockState(pos);

                    if (iblockstate1.getBlock() == this.block)
                    {
                        ItemBlock.setTileEntityNBT(worldIn, pos, stack);
                        iblockstate1.getBlock().onBlockPlacedBy(worldIn, pos, iblockstate1, playerIn, stack);
                    }

                    worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
                    --stack.stackSize;
                    return true;
                }
            }

            return false;
        }
    }

    public String getName()
    {
    	return name;
    }
}
