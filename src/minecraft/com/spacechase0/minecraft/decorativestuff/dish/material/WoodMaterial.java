package com.spacechase0.minecraft.decorativestuff.dish.material;

import com.spacechase0.minecraft.decorativestuff.block.DishBlock;
import com.spacechase0.minecraft.decorativestuff.client.renderer.ITextureBinder;
import com.spacechase0.minecraft.decorativestuff.dish.data.DishData;
import com.spacechase0.minecraft.decorativestuff.dish.data.WoodData;
import com.spacechase0.minecraft.decorativestuff.dish.StencilType;
import com.spacechase0.minecraft.decorativestuff.item.StencilItem;
import com.spacechase0.minecraft.decorativestuff.tileentity.DishTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WoodMaterial extends DishMaterial
{
	@Override
	public DishData getDishData( int data )
	{
		return WoodData.getFromData( data );
	}
	
	@Override
	public DishData getDishData( NBTTagCompound tag )
	{
		return new WoodData( tag );
	}
	
	@Override
	public String getDisplayName( DishBlock block, int itemData )
	{
		WoodData data = WoodData.getFromData( itemData );
		
		String name = "";
		name += Block.blocksList[ data.id ].getLocalizedName();
		name += " ";
		name += block.getDishType().Type;
		return name;
	}

	@Override
	public void addSubItems( int id, List list )
	{
		List< ItemStack > woodTypes = OreDictionary.getOres( "plankWood" );
		addSubItems( id, list, woodTypes );
	}

	@Override
	public String getType()
	{
		return "porcelain";
	}
	
	@Override
	public byte getId()
	{
		return 1;
	}
	
	private void addSubItems( int id, List list, List< ItemStack > items )
	{
		Iterator< ItemStack > it = items.iterator();
		while ( it.hasNext() )
		{
			ItemStack stack = it.next();
			if ( stack.getItemDamage() == OreDictionary.WILDCARD_VALUE )
			{
				List< ItemStack > stacks = new ArrayList< ItemStack >();
				Block.blocksList[ stack.itemID ].getSubBlocks( stack.itemID, null, stacks );
				addSubItems( id, list, stacks );
			}
			else
			{
				WoodData data = new WoodData( ( short ) stack.itemID, ( byte ) stack.getItemDamage() );
				list.add( new ItemStack( id, 1, data.getAsData() ) );
			}
		}
	}
}
