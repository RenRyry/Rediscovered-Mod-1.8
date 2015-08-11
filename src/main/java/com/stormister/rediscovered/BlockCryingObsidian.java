package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class BlockCryingObsidian extends Block
{
	private final String name = "CryingObsidian";
	public BlockCryingObsidian()
    {
        super(Material.rock);
        this.setHarvestLevel("pickaxe", 3);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }
    public String getName()
    {
    	return name;
    }
}
