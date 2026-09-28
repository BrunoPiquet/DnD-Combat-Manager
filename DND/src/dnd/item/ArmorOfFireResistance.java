package dnd.item;
import dnd.Character;
import dnd.core.DamageType;

import java.util.function.Function;

public class ArmorOfFireResistance extends Armor {
	
	public ArmorOfFireResistance(){
		itemName = "Armor of Fire Resistance";
	}
	
	public void onEquip(Character c) {
		c.setResistance(DamageType.Fire, 0.5f);
	}
	
	public void onUnEquip(Character c) {
		c.setResistance(DamageType.Fire, 1f);
	}

	public Function<Integer, Integer> getArmorCalc() {
		return (mod) -> {return 18;};
	}

}
