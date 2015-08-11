package com.stormister.rediscovered;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLectern extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Gold;
    ModelRenderer Post;
    ModelRenderer StandBase;
    ModelRenderer Stand;
    ModelRenderer BackStand;
    ModelRenderer StandSide;
    ModelRenderer BookCover;
    ModelRenderer BookPages;
    ModelRenderer BookBack;
    ModelRenderer BookSide;
  
  public ModelLectern()
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
      BookCover = new ModelRenderer(this, 80, 15);
      BookCover.addBox(0F, 0F, 0F, 6, 0, 10);
      BookCover.setRotationPoint(-0.1F, 2.1F, -4F);
      BookCover.setTextureSize(128, 64);
      BookCover.mirror = true;
      setRotation(BookCover, 0.1919862F, 0F, 0F);
      BookBack = new ModelRenderer(this, 80, 27);
      BookBack.addBox(0F, 0F, 0F, 6, 0, 10);
      BookBack.setRotationPoint(-0.1F, 4.1F, -3.5F);
      BookBack.setTextureSize(128, 64);
      BookBack.mirror = true;
      setRotation(BookBack, 0.1919862F, 0F, 0F);
      BookSide = new ModelRenderer(this, 80, 37);
      BookSide.addBox(0F, 0F, 0F, 0, 2, 10);
      BookSide.setRotationPoint(-0.1F, 2.1F, -4F);
      BookSide.setTextureSize(128, 64);
      BookSide.mirror = true;
      setRotation(BookSide, 0.1919862F, 0F, 0F);
      BookPages = new ModelRenderer(this, 100, 39);
      BookPages.addBox(0F, 0F, 0F, 5, 2, 8);
      BookPages.setRotationPoint(0F, 1.9F, -3F);
      BookPages.setTextureSize(128, 64);
      BookPages.mirror = true;
      setRotation(BookPages, 0.1919862F, 0F, 0F);
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
      BookCover.render(scale);
	  BookBack.render(scale);
	  BookSide.render(scale);
	  BookPages.render(scale);
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
	  BookCover.render(0.0625F);
	  BookBack.render(0.0625F);
	  BookSide.render(0.0625F);
	  BookPages.render(0.0625F);
  }

}
