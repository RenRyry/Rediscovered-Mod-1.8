package com.stormister.rediscovered;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.IntCache;

public class ChunkManagerOld extends WorldChunkManager
{
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;

    private static OldNoiseGeneratorOctaves2 field_4194_e;
    private static OldNoiseGeneratorOctaves2 field_4193_f;
    private static OldNoiseGeneratorOctaves2 field_4192_g;
    public static double temperature[]; 
    public static double humidity[];
    public static double field_4196_c[];
    private static int biomeLookupTable[] = new int[4096];	

	protected ChunkManagerOld()
	{
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        this.biomesToSpawnIn.addAll(allowedBiomes);
	}

    public ChunkManagerOld(World par1World, boolean remote)
    {
        this();
        long seed = par1World.getSeed();
        
        if(remote)
        {
			generateBiomeLookup();
	        field_4194_e = new OldNoiseGeneratorOctaves2(new Random(seed * 9871L), 4);
	        field_4193_f = new OldNoiseGeneratorOctaves2(new Random(seed * 39811L), 4);
	        field_4192_g = new OldNoiseGeneratorOctaves2(new Random(seed * 0x84a59L), 2);
        }
    }    
    
	public static double[] getColdTemperatures(double ad[], int i, int j, int k, int l)
    {
        if(ad == null || ad.length < k * l)
        {
            ad = new double[k * l];
        }
        ad = field_4194_e.func_4112_a(ad, i, j, k, l, 0.02500000037252903D, 0.02500000037252903D, 0.25D);
        field_4196_c = field_4192_g.func_4112_a(field_4196_c, i, j, k, l, 0.25D, 0.25D, 0.58823529411764708D);
        int i1 = 0;
        for(int j1 = 0; j1 < k; j1++)
        {
            for(int k1 = 0; k1 < l; k1++)
            {
                double d = field_4196_c[i1] * 1.1000000000000001D + 0.5D;
                double d1 = 0.01D;
                double d2 = 1.0D - d1;
                double d3 = (ad[i1] * 0.14999999999999999D + 0.69999999999999996D) * d2 + d * d1;
                d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
                if(d3 < 0.0D)
                {
                    d3 = 0.0D;
                }
                if(d3 > 1.0D)
                {
                    d3 = 1.0D;
                }
                ad[i1] = d3;
                i1++;
            }

        }

        return ad;
    }
    public static int[] getBiomesGens(int par1, int par2, int par3, int par4)
    {		
		int[] abiomegenbase = new int[par3 * par4];
		temperature = field_4194_e.func_4112_a(temperature, par1, par2, par3, par3, 0.02500000037252903D, 0.02500000037252903D, 0.25D);
		humidity = field_4193_f.func_4112_a(humidity, par1, par2, par3, par3, 0.05000000074505806D, 0.05000000074505806D, 0.33333333333333331D);
		field_4196_c = field_4192_g.func_4112_a(field_4196_c, par1, par2, par3, par3, 0.25D, 0.25D, 0.58823529411764708D);
		int i1 = 0;
		for(int j1 = 0; j1 < par3; j1++)
		{
			for(int k1 = 0; k1 < par4; k1++)
			{
				double d = field_4196_c[i1] * 1.1000000000000001D + 0.5D;
				double d1 = 0.01D;
				double d2 = 1.0D - d1;
				double d3 = (temperature[i1] * 0.14999999999999999D + 0.69999999999999996D) * d2 + d * d1;
				d1 = 0.002D;
				d2 = 1.0D - d1;
				double d4 = (humidity[i1] * 0.14999999999999999D + 0.5D) * d2 + d * d1;
				d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
				if(d3 < 0.0D)
				{
					d3 = 0.0D;
				}
				if(d4 < 0.0D)
				{
					d4 = 0.0D;
				}
				if(d3 > 1.0D)
				{
					d3 = 1.0D;
				}
				if(d4 > 1.0D)
				{
					d4 = 1.0D;
				}
				temperature[i1] = d3;
				humidity[i1] = d4;
				abiomegenbase[i1++] = getBiomeFromLookup(d3, d4);
			}
		}
		return abiomegenbase;
    }
    
    public static int getBiomeFromLookup(double d, double d1)
    {
        int i = (int)(d * 63D);
        int j = (int)(d1 * 63D);
        return biomeLookupTable[i + j * 64];
    }
    
    public void generateBiomeLookup()  
    {
    	
				for(int i = 0; i < 64; i++)
				{
					for(int j = 0; j < 64; j++)
					{
						biomeLookupTable[i + j * 64] = getBetaBiomes((float)i / 63F, (float)j / 63F);
					}
				}
    }

    public int getBetaBiomes(float f, float f1) 
    {
		return mod_Rediscovered.heaven.biomeID;
    }
    
    public int getDefaultBiomes(float temp, float rain) 
    {
		rain *= temp;
		if(temp < 0.2F)
		{
			if(rain < 0.1F)
			{
				return BiomeGenBase.icePlains.biomeID;
			}
			else
			{
				return BiomeGenBase.coldTaiga.biomeID; 
			}
		}
		if(temp < 0.4F)
		{
			if(rain < 0.1F)
			{
				return BiomeGenBase.coldTaiga.biomeID; 
			}
			if(rain < 0.2F)
			{
				return BiomeGenBase.icePlains.biomeID;
			}
			else
			{
				return BiomeGenBase.coldTaiga.biomeID; 
			}
		}
		if(temp < 0.5F)
		{
			if(rain < 0.1F)
			{
				return BiomeGenBase.megaTaiga.biomeID;
			}
			if(rain < 0.2F)
			{
				return BiomeGenBase.forest.biomeID;
			}
			else
			{
				return BiomeGenBase.megaTaiga.biomeID;
			} 
		}
		if(temp < 0.7F)
		{
			if(rain < 0.3F)
			{
				return BiomeGenBase.forest.biomeID;
			}
			if(rain < 0.5F)
			{
				return BiomeGenBase.plains.biomeID;
			}
			if(rain < 0.7F)
			{
				return BiomeGenBase.plains.biomeID;
			}
			else
			{
				return BiomeGenBase.swampland.biomeID;
			} 
		}
		if(temp < 0.8F)
		{
			if(rain < 0.2F)
			{
				return BiomeGenBase.plains.biomeID;
			}
			if(rain < 0.5F)
			{
				return BiomeGenBase.forest.biomeID;
			}
			if(rain < 0.7F)
			{
				return BiomeGenBase.forest.biomeID;
			}
			else
			{
				return BiomeGenBase.swampland.biomeID;
			}
		}
		if(rain < 0.2F)
		{
			if(temp < 0.9F)
			{
				return BiomeGenBase.savanna.biomeID;
			}
			else
			{
				return BiomeGenBase.desert.biomeID;
			}
		}
		if(rain < 0.3F)
		{
			return BiomeGenBase.savanna.biomeID;
		}
		if(rain < 0.4F)
		{
			return BiomeGenBase.plains.biomeID;
		}
		if(rain < 0.7F)
		{
			return BiomeGenBase.forest.biomeID; 
		}
		if(rain < 0.8F)
		{
			return BiomeGenBase.jungleEdge.biomeID;
		}
		else
		{
			return BiomeGenBase.jungle.biomeID;
		}
    }
    @Override
    public List getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }
    @Override
    public BiomeGenBase func_180300_a(BlockPos p_180300_1_, BiomeGenBase p_180300_2_)
    {
        return this.biomeCache.func_180284_a(p_180300_1_.getX(), p_180300_1_.getZ(), p_180300_2_);
    }
    @Override
    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

		int var6[] = getBiomesGens(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            float var8 = (float)BiomeGenBase.getBiome(var6[var7]).getIntRainfall() / 65536.0F;

            if (var8 > 1.0F)
            {
                var8 = 1.0F;
            }

            par1ArrayOfFloat[var7] = var8;
        }

        return par1ArrayOfFloat;
    }
    @Override
    public float getTemperatureAtHeight(float par1, int par2)
    {
        return par1;
    }

    /*public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

		int var6[] = getBiomesGens(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            float var8 = (float)BiomeGenBase.func_150568_d(var6[var7]).getIntTemperature() / 65536.0F;

            if (var8 > 1.0F)
            {
                var8 = 1.0F;
            }

            par1ArrayOfFloat[var7] = var8;
        }

        return par1ArrayOfFloat;
    }*/
    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

		int var6[] = getBiomesGens(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            par1ArrayOfBiomeGenBase[var7] = BiomeGenBase.getBiome(var6[var7]);
        }

        return par1ArrayOfBiomeGenBase;
    }
    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }
    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
        {
            BiomeGenBase[] var9 = this.biomeCache.getCachedBiomes(par2, par3);
            System.arraycopy(var9, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
            return par1ArrayOfBiomeGenBase;
        }
        else
        {
			int var7[] = getBiomesGens(par2, par3, par4, par5);

            for (int var8 = 0; var8 < par4 * par5; ++var8)
            {
                par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.getBiome(var7[var8]);
            }

            return par1ArrayOfBiomeGenBase;
        }
    }
    @Override
    public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
    {
    	return false;
    }
    //TODO Maybe not keep
    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List biomes, Random random)
    {
    	return null;
    }
    @Override
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}