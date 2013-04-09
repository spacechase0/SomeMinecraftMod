package com.spacechase0.minecraft.decorativestuff.tileentity;

import com.spacechase0.minecraft.decorativestuff.block.KilnBlock;
import com.spacechase0.minecraft.decorativestuff.dish.data.PorcelainData;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.item.MoldItem;
import com.spacechase0.minecraft.decorativestuff.item.StencilItem;
import java.util.List;
import net.minecraft.block.BlockFurnace;
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
		if ( stacks[ slot ] == null )
		{
			return null;
		}
		
		ItemStack ret = stacks[ slot ].copy();
		ret.stackSize = Math.min( amt, stacks[ slot ].stackSize );
		
		stacks[ slot ].stackSize -= ret.stackSize;
		if ( stacks[ slot ].stackSize <= 0 )
		{
			stacks[ slot ] = null;
		}
		
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
	            "dyeWhite",
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
		//System.out.println(burnTimeLeft + "/" + burnTimeTotal + " " + progressAmount + "/" + progressNeeded);
		if ( progressNeeded > 0 && burnTimeLeft <= 0 && stacks[ FUEL_SLOT ] != null )
		{
			burnTimeTotal = burnTimeLeft = TileEntityFurnace.getItemBurnTime( stacks[ FUEL_SLOT ] );
			decrStackSize( FUEL_SLOT, 1 );
			setBurnState( true );
			onInventoryChanged();
		}
		
		if ( burnTimeLeft > 0 )
		{
			--burnTimeLeft;
			if ( burnTimeLeft <= 0 )
			{
				setBurnState( false );
			}
			
			if ( progressNeeded > 0 )
			{
				++progressAmount;
			}
		}
		else
		{
			burnTimeTotal = 0;
		}
		
		if ( progressNeeded > 0 && progressAmount >= progressNeeded )
		{
			if ( stacks[ OUTPUT_SLOT ] == null )
			{
				stacks[ OUTPUT_SLOT ] = getProjectedOutput();
			}
			else
			{
				++stacks[ OUTPUT_SLOT ].stackSize;
			}
			decrStackSize( MAIN_DYE_SLOT, 1 );
			decrStackSize( PORCELAIN_SLOT, 1 );
			decrStackSize( OTHER_DYE_SLOT, 1 );
			
			stacks[ MOLD_SLOT ].setItemDamage( stacks[ MOLD_SLOT ].getItemDamage() + 1 );
			if ( stacks[ MOLD_SLOT ].getItemDamage() > stacks[ MOLD_SLOT ].getItem().getMaxDamage() )
			{
				decrStackSize( MOLD_SLOT, 1 );
			}
			
			if ( stacks[ STENCIL_SLOT ] != null )
			{
				stacks[ STENCIL_SLOT ].setItemDamage( stacks[ STENCIL_SLOT ].getItemDamage() + 1 );
				if ( stacks[ STENCIL_SLOT ].getItemDamage() > stacks[ STENCIL_SLOT ].getItem().getMaxDamage() )
				{
					decrStackSize( STENCIL_SLOT, 1 );
				}
			}
			
			progressAmount = 0;
			
			onInventoryChanged();
		}
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
        burnTimeTotal = ( int ) tag.getShort( "BurnTimeTotal" );
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
        
        tag.setShort( "BurnTimeTotal", ( short ) burnTimeTotal );
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
    
    public int getBurnTimeTotal()
    {
    	return burnTimeTotal;
    }
    
    public int getBurnTimeLeft()
    {
    	return burnTimeLeft;
    }
    
    public int getProgressNeeded()
    {
    	return progressNeeded;
    }
    
    public int getProgressAmount()
    {
    	return progressAmount;
    }
    
    private void updateProgressNeeded()
    {
    	if ( stacks[ PORCELAIN_SLOT ] != null && stacks[ MOLD_SLOT ] != null )
		{
			progressNeeded = BASE_BURN_TIME;
			if ( stacks[ STENCIL_SLOT ] != null & stacks[ OTHER_DYE_SLOT ] != null )
			{
				progressNeeded += EXTRA_BURN_TIME;
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
    	int id = ( ( MoldItem )( stacks[ MOLD_SLOT ].getItem() ) ).getOutputId( DishMaterial.PORCELAIN.getId() );
    	
    	int plateColor = getDyeColor( stacks[ MAIN_DYE_SLOT ] );
    	PorcelainData data = new PorcelainData( ( byte ) plateColor, ( byte ) 0, ( byte ) 0 );
    	if ( stacks[ STENCIL_SLOT ] != null && stacks[ OTHER_DYE_SLOT ] != null )
    	{
    		byte stencilType = ( ( StencilItem )( stacks[ STENCIL_SLOT ].getItem() ) ).getStencilType();
        	byte stencilColor = getDyeColor( stacks[ OTHER_DYE_SLOT ] );

        	data.stencilType = stencilType;
        	data.stencilColor = stencilColor;
    	}
    	
    	ItemStack result = new ItemStack( id, 1, data.getAsData() );
    	return result;
    }
    
    private byte getDyeColor( ItemStack stack )
    {
    	if ( stack == null )
    	{
    		return 15; // White
    	}
    	
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
        	
        	if ( thisColor && vanillaDye != null )
        	{
        		return ( byte ) vanillaDye.getItemDamage();
        	}
        }
        
        // Why would this happen? I have no idea.
        return 15; // White
    }
    
    private void setBurnState( boolean burning )
    {
    	KilnBlock.updateKilnBlockState( burning, this.worldObj, this.xCoord, this.yCoord, this.zCoord );
    }
    
    private ItemStack stacks[] = new ItemStack[ 7 ];
    private int burnTimeLeft;
    private int burnTimeTotal;
    private int progressAmount;
    private int progressNeeded;
    
    public static final int FUEL_SLOT = 0;
    public static final int MAIN_DYE_SLOT = 1;
    public static final int PORCELAIN_SLOT = 2;
    public static final int MOLD_SLOT = 3;
    public static final int OTHER_DYE_SLOT = 4;
    public static final int STENCIL_SLOT = 5;
    public static final int OUTPUT_SLOT = 6;
    
    public static final int BASE_BURN_TIME = 3600;
    public static final int EXTRA_BURN_TIME = 2400;
}
