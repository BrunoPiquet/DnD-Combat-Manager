import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;


public class DnD_Combat_Manager extends JFrame {
		
	
	//Vector<Character> combatCharacters = new Vector<Character>();
	static Vector<CharacterPanel> characterList = new Vector<CharacterPanel>();
	
	//static JList<Character> list = new JList<Character>(characterList);
	
	
	static JPanel listPanel = new JPanel();
	
	DnD_Combat_Manager(){
		super("DND Combat Manager");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		
		listPanel.setLayout(new GridLayout(15,1));
		
		
		for(int i=0; i<characterList.size();i++) {
			JPanel p = characterList.get(i).getPanel();
			listPanel.add(p);
		}
		
		this.add(new JScrollPane(listPanel));
		
		//Listener listener = new Listener();
		//list.addListSelectionListener(listener);
		
		
		this.setVisible(true);
		
	}

	

	
	public static void main(String[] args) {

		characterList.add(new CharacterPanel("Kulve",20,14,19,10,10,13));
		
		characterList.add(new CharacterPanel("Rath",20,14,19,10,10,13));
		
		characterList.get(0).addToInventory(new Greatsword());
		//characterList.get(0).addToInventory(new HideArmor());
		characterList.get(0).equipBody(new PlateArmor());
		characterList.get(0).equipRightHand(new Longsword());
		
		
		 Character kulve = new Character("Kulve",20,14,19,10,10,13);
		  
		 kulve.equipBody(new HideArmor());
		  
		 System.out.println(kulve.getArmorClass());
		  
		 kulve.equipBody(new PlateArmor());
		  
		 System.out.println(kulve.getArmorClass());
		 
		 kulve.unEquipBody();
		  
		 System.out.println(kulve.getArmorClass());
		 
		
//		new DnD_Combat_Manager();

		

	}
	

	

}
