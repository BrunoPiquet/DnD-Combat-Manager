package dnd.item;
import dnd.core.AbilityScore;

import java.awt.GridLayout;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dnd.core.Damage;

public abstract class Weapon extends Item {

	public abstract Damage calcDamage(Function<AbilityScore, Integer> mod);
	
	public JPanel getPanel() {
		JPanel p = new JPanel(new GridLayout(1,3));
		
		p.add(new JLabel(itemName));
		p.add(new JButton("Equip Left"));
		p.add(new JButton("Equip Right"));
		
		return p;
	}

}
