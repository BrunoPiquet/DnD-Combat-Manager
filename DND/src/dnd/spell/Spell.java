package dnd.spell;
import dnd.core.*;

import java.io.Serializable;

import dnd.Character;

public abstract class Spell implements Serializable{
	private static final long serialVersionUID = 1L;

	public String spellName;
	
	public AbilityScore casterType;
	
	public boolean isSave;
	
	public Spell(AbilityScore as) {
		this.casterType = as;
		}
	
	public Damage Save(Character caster, Character target, int level) {return null;};
	
	public Damage Cast(Character caster) {return null;};
	
	public String toString() {
		return spellName;
	}
	
}
