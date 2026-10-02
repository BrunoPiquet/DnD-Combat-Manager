package dnd.spell;
import dnd.core.*;
import dnd.Character;

public abstract class Spell {
	
	public String spellName;
	
	public AbilityScore casterType;
	
	public Spell(AbilityScore as) {
		this.casterType = as;
		}
	
	public Damage Save(Character caster, Character target, int level) {return null;};
	
	public Damage Cast(Character caster) {return null;};
	
	public String toString() {
		return spellName;
	}
	
}
