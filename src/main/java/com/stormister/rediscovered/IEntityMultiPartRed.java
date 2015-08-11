package com.stormister.rediscovered;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public interface IEntityMultiPartRed
{
    World func_82194_d();

    boolean attackEntityFromPart(EntityGoodDragonPart var1, DamageSource var2, float var3);
    boolean mount(EntityPlayer entity);
}
