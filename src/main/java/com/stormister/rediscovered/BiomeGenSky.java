package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSky extends BiomeGenBase
{
    public BiomeGenSky(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityGiant.class, mod_Rediscovered.GiantSpawn, 2, 2));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityGoodDragon.class, mod_Rediscovered.RedDragonSpawn, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySkyChicken.class, mod_Rediscovered.SkyChickenSpawn, 6, 6));
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree genBigTreeChance(Random par1Random)
    {
        return (WorldGenAbstractTree)(new WorldGenCherryTrees(true));
    }
}