package dnd.item;

import java.awt.GridLayout;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dnd.core.AbilityScore;

public abstract class Armor extends Item {
	private static final long serialVersionUID = 1L;

	public abstract int getArmorClass(Function<AbilityScore, Integer> mod);
	
	public JPanel getPanel() {
		JPanel p = new JPanel(new GridLayout(1,3));
		
		p.add(new JLabel(itemName));
		p.add(new JButton("Equip Armor"));

		
		return p;
	}
}
