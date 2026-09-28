package dnd.item;

import java.util.function.Function;

import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;
import dnd.Character;

public class DarkfireShortbow extends Weapon {
	
	public DarkfireShortbow(){
		itemName = "Darkfire Shortbow";
	}
	
	public void onEquip(Character c) {
		c.setResistance(DamageType.Fire, 0.5f);
		c.setResistance(DamageType.Cold, 0.5f);
	}

	public void onUnEquip(Character c) {
		c.setResistance(DamageType.Fire, 1f);
		c.setResistance(DamageType.Cold, 1f);
	}
	
	public Damage calcDamage(Function<AbilityScore, Integer> mod) {
		Damage d = new Damage();
		d.add(Dice.roll(1, 6)+mod.apply(AbilityScore.DEX)+2, DamageType.Piercing);
		return d;
	}

}
