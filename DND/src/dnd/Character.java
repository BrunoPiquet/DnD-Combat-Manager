package dnd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.function.Function;

import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;
import dnd.core.Caster;
import dnd.item.Armor;
import dnd.item.Item;
import dnd.item.Weapon;
import dnd.spell.*;

public class Character implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String characterName;
	private String ownerName = "";
	
	private int level;
	
	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;
	
	private int maxHealth = 20;
	private int currentHealth = maxHealth;
	
	private int armorClass = 10;
	
	private int speed = 30;
	
	private Weapon rightHand;
	private Weapon leftHand;
	
	private Armor body;
	//private Function<Integer,Integer> calculateArmorClass;
	
	public EnumMap<DamageType, Float> resistances = new EnumMap<>(DamageType.class);
	public EnumMap<AbilityScore, Boolean> savingThrowProficiencies = new EnumMap<>(AbilityScore.class);
	
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private ArrayList<Spell> spellList = new ArrayList<Spell>();
	
	public Character(String n, int level, int str, int dex, int con, int wis, int inte, int cha) {
		this.characterName = n;
		this.level = level;
		this.strength=str;
		this.dexterity=dex;
		this.constitution=con;
		this.intelligence=inte;
		this.wisdom=wis;
		this.charisma=cha;
		
		for (DamageType type : DamageType.values()) {
	        resistances.put(type, (float) 1);
	    }
		for (AbilityScore type : AbilityScore.values()) {
			savingThrowProficiencies.put(type, false);
	    }
	}
	
	public void addToInventory(Item i) {
		inventory.add(i);
	}
	
	public void addToSpellList(Spell spell, AbilityScore as) {
		spell.caster = this;
		spell.casterType = as;
		spellList.add(spell);
	}
		
	
	public Damage weaponAttack() {
		
		if(rightHand == null) {
			 return new Damage(1+getModifier(AbilityScore.STR),DamageType.Bludgeoning);
		}
		
		return rightHand.calcDamage(this::getModifier);
	}
	
	public int weaponAttackRoll() {
		if(rightHand == null) {
			 return Dice.d20() + getModifier(AbilityScore.STR);
		}
		
		return Dice.d20() + getModifier(rightHand.abilityModifier) + getProficiency() + rightHand.enhancement;
	}
	public int spellAttackRoll(AbilityScore as) {
		return Dice.d20() + getModifier(as) + getProficiency();
	}
	
	public ArrayList<String> getActions(){
		ArrayList<String> actions = new ArrayList<String>();
		actions.add("Attack");
		
		for(int i=0; i<spellList.size();i++) {
			actions.add(spellList.get(i).toString());
		}
		
		return actions;
	}
	
	public Spell castSpell(int index) {
		//Spell bolt = new Fireball(3, getProficiency(), getModifier(AbilityScore.CHA), AbilityScore.DEX);
		return spellList.get(index);
	}
	
	public int savingThrow(AbilityScore skill) {
		return Dice.roll(1, 20) + getModifier(skill) + ((savingThrowProficiencies.get(skill)) ? getProficiency() : 0);
	}
	
	public void takeDamage(Damage d) {
		for(int i = 0; i < d.ammountArray.size() && i < d.typeArray.size(); i++) {
			int ins = (int) (d.ammountArray.get(i) * this.resistances.get(d.typeArray.get(i)));			
			this.currentHealth -= ins;
		}
	}
	
	public void setResistance(DamageType type, float r) {
		this.resistances.put(type, r);
	}
	
	private void onEquipRefresh() {
		if(body!=null) {body.onEquip(this);}
		if(rightHand!=null) {rightHand.onEquip(this);}
		if(leftHand!=null) {leftHand.onEquip(this);}
	}
	
	public void equipFromInventory(int index, int hand) {
		if(hand==0) {
			unEquipBody();
			equipBody(inventory.get(index));
			
		}
		else if (hand==1) {
			unEquipLeftHand();
			equipLeftHand(inventory.get(index));
		}
		else {
			unEquipRightHand();
			equipRightHand(inventory.get(index));
		}
		
		inventory.remove(index);
	}
	
	public void equipRightHand(Item item) {
		rightHand = (Weapon) item;
	}
	
	public void unEquipRightHand() {
		if(rightHand==null) return;
		rightHand.onUnEquip(this);
		inventory.add(rightHand);
		rightHand = null;
		onEquipRefresh();
	}
	
	public Weapon getRightHand() {
		return rightHand;
	}
	
	public String getRightHandName() {
		if(rightHand==null) return "";
		return rightHand.toString();
	}
	
	public void equipLeftHand(Item item) {
		leftHand = (Weapon) item;
	}
	
	public void unEquipLeftHand() {
		if(leftHand==null) return;
		leftHand.onUnEquip(this);
		inventory.add(leftHand);
		leftHand = null;
		onEquipRefresh();
	}
	public Weapon getLeftHand() {
		return leftHand;
	}
	public String getLeftHandName() {
		if(leftHand==null) return "";
		return leftHand.toString();
	}
	
	public void equipBody(Item item) {
		body = (Armor) item;
		//calculateArmorClass = ((Armor) item).getArmorCalc();
	}
	public void unEquipBody() {
		if(body==null) return;
		body.onUnEquip(this);
		inventory.add(body);
		body=null;
		//calculateArmorClass = null;
		onEquipRefresh();
	}
	
	public Armor getBody() {
		return body;
	}
	
	public String getBodyName() {
		if(body==null) return "";
		return body.toString();
	}
	
	public ArrayList<Item> getInventory(){
		return inventory;
	}
	
	public ArrayList<Spell> getSpellList(){
		return spellList;
	}
	
	public int getArmorClass() {
		if(body == null) {return armorClass;}
		return body.getArmorClass(this::getModifier);
	}
	
	public int getModifier(AbilityScore as) {
		int score = 0;
		switch(as) {
		case STR:{
			score = this.strength;
			break;
		}
		case DEX:{
				score = this.dexterity;
			break;
		}
		case CON:{
			score = this.constitution;
			break;
		}
		case INT:{
			score = this.intelligence;
			break;
		}
		case WIS:{
			score = this.wisdom;
			break;
		}
		case CHA:{
			score = this.charisma;
		}
		}
		return (score - 10)/2;
	}
	
	public int getSpellSave(AbilityScore as) {
		return 8 + getProficiency() + getModifier(as);
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
	
	public String getName() {
		return characterName;
	}
	public void setName(String name) {
		this.characterName = name;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String name) {
		this.ownerName = name;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getDexterity() {
		return dexterity;
	}
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	public int getConstitution() {
		return constitution;
	}
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	public int getWisdom() {
		return wisdom;
	}
	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}
	public int getCharisma() {
		return charisma;
	}
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currenthealth) {
		this.currentHealth = currenthealth;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/*
	public void setSpellcastingAbility(AbilityScore as) {
		spellCastingAbility = as;
	}
	*/
	
	public int getProficiency() {
		return 2 + ((level - 1) / 4);
	}


	public String toString() {
		return characterName;
	}
	
}
