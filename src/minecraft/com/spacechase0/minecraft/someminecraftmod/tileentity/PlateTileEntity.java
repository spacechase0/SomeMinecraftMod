package com.spacechase0.minecraft.someminecraftmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PlateTileEntity extends TileEntity implements IInventory
{
	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot( int slot )
	{
		return stack;
	}

	@Override
	public ItemStack decrStackSize( int slot, int amt )
	{
		ItemStack ret = stack.copy();
		ret.stackSize = Math.min( amt, stack.stackSize );
		
		stack.stackSize -= ret.stackSize;
		
		return ret;
	}

	@Override
	public ItemStack getStackInSlotOnClosing( int slot )
	{
		return null;
	}

	@Override
	public void setInventorySlotContents( int slot, ItemStack item )
	{
		if ( isStackValidForSlot( slot, item ) )
		{
			stack = item;
		}
	}

	@Override
	public String getInvName()
	{
		return "Plate";
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return true; // Laziness :P
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer( EntityPlayer player )
	{
		// Copied from chest code :P
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public boolean isStackValidForSlot( int i, ItemStack item )
	{
		return ( item.getItem() instanceof ItemFood );
	}
	
	private ItemStack stack;
	private World world;
}
