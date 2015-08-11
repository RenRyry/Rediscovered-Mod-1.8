package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorPigmanVillage implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		if (world.provider.getDimensionId() == mod_Rediscovered.DimID && mod_Rediscovered.EnablePigmanVillages) 
		{
				generateSky(world, random, chunkX * 16, chunkZ * 16);
		}

	}

	public void generateSky(World world, Random rand, int y, int z) {

		int RandPosX = y + rand.nextInt(16);
		int RandPosY = rand.nextInt(150) + 50;
		int RandPosZ = z + rand.nextInt(16);
		//TODO
		(new WorldGenSmallPigmanVillage()).generate(world, rand, new BlockPos(RandPosX, RandPosY, RandPosZ));

	}

}