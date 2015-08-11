package com.stormister.rediscovered;

import com.google.common.base.Predicate;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCherryLeaves extends BlockLeaves {
	
	private final String name = "CherryLeaves";
	private static final PropertyBool VARIANT_PROPERTY = PropertyBool.create("cherry");

	public BlockCherryLeaves()
    {
        super();
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
	public String getName()
    {
    	return name;
    }
	
	@Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		return this.getDefaultState().withProperty(CHECK_DECAY, false);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(mod_Rediscovered.CherrySapling);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(mod_Rediscovered.CherryLeaves);
    }

	public int damageDropped(IBlockState state) {
		return 0;
	}
	
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return Blocks.leaves.getBlockLayer();
	}

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return 0xE9C2D2;
    }

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		list.add(new ItemStack(itemIn, 1));
	}

	@Override
    public final IBlockState getStateFromMeta(final int meta) {
        IBlockState blockState = this.getDefaultState();
        blockState = blockState.withProperty(VARIANT_PROPERTY, false);

        return blockState;
    }

	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0 | 0;

		if (!((Boolean) state.getValue(DECAYABLE)).booleanValue()) {
			i |= 4;
		}

		if (((Boolean) state.getValue(CHECK_DECAY)).booleanValue()) {
			i |= 8;
		}

		return i;
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {VARIANT_PROPERTY, CHECK_DECAY, DECAYABLE });
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(mod_Rediscovered.CherrySapling);
    }

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		IBlockState state = world.getBlockState(pos);
		return new java.util.ArrayList(
				java.util.Arrays.asList(new ItemStack(this, 1)));
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}
}