package com.stormister.rediscovered;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerLockedChest extends Container
{

	public ContainerLockedChest(EntityPlayer player){
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
