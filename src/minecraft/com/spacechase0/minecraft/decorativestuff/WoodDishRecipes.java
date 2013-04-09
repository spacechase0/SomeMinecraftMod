package com.spacechase0.minecraft.decorativestuff;

import com.spacechase0.minecraft.decorativestuff.dish.data.WoodData;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import com.spacechase0.minecraft.decorativestuff.dish.material.WoodMaterial;
import com.spacechase0.minecraft.decorativestuff.item.MoldItem;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class WoodDishRecipes implements IRecipe
{
	@Override
	public boolean matches( InventoryCrafting inv, World world )
	{
		//ItemStack axe = null;
		ItemStack mold = null;
		ItemStack wood = null;
		int woodCount = 0;
		for ( int i = 0; i < inv.getSizeInventory(); ++i )
		{
			ItemStack stack = inv.getStackInSlot( i );
			if ( stack == null )
			{
				continue;
			}
			
			/*
			if ( stack.getItem() instanceof ItemAxe && axe == null )
			{
				axe = stack;
			}
			//*/
			else if ( stack.getItem() instanceof MoldItem && mold == null )
			{
				mold = stack;
			}
			else if ( isWoodenPlanks( stack ) )
			{
				if ( wood == null || wood.isItemEqual( stack ) )
				{
					++woodCount;
					wood = stack;
				}
				else
				{
					System.out.println(woodCount);
					return false;
				}
			}
			else
			{
				System.out.println("bad item " + stack.toString() );
				return false;
			}
		}
		
		return ( /*axe != null &&*/ mold != null && wood != null && ( woodCount == 3 ) );
	}

	@Override
	public ItemStack getCraftingResult( InventoryCrafting inv )
	{
		//ItemStack axe = null;
		ItemStack mold = null;
		ItemStack wood = null;
		int woodCount = 0;
		for ( int i = 0; i < inv.getSizeInventory(); ++i )
		{
			ItemStack stack = inv.getStackInSlot( i );
			if ( stack == null )
			{
				continue;
			}
			
			/*
			if ( stack.getItem() instanceof ItemAxe && axe == null )
			{
				axe = stack;
			}
			//*/
			else if ( stack.getItem() instanceof MoldItem && mold == null )
			{
				mold = stack;
			}
			else if ( isWoodenPlanks( stack ) )
			{
				if ( wood == null || wood.isItemEqual( stack ) )
				{
					++woodCount;
					wood = stack;
				}
				else
				{
					return null;
				}
			}
			else
			{
				return null;
			}
		}
		
		int data = 0;
		data |= ( wood.itemID << 0 ) & 0x0FFF;
		data |= ( wood.getItemDamage() << 12 ) & 0xF000;
		
		MoldItem moldItem = ( MoldItem ) mold.getItem();
		
		return new ItemStack( moldItem.getOutputId( DishMaterial.WOOD.getId() ), 1, new WoodData( ( short ) wood.itemID, ( byte ) wood.getItemDamage() ).getAsData() );
	}

	@Override
	public int getRecipeSize()
	{
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return null;
	}
	
    private static boolean isWoodenPlanks( ItemStack target )
    {
    	ArrayList< ItemStack > stacks = OreDictionary.getOres( "plankWood" );
    	
    	Iterator< ItemStack > it = stacks.iterator();
    	while ( it.hasNext() )
    	{
    		ItemStack stack = it.next();
    		
    		if ( stack.getItemDamage() == OreDictionary.WILDCARD_VALUE )
    		{
    			return ( stack.itemID == target.itemID );
    		}
    		else
    		{
    			return ( stack.itemID == target.itemID && stack.getItemDamage() == target.getItemDamage() );
    		}
    	}
    	
    	return false;
    }
}
