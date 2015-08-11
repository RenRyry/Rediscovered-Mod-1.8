package com.stormister.rediscovered;

import java.io.PrintStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public final class MD3Renderer
{
  private MD3Model model;
  private boolean b = false;
  private int displayList;
  private boolean useAnimation;

  public MD3Renderer(MD3Model var1, boolean anim)
  {
    this.model = var1;
    this.displayList = 0;

    this.useAnimation = anim;
  }

  public final int getAnimFrames() {
    return this.model.animFrames;
  }

  public final void render(int var1, int var2, float var3) {
    if ((this.displayList == 0) || (this.useAnimation)) {
      if (!this.useAnimation) {
        this.displayList = GL11.glGenLists(1);
      }

      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32888);
      GL11.glEnableClientState(32885);
      if (!this.useAnimation) {
        GL11.glNewList(this.displayList, 4864);
      }

      for (int i = 0; i < this.model.surfaces.length; i++) {
        MD3Surface surface = this.model.surfaces[i];
        if (this.useAnimation)
          surface.setFrame(var1, var2, var3);
        else {
          surface.setFrame(0, 0, 0.0F);
        }

        surface.triangles.position(0);

        surface.d.position(0);
        GL11.glVertexPointer(3, 0, surface.vertices);
        GL11.glNormalPointer(0, surface.normals);
        GL11.glTexCoordPointer(2, 0, surface.d);
        GL11.glDrawElements(4, surface.triangles);
      }

      if (!this.useAnimation) {
        GL11.glEndList();
      }
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32888);
      GL11.glDisableClientState(32885);
    }

    if (!this.useAnimation)
      GL11.glCallList(this.displayList);
  }

  public void renderTag(String tagName, MD3Renderer renderer, int var1, int var2, float var3, int var4, int var5, float var6)
  {
    MD3Tag tag = (MD3Tag)this.model.tags.get(tagName);
    if (tag == null) {
      System.out.println(tagName + ": no such tag!");
      return;
    }
    double x = tag.coords[var1].xCoord + (tag.coords[var2].xCoord - tag.coords[var1].xCoord) * var3;
    double y = tag.coords[var1].yCoord + (tag.coords[var2].yCoord - tag.coords[var1].yCoord) * var3;
    double z = tag.coords[var1].zCoord + (tag.coords[var2].zCoord - tag.coords[var1].zCoord) * var3;
    GL11.glTranslated(x, y, z);
    renderer.render(var4, var5, var6);
  }
}