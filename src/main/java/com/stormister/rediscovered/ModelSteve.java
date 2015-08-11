package com.stormister.rediscovered;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSteve extends ModelBiped
{
  //fields
    ModelRenderer leftfoot;
    ModelRenderer rightfoot;
    ModelRenderer lefttoes;
    ModelRenderer righttoes;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer rightear;
    ModelRenderer lefthand;
    ModelRenderer righthand;
    ModelRenderer leftear;
    ModelRenderer neck;
    ModelRenderer righttoptoes;
    ModelRenderer lefttoptoes;
    ModelRenderer righttopfoot;
    ModelRenderer lefttopfoot;
    ModelRenderer righttophand;
    ModelRenderer lefttophand;
  
  public ModelSteve()
  {
    textureWidth = 256;
    textureHeight = 128;
    
      leftfoot = new ModelRenderer(this, 143, 16);
      leftfoot.addBox(0F, 0F, -3F, 4, 12, 4);
      leftfoot.setRotationPoint(0F, 12F, -4F);
      leftfoot.setTextureSize(256, 128);
      leftfoot.mirror = true;
      setRotation(leftfoot, 0F, 0F, 0F);
      rightfoot = new ModelRenderer(this, 143, 16);
      rightfoot.addBox(0F, 0F, -3F, 4, 12, 4);
      rightfoot.setRotationPoint(-4F, 12F, -3F);
      rightfoot.setTextureSize(256, 128);
      rightfoot.mirror = true;
      setRotation(rightfoot, 0F, 0F, 0F);
      lefttoes = new ModelRenderer(this, 151, 0);
      lefttoes.addBox(0F, 0F, -4F, 4, 12, 5);
      lefttoes.setRotationPoint(0F, 12F, -4F);
      lefttoes.setTextureSize(256, 128);
      lefttoes.mirror = true;
      setRotation(lefttoes, 0F, 0F, 0F);
      righttoes = new ModelRenderer(this, 151, 0);
      righttoes.addBox(0F, 0F, -4F, 4, 12, 5);
      righttoes.setRotationPoint(-4F, 12F, -4F);
      righttoes.setTextureSize(256, 128);
      righttoes.mirror = true;
      setRotation(righttoes, 0F, 0F, 0F);
      head = new ModelRenderer(this, 1, 6);
      head.addBox(-4F, -10F, -4F, 10, 10, 8);
      head.setRotationPoint(-1F, -2F, 0F);
      head.setTextureSize(256, 128);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 99, 17);
      body.addBox(-4F, 0F, -2F, 6, 11, 4);
      body.setRotationPoint(1F, 1F, 0F);
      body.setTextureSize(256, 128);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 119, 16);
      rightarm.addBox(0F, -2F, 0F, 2, 8, 2);
      rightarm.setRotationPoint(-2F, 4F, 1F);
      rightarm.setTextureSize(256, 128);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0.185895F);
      leftarm = new ModelRenderer(this, 119, 16);
      leftarm.addBox(-2F, -2F, -1F, 2, 8, 2);
      leftarm.setRotationPoint(4F, 4F, 1F);
      leftarm.setTextureSize(256, 128);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, -0.1858931F);
      rightleg = new ModelRenderer(this, 136, 0);
      rightleg.addBox(0F, 0F, -2F, 3, 8, 3);
      rightleg.setRotationPoint(-3F, 12F, -2F);
      rightleg.setTextureSize(256, 128);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 136, 0);
      leftleg.addBox(0F, 0F, -2F, 3, 8, 3);
      leftleg.setRotationPoint(0F, 12F, -2F);
      leftleg.setTextureSize(256, 128);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      rightear = new ModelRenderer(this, 100, 12);
      rightear.addBox(-5F, -5F, 0F, 12, 3, 1);
      rightear.setRotationPoint(-1F, -2F, 0F);
      rightear.setTextureSize(256, 128);
      rightear.mirror = true;
      setRotation(rightear, 0F, 0F, 0F);
      lefthand = new ModelRenderer(this, 239, 0);
      lefthand.addBox(-3F, -1F, -2F, 4, 12, 4);
      lefthand.setRotationPoint(2F, 2F, -2F);
      lefthand.setTextureSize(256, 128);
      lefthand.mirror = true;
      setRotation(lefthand, 0F, 0F, -0.185895F);
      righthand = new ModelRenderer(this, 239, 16);
      righthand.addBox(-1F, 0F, -1F, 4, 12, 4);
      righthand.setRotationPoint(-6F, 1F, -2F);
      righthand.setTextureSize(256, 128);
      righthand.mirror = true;
      setRotation(righthand, 0F, 0F, 0.185895F);
      righthand.mirror = true;
      leftear = new ModelRenderer(this, 100, 12);
      leftear.addBox(-5F, -5F, 0F, 12, 3, 1);
      leftear.setRotationPoint(-1F, -2F, 0F);
      leftear.setTextureSize(256, 128);
      leftear.mirror = true;
      setRotation(leftear, 0F, 0F, 0F);
      neck = new ModelRenderer(this, 0, 0);
      neck.addBox(0F, 0F, 0F, 4, 4, 2);
      neck.setRotationPoint(-2F, -2F, -1F);
      neck.setTextureSize(256, 128);
      neck.mirror = true;
      setRotation(neck, 0F, 0F, 0F);
      righttoptoes = new ModelRenderer(this, 130, 27);
      righttoptoes.addBox(0F, 0F, -4F, 4, 10, 5);
      righttoptoes.setRotationPoint(-4F, 12F, -4F);
      righttoptoes.setTextureSize(256, 128);
      righttoptoes.mirror = true;
      setRotation(righttoptoes, 0F, 0F, 0F);
      lefttoptoes = new ModelRenderer(this, 130, 27);
      lefttoptoes.addBox(0F, 0F, -4F, 4, 10, 5);
      lefttoptoes.setRotationPoint(0F, 12F, -4F);
      lefttoptoes.setTextureSize(256, 128);
      lefttoptoes.mirror = true;
      setRotation(lefttoptoes, 0F, 0F, 0F);
      righttopfoot = new ModelRenderer(this, 96, 0);
      righttopfoot.addBox(0F, 0F, -3F, 4, 8, 4);
      righttopfoot.setRotationPoint(-4F, 12F, -3F);
      righttopfoot.setTextureSize(256, 128);
      righttopfoot.mirror = true;
      setRotation(righttopfoot, 0F, 0F, 0F);
      lefttopfoot = new ModelRenderer(this, 96, 0);
      lefttopfoot.addBox(0F, 0F, -3F, 4, 8, 4);
      lefttopfoot.setRotationPoint(0F, 12F, -3F);
      lefttopfoot.setTextureSize(256, 128);
      lefttopfoot.mirror = true;
      setRotation(lefttopfoot, 0F, 0F, 0F);
      righttophand = new ModelRenderer(this, 117, 0);
      righttophand.addBox(-1F, 0F, -1F, 4, 8, 4);
      righttophand.setRotationPoint(-6F, 1F, -2F);
      righttophand.setTextureSize(256, 128);
      righttophand.mirror = true;
      setRotation(righttophand, 0F, 0F, 0.185895F);
      lefttophand = new ModelRenderer(this, 117, 0);
      lefttophand.addBox(-3F, -1F, -2F, 4, 8, 4);
      lefttophand.setRotationPoint(2F, 2F, -2F);
      lefttophand.setTextureSize(256, 128);
      lefttophand.mirror = true;
      setRotation(lefttophand, 0F, 0F, -0.185895F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    leftfoot.render(f5);
    rightfoot.render(f5);
    lefttoes.render(f5);
    righttoes.render(f5);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    rightear.render(f5);
    lefthand.render(f5);
    righthand.render(f5);
    leftear.render(f5);
    neck.render(f5);
    righttoptoes.render(f5);
    lefttoptoes.render(f5);
    righttopfoot.render(f5);
    lefttopfoot.render(f5);
    righttophand.render(f5);
    lefttophand.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
  {
	  head.rotateAngleY = par4 / (180F / (float)Math.PI);
      head.rotateAngleX = par5 / (180F / (float)Math.PI);
      rightear.rotateAngleY = par4 / (180F / (float)Math.PI);
      rightear.rotateAngleX = par5 / (180F / (float)Math.PI);
      leftear.rotateAngleY = par4 / (180F / (float)Math.PI);
      leftear.rotateAngleX = par5 / (180F / (float)Math.PI);
      rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
      leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
      rightarm.rotateAngleZ = 0.0F;
      leftarm.rotateAngleZ = 0.0F;
      righthand.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
      lefthand.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
      righthand.rotateAngleZ = 0.0F;
      lefthand.rotateAngleZ = 0.0F;
      righttophand.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
      lefttophand.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
      righttophand.rotateAngleZ = 0.0F;
      lefttophand.rotateAngleZ = 0.0F;
      rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
      rightleg.rotateAngleY = 0.0F;
      leftleg.rotateAngleY = 0.0F;
      rightfoot.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      leftfoot.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
      rightfoot.rotateAngleY = 0.0F;
      leftfoot.rotateAngleY = 0.0F;
      righttoes.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      lefttoes.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
      righttoes.rotateAngleY = 0.0F;
      lefttoes.rotateAngleY = 0.0F;
      righttoptoes.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      lefttoptoes.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
      righttoptoes.rotateAngleY = 0.0F;
      lefttoptoes.rotateAngleY = 0.0F;
      righttopfoot.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      lefttopfoot.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
      righttopfoot.rotateAngleY = 0.0F;
      lefttopfoot.rotateAngleY = 0.0F;
      
      if (heldItemLeft != 0)
      {
          lefthand.rotateAngleX = lefthand.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)heldItemLeft;
          lefttophand.rotateAngleX = lefttophand.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)heldItemLeft;
          leftarm.rotateAngleX = leftarm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)heldItemLeft;
      }

      if (heldItemRight != 0)
      {
          righthand.rotateAngleX = righthand.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)heldItemRight;
          righttophand.rotateAngleX = righttophand.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)heldItemRight;
          rightarm.rotateAngleX = rightarm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)heldItemRight;
      }

      if (isRiding)
      {
          rightarm.rotateAngleX += -((float)Math.PI / 5F);
          leftarm.rotateAngleX += -((float)Math.PI / 5F);
          righthand.rotateAngleX += -((float)Math.PI / 5F);
          lefthand.rotateAngleX += -((float)Math.PI / 5F);
          rightleg.rotateAngleX = -((float)Math.PI * 2F / 5F);
          leftleg.rotateAngleX = -((float)Math.PI * 2F / 5F);
          rightleg.rotateAngleY = ((float)Math.PI / 10F);
          leftleg.rotateAngleY = -((float)Math.PI / 10F);
          rightfoot.rotateAngleX = -((float)Math.PI * 2F / 5F);
          leftfoot.rotateAngleX = -((float)Math.PI * 2F / 5F);
          rightfoot.rotateAngleY = ((float)Math.PI / 10F);
          leftfoot.rotateAngleY = -((float)Math.PI / 10F);
      }

      rightarm.rotateAngleY = 0.0F;
      leftarm.rotateAngleY = 0.0F;
      righthand.rotateAngleY = 0.0F;
      lefthand.rotateAngleY = 0.0F;
      righttophand.rotateAngleY = 0.0F;
      lefttophand.rotateAngleY = 0.0F;

      if (swingProgress > -9990F)
      {
          float f = swingProgress;
          body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f) * (float)Math.PI * 2.0F) * 0.2F;
          rightarm.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 5F;
          rightarm.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 5F;
          leftarm.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 5F;
          leftarm.rotationPointX = MathHelper.cos(body.rotateAngleY) * 5F;
          rightarm.rotateAngleY += body.rotateAngleY;
          leftarm.rotateAngleY += body.rotateAngleY;
          leftarm.rotateAngleX += body.rotateAngleY;
          righthand.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 5F;
          righthand.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 5F;
          lefthand.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 5F;
          lefthand.rotationPointX = MathHelper.cos(body.rotateAngleY) * 5F;
          righthand.rotateAngleY += body.rotateAngleY;
          lefthand.rotateAngleY += body.rotateAngleY;
          lefthand.rotateAngleX += body.rotateAngleY;
          righttophand.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 5F;
          righttophand.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 5F;
          lefttophand.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 5F;
          lefttophand.rotationPointX = MathHelper.cos(body.rotateAngleY) * 5F;
          righttophand.rotateAngleY += body.rotateAngleY;
          lefttophand.rotateAngleY += body.rotateAngleY;
          lefttophand.rotateAngleX += body.rotateAngleY;
          f = 1.0F - swingProgress;
          f *= f;
          f *= f;
          f = 1.0F - f;
          float f2 = MathHelper.sin(f * (float)Math.PI);
          float f4 = MathHelper.sin(swingProgress * (float)Math.PI) * -(head.rotateAngleX - 0.7F) * 0.75F;
          rightarm.rotateAngleX -= (double)f2 * 1.2D + (double)f4;
          rightarm.rotateAngleY += body.rotateAngleY * 2.0F;
          rightarm.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI) * -0.4F;
          
          
          righthand.rotateAngleX -= (double)f2 * 1.2D + (double)f4;
          righthand.rotateAngleY += body.rotateAngleY * 2.0F;
          righthand.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI) * -0.4F;
          
          righttophand.rotateAngleX -= (double)f2 * 1.2D + (double)f4;
          righttophand.rotateAngleY += body.rotateAngleY * 2.0F;
          righttophand.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI) * -0.4F;
      }

          body.rotateAngleX = 0.0F;
          rightleg.rotationPointZ = 0.0F;
          leftleg.rotationPointZ = 0.0F;
          rightleg.rotationPointY = 12F;
          leftleg.rotationPointY = 12F;
          rightfoot.rotationPointZ = 0.0F;
          leftfoot.rotationPointZ = 0.0F;
          rightfoot.rotationPointY = 12F;
          leftfoot.rotationPointY = 12F;
          righttoes.rotationPointZ = 0.0F;
          lefttoes.rotationPointZ = 0.0F;
          righttoes.rotationPointY = 12F;
          lefttoes.rotationPointY = 12F;
          righttoptoes.rotationPointZ = 0.0F;
          lefttoptoes.rotationPointZ = 0.0F;
          righttoptoes.rotationPointY = 12F;
          lefttoptoes.rotationPointY = 12F;
          righttopfoot.rotationPointZ = 0.0F;
          lefttopfoot.rotationPointZ = 0.0F;
          righttopfoot.rotationPointY = 12F;
          lefttopfoot.rotationPointY = 12F;
          head.rotationPointY = 0.0F;
          rightear.rotationPointY = 0.0F;
          leftear.rotationPointY = 0.0F;
      

      rightarm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      leftarm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      rightarm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
      leftarm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
      righthand.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      lefthand.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      righthand.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
      lefthand.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
      righttophand.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      lefttophand.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
      righttophand.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
      lefttophand.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
  }

}
