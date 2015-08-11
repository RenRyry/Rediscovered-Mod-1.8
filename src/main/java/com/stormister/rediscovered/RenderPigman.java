package com.stormister.rediscovered;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderPigman extends RenderBiped
{
	private static final ResourceLocation field_110871_a = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/Pigman.png");

	public RenderPigman(RenderManager p_i46173_1_, ModelBiped par1ModelBiped, float par2)
    {
        this(p_i46173_1_, par1ModelBiped, par2, 1.0F);
    }

    public RenderPigman(RenderManager p_i46173_1_, ModelBiped par1ModelBiped, float par2, float par3)
    {
        super(p_i46173_1_, par1ModelBiped, par2);
        this.modelBipedMain = par1ModelBiped;
        this.field_77070_b = par3;
    }

    protected void func_82438_a(EntityLiving par1EntitySkeleton, float par2)
    {
    }

    public void func_82422_c()
    {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }

    protected ResourceLocation getSquidTextures(EntityLiving par1EntitySquid)
    {
        return field_110871_a;
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getSquidTextures((EntityLiving)par1Entity);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.func_82438_a((EntityLiving)par1EntityLivingBase, par2);
    }
}
