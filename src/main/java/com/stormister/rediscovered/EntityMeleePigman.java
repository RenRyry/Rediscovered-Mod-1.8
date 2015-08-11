package com.stormister.rediscovered;

import com.google.common.base.Predicate;

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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;


public class EntityMeleePigman extends EntityMob
{
    private int field_48119_b;
    Village villageObj;
    private int field_48120_c;
    private int field_48118_d;
    public int type;
    public float animSpeed;
    private int randomTickDivider;
    private static final ItemStack defaultHeldItem = new ItemStack(Items.iron_sword, 1);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);

    public EntityMeleePigman(World par1World)
    {
        super(par1World);
        field_48119_b = 0;
        villageObj = null;
        type = rand.nextInt(3);
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
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.16F, true));
        tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 0.16F));
        tasks.addTask(8, new EntityAIWander(this, 0.16F));
        tasks.addTask(9, new EntityAIWatchClosest2(this, net.minecraft.entity.player.EntityPlayer.class, 3F, 1.0F));
        tasks.addTask(10, new EntityAIWatchClosest2(this, net.minecraft.entity.passive.EntityVillager.class, 5F, 0.02F));
        tasks.addTask(11, new EntityAIWatchClosest2(this, com.stormister.rediscovered.EntityRana.class, 5F, 0.02F));
        tasks.addTask(12, new EntityAIWatchClosest2(this, com.stormister.rediscovered.EntityBlackSteve.class, 5F, 0.02F));
        tasks.addTask(13, new EntityAILookIdle(this));
        tasks.addTask(14, new EntityAIRestrictOpenDoor(this));
        tasks.addTask(15, new EntityAIOpenDoor(this, true));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.applyEntityAI();
        if (par1World != null && !par1World.isRemote)
        {
            this.setCombatTask();
        }
    }
    
    protected void applyEntityAI()
    {
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.2D);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
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
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.pig.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.pig.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.pig.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.pig.step", 0.15F, 1.0F);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected Item getDropItem()
    {
        return this.isBurning() ? Items.cooked_porkchop : Items.porkchop;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

        for (int k = 0; k < j; ++k)
        {
            if (this.isBurning())
            {
                this.dropItem(Items.cooked_porkchop, 1);
            }
            else
            {
                this.dropItem(Items.porkchop, 1);
            }
        }
    }
    
    /**
     * sets this entity's combat AI.
     */
    public void setCombatTask()
    {
        this.tasks.addTask(4, this.aiAttackOnCollide);
        
    }
    
    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }
    
    /**
     * Makes entity wear random armor based on difficulty
     */
    protected void addRandomArmor()
    {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
    }
    
    /**
     * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is armor. Params: Item, slot
     */
    public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack)
    {
        super.setCurrentItemOrArmor(par1, par2ItemStack);

        if (!this.worldObj.isRemote && par1 == 0)
        {
            this.setCombatTask();
        }
    }
    
    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setCombatTask();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
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
