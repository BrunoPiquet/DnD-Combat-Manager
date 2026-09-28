package dnd;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;

import dnd.core.AbilityScore;
import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.item.Armor;
import dnd.item.Item;
import dnd.item.Weapon;

public class Character {
	private String characterName;
	
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
	private Function<Integer,Integer> calculateArmorClass;
	
	public EnumMap<DamageType, Float> resistances = new EnumMap<>(DamageType.class);
	
	private ArrayList<Item> inventory = new ArrayList<Item>();

	public Character(String n, int str, int dex, int con, int wis, int inte, int cha) {
		this.characterName = n;
		this.strength=str;
		this.dexterity=dex;
		this.constitution=con;
		this.intelligence=inte;
		this.wisdom=wis;
		this.charisma=cha;
		
		for (DamageType type : DamageType.values()) {
	        resistances.put(type, (float) 1);
	    }
	}
	
	public void addToInventory(Item i) {
		inventory.add(i);
	}
		
	
	public Damage Attack() {
		
		if(rightHand == null) {
			 return new Damage(1+getModifier(AbilityScore.STR),DamageType.Bludgeoning);
		}
		
		return rightHand.calcDamage(this::getModifier);
	}
	
	public void TakeDamage(Damage d) {
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
		calculateArmorClass = ((Armor) item).getArmorCalc();
	}
	public void unEquipBody() {
		if(body==null) return;
		body.onUnEquip(this);
		inventory.add(body);
		body=null;
		calculateArmorClass = null;
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
	
	public int getArmorClass() {
		if(calculateArmorClass == null) {return armorClass;}
		return calculateArmorClass.apply(this.getDexterity());
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
	
	
	public String getName() {
		return characterName;
	}
	public void setName(String name) {
		this.characterName = name;
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


	public String toString() {
		return characterName;
	}
	
}
