package com.stormister.rediscovered;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGoodDragon extends EntityCreature implements IEntityMultiPartRed, IMob
{
    public double targetX;
    public double targetY;
    public double targetZ;

    /**
     * Ring buffer array for the last 64 Y-positions and yaw rotations. Used to calculate offsets for the animations.
     */
    public double[][] ringBuffer = new double[64][3];

    /**
     * Index into the ring buffer. Incremented once per tick and restarts at 0 once it reaches the end of the buffer.
     */
    public int ringBufferIndex = -1;

    /** An array containing all body parts of this dragon */
    public EntityGoodDragonPart[] dragonPartArray;

    /** The head bounding box of a dragon */
    public EntityGoodDragonPart dragonPartHead;

    /** The body bounding box of a dragon */
    public EntityGoodDragonPart dragonPartBody;
    public EntityGoodDragonPart dragonPartTail1;
    public EntityGoodDragonPart dragonPartTail2;
    public EntityGoodDragonPart dragonPartTail3;
    public EntityGoodDragonPart dragonPartTailSpike1;
    public EntityGoodDragonPart dragonPartTailSpike2;
    public EntityGoodDragonPart dragonPartTailSpike3;
    public EntityGoodDragonPart dragonPartTailSpike4;
    public EntityGoodDragonPart dragonPartTailSpike5;
    public EntityGoodDragonPart dragonPartWing1;
    public EntityGoodDragonPart dragonPartWing2;

    /** Animation time at previous tick. */
    public float prevAnimTime = 0.0F;

    /**
     * Animation time, used to control the speed of the animation cycles (wings flapping, jaw opening, etc.)
     */
    public float animTime = 0.0F;

    /** Force selecting a new flight target at next tick if set to true. */
    public boolean forceNewTarget = false;
    public boolean angry = false;
    public boolean renderTailSpike = false;
    
    public int health = 100;

    /**
     * Activated if the dragon is flying though obsidian, white stone or bedrock. Slows movement and animation speed.
     */
    public boolean slowed = false;
    private Entity target;
    public int deathTicks = 0;

    /** The current endercrystal that is healing this dragon */
    public EntityEnderCrystal healingEnderCrystal = null;

    public EntityGoodDragon(World par1World)
    {
        super(par1World);
        this.dragonPartArray = new EntityGoodDragonPart[] {this.dragonPartHead = new EntityGoodDragonPart(this, "head", 6.0F, 6.0F), this.dragonPartBody = new EntityGoodDragonPart(this, "body", 8.0F, 8.0F), this.dragonPartTail1 = new EntityGoodDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail2 = new EntityGoodDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail3 = new EntityGoodDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartWing1 = new EntityGoodDragonPart(this, "wing", 4.0F, 4.0F), this.dragonPartWing2 = new EntityGoodDragonPart(this, "wing", 4.0F, 4.0F), this.dragonPartTailSpike1 = new EntityGoodDragonPart(this, "tail", 2.0F, 2.0F), this.dragonPartTailSpike2 = new EntityGoodDragonPart(this, "tail", 2.0F, 2.0F), this.dragonPartTailSpike3 = new EntityGoodDragonPart(this, "tail", 2.0F, 2.0F), this.dragonPartTailSpike4 = new EntityGoodDragonPart(this, "tail", 2.0F, 2.0F), this.dragonPartTailSpike5 = new EntityGoodDragonPart(this, "tail", 2.0F, 2.0F)};
        this.setHealth(this.getMaxHealth());
        this.setSize(16.0F, 8.0F);
        this.noClip = true;
        this.isImmuneToFire = true;
        this.targetY = 100.0D;
        this.ignoreFrustumCheck = true;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(20, new Byte((byte)0));
    }

    /**
	 * Returns a double[3] array with movement offsets, used to calculate trailing tail/neck positions. [0] = yaw
	 * offset, [1] = y offset, [2] = unused, always 0. Parameters: buffer index offset, partial ticks.
	 */
	public double[] getMovementOffsets(int par1, float par2)
	{
		if (this.getMaxHealth() <= 0)
		{
			par2 = 0.0F;
		}

		par2 = 1.0F - par2;
		int var3 = this.ringBufferIndex - par1 * 1 & 63;
		int var4 = this.ringBufferIndex - par1 * 1 - 1 & 63;
		double[] var5 = new double[3];
		double var6 = this.ringBuffer[var3][0];
		double var8 = MathHelper.wrapAngleTo180_double(this.ringBuffer[var4][0] - var6);
		var5[0] = var6 + var8 * (double)par2;
		var6 = this.ringBuffer[var3][1];
		var8 = this.ringBuffer[var4][1] - var6;
		var5[1] = var6 + var8 * (double)par2;
		var5[2] = this.ringBuffer[var3][2] + (this.ringBuffer[var4][2] - this.ringBuffer[var3][2]) * (double)par2;
		return var5;
	}

	/**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        float f;
        float f1;

        if (this.worldObj.isRemote)
        {
            f = MathHelper.cos(this.animTime * (float)Math.PI * 2.0F);
            f1 = MathHelper.cos(this.prevAnimTime * (float)Math.PI * 2.0F);

            if (f1 <= -0.3F && f >= -0.3F)
            {
                this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
            }
        }

        this.prevAnimTime = this.animTime;
        float f2;

        if (this.getHealth() <= 0.0F)
        {
            f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }
        else
        {
            this.updateDragonEnderCrystal();
            f = 0.2F / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F);
            f *= (float)Math.pow(2.0D, this.motionY);

            if (this.slowed)
            {
                this.animTime += f * 0.5F;
            }
            else
            {
                this.animTime += f;
            }

            this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);

            if (this.ringBufferIndex < 0)
            {
                for (int i = 0; i < this.ringBuffer.length; ++i)
                {
                    this.ringBuffer[i][0] = (double)this.rotationYaw;
                    this.ringBuffer[i][1] = this.posY;
                }
            }

            if (++this.ringBufferIndex == this.ringBuffer.length)
            {
                this.ringBufferIndex = 0;
            }

            this.ringBuffer[this.ringBufferIndex][0] = (double)this.rotationYaw;
            this.ringBuffer[this.ringBufferIndex][1] = this.posY;
            double d0;
            double d1;
            double d2;
            double d3;
            float f3;

            if (this.worldObj.isRemote)
            {
                if (this.newPosRotationIncrements > 0)
                {
                    d3 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
                    d0 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
                    d1 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
                    d2 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
                    this.rotationYaw = (float)((double)this.rotationYaw + d2 / (double)this.newPosRotationIncrements);
                    this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
                    --this.newPosRotationIncrements;
                    this.setPosition(d3, d0, d1);
                    this.setRotation(this.rotationYaw, this.rotationPitch);
                }
            }
            else
            {
                d3 = this.targetX - this.posX;
                d0 = this.targetY - this.posY;
                d1 = this.targetZ - this.posZ;
                d2 = d3 * d3 + d0 * d0 + d1 * d1;

                if (this.target != null)
                {
                    this.targetX = this.target.posX;
                    this.targetZ = this.target.posZ;
                    double d4 = this.targetX - this.posX;
                    double d5 = this.targetZ - this.posZ;
                    double d6 = Math.sqrt(d4 * d4 + d5 * d5);
                    double d7 = 0.4000000059604645D + d6 / 80.0D - 1.0D;

                    if (d7 > 10.0D)
                    {
                        d7 = 10.0D;
                    }

                    this.targetY = this.target.posY + d7;
                }
                else
                {
                    this.targetX += this.rand.nextGaussian() * 2.0D;
                    this.targetZ += this.rand.nextGaussian() * 2.0D;
                }

                if (this.forceNewTarget || d2 < 100.0D || d2 > 22500.0D || this.isCollidedHorizontally || this.isCollidedVertically)
                {
                    this.setNewTarget();
                }

                d0 /= (double)MathHelper.sqrt_double(d3 * d3 + d1 * d1);
                f3 = 0.6F;

                if (d0 < (double)(-f3))
                {
                    d0 = (double)(-f3);
                }

                if (d0 > (double)f3)
                {
                    d0 = (double)f3;
                }

                this.motionY += d0 * 0.10000000149011612D;
                this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
                double d8 = 180.0D - Math.atan2(d3, d1) * 180.0D / Math.PI;
                double d9 = MathHelper.wrapAngleTo180_double(d8 - (double)this.rotationYaw);

                if (d9 > 50.0D)
                {
                    d9 = 50.0D;
                }

                if (d9 < -50.0D)
                {
                    d9 = -50.0D;
                }
                Vec3 vec3 = (new Vec3(this.targetX - this.posX, this.targetY - this.posY, this.targetZ - this.posZ)).normalize();
                d8 = (double)(-MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F));
                Vec3 vec31 = (new Vec3((double)MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F), this.motionY, d8)).normalize();
                float f4 = (float)(vec31.dotProduct(vec3) + 0.5D) / 1.5F;

                if (f4 < 0.0F)
                {
                    f4 = 0.0F;
                }

                this.randomYawVelocity *= 0.8F;
                float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F;
                double d10 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0D + 1.0D;

                if (d10 > 40.0D)
                {
                    d10 = 40.0D;
                }

                this.randomYawVelocity = (float)((double)this.randomYawVelocity + d9 * (0.699999988079071D / d10 / (double)f5));
                this.rotationYaw += this.randomYawVelocity * 0.1F;
                float f6 = (float)(2.0D / (d10 + 1.0D));
                float f7 = 0.06F;
                this.moveFlying(0.0F, -1.0F, f7 * (f4 * f6 + (1.0F - f6)));

                if (this.slowed)
                {
                    this.moveEntity(this.motionX * 0.800000011920929D, this.motionY * 0.800000011920929D, this.motionZ * 0.800000011920929D);
                }
                else
                {
                    this.moveEntity(this.motionX, this.motionY, this.motionZ);
                }

                Vec3 vec32 = (new Vec3(this.motionX, this.motionY, this.motionZ)).normalize();
                float f8 = (float)(vec32.dotProduct(vec31) + 1.0D) / 2.0F;
                f8 = 0.8F + 0.15F * f8;
                this.motionX *= (double)f8;
                this.motionZ *= (double)f8;
                this.motionY *= 0.9100000262260437D;
            }

            this.renderYawOffset = this.rotationYaw;
            this.dragonPartHead.width = this.dragonPartHead.height = 3.0F;
            this.dragonPartTail1.width = this.dragonPartTail1.height = 2.0F;
            this.dragonPartTail2.width = this.dragonPartTail2.height = 2.0F;
            this.dragonPartTail3.width = this.dragonPartTail3.height = 2.0F;
            this.dragonPartBody.height = 3.0F;
            this.dragonPartBody.width = 5.0F;
            this.dragonPartWing1.height = 2.0F;
            this.dragonPartWing1.width = 4.0F;
            this.dragonPartWing2.height = 3.0F;
            this.dragonPartWing2.width = 4.0F;
            f1 = (float)(this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F / 180.0F * (float)Math.PI;
            f2 = MathHelper.cos(f1);
            float f9 = -MathHelper.sin(f1);
            float f10 = this.rotationYaw * (float)Math.PI / 180.0F;
            float f11 = MathHelper.sin(f10);
            float f12 = MathHelper.cos(f10);
            this.dragonPartBody.onUpdate();
            this.dragonPartBody.setLocationAndAngles(this.posX + (double)(f11 * 0.5F), this.posY, this.posZ - (double)(f12 * 0.5F), 0.0F, 0.0F);
            this.dragonPartWing1.onUpdate();
            this.dragonPartWing1.setLocationAndAngles(this.posX + (double)(f12 * 4.5F), this.posY + 2.0D, this.posZ + (double)(f11 * 4.5F), 0.0F, 0.0F);
            this.dragonPartWing2.onUpdate();
            this.dragonPartWing2.setLocationAndAngles(this.posX - (double)(f12 * 4.5F), this.posY + 2.0D, this.posZ - (double)(f11 * 4.5F), 0.0F, 0.0F);

            if (!this.worldObj.isRemote && this.hurtTime == 0)
            {
            	this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing1.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
                this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing2.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
                this.attackEntitiesInList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartHead.getEntityBoundingBox().expand(1.0D, 1.0D, 1.0D)));
            }

            double[] adouble = this.getMovementOffsets(5, 1.0F);
            double[] adouble1 = this.getMovementOffsets(0, 1.0F);
            f3 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F - this.randomYawVelocity * 0.01F);
            float f13 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F - this.randomYawVelocity * 0.01F);
            this.dragonPartHead.onUpdate();
            this.dragonPartHead.setLocationAndAngles(this.posX + (double)(f3 * 5.5F * f2), this.posY + (adouble1[1] - adouble[1]) * 1.0D + (double)(f9 * 5.5F), this.posZ - (double)(f13 * 5.5F * f2), 0.0F, 0.0F);

            for (int j = 0; j < 3; ++j)
            {
                EntityGoodDragonPart entitydragonpart = null;

                if (j == 0)
                {
                    entitydragonpart = this.dragonPartTail1;
                }

                if (j == 1)
                {
                    entitydragonpart = this.dragonPartTail2;
                }

                if (j == 2)
                {
                    entitydragonpart = this.dragonPartTail3;
                }

                double[] adouble2 = this.getMovementOffsets(12 + j * 2, 1.0F);
                float f14 = this.rotationYaw * (float)Math.PI / 180.0F + this.simplifyAngle(adouble2[0] - adouble[0]) * (float)Math.PI / 180.0F * 1.0F;
                float f15 = MathHelper.sin(f14);
                float f16 = MathHelper.cos(f14);
                float f17 = 1.5F;
                float f18 = (float)(j + 1) * 2.0F;
                entitydragonpart.onUpdate();
                entitydragonpart.setLocationAndAngles(this.posX - (double)((f11 * f17 + f15 * f18) * f2), this.posY + (adouble2[1] - adouble[1]) * 1.0D - (double)((f18 + f17) * f9) + 1.5D, this.posZ + (double)((f12 * f17 + f16 * f18) * f2), 0.0F, 0.0F);
            }

//            if (!this.worldObj.isRemote)
//            {
//                this.slowed = this.destroyBlocksInAABB(this.dragonPartHead.boundingBox) | this.destroyBlocksInAABB(this.dragonPartBody.boundingBox);
//            }
        }
    }

    /**
     * Updates the state of the enderdragon's current endercrystal.
     */
    private void updateDragonEnderCrystal()
    {
        if (this.healingEnderCrystal != null)
        {
            if (this.healingEnderCrystal.isDead)
            {
                if (!this.worldObj.isRemote)
                {
                    this.attackEntityFromPart(this.dragonPartHead, DamageSource.generic, 10);
                }

                this.healingEnderCrystal = null;
            }
            else if (this.ticksExisted % 10 == 0 && this.getMaxHealth() < this.getMaxHealth())
            {
                ++health;
            }
        }

        if (this.rand.nextInt(10) == 0)
        {
            float f = 32.0F;
            List var2 = this.worldObj.getEntitiesWithinAABB(EntityEnderCrystal.class, this.getEntityBoundingBox().expand((double)f, (double)f, (double)f));
            EntityEnderCrystal var3 = null;
            double var4 = Double.MAX_VALUE;
            Iterator var6 = var2.iterator();

            while (var6.hasNext())
            {
                EntityEnderCrystal var7 = (EntityEnderCrystal)var6.next();
                double var8 = var7.getDistanceSqToEntity(this);

                if (var8 < var4)
                {
                    var4 = var8;
                    var3 = var7;
                }
            }

            this.healingEnderCrystal = var3;
        }
    }

    /**
	 * Pushes all entities inside the list away from the enderdragon.
	 */
	private void collideWithEntities(List par1List)
	{
		double var2 = (this.dragonPartBody.getEntityBoundingBox().minX + this.dragonPartBody.getEntityBoundingBox().maxX) / 2.0D;
        double var4 = (this.dragonPartBody.getEntityBoundingBox().minZ + this.dragonPartBody.getEntityBoundingBox().maxZ) / 2.0D;
		Iterator var6 = par1List.iterator();

		while (var6.hasNext())
		{
			Entity var7 = (Entity)var6.next();

			if (var7 instanceof EntityLiving)
			{
				double var8 = var7.posX - var2;
				double var10 = var7.posZ - var4;
				double var12 = var8 * var8 + var10 * var10;
				var7.addVelocity(var8 / var12 * 4.0D, 0.20000000298023224D, var10 / var12 * 4.0D);
			}
		}
	}

    /**
     * Attacks all entities inside this list, dealing 5 hearts of damage.
     */
    private void attackEntitiesInList(List par1List)
    {
    	if(angry)
    	{
        	for (int var2 = 0; var2 < par1List.size(); ++var2)
        	{
        		Entity var3 = (Entity)par1List.get(var2);

            	if (var3 instanceof EntityLivingBase)
            	{
            		var3.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0F);
            	}
        	}
    	}
    }

    /**
     * Sets a new target for the flight AI. It can be a random coordinate or a nearby player.
     */
    private void setNewTarget()
    {
        this.forceNewTarget = false;

        if (this.rand.nextInt(3) == 0 && !this.worldObj.playerEntities.isEmpty())
        {
            this.target = (Entity)this.worldObj.playerEntities.get(this.rand.nextInt(this.worldObj.playerEntities.size()));
        }
        else
        {
            boolean flag = false;

            do
            {
                //this.targetX = 0.0D;
                this.targetY = (double)(70.0F + this.rand.nextFloat() * 50.0F);
                //this.targetZ = 0.0D;
                this.targetX += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
                this.targetZ += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
                double d0 = this.posX - this.targetX;
                double d1 = this.posY - this.targetY;
                double d2 = this.posZ - this.targetZ;
                flag = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
            }
            while (!flag);

            this.target = null;
        }
    }

    /**
     * Simplifies the value of a number by adding/subtracting 180 to the point that the number is between -180 and 180.
     */
    private float simplifyAngle(double par1)
    {
        return (float)MathHelper.wrapAngleTo180_double(par1);
    }
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean mount(EntityPlayer par1EntityPlayer)
    {
    	par1EntityPlayer.rotationYaw = this.rotationYaw;
        par1EntityPlayer.rotationPitch = this.rotationPitch;

        if (!this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer))
        {
            par1EntityPlayer.mountEntity(this);
        }
        return true;
    }
    
    public void updateRiderPosition()
    {
	    if (this.riddenByEntity != null)
	    {
		    this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset()-2.5, this.posZ);
	    }
    }

    /**
     * Destroys all blocks that aren't associated with 'The End' inside the given bounding box.
     */
    private boolean destroyBlocksInAABB(AxisAlignedBB p_70972_1_)
    {
        int i = MathHelper.floor_double(p_70972_1_.minX);
        int j = MathHelper.floor_double(p_70972_1_.minY);
        int k = MathHelper.floor_double(p_70972_1_.minZ);
        int l = MathHelper.floor_double(p_70972_1_.maxX);
        int i1 = MathHelper.floor_double(p_70972_1_.maxY);
        int j1 = MathHelper.floor_double(p_70972_1_.maxZ);
        boolean flag = false;
        boolean flag1 = false;

        for (int k1 = i; k1 <= l; ++k1)
        {
            for (int l1 = j; l1 <= i1; ++l1)
            {
                for (int i2 = k; i2 <= j1; ++i2)
                {
                    Block block = this.worldObj.getBlockState(new BlockPos(k1, l1, i2)).getBlock();

                    if (!block.isAir(worldObj, new BlockPos(k1, l1, i2)))
                    {
                        if (block.canEntityDestroy(worldObj, new BlockPos(k1, l1, i2), this) && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
                        {
                            flag1 = this.worldObj.setBlockToAir(new BlockPos(k1, l1, i2)) || flag1;
                        }
                        else
                        {
                            flag = true;
                        }
                    }
                }
            }
        }

        if (flag1)
        {
        	double d1 = p_70972_1_.minX + (p_70972_1_.maxX - p_70972_1_.minX) * (double)this.rand.nextFloat();
            double d2 = p_70972_1_.minY + (p_70972_1_.maxY - p_70972_1_.minY) * (double)this.rand.nextFloat();
            double d0 = p_70972_1_.minZ + (p_70972_1_.maxZ - p_70972_1_.minZ) * (double)this.rand.nextFloat();
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, d1, d2, d0, 0.0D, 0.0D, 0.0D, new int[0]);
        }

        return flag;
    }

    public boolean attackEntityFromPart(EntityGoodDragonPart par1EntityGoodDragonPart, DamageSource par2DamageSource, float par3)
	{
		if (par1EntityGoodDragonPart != this.dragonPartHead)
		{
			par3 = par3 / 4 + 1;
		}

		float var4 = this.rotationYaw * (float)Math.PI / 180.0F;
		float var5 = MathHelper.sin(var4);
		float var6 = MathHelper.cos(var4);
		this.targetX = this.posX + (double)(var5 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
		this.targetY = this.posY + (double)(this.rand.nextFloat() * 3.0F) + 1.0D;
		this.targetZ = this.posZ - (double)(var6 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
		this.target = null;
		
		if (par2DamageSource.getEntity() instanceof EntityPlayer || par2DamageSource.isExplosion())
		{
			angry = true;
			this.func_82195_e(par2DamageSource, par3);
		}
		
		return true;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		return false;
	}
	
	protected boolean func_82195_e(DamageSource par1DamageSource, float par2)
    {
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    /**
     * handles entity death timer, experience orb and particle creation
     */
    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float var1 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float var2 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float var3 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + (double)var1, this.posY + 2.0D + (double)var2, this.posZ + (double)var3, 0.0D, 0.0D, 0.0D);
        }

        int var4;
        int var5;

        if (!this.worldObj.isRemote)
        {
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0)
            {
                var4 = 1000;

                while (var4 > 0)
                {
                    var5 = EntityXPOrb.getXPSplit(var4);
                    var4 -= var5;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
                }
            }

            if (this.deathTicks == 1)
            {
            	this.worldObj.playBroadcastSound(1018, new BlockPos(this), 0);
            }
        }

        this.moveEntity(0.0D, 0.10000000149011612D, 0.0D);
        this.renderYawOffset = this.rotationYaw += 20.0F;

        if (this.deathTicks == 200 && !this.worldObj.isRemote)
        {
            var4 = 2000;

            while (var4 > 0)
            {
                var5 = EntityXPOrb.getXPSplit(var4);
                var4 -= var5;
                this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
            }

            this.func_175499_a(new BlockPos(this.posX, 64.0D, this.posZ));
            this.setDead();
        }
    }

    /**
     * Creates the ender portal leading back to the normal world after defeating the enderdragon.
     */
    private void func_175499_a(BlockPos p_175499_1_)
    {
        boolean flag = true;
        double d0 = 12.25D;
        double d1 = 6.25D;

        for (int i = -1; i <= 32; ++i)
        {
            for (int j = -4; j <= 4; ++j)
            {
                for (int k = -4; k <= 4; ++k)
                {
                    double d2 = (double)(j * j + k * k);

                    if (d2 <= 12.25D)
                    {
                        BlockPos blockpos1 = p_175499_1_.add(j, i, k);

                        if (i < 0)
                        {
                            if (d2 <= 6.25D)
                            {
                                this.worldObj.setBlockState(blockpos1, mod_Rediscovered.CryingObsidian.getDefaultState());
                            }
                        }
                        else if (i > 0)
                        {
                            this.worldObj.setBlockState(blockpos1, Blocks.air.getDefaultState());
                        }
                        else if (d2 > 6.25D)
                        {
                            this.worldObj.setBlockState(blockpos1, mod_Rediscovered.CryingObsidian.getDefaultState());
                        }
                        else
                        {
                            this.worldObj.setBlockState(blockpos1, Blocks.fire.getDefaultState());
                        }
                    }
                }
            }
        }

        this.worldObj.setBlockState(p_175499_1_, mod_Rediscovered.CryingObsidian.getDefaultState());
        this.worldObj.setBlockState(p_175499_1_.up(), mod_Rediscovered.CryingObsidian.getDefaultState());
        BlockPos blockpos2 = p_175499_1_.up(2);
        this.worldObj.setBlockState(blockpos2, mod_Rediscovered.CryingObsidian.getDefaultState());
        this.worldObj.setBlockState(blockpos2.west(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST));
        this.worldObj.setBlockState(blockpos2.east(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST));
        this.worldObj.setBlockState(blockpos2.north(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH));
        this.worldObj.setBlockState(blockpos2.south(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH));
        this.worldObj.setBlockState(p_175499_1_.up(3), mod_Rediscovered.CryingObsidian.getDefaultState());
        this.worldObj.setBlockState(p_175499_1_.up(4), mod_Rediscovered.DragonEggRed.getDefaultState());
    }

    /**
     * Makes the entity despawn if requirements are reached
     */
    protected void despawnEntity() {}

    
    /**
     * Return the Entity parts making up this Entity (currently only for dragons)
     */
    public Entity[] getParts()
    {
        return this.dragonPartArray;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }
    
    public boolean getCanSpawnHere()
    {
//            int i = MathHelper.floor_double(this.posX);
//            int j = MathHelper.floor_double(this.posY);
//            int k = MathHelper.floor_double(this.posZ);  
//            return
//              this.getBlockPathWeight(new BlockPos(i, j, k)) >= 0.0F &&
//              this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() &&
//              !this.worldObj.isAnyLiquid(this.getEntityBoundingBox());
    	return true;
    }
    
    public float getBlockPathWeight(BlockPos p_180484_1_)
    {
        return this.worldObj.getLightBrightness(p_180484_1_) - 0.5F;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the health points of the dragon.
     */
    public int getDragonHealth()
    {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.enderdragon.growl";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.enderdragon.hit";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 5.0F;
    }

    public World func_82194_d()
    {
        return this.worldObj;
    }
}
