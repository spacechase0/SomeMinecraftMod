package com.spacechase0.minecraft.decorativestuff.dish.material;

import com.spacechase0.minecraft.decorativestuff.block.DishBlock;
import com.spacechase0.minecraft.decorativestuff.client.renderer.ITextureBinder;
import com.spacechase0.minecraft.decorativestuff.dish.data.DishData;
import com.spacechase0.minecraft.decorativestuff.dish.data.PorcelainData;
import com.spacechase0.minecraft.decorativestuff.dish.StencilType;
import com.spacechase0.minecraft.decorativestuff.item.StencilItem;
import com.spacechase0.minecraft.decorativestuff.tileentity.DishTileEntity;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PorcelainMaterial extends DishMaterial
{
	@Override
	public DishData getDishData( int data )
	{
		return PorcelainData.getFromData( data );
	}
	
	@Override
	public DishData getDishData( NBTTagCompound tag )
	{
		return new PorcelainData( tag );
	}
	
	@Override
	public String getDisplayName( DishBlock block, int itemData )
	{
		PorcelainData data = PorcelainData.getFromData( itemData );
		
		String name = "";
		name += getColorString( data.color );
		name += " Porcelain ";
		name += block.getDishType().Type;
		
		if ( data.stencilType != StencilItem.NO_STENCIL )
		{
			name +=  " with ";
			name += getColorString( data.stencilColor );
			name += " ";
			name += StencilType.forId( data.stencilType ).Type;
			name +=  " Design";
		}
		
		return name;
	}

	@Override
	public void addSubItems( int id, List list )
	{
		for ( byte mainCol = 0; mainCol < 16; ++mainCol )
		{
			for ( byte stencilType = 0; stencilType < StencilItem.STENCIL_COUNT; ++stencilType )
			{
				
				if ( stencilType != StencilItem.NO_STENCIL )
				{
					for ( byte stencilColor = 0; stencilColor < 16; ++stencilColor )
					{
						PorcelainData data = new PorcelainData( mainCol, stencilType, stencilColor );
						list.add( new ItemStack( id, 1, data.getAsData() ) );
					}
				}
				else
				{
					PorcelainData data = new PorcelainData( mainCol, stencilType, ( byte ) 0 );
					list.add( new ItemStack( id, 1, data.getAsData() ) );
				}
			}
		}
	}

	@Override
	public String getType()
	{
		return "porcelain";
	}
	
	@Override
	public byte getId()
	{
		return 0;
	}
	
	private String getColorString( int col )
	{
		switch ( col )
		{
			case 0: return "Black";
			case 1: return "Red";
			case 2: return "Green";
			case 3: return "Brown";
			case 4: return "Blue";
			case 5: return "Purple";
			case 6: return "Cyan";
			case 7: return "Light Gray";
			case 8: return "Gray";
			case 9: return "Pink";
			case 10: return "Lime";
			case 11: return "Yellow";
			case 12: return "Light Blue";
			case 13: return "Magenta";
			case 14: return "Orange";
			case 15: return "White";
		}
		
		return "Unknown";
	}
}
