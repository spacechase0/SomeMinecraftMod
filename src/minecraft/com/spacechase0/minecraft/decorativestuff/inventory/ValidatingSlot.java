package com.spacechase0.minecraft.decorativestuff.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ValidatingSlot extends Slot
{
    public ValidatingSlot( IInventory inventory, int index, int x, int y )
    {
    	super( inventory, index, x, y );
    }
    
    @Override
    public boolean isItemValid( ItemStack stack )
    {
        return inventory.isStackValidForSlot( getSlotIndex(), stack );
    }
}
