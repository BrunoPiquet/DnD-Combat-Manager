package dnd.item;

import java.util.function.Function;

import dnd.core.AbilityScore;
import dnd.core.Damage;

public class GenericWeapon extends Weapon {

	@Override
	public Damage calcDamage(Function<AbilityScore, Integer> mod) {
		Damage d = new Damage();

		for(int i=0; i<super.weaponDamageDice.size() && i<super.weaponDamageTypes.size();i++) {
			int roll = weaponDamageDice.get(i).roll();
			if(i==0) { roll = roll + enhancement + mod.apply(super.abilityModifier);}
			d.add(roll, weaponDamageTypes.get(i)); 
		}
		return d;
	}

}
