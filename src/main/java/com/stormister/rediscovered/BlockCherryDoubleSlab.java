package com.stormister.rediscovered;

import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockCherryDoubleSlab extends BlockCherrySlab{

	private final String name = "CherryDoubleSlab";
	public BlockCherryDoubleSlab()
    {
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }
	public String getName()
    {
    	return name;
    }
	
	@Override
    public final boolean isDouble() {
        return true;
    }
}
