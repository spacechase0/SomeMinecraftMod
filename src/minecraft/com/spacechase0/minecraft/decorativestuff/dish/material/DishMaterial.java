package com.spacechase0.minecraft.decorativestuff.dish.material;

import com.spacechase0.minecraft.decorativestuff.block.DishBlock;
import com.spacechase0.minecraft.decorativestuff.client.renderer.ITextureBinder;
import com.spacechase0.minecraft.decorativestuff.dish.data.DishData;
import com.spacechase0.minecraft.decorativestuff.tileentity.DishTileEntity;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;

public abstract class DishMaterial
{
	public static final DishMaterial PORCELAIN = new PorcelainMaterial();
	public static final DishMaterial WOOD = new WoodMaterial();
	
	public abstract DishData getDishData( int itemData );
	public abstract DishData getDishData( NBTTagCompound tag );
	public abstract String getDisplayName( DishBlock block, int data );
	public abstract void addSubItems( int id, List list );
	public abstract String getType();
	public abstract byte getId();
	
	public static DishMaterial forId( byte id )
	{
		switch ( id )
		{
			case 0: return PORCELAIN;
			case 1: return WOOD;
		}
		
		return null;
	}
}
