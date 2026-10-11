package dnd.item;

import dnd.item.Armors.*;
import dnd.item.MeleeWeapons.*;
import dnd.item.RangedWeapons.*;


public interface ItemFactory {
	
	public static Item createItem(String itemName) {
		Item item = null;
		
		switch(itemName) {
			//Melee weapons
			case "Longsword" :{item = new Longsword(); break;}
			case "Greatsword" :{item = new Greatsword(); break;}
			case "Everburn Blade" :{item = new EverburnBlade(); break;}

			//Ranged
			case "Longbow" :{item = new Longbow(); break;}
			case "Darkfire Shortbow" :{item = new DarkfireShortbow(); break;}
			
			//Armor
			case "Armor Of Fire Resistance" :{item = new ArmorOfFireResistance(); break;}
			case "Hide Armor" :{item = new HideArmor(); break;}
			case "Plate Armor" :{item = new PlateArmor(); break;}
			case "Splint Armor" :{item = new SplintArmor(); break;}
			
			//Comsumables
		}
		
		
		return item;
	}

}
