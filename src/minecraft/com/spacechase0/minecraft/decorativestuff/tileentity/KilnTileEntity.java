package com.spacechase0.minecraft.decorativestuff.tileentity;

import java.util.List;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.item.MoldItem;
import com.spacechase0.minecraft.decorativestuff.item.StencilItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.oredict.OreDictionary;

public class KilnTileEntity extends TileEntity implements IInventory {

	@Override
	public int getSizeInventory()
	{
		return 7;
	}

	@Override
	public ItemStack getStackInSlot( int slot )
	{
		return stacks[ slot ];
	}

	@Override
	public ItemStack decrStackSize( int slot, int amt )
	{
		ItemStack ret = stacks[ slot ].copy();
		ret.stackSize = Math.min( amt, stacks[ slot ].stackSize );
		
		stacks[ slot ].stackSize -= ret.stackSize;
		
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
			stacks[ slot ] = item;
		}
	}

	@Override
	public String getInvName()
	{
		return "Kiln";
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
	public boolean isStackValidForSlot( int slot, ItemStack item )
	{
		if ( item == null )
		{
			return true;
		}
		
		if ( slot == FUEL_SLOT )
		{
			return ( TileEntityFurnace.getItemBurnTime( item ) > 0 );
		}
		else if ( slot == MAIN_DYE_SLOT || slot == OTHER_DYE_SLOT )
		{
	        String[] dyes =
	        {
	            "dyeBlack",
	            "dyeRed",
	            "dyeGreen",
	            "dyeBrown",
	            "dyeBlue",
	            "dyePurple",
	            "dyeCyan",
	            "dyeLightGray",
	            "dyeGray",
	            "dyePink",
	            "dyeLime",
	            "dyeYellow",
	            "dyeLightBlue",
	            "dyeMagenta",
	            "dyeOrange",
	            "dyeWhite"
	        };
	        
	        for ( String dye : dyes )
	        {
	        	List< ItemStack > matches = OreDictionary.getOres( dye );
	        	for ( ItemStack dyeItem : matches )
	        	{
	        		if ( item.itemID == dyeItem.itemID && item.getItemDamage() == dyeItem.getItemDamage() )
	        		{
	        			return true;
	        		}
	        	}
	        }
	        
	        return false;
		}
		else if ( slot == PORCELAIN_SLOT )
		{
			return ( item.itemID == DecorativeStuff.rawPorcelainChunk.itemID );
		}
		else if ( slot == MOLD_SLOT )
		{
			return ( item.getItem() instanceof MoldItem );
		}
		else if ( slot == STENCIL_SLOT )
		{
			return ( item.getItem() instanceof StencilItem );
		}
		else if ( slot == OUTPUT_SLOT )
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public void updateEntity()
	{
		updateProgressNeeded();
		
		if ( burnTimeLeft > 0 )
		{
			--burnTimeLeft;
			if ( progressNeeded > 0 )
			{
				++progressAmount;
			}
		}
		
		if ( progressNeeded >= 0 ) stacks[ OUTPUT_SLOT ] = getProjectedOutput();
	}
	
    @Override
    public void readFromNBT( NBTTagCompound tag )
    {
		super.readFromNBT( tag );
		
        NBTTagList inv = tag.getTagList( "Items" );
        for ( int i = 0; i < inv.tagCount(); ++i )
        {
            NBTTagCompound compound = ( NBTTagCompound ) inv.tagAt( i );
            byte slot = compound.getByte( "Slot" );

            if ( slot >= 0 && slot < stacks.length )
            {
                stacks[ slot ] = ItemStack.loadItemStackFromNBT( compound );
            }
        }
        burnTimeLeft = ( int ) tag.getShort( "BurnTimeLeft" );
        progressAmount = ( int ) tag.getShort( "ProgressAmount" );
    }

    @Override
    public void writeToNBT( NBTTagCompound tag )
    {
		super.writeToNBT( tag );

        NBTTagList inv = new NBTTagList();
        for ( int i = 0; i < stacks.length; ++i )
        {
            if ( stacks[ i ] != null )
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte( "Slot", ( byte ) i );
                stacks[ i ].writeToNBT( compound );
                inv.appendTag( compound );
            }
        }
        tag.setTag( "Items", inv );
        
        tag.setShort( "BurnTimeLeft", ( short ) burnTimeLeft );
        tag.setShort( "ProgressAmount", ( short ) progressAmount );
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
    
    private void updateProgressNeeded()
    {
    	if ( stacks[ PORCELAIN_SLOT ] != null && stacks[ MOLD_SLOT ] != null )
		{
			progressNeeded = 360;
			if ( stacks[ STENCIL_SLOT ] != null & stacks[ OTHER_DYE_SLOT ] != null )
			{
				progressNeeded += 240;
			}
			
			if ( stacks[ OUTPUT_SLOT ] != null )
			{
				ItemStack output = stacks[ OUTPUT_SLOT ];
				ItemStack result = getProjectedOutput();
				
				if ( output.itemID != result.itemID || output.getItemDamage() != result.getItemDamage() || output.getMaxStackSize() == output.stackSize )
				{
					progressNeeded = -1;
				}
			}
		}
		else
		{
			progressNeeded = -1;
		}
    }
    
    private ItemStack getProjectedOutput()
    {
    	int id = ( ( MoldItem )( stacks[ MOLD_SLOT ].getItem() ) ).getOutputId();
    	int plateColor = getDyeColor( stacks[ MAIN_DYE_SLOT ] );
    	int data = ( plateColor >> 0 ) & 0x00F;
    	
    	if ( stacks[ STENCIL_SLOT ] != null && stacks[ OTHER_DYE_SLOT ] != null )
    	{
    		int stencilType = ( ( StencilItem )( stacks[ STENCIL_SLOT ].getItem() ) ).getStencilType();
        	int stencilColor = getDyeColor( stacks[ OTHER_DYE_SLOT ] );
        	
        	data |= ( stencilType >> 4 ) & 0x0F0;
        	data |= ( stencilColor >> 8 ) & 0xF00;
    	}
    	
    	ItemStack result = new ItemStack( id, 1, data );
    	return result;
    }
    
    private int getDyeColor( ItemStack stack )
    {
        String[] dyes =
        {
            "dyeBlack",
            "dyeRed",
            "dyeGreen",
            "dyeBrown",
            "dyeBlue",
            "dyePurple",
            "dyeCyan",
            "dyeLightGray",
            "dyeGray",
            "dyePink",
            "dyeLime",
            "dyeYellow",
            "dyeLightBlue",
            "dyeMagenta",
            "dyeOrange",
            "dyeWhite"
        };
        
        for ( String dye : dyes )
        {
        	List< ItemStack > matches = OreDictionary.getOres( dye );
        	
        	boolean thisColor = false;
        	ItemStack vanillaDye = null;
        	for ( ItemStack dyeItem : matches )
        	{
        		if ( dyeItem.itemID == Item.dyePowder.itemID )
        		{
        			vanillaDye = dyeItem;
        		}
        		
        		if ( stack.itemID == dyeItem.itemID && stack.getItemDamage() == dyeItem.getItemDamage() )
        		{
        			thisColor = true;
        		}
        	}
        	
        	if ( vanillaDye != null )
        	{
        		return vanillaDye.getItemDamage();
        	}
        }
        
        // Why would this happen? I have no idea.
        return 15; // White
    }
    
    private ItemStack stacks[] = new ItemStack[ 7 ];
    private int burnTimeLeft;
    private int progressAmount;
    private int progressNeeded;
    
    public static final int FUEL_SLOT = 0;
    public static final int MAIN_DYE_SLOT = 1;
    public static final int PORCELAIN_SLOT = 2;
    public static final int MOLD_SLOT = 3;
    public static final int OTHER_DYE_SLOT = 4;
    public static final int STENCIL_SLOT = 5;
    public static final int OUTPUT_SLOT = 6;
}
