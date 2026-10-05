package dnd.item;
import dnd.Character;

import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.*;

public abstract class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	public String itemName = "Item Name Pending";
	
	public String toString() {
		return itemName;
	}
	
	public void onEquip(Character c) {
		
	}
	
	public void onUnEquip(Character c) {
		
	}
	
	public JPanel getPanel() {
		JPanel p = new JPanel(new GridLayout(1,2));
		//JButton b = new JButton();
		
		//b.setActionCommand(itemName);
		p.add(new JLabel(itemName));
		p.add(new JButton("Equip"));
		
		return p;
	}
}
