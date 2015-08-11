package com.stormister.rediscovered;


import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderHeaven extends WorldProvider {
	private float[] colorsSunriseSunset = new float[4];
	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(mod_Rediscovered.heaven, this.dimensionId);
		this.dimensionId = mod_Rediscovered.DimID;
		this.hasNoSky = false;
	}
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderHeaven(this.worldObj, this.worldObj.getSeed());
	}
	@Override
	public int getAverageGroundLevel() {
		return 200;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public double getVoidFogYFactor()
    {
        return 30;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2) {
		return false;
	}
	@Override
	public String getDimensionName() {
		return "Sky";
	}
	@Override
	public double getMovementFactor()
    {
        return 1.2;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored() {
		return true;
	}
	@Override
	public boolean canRespawnHere() {
		return false;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {
		return 0F;
	}
	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

//	public ChunkCoordinates getEntrancePortalLocation() {
//		return new ChunkCoordinates(50, 75, 0);
//	}
	@Override
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage() {
		if ((this instanceof WorldProviderHeaven)) {
			return "Falling Asleep...";
		}
		return null;
	}
	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.0F;
	}
	@Override
	public String getInternalNameSuffix() {
		// TODO Auto-generated method stub
		return null;
	}

}