package dnd.item;

import java.util.function.Function;
public class HideArmor extends Armor {
	HideArmor(){
		itemName = "Hide Armor";
	}
	
	public Function<Integer,Integer> getArmorCalc(){
		return (mod) -> {
			if(mod > 2) mod=2;
			return 12+mod;
		};
	}
}
