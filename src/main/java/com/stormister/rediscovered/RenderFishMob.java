package com.stormister.rediscovered;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.ResourceLocation;

public class RenderFishMob extends RenderLiving
{
	private static final ResourceLocation field_110871_a = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/Fish.png");
	
    public RenderFishMob(RenderManager p_i46173_1_, ModelFish modelFish, float f)
    {
        super(p_i46173_1_, new ModelFish(), 0.3F);
    }

    /**
     * Return the silverfish's maximum death rotation.
     */
    protected float getFishDeathRotation(EntityFish par1EntitySilverfish)
    {
        return 180.0F;
    }

    /**
     * Renders the silverfish.
     */
    public void renderFishMob(EntityFish par1EntitySilverfish, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntitySilverfish, par2, par4, par6, par8, par9);
    }

    /**
     * Disallows the silverfish to render the renderPassModel.
     */
    protected int shouldFishRenderPass(EntityFish par1EntitySilverfish, int par2, float par3)
    {
        return -1;
    }

    protected float getDeathMaxRotation(EntityLiving par1EntityLiving)
    {
        return this.getFishDeathRotation((EntityFish)par1EntityLiving);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.shouldFishRenderPass((EntityFish)par1EntityLiving, par2, par3);
    }

    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderFishMob((EntityFish)par1EntityLiving, par2, par4, par6, par8, par9);
    }
    
    protected ResourceLocation getSquidTextures(EntityFish par1EntitySquid)
    {
        return field_110871_a;
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getSquidTextures((EntityFish)par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderFishMob((EntityFish)par1Entity, par2, par4, par6, par8, par9);
    }
}
