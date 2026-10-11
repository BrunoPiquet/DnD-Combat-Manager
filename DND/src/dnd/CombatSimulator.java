package dnd;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import dnd.core.*;
import dnd.spell.Spell;

public class CombatSimulator {
	private CharacterPanel actor;
	private ArrayList<CharacterPanel> targetList = new ArrayList<CharacterPanel>();
	
	public ArrayList<String> combatLogs = new ArrayList<String>();
	static JPanel simulatorPanel = new JPanel(new GridLayout(2,1));
	static JTextArea logArea = new JTextArea();
	static JPanel controllerPanel = new JPanel();
	static JLabel actorLabel = new JLabel("", SwingConstants.CENTER);
	static JLabel targetLabel = new JLabel("", SwingConstants.CENTER);
	static JButton actButton = new JButton("Act");
	static JButton clearButton = new JButton("Clear");
	static JComboBox<String> actionsBox = new JComboBox<>();
	static JComboBox<Integer> levelSelectionBox = new JComboBox<>();
	static JPanel buttonsPanel = new JPanel(new GridLayout(1,3));
	
	CombatSimulator(){

		logArea.setEditable(false);
		
		simulatorPanel.add(controllerPanel);
		
		logArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		
		controllerPanel.setLayout(new GridLayout(5,1));

		JLabel labelActor = new JLabel("Actor", SwingConstants.CENTER);
		labelActor.setVerticalAlignment(SwingConstants.BOTTOM);
		controllerPanel.add(labelActor);
		
		controllerPanel.add(actorLabel);
		
		//simulatorPanel.add(new JLabel());
		
		JLabel labelTarget = new JLabel("Target", SwingConstants.CENTER);
		labelTarget.setVerticalAlignment(SwingConstants.BOTTOM);
		controllerPanel.add(labelTarget);
		
		controllerPanel.add(targetLabel);
		
		actorLabel.setFont(new Font("Arial", Font.BOLD, 25));
		targetLabel.setFont(new Font("Arial", Font.BOLD, 25));
		
		buildButtonsPanel(false);
		
		actButton.addActionListener(listener);
		clearButton.addActionListener(listener);
		actionsBox.addActionListener(listener);
		levelSelectionBox.addActionListener(listener);
		
		JScrollPane scrollPanel = new JScrollPane(logArea);
		scrollPanel.setPreferredSize(new java.awt.Dimension(10, 10));
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		
		simulatorPanel.add(scrollPanel);
		
		
	}
	private void buildButtonsPanel(boolean withLevelSelection) {
		buttonsPanel.removeAll();
		if(withLevelSelection) {
			buttonsPanel.setLayout(new GridLayout(1,4));
		}
		else {
			buttonsPanel.setLayout(new GridLayout(1,3));
		}
		
		buttonsPanel.add(actButton);
		buttonsPanel.add(actionsBox);
		if(withLevelSelection) {
			buttonsPanel.add(levelSelectionBox);
		}
		buttonsPanel.add(clearButton);
		controllerPanel.add(buttonsPanel);
		
		buttonsPanel.revalidate();
	}
	
	private ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == actButton) {
				Act();
			}
			else if(e.getSource() == clearButton) {
				clear();
				actorLabel.setText("");
				targetLabel.setText("");
			}
			else if(e.getSource() == actionsBox) {
				levelSelectionBox.removeAllItems();
				buildButtonsPanel(false);
				
				Spell selectedSpell = null;
				for(int i=0;i<actor.getSpellList().size();i++) {
					if(actor.getSpellList().get(i).toString() == actionsBox.getSelectedItem()) {
						selectedSpell = actor.getSpellList().get(i);
						buildButtonsPanel(true);
						break;
					}
				}
				if(selectedSpell != null) {
					if(selectedSpell.minimumCastLevel==0) {
						levelSelectionBox.addItem(0);
					}
					else {
						for(int i = selectedSpell.minimumCastLevel; i <= 9; i++) {
							levelSelectionBox.addItem(i);
						}
					}

				}
			}
			else if(e.getSource() == levelSelectionBox) {

				
			}
			
		}
	};
	
	public JPanel getPanel() {
		return simulatorPanel;
	}
	
	public void setActions(ArrayList<String> items) {
		actionsBox.removeAllItems();
		for(int i=0;i<items.size();i++) {
			actionsBox.addItem(items.get(i));
		}
	}
	
	public void addToLog(String message) {
		combatLogs.add(message);
		String s = "";
		
		for(int i=0; i<combatLogs.size();i++) {
			s += combatLogs.get(i);
		}
		
		logArea.setText(s);
	}
	
	public CharacterPanel getActor() {
		return actor;
	}
	
	public void clear() {
		actionsBox.removeAllItems();
		this.actor = null;
		this.targetList.clear();
		//this.simulatorPanel.removeAll();

	}

	public void setActor(CharacterPanel actor) {
		this.actor = actor;
		actorLabel.setText(actor.getName());
		setActions(actor.getActions());
	}

	public ArrayList<CharacterPanel> getTargets() {
		return targetList;
	}

	public void addOrRemoveFromTargetList(CharacterPanel target) {
		if(targetList.contains(target)) {
			targetList.remove(target);
			}
		else {
			this.targetList.add(target);
		}
		targetLabel.setText(targetList.toString());
	}

	public void Act() {
		
		if(actionsBox.getSelectedItem()== "Attack") {
			CharacterPanel target = targetList.get(0);
			int attackRoll = actor.weaponAttackRoll();
			
			addToLog(actor.getName() + " used "+ actionsBox.getSelectedItem()+" on "+ target.getName() + "\n");
			
			if(attackRoll > target.getArmorClass()) {
				Damage d = actor.weaponAttack();
				target.takeDamage(d);
				addToLog(target.getName() + " takes " + d.toString() +" damage from "+ actor.getName()+"'s "+ actor.getRightHandName()+"" +"\n");
			}
			else {
				addToLog(actor.getName() + " missed "+ target.getName() + " (" + attackRoll +" < "+ target.getArmorClass() +")\n");
			}
			

		}
		else {
			Spell selectedSpell = null;
			for(int i=0;i<actor.getSpellList().size();i++) {
				if(actor.getSpellList().get(i).toString() == actionsBox.getSelectedItem()) {
					selectedSpell = actor.getSpellList().get(i);
					break;
				}
			}
			
			if(selectedSpell.isSave) {
				addToLog(actor.getName() + " used "+ actionsBox.getSelectedItem()+" on "+ targetList.toString() + "\n");
				
				selectedSpell.rollDamage((Integer)levelSelectionBox.getSelectedItem());
				for(Character target : targetList) {
					Damage d = selectedSpell.Save(target);
					target.takeDamage(d);
					addToLog(target.getName() + " takes " + d.toString() +" damage from "+ actor.getName()+"'s "+ selectedSpell.spellName+"" +"\n");
				}
				
			}
			else {
				CharacterPanel target = targetList.get(0);
				
				addToLog(actor.getName() + " used "+ actionsBox.getSelectedItem()+" on "+ target.getName() + "\n");
				
				int attackRoll = actor.spellAttackRoll(selectedSpell.casterType);
				
				if(attackRoll > target.getArmorClass()) {
					Damage d = selectedSpell.Cast();
					target.takeDamage(d);
					addToLog(target.getName() + " takes " + d.toString() +" damage from "+ actor.getName()+"'s "+ selectedSpell.spellName+"" +"\n");
				}
				else {
					addToLog(actor.getName() + " missed "+ target.getName() + " (" + attackRoll +" < "+ target.getArmorClass() +")\n");
				}
				
			}
			
			
		}
		addToLog("\n");
		actor.refresh();
		for(CharacterPanel target : targetList) {
			target.refresh();
		}
	}

}
