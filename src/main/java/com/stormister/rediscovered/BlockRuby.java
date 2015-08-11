package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRuby extends Block
{
	private final String name = "RubyBlock";
    public BlockRuby(String texture)
    {
        super(Material.rock);
        this.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public Block idDropped(int i, Random random, int j)
    {
        return mod_Rediscovered.RubyBlock;
    }
    
    public String getName()
    {
    	return name;
    }
}
