package dnd.item;

import java.util.function.Function;

public class PlateArmor extends Armor {

	public PlateArmor(){
		itemName = "Plate Armor";
	}
	
	@Override
	public Function<Integer, Integer> getArmorCalc() {
		return (mod) -> {return 18;}; 
	}

}
