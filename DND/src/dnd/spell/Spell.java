package dnd.spell;
import dnd.core.*;
import dnd.Character;

public abstract class Spell {
	
	public String spellName;
	
	public Spell() {}
	
	public Damage Save(Character target, int level, int spellSave) {return null;};
	
	public Damage Cast(Character caster) {return null;};
	
	
	
}
