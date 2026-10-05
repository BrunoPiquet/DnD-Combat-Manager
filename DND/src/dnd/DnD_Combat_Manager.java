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
		JMenu charactersMenu = new JMenu("Characters");
		
		menuBar.add(addMenu);
		menuBar.add(charactersMenu);
		
		JMenuItem addItem = new JMenuItem("New Character");
		addMenu.add(addItem);
		
		JMenuItem saveItem = new JMenuItem("Save Character");
		charactersMenu.add(saveItem);
		
		JMenuItem loadItem = new JMenuItem("Load Character");
		charactersMenu.add(loadItem);
		
		JMenuItem clearItem = new JMenuItem("Clear Characters");
		charactersMenu.add(clearItem);
		
		addItem.addActionListener(e -> {characterList.add(new CharacterPanel()); buildListPanel();});
		
		saveItem.addActionListener(e -> {new CharacterFileSystem();});
		loadItem.addActionListener(e -> {characterList.add(loadCharacter("file.ser")); buildListPanel();});
		clearItem.addActionListener(e ->{characterList.removeAllElements(); buildListPanel();});
		
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
	
	public static void saveCharacter(CharacterPanel c){
        String filename = "file.ser";

        // Serialization
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(c);
            out.close();
            file.close();
            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public static CharacterPanel loadCharacter(String filename) {
        // Deserialization
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            CharacterPanel character = (CharacterPanel) in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been deserialized");
            return character;

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
		return null;
	}

	public static void main(String[] args) {

		characterList.add(new CharacterPanel("Kulve", 5, 20,14,19,10,10,13));
		//characterList.add(loadCharacter("file.ser"));
		
		characterList.add(new CharacterPanel("Rath", 5, 20,14,19,10,10,13));
		
		//characterList.get(0).addToInventory(new Greatsword());
		//characterList.get(0).addToInventory(new HideArmor());
		characterList.get(0).equipBody(new PlateArmor());
		//characterList.get(0).equipRightHand(new Longbow());
		
		 new DnD_Combat_Manager();
		 
		 
		 CharacterPanel kulve = characterList.get(0);
		 CharacterPanel rath = characterList.get(1);
		 
		 
		 rath.equipBody(new ArmorOfFireResistance());
		 rath.equipRightHand(new DarkfireShortbow());
		 rath.unEquipRightHand();
		 
		 //kulve.getSpellList().add(new Fireball(AbilityScore.CHA));
		 //kulve.getSpellList().add(new FireBolt(AbilityScore.CHA));
		 
	 
		 
	}

}
