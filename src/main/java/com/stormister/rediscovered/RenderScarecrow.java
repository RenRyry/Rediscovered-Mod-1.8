package com.stormister.rediscovered;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScarecrow extends RenderLiving
{
    private static final ResourceLocation field_110920_a = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/Scarecrow.png");

    public RenderScarecrow(RenderManager p_i46173_1_, ModelBase par1ModelBase, float par2)
    {
        super(p_i46173_1_, par1ModelBase, par2);
    }

    public void renderChicken(EntityScarecrow par1EntityScarecrow, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityScarecrow, par2, par4, par6, par8, par9);
    }
    
    protected float getDeathMaxRotation(EntityLiving par1EntityLiving)
    {
        return this.getFishDeathRotation((EntityScarecrow)par1EntityLiving);
    }
    
    protected float getFishDeathRotation(EntityScarecrow par1EntitySilverfish)
    {
        return 0.0F;
    }

    public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntityScarecrow)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation getSquidTextures(EntityScarecrow par1EntitySquid)
    {
        return field_110920_a;
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getSquidTextures((EntityScarecrow)par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntityScarecrow)par1Entity, par2, par4, par6, par8, par9);
    }
}
