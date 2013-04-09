package com.spacechase0.minecraft.decorativestuff.tileentity;

import com.spacechase0.minecraft.decorativestuff.dish.*;
import com.spacechase0.minecraft.decorativestuff.dish.data.*;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import com.spacechase0.minecraft.decorativestuff.dish.type.DishType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class DishTileEntity extends TileEntity implements IInventory
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
	public boolean isStackValidForSlot( int i, ItemStack item )
	{
		return type.isStackValid( item );
	}
	
	@Override
	public String getInvName()
	{
		return type.Type;
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
    public void readFromNBT( NBTTagCompound tag )
    {
		super.readFromNBT( tag );
		stack = ItemStack.loadItemStackFromNBT( ( NBTTagCompound ) tag.getTag( "Food" ) );

		NBTTagCompound nbtData = ( NBTTagCompound ) tag.getTag( "Data" );
		if ( nbtData != null )
		{
			type = DishType.forId( nbtData.getByte( "Type" ) );
			
			byte mat = nbtData.getByte( "Material" );
			setDishData( DishMaterial.forId( mat ).getDishData( nbtData ) );
		}
		else
		{
			// For compatibility with 0.1
			type = DishType.PLATE;
			
			byte color = ( byte ) tag.getShort( "Color" );
			byte stencilType = ( byte ) tag.getShort( "StencilType" );
			byte stencilColor = ( byte ) tag.getShort( "StencilColor" );
			
			data = new PorcelainData( color, stencilType, stencilColor );
		}
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
		
		tag.setByte( "Type", ( byte ) type.id );
		
		NBTTagCompound nbtData = new NBTTagCompound();
		nbtData.setByte( "Material", data.getMaterial().getId() );
		data.writeToNbt( nbtData );
		tag.setTag( "Data", nbtData );
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
    
    public void setDishType( DishType theType )
    {
    	type = theType;
    }
    
    public DishType getDishType()
    {
    	return type;
    }
    
    public void setDishData( DishData theData )
    {
    	data = theData;
    }

    public void setAsPorcelain( byte color, byte stencilType, byte stencilColor )
    {
    	data = new PorcelainData( color, stencilType, stencilColor );
    }

    public void setAsWood( short id, byte theData )
    {
    	data = new WoodData( id, theData );
    }
    
    public DishData getDishData()
    {
    	return data;
    }
    
    public PorcelainData getPorcelainData()
    {
    	return ( ( data instanceof PorcelainData ) ? ( ( PorcelainData ) data ) : null );
    }
    
    public WoodData getWoodData()
    {
    	return ( ( data instanceof WoodData ) ? ( ( WoodData ) data ) : null );
    }
	
	private ItemStack stack;
	private DishType type;
	private DishData data;
}
