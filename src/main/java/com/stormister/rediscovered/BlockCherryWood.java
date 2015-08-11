package com.stormister.rediscovered;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCherryWood extends Block
{
    /** The type of tree this block came from. */
    public static final String[] woodType = new String[] {"cherry"};
    private final String name = "CherryPlank";

    public BlockCherryWood(String texture)
    {
        super(Material.wood);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public String getName()
    {
    	return name;
    }
}
