package dnd.item.MeleeWeapons;
import java.util.function.Function;

import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;
import dnd.item.Weapon;

public class Greatsword extends Weapon {
	
	public Greatsword(){
		this.itemName = "Great Sword";
	}

	public Damage calcDamage(Function<AbilityScore, Integer> mod) {
		Damage d = new Damage();
		d.add(Dice.roll(2, 6)+mod.apply(AbilityScore.STR), DamageType.Slashing);
		return d;
	}
	

	

}
