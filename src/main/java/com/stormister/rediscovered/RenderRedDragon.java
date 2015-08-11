package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderRedDragon extends RenderLiving
{
    private static final ResourceLocation enderDragonExplodingTextures = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
    private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
    private static final ResourceLocation field_110845_h = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/reddragon/red_eyes.png");
    private static final ResourceLocation enderDragonTextures = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/reddragon/red.png");

    /** An instance of the dragon model in RenderDragon */
    protected ModelRedDragon modelDragon;

    public RenderRedDragon(RenderManager p_i46183_1_)
    {
        super(p_i46183_1_, new ModelRedDragon(0.0F), 0.5F);
        this.modelDragon = (ModelRedDragon)this.mainModel;
//        this.setRenderPassModel(this.mainModel);
    }

    /**
     * Used to rotate the dragon as a whole in RenderDragon. It's called in the rotateCorpse method.
     */
    protected void func_180575_a(EntityGoodDragon p_180575_1_, float p_180575_2_, float p_180575_3_, float p_180575_4_)
    {
        float f3 = (float)p_180575_1_.getMovementOffsets(7, p_180575_4_)[0];
        float f4 = (float)(p_180575_1_.getMovementOffsets(5, p_180575_4_)[1] - p_180575_1_.getMovementOffsets(10, p_180575_4_)[1]);
        GlStateManager.rotate(-f3, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f4 * 10.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0.0F, 0.0F, 1.0F);

        if (p_180575_1_.deathTime > 0)
        {
            float f5 = ((float)p_180575_1_.deathTime + p_180575_4_ - 1.0F) / 20.0F * 1.6F;
            f5 = MathHelper.sqrt_float(f5);

            if (f5 > 1.0F)
            {
                f5 = 1.0F;
            }

            GlStateManager.rotate(f5 * this.getDeathMaxRotation(p_180575_1_), 0.0F, 0.0F, 1.0F);
        }
    }

    protected void renderModel(EntityGoodDragon p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        if (p_77036_1_.deathTicks > 0)
        {
            float f6 = (float)p_77036_1_.deathTicks / 200.0F;
            GlStateManager.depthFunc(515);
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(516, f6);
            this.bindTexture(enderDragonExplodingTextures);
            this.mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.depthFunc(514);
        }

        this.bindEntityTexture(p_77036_1_);
        this.mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);

        if (p_77036_1_.hurtTime > 0)
        {
            GlStateManager.depthFunc(514);
            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F);
            this.mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.depthFunc(515);
        }
    }

    public void doRender(EntityGoodDragon entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
//        BossStatus.setBossStatus(entity, false);
        super.doRender((EntityLiving)entity, x, y, z, p_76986_8_, partialTicks);

        if (entity.healingEnderCrystal != null)
        {
            this.drawRechargeRay(entity, x, y, z, partialTicks);
        }
    }

    protected void drawRechargeRay(EntityGoodDragon p_180574_1_, double p_180574_2_, double p_180574_4_, double p_180574_6_, float p_180574_8_)
    {
        float f1 = (float)p_180574_1_.healingEnderCrystal.innerRotation + p_180574_8_;
        float f2 = MathHelper.sin(f1 * 0.2F) / 2.0F + 0.5F;
        f2 = (f2 * f2 + f2) * 0.2F;
        float f3 = (float)(p_180574_1_.healingEnderCrystal.posX - p_180574_1_.posX - (p_180574_1_.prevPosX - p_180574_1_.posX) * (double)(1.0F - p_180574_8_));
        float f4 = (float)((double)f2 + p_180574_1_.healingEnderCrystal.posY - 1.0D - p_180574_1_.posY - (p_180574_1_.prevPosY - p_180574_1_.posY) * (double)(1.0F - p_180574_8_));
        float f5 = (float)(p_180574_1_.healingEnderCrystal.posZ - p_180574_1_.posZ - (p_180574_1_.prevPosZ - p_180574_1_.posZ) * (double)(1.0F - p_180574_8_));
        float f6 = MathHelper.sqrt_float(f3 * f3 + f5 * f5);
        float f7 = MathHelper.sqrt_float(f3 * f3 + f4 * f4 + f5 * f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)p_180574_2_, (float)p_180574_4_ + 2.0F, (float)p_180574_6_);
        GlStateManager.rotate((float)(-Math.atan2((double)f5, (double)f3)) * 180.0F / (float)Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(-Math.atan2((double)f6, (double)f4)) * 180.0F / (float)Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableCull();
        this.bindTexture(enderDragonCrystalBeamTextures);
        GlStateManager.shadeModel(7425);
        float f8 = 0.0F - ((float)p_180574_1_.ticksExisted + p_180574_8_) * 0.01F;
        float f9 = MathHelper.sqrt_float(f3 * f3 + f4 * f4 + f5 * f5) / 32.0F - ((float)p_180574_1_.ticksExisted + p_180574_8_) * 0.01F;
        worldrenderer.startDrawing(5);
        byte b0 = 8;

        for (int i = 0; i <= b0; ++i)
        {
            float f10 = MathHelper.sin((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
            float f11 = MathHelper.cos((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
            float f12 = (float)(i % b0) * 1.0F / (float)b0;
            worldrenderer.setColorOpaque_I(0);
            worldrenderer.addVertexWithUV((double)(f10 * 0.2F), (double)(f11 * 0.2F), 0.0D, (double)f12, (double)f9);
            worldrenderer.setColorOpaque_I(16777215);
            worldrenderer.addVertexWithUV((double)f10, (double)f11, (double)f7, (double)f12, (double)f8);
        }

        tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.shadeModel(7424);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    protected ResourceLocation getEntityTexture(EntityGoodDragon entity)
    {
        return enderDragonTextures;
    }

    public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityGoodDragon)entity, x, y, z, p_76986_8_, partialTicks);
    }

    protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        this.func_180575_a((EntityGoodDragon)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

    protected void renderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        this.renderModel((EntityGoodDragon)p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
    }

    public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityGoodDragon)entity, x, y, z, p_76986_8_, partialTicks);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityGoodDragon)entity);
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityGoodDragon)entity, x, y, z, p_76986_8_, partialTicks);
    }
}
