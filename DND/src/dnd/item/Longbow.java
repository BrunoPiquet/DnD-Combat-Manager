package dnd.item;

import java.util.function.Function;

import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;

public class Longbow extends Weapon {
	public Longbow(){
		itemName = "Longbow";
	}

	public Damage calcDamage(Function<AbilityScore, Integer> mod) {
		Damage d = new Damage();
		d.add(Dice.roll(1, 8)+mod.apply(AbilityScore.DEX), DamageType.Piercing);
		return d;
	}
}
