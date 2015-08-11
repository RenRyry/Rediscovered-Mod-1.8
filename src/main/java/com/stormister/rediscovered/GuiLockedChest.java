package com.stormister.rediscovered;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLockedChest extends GuiContainer implements GuiYesNoCallback
{
	public final ResourceLocation texture = new ResourceLocation(mod_Rediscovered.modid.toLowerCase(), "textures/gui/LockedChest.png");
	private final int PROMPT_REPLY_ACTION = 0;
	private URI displayedURI = null;
	
	public GuiLockedChest(EntityPlayer player){
		super(new ContainerLockedChest(player));
		this.xSize = 248;
		this.ySize = 166;
	}
	
    public void initGui()
    {
        this.buttonList.clear();
        byte b0 = -16;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 107, this.height / 6 + 130, 98, 20, I18n.format("Not Now", new Object[0])));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 10, this.height / 6 + 130, 98, 20, I18n.format("Go to store", new Object[0])));
    }
    
    protected void actionPerformed(GuiButton p_146284_1_)
    {
        switch (p_146284_1_.id)
        {
            case 0:
            	this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
                break;
            case 1:
            	URI uri = URI.create("http://artur1998g.ru/store/loot.html");
				if (uri != null) {
					// Rude not to ask
					if (Minecraft.getMinecraft().gameSettings.chatLinksPrompt) {
						this.displayedURI = uri;
						this.mc.displayGuiScreen(new GuiConfirmOpenLink(this, this.displayedURI.toString(), PROMPT_REPLY_ACTION, false));
					} else {
						openURI(uri);
					}
				}
            	break;
        }
    }
    
//    @Override
//	public void componentMouseDown(BaseComponent component, int offsetX, int offsetY, int button) {
//		if (component.getName().equals("btnDonate")) {
//			if (((GuiComponentTextButton)component).isButtonEnabled()) {
//				URI uri = URI.create(getContainer().getOwner().getDonateUrl());
//				if (uri != null) {
//					// Rude not to ask
//					if (Minecraft.getMinecraft().gameSettings.chatLinksPrompt) {
//						this.displayedURI = uri;
//						this.mc.displayGuiScreen(new GuiConfirmOpenLink(this, this.displayedURI.toString(), PROMPT_REPLY_ACTION, false));
//					} else {
//						openURI(uri);
//					}
//				}
//			}
//		}
//	}

	private void openURI(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException e) {}
	}

	@Override
	public void confirmClicked(boolean result, int action) {
		if (action == PROMPT_REPLY_ACTION && result) {
			openURI(this.displayedURI);
			this.displayedURI = null;
		}
		this.mc.displayGuiScreen(this);
	}
    
    @Override
    public boolean doesGuiPauseGame()
    {
    	return false;
    }

	@Override
	public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(this.width / 2 - (this.xSize/2), this.height / 6, 0, 0, xSize, ySize);
	}
}
