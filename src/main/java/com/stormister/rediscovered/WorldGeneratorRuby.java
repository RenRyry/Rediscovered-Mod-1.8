package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorRuby implements IWorldGenerator 
{
	 
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
                    IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
            switch(world.provider.getDimensionId()){
            case 0 : generateSurface(world, random,chunkX*16,chunkZ*16);
            }

    }

    private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
            for(int i =0; i<6;i++){
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int yCoord = random.nextInt(32);
            (new WorldGenMinable(mod_Rediscovered.RubyOre.getDefaultState(), 4)).generate(world, random, new BlockPos(Xcoord, yCoord, Zcoord));
    }}
}