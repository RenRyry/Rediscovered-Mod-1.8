package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class BlockCherryStairs extends BlockStairs
{
	private final String name = "CherryStairs";
	
	public BlockCherryStairs(IBlockState state)
	{
		super(state);
		GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
		setLightOpacity(0);
	}
	
	public String getName()
    {
    	return name;
    }
}
