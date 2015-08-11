package com.stormister.rediscovered;

import java.io.IOException;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderSteve extends RenderLiving
{
  private MD3Renderer renderer;
  private ResourceLocation[] textures;

  public RenderSteve(boolean anim, String modelname, ResourceLocation[] textures)
  {
	//TODO: Possibly change RenderManager to something other than null
      super(null, null, 0.5F);
    if (textures.length > 0)
      this.textures = textures;
    else {
      return;
    }

    try
    {
      this.renderer = new MD3Renderer(new MD3Loader().load(modelname), anim);
    }
    catch (IOException var2) {
      var2.printStackTrace();
    }
  }

  public void func_77031_a(EntityLiving par1EntityLiving, double d, double d1, double d2, float f, float f1)
  {
    renderMD3(par1EntityLiving, (float)d, (float)d1, (float)d2, f, f1);
  }

  protected int getTextureIndex(EntityLiving e) {
    return e.hashCode();
  }

  public final void renderMD3(EntityLiving entity, float f, float f1, float f2, float f3, float f4)
  {
    f3 = f2;
    //Change later
    f2 = f1 - 0;
    f1 = f;
    GL11.glPushMatrix();
    //Change these value if it doesn't work
    float f5 = entity.prevSwingProgress + (entity.swingProgress - entity.prevSwingProgress) * f4;
    GL11.glTranslatef(f1, f2, f3);
    GL11.glRotatef(-f5 + 180.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(0.02F, -0.02F, 0.02F);
    //Change rotationYawHead
    float rotation = handleRotationFloat(entity, f4) * entity.rotationYawHead;
    try {
      int frame1 = (int)rotation % this.renderer.getAnimFrames();
      int frame2 = (frame1 + 1) % this.renderer.getAnimFrames();
      GL11.glShadeModel(7425);
      GL11.glEnable(2977);
      this.renderer.render(frame1, frame2, rotation - (int)rotation);
      GL11.glDisable(2977);
    } catch (Exception e) {
      e.printStackTrace();
    }
    GL11.glPopMatrix();
    //Change float values
    func_77033_b(entity, f1, f2, f3, 1.0F, 1.0F);
  }
  
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
      return this.getSquidTextures((EntityGolem)par1Entity);
  }
  
  protected ResourceLocation getSquidTextures(EntityGolem par1EntitySkyChicken)
  {
      return this.textures[(getTextureIndex(par1EntitySkyChicken) % this.textures.length)];
  }  
  
  protected void func_77033_b(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par7, float par8)
  {
    par4 += 0.5D;
    super.doRender(par1EntityLiving, par2, par4, par6, par7, par8);
  }

  public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
  {
      this.func_77033_b(par1EntityLiving, par2, par4, par6, par8, par9);
  }

  /**
   * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
   * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
   * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
   * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
   */
  public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
  {
      this.func_77033_b((EntityGolem)par1Entity, par2, par4, par6, par8, par9);
  }
}