package dnd.spell;
import dnd.core.*;

import java.io.Serializable;

import dnd.Character;

public abstract class Spell implements Serializable{
	private static final long serialVersionUID = 1L;

	public String spellName;
	
	public AbilityScore casterType;
	
	public boolean isSave;
	
	public Character caster;
	
	public int minimumCastLevel;
	
	public Spell() {}
	
	public Spell(Character caster, AbilityScore as) {
		this.caster = caster;
		this.casterType = as;
		}
	
	public Damage Save(Character target, int level) {return null;};
	
	public Damage Cast() {return null;};
	
	public String toString() {
		return spellName;
	}
	
}
