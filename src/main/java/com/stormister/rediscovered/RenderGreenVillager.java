package com.stormister.rediscovered;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGreenVillager extends RenderLiving
{
    private static final ResourceLocation villagerTextures = new ResourceLocation("textures/entity/villager/villager.png");

    private static final String __OBFID = "CL_00001032";

    public RenderGreenVillager(RenderManager p_i46132_1_)
    {
        super(p_i46132_1_, new ModelVillager(0.0F), 0.5F);
        this.addLayer(new LayerCustomHead(this.func_177134_g().villagerHead));
    }

    public ModelVillager func_177134_g()
    {
        return (ModelVillager)super.getMainModel();
    }

    protected ResourceLocation getEntityTexture(EntityGreenVillager entity)
    {
        
        return net.minecraftforge.fml.common.registry.VillagerRegistry.getVillagerSkin(entity.getProfession(), villagerTextures);
        
    }

    protected void preRenderCallback(EntityGreenVillager p_77041_1_, float p_77041_2_)
    {
        float f1 = 0.9375F;

        if (p_77041_1_.getGrowingAge() < 0)
        {
            f1 = (float)((double)f1 * 0.5D);
            this.shadowSize = 0.25F;
        }
        else
        {
            this.shadowSize = 0.5F;
        }

        GlStateManager.scale(f1, f1, f1);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityGreenVillager)p_77041_1_, p_77041_2_);
    }

    public ModelBase getMainModel()
    {
        return this.func_177134_g();
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityGreenVillager)entity);
    }
}