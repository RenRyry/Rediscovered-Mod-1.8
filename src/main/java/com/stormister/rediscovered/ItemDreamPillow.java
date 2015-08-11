package com.stormister.rediscovered;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemDreamPillow extends Item
{
	private final String name = "DreamPillow";
    public ItemDreamPillow()
    {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabMisc);
        GameRegistry.registerItem(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }
    
    public String getName()
    {
    	return name;
    }
}
