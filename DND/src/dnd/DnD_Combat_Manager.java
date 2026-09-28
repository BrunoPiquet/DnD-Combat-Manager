package dnd;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dnd.item.EverburnBlade;
import dnd.item.PlateArmor;

import java.awt.*;
import java.awt.event.*;

import dnd.core.Damage;
import dnd.core.DamageType;
import dnd.item.*;

public class DnD_Combat_Manager extends JFrame {
		
	
	//Vector<Character> combatCharacters = new Vector<Character>();
	static Vector<CharacterPanel> characterList = new Vector<CharacterPanel>();
	
	//static JList<Character> list = new JList<Character>(characterList);
	
	static JPanel listPanel = new JPanel();
	static JTextArea logArea = new JTextArea();
	static JPanel simulatorPanel = new JPanel();
	static JLabel actorLabel = new JLabel("", SwingConstants.CENTER);
	static JLabel targetLabel = new JLabel("", SwingConstants.CENTER);
	static JButton actButton = new JButton("Act");
	static JButton clearButton = new JButton("Clear");
	static CombatSimulator simulator = new CombatSimulator(logArea, simulatorPanel);
	
	DnD_Combat_Manager(){
		super("DND Combat Manager");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(1,2));
		listPanel.setLayout(new GridLayout(15,1));
		
		this.add(new JScrollPane(listPanel));
		JPanel rightPanel = new JPanel(new GridLayout(2,1));
		
		logArea.setEditable(false);
		
		this.add(rightPanel);
		rightPanel.add(simulatorPanel);
		rightPanel.add(logArea);
		logArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		buildListPanel();
		
		simulatorPanel.setLayout(new GridLayout(5,1));
		JLabel labelActor = new JLabel("Actor", SwingConstants.CENTER);
		labelActor.setVerticalAlignment(SwingConstants.BOTTOM);
		simulatorPanel.add(labelActor);
		simulatorPanel.add(actorLabel);
		//simulatorPanel.add(new JLabel());
		JLabel labelTarget = new JLabel("Actor", SwingConstants.CENTER);
		labelTarget.setVerticalAlignment(SwingConstants.BOTTOM);
		simulatorPanel.add(labelTarget);
		
		simulatorPanel.add(targetLabel);
		
		actorLabel.setFont(new Font("Arial", Font.BOLD, 25));
		targetLabel.setFont(new Font("Arial", Font.BOLD, 25));
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1,3));
		buttonsPanel.add(actButton);
		buttonsPanel.add(new JLabel());
		buttonsPanel.add(clearButton);
		
		actButton.addActionListener(listener);
		clearButton.addActionListener(listener);
		
		simulatorPanel.add(buttonsPanel);

		
		//Listener listener = new Listener();
		//list.addListSelectionListener(listener);
		
		this.setVisible(true);
		
	}
	
	private MouseAdapter adapter = new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
	    	for(int i =0; i<characterList.size();i++) {
	    		if(e.getSource() == characterList.get(i).getPanel()) {
	    			if(e.getButton() == MouseEvent.BUTTON1) {
	    				simulator.setActor(characterList.get(i));
	    				actorLabel.setText(characterList.get(i).getName());
	    			}
	    			else if(e.getButton() == MouseEvent.BUTTON3) {
	    				simulator.setTarget(characterList.get(i));
	    				targetLabel.setText(characterList.get(i).getName());
	    			}
	    		}
	    	}
	    }
	};
	private ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == actButton) {
				simulator.Attack();
			}
			else if(e.getSource() == clearButton) {
				simulator.clear();
				actorLabel.setText("");
				targetLabel.setText("");
			}
			
		}
	};
	    
	public void buildListPanel() {
		for(int i=0; i<characterList.size();i++) {
			JPanel p = characterList.get(i).makePanel();
						
			p.addMouseListener(adapter);
			
			listPanel.add(p);
		}
	}

	public static void main(String[] args) {

		characterList.add(new CharacterPanel("Kulve",20,14,19,10,10,13));
		
		characterList.add(new CharacterPanel("Rath",20,14,19,10,10,13));
		
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
		 
		 //simulator.setActor(kulve);
		 //simulator.setTarget(rath);
		 //simulator.Attack();
		 //simulator.Attack();
		  
	}

}
