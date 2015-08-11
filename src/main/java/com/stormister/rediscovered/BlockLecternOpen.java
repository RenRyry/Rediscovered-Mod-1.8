package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLecternOpen extends BlockContainer implements ITileEntityProvider
{
    private Random random = new Random();
    /** Axis aligned bounding box. */
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public final AxisAlignedBB boundingBox;
    public int height = 1;
    public int meta;
    private final String name = "LecternOpen";

    protected BlockLecternOpen()
    {
        super(Material.wood);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
        this.setLightOpacity(0);
        this.useNeighborBrightness = true;
        this.boundingBox = AxisAlignedBB.fromBounds(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
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
    
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }
    
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityLecternOpen();
    }
//    
//    /**
//     * Returns a new instance of a block's tile entity class. Called on placing the block.
//     */
//    @Override
//    public boolean hasTileEntity(IBlockState state)
//    {
//        createNewTileEntity();
//        return this.hasTileEntity(state);
//    }
    
    @Override
    public int getRenderType()
    {
    	return mod_Rediscovered.proxy.getBlockRender(this);
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(world, pos);
        return super.getCollisionBoundingBox(world, pos, state);
    }
    
    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, BlockPos pos)
    {
        this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.2F, 0.875F);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(world, pos, state);
        world.markBlockForUpdate(pos);
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, enumfacing);
        worldIn.setBlockState(pos, state, 3);
    }
    
    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityplayer, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	meta = world.getBlockState(pos).getBlock().getMetaFromState(state);
    	world.setBlockState(pos, mod_Rediscovered.Lectern.getStateFromMeta(meta), 3);
        return true;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(mod_Rediscovered.Lectern);
    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(mod_Rediscovered.Lectern);
    }
    
    public String getName()
    {
    	return name;
    }
}
