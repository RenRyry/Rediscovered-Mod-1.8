package com.stormister.rediscovered;

import java.util.List;

import net.minecraft.block.BlockDispenser;
import net.minecraft.command.IEntitySelector;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.base.Predicates;

public class ItemLC extends ItemArmor
{
    private static final int[] maxDamageArray = new int[] {11, 16, 15, 13};
    public static final String[] EMPTY_SLOT_NAMES = new String[] {"minecraft:items/empty_armor_slot_helmet", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_boots"};
    private static final IBehaviorDispenseItem dispenserBehavior = new BehaviorDefaultDispenseItem()
    {
        protected ItemStack dispenseStack(IBlockSource source, ItemStack stack)
        {
            BlockPos blockpos = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
            int i = blockpos.getX();
            int j = blockpos.getY();
            int k = blockpos.getZ();
            AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
            List list = source.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb, Predicates.and(IEntitySelector.NOT_SPECTATING, new IEntitySelector.ArmoredMob(stack)));

            if (list.size() > 0)
            {
                EntityLivingBase entitylivingbase = (EntityLivingBase)list.get(0);
                int l = 0;
                int i1 = EntityLiving.getArmorPosition(stack);
                ItemStack itemstack1 = stack.copy();
                itemstack1.stackSize = 1;
                entitylivingbase.setCurrentItemOrArmor(i1 - l, itemstack1);

                if (entitylivingbase instanceof EntityLiving)
                {
                    ((EntityLiving)entitylivingbase).setEquipmentDropChance(i1, 2.0F);
                }

                --stack.stackSize;
                return stack;
            }
            else
            {
                return super.dispenseStack(source, stack);
            }
        }
    };
    public final int armorType;
    public final int damageReduceAmount;
    public final int renderIndex;
    private final ItemArmor.ArmorMaterial material;
    String name;
    public ItemLC(ItemArmor.ArmorMaterial material, int renderIndex, int armorType)
    {
    	super(material, renderIndex, armorType);
        this.material = material;
        this.armorType = armorType;
        if(this.armorType == 0)
        	name = "LeatherChainHelmet";
        else if(this.armorType == 1)
        	name = "LeatherChainChest";
        else if(this.armorType == 2)
        	name = "LeatherChainLegs";
        else if(this.armorType == 3)
        	name = "LeatherChainBoots";
        else
        	name = "LeatherChainHelmet";
        GameRegistry.registerItem(this, name);
        setUnlocalizedName(mod_Rediscovered.modid + "_" + name);
        
        this.renderIndex = renderIndex;
        this.damageReduceAmount = material.getDamageReductionAmount(armorType);
        this.setMaxDamage(material.getDurability(armorType));
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabCombat);
        BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
    }
    
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    public ItemArmor.ArmorMaterial getArmorMaterial()
    {
        return this.material;
    }
    
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return this.material.getRepairItem() == repair.getItem() ? true : super.getIsRepairable(toRepair, repair);
    }

    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        int i = EntityLiving.getArmorPosition(itemStackIn) - 1;
        ItemStack itemstack1 = playerIn.getCurrentArmor(i);

        if (itemstack1 == null)
        {
            playerIn.setCurrentItemOrArmor(i + 1, itemStackIn.copy());
            itemStackIn.stackSize = 0;
        }

        return itemStackIn;
    }
    
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return mod_Rediscovered.modid + ":textures/models/" + "leatherchain" + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
    }

    public String getName()
    {
    	return name;
    }
}