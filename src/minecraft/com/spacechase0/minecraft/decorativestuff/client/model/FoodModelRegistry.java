package com.spacechase0.minecraft.decorativestuff.client.model;

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
			registerModel( id, new GenericItemModel( /*1.f*/ ) );
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
//		360 melon
		registerModel( Item.chickenCooked.itemID, new ChickenModel( true ) );
		registerModel( Item.chickenRaw.itemID, new ChickenModel( false ) );
//		392 potatos
//		393 baked potatos
//		394 poisonous potato
//		396 golden carrot
		registerModel( Item.pumpkinPie.itemID, new PumpkinPieModel() );
	}
}
