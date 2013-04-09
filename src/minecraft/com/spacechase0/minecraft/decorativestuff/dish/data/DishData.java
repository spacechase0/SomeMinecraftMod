package com.spacechase0.minecraft.decorativestuff.dish.data;

import com.spacechase0.minecraft.decorativestuff.client.renderer.ITextureBinder;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import net.minecraft.nbt.NBTTagCompound;

public interface DishData
{
	public void writeToNbt( NBTTagCompound tag );
	public short getAsData();
	public DishMaterial getMaterial();
	public void bindTexture( String dishType, ITextureBinder binder );
}
