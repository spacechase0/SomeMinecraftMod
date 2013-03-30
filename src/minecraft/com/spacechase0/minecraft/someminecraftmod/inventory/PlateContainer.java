package com.spacechase0.minecraft.someminecraftmod.inventory;

import com.spacechase0.minecraft.someminecraftmod.tileentity.PlateTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class PlateContainer extends Container
{
	public PlateContainer( InventoryPlayer player, PlateTileEntity thePlate )
	{
		plate = thePlate;
		
		addSlotToContainer( new FoodSlot( plate, 0, 80, 35 ) );
		bindPlayerInventory( player );
	}
	
	@Override
	public boolean canInteractWith( EntityPlayer player )
	{
		return plate.isUseableByPlayer( player );
	}
	
    @Override
    public ItemStack transferStackInSlot( EntityPlayer player, int slot )
    {
    	ItemStack stack = null;
    	Slot slotObj = ( Slot ) inventorySlots.get( slot );
    	
    	if ( slotObj != null && slotObj.getHasStack() )
    	{
    		ItemStack stackInSlot = slotObj.getStack();
    		stack = stackInSlot.copy();
    		
    		if ( slot == 0 )
    		{
    			if ( !mergeItemStack( stackInSlot, 2, 37, true ) )
    			{
    				return null;
    			}
    		}
    		else if ( !mergeItemStack( stackInSlot, 0, 0, false ) )
    		{
    			return null;
    		}
    		
    		if ( stackInSlot.stackSize == 0 )
    		{
    			slotObj.putStack( null );
    		}
    		else
    		{
    			slotObj.onSlotChanged();
    		}
    		
    		if ( stackInSlot.stackSize == stack.stackSize )
    		{
    			return null;
    		}
    		
    		slotObj.onPickupFromSlot( player,  stackInSlot );
    	}
    	
    	return stack;
    }

    protected void bindPlayerInventory( InventoryPlayer player )
    {
		for (int iy = 0; iy < 3; iy++)
		{
			for (int ix = 0; ix < 9; ix++)
			{
				addSlotToContainer( new Slot( player, ix + iy * 9 + 9, 8 + ix * 18, 84 + iy * 18 ) );
			}
		}
		
		for (int ix = 0; ix < 9; ix++)
		{
			addSlotToContainer( new Slot( player, ix, 8 + ix * 18, 142 ) );
		}
	}
	
	private final PlateTileEntity plate;
}
