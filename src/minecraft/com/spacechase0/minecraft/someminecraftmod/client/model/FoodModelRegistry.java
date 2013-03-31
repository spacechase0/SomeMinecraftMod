package com.spacechase0.minecraft.someminecraftmod.client.model;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;

public class FoodModelRegistry
{
	public static void registerModel( int id, FoodModel model )
	{
		models.put( id, model );
	}
	
	public static FoodModel getModelForId( int id )
	{
		FoodModel model = models.get( id );
		if ( model == null )
		{
			registerModel( id, new GenericItemModel() );
			return getModelForId( id );
		}
		
		return model;
	}
	
	private static Map< Integer, FoodModel > models;
	
	static
	{
		models = new HashMap< Integer, FoodModel >();
		
		registerModel( Item.appleRed.itemID, new AppleModel( false ) );
		registerModel( Item.bowlSoup.itemID, new MushroomSoupModel() );
		registerModel( Item.bread.itemID, new BreadModel() );
		registerModel( Item.appleGold.itemID, new AppleModel( true ) );
//		357 cookie
//		360 melon
//		363 raw beef
//		364 cooked beef
//		365 raw chicken
//		366 cooked chicken
//		367 rotten flesh
//		375 spider eyes
//		391 carrot
//		392 potatos
//		393 baked potatos
//		394 poisonous potato
//		396 golden carrot
//		400 pumpkin pie
	}
}
