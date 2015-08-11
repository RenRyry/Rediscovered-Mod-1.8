package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGear extends Block
{
	private final String name = "GearWall";
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
    protected BlockGear()
    {
        super(Material.circuits);
        this.setTickRandomly(true);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        this.setHarvestLevel("pickaxe", 0);
    }
    
    @Override
    public int tickRate(World p_149738_1_)
    {
    	return 2;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean isFullCube()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
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
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(mod_Rediscovered.ItemGear);
    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return mod_Rediscovered.ItemGear;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos)
    {
        this.setBlockBoundsBasedOnState(world, pos);
        return super.getSelectedBoundingBox(world, pos);
    }
    
    /**
     * Gets if we can place a torch on a block.
     */
    private static boolean canPlaceGearOn(World par1World, BlockPos pos)
    {
        if (par1World.doesBlockHaveSolidTopSurface(par1World, pos))
        {
            return true;
        }
        else
        {
            Block l = par1World.getBlockState(pos).getBlock();
            return (l != null && l.canPlaceTorchOnTop(par1World, pos));
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
    	return 	world.getBlockState(pos.down()).getBlock().isSideSolid(world, pos.down(), EnumFacing.UP) ||
    			world.getBlockState(pos.up()).getBlock().isSideSolid(world, pos.up(), EnumFacing.DOWN) ||
    			world.getBlockState(pos.west()).getBlock().isSideSolid(world, pos.west(), EnumFacing.EAST) ||
        		world.getBlockState(pos.east()).getBlock().isSideSolid(world, pos.east(), EnumFacing.WEST) ||
        		world.getBlockState(pos.north()).getBlock().isSideSolid(world, pos.north(), EnumFacing.SOUTH) ||
        		world.getBlockState(pos.south()).getBlock().isSideSolid(world, pos.south(), EnumFacing.NORTH);
//        		|| canPlaceGearOn(world, pos.down());
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
            int l = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);

            if (l == 0)
            {
            	this.setBlockBounds(0.0F, 0.9375F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
            
            if (l == 2)
            {
            	this.setBlockBounds(0.0F, 0.0F, 0.9375F, 1.0F, 1.0F, 1.0F);
            }

            if (l == 3)
            {
            	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
            }

            if (l == 4)
            {
            	this.setBlockBounds(0.9375F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }

            if (l == 5)
            {
            	this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
            }
    }
    
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
            EnumFacing[] aenumfacing = EnumFacing.values();
            int i = aenumfacing.length;

            for (int j = 0; j < i; ++j)
            {
                EnumFacing enumfacing = aenumfacing[j];
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this);
            }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
            EnumFacing[] aenumfacing = EnumFacing.values();
            int i = aenumfacing.length;

            for (int j = 0; j < i; ++j)
            {
                EnumFacing enumfacing = aenumfacing[j];
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this);
            }
    }
    
    /**
   * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
   * their own) Args: x, y, z, neighbor blockID
   */
  @Override
  public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighbor)
  {
      boolean flag = false;

          int i1 = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
          flag = true;
          
          if (i1 == 1 && world.getBlockState(pos.down()).getBlock().getMaterial().isSolid())
          {
              flag = false;
          }

          if (i1 == 2 && world.getBlockState(pos.south()).getBlock().getMaterial().isSolid())
          {
              flag = false;
          }

          if (i1 == 3 && world.getBlockState(pos.north()).getBlock().getMaterial().isSolid())
          {
              flag = false;
          }

          if (i1 == 4 && world.getBlockState(pos.east()).getBlock().getMaterial().isSolid())
          {
              flag = false;
          }

          if (i1 == 5 && world.getBlockState(pos.west()).getBlock().getMaterial().isSolid())
          {
              flag = false;
          }
          
          if (i1 == 0 && world.getBlockState(pos.up()).getBlock().getMaterial().isSolid())
          {
              flag = false;
          }
      
      if (flag)
      {
    	  this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
          world.setBlockToAir(pos);
      }
      super.onNeighborBlockChange(world, pos, state, neighbor);
  }
    
    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return true;
    }

    public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side)
    {
        return 15;
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
