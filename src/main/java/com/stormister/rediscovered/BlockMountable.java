package com.stormister.rediscovered;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/*ITOS:
 *This class is for your convenience. It is not necessary for your block to be a subclass of this class. 
 *As long as your block has and/or calls the methods specified here it can use the EntityMountableBlock 
 *class. If your class is a subclass to this class you don't have to do anything to be able to mount it, 
 *just make sure that the onBlockActivated methods are not overridden.
*/

public class BlockMountable extends Block
{
	
	//This constructor just pass thing on.
	public BlockMountable(Material material)
	{
		super(material);
	}
	
	//Use this method for a custom mounting height.
	public static boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityplayer, EnumFacing facing)
    {
		return onBlockActivated(world, pos, state, entityplayer, facing, 0.5F, 0.5F, 0.5F, 0);
    }
    
	//This is the main onBlockActivated method. Use it for fully custom mounting positions.
    public static boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityplayer, EnumFacing side, float x, float y, float z, int blah)
    {
		if (!world.isRemote)
		{
			//Looks for EMBs up to 1 block away from the activated block. Hopefully you didn't set the mounting position further away than this.
			List<EntityMountableBlock> listEMB = world.getEntitiesWithinAABB(EntityMountableBlock.class, AxisAlignedBB.fromBounds(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ() + 1.0D).expand(1D, 1D, 1D));
	    	for (EntityMountableBlock entitytocheck : listEMB)
	    	{
	    		//Looks for an EMB created by this block.
	    		if (entitytocheck.orgBlockPosX == pos.getX() && entitytocheck.orgBlockPosY == pos.getY() && entitytocheck.orgBlockPosZ == pos.getZ())
	    		{
	    			entitytocheck.interact(entityplayer);
	    			return true;
	    		}
	    	}
			//Sets coordinates for mounting a north oriented block.
			float mountingX = pos.getX() + x;
			float mountingY = pos.getY() + y;
			float mountingZ = pos.getZ() + z;
			//Changes coordinates for mounting to compensate for none-north block orientation.
//			if(north != south) 
//			{
//				int md = world.getBlockState(new BlockPos(i, j, k));
//				if (md == east) 
//				{
//					mountingX = i + 1 - z; 
//					mountingZ = k + x; 
//				}
//				else if (md == south) 
//				{
//					mountingX = i + 1 - x; 
//					mountingZ = k + 1 - z; 
//				}
//				else if (md == west) 
//				{
//					mountingX = i + z; 
//					mountingZ = k + 1 - x; 
//				}
//			}
	    	//Creates a new EMB if none had been created already or if the old one was bugged.
	    	EntityMountableBlock nemb = new EntityMountableBlock(world, entityplayer, pos.getX(), pos.getY(), pos.getZ(), mountingX, mountingY, mountingZ); 
	    	world.spawnEntityInWorld(nemb);
	    	nemb.interact(entityplayer);
	    	return true;
		}
		return true;
    }
	
}