
package com.stormister.rediscovered;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import java.io.IOException;
import java.io.InputStream;

public class RenderTileEntityLecternOpen extends TileEntitySpecialRenderer
{
	private static final ResourceLocation field_110871_a = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/Lectern.png");
	
	public RenderTileEntityLecternOpen()
	{
		model = new ModelLecternOpen();
	}
	
	public void renderAModelAt(TileEntityLecternOpen tile, double d, double d1, double d2, float f) 
	{
	
		int j = 0;
		if(tile != null && tile.hasWorldObj())
		{
			j = tile.getBlockMetadata();
		}
		short short1 = 0;

        if (j == 2)
        {
            short1 = 180;
        }

        if (j == 3)
        {
            short1 = 0;
        }

        if (j == 4)
        {
            short1 = 90;
        }

        if (j == 5)
        {
            short1 = -90;
        }
		this.bindTexture(field_110871_a);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);

		
		model.renderAll();
		GL11.glPopMatrix(); 
		//end
	}
	
	private ModelLecternOpen model;
	
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int blah)
	{
		this.renderAModelAt((TileEntityLecternOpen)par1TileEntity, par2, par4, par6, par8);
	}
}