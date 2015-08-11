package com.stormister.rediscovered;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ServerPacketHandler
{
   private void toggleHotbarLantern(EntityPlayer p, int slot)
    {
        if (slot < 0 || slot > 8)
        {
            return;
        }

        ItemStack item = p.inventory.mainInventory[slot];

        if (item != null && (item == new ItemStack(mod_Rediscovered.ItemLantern)))
        {
            if (item == new ItemStack(mod_Rediscovered.ItemLantern))
            {
                p.inventory.mainInventory[slot] = new ItemStack(mod_Rediscovered.ItemLantern, 1);
            }
            else
            {
                
            }
        }
    }

    
}
