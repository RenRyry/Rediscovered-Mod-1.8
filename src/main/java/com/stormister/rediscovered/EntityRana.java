package com.stormister.rediscovered;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;


public class EntityRana extends EntityGolem
{
    private int randomTickDivider;
    private boolean isPlayingFlag;
    static boolean dead;
    private int field_48120_c;
    private int field_48118_d;
    Village villageObj;

    public EntityRana(World par1World)
    {
        this(par1World, 0);
    }

    public EntityRana(World par1World, int par2)
    {
        super(par1World);
        randomTickDivider = 0;
        isPlayingFlag = false;
        villageObj = null;
        setProfession(par2);
        dead = false;
        ((PathNavigateGround)this.getNavigator()).func_179690_a(true);
        tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, new Predicate()
        {
            public boolean func_179530_a(Entity p_179530_1_)
            {
                return p_179530_1_ instanceof EntityZombie;
            }
            public boolean apply(Object p_apply_1_)
            {
                return this.func_179530_a((Entity)p_apply_1_);
            }
        }, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, new Predicate()
        {
            public boolean func_179530_a(Entity p_179530_1_)
            {
                return p_179530_1_ instanceof EntitySkeleton;
            }
            public boolean apply(Object p_apply_1_)
            {
                return this.func_179530_a((Entity)p_apply_1_);
            }
        }, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, new Predicate()
        {
            public boolean func_179530_a(Entity p_179530_1_)
            {
                return p_179530_1_ instanceof EntitySpider;
            }
            public boolean apply(Object p_apply_1_)
            {
                return this.func_179530_a((Entity)p_apply_1_);
            }
        }, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, new Predicate()
        {
            public boolean func_179530_a(Entity p_179530_1_)
            {
                return p_179530_1_ instanceof EntityTNTPrimed;
            }
            public boolean apply(Object p_apply_1_)
            {
                return this.func_179530_a((Entity)p_apply_1_);
            }
        }, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, new Predicate()
        {
            public boolean func_179530_a(Entity p_179530_1_)
            {
                return p_179530_1_ instanceof EntityCreeper;
            }
            public boolean apply(Object p_apply_1_)
            {
                return this.func_179530_a((Entity)p_apply_1_);
            }
        }, 8.0F, 0.6D, 0.6D));
        tasks.addTask(5, new EntityAIMoveIndoors(this));
        tasks.addTask(6, new EntityAIRestrictOpenDoor(this));
        tasks.addTask(7, new EntityAIOpenDoor(this, true));
        tasks.addTask(8, new EntityAIMoveTowardsRestriction(this, 0.3F));
        tasks.addTask(9, new EntityAIWatchClosest2(this, net.minecraft.entity.player.EntityPlayer.class, 3F, 1.0F));
        tasks.addTask(10, new EntityAIWatchClosest2(this, net.minecraft.entity.passive.EntityVillager.class, 5F, 0.02F));
        tasks.addTask(11, new EntityAIWatchClosest2(this, com.stormister.rediscovered.EntityRana.class, 5F, 0.02F));
        tasks.addTask(12, new EntityAIWatchClosest2(this, com.stormister.rediscovered.EntitySteve.class, 5F, 0.02F));
        tasks.addTask(13, new EntityAIWander(this, 0.3F));
        tasks.addTask(14, new EntityAIWatchClosest(this, net.minecraft.entity.EntityLiving.class, 8F));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }
    
    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(20, Integer.valueOf(0));
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    protected void updateAITasks()
    {
        if (--this.randomTickDivider <= 0)
        {
            BlockPos blockpos = new BlockPos(this);
            this.worldObj.getVillageCollection().addToVillagerPositionList(blockpos);
            this.randomTickDivider = 70 + this.rand.nextInt(50);
            this.villageObj = this.worldObj.getVillageCollection().getNearestVillage(blockpos, 32);

            if (this.villageObj == null)
            {
                this.detachHome();
            }
            else
            {
                BlockPos blockpos1 = this.villageObj.getCenter();
                this.func_175449_a(blockpos1, (int)((float)this.villageObj.getVillageRadius() * 1.0F));
            }
        }

        super.updateAITasks();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
    }
    
//    @Override
//    public boolean getCanSpawnHere() 
//    {
//	    if(worldObj.villageCollectionObj.getVillageList().iterator().hasNext() && worldObj.villageCollectionObj.findNearestVillage((int)this.posX, (int)this.posY, (int)this.posZ, 10) == null) 
//	    {
//	    	return false;
//	    }
//	    return true;
//    }
    
    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (field_48120_c > 0)
        {
            field_48120_c--;
        }

        if (field_48118_d > 0)
        {
            field_48118_d--;
        }

        if (motionX * motionX + motionZ * motionZ > 2.5000002779052011E-007D && rand.nextInt(5) == 0)
        {
        	int i = MathHelper.floor_double(posX);
            int j = MathHelper.floor_double(posY - 0.20000000298023224D - (double)this.getYOffset());
            int k = MathHelper.floor_double(posZ);
            IBlockState l = worldObj.getBlockState(new BlockPos(i, j, k));

        }
    }
    
    ///**
    // * Called when the mob's health reaches 0.
    // */
    //public void onDeath(DamageSource par1DamageSource)
    //{
     //   super.onDeath(par1DamageSource);
//
     //   if (par1DamageSource.getEntity() instanceof EntityPlayer)
     //   {
     //       EntityPlayer entityplayer = (EntityPlayer)par1DamageSource.getEntity();
    //        entityplayer.triggerAchievement(mod_Rediscovered.R);            
    //    }
    //}

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Profession", getProfession());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        setProfession(par1NBTTagCompound.getInteger("Profession"));
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
            return Items.apple;
    }
    
    public Village getVillage()
    {
        return villageObj;
    }

    public int func_48114_ab()
    {
        return field_48120_c;
    }
    
    public void setProfession(int par1)
    {
        dataWatcher.updateObject(20, Integer.valueOf(par1));
    }

    public int getProfession()
    {
        return dataWatcher.getWatchableObjectInt(20);
    }
    
    public void func_48116_a(boolean par1)
    {
        field_48118_d = par1 ? 400 : 0;
        worldObj.setEntityState(this, (byte)11);
    }

    public void setIsPlayingFlag(boolean par1)
    {
        isPlayingFlag = par1;
    }

    public boolean getIsPlayingFlag()
    {
        return isPlayingFlag;
    }
    
    public void setRevengeTarget(EntityLiving par1EntityLiving)
    {
        super.setRevengeTarget(par1EntityLiving);

        if (villageObj != null && par1EntityLiving != null)
        {
            villageObj.addOrRenewAgressor(par1EntityLiving);
        }
    }
    
    public int func_48117_D_()
    {
        return field_48118_d;
    }

    public boolean func_48112_E_()
    {
        return (dataWatcher.getWatchableObjectByte(20) & 1) != 0;
    }

    public void func_48115_b(boolean par1)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(20);

        if (par1)
        {
            dataWatcher.updateObject(20, Byte.valueOf((byte)(byte0 | 1)));
        }
        else
        {
            dataWatcher.updateObject(20, Byte.valueOf((byte)(byte0 & -2)));
        }
    }
}
