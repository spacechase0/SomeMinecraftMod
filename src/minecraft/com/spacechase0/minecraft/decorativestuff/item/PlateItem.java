package com.spacechase0.minecraft.decorativestuff.item;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.*;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class PlateItem extends ItemReed // I have no idea. Plus, it's called SUGARCANE!
{
	public PlateItem( int id, Block block, String theType )
	{
		super( id, block );
		
        setHasSubtypes( true );
        setMaxDamage( 0 );
		
		type = theType;
		setUnlocalizedName( type + "PlateItem" );
		setCreativeTab( DecorativeStuff.decorativeTab );
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void updateIcons( IconRegister register )
    {
        iconIndex = register.registerIcon( "decorativeStuff:" + type + "plate" );
    }
	
	@Override
    public Icon getIconFromDamage( int dmg )
    {
		return iconIndex;
    }
	
	@Override
    public String getItemDisplayName( ItemStack stack )
	{
		int data = stack.getItemDamage();
		int mainCol = ( data >> 0 ) & 0xF;
		int stencilType = ( data >> 4 ) & 0xF;
		int stencilColor = ( data >> 8 ) & 0xF;
		
		String name = "";
		
		switch ( mainCol )
		{
			case 0: name += "Black"; break;
			case 1: name += "Red"; break;
			case 2: name += "Green"; break;
			case 3: name += "Brown"; break;
			case 4: name += "Blue"; break;
			case 5: name += "Purple"; break;
			case 6: name += "Cyan"; break;
			case 7: name += "Light Gray"; break;
			case 8: name += "Gray"; break;
			case 9: name += "Pink"; break;
			case 10: name += "Lime"; break;
			case 11: name += "Yellow"; break;
			case 12: name += "Light Blue"; break;
			case 13: name += "Magenta"; break;
			case 14: name += "Orange"; break;
			case 15: name += "White"; break;
		}
		
		name += " Porcelain Plate";
		
		if ( stencilType != StencilItem.NO_STENCIL )
		{
			name += " with ";
			switch ( stencilColor )
			{
				case 0: name += "Black"; break;
				case 1: name += "Red"; break;
				case 2: name += "Green"; break;
				case 3: name += "Brown"; break;
				case 4: name += "Blue"; break;
				case 5: name += "Purple"; break;
				case 6: name += "Cyan"; break;
				case 7: name += "Light Gray"; break;
				case 8: name += "Gray"; break;
				case 9: name += "Pink"; break;
				case 10: name += "Lime"; break;
				case 11: name += "Yellow"; break;
				case 12: name += "Light Blue"; break;
				case 13: name += "Magenta"; break;
				case 14: name += "Orange"; break;
				case 15: name += "White"; break;
			}
			name += " ";
			switch ( stencilType )
			{
				case StencilItem.STRAIGHT_STENCIL: name += "Straight"; break;
				case StencilItem.ABSTRACT_STENCIL: name += "Abstract"; break;
				case StencilItem.BUBBLE_STENCIL: name += "Bubble"; break;
			}
			name += " Design";
		}
		
		return name;
	}
	
	@Override
    public void getSubItems( int id, CreativeTabs tabs, List list )
    {
		for ( int mainCol = 0; mainCol < 16; ++mainCol )
		{
			for ( int stencilType = 0; stencilType < StencilItem.STENCIL_COUNT; ++stencilType )
			{
				
				if ( stencilType != StencilItem.NO_STENCIL )
				{
					for ( int stencilColor = 0; stencilColor < 16; ++stencilColor )
					{
						int data = 0;
						data |= ( mainCol << 0 ) & 0x00F;
						data |= ( stencilType << 4 ) & 0x0F0;
						data |= ( stencilColor << 8 ) & 0xF00;
						
						list.add( new ItemStack( id, 1, data ) );
					}
				}
				else
				{
					int data = 0;
					data |= ( mainCol << 0 ) & 0x00F;
					list.add( new ItemStack( id, 1, data ) );
				}
			}
		}
    }
	
	public final String type;
}
