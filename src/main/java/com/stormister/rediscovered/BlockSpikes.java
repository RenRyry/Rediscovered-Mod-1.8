package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSpikes extends Block
{
    private Random random = new Random();
    /** Axis aligned bounding box. */
    public final AxisAlignedBB boundingBox;
    private final String name = "Spikes";
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public int height = 1;

    protected BlockSpikes()
    {
        super(Material.wood);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        this.useNeighborBrightness = true;
        this.setLightOpacity(0);
        this.setBlockBounds(0.05F, 0.05F, 0.05F, 0.95F, 0.95F, 0.95F);
        this.boundingBox = AxisAlignedBB.fromBounds(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.setCreativeTab(CreativeTabs.tabDecorations);
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
    
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
    
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity)
    {
    	if(entity instanceof EntityLivingBase){
    		entity.attackEntityFrom(DamageSource.cactus, 4.0F);
    	}
    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(world, pos);
        return super.getCollisionBoundingBox(world, pos, state);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return true;
    }
    
    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    @Override
    public int getMobilityFlag()
    {
        return 0;
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
        return new ItemStack(mod_Rediscovered.Spikes);
    }
    
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, getFacingFromEntity(worldIn, pos, placer));
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(worldIn, pos, placer)), 2);
    }
    
    public static EnumFacing getFacingFromEntity(World worldIn, BlockPos clickedBlock, EntityLivingBase entityIn)
    {
        if (MathHelper.abs((float)entityIn.posX - (float)clickedBlock.getX()) < 2.0F && MathHelper.abs((float)entityIn.posZ - (float)clickedBlock.getZ()) < 2.0F)
        {
            double d0 = entityIn.posY + (double)entityIn.getEyeHeight();

            if (d0 - (double)clickedBlock.getY() > 2.0D)
            {
                return EnumFacing.UP;
            }

            if ((double)clickedBlock.getY() - d0 > 0.0D)
            {
                return EnumFacing.DOWN;
            }
        }

        return entityIn.getHorizontalFacing().getOpposite();
    }

    public int getRenderType()
    {
        return 3;
    }

    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.UP);
    }

    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }
    
    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        worldIn.setBlockState(pos, mod_Rediscovered.Spikes.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        worldIn.setBlockState(pos, mod_Rediscovered.Spikes.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
    }

    @SideOnly(Side.CLIENT)

    static final class SwitchEnumFacing
        {
            static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];

            static
            {
                try
                {
                    FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 1;
                }
                catch (NoSuchFieldError var4)
                {
                    ;
                }

                try
                {
                    FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 2;
                }
                catch (NoSuchFieldError var3)
                {
                    ;
                }

                try
                {
                    FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 3;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 4;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }
    
    public String getName()
    {
    	return name;
    }
}
