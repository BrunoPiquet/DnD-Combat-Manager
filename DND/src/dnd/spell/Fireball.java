package dnd.spell;

import dnd.Character;
import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;

public class Fireball extends Spell {

	public Fireball(AbilityScore casterType) {
		super(casterType);
		spellName = "Fireball";
	}

	public Damage Save(Character caster, Character target, int level) {
		Damage d = new Damage();
		d.add(Dice.roll(8 + (level-3), 6), DamageType.Fire);
		if(target.savingThrow(AbilityScore.DEX) > caster.getSpellSave(casterType)) {d.ammountArray.set(0, d.ammountArray.get(0)/2);}
		return d;
	}


}
