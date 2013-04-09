package com.spacechase0.minecraft.decorativestuff;

import com.spacechase0.minecraft.decorativestuff.dish.data.WoodData;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import com.spacechase0.minecraft.decorativestuff.dish.material.WoodMaterial;
import com.spacechase0.minecraft.decorativestuff.item.MoldItem;
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
		ItemStack axe = null;
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
			
			if ( stack.getItem() instanceof ItemAxe && axe == null )
			{
				axe = stack;
			}
			else if ( stack.getItem() instanceof MoldItem && mold == null )
			{
				mold = stack;
			}
			else if ( containsMatch( ( ItemStack[] ) OreDictionary.getOres( "plankWood" ).toArray(), stack ) )
			{
				if ( wood == null || wood.isItemEqual( stack ) )
				{
					++woodCount;
					wood = stack;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		
		return ( axe != null && mold != null && wood != null && ( woodCount == 3 ) );
	}

	@Override
	public ItemStack getCraftingResult( InventoryCrafting inv )
	{
		ItemStack axe = null;
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
			
			if ( stack.getItem() instanceof ItemAxe && axe == null )
			{
				axe = stack;
			}
			else if ( stack.getItem() instanceof MoldItem && mold == null )
			{
				mold = stack;
			}
			else if ( containsMatch( ( ItemStack[] ) OreDictionary.getOres( "plankWood" ).toArray(), stack ) )
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
		
		axe.setItemDamage( axe.getItemDamage() + 1 );
		if ( axe.getItemDamage() > axe.getMaxDamage() )
		{
			axe.stackSize = 0;
		}
		
		mold.setItemDamage( mold.getItemDamage() + 1 );
		if ( mold.getItemDamage() > mold.getMaxDamage() )
		{
			mold.stackSize = 0;
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
	
	// Copied from OreDictionary
	// I wonder why it wasn't public...
    private static boolean containsMatch( ItemStack[] inputs, ItemStack... targets )
    {
        for ( ItemStack input : inputs )
        {
            for ( ItemStack target : targets )
            {
                if ( OreDictionary.itemMatches( target, input, true ) )
                {
                    return true;
                }
            }
        }
        return false;
    }
}
