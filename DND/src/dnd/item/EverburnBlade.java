package dnd.item;

import java.util.function.Function;

import dnd.core.Damage;
import dnd.core.*;

public class EverburnBlade extends Weapon {

	public EverburnBlade(){
		itemName = "Everbrun Blade";
	}
	
	public Damage calcDamage(Function<AbilityScore, Integer> mod) {
		Damage d = new Damage();
		d.add(Dice.roll(2, 6)+mod.apply(AbilityScore.STR), DamageType.Slashing);
		d.add(Dice.roll(1, 4), DamageType.Fire);
		return d;
	}

}
