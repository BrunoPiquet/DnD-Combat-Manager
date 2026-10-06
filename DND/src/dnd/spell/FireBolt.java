package dnd.spell;

import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;
import dnd.Character;

public class FireBolt extends Spell{
	
	public FireBolt() {
		spellName = "Fire Bolt";
		minimumCastLevel = 0;
	}

	public FireBolt(Character caster, AbilityScore casterType) {
		super(caster, casterType);
		spellName = "Fire Bolt";
		minimumCastLevel = 0;
	}

	public Damage Cast() {
		Damage d = new Damage();
		int dices = (int) Math.floor((caster.getLevel()+1)/6)+1;
		d.add(Dice.roll(dices, 10), DamageType.Fire);
		return d;
	}


}
