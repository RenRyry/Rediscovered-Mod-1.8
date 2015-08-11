package com.stormister.rediscovered;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemLecternOpen implements IItemRenderer 
{

	private ModelLecternOpen ChairModel;
	
	public RenderItemLecternOpen() 
	{
		ChairModel = new ModelLecternOpen();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityLecternOpen(), 0.0D, -0.3D, 0.0D, 0.0F);
	}
}