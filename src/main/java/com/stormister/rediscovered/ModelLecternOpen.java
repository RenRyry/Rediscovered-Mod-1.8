package com.stormister.rediscovered;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLecternOpen extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Gold;
    ModelRenderer Post;
    ModelRenderer StandBase;
    ModelRenderer Stand;
    ModelRenderer BackStand;
    ModelRenderer StandSide;
    ModelRenderer BookSide;
    ModelRenderer BookFront;
    ModelRenderer BookBack;
    ModelRenderer BookPagesLeft;
    ModelRenderer BookPagesRight;
    ModelRenderer BookPage1;
    ModelRenderer BookPage2;
  
  public ModelLecternOpen()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Base = new ModelRenderer(this, 0, 17);
      Base.addBox(0F, 0F, 0F, 14, 1, 14);
      Base.setRotationPoint(-7F, 23F, -7F);
      Base.setTextureSize(128, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Gold = new ModelRenderer(this, 0, 0);
      Gold.addBox(0F, 0F, 0F, 12, 1, 12);
      Gold.setRotationPoint(-6F, 22F, -6F);
      Gold.setTextureSize(128, 64);
      Gold.mirror = true;
      setRotation(Gold, 0F, 0F, 0F);
      Post = new ModelRenderer(this, 32, 13);
      Post.addBox(0F, 0F, 0F, 8, 16, 8);
      Post.setRotationPoint(-4F, 6F, -4F);
      Post.setTextureSize(128, 64);
      Post.mirror = true;
      setRotation(Post, 0F, 0F, 0F);
      StandBase = new ModelRenderer(this, 0, 15);
      StandBase.addBox(0F, 0F, 0F, 16, 1, 16);
      StandBase.setRotationPoint(-8F, 5F, -8F);
      StandBase.setTextureSize(128, 64);
      StandBase.mirror = true;
      setRotation(StandBase, 0F, 0F, 0F);
      Stand = new ModelRenderer(this, 0, 15);
      Stand.addBox(0F, 0F, 0F, 16, 1, 16);
      Stand.setRotationPoint(-8F, 5F, -8F);
      Stand.setTextureSize(128, 64);
      Stand.mirror = true;
      setRotation(Stand, 0.1832596F, 0F, 0F);
      BackStand = new ModelRenderer(this, 0, 23);
      BackStand.addBox(0F, 0F, 0F, 16, 3, 1);
      BackStand.setRotationPoint(-8F, 2F, 7F);
      BackStand.setTextureSize(128, 64);
      BackStand.mirror = true;
      setRotation(BackStand, 0F, 0F, 0F);
      StandSide = new ModelRenderer(this, 64, 0);
      StandSide.addBox(0F, 0F, 0F, 16, 2, 10);
      StandSide.setRotationPoint(-8F, 3F, -3F);
      StandSide.setTextureSize(128, 64);
      StandSide.mirror = true;
      setRotation(StandSide, 0F, 0F, 0F);
      BookSide = new ModelRenderer(this, 70, 49);
      BookSide.addBox(0F, 0F, 0F, 2, 0, 10);
      BookSide.setRotationPoint(-1.1F, 4F, -4F);
      BookSide.setTextureSize(128, 64);
      BookSide.mirror = true;
      setRotation(BookSide, 0.1919862F, 0F, 0F);
      BookFront = new ModelRenderer(this, 80, 15);
      BookFront.addBox(0F, 0F, 0F, 6, 0, 10);
      BookFront.setRotationPoint(-1F, 4F, -4F);
      BookFront.setTextureSize(128, 64);
      BookFront.mirror = true;
      setRotation(BookFront, -0.1919862F, 0.0523599F, -2.86234F);
      BookBack = new ModelRenderer(this, 80, 27);
      BookBack.addBox(0F, 0F, 0F, 6, 0, 10);
      BookBack.setRotationPoint(0.8F, 4F, -4F);
      BookBack.setTextureSize(128, 64);
      BookBack.mirror = true;
      setRotation(BookBack, 0.1919862F, 0.0523599F, -0.2792527F);
      BookPagesLeft = new ModelRenderer(this, 0, 45);
      BookPagesLeft.addBox(0F, 0F, 0F, 5, 1, 8);
      BookPagesLeft.setRotationPoint(-0.5F, 3.7F, -3F);
      BookPagesLeft.setTextureSize(128, 64);
      BookPagesLeft.mirror = true;
      setRotation(BookPagesLeft, -0.1919862F, 0.0523599F, -2.86234F);
      BookPagesRight = new ModelRenderer(this, 26, 45);
      BookPagesRight.addBox(0F, 0F, 0F, 5, 1, 8);
      BookPagesRight.setRotationPoint(-0.2F, 2.8F, -3.2F);
      BookPagesRight.setTextureSize(128, 64);
      BookPagesRight.mirror = true;
      setRotation(BookPagesRight, 0.1919862F, 0.0523599F, -0.2792527F);
      BookPage1 = new ModelRenderer(this, 0, 55);
      BookPage1.addBox(0F, 0F, 0F, 5, 0, 8);
      BookPage1.setRotationPoint(-0.2F, 2.8F, -3.2F);
      BookPage1.setTextureSize(128, 64);
      BookPage1.mirror = true;
      setRotation(BookPage1, -0.1745329F, 0.1047197F, -2.617994F);
      BookPage2 = new ModelRenderer(this, 0, 55);
      BookPage2.addBox(0F, 0F, 0F, 5, 0, 8);
      BookPage2.setRotationPoint(-0.2F, 2.8F, -3.2F);
      BookPage2.setTextureSize(128, 64);
      BookPage2.mirror = true;
      setRotation(BookPage2, 0.1745329F, 0.1047197F, -0.5235988F);
  }
  
  public void render(float scale, double x, double y, double z, float ang, float angY, boolean renderLantern, boolean lanternOn, boolean renderHeadTorch)
  {
      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5, y + 0.5 , z + 0.5);
      GL11.glPushMatrix();
      GL11.glRotatef(ang, 1f, 0f, 0f);
      GL11.glRotatef(angY, 0f, 1f, 0f);
      Base.render(scale);
      Gold.render(scale);
	  Post.render(scale);
	  StandBase.render(scale);
	  Stand.render(scale);
	  BackStand.render(scale);
	  StandSide.render(scale);
	  BookSide.render(scale);
	  BookFront.render(scale);
	  BookBack.render(scale);
	  BookPagesLeft.render(scale);
	  BookPagesRight.render(scale);
	  BookPage1.render(scale);
	  BookPage2.render(scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void renderAll() 
  {
	  Base.render(0.0625F);
      Gold.render(0.0625F);
	  Post.render(0.0625F);
	  StandBase.render(0.0625F);
	  Stand.render(0.0625F);
	  BackStand.render(0.0625F);
	  StandSide.render(0.0625F);
	  BookSide.render(0.0625F);
	  BookFront.render(0.0625F);
	  BookBack.render(0.0625F);
	  BookPagesLeft.render(0.0625F);
	  BookPagesRight.render(0.0625F);
	  BookPage1.render(0.0625F);
	  BookPage2.render(0.0625F);
  }

}
