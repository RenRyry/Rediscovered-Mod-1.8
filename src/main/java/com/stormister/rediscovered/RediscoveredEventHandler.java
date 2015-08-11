package com.stormister.rediscovered;

import java.util.Random;

import com.google.common.base.Predicate;
import com.mojang.authlib.GameProfile;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RediscoveredEventHandler
{
	Random gen = new Random();
	protected static Random itemRand = new Random();
	
	
	//Sky Dimension Teleportation
	@SubscribeEvent
	public void onPlayerSleepInBed(PlayerSleepInBedEvent event) 
	{
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack itemStack = inv.getStackInSlot(inv.currentItem);
		World world = event.entityLiving.worldObj;
		
		if(player.dimension == 0 && !world.isDaytime() && itemRand.nextInt(100)<=mod_Rediscovered.DreamChance && (itemStack == null || itemStack.getItem() != mod_Rediscovered.DreamPillow) && player instanceof EntityPlayerMP)
		{
			ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);
			props.setRespawn(event.pos.getX(), event.pos.getY(), event.pos.getZ());
			EntityPlayerMP thePlayer = (EntityPlayerMP) player;
			thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, mod_Rediscovered.DimID, new SkyDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(mod_Rediscovered.DimID)));
		}
	}
	@SubscribeEvent
	public void onLivingHurtEvent(LivingHurtEvent event) 
	{	
		if (!event.entityLiving.worldObj.isRemote && event.entityLiving instanceof EntityPlayerMP && event.entityLiving.dimension == mod_Rediscovered.DimID && event.source.damageType.equals("outOfWorld")) 
		{
			final WorldServer world = (WorldServer) event.entityLiving.worldObj;
			final Object[] entityList = world.loadedEntityList.toArray();
				for (final Object o : entityList)
				{
					if (o instanceof EntityPlayerMP)
					{
						final EntityPlayerMP e = (EntityPlayerMP) o;

						if (e.posY <= 10)
						{
							ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);
							event.setCanceled(true);
							e.mcServer.getConfigurationManager().transferPlayerToDimension(e, 0, new SkyDimensionTeleporter(e.mcServer.worldServerForDimension(0)));
							BlockPos coordinates = props.getRespawn();
			        		if(coordinates!=null)	
			        			coordinates = this.verifyRespawnCoordinates(e.mcServer.worldServerForDimension(0), coordinates, true, e);
			        		if(coordinates == null) {
			                	coordinates = world.getSpawnPoint();
			            		e.setPositionAndUpdate((double) coordinates.getX() + 0.5D, (double) coordinates.getY() + 3, (double) coordinates.getZ() + 0.5D);
			            		e.addChatComponentMessage(new ChatComponentTranslation("message.missingbed", new Object[0]));
			        		}
			        		else if(coordinates != null) {
			            		e.setPositionAndUpdate((double) coordinates.getX() + 0.5D, (double) coordinates.getY() + 3, (double) coordinates.getZ() + 0.5D);
			                }
							e.fallDistance = 0;
						}
					
					}
				}
		}
	}
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) 
	{
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack itemStack = inv.getStackInSlot(inv.currentItem);
		final World world = (World) event.entityLiving.worldObj;

		if(!event.entityLiving.worldObj.isRemote && event.action == event.action.RIGHT_CLICK_BLOCK && world.getBlockState(event.pos).getBlock().equals(Blocks.bed) && ((itemStack != null && itemStack.getItem().equals(mod_Rediscovered.DreamPillow) && (mod_Rediscovered.DaytimeBed || (!mod_Rediscovered.DaytimeBed && !world.isDaytime()))) || player.dimension == mod_Rediscovered.DimID) && player instanceof EntityPlayerMP){
        	event.setCanceled(true);
			EntityPlayerMP thePlayer = (EntityPlayerMP) player;
			ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);
			if (player.dimension == 0)
        	{            
				props.setRespawn(event.pos.getX(), event.pos.getY(), event.pos.getZ());
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, mod_Rediscovered.DimID, new SkyDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(mod_Rediscovered.DimID)));
        	}
        	else if (player.dimension == mod_Rediscovered.DimID)
        	{
        		thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new SkyDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(0)));
        		BlockPos coordinates = props.getRespawn();
        		if(coordinates!=null)	
        			coordinates = this.verifyRespawnCoordinates(thePlayer.mcServer.worldServerForDimension(0), coordinates, true, thePlayer);
        		if(coordinates == null) {
                	coordinates = world.getSpawnPoint();
            		thePlayer.setPositionAndUpdate((double) coordinates.getX() + 0.5D, (double) coordinates.getY() + 3, (double) coordinates.getZ() + 0.5D);
            		player.addChatComponentMessage(new ChatComponentTranslation("message.missingbed", new Object[0]));
        		}
        		else if(coordinates != null) {
            		thePlayer.setPositionAndUpdate((double) coordinates.getX() + 0.5D, (double) coordinates.getY() + 3, (double) coordinates.getZ() + 0.5D);
                }

        	}
		}
		
		
		//Bush Shearing
		if(event.action == event.action.RIGHT_CLICK_BLOCK && world.getBlockState(event.pos).getBlock().equals(Blocks.double_plant) && itemStack != null && itemStack.getItem().equals(Items.shears) && player instanceof EntityPlayerMP){
        	event.setCanceled(true);
        	if(world.getBlockState(event.pos) == Blocks.double_plant.getStateFromMeta(4) || (world.getBlockState(event.pos.down()) == Blocks.double_plant.getStateFromMeta(4))){
        		if(world.getBlockState(event.pos) == Blocks.double_plant.getStateFromMeta(4)){
        			world.setBlockState(event.pos, mod_Rediscovered.EmptyRoseBush.getDefaultState());
        			world.setBlockState(event.pos.up(), mod_Rediscovered.EmptyRoseBushTop.getDefaultState());
        		}
        		else if((world.getBlockState(event.pos.down()) == Blocks.double_plant.getStateFromMeta(4))){
        			world.setBlockState(event.pos.down(), mod_Rediscovered.EmptyRoseBush.getDefaultState());
        			world.setBlockState(event.pos, mod_Rediscovered.EmptyRoseBushTop.getDefaultState());
        		}
        		ItemStack itemStack2 = new ItemStack(mod_Rediscovered.Rose, world.rand.nextInt(3)+1);
        		EntityItem item = new EntityItem(world, event.pos.getX(), event.pos.getY(), event.pos.getZ(), itemStack2);
        		world.spawnEntityInWorld(item);
        	}
        	if(world.getBlockState(event.pos) == Blocks.double_plant.getStateFromMeta(5) || (world.getBlockState(event.pos.down()) == Blocks.double_plant.getStateFromMeta(5))){
        		if(world.getBlockState(event.pos) == Blocks.double_plant.getStateFromMeta(5)){
        			world.setBlockState(event.pos, mod_Rediscovered.EmptyPeonyBush.getDefaultState());
        			world.setBlockState(event.pos.up(), mod_Rediscovered.EmptyPeonyBushTop.getDefaultState());
        		}
        		else if((world.getBlockState(event.pos.down()) == Blocks.double_plant.getStateFromMeta(5))){
        			world.setBlockState(event.pos.down(), mod_Rediscovered.EmptyPeonyBush.getDefaultState());
        			world.setBlockState(event.pos, mod_Rediscovered.EmptyPeonyBushTop.getDefaultState());
        		}
        		ItemStack itemStack2 = new ItemStack(mod_Rediscovered.Peony, world.rand.nextInt(3)+1);
        		EntityItem item = new EntityItem(world, event.pos.getX(), event.pos.getY(), event.pos.getZ(), itemStack2);
        		world.spawnEntityInWorld(item);
        	}
        	itemStack.damageItem(1, player);
		}
	}
	
	
	//Mob AI
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) 
	{
		if(event.entity instanceof EntityZombie){
			EntityCreature entity = (EntityCreature) event.entity;
			if(mod_Rediscovered.ScarecrowAttractsMobs){
				entity.targetTasks.addTask(2, new EntityAINearestAttackableTarget(entity, EntityScarecrow.class, true));
				entity.tasks.addTask(0, new EntityAIAttackOnCollide(entity, EntityScarecrow.class, 0.8D, false));
			}
			else
				entity.tasks.addTask(0, new EntityAIAvoidEntity(entity, new Predicate()
		        {
		            public boolean func_179530_a(Entity p_179530_1_)
		            {
		                return p_179530_1_ instanceof EntityScarecrow;
		            }
		            public boolean apply(Object p_apply_1_)
		            {
		                return this.func_179530_a((Entity)p_apply_1_);
		            }
		        }, 8.0F, 0.6D, 0.6D));
		}
		if(event.entity instanceof EntityAnimal){
			EntityCreature entity = (EntityCreature) event.entity;
			entity.tasks.addTask(0, new EntityAIAvoidEntity(entity, new Predicate()
	        {
	            public boolean func_179530_a(Entity p_179530_1_)
	            {
	                return p_179530_1_ instanceof EntityScarecrow;
	            }
	            public boolean apply(Object p_apply_1_)
	            {
	                return this.func_179530_a((Entity)p_apply_1_);
	            }
	        }, 8.0F, 0.6D, 0.6D));
		}
	}
	
	
	//Cherry Tree Sapling
	@SubscribeEvent
	public void onBonemealClick(BonemealEvent event) 
	{
		World world = event.world;
		if (event.block.equals(mod_Rediscovered.CherrySapling)) 
		{
			if (!world.isRemote)
			{
				double chance = 0.45D;
				if (world.rand.nextFloat() < chance)
				{
					//grow tree
					event.setResult(Result.ALLOW);
					((BlockCherrySapling)mod_Rediscovered.CherrySapling).generateTree(event.world, event.pos, event.world.getBlockState(event.pos), event.world.rand);
					
				}
			}
		}
	}
	
	
	//Quiver Bow
	@SubscribeEvent
    public void onArrowNockEvent(ArrowNockEvent event)
    {
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack par1ItemStack = inv.getStackInSlot(inv.currentItem);
			
		        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		        ItemStack itemstack = player.inventory.armorInventory[2];

		        if(itemstack != null && (itemstack.getItem() == mod_Rediscovered.Quiver || itemstack.getItem() == mod_Rediscovered.LeatherQuiver || itemstack.getItem() == mod_Rediscovered.ChainQuiver || itemstack.getItem() == mod_Rediscovered.GoldQuiver || itemstack.getItem() == mod_Rediscovered.IronQuiver || itemstack.getItem() == mod_Rediscovered.DiamondQuiver))
		        {
		            if (player.inventory.hasItem(Items.arrow) || flag)
		            {
		                EntityArrow entityarrow = new EntityArrow(player.worldObj, player, 1.0F);
		                player.worldObj.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		                entityarrow.setIsCritical(true);
		
		                
		
		                if (!player.worldObj.isRemote)
		                {
		                	if (!flag)
			                {
			                	player.inventory.consumeInventoryItem(Items.arrow);
			                }
			                else
			                {
			                    entityarrow.canBePickedUp = 0;
			                }
		                	player.worldObj.spawnEntityInWorld(entityarrow);
		                }
		                event.setCanceled(true);
		            }
		        }
		
    }
	@SubscribeEvent
    public void onArrowLooseEvent(ArrowLooseEvent event)
    {
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack par1ItemStack = inv.getStackInSlot(inv.currentItem);
		ItemStack blah = new ItemStack(Items.bow);
		ItemStack quiver = new ItemStack(mod_Rediscovered.Quiver);
		ItemStack lquiver = new ItemStack(mod_Rediscovered.LeatherQuiver);
		ItemStack cquiver = new ItemStack(mod_Rediscovered.ChainQuiver);
		ItemStack gquiver = new ItemStack(mod_Rediscovered.GoldQuiver);
		ItemStack iquiver = new ItemStack(mod_Rediscovered.IronQuiver);
		ItemStack dquiver = new ItemStack(mod_Rediscovered.DiamondQuiver);
		if(inv.getCurrentItem().equals(blah))
		{
			
		        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		        ItemStack itemstack = player.inventory.armorInventory[2];
		
		        if (itemstack != null && (itemstack == quiver || itemstack != null && itemstack.equals(lquiver) || itemstack != null && itemstack.equals(cquiver) || itemstack != null && itemstack.equals(iquiver) || itemstack != null && itemstack.equals(gquiver) || itemstack != null && itemstack.equals(dquiver)))
		        {
		        	event.setCanceled(true);
		        }
	        
		}
    }
    
	
	//Lantern
    @SubscribeEvent
    public void onPlayerMove(LivingUpdateEvent e)
    {
    	if (e.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer p = (EntityPlayer) e.entityLiving;
            if (!p.worldObj.isRemote)
            {
                if (mod_Rediscovered.hasLitLanternOnHotbar(p.inventory))
                {
                	BlockPos pos = new BlockPos(MathHelper.floor_double(p.posX), MathHelper.floor_double(p.posY) + 1, MathHelper.floor_double(p.posZ));
                	
                    if (p.ridingEntity != null)
                    {
                        pos = new BlockPos(MathHelper.floor_double(p.ridingEntity.posX), MathHelper.floor_double(p.ridingEntity.posY) + 1, MathHelper.floor_double(p.ridingEntity.posZ));
                    }

                    if (p.worldObj.getBlockState(pos).equals(Blocks.air.getDefaultState()))
                    {
                        p.worldObj.setBlockState(pos, mod_Rediscovered.Lantern.getDefaultState());
                    }

                    if (mod_Rediscovered.usernameLastPosMap.containsKey(p.getDisplayNameString()))
                    {
                    	BlockPos pos2 = mod_Rediscovered.usernameLastPosMap.get(p.getDisplayNameString());

                        if ((pos2.getX() != pos.getX() || pos2.getY() != pos.getY() || pos2.getZ() != pos.getZ()) && p.worldObj.getBlockState(pos2).equals(mod_Rediscovered.Lantern.getDefaultState()))
                        {
                            p.worldObj.setBlockToAir(pos2);
                        }
                    }
                    //TODO Find correct string for username
                    mod_Rediscovered.usernameLastPosMap.put(p.getDisplayNameString(), pos);
                }
                else
                {
                    if (mod_Rediscovered.usernameLastPosMap.containsKey(p.getDisplayNameString()))
                    {
                    	BlockPos pos = mod_Rediscovered.usernameLastPosMap.get(p.getDisplayNameString());

                        if (p.worldObj.getBlockState(pos).equals(mod_Rediscovered.Lantern.getDefaultState()))
                        {
                            p.worldObj.setBlockToAir(pos);
                        }

                        mod_Rediscovered.usernameLastPosMap.remove(p.getDisplayNameString());
                    }
                }
            }
        }
    }
    
    
    
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
	    if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
		    ExtendedPlayer.register((EntityPlayer) event.entity);
    }
    
    public static BlockPos verifyRespawnCoordinates(World par0World, BlockPos par1ChunkCoordinates, boolean par2, EntityPlayerMP player)
    {
//    	player.addChatComponentMessage(new ChatComponentText("" + player.mcServer.worldServerForDimension(0).getBlockState(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ)));
        if (player.mcServer.worldServerForDimension(0).getBlockState(par1ChunkCoordinates).getBlock().equals(Blocks.bed))
        {
            return par1ChunkCoordinates;
        }
        else
        {
            return null;
        }
    }
}
