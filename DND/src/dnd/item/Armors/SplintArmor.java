package dnd.item.Armors;

import java.util.function.Function;

import dnd.core.AbilityScore;
import dnd.item.Armor;

public class SplintArmor extends Armor{	
	private static final long serialVersionUID = 1L;

	public int getArmorClass(Function<AbilityScore, Integer> mod) {
		// TODO Auto-generated method stub
		return 17;
	}
	
}
