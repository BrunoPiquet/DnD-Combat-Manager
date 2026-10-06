package dnd.spell;

import dnd.Character;
import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;

public class ConeOfCold extends Spell {
	public ConeOfCold() {
		spellName = "Cone Of Cold";
		isSave = true;
		minimumCastLevel = 5;
	}
	
	public Damage Save(Character target, int castLevel) {
		if(castLevel < minimumCastLevel) {castLevel = minimumCastLevel;}
		
		Damage d = new Damage();
		d.add(Dice.roll(8 + (castLevel-minimumCastLevel), 8), DamageType.Cold);
		if(target.savingThrow(AbilityScore.CON) > caster.getSpellSave(casterType)) {d.ammountArray.set(0, d.ammountArray.get(0)/2);}
		return d;
	}
}
