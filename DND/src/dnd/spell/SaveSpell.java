package dnd.spell;

import dnd.Character;
import dnd.core.AbilityScore;
import dnd.core.Damage;

public abstract class SaveSpell extends Spell {
	
	public boolean hasSaved = false;
	
	SaveSpell(Character caster, AbilityScore casterType){
		super(caster, casterType);
	}
	SaveSpell(){}
	
	public abstract Damage Save(Character target);
}
