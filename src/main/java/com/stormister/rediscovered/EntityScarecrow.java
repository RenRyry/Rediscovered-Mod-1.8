package com.stormister.rediscovered;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityScarecrow extends EntityAnimal
{
    public float field_70886_e;
    public float field_70884_g;
    public float field_70888_h;
    public float field_70889_i = 1.0F;

    public EntityScarecrow(World par1World)
    {
        super(par1World);
        this.preventEntitySpawning = true;
        this.setSize(0.5F, 2.0F);
    }
    
    public EntityScarecrow(World par1World, EntityPlayer player)
    {
    	super(par1World);
    	this.rotationYaw = player.rotationYaw;
    	this.rotationPitch = player.rotationPitch;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }
    
    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    @Override
    public boolean canBePushed()
    {
        return false;
    }
    
    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return true;
    }
    
    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
    }
    
    protected void updateAITasks()
    {
        super.updateAITasks();
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1) {}

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
        return mod_Rediscovered.Scarecrow;
    }
    

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        this.dropItem(mod_Rediscovered.Scarecrow, 1);
    }
    
    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
    	if (!this.worldObj.isRemote && !this.isDead)
        {
            this.setDead();
            this.dropItem(mod_Rediscovered.Scarecrow, 1);
        }
    	return true;
    }
    
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            if (this.worldObj.handleMaterialAcceleration(this.getBoundingBox().expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this))
            {
            	this.setDead();
            	this.dropItem(mod_Rediscovered.Scarecrow, 1);
            }
        }
        motionX *= 0.0;
        motionZ *= 0.0;
        super.onLivingUpdate();
    }
    
    public void onUpdate()
    {
         
    }

    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public EntityScarecrow spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
        return new EntityScarecrow(this.worldObj);
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }
}
