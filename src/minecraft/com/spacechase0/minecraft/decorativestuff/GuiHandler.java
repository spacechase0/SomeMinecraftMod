package com.spacechase0.minecraft.decorativestuff;

import com.spacechase0.minecraft.decorativestuff.client.gui.*;
import com.spacechase0.minecraft.decorativestuff.inventory.*;
import com.spacechase0.minecraft.decorativestuff.tileentity.*;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z )
	{
		TileEntity entity = world.getBlockTileEntity( x, y, z );
		if ( entity instanceof PlateTileEntity )
		{
			return new PlateContainer( player.inventory, ( PlateTileEntity ) entity );
		}
		else if ( entity instanceof KilnTileEntity )
		{
			return new KilnContainer( player.inventory, ( KilnTileEntity ) entity );
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement( int id, EntityPlayer player, World world, int x, int y, int z )
	{
		TileEntity entity = world.getBlockTileEntity( x, y, z );
		if ( entity instanceof PlateTileEntity )
		{
			return new PlateGui( player.inventory, ( PlateTileEntity ) entity );
		}
		else if ( entity instanceof KilnTileEntity )
		{
			return new KilnGui( player.inventory, ( KilnTileEntity ) entity );
		}
		
		return null;
	}
}
