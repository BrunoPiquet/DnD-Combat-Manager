package dnd.item;

import java.util.function.Function;

import dnd.core.AbilityScore;
public class HideArmor extends Armor {
 static final long serialVersionUID = 1L;

	HideArmor(){
		itemName = "Hide Armor";
	}
	
	public int getArmorClass(Function<AbilityScore, Integer> mod){
		int m = mod.apply(AbilityScore.DEX);
		if(m > 2) {m=2;};
		return 12+m;

	}
}
