package com.stormister.rediscovered;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class EntityGoodDragonPart extends Entity
{
    /** The dragon entity this dragon part belongs to */
    public final IEntityMultiPartRed entityDragonObj;

    /** The name of the Dragon Part */
    public final String name;

    public EntityGoodDragonPart(IEntityMultiPartRed par1, String par2, float par3, float par4)
    {
    	super(par1.func_82194_d());
        this.setSize(par3, par4);
        this.entityDragonObj = par1;
        this.name = par2;
    }

    protected void entityInit() {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return true;
    }
    
    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return this.isEntityInvulnerable(source) ? false : this.entityDragonObj.attackEntityFromPart(this, source, amount);
    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    @Override
    public boolean interactFirst(EntityPlayer par1EntityPlayer)
    {
//    	par1EntityPlayer.addChatComponentMessage(new ChatComponentTranslation("You right clicked me! Ah!", new Object[0]));
    	entityDragonObj.mount(par1EntityPlayer);
    	return true;
    }

    /**
     * Returns true if Entity argument is equal to this Entity
     */
    public boolean isEntityEqual(Entity par1Entity)
    {
        return this == par1Entity || this.entityDragonObj == par1Entity;
    }
}
