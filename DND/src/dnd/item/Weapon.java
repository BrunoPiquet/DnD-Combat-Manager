package dnd.item;
import dnd.core.AbilityScore;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.core.Dice;

public abstract class Weapon extends Item {

	public ArrayList<Dice> weaponDamageDice = new ArrayList<Dice>();
	public ArrayList<DamageType> weaponDamageTypes = new ArrayList<DamageType>();
	public int enhancement;
	public AbilityScore abilityModifier;
	
	public abstract Damage calcDamage(Function<AbilityScore, Integer> mod);
	
	public JPanel getPanel() {
		JPanel p = new JPanel(new GridLayout(1,3));
		
		p.add(new JLabel(itemName));
		p.add(new JButton("Equip Left"));
		p.add(new JButton("Equip Right"));
		
		return p;
	}

}
