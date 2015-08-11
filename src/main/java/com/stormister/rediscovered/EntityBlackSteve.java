package com.stormister.rediscovered;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

import com.google.common.base.Predicate;


public class EntityBlackSteve extends EntityMob
{
    private int field_48119_b;
    Village villageObj;
    private int field_48120_c;
    private int field_48118_d;
    public int type;
    public float animSpeed;
    private int randomTickDivider;

    public EntityBlackSteve(World par1World)
    {
        super(par1World);
        field_48119_b = 0;
        villageObj = null;
        type = rand.nextInt(3);
        //animSpeed = (float)(Math.random() * 0.89999997615814209D + 0.10000000149011612D);
        animSpeed = (float)(0.89999997615814209D);
        ((PathNavigateGround)this.getNavigator()).func_179690_a(true);
        tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.25F, true));
        tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.22F, 32F));
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
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.16F, true));
        tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 0.16F));
        tasks.addTask(8, new EntityAIWander(this, 0.16F));
        tasks.addTask(9, new EntityAIWatchClosest2(this, net.minecraft.entity.player.EntityPlayer.class, 3F, 1.0F));
        tasks.addTask(10, new EntityAIWatchClosest2(this, net.minecraft.entity.passive.EntityVillager.class, 5F, 0.02F));
        tasks.addTask(11, new EntityAIWatchClosest2(this, com.stormister.rediscovered.EntityRana.class, 5F, 0.02F));
        tasks.addTask(12, new EntityAIWatchClosest2(this, com.stormister.rediscovered.EntityBeastBoy.class, 5F, 0.02F));
        tasks.addTask(13, new EntityAILookIdle(this));
        tasks.addTask(14, new EntityAIRestrictOpenDoor(this));
        tasks.addTask(15, new EntityAIOpenDoor(this, true));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        if(mod_Rediscovered.BlackSteveHostile)
        	this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.applyEntityAI();
    }
    
    protected void applyEntityAI()
    {
    	if(mod_Rediscovered.BlackSteveHostile)
    		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(20, Byte.valueOf((byte)0));
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.2D);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }
    
    public static class RenderMD3Steve extends RenderMD3{
        public RenderMD3Steve(boolean anim, String model, String texture){
            super(anim, model, texture + ".png", texture + ".png", texture + ".png"); 
        }

        @Override
        protected int getTextureIndex(Entity e){
            return ((EntityBlackSteve)e).type;
        }

        @Override
        protected float getSpeedMultiplier(Entity e){
            return ((EntityBlackSteve)e).animSpeed;
        }
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

    /**
     * Decrements the entity's air supply when underwater
     */
    protected int decreaseAirSupply(int par1)
    {
        return par1;
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

    public boolean isExplosiveMob(Class par1Class)
    {
        if (func_48112_E_() && (net.minecraft.entity.player.EntityPlayer.class).isAssignableFrom(par1Class))
        {
            return false;
        }
        else
        {
            return super.canAttackClass(par1Class);
        }
    }
    
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        field_48120_c = 10;
        worldObj.setEntityState(this, (byte)4);
        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 7 + rand.nextInt(15));
        return flag;
    }

    public Village getVillage()
    {
        return villageObj;
    }

    public int func_48114_ab()
    {
        return field_48120_c;
    }

    public void func_48116_a(boolean par1)
    {
        field_48118_d = par1 ? 400 : 0;
        worldObj.setEntityState(this, (byte)11);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
            return Items.gunpowder;
    }
    
    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int i = rand.nextInt(3 + par2);

        for (int j = 0; j < i; j++)
        {
            dropItem(Items.string, 1);
        }

        i = rand.nextInt(3 + par2);

        for (int k = 0; k < i; k++)
        {
            dropItem(Items.feather, 1);
        }
        
        i = rand.nextInt(3 + par2);

        for (int k = 0; k < i; k++)
        {
            dropItem(Items.gunpowder, 1);
        }
    }

    protected void dropRareDrop(int par1)
    {
        if (par1 > 0)
        {
            ItemStack itemstack = new ItemStack(Items.flint_and_steel);
            entityDropItem(itemstack, 0.0F);
        }
        else
        {
            dropItem(Items.flint_and_steel, 1);
        }
    }
    
    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
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
