package com.stormister.rediscovered;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkProviderHeaven implements IChunkProvider
{
    private Random rand;
    private NoiseOctavesBeta field_912_k;
    private NoiseOctavesBeta field_911_l;
    private NoiseOctavesBeta field_910_m;
    private NoiseOctavesBeta field_909_n;
    private NoiseOctavesBeta field_908_o;
    public NoiseOctavesBeta field_922_a;
    public NoiseOctavesBeta field_921_b;
    public NoiseOctavesBeta mobSpawnerNoise;

    private World worldObj;
    private double field_4180_q[];
    private double sandNoise[];
    private double gravelNoise[];
    private double stoneNoise[];
    private MapGenSky field_902_u;
    private BiomeGenBase biomesForGeneration[];
	private int worldtype;

    double field_4185_d[];
    double field_4184_e[];
    double field_4183_f[];
    double field_4182_g[];
    double field_4181_h[];
    int field_914_i[][];
    private double generatedTemperatures[];
    private boolean nether;

    public ChunkProviderHeaven(World world, long l)
    {
        sandNoise = new double[256];
        gravelNoise = new double[256];
        stoneNoise = new double[256];
        field_902_u = new MapGenSkyCaves();
        field_914_i = new int[32][32];
        worldObj = world;
        rand = new Random(l);
        nether = false;
        field_912_k = new NoiseOctavesBeta(rand, 16);
        field_911_l = new NoiseOctavesBeta(rand, 16);
        field_910_m = new NoiseOctavesBeta(rand, 8);
        field_909_n = new NoiseOctavesBeta(rand, 4);
        field_908_o = new NoiseOctavesBeta(rand, 4);
        field_922_a = new NoiseOctavesBeta(rand, 10);
        field_921_b = new NoiseOctavesBeta(rand, 16);
        mobSpawnerNoise = new NoiseOctavesBeta(rand, 8);
    }

    public void generateTerrain(int i, int j, ChunkPrimer primer, BiomeGenBase abiomegenbase[], double ad[])
    {
        byte byte0 = 2;
        int k = byte0 + 1;
        byte byte1 = 33;
        int l = byte0 + 1;
        field_4180_q = func_4061_a(field_4180_q, i * byte0, 0, j * byte0, k, byte1, l);
        for(int i1 = 0; i1 < byte0; i1++)
        {
            for(int j1 = 0; j1 < byte0; j1++)
            {
                for(int k1 = 0; k1 < 32; k1++)
                {
                    double d = 0.25D;
                    double d1 = field_4180_q[((i1 + 0) * l + (j1 + 0)) * byte1 + (k1 + 0)];
                    double d2 = field_4180_q[((i1 + 0) * l + (j1 + 1)) * byte1 + (k1 + 0)];
                    double d3 = field_4180_q[((i1 + 1) * l + (j1 + 0)) * byte1 + (k1 + 0)];
                    double d4 = field_4180_q[((i1 + 1) * l + (j1 + 1)) * byte1 + (k1 + 0)];
                    double d5 = (field_4180_q[((i1 + 0) * l + (j1 + 0)) * byte1 + (k1 + 1)] - d1) * d;
                    double d6 = (field_4180_q[((i1 + 0) * l + (j1 + 1)) * byte1 + (k1 + 1)] - d2) * d;
                    double d7 = (field_4180_q[((i1 + 1) * l + (j1 + 0)) * byte1 + (k1 + 1)] - d3) * d;
                    double d8 = (field_4180_q[((i1 + 1) * l + (j1 + 1)) * byte1 + (k1 + 1)] - d4) * d;
                    for(int l1 = 0; l1 < 4; l1++)
                    {
                        double d9 = 0.125D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        //Imported from ChunkProviderEnd
                        for (int i2 = 0; i2 < 8; ++i2)
                        {
                            double d14 = 0.125D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int j2 = 0; j2 < 8; ++j2)
                            {
                                IBlockState iblockstate = null;

                                if (d15 > 0.0D)
                                {
                                    iblockstate = Blocks.stone.getDefaultState();
                                }

                                int k2 = i2 + i1 * 8;
                                int l2 = l1 + k1 * 4;
                                int i3 = j2 + j1 * 8;
                                primer.setBlockState(k2, l2, i3, iblockstate);
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }
                        

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }

                }

            }

        }

    }

    public void replaceBlocksForBiome(int io, int jo, ChunkPrimer primer, BiomeGenBase abiomegenbase[])
    {
    	for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                byte b0 = 1;
                int k = -1;
                IBlockState iblockstate = Blocks.grass.getDefaultState();
                IBlockState iblockstate1 = Blocks.dirt.getDefaultState();

                for (int l = 127; l >= 0; --l)
                {
                    IBlockState iblockstate2 = primer.getBlockState(i, l, j);

                    if (iblockstate2.getBlock().getMaterial() == Material.air)
                    {
                        k = -1;
                    }
                    else if (iblockstate2.getBlock() == Blocks.stone)
                    {
                        if (k == -1)
                        {
                            if (b0 <= 0)
                            {
                                iblockstate = Blocks.grass.getDefaultState();
                                iblockstate1 = Blocks.dirt.getDefaultState();
                            }

                            k = b0;

                            if (l >= 0)
                            {
                            	primer.setBlockState(i, l, j, iblockstate);
                            }
                            else
                            {
                            	primer.setBlockState(i, l, j, iblockstate1);
                            }
                        }
                        else if (k > 0)
                        {
                            --k;
                            primer.setBlockState(i, l, j, iblockstate1);
                        }
                    }
                }
            }
        }
    }
    
    public Chunk loadChunk(int par1, int par2)
    {
        return provideChunk(par1, par2);
    }

    public Chunk provideChunk(int i, int j)
    {
    	ChunkPrimer primer = new ChunkPrimer();
        rand.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
        Block blocks[] = new Block[32768];
        biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, i * 16, j * 16, 16, 16);
        double ad[] = ChunkManagerOld.temperature;
        generateTerrain(i, j, primer, biomesForGeneration, ad);
        replaceBlocksForBiome(i, j, primer, biomesForGeneration);
        field_902_u.generate(this, worldObj, i, j, blocks);

        Chunk chunk = new Chunk(worldObj, primer, i, j);
        byte abyte1[] = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; k++)
        {
            abyte1[k] = (byte)biomesForGeneration[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] func_4061_a(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
    	if(ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        double ad1[] = ChunkManagerOld.temperature;
        double ad2[] = ChunkManagerOld.humidity;
        field_4182_g = field_922_a.generateNoiseOctaves(field_4182_g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        field_4181_h = field_921_b.generateNoiseOctaves(field_4181_h, i, k, l, j1, 200D, 200D, 0.5D);
        d *= 2D;
        field_4185_d = field_910_m.generateNoiseOctaves(field_4185_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        field_4184_e = field_912_k.generateNoiseOctaves(field_4184_e, i, j, k, l, i1, j1, d, d1, d);
        field_4183_f = field_911_l.generateNoiseOctaves(field_4183_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        int i2 = 16 / l;
        for(int j2 = 0; j2 < l; j2++)
        {
            int k2 = j2 * i2 + i2 / 2;
            for(int l2 = 0; l2 < j1; l2++)
            {
                int i3 = l2 * i2 + i2 / 2;
				double d3;
				if(nether) 
				{
					double d2 = ad1[k2 * 16 + i3];
					d3 = ad2[k2 * 16 + i3] * d2;
				} 
				else 
				{
					d3 = 0.5D;
				}
                double d4 = 1.0D - d3;
                d4 *= d4;
                d4 *= d4;
                d4 = 1.0D - d4;
                double d5 = (field_4182_g[l1] + 256D) / 512D;
                d5 *= d4;
                if(d5 > 1.0D)
                {
                    d5 = 1.0D;
                }
                double d6 = field_4181_h[l1] / 8000D;
                if(d6 < 0.0D)
                {
                    d6 = -d6 * 0.29999999999999999D;
                }
                d6 = d6 * 3D - 2D;
                if(d6 > 1.0D)
                {
                    d6 = 1.0D;
                }
                d6 /= 8D;
                d6 = 0.0D;
                if(d5 < 0.0D)
                {
                    d5 = 0.0D;
                }
                d5 += 0.5D;
                d6 = (d6 * (double)i1) / 16D;
                l1++;
                double d7 = (double)i1 / 2D;
                for(int j3 = 0; j3 < i1; j3++)
                {
                    double d8 = 0.0D;
                    double d9 = (((double)j3 - d7) * 8D) / d5;
                    if(d9 < 0.0D)
                    {
                        d9 *= -1D;
                    }
                    double d10 = field_4184_e[k1] / 512D;
                    double d11 = field_4183_f[k1] / 512D;
                    double d12 = (field_4185_d[k1] / 10D + 1.0D) / 2D;
                    if(d12 < 0.0D)
                    {
                        d8 = d10;
                    } else
                    if(d12 > 1.0D)
                    {
                        d8 = d11;
                    } else
                    {
                        d8 = d10 + (d11 - d10) * d12;
                    }
                    d8 -= 8D;
                    int k3 = 32;
                    if(j3 > i1 - k3)
                    {
                        double d13 = (float)(j3 - (i1 - k3)) / ((float)k3 - 1.0F);
                        d8 = d8 * (1.0D - d13) + -30D * d13;
                    }
                    k3 = 8;
                    if(j3 < k3)
                    {
                        double d14 = (float)(k3 - j3) / ((float)k3 - 1.0F);
                        d8 = d8 * (1.0D - d14) + -30D * d14;
                    }
                    ad[k1] = d8;
                    k1++;
                }

            }

        }

        return ad;
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }
    @Override
    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
			BlockSand.fallInstantly = true;
			int k = i * 16;
			int l = j * 16;
			
			BiomeGenBase biomegenbase = worldObj.getWorldChunkManager().func_180300_a(new BlockPos(k + 16, 64, l + 16), null);
			rand.setSeed(worldObj.getSeed());
			long l1 = (rand.nextLong() / 2L) * 2L + 1L;
			long l2 = (rand.nextLong() / 2L) * 2L + 1L;
			rand.setSeed((long)i * l1 + (long)j * l2 ^ worldObj.getSeed());
			double d = 0.25D;

			MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, worldObj, rand, i, j, false));

			if (rand.nextInt(4) == 0)
	        {
	            int i1 = k + rand.nextInt(16) + 8;
	            worldObj.getClass();
	            int l4 = rand.nextInt(128);
	            int i8 = l + rand.nextInt(16) + 8;
	            (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, new BlockPos(i1, l4, i8));
	        }
			
	        for (int i2 = 0; i2 < 10; i2++)
	        {
	            int k5 = k + rand.nextInt(16);
	            worldObj.getClass();
	            int l8 = rand.nextInt(128);
	            int j13 = l + rand.nextInt(16);
	            (new WorldGenClay(32)).generate(worldObj, rand, new BlockPos(k5, l8, j13));
	        }

	        for (int j2 = 0; j2 < 20; j2++)
	        {
	            int l5 = k + rand.nextInt(16);
	            worldObj.getClass();
	            int i9 = rand.nextInt(128);
	            int k13 = l + rand.nextInt(16);
	            (new WorldGenMinable(Blocks.dirt.getDefaultState(), 32)).generate(worldObj, rand, new BlockPos(l5, i9, k13));
	        }

	        for (int j3 = 0; j3 < 20; j3++)
	        {
	            int k6 = k + rand.nextInt(16);
	            worldObj.getClass();
	            int l9 = rand.nextInt(64);
	            int j14 = l + rand.nextInt(16);
	            (new WorldGenMinable(Blocks.iron_ore.getDefaultState(), 8)).generate(worldObj, rand, new BlockPos(k6, l9, j14));
	        }

	        for (int k3 = 0; k3 < 2; k3++)
	        {
	            int l6 = k + rand.nextInt(16);
	            worldObj.getClass();
	            int i10 = rand.nextInt(32);
	            int k14 = l + rand.nextInt(16);
	            (new WorldGenMinable(Blocks.gold_ore.getDefaultState(), 8)).generate(worldObj, rand, new BlockPos(l6, i10, k14));
	        }

	        for (int i4 = 0; i4 < 1; i4++)
	        {
	            int j7 = k + rand.nextInt(16);
	            worldObj.getClass();
	            int k10 = rand.nextInt(16);
	            int i15 = l + rand.nextInt(16);
	            (new WorldGenMinable(Blocks.diamond_ore.getDefaultState(), 7)).generate(worldObj, rand, new BlockPos(j7, k10, i15));
	        }

	        for (int j4 = 0; j4 < 1; j4++)
	        {
	            int k7 = k + rand.nextInt(16);
	            worldObj.getClass();
	            worldObj.getClass();
	            int l10 = rand.nextInt(16) + rand.nextInt(16);
	            int j15 = l + rand.nextInt(16);
	            (new WorldGenMinable(Blocks.lapis_ore.getDefaultState(), 6)).generate(worldObj, rand, new BlockPos(k7, l10, j15));
	        }

	        d = 0.5D;
	        int l7 = 0;

	        if (rand.nextInt(10) == 0)
	        {
	            l7++;
	        }
	        
//	        TREES
	        for (int i11 = 0; i11 < l7; i11++)
	        {
	            int k15 = k + rand.nextInt(16) + 8;
	            int j18 = l + rand.nextInt(16) + 8;
	            WorldGenerator worldgenerator = biomegenbase.genBigTreeChance(rand);
	            worldgenerator.generate(worldObj, rand, new BlockPos(k15, worldObj.getChunkFromChunkCoords(k15 >> 4, j18 >> 4).getHeight(k15 & 15, j18 & 15), j18));
	        }
	        for (int i11 = 0; i11 < l7; i11++)
	        {
	            int k15 = k + rand.nextInt(16) + 8;
	            int j18 = l + rand.nextInt(16) + 8;
	            (new WorldGenCherryTrees(true)).generate(worldObj, rand, new BlockPos(k15, worldObj.getChunkFromChunkCoords(k15 >> 4, j18 >> 4).getHeight(k15 & 15, j18 & 15), j18));
	        }

	        for (int j11 = 0; j11 < 2; j11++)
	        {
	            int l15 = k + rand.nextInt(16) + 8;
	            worldObj.getClass();
	            int k18 = rand.nextInt(128);
	            int i21 = l + rand.nextInt(16) + 8;
	            (new WorldGenFlowers(Blocks.yellow_flower, EnumFlowerType.DANDELION)).generate(worldObj, rand, new BlockPos(l15, k18, i21));
	        }

	        for (int j11 = 0; j11 < 2; j11++)
	        {
	            int k11 = k + rand.nextInt(16) + 8;
	            worldObj.getClass();
	            int i16 = rand.nextInt(128);
	            int l18 = l + rand.nextInt(16) + 8;
	            (new WorldGenRose(mod_Rediscovered.Rose, EnumFlowerType.POPPY)).generate(worldObj, rand, new BlockPos(k11, i16, l18));
	        }

	        for (int j12 = 0; j12 < 10; j12++)
	        {
	            int l16 = k + rand.nextInt(16) + 8;
	            worldObj.getClass();
	            int k19 = rand.nextInt(128);
	            int j21 = l + rand.nextInt(16) + 8;
	            (new WorldGenReed()).generate(worldObj, rand, new BlockPos(l16, k19, j21));
	        }

	        for (int k17 = 0; k17 < 50; k17++)
	        {
	            int j20 = k + rand.nextInt(16) + 8;
	            worldObj.getClass();
	            int l21 = rand.nextInt(rand.nextInt(120) + 8);
	            int l22 = l + rand.nextInt(16) + 8;
	            (new WorldGenLiquids(Blocks.flowing_water)).generate(worldObj, rand, new BlockPos(j20, l21, l22));
	        }

	        for (int i18 = 0; i18 < 16; i18++)
	        {
	            for (int l20 = 0; l20 < 16; l20++)
	            {
	            	
	                int j22 = worldObj.getPrecipitationHeight(new BlockPos(k + i18, 70, l + l20)).getY();
	                
	                //SNOW HEIGHT
	                if (j22 > 70 && j22 < 96)
	                {
	                    worldObj.setBlockState(new BlockPos(i18 + k, j22, l20 + l), Blocks.snow_layer.getDefaultState());
	                }
	            }
	        }

	        BlockSand.fallInstantly = false;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(new BlockPos(par2, 64, par4));
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void saveExtraData() {}

    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_)
    {}

	@Override
	public Chunk provideChunk(BlockPos blockPosIn) {
		return this.provideChunk(blockPosIn.getX(), blockPosIn.getZ());
	}

	@Override
	public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
		return true;
	}

	@Override
	public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_)
    {
        return this.worldObj.getBiomeGenForCoords(p_177458_2_).getSpawnableList(p_177458_1_);
    }

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		return null;
	}
}