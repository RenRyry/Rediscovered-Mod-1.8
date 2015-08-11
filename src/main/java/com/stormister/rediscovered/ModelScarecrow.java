package com.stormister.rediscovered;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScarecrow extends ModelBase
{
    ModelRenderer Body;
    ModelRenderer Arms;
    ModelRenderer Leg;
    ModelRenderer Head;
  
  public ModelScarecrow()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Body = new ModelRenderer(this, 0, 1);
      Body.addBox(0F, 0F, 0F, 11, 11, 11);
      Body.setRotationPoint(-5F, -1F, -6F);
      Body.setTextureSize(128, 64);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Arms = new ModelRenderer(this, 0, 25);
      Arms.addBox(0F, 0F, 0F, 30, 1, 1);
      Arms.setRotationPoint(-15F, 4F, -1F);
      Arms.setTextureSize(128, 64);
      Arms.mirror = true;
      setRotation(Arms, 0F, 0F, 0F);
      Leg = new ModelRenderer(this, 0, 23);
      Leg.addBox(0F, 0F, 0F, 1, 14, 1);
      Leg.setRotationPoint(0F, 10F, -1F);
      Leg.setTextureSize(128, 64);
      Leg.mirror = true;
      setRotation(Leg, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 45, 0);
      Head.addBox(0F, 0F, 0F, 9, 9, 9);
      Head.setRotationPoint(-4F, -10F, -5F);
      Head.setTextureSize(128, 64);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    Body.render(f5);
    Arms.render(f5);
    Leg.render(f5);
    Head.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
