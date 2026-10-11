package dnd.item.Armors;

import java.util.function.Function;

import dnd.core.AbilityScore;
import dnd.item.Armor;

public class PlateArmor extends Armor {
	private static final long serialVersionUID = 1L;

	public PlateArmor(){
		itemName = "Plate Armor";
	}
	

	public int getArmorClass(Function<AbilityScore, Integer> mod) {
		return 18;
	}
	
}
