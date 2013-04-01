package com.spacechase0.minecraft.decorativestuff.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
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
		if ( stack == null )
		{
			return null;
		}
		
		ItemStack ret = stack.copy();
		ret.stackSize = Math.min( amt, stack.stackSize );
		
		stack.stackSize -= ret.stackSize;
		
		if ( stack.stackSize <= 0 )
		{
			stack = null;
		}
		
		onInventoryChanged();
		worldObj.markBlockForUpdate( xCoord, yCoord, zCoord );
		
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
		return ( ( item == null ) || ( item.getItem() instanceof ItemFood ) );
	}
	
    @Override
    public void readFromNBT( NBTTagCompound tag )
    {
		super.readFromNBT( tag );
		stack = ItemStack.loadItemStackFromNBT( ( NBTTagCompound ) tag.getTag( "Food" ) );
		color = ( int ) tag.getShort( "Color" );
		stencilType = ( int ) tag.getShort( "StencilType" );
		stencilColor = ( int ) tag.getShort( "StencilColor" );
    }

    @Override
    public void writeToNBT( NBTTagCompound tag )
    {
		super.writeToNBT( tag );
		
		NBTTagCompound item = new NBTTagCompound();
		if ( stack != null )
		{
			stack.writeToNBT( item );
		}
		tag.setTag( "Food", item );
		
		tag.setShort( "Color", ( short ) color );
		tag.setShort( "StencilType", ( short ) stencilType );
		tag.setShort( "StencilColor", ( short ) stencilColor );
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, -1, nbttagcompound);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
    	if ( worldObj.isRemote )
    	{
    		readFromNBT( pkt.customParam1 );
    	}
    }
    
    public void setColor( int theColor )
    {
    	color = theColor;
    }
    
    public void setStencil( int type, int color )
    {
    	stencilType = type;
    	stencilColor = color;
    }
    
    public int getColor()
    {
    	return color;
    }
    
    public int getStencilType()
    {
    	return stencilType;
    }
    
    public int getStencilColor()
    {
    	return stencilColor;
    }
	
	private ItemStack stack;
	private int color;
	private int stencilType;
	private int stencilColor;
}
