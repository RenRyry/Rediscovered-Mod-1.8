package com.stormister.rediscovered;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGiant extends EntityMob
{
    public EntityGiant(World par1World)
    {
        super(par1World);
        stepHeight = 5.0F;
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.5F, true));
        tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0F, 32F));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        tasks.addTask(7, new EntityAIWander(this, 0.5D));
        tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        setSize(this.width*2.0F, this.height * 7.0F);
        experienceValue = 30;
    }
    
    public float getEyeHeight()
    {
        return 10.440001F;
    }

	protected void applyEntityAttributes()
	{
	    super.applyEntityAttributes();
	    this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
	    this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0D);
	    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
	    this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(50.0D);
	}
	
	/**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
	public float getBlockPathWeight(BlockPos p_180484_1_)
    {
        return this.worldObj.getLightBrightness(p_180484_1_) - 0.5F;
    }
	
	public void fall(float distance, float damageMultiplier){}
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
            return Items.golden_apple;
    }
    
    protected void dropRareDrop(int par1)
    {
        switch (rand.nextInt(4))
        {
            case 0:
                dropItem(Items.gold_ingot, 1);
                break;

            case 1:
                dropItem(Items.diamond, 1);
                break;
                
            case 2:
            	this.entityDropItem(new ItemStack(Items.golden_apple, 1, 1), 0.0F);
                break;

        }
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY);
            int k = MathHelper.floor_double(this.posZ);  
            return
              this.getBlockPathWeight(new BlockPos(i, j, k)) >= 0.0F &&
              this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() &&
              !this.worldObj.isAnyLiquid(this.getEntityBoundingBox());
    }
    
    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 3;
    }
}
