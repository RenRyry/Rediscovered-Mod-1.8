package com.stormister.rediscovered;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderZombieHorse extends RenderLiving
{
	private static final ResourceLocation field_110871_a = new ResourceLocation("textures/entity/horse/horse_zombie.png");
		
        public RenderZombieHorse(RenderManager p_i46170_1_, ModelBase par1ModelBase, float par2)
        {
                super(p_i46170_1_, par1ModelBase, par2);
        }

        public void renderMyExample(EntityZombieHorse par1EntityExampleH, double par2, double par4, double par6, float par8, float par9)
        {
                super.doRender(par1EntityExampleH, par2, par4, par6, par8, par9);
        }

        public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
        {
                renderMyExample((EntityZombieHorse)par1EntityLiving, par2, par4, par6, par8, par9);
        }

        public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
        {
                renderMyExample((EntityZombieHorse)par1Entity, par2, par4, par6, par8, par9);
        }
        
        // Added these two functions, to allow for scaling.
        protected void preRenderScale(EntityZombieHorse par1EntityExampleH, float par2)
        {
                // These values are x,y,z scale. Where 1.0F = 100%
                GL11.glScalef(1.0F, 1.0F, 1.0F);
        }
        
        protected ResourceLocation getSquidTextures(EntityZombieHorse par1EntitySquid)
        {
            return field_110871_a;
        }
        
        /**
         * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
         */
        protected ResourceLocation getEntityTexture(Entity par1Entity)
        {
            return this.getSquidTextures((EntityZombieHorse)par1Entity);
        }
        
        protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
        {
                preRenderScale((EntityZombieHorse)par1EntityLiving, par2);
        }
}