package dnd.spell;

import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;
import dnd.Character;

public class FireBolt extends Spell{

	public FireBolt() {
		super();
	}

	public Damage Cast(Character c) {
		Damage d = new Damage();
		int dices = (int) Math.floor((c.getLevel()+1)/6)+1;
		d.add(Dice.roll(dices, 10), DamageType.Fire);
		return d;
	}


}
