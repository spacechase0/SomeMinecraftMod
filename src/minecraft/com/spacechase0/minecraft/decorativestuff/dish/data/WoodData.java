package com.spacechase0.minecraft.decorativestuff.dish.data;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.client.ClientProxy;
import com.spacechase0.minecraft.decorativestuff.client.renderer.ITextureBinder;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;

public class WoodData implements DishData
{
	public WoodData( NBTTagCompound tag )
	{
		id = tag.getShort( "Id" );
		data = tag.getByte( "Data" );
	}
	
	public WoodData( short theId, byte theData )
	{
		id = theId;
		data = theData;
	}
	
	@Override
	public void writeToNbt( NBTTagCompound tag )
	{
		tag.setShort( "Id", id );
		tag.setByte( "Data", data );
	}
	
	@Override
	public short getAsData()
	{
		short ret = 0;
		ret |= ( short )( ( id << 0 ) & 0x0FFF );
		ret |= ( short )( ( data << 12 ) & 0xF000 );
		
		return ret;
	}

	@Override
	public DishMaterial getMaterial()
	{
		return DishMaterial.WOOD;
	}
	
	@Override
	public void bindTexture( String dishType, ITextureBinder binder )
	{
		String path = "/mod/decorativestuff/texture/models/dish" + dishType + "Wood-" + id + "_" + data + ".png";
		binder.bindTexture( DecorativeStuff.instance.proxy.getTexture( path ) );
		//binder.bindTexture( "/mods/decorativestuff/textures/blocks/porcelainBlock.png" );
	}
	
	public static WoodData getFromData( int itemData )
	{
		short id = ( short )( ( itemData >> 0 ) & 0x0FFF );
		byte data = ( byte )( ( itemData >> 12 ) & 0x000F );
		
		return new WoodData( id, data );
	}

	public short id;
	public byte data;
}
