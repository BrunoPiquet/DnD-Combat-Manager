package dnd.item;

import java.util.function.Function;

import dnd.core.AbilityScore;

public class SplintArmor extends Armor{	
	private static final long serialVersionUID = 1L;

	public int getArmorClass(Function<AbilityScore, Integer> mod) {
		// TODO Auto-generated method stub
		return 17;
	}
	
}
