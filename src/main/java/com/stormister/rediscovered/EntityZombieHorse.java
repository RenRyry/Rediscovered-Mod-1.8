package com.stormister.rediscovered;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityZombieHorse extends EntityMob
{
    private int field_110285_bP;
    private float field_110284_bK;
    private float field_110283_bJ;
    private float field_110281_bL;
    private float field_110282_bM;
    private float field_110287_bN;
    private float field_110288_bO;
    public int field_110278_bp;

    public EntityZombieHorse(World par1World)
    {
        super(par1World);
        this.setSize(1.4F, 1.6F);
        this.getNavigator();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityHorse.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.applyEntityAI();
    }
    
    protected void applyEntityAI()
    {
    	this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHorse.class, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
    }
    
    
    @SideOnly(Side.CLIENT)
    public float func_110258_o(float par1)
    {
        return this.field_110284_bK + (this.field_110283_bJ - this.field_110284_bK) * par1;
    }
    
    
    
    public boolean func_110257_ck()
    {
        return this.func_110233_w(4);
    }
    
    public boolean func_110261_ca()
    {
        return this.func_110233_w(8);
    }
    
    public boolean func_110204_cc()
    {
        return this.func_110233_w(32);
    }
    
    public boolean func_110209_cd()
    {
        return this.func_110233_w(64);
    }
    
    private boolean func_110233_w(int par1)
    {
        return (this.dataWatcher.getWatchableObjectInt(16) & par1) != 0;
    }
    
    public float func_110254_bY()
    {
        int i = 1;
        return i >= 0 ? 1.0F : 0.5F + (float)(-24000 - i) / -24000.0F * 0.5F;
    }
    
    @SideOnly(Side.CLIENT)
    public float func_110223_p(float par1)
    {
        return this.field_110282_bM + (this.field_110281_bL - this.field_110282_bM) * par1;
    }

    @SideOnly(Side.CLIENT)
    public float func_110201_q(float par1)
    {
        return this.field_110288_bO + (this.field_110287_bN - this.field_110288_bO) * par1;
    }
    
    private void func_110210_cH()
    {
        this.field_110278_bp = 1;
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.worldObj.isRemote && this.dataWatcher.hasObjectChanged())
        {
            this.dataWatcher.func_111144_e();
        }

        if (this.field_110278_bp > 0 && ++this.field_110278_bp > 8)
        {
            this.field_110278_bp = 0;
        }

        this.field_110284_bK = this.field_110283_bJ;

        if (this.func_110204_cc())
        {
            this.field_110283_bJ += (1.0F - this.field_110283_bJ) * 0.4F + 0.05F;

            if (this.field_110283_bJ > 1.0F)
            {
                this.field_110283_bJ = 1.0F;
            }
        }
        else
        {
            this.field_110283_bJ += (0.0F - this.field_110283_bJ) * 0.4F - 0.05F;

            if (this.field_110283_bJ < 0.0F)
            {
                this.field_110283_bJ = 0.0F;
            }
        }

        this.field_110282_bM = this.field_110281_bL;

        if (this.func_110209_cd())
        {
            this.field_110284_bK = this.field_110283_bJ = 0.0F;
            this.field_110281_bL += (1.0F - this.field_110281_bL) * 0.4F + 0.05F;

            if (this.field_110281_bL > 1.0F)
            {
                this.field_110281_bL = 1.0F;
            }
        }
        else
        {
            this.field_110281_bL += (0.8F * this.field_110281_bL * this.field_110281_bL * this.field_110281_bL - this.field_110281_bL) * 0.6F - 0.05F;

            if (this.field_110281_bL < 0.0F)
            {
                this.field_110281_bL = 0.0F;
            }
        }

        this.field_110288_bO = this.field_110287_bN;

        if (this.func_110233_w(128))
        {
            this.field_110287_bN += (1.0F - this.field_110287_bN) * 0.7F + 0.05F;

            if (this.field_110287_bN > 1.0F)
            {
                this.field_110287_bN = 1.0F;
            }
        }
        else
        {
            this.field_110287_bN += (0.0F - this.field_110287_bN) * 0.7F - 0.05F;

            if (this.field_110287_bN < 0.0F)
            {
                this.field_110287_bN = 0.0F;
            }
        }
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    protected boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild())
        {
            float f = this.getBrightness(1.0F);
            BlockPos blockpos = new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canSeeSky(blockpos))
            {
                boolean flag = true;
                ItemStack itemstack = this.getEquipmentInSlot(4);

                if (itemstack != null)
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

                        if (itemstack.getItemDamage() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setCurrentItemOrArmor(4, (ItemStack)null);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.setFire(8);
                }
            }
        }
        super.onLivingUpdate();
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (super.attackEntityFrom(source, amount))
        {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            if (entitylivingbase == null && source.getEntity() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)source.getEntity();
            }

            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY);
            int k = MathHelper.floor_double(this.posZ);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        boolean flag = super.attackEntityAsMob(p_70652_1_);

        if (flag)
        {
            int i = this.worldObj.getDifficulty().getDifficultyId();

            if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float)i * 0.3F)
            {
                p_70652_1_.setFire(2 * i);
            }
        }

        return flag;
    }
    

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.horse.zombie.idle";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.horse.zombie.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.horse.zombie.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_)
    {
        Block.SoundType soundtype = p_180429_2_.stepSound;

        if (this.worldObj.getBlockState(p_180429_1_.up()).getBlock() == Blocks.snow_layer)
        {
            soundtype = Blocks.snow_layer.stepSound;
        }

        if (!p_180429_2_.getMaterial().isLiquid())
        {
            if (soundtype == Block.soundTypeWood)
            {
                this.playSound("mob.horse.wood", soundtype.getVolume() * 0.15F, soundtype.getFrequency());
            }
            else
            {
                this.playSound("mob.horse.soft", soundtype.getVolume() * 0.15F, soundtype.getFrequency());
            }
        }
    }
    
    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    public void fall(float distance, float damageMultiplier)
    {
        if (distance > 1.0F)
        {
            this.playSound("mob.horse.land", 0.4F, 1.0F);
        }

        int i = MathHelper.ceiling_float_int((distance * 0.5F - 3.0F) * damageMultiplier);

        if (i > 0)
        {
            this.attackEntityFrom(DamageSource.fall, (float)i);

            if (this.riddenByEntity != null)
            {
                this.riddenByEntity.attackEntityFrom(DamageSource.fall, (float)i);
            }

            Block block = this.worldObj.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - (double)this.prevRotationYaw, this.posZ)).getBlock();

            if (block.getMaterial() != Material.air && !this.isSilent())
            {
                Block.SoundType soundtype = block.stepSound;
                this.worldObj.playSoundAtEntity(this, soundtype.getStepSound(), soundtype.getVolume() * 0.5F, soundtype.getFrequency() * 0.75F);
            }
        }
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
        return Items.rotten_flesh;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int j;
        int k;
        j = this.rand.nextInt(3 + par2);

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Items.rotten_flesh, 1);
        }
        

        j = this.rand.nextInt(3 + par2);

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Items.leather, 1);
        }
    }


    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLivingBase par1EntityLivingBase)
    {
        super.onKillEntity(par1EntityLivingBase);

        if (par1EntityLivingBase instanceof EntityHorse)
        {
            if (this.rand.nextBoolean())
            {
                return;
            }

            EntityZombieHorse entityzombie = new EntityZombieHorse(this.worldObj);
            entityzombie.copyLocationAndAnglesFrom(par1EntityLivingBase);
            this.worldObj.removeEntity(par1EntityLivingBase);

            this.worldObj.spawnEntityInWorld(entityzombie);
            this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1016, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
        }
    }


    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte par1)
    {
        if (par1 == 16)
        {
            this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "mob.zombie.remedy", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
        }
        else
        {
            super.handleHealthUpdate(par1);
        }
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return true;
    }
}
