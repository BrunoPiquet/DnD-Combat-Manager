package dnd.core;
import dnd.Character;
import dnd.CharacterSheet;
import dnd.item.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WeaponCreator extends JFrame{
	CharacterSheet sheet;
	Weapon weapon = new GenericWeapon();
	
	JTextField nameField = new JTextField(); 
	JTextField enhancementField = new JTextField(); 
	JTextField nDiceField = new JTextField();
	JTextField nFacesField = new JTextField();
	JButton addButton = new JButton("Add");
	JButton finishButton = new JButton("Create");
	JComboBox<DamageType> damageTypeBox = new JComboBox<DamageType>(DamageType.values());
	JComboBox<AbilityScore> abilityTypeBox = new JComboBox<AbilityScore>(AbilityScore.values());
	
	public WeaponCreator(CharacterSheet sheet) {
		super("Weapon Creator");
		
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(650, 300);
		this.setLocationRelativeTo(null);
		
		this.sheet = sheet;
		
		this.setLayout(new GridLayout(1,2));
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel(new GridLayout(1,1));
		
		
		
		JTextArea info = new JTextArea("Welcome to the weapon creator. \n\nGive it a name and enhancement level. \nTo add damage insert the ammount of dice on the \nleft field and the type of dice on the right, \nthen click add. \nYou can do this multiple times. \nOnly the first damage will be affected by enhancement\n and ability scores. \n\nWhen done click finish");
		info.setEditable(false);
		info.setLineWrap(true);
		info.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		rightPanel.add(info);
		
		this.add(leftPanel);
		this.add(rightPanel);
		
		
		
		leftPanel.setLayout(new GridLayout(6,1));
		
		JPanel topPanel = new JPanel(new GridLayout(2,2));
		topPanel.add(new JLabel("Name"));
		topPanel.add(nameField);
		topPanel.add(new JLabel("Enhancement"));
		topPanel.add(enhancementField);
		leftPanel.add(topPanel);
		
		leftPanel.add(new JPanel());
		
		JPanel modPanel = new JPanel(new GridLayout(1,2));
		modPanel.add(new JLabel("Ability Modifier"));
		modPanel.add(abilityTypeBox);
		leftPanel.add(modPanel);
		
		
		JPanel midPanel = new JPanel(new GridLayout(2,4));
		midPanel.add(new JLabel("Dices"));
		midPanel.add(new JPanel());
		midPanel.add(new JLabel("Faces"));
		midPanel.add(new JPanel());
		midPanel.add(nDiceField);
		midPanel.add(new JLabel("d"));
		midPanel.add(nFacesField);
		midPanel.add(damageTypeBox);
		leftPanel.add(midPanel);
		
		leftPanel.add(new JPanel());
		
		JPanel bottomPanel = new JPanel(new GridLayout(1,2));
		bottomPanel.add(addButton);
		bottomPanel.add(finishButton);
		leftPanel.add(bottomPanel);

		addButton.addActionListener(listener);
		finishButton.addActionListener(listener);
		
		this.setVisible(true);
	}
	
	private ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addButton) {
				weapon.weaponDamageDice.add(new Dice(Integer.parseInt(nDiceField.getText()), Integer.parseInt(nFacesField.getText())));
				weapon.weaponDamageTypes.add((DamageType)damageTypeBox.getSelectedItem());
			}
			else if(e.getSource() == finishButton) {
				weapon.abilityModifier = (AbilityScore)abilityTypeBox.getSelectedItem();
				weapon.itemName = nameField.getText();
				weapon.enhancement = Integer.parseInt(enhancementField.getText());
				sheet.character.addToInventory(weapon);
				sheet.loadInventoryPanel();
			}
			
		}
	};
}
