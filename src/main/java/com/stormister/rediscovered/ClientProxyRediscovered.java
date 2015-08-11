package com.stormister.rediscovered;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;



public class ClientProxyRediscovered extends CommonProxyRediscovered
{
	@Override
	public void registerRenderThings()
	{		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLectern.class, new RenderTileEntityLectern());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLecternOpen.class, new RenderTileEntityLecternOpen());
		
		FMLCommonHandler.instance().bus().register(mod_Rediscovered.c);
		
		RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityRana.class, new RenderMD3(false, "/mcexport01.MD3", mod_Rediscovered.rana));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntitySteve.class, new EntitySteve.RenderMD3Steve(mod_Rediscovered.anmen, "/Steve.MD3", mod_Rediscovered.steve ));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityBlackSteve.class, new EntityBlackSteve.RenderMD3Steve(mod_Rediscovered.anmen, "/Steve.MD3", mod_Rediscovered.blacksteve ));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityBeastBoy.class, new EntityBeastBoy.RenderMD3Steve(mod_Rediscovered.anmen, "/Steve.MD3", mod_Rediscovered.beastboy ));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityPigman.class, new RenderPigman(Minecraft.getMinecraft().getRenderManager(), new ModelBiped(), 0.5F));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityMeleePigman.class, new RenderPigman(Minecraft.getMinecraft().getRenderManager(), new ModelBiped(), 0.5F));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityRangedPigman.class, new RenderPigman(Minecraft.getMinecraft().getRenderManager(), new ModelRangedPigman(), 0.5F));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityGreenVillager.class, new RenderGreenVillager(Minecraft.getMinecraft().getRenderManager()));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntitySkyChicken.class, new RenderSkyChicken(Minecraft.getMinecraft().getRenderManager(), new ModelSkyChicken(), 0.5F));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityFish.class, new RenderFishMob(Minecraft.getMinecraft().getRenderManager(), new ModelFish(), 0.5F));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityZombieHorse.class, new RenderZombieHorse(Minecraft.getMinecraft().getRenderManager(), new ModelZombieHorse(), 0.5F));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntitySkeletonHorse.class, new RenderSkeletonHorse(Minecraft.getMinecraft().getRenderManager(), new ModelSkeletonHorse(), 0.5F));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityGoodDragon.class, new RenderRedDragon(Minecraft.getMinecraft().getRenderManager()));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityParrow.class, new RenderParrow(Minecraft.getMinecraft().getRenderManager()));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityGiant.class, new RenderGiant(Minecraft.getMinecraft().getRenderManager(), new ModelZombie(), 0.5F, 6.0F));
    	RenderingRegistry.registerEntityRenderingHandler(com.stormister.rediscovered.EntityScarecrow.class, new RenderScarecrow(Minecraft.getMinecraft().getRenderManager(), new ModelScarecrow(), 0.5F));
	}
	
	public void registerTileEntitySpecialRenderer(){
		
	}   

}
