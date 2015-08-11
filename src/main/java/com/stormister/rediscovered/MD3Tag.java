package com.stormister.rediscovered;

import net.minecraft.util.Vec3;

public final class MD3Tag
{
  public String name;
  public Vec3[] coords;
  public Vec3[] c;
  public Vec3[] d;
  public Vec3[] e;

  public MD3Tag(int var1)
  {
    this.coords = new Vec3[var1];
    this.c = new Vec3[var1];
    this.d = new Vec3[var1];
    this.e = new Vec3[var1];
  }
}