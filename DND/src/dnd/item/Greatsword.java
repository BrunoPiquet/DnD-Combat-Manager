package dnd.item;
import java.util.function.Function;

import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;

public class Greatsword extends Weapon {
	
	Greatsword(){
		this.itemName = "Great Sword";
	}

	public Damage calcDamage(Function<AbilityScore, Integer> mod) {
		Damage d = new Damage();
		d.add(Dice.roll(2, 6)+mod.apply(AbilityScore.STR), DamageType.Slashing);
		return d;
	}
	

	

}
