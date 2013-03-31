package com.spacechase0.minecraft.decorativestuff.client.gui;

import com.spacechase0.minecraft.decorativestuff.inventory.KilnContainer;
import com.spacechase0.minecraft.decorativestuff.tileentity.KilnTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class KilnGui extends GuiContainer
{
	public KilnGui( InventoryPlayer player, KilnTileEntity plate )
	{
        super( new KilnContainer( player, plate ) );
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer( int param1, int param2 )
	{
        fontRenderer.drawString( "Kiln", 8, 6, 4210752 );
        fontRenderer.drawString( StatCollector.translateToLocal( "container.inventory" ), 8, ySize - 96 + 2, 4210752 );
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer( float f, int i, int j )
	{
        GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
        mc.renderEngine.bindTexture("/mods/decorativeStuff/textures/gui/kiln.png" );
        int x = ( width - xSize ) / 2;
        int y = ( height - ySize ) / 2;
        this.drawTexturedModalRect( x, y, 0, 0, xSize, ySize );
        
        // TO DO: DRaw arrows and burning
	}
}
