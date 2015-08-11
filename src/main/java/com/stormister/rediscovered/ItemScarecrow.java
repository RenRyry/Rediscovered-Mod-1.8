package com.stormister.rediscovered;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemScarecrow extends Item
{
	private final String name = "Scarecrow";
	
    public ItemScarecrow()
    {
        super();
        GameRegistry.registerItem(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(!world.isRemote){
		    EntityScarecrow sheep = new EntityScarecrow(world);
		    sheep.setLocationAndAngles(pos.getX() + 0.5, pos.getY()+1, pos.getZ() + 0.5, player.rotationYaw, player.rotationPitch);
		    world.spawnEntityInWorld(sheep);
    		--itemStack.stackSize;
	    }
    	else
    	{
    		return false;
    	}
    	return true;
    }
    
    public String getName()
	{
		return name;
	}
}
