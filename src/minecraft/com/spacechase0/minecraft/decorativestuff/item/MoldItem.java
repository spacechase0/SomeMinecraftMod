package com.spacechase0.minecraft.decorativestuff.item;

import net.minecraft.item.ItemStack;

public class MoldItem extends SimpleItem
{
	public MoldItem( int id, String name, int[] theOutput )
	{
		super( id, name + "Mold" );
		output = theOutput;
		
		setMaxStackSize( 1 );
		setMaxDamage( 4 );
		
		setContainerItem( this );
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid( ItemStack stack )
	{
		System.out.println("checking leave grid");
		return false;
	}
	
    @Override
    public ItemStack getContainerItemStack( ItemStack stack )
    {
    	System.out.println("getting container");
        stack.setItemDamage( stack.getItemDamage() + 1 );
        return stack;
    }
	
	public int getOutputId( byte matType )
	{
		return output[ matType ];
	}
	
	private final int output[];
}
