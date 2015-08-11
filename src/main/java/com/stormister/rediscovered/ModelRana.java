package com.stormister.rediscovered;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelRana extends ModelBiped
{
  //fields
    ModelRenderer froghat;
    ModelRenderer leftfrogeye;
    ModelRenderer backpack;
    ModelRenderer toprighttoe;
    ModelRenderer toplefttoe;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer lefttoe;
    ModelRenderer righttoe;
    ModelRenderer rightfrogeye;
    ModelRenderer rightbandpigtail;
    ModelRenderer leftbandpigtail;
    ModelRenderer rightpigtail;
    ModelRenderer leftpigtail;
  
  public ModelRana()
  {
    textureWidth = 256;
    textureHeight = 128;
    
      froghat = new ModelRenderer(this, 96, 17);
      froghat.addBox(-5F, -6F, -5F, 10, 10, 10);
      froghat.setRotationPoint(0F, 3F, 0F);
      froghat.setTextureSize(256, 128);
      froghat.mirror = true;
      setRotation(froghat, 0F, 0F, 0F);
      leftfrogeye = new ModelRenderer(this, 48, 70);
      leftfrogeye.addBox(1F, -9F, -2F, 4, 3, 3);
      leftfrogeye.setRotationPoint(0F, 3F, 0F);
      leftfrogeye.setTextureSize(256, 128);
      leftfrogeye.mirror = true;
      setRotation(leftfrogeye, 0F, 0F, 0F);
      backpack = new ModelRenderer(this, 128, 14);
      backpack.addBox(0F, 0F, 0F, 4, 5, 1);
      backpack.setRotationPoint(-2F, 4F, 2F);
      backpack.setTextureSize(256, 128);
      backpack.mirror = true;
      setRotation(backpack, 0F, 0F, 0F);
      toprighttoe = new ModelRenderer(this, 12, 16);
      toprighttoe.addBox(0F, 0F, 0F, 3, 7, 1);
      toprighttoe.setRotationPoint(-3F, 15F, -3F);
      toprighttoe.setTextureSize(256, 128);
      toprighttoe.mirror = true;
      setRotation(toprighttoe, 0F, 0F, 0F);
      toplefttoe = new ModelRenderer(this, 12, 16);
      toplefttoe.addBox(0F, 0F, 0F, 3, 7, 1);
      toplefttoe.setRotationPoint(0F, 15F, -3F);
      toplefttoe.setTextureSize(256, 128);
      toplefttoe.mirror = true;
      setRotation(toplefttoe, 0F, 0F, 0F);
      head = new ModelRenderer(this, 96, 0);
      head.addBox(-4F, -5F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, 3F, 0F);
      head.setTextureSize(256, 128);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-4F, 0F, -2F, 8, 12, 4);
      body.setRotationPoint(0F, 3F, 0F);
      body.setTextureSize(256, 128);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 0, 16);
      rightarm.addBox(-3F, -2F, -2F, 3, 12, 3);
      rightarm.setRotationPoint(-3F, 5F, 1F);
      rightarm.setTextureSize(256, 128);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 0, 16);
      leftarm.addBox(-1F, -2F, -2F, 3, 12, 3);
      leftarm.setRotationPoint(5F, 5F, 1F);
      leftarm.setTextureSize(256, 128);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 136, 20);
      rightleg.addBox(0F, 0F, 0F, 3, 9, 3);
      rightleg.setRotationPoint(-3F, 15F, -2F);
      rightleg.setTextureSize(256, 128);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 136, 20);
      leftleg.addBox(0F, 0F, 0F, 3, 9, 3);
      leftleg.setRotationPoint(0F, 15F, -2F);
      leftleg.setTextureSize(256, 128);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      lefttoe = new ModelRenderer(this, 24, 6);
      lefttoe.addBox(0F, 0F, 0F, 3, 9, 1);
      lefttoe.setRotationPoint(0F, 15F, -3F);
      lefttoe.setTextureSize(256, 128);
      lefttoe.mirror = true;
      setRotation(lefttoe, 0F, 0F, 0F);
      righttoe = new ModelRenderer(this, 24, 6);
      righttoe.addBox(0F, 0F, 0F, 3, 9, 1);
      righttoe.setRotationPoint(-3F, 15F, -3F);
      righttoe.setTextureSize(256, 128);
      righttoe.mirror = true;
      setRotation(righttoe, 0F, 0F, 0F);
      rightfrogeye = new ModelRenderer(this, 48, 64);
      rightfrogeye.addBox(-5F, -9F, -2F, 4, 3, 3);
      rightfrogeye.setRotationPoint(0F, 3F, 0F);
      rightfrogeye.setTextureSize(256, 128);
      rightfrogeye.mirror = true;
      setRotation(rightfrogeye, 0F, 0F, 0F);
      rightbandpigtail = new ModelRenderer(this, 70, 65);
      rightbandpigtail.addBox(-4F, 0F, 4F, 2, 2, 2);
      rightbandpigtail.setRotationPoint(0F, 3F, 0F);
      rightbandpigtail.setTextureSize(256, 128);
      rightbandpigtail.mirror = true;
      setRotation(rightbandpigtail, 0F, 0F, 0F);
      leftbandpigtail = new ModelRenderer(this, 70, 65);
      leftbandpigtail.addBox(2F, 0F, 4F, 2, 2, 2);
      leftbandpigtail.setRotationPoint(0F, 3F, 0F);
      leftbandpigtail.setTextureSize(256, 128);
      leftbandpigtail.mirror = true;
      setRotation(leftbandpigtail, 0F, 0F, 0F);
      rightpigtail = new ModelRenderer(this, 144, 64);
      rightpigtail.addBox(-5F, 1F, 5F, 3, 3, 3);
      rightpigtail.setRotationPoint(0F, 3F, 0F);
      rightpigtail.setTextureSize(256, 128);
      rightpigtail.mirror = true;
      setRotation(rightpigtail, 0F, 0F, 0F);
      leftpigtail = new ModelRenderer(this, 144, 64);
      leftpigtail.addBox(3F, 1F, 5F, 3, 3, 3);
      leftpigtail.setRotationPoint(0F, 3F, 0F);
      leftpigtail.setTextureSize(256, 128);
      leftpigtail.mirror = true;
      setRotation(leftpigtail, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    froghat.render(f5);
    leftfrogeye.render(f5);
    backpack.render(f5);
    toprighttoe.render(f5);
    toplefttoe.render(f5);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    lefttoe.render(f5);
    righttoe.render(f5);
    rightfrogeye.render(f5);
    rightbandpigtail.render(f5);
    leftbandpigtail.render(f5);
    rightpigtail.render(f5);
    leftpigtail.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  /**
   * Sets the models various rotation angles.
   */
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
  {
	  head.rotateAngleY = par4 / (180F / (float)Math.PI);
      head.rotateAngleX = par5 / (180F / (float)Math.PI);
      froghat.rotateAngleY = par4 / (180F / (float)Math.PI);
      froghat.rotateAngleX = par5 / (180F / (float)Math.PI);
      rightfrogeye.rotateAngleY = par4 / (180F / (float)Math.PI);
      rightfrogeye.rotateAngleX = par5 / (180F / (float)Math.PI);
      leftfrogeye.rotateAngleY = par4 / (180F / (float)Math.PI);
      leftfrogeye.rotateAngleX = par5 / (180F / (float)Math.PI);
      rightbandpigtail.rotateAngleY = par4 / (180F / (float)Math.PI);
      rightbandpigtail.rotateAngleX = par5 / (180F / (float)Math.PI);
      leftbandpigtail.rotateAngleY = par4 / (180F / (float)Math.PI);
      leftbandpigtail.rotateAngleX = par5 / (180F / (float)Math.PI);
      rightpigtail.rotateAngleY = par4 / (180F / (float)Math.PI);
      rightpigtail.rotateAngleX = par5 / (180F / (float)Math.PI);
      leftpigtail.rotateAngleY = par4 / (180F / (float)Math.PI);
      leftpigtail.rotateAngleX = par5 / (180F / (float)Math.PI);
      rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 * 0.5F;
      leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2 * 0.5F;
      rightleg.rotateAngleY = 0.0F;
      leftleg.rotateAngleY = 0.0F;
      lefttoe.rotateAngleX = leftleg.rotateAngleX;
      lefttoe.rotateAngleY = leftleg.rotateAngleY;
      righttoe.rotateAngleX = rightleg.rotateAngleX;
      righttoe.rotateAngleY = rightleg.rotateAngleY;
      toplefttoe.rotateAngleX = leftleg.rotateAngleX;
      toplefttoe.rotateAngleY = leftleg.rotateAngleY;
      toprighttoe.rotateAngleX = rightleg.rotateAngleX;
      toprighttoe.rotateAngleY = rightleg.rotateAngleY;
      rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
      leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
      rightarm.rotateAngleZ = 0.0F;
      leftarm.rotateAngleZ = 0.0F;
      rightarm.rotateAngleY = 0.0F;
      leftarm.rotateAngleY = 0.0F;
      head.rotationPointY = 0.0F;
      froghat.rotationPointY = 0.0F;
      rightfrogeye.rotationPointY = 0.0F;
      leftfrogeye.rotationPointY = 0.0F;
      rightbandpigtail.rotationPointY = 0.0F;
      leftbandpigtail.rotationPointY = 0.0F;
      rightpigtail.rotationPointY = 0.0F;
      leftpigtail.rotationPointY = 0.0F;
      
      if (swingProgress > -9990F)
      {
          float f = swingProgress;
          body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f) * (float)Math.PI * 2.0F);
          rightarm.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 4F;
          rightarm.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 4F;
          leftarm.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 4F;
          leftarm.rotationPointX = MathHelper.cos(body.rotateAngleY) * 5F;
          rightarm.rotateAngleY += body.rotateAngleY;
          leftarm.rotateAngleY += body.rotateAngleY;
          leftarm.rotateAngleX += body.rotateAngleY;
          
          f = 1.0F - swingProgress;
          f *= f;
          f *= f;
          f = 1.0F - f;
          float f2 = MathHelper.sin(f * (float)Math.PI);
          float f4 = MathHelper.sin(swingProgress * (float)Math.PI) * -(head.rotateAngleX - 0.7F) * 0.75F;
          rightarm.rotateAngleX -= (double)f2 * 1.2D + (double)f4;
          rightarm.rotateAngleY += body.rotateAngleY * 2.0F;
          rightarm.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI * 0.4F);
      }
      
      rightarm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      leftarm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      rightarm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
      leftarm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;            

  }
}
