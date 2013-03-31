package com.spacechase0.minecraft.decorativestuff.client.gui;

import com.spacechase0.minecraft.decorativestuff.inventory.KilnContainer;
import com.spacechase0.minecraft.decorativestuff.tileentity.KilnTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class KilnGui extends GuiContainer
{
	public KilnGui( InventoryPlayer player, KilnTileEntity theKiln )
	{
        super( new KilnContainer( player, theKiln ) );
        kiln = theKiln;
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

        if ( kiln.getBurnTimeLeft() > 0 )
        {
        	float scale = kiln.getBurnTimeLeft() / ( ( float )( kiln.getBurnTimeTotal() ) );
        	int size = ( int ) Math.ceil( 14 * scale );
        	
            this.drawTexturedModalRect( x + 80, y + 45 + ( 14 - size ), 176, 14 - size, 14, size );
        }
        
        if ( kiln.getProgressAmount() > 0 )
        {
        	if ( kiln.getProgressAmount() < KilnTileEntity.BASE_BURN_TIME )
        	{
            	float scale = kiln.getProgressAmount() / ( ( float ) KilnTileEntity.BASE_BURN_TIME );
            	int size = ( int ) Math.ceil( 24 * scale );
            	
                this.drawTexturedModalRect( x + 55, y + 17, 176, 14, size, 17 );
        	}
        	else
        	{
        		this.drawTexturedModalRect( x + 55, y + 17, 176, 14, 24, 17 );
        		
        		int prog2 = kiln.getProgressAmount() - KilnTileEntity.BASE_BURN_TIME;
            	if ( prog2 < KilnTileEntity.EXTRA_BURN_TIME )
            	{
                	float scale = prog2 / ( ( float ) KilnTileEntity.EXTRA_BURN_TIME );
                	int size = ( int ) Math.ceil( 24 * scale );
                	
                    this.drawTexturedModalRect( x + 97, y + 17, 176, 14, size, 17 );
            	}
            	else
            	{
            		this.drawTexturedModalRect( x + 97, y + 17, 176, 14, 24, 17 );
            	}
        	}
        }
	}
	
	private KilnTileEntity kiln;
}
