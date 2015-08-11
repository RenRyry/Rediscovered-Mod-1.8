package com.stormister.rediscovered;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/*ITOS:
 *This class acts as a bridge between the mountable block and the player.
 *This entity is what the player actually mounts instead of the block.
 *An entity of this class is created by the mountable block upon activation
 *and is killed when it's no longer used.
*/

public class EntityMountableBlock extends Entity
{
	
	//These variables keep track of the block that created the entity.
	protected int orgBlockPosX;
	protected int orgBlockPosY;
	protected int orgBlockPosZ;
	protected IBlockState orgBlockID;
	protected float blahx, blahy, blahz;
	
	public EntityMountableBlock (World world)
	{
		super(world);
        noClip = true;
        preventEntitySpawning = true;
        width = 0F;
        height = 0F;
	}
	
	public EntityMountableBlock (World world, double d, double d1, double d2)
	{
        super(world);
        noClip = true;
        preventEntitySpawning = true;
        width = 0F;
        height = 0F;
        setPosition(d, d1, d2);
	}

	//This constructor is called by the mountable block.
	public EntityMountableBlock (World world, EntityPlayer entityplayer, int i, int j, int k, float mountingX, float mountingY, float mountingZ)
	{
		super(world);
        noClip = true;
        preventEntitySpawning = true;
        width = 0.0F;
        height = 0.0F;
        
    	orgBlockPosX = i;
    	orgBlockPosY = j;
    	orgBlockPosZ = k;
    	orgBlockID = world.getBlockState(new BlockPos(i, j, k));
    	
    	blahx = mountingX;
    	blahy = mountingY;
    	blahz = mountingZ;
    	setPosition(mountingX, mountingY, mountingZ);
	}
	
	//This method handles mounting and dismounting.
    public boolean interact(EntityPlayer entityplayer)
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != entityplayer)
        {
        	return true;
        }
        else
        {
        	if (!this.worldObj.isRemote)
        	{
        		entityplayer.mountEntity(this);
        	}
        	return true;
        }
    }
    
	//This method is mostly a simplified version of the one in Entity but it also deletes unused EMBs.
    @Override
    public void onEntityUpdate()
    {
    	this.worldObj.theProfiler.startSection("entityBaseTick");
        if(riddenByEntity == null || riddenByEntity.isDead)
        {
			this.setDead();
        }
        if ((this.posY == Math.floor(this.posY)) && !Double.isInfinite(this.posY)) {
            this.posY-=0.5F;
        }
        ticksExisted++;
        this.worldObj.theProfiler.endSection();
    }
    
    @Override
    public boolean canBePushed()
    {
        return false;
    }
    
    //The following methods are required by the Entity class but I don't know what they are for.
    @Override
    public void entityInit() {}
    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}
    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}
	
}
