package com.stormister.rediscovered;

import net.minecraft.block.properties.IProperty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockDirtHalfSlab extends BlockDirtSlab{

	private final String name = "DirtHalfSlab";
	public BlockDirtHalfSlab()
    {
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
	public String getName()
    {
    	return name;
    }
	
	@Override
    public final boolean isDouble() {
        return false;
    }
}
