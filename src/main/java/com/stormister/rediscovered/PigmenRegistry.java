package com.stormister.rediscovered;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.Validate;


import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.stormister.rediscovered.EntityPigman.ITradeList;
import com.stormister.rediscovered.EntityPigman.PriceInfo;
/**
 * Registry for villager trading control
 *
 * @author cpw
 *
 */
public class PigmenRegistry
{
    private static final PigmenRegistry INSTANCE = new PigmenRegistry();

    private Map<Class<?>, IVillageCreationHandler> villageCreationHandlers = Maps.newHashMap();
    private List<Integer> newVillagerIds = Lists.newArrayList();
    @SideOnly(Side.CLIENT)
    private Map<Integer, ResourceLocation> newVillagers;

    private PigmenRegistry()
    {
        init();
    }

    /**
     * Allow access to the {@link net.minecraft.world.gen.structure.StructureVillagePieces} array controlling new village
     * creation so you can insert your own new village pieces
     *
     * @author cpw
     *
     */
    public interface IVillageCreationHandler
    {
        /**
         * Called when {@link net.minecraft.world.gen.structure.MapGenVillage} is creating a new village
         *
         * @param random
         * @param i
         */
        StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i);

        /**
         * The class of the root structure component to add to the village
         */
        Class<?> getComponentClass();


        /**
         * Build an instance of the village component {@link net.minecraft.world.gen.structure.StructureVillagePieces}
         * @param villagePiece
         * @param startPiece
         * @param pieces
         * @param random
         * @param p1
         * @param p2
         * @param p3
         * @param facing
         * @param p5
         */
        Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, @SuppressWarnings("rawtypes") List pieces, Random random, int p1,
                int p2, int p3, EnumFacing facing, int p5);
    }

    public static PigmenRegistry instance()
    {
        return INSTANCE;
    }

    /**
     * Register your villager id
     * @param id
     */
    public void registerVillagerId(int id)
    {
        if (newVillagerIds.contains(id))
        {
            FMLLog.severe("Attempt to register duplicate villager id %d", id);
            throw new RuntimeException();
        }
        newVillagerIds.add(id);
    }
    /**
     * Register a new skin for a villager type
     *
     * @param villagerId
     * @param villagerSkin
     */
    @SideOnly(Side.CLIENT)
    public void registerVillagerSkin(int villagerId, ResourceLocation villagerSkin)
    {
        if (newVillagers == null)
        {
            newVillagers = Maps.newHashMap();
        }
        newVillagers.put(villagerId, villagerSkin);
    }

    /**
     * Register a new village creation handler
     *
     * @param handler
     */
    public void registerVillageCreationHandler(IVillageCreationHandler handler)
    {
        villageCreationHandlers.put(handler.getComponentClass(), handler);
    }

    /**
     * Callback to setup new villager types
     *
     * @param villagerType
     * @param defaultSkin
     */
    @SideOnly(Side.CLIENT)
    public static ResourceLocation getVillagerSkin(int villagerType, ResourceLocation defaultSkin)
    {
        if (instance().newVillagers != null && instance().newVillagers.containsKey(villagerType))
        {
            return instance().newVillagers.get(villagerType);
        }
        return defaultSkin;
    }

    /**
     * Returns a list of all added villager types
     *
     * @return newVillagerIds
     */
    public static Collection<Integer> getRegisteredVillagers()
    {
        return Collections.unmodifiableCollection(instance().newVillagerIds);
    }

    public static void addExtraVillageComponents(@SuppressWarnings("rawtypes") ArrayList components, Random random, int i)
    {
        @SuppressWarnings("unchecked")
        List<StructureVillagePieces.PieceWeight> parts = components;
        for (IVillageCreationHandler handler : instance().villageCreationHandlers.values())
        {
            parts.add(handler.getVillagePieceWeight(random, i));
        }
    }

    public static Object getVillageComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, @SuppressWarnings("rawtypes") List pieces, Random random,
            int p1, int p2, int p3, EnumFacing facing, int p5)
    {
        return instance().villageCreationHandlers.get(villagePiece.villagePieceClass).buildComponent(villagePiece, startPiece, pieces, random, p1, p2, p3, facing, p5);
    }

    public void register(VillagerProfession prof)
    {
        //blah
    }

    private boolean hasInit = false;
    private List<VillagerProfession> professions = Lists.newArrayList();



    private void init()
    {
        if (hasInit)
            return;

        VillagerProfession prof = new VillagerProfession("minecraft:farmer", "minecraft:textures/entity/villager/farmer.png");
        {
            register(prof);
            (new VillagerCareer(prof, "farmer"    )).init(VanillaTrades.trades[0][0]);
            (new VillagerCareer(prof, "fisherman" )).init(VanillaTrades.trades[0][1]);
            (new VillagerCareer(prof, "shepherd"  )).init(VanillaTrades.trades[0][2]);
            (new VillagerCareer(prof, "fletcher"  )).init(VanillaTrades.trades[0][3]);
        }
        prof = new VillagerProfession("minecraft:librarian", "minecraft:textures/entity/villager/librarian.png");
        {
            register(prof);
            (new VillagerCareer(prof, "librarian")).init(VanillaTrades.trades[1][0]);
        }
        prof = new VillagerProfession("minecraft:priest", "minecraft:textures/entity/villager/priest.png");
        {
            register(prof);
            (new VillagerCareer(prof, "cleric")).init(VanillaTrades.trades[2][0]);
        }
        prof = new VillagerProfession("minecraft:smith", "minecraft:textures/entity/villager/smith.png");
        {
            register(prof);
            (new VillagerCareer(prof, "armor" )).init(VanillaTrades.trades[3][0]);
            (new VillagerCareer(prof, "weapon")).init(VanillaTrades.trades[3][1]);
            (new VillagerCareer(prof, "tool"  )).init(VanillaTrades.trades[3][2]);
        }
        prof = new VillagerProfession("minecraft:butcher", "minecraft:textures/entity/villager/butcher.png");
        {
            register(prof);
            (new VillagerCareer(prof, "butcher")).init(VanillaTrades.trades[4][0]);
            (new VillagerCareer(prof, "leather")).init(VanillaTrades.trades[4][1]);
        }
    }

    public static class VillagerProfession
    {
        private ResourceLocation name;
        private ResourceLocation texture;
        private List<VillagerCareer> careers = Lists.newArrayList();

        public VillagerProfession(String name, String texture)
        {
            this.name = new ResourceLocation(name);
            this.texture = new ResourceLocation(texture);
        }

        private void register(VillagerCareer career)
        {
            Validate.isTrue(!careers.contains(career), "Attempted to register career that is already registered.");
            Validate.isTrue(career.profession == this, "Attempted to register career for the wrong profession.");
            career.id = careers.size();
            careers.add(career);
        }
    }

    public static class VillagerCareer
    {
        private VillagerProfession profession;
        private String name;
        private int id;
        public VillagerCareer(VillagerProfession parent, String name)
        {
            this.profession = parent;
            this.name = name;
            parent.register(this);
        }

        private VillagerCareer init(EntityPigman.ITradeList[][] traids)
        {
            return this;
        }

        @Override
        public boolean equals(Object o)
        {
            if (o == this) return true;
            if (!(o instanceof VillagerCareer)) return false;
            VillagerCareer oc = (VillagerCareer)o;
            return name.equals(oc.name) && profession == oc.profession;
        }
    }

    /**
     * Hook called when spawning a Villager, sets it's profession to a random registered profession.
     *
     * @param entity The new entity
     * @param rand The world's RNG
     */
    public static void setRandomProfession(EntityPigman entity, Random rand)
    {
        entity.setProfession(rand.nextInt(5));
    }


    private static class VanillaTrades
    {
        private static final ITradeList[][][][] trades =
        {
            {
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.wheat, new PriceInfo(18, 22)),
                        new EntityPigman.EmeraldForItems(Items.potato, new PriceInfo(15, 19)),
                        new EntityPigman.EmeraldForItems(Items.carrot, new PriceInfo(15, 19)),
                        new EntityPigman.EmeraldForItems(Items.bread, new PriceInfo(-4, -2))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Item.getItemFromBlock(Blocks.pumpkin), new PriceInfo(8, 13)),
                        new EntityPigman.EmeraldForItems(Items.pumpkin_pie, new PriceInfo(-3, -2))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Item.getItemFromBlock(Blocks.melon_block), new PriceInfo(7, 12)),
                        new EntityPigman.EmeraldForItems(Items.apple, new PriceInfo(-5, -7))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.cookie, new PriceInfo(-6, -10)),
                        new EntityPigman.EmeraldForItems(Items.cake, new PriceInfo(1, 1))
                    }
                },
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.string, new PriceInfo(15, 20)),
                        new EntityPigman.EmeraldForItems(Items.coal, new PriceInfo(16, 24)),
                        new EntityPigman.ItemAndEmeraldToItem(Items.fish, new PriceInfo(6, 6), Items.cooked_fish, new PriceInfo(6, 6))
                    },
                    {
                        new EntityPigman.ListEnchantedItemForEmeralds(Items.fishing_rod, new PriceInfo(7, 8))
                    }
                },
                {
                    {
                        new EntityPigman.EmeraldForItems(Item.getItemFromBlock(Blocks.wool), new PriceInfo(16, 22)),
                        new EntityPigman.EmeraldForItems(Items.shears, new PriceInfo(3, 4))
                    },
                    {
                        
                    }
                },
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.string, new PriceInfo(15, 20)),
                        new EntityPigman.EmeraldForItems(Items.arrow, new PriceInfo(-12, -8))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.bow, new PriceInfo(2, 3)),
                        new EntityPigman.ItemAndEmeraldToItem(Item.getItemFromBlock(Blocks.gravel), new PriceInfo(10, 10), Items.flint, new PriceInfo(6, 10))
                    }
                }
            },
            {
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.paper, new PriceInfo(24, 36)),
                        new EntityPigman.ListEnchantedBookForEmeralds()
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.book, new PriceInfo(8, 10)),
                        new EntityPigman.EmeraldForItems(Items.compass, new PriceInfo(10, 12)),
                        new EntityPigman.EmeraldForItems(Item.getItemFromBlock(Blocks.bookshelf), new PriceInfo(3, 4))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.written_book, new PriceInfo(2, 2)),
                        new EntityPigman.EmeraldForItems(Items.clock, new PriceInfo(10, 12)),
                        new EntityPigman.EmeraldForItems(Item.getItemFromBlock(Blocks.glass), new PriceInfo(-5, -3))
                    },
                    {
                        new EntityPigman.ListEnchantedBookForEmeralds()
                    },
                    {
                        new EntityPigman.ListEnchantedBookForEmeralds()
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.name_tag, new PriceInfo(20, 22))
                    }
                }
            },
            {
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.rotten_flesh, new PriceInfo(36, 40)),
                        new EntityPigman.EmeraldForItems(Items.gold_ingot, new PriceInfo(8, 10))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.redstone, new PriceInfo(-4, -1)),

                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.ender_eye, new PriceInfo(7, 11)),
                        new EntityPigman.EmeraldForItems(Item.getItemFromBlock(Blocks.glowstone), new PriceInfo(-3, -1))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.experience_bottle, new PriceInfo(3, 11))
                    }
                }
            },
            {
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.coal, new PriceInfo(16, 24)),
                        new EntityPigman.EmeraldForItems(Items.iron_helmet, new PriceInfo(4, 6))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.iron_ingot, new PriceInfo(7, 9)),
                        new EntityPigman.EmeraldForItems(Items.iron_chestplate, new PriceInfo(10, 14))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.diamond, new PriceInfo(3, 4)),
                        new EntityPigman.ListEnchantedItemForEmeralds(Items.diamond_chestplate, new PriceInfo(16, 19))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.chainmail_boots, new PriceInfo(5, 7)),
                        new EntityPigman.EmeraldForItems(Items.chainmail_leggings, new PriceInfo(9, 11)),
                        new EntityPigman.EmeraldForItems(Items.chainmail_helmet, new PriceInfo(5, 7)),
                        new EntityPigman.EmeraldForItems(Items.chainmail_chestplate, new PriceInfo(11, 15))
                    }
                },
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.coal, new PriceInfo(16, 24)),
                        new EntityPigman.EmeraldForItems(Items.iron_axe, new PriceInfo(6, 8))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.iron_ingot, new PriceInfo(7, 9)),
                        new EntityPigman.ListEnchantedItemForEmeralds(Items.iron_sword, new PriceInfo(9, 10))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.diamond, new PriceInfo(3, 4)),
                        new EntityPigman.ListEnchantedItemForEmeralds(Items.diamond_sword, new PriceInfo(12, 15)),
                        new EntityPigman.ListEnchantedItemForEmeralds(Items.diamond_axe, new PriceInfo(9, 12))
                    }
                },
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.coal, new PriceInfo(16, 24)),
                        new EntityPigman.ListEnchantedItemForEmeralds(Items.iron_shovel, new PriceInfo(5, 7))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.iron_ingot, new PriceInfo(7, 9)),
                        new EntityPigman.ListEnchantedItemForEmeralds(Items.iron_pickaxe, new PriceInfo(9, 11))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.diamond, new PriceInfo(3, 4)),
                        new EntityPigman.ListEnchantedItemForEmeralds(Items.diamond_pickaxe, new PriceInfo(12, 15))
                    }
                }
            },
            {
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.porkchop, new PriceInfo(14, 18)),
                        new EntityPigman.EmeraldForItems(Items.chicken, new PriceInfo(14, 18))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.coal, new PriceInfo(16, 24)),
                        new EntityPigman.EmeraldForItems(Items.cooked_porkchop, new PriceInfo(-7, -5)),
                        new EntityPigman.EmeraldForItems(Items.cooked_chicken, new PriceInfo(-8, -6))
                    }
                },
                {
                    {
                        new EntityPigman.EmeraldForItems(Items.leather, new PriceInfo(9, 12)),
                        new EntityPigman.EmeraldForItems(Items.leather_leggings, new PriceInfo(2, 4))
                    },
                    {
                        new EntityPigman.ListEnchantedItemForEmeralds(Items.leather_chestplate, new PriceInfo(7, 12))
                    },
                    {
                        new EntityPigman.EmeraldForItems(Items.saddle, new PriceInfo(8, 10))
                    }
                }
            }
        };
    }
}