package dnd.spell;

import dnd.Character;
import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;

public class Fireball extends SaveSpell {

	public Fireball() {
		spellName = "Fireball";
		minimumCastLevel = 3;
	}
	
	public Fireball(Character caster, AbilityScore casterType) {
		super(caster, casterType);
		spellName = "Fireball";
		minimumCastLevel = 3;
	}
	
	public void rollDamage(int castLevel) {
		if(castLevel < minimumCastLevel) {castLevel = minimumCastLevel;}
		Damage d = new Damage();
		d.add(Dice.roll(8 + (castLevel-minimumCastLevel), 6), DamageType.Fire);
		this.rolledDamage = d;
	}

	public Damage Save(Character target) {
		hasSaved = false;
		Damage d = new Damage(rolledDamage);
		if(target.savingThrow(AbilityScore.DEX) > caster.getSpellSave(casterType)) {
			d.ammountArray.set(0, d.ammountArray.get(0)/2); 
			hasSaved = true;
			}
		return d;
	}


}
