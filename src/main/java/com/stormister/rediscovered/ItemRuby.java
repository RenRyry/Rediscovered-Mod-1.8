package com.stormister.rediscovered;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRuby extends Item
{
	private Minecraft mc;
	private final String name = "gemRuby";
	
	public ItemRuby()
    {
        super();
        GameRegistry.registerItem(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }
	
	public String getName()
	{
		return name;
	}
}
