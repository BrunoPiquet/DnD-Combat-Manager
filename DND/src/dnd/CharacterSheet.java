package dnd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import dnd.core.WeaponCreator;
import dnd.item.Armor;


public class CharacterSheet extends JFrame {
	public CharacterPanel character;
		
	JTextField strField = new JTextField() ;
	JTextField dexField = new JTextField();
	JTextField conField = new JTextField();
	JTextField intField = new JTextField();
	JTextField wisField = new JTextField();
	JTextField chaField = new JTextField();
	
	JTextField acField = new JTextField();
	JTextField speedField = new JTextField();
	JTextField maxHealthField = new JTextField();
	JTextField currentHealthField = new JTextField();
	
	JTextField rightHandField= new JTextField();
	JTextField leftHandField= new JTextField();
	JTextField bodyField= new JTextField();
	
	JPanel leftPanel = new JPanel(new GridLayout(17,1,0,0));
	JPanel rightPanel = new JPanel(new GridLayout(3,1));
	
	JPanel statsPanel = new JPanel(new GridLayout(4,2,20,0));
	JPanel equipmentPanel = new JPanel(new GridLayout(3,3,20,10));
	JPanel inventoryPanel = new JPanel();
	
	JButton unequipRightHandButton = new JButton("Unequip");
	JButton unequipLeftHandButton = new JButton("Unequip");
	JButton unequipBodyButton = new JButton("Unequip");
	
	Listener listener = new Listener();
	
	CharacterSheet(CharacterPanel cha){
		super(cha.getName()+ " Character's Sheet");
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {    
			public void windowClosing(WindowEvent e) {
				character.isUiOpen=false;
			}
		});
		this.character = cha;
		
		this.setSize(700, 800);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		
		
		JMenuBar menuBar = new JMenuBar();
		
        JMenu inventoryMenu = new JMenu("Inventory");
        
        JMenu addMenu = new JMenu("Add");
                
        addMenu.add(new JMenuItem("Swords"));
        addMenu.add(new JMenuItem("Bows"));
        addMenu.add(new JMenuItem("Armors"));
        
        JMenuItem clearMenu = new JMenuItem("Clear");
        clearMenu.addActionListener(e -> {character.getInventory().clear();loadInventoryPanel();this.repaint();});
        
        JMenuItem customWeaponMenu = new JMenuItem("Custom Weapon");
        customWeaponMenu.addActionListener(e -> {new WeaponCreator(this);});
        
        addMenu.add(customWeaponMenu);
        
        inventoryMenu.add(addMenu);
        inventoryMenu.add(clearMenu);

		menuBar.add(inventoryMenu);
		setJMenuBar(menuBar);
		
		strField.setText(character.getStrength()+"");
		strField.setHorizontalAlignment(SwingConstants.CENTER);
		
		dexField.setText(character.getDexterity()+"");
		dexField.setHorizontalAlignment(SwingConstants.CENTER);
		
		conField.setText(character.getConstitution()+"");
		conField.setHorizontalAlignment(SwingConstants.CENTER);
		
		intField.setText(character.getIntelligence()+"");
		intField.setHorizontalAlignment(SwingConstants.CENTER);
		
		wisField.setText(character.getWisdom()+"");
		wisField.setHorizontalAlignment(SwingConstants.CENTER);
		
		chaField.setText(character.getCharisma()+"");
		chaField.setHorizontalAlignment(SwingConstants.CENTER);
		
		maxHealthField.setText(character.getMaxHealth()+"");
		maxHealthField.setHorizontalAlignment(SwingConstants.CENTER);
		
		currentHealthField.setText(character.getCurrentHealth()+"");
		currentHealthField.setHorizontalAlignment(SwingConstants.CENTER);
		
		speedField.setText(character.getSpeed()+"");
		speedField.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		leftPanel.add(new JLabel("STR",JLabel.CENTER));
		leftPanel.add(strField);
		leftPanel.add(new JPanel());
		
		leftPanel.add(new JLabel("DEX",JLabel.CENTER));
		leftPanel.add(dexField);
		leftPanel.add(new JPanel());
		
		leftPanel.add(new JLabel("CON",JLabel.CENTER));
		leftPanel.add(conField);
		leftPanel.add(new JPanel());
		
		leftPanel.add(new JLabel("INT",JLabel.CENTER));
		leftPanel.add(intField);
		leftPanel.add(new JPanel());
		
		leftPanel.add(new JLabel("WIS",JLabel.CENTER));
		leftPanel.add(wisField);
		leftPanel.add(new JPanel());
		
		leftPanel.add(new JLabel("CHA",JLabel.CENTER));
		leftPanel.add(chaField);
		//leftPanel.add(new JPanel());
		
		
		//statsPanel.setBackground(Color.CYAN);
		//inventoryPanel.setBackground(Color.RED);
		
		statsPanel.add(new JLabel("Max Health",JLabel.CENTER));
		statsPanel.add(new JLabel("Current Health",JLabel.CENTER));
		
		statsPanel.add(maxHealthField);
		statsPanel.add(currentHealthField);
		
		statsPanel.add(new JLabel("Armor Class",JLabel.CENTER));
		statsPanel.add(new JLabel("Speed",JLabel.CENTER));
		
		statsPanel.add(acField);
		statsPanel.add(speedField);
		
		loadEquipmentPanel();
		
		//statsPanel.add(new JLabel("Body"));
		//statsPanel.add(bodyField);
			
		inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		/*
		 * gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 0.20; gbc.fill =
		 * GridBagConstraints.BOTH; rightPanel.add(statsPanel,gbc);
		 * 
		 * gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1; gbc.weighty = 0.80; gbc.fill =
		 * GridBagConstraints.BOTH; rightPanel.add(new JScrollPane(inventoryPanel),gbc);
		 */
		
		rightPanel.add(statsPanel);
		rightPanel.add(equipmentPanel);
		rightPanel.add(inventoryPanel);
		
		
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
		this.add(new JPanel(),gbc);
		
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.33;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
		this.add(leftPanel,gbc);
		
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
		this.add(new JPanel(),gbc);
		
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
		this.add(rightPanel,gbc);
		
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
		this.add(new JPanel(),gbc);
		
		maxHealthField.addActionListener(listener);
		currentHealthField.addActionListener(listener);
		
		strField.addActionListener(listener);
		dexField.addActionListener(listener);
		conField.addActionListener(listener);
		intField.addActionListener(listener);
		wisField.addActionListener(listener);
		chaField.addActionListener(listener);
		
		unequipLeftHandButton.addActionListener(listener);
		unequipRightHandButton.addActionListener(listener);
		unequipBodyButton.addActionListener(listener);
		
		
		loadInventoryPanel();
		
		this.setVisible(true);
	}
	
	public void loadInventoryPanel() {
		inventoryPanel.removeAll();
		
		if(character.getInventory().size()==0) {inventoryPanel.add(new JPanel());return;}
		
		for (int i = 0; i < character.getInventory().size(); i++) {

			JPanel p = new JPanel(new GridLayout(1, 2));

			p.add(new JLabel(character.getInventory().get(i).toString()));

			if(character.getInventory().get(i).getClass().getSuperclass() == Armor.class) {
				JButton equipButton = new JButton("Equip");
				
				equipButton.addActionListener(listener);
				
				equipButton.setActionCommand("b:"+i);
				p.add(equipButton);
			}
			else {
				JButton leftButton = new JButton("Equip L");
				JButton rightButton = new JButton("Equip R");
				
				leftButton.setActionCommand("l:"+i);
				rightButton.setActionCommand("r:"+i);
				
				leftButton.addActionListener(listener);
				rightButton.addActionListener(listener);

				//leftButton.setActionCommand(i);
				//rightButton.setActionCommand(i);
				
				p.add(leftButton);
				p.add(rightButton);
			}
			


			inventoryPanel.add(p);

			// inventoryPanel.add(character.getInventory().get(i).getPanel());
		}
		revalidate();
	}
	
	private void loadEquipmentPanel() {
		equipmentPanel.removeAll();
		
		acField.setText(character.getArmorClass()+"");
		acField.setHorizontalAlignment(SwingConstants.CENTER);
		
		leftHandField.setText(character.getLeftHandName()+"");
		leftHandField.setHorizontalAlignment(SwingConstants.CENTER);
		
		rightHandField.setText(character.getRightHandName()+"");
		rightHandField.setHorizontalAlignment(SwingConstants.CENTER);
		
		bodyField.setText(character.getBodyName()+"");
		bodyField.setHorizontalAlignment(SwingConstants.CENTER);
		
		equipmentPanel.add(new JLabel("Left Hand",JLabel.RIGHT));
		equipmentPanel.add(leftHandField);
		equipmentPanel.add(unequipLeftHandButton);
		
		equipmentPanel.add(new JLabel("Right Hand",JLabel.RIGHT));
		equipmentPanel.add(rightHandField);
		equipmentPanel.add(unequipRightHandButton);
		
		equipmentPanel.add(new JLabel("Body",JLabel.RIGHT));
		equipmentPanel.add(bodyField);
		equipmentPanel.add(unequipBodyButton);
		revalidate();
	}
	
	private class Listener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent ev) {
			if(ev.getSource().equals(strField)) {
				try {
					character.setStrength(Integer.parseInt(strField.getText()));
				}
				catch(NumberFormatException ex) {
					System.out.println("Invalid Ability Score");
				}
			}
			
			else if(ev.getSource().equals(dexField)) {
				try {
					character.setDexterity(Integer.parseInt(dexField.getText()));
				}
				catch(NumberFormatException ex) {
					System.out.println("Invalid Ability Score");
				}
			}
			
			else if(ev.getSource().equals(conField)) {
				try {
					character.setConstitution(Integer.parseInt(conField.getText()));
				}
				catch(NumberFormatException ex) {
					System.out.println("Invalid Ability Score");
				}
			}
			
			else if(ev.getSource().equals(intField)) {
				try {
					character.setIntelligence(Integer.parseInt(intField.getText()));
				}
				catch(NumberFormatException ex) {
					System.out.println("Invalid Ability Score");
				}
			}
			
			else if(ev.getSource().equals(wisField)) {
				try {
					character.setWisdom(Integer.parseInt(wisField.getText()));
				}
				catch(NumberFormatException ex) {
					System.out.println("Invalid Ability Score");
				}
			}
			
			else if(ev.getSource().equals(chaField)) {
				try {
					character.setCharisma(Integer.parseInt(chaField.getText()));
				}
				catch(NumberFormatException ex) {
					System.out.println("Invalid Ability Score");
				}
			}
			else if(ev.getSource().equals(maxHealthField)) {
				try {
					character.setMaxHealth(Integer.parseInt(maxHealthField.getText()));
				}
				catch(NumberFormatException ex) {
					System.out.println("Invalid Max Health");
				}
			}
			else if(ev.getSource().equals(currentHealthField)) {
				try {
					character.setCurrentHealth(Integer.parseInt(currentHealthField.getText()));
				}
				catch(NumberFormatException ex) {
					System.out.println("Invalid Current Health");
				}
			}
			else if(ev.getSource().equals(unequipLeftHandButton)) {
				character.unEquipLeftHand();
				loadInventoryPanel();
				loadEquipmentPanel();
			}
			else if(ev.getSource().equals(unequipRightHandButton)) {
				character.unEquipRightHand();
				loadInventoryPanel();
				loadEquipmentPanel();
			}
			else if(ev.getSource().equals(unequipBodyButton)) {
				character.unEquipBody();
				loadInventoryPanel();
				loadEquipmentPanel();

			}
			else {
				
				String command = ev.getActionCommand();
				int hand = 0;
				//System.out.println(command.substring(1, command.length()));
				int index = Integer.parseInt(command.substring(2, command.length()));
				if(command.charAt(0) == 'l') {hand = 1;}
				else if(command.charAt(0) == 'r') {hand = 2;}
				
				character.equipFromInventory(index, hand);
				loadInventoryPanel();
				loadEquipmentPanel();
				revalidate();
			}
			character.refresh();
		}

	}// End Listener
	
}
