package com.spacechase0.minecraft.decorativestuff.client.gui;

import com.spacechase0.minecraft.decorativestuff.inventory.DishContainer;
import com.spacechase0.minecraft.decorativestuff.tileentity.DishTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class PlateGui extends GuiContainer
{
	public PlateGui( InventoryPlayer player, DishTileEntity plate )
	{
        super( new DishContainer( player, plate ) );
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer( int param1, int param2 )
	{
        fontRenderer.drawString( "Plate", 8, 6, 4210752 );
        fontRenderer.drawString( StatCollector.translateToLocal( "container.inventory" ), 8, ySize - 96 + 2, 4210752 );
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer( float f, int i, int j )
	{
        GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
        mc.renderEngine.bindTexture("/mods/decorativeStuff/textures/gui/food.png" );
        int x = ( width - xSize ) / 2;
        int y = ( height - ySize ) / 2;
        this.drawTexturedModalRect( x, y, 0, 0, xSize, ySize );
	}
}
