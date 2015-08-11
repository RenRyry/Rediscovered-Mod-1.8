package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTable extends Block implements ITileEntityProvider
{
    private final Random random = new Random();
    public final int field_94443_a;
    public boolean filled = false;
    private final String name = "Table";

    protected BlockTable(int par2)
    {
        super(Material.wood);
        this.field_94443_a = par2;
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        this.setLightOpacity(0);
        this.useNeighborBrightness = true;
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.6875F, 0.875F);
        this.isBlockContainer = true;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean isFullCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean isNormalCube()
    {
        return false;
    }
    

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    /**
     * Called on both Client and Server when World#addBlockEvent is called
     */
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam)
    {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, BlockPos pos)
    {
    	this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.6875F, 0.875F);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return true;
    }
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World, int par1)
    {
    	TileEntityTable tileentitychest = new TileEntityTable();
        return tileentitychest;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(mod_Rediscovered.Table);
    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(mod_Rediscovered.Table);
    }
    
    public String getName()
    {
    	return name;
    }
}
