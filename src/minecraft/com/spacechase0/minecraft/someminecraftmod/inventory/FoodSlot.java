package com.spacechase0.minecraft.someminecraftmod.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class FoodSlot extends Slot
{
    public FoodSlot( IInventory inventory, int index, int x, int y )
    {
    	super( inventory, index, x, y );
    }
    
    @Override
    public boolean isItemValid( ItemStack stack )
    {
        return ( stack.getItem() instanceof ItemFood );
    }
}
