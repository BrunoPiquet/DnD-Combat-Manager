package dnd.spell;
import dnd.core.*;

import java.io.Serializable;

import dnd.Character;

public abstract class Spell implements Serializable{
	private static final long serialVersionUID = 1L;

	public String spellName;
	
	public AbilityScore casterType;
	
	public Character caster;
	
	public int minimumCastLevel;
	
	public Damage rolledDamage = new Damage();
	
	public Spell() {}
	
	public Spell(Character caster, AbilityScore as) {
		this.caster = caster;
		this.casterType = as;
		}
	
	public void rollDamage(int level) {}
	
	public String toString() {
		return spellName;
	}
}
