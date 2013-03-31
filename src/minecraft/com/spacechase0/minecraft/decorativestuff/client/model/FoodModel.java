package com.spacechase0.minecraft.decorativestuff.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;

public abstract class FoodModel extends ModelBase
{
	public abstract String getTextureName();
	public abstract void renderFood( ItemStack food );
}
