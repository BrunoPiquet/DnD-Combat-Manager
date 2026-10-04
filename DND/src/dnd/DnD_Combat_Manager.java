package dnd;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import dnd.item.*;
import dnd.spell.*;
import dnd.core.*;

public class DnD_Combat_Manager extends JFrame {
		
	
	//Vector<Character> combatCharacters = new Vector<Character>();
	static Vector<CharacterPanel> characterList = new Vector<CharacterPanel>();
	
	//static JList<Character> list = new JList<Character>(characterList);
	
	static JPanel listPanel = new JPanel();

	static CombatSimulator simulator = new CombatSimulator();
	
	DnD_Combat_Manager(){
		super("DND Combat Manager");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(1,2));
		listPanel.setLayout(new GridLayout(15,1));
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu addMenu = new JMenu("Add");
		
		menuBar.add(addMenu);
		
		JMenuItem addItem = new JMenuItem("New Character");
		addMenu.add(addItem);
		
		addItem.addActionListener(e -> {characterList.add(new CharacterPanel()); buildListPanel();});
		
		this.add(new JScrollPane(listPanel));
		buildListPanel();

		this.add(new JScrollPane(simulator.getPanel()));
		
		//Listener listener = new Listener();
		//list.addListSelectionListener(listener);
		
		setJMenuBar(menuBar);
		
		this.setVisible(true);
		
	}
	
	private MouseAdapter adapter = new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
	    	for(int i =0; i<characterList.size();i++) {
	    		if(e.getSource() == characterList.get(i).getPanel()) {
	    			if(e.getButton() == MouseEvent.BUTTON1) {
	    				simulator.setActor(characterList.get(i));
	    				//actorLabel.setText(characterList.get(i).getName());
	    			}
	    			else if(e.getButton() == MouseEvent.BUTTON3) {
	    				simulator.setTarget(characterList.get(i));
	    				//targetLabel.setText(characterList.get(i).getName());
	    			}
	    		}
	    	}
	    }
	};
	    
	public void buildListPanel() {
		listPanel.removeAll();
		for(int i=0; i<characterList.size();i++) {
			JPanel p = characterList.get(i).makePanel();
						
			p.addMouseListener(adapter);
			
			listPanel.add(p);
		}
		revalidate();
		repaint();
	}

	public static void main(String[] args) {

		characterList.add(new CharacterPanel("Kulve", 5, 20,14,19,10,10,13));
		
		characterList.add(new CharacterPanel("Rath", 5, 20,14,19,10,10,13));
		
		//characterList.get(0).addToInventory(new Greatsword());
		//characterList.get(0).addToInventory(new HideArmor());
		characterList.get(0).equipBody(new PlateArmor());
		characterList.get(0).equipRightHand(new Longbow());
		
		 new DnD_Combat_Manager();
		 
		 
		 CharacterPanel kulve = characterList.get(0);
		 CharacterPanel rath = characterList.get(1);
		 
		 
		 rath.equipBody(new ArmorOfFireResistance());
		 rath.equipRightHand(new DarkfireShortbow());
		 rath.unEquipRightHand();
		 
		 kulve.getSpellList().add(new Fireball(AbilityScore.CHA));
		 kulve.getSpellList().add(new FireBolt(AbilityScore.CHA));
		 
		 //rath.takeDamage(kulve.castSpell(0).Save(kulve, rath, 3));
		 
		 //simulator.setActor(kulve);
		 //simulator.setTarget(rath);
		 //simulator.Attack();
		 //simulator.Attack();
	}

}
