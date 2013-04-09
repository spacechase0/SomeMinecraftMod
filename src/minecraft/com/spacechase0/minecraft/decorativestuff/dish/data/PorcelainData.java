package com.spacechase0.minecraft.decorativestuff.dish.data;

import com.spacechase0.minecraft.decorativestuff.client.renderer.ITextureBinder;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import net.minecraft.nbt.NBTTagCompound;

public class PorcelainData implements DishData
{
	public PorcelainData( NBTTagCompound tag )
	{
		color = tag.getByte( "Color" );
		stencilType = tag.getByte( "StencilType" );
		stencilColor = tag.getByte( "StencilColor" );
	}
	
	public PorcelainData( byte theColor, byte theStencilType, byte theStencilColor )
	{
		color = theColor;
		stencilType = theStencilType;
		stencilColor = theStencilColor;
	}
	
	@Override
	public void writeToNbt( NBTTagCompound tag )
	{
		tag.setByte( "Color", color );
		tag.setByte( "StencilType", stencilType );
		tag.setByte( "StencilColor", stencilColor );
	}

	@Override
	public short getAsData()
	{
		short data = 0;
		data |= ( short )( ( color << 0 ) & 0x00F );
		data |= ( short )( ( stencilType << 4 ) & 0x0F0 );
		data |= ( short )( ( stencilColor << 8 ) & 0xF00 );
		
		return data;
	}

	@Override
	public DishMaterial getMaterial()
	{
		return DishMaterial.PORCELAIN;
	}
	
	@Override
	public void bindTexture( String dishType, ITextureBinder binder )
	{
		binder.bindTexture( "/mods/decorativestuff/textures/blocks/porcelainBlock.png" );
	}
	
	public static PorcelainData getFromData( int data )
	{
		byte mainCol = ( byte )( ( data >> 0 ) & 0xF );
		byte stencilType = ( byte )( ( data >> 4 ) & 0xF );
		byte stencilColor = ( byte )( ( data >> 8 ) & 0xF );
		
		return new PorcelainData( mainCol, stencilType, stencilColor );
	}
	
	public byte color;
	public byte stencilType;
	public byte stencilColor;
}
