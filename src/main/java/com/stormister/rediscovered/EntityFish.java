package com.stormister.rediscovered;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EntityFish extends EntityWaterMob
{
	public float squidPitch;
    public float prevSquidPitch;
    public float squidYaw;
    public float prevSquidYaw;
    public float field_70867_h;
    public float field_70868_i;

    /** angle of the tentacles in radians */
    public float tentacleAngle = 0.0F;

    /** the last calculated angle of the tentacles in radians */
    public float lastTentacleAngle = 0.0F;
    private float randomMotionSpeed = 0.0F;
    private float field_70864_bA = 0.0F;
    private float field_70871_bB = 0.0F;
    private float randomMotionVecX = 0.0F;
    private float randomMotionVecY = 0.0F;
    private float randomMotionVecZ = 0.0F;

    public EntityFish(World par1World)
    {
        super(par1World);
        this.setSize(0.5F, 0.5F);
        this.field_70864_bA = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
        this.tasks.addTask(0, new EntityFish.AIMoveRandom());
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return null;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
        return this.isBurning() ? Items.cooked_fish : Items.fish;
    }
    
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
        Item j = this.getDropItem();

        this.dropItem(j, 1);
        
    }

    /**
     * Checks if this entity is inside water (if inWater field is true as a result of handleWaterMovement() returning
     * true)
     */
    public boolean isInWater()
    {
        return this.worldObj.handleMaterialAcceleration(this.getEntityBoundingBox().expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.prevSquidPitch = this.squidPitch;
        this.prevSquidYaw = this.squidYaw;
        this.field_70868_i = this.field_70867_h;
        this.lastTentacleAngle = this.tentacleAngle;
        this.field_70867_h += this.field_70864_bA;

        if (this.field_70867_h > ((float)Math.PI * 2F))
        {
            this.field_70867_h -= ((float)Math.PI * 2F);

            if (this.rand.nextInt(10) == 0)
            {
                this.field_70864_bA = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
            }
        }

        if (this.isInWater())
        {
            float f;

            if (this.field_70867_h < (float)Math.PI)
            {
                f = this.field_70867_h / (float)Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;

                if ((double)f > 0.75D)
                {
                    this.randomMotionSpeed = 1.0F;
                    this.field_70871_bB = 1.0F;
                }
                else
                {
                    this.field_70871_bB *= 0.8F;
                }
            }
            else
            {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.9F;
                this.field_70871_bB *= 0.99F;
            }

            if (!this.worldObj.isRemote)
            {
                this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
            }

            f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.squidYaw += (float)Math.PI * this.field_70871_bB * 1.5F;
            this.squidPitch += (-((float)Math.atan2((double)f, this.motionY)) * 180.0F / (float)Math.PI - this.squidPitch) * 0.1F;
        }
        else
        {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.field_70867_h)) * (float)Math.PI * 0.25F;

            if (!this.worldObj.isRemote)
            {
                this.motionX = 0.0D;
                this.motionY -= 0.08D;
                this.motionY *= 0.9800000190734863D;
                this.motionZ = 0.0D;
            }

            this.squidPitch = (float)((double)this.squidPitch + (double)(-90.0F - this.squidPitch) * 0.02D);
        }
    }

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_)
    {
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_)
    {
    	super.handleHealthUpdate(p_70103_1_);
    }

    public void func_175568_b(float p_175568_1_, float p_175568_2_, float p_175568_3_)
    {
        this.randomMotionVecX = p_175568_1_;
        this.randomMotionVecY = p_175568_2_;
        this.randomMotionVecZ = p_175568_3_;
    }

    public boolean func_175567_n()
    {
        return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
//    public boolean getCanSpawnHere()
//    {
//        return this.posY > 45.0D && this.posY < 63.0D && super.getCanSpawnHere();
//    }
    class AIMoveRandom extends EntityAIBase
    {
        private EntityFish field_179476_a = EntityFish.this;

        public boolean shouldExecute()
        {
            return true;
        }

        public void updateTask()
        {
            int i = this.field_179476_a.getAge();

            if (i > 100)
            {
                this.field_179476_a.func_175568_b(0.0F, 0.0F, 0.0F);
            }
            else if (this.field_179476_a.getRNG().nextInt(50) == 0 || !this.field_179476_a.inWater || !this.field_179476_a.func_175567_n())
            {
                float f = this.field_179476_a.getRNG().nextFloat() * (float)Math.PI * 2.0F;
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.field_179476_a.getRNG().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;
                this.field_179476_a.func_175568_b(f1, f2, f3);
            }
        }
    }
}
