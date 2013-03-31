package com.spacechase0.minecraft.decorativestuff.inventory;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.item.MoldItem;
import com.spacechase0.minecraft.decorativestuff.item.StencilItem;
import com.spacechase0.minecraft.decorativestuff.tileentity.KilnTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class KilnContainer extends Container
{
	public KilnContainer( InventoryPlayer player, KilnTileEntity theKiln )
	{
		kiln = theKiln;

		//*
		addSlotToContainer( new ValidatingSlot( kiln, 0,  80, 62 ) ); // Fuel
		addSlotToContainer( new ValidatingSlot( kiln, 1,  19,  8 ) ); // Dye
		addSlotToContainer( new ValidatingSlot( kiln, 2,  19, 27 ) ); // Porcelain
		addSlotToContainer( new ValidatingSlot( kiln, 3,  38, 17 ) ); // Mold
		addSlotToContainer( new ValidatingSlot( kiln, 4,  80, 8 ) ); // Dye
		addSlotToContainer( new ValidatingSlot( kiln, 5,  80, 27 ) ); // Stencil
		addSlotToContainer( new           Slot( kiln, 6, 126, 19 ) ); // Output
		bindPlayerInventory( player );
		//*/
	}
	
	@Override
	public boolean canInteractWith( EntityPlayer player )
	{
		return kiln.isUseableByPlayer( player );
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
    		
    		if ( slot < 7 )
    		{
    			if ( !mergeItemStack( stackInSlot, 7, 7 + 36, true ) )
    			{
    				return null;
    			}
    		}
    		else if ( kiln.isStackValidForSlot( 0, stackInSlot ) ) // Fuel
    		{
    			if ( !mergeItemStack( stackInSlot, 0, 1, false ) )
    			{
    				return null;
    			}
    		}
    		else if ( kiln.isStackValidForSlot( 1, stackInSlot ) ) // Dye
    		{
    			if ( !mergeItemStack( stackInSlot, 1, 2, false ) )
    			{
        			if ( !mergeItemStack( stackInSlot, 4, 5, false ) )
        			{
        				return null;
        			}
    			}
    		}
    		else if ( stackInSlot.itemID == DecorativeStuff.rawPorcelainChunk.itemID )
    		{
    			if ( !mergeItemStack( stackInSlot, 2, 3, false ) )
    			{
    				return null;
    			}
    		}
    		else if ( stackInSlot.getItem() instanceof MoldItem )
    		{
    			if ( !mergeItemStack( stackInSlot, 3, 4, false ) )
    			{
    				return null;
    			}
    		}
    		// 4 -> 1
    		else if ( stackInSlot.getItem() instanceof StencilItem )
    		{
    			if ( !mergeItemStack( stackInSlot, 5, 6, false ) )
    			{
    				return null;
    			}
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
    	//*
		for (int iy = 0; iy < 3; iy++)
		{
			for (int ix = 0; ix < 9; ix++)
			{
				addSlotToContainer( new Slot( player, ix + iy * 9 + 9, 8 + ix * 18, 84 + iy * 18 ) );
			}
		}
		//*/
		//*
		for (int ix = 0; ix < 9; ix++)
		{
			addSlotToContainer( new Slot( player, ix, 8 + ix * 18, 142 ) );
		}
		//*/
	}
	
	private final KilnTileEntity kiln;
}
