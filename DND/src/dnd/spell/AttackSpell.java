package dnd.spell;

import dnd.Character;
import dnd.core.AbilityScore;
import dnd.core.Damage;

public class AttackSpell extends Spell{
	AttackSpell(Character caster, AbilityScore casterType){
		super(caster, casterType);
	}
	AttackSpell(){}
	
	public Damage Cast() {return null;};
}
