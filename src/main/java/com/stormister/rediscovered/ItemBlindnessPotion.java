package com.stormister.rediscovered;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlindnessPotion extends Item
{

	private final String name = "Blindness";
    public ItemBlindnessPotion(int par2, float par3, boolean par4, String texture)
    {
        super();
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerItem(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
	    
	            
	    entityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 30 * 20, 6));
	    
	    if (!entityPlayer.capabilities.isCreativeMode)
        {
	    	itemStack.stackSize--;
            if (itemStack.stackSize <= 0)
            {
                return new ItemStack(Items.glass_bottle);
            }

            entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }
	
	    return itemStack;
    }    
    
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.DRINK;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            return par1ItemStack;
        
    }
    
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
            par3List.add("\u00a77" + "Blindness (0:30)");                
        
    }
    
    public String getName()
    {
    	return name;
    }
}
