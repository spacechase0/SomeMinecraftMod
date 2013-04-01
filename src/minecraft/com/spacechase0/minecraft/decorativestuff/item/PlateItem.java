package com.spacechase0.minecraft.decorativestuff.item;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.tileentity.PlateTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.*;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class PlateItem extends Item//Reed // I have no idea. Plus, it's called SUGARCANE!
{
	public PlateItem( int id, Block theBlock, String theType )
	{
		super( id );
		block = theBlock;
		
        setHasSubtypes( true );
        setMaxDamage( 0 );
		
		type = theType;
		setUnlocalizedName( type + "PlateItem" );
		setCreativeTab( DecorativeStuff.decorativeTab );
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
		// From ItemReed
        int i1 = world.getBlockId(x, y, z);

        if (i1 == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
        {
            par7 = 1;
        }
        else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID)
        {
            if (par7 == 0)
            {
                --y;
            }

            if (par7 == 1)
            {
                ++y;
            }

            if (par7 == 2)
            {
                --z;
            }

            if (par7 == 3)
            {
                ++z;
            }

            if (par7 == 4)
            {
                --x;
            }

            if (par7 == 5)
            {
                ++x;
            }
        }

        if (!player.canPlayerEdit(x, y, z, par7, stack))
        {
            return false;
        }
        else if (stack.stackSize == 0)
        {
            return false;
        }
        else
        {
            if (world.canPlaceEntityOnSide( block.blockID, x, y, z, false, par7, (Entity)null, stack))
            {
                Block block = Block.blocksList[this.block.blockID];
                int j1 = block.onBlockPlaced(world, x, y, z, par7, par8, par9, par10, 0);

                if (world.setBlock(x, y, z, this.block.blockID, j1, 3))
                {
                    if (world.getBlockId(x, y, z) == this.block.blockID)
                    {
                        Block.blocksList[this.block.blockID].onBlockPlacedBy(world, x, y, z, player, stack);
                        Block.blocksList[this.block.blockID].onPostBlockPlaced(world, x, y, z, j1);
                        
                        // Mine starts here
                		int data = stack.getItemDamage();
                		int mainCol = ( data >> 0 ) & 0xF;
                		int stencilType = ( data >> 4 ) & 0xF;
                		int stencilColor = ( data >> 8 ) & 0xF;
                		System.out.println(mainCol + " " + stencilType + " " + stencilColor);
                        PlateTileEntity plate = ( PlateTileEntity ) world.getBlockTileEntity( x, y, z );
                        plate.setColor( mainCol );
                        plate.setStencil( stencilType, stencilColor );
                        // And in here
                    }

                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                    --stack.stackSize;
                }
            }

            return true;
        }
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
	public final Block block;
}
