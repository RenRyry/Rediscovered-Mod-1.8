package com.stormister.rediscovered;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.ForgeHooksClient;

public class ModelDragonEggRed extends ModelBase
{
  //fields
    ModelRenderer Bulk;
    ModelRenderer Bulk2;
    ModelRenderer Bulk3;
    ModelRenderer Bulk4;
    ModelRenderer Bulk5;
    ModelRenderer Bulk6;
  
  public ModelDragonEggRed()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Bulk = new ModelRenderer(this, 0, 0);
      Bulk.addBox(0F, 0F, 0F, 14, 5, 14);
      Bulk.setRotationPoint(-7F, 16F, -7F);
      Bulk.setTextureSize(64, 32);
      Bulk.mirror = true;
      setRotation(Bulk, 0F, 0F, 0F);
      Bulk2 = new ModelRenderer(this, 0, 0);
      Bulk2.addBox(0F, 0F, 0F, 12, 10, 12);
      Bulk2.setRotationPoint(-6F, 13F, -6F);
      Bulk2.setTextureSize(64, 32);
      Bulk2.mirror = true;
      setRotation(Bulk2, 0F, 0F, 0F);
      Bulk3 = new ModelRenderer(this, 0, 0);
      Bulk3.addBox(0F, 0F, 0F, 10, 2, 10);
      Bulk3.setRotationPoint(-5F, 11F, -5F);
      Bulk3.setTextureSize(64, 32);
      Bulk3.mirror = true;
      setRotation(Bulk3, 0F, 0F, 0F);
      Bulk4 = new ModelRenderer(this, 0, 0);
      Bulk4.addBox(0F, 0F, 0F, 8, 1, 8);
      Bulk4.setRotationPoint(-4F, 10F, -4F);
      Bulk4.setTextureSize(64, 32);
      Bulk4.mirror = true;
      setRotation(Bulk4, 0F, 0F, 0F);
      Bulk5 = new ModelRenderer(this, 0, 0);
      Bulk5.addBox(0F, 0F, 0F, 6, 15, 6);
      Bulk5.setRotationPoint(-3F, 9F, -3F);
      Bulk5.setTextureSize(64, 32);
      Bulk5.mirror = true;
      setRotation(Bulk5, 0F, 0F, 0F);
      Bulk6 = new ModelRenderer(this, 0, 0);
      Bulk6.addBox(0F, 0F, 0F, 4, 1, 4);
      Bulk6.setRotationPoint(-2F, 8F, -2F);
      Bulk6.setTextureSize(64, 32);
      Bulk6.mirror = true;
      setRotation(Bulk6, 0F, 0F, 0F);
  }
  
  public void render(float scale, double x, double y, double z, float ang, float angY, boolean renderLantern, boolean lanternOn, boolean renderHeadTorch)
  {
	  GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5, y + 0.5 , z + 0.5);
      GL11.glPushMatrix();
      GL11.glRotatef(ang, 1f, 0f, 0f);
      GL11.glRotatef(angY, 0f, 1f, 0f);
      Bulk.render(scale);
      Bulk2.render(scale);
      Bulk3.render(scale);
      Bulk4.render(scale);
      Bulk5.render(scale);
      Bulk6.render(scale);

      GL11.glPopMatrix();
      GL11.glPopMatrix();
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void renderAll() 
  {
	  Bulk.render(0.0625F);
	  Bulk2.render(0.0625F);
	  Bulk3.render(0.0625F);
	  Bulk4.render(0.0625F);
	  Bulk5.render(0.0625F);
	  Bulk6.render(0.0625F);
  }
}
