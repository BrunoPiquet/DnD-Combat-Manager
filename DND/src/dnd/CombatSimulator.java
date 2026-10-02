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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import dnd.core.*;
import dnd.spell.Spell;

public class CombatSimulator {
	private CharacterPanel actor;
	private CharacterPanel target;
	
	public ArrayList<String> combatLogs = new ArrayList<String>();
	static JPanel simulatorPanel = new JPanel(new GridLayout(2,1));
	static JTextArea logArea = new JTextArea();
	static JPanel controllerPanel = new JPanel();
	static JLabel actorLabel = new JLabel("", SwingConstants.CENTER);
	static JLabel targetLabel = new JLabel("", SwingConstants.CENTER);
	static JButton actButton = new JButton("Act");
	static JButton clearButton = new JButton("Clear");
	static JComboBox<String> actionsBox = new JComboBox<>();
	
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
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1,3));
		
		buttonsPanel.add(actButton);
		buttonsPanel.add(actionsBox);
		buttonsPanel.add(clearButton);
		controllerPanel.add(buttonsPanel);
		
		actButton.addActionListener(listener);
		clearButton.addActionListener(listener);
		
		
		simulatorPanel.add(logArea);
		
		
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
		this.actor = null;
		this.target = null;
		//this.simulatorPanel.removeAll();
		actionsBox.removeAllItems();
	}

	public void setActor(CharacterPanel actor) {
		this.actor = actor;
		actorLabel.setText(actor.getName());
		setActions(actor.getActions());
	}

	public CharacterPanel getTarget() {
		return target;
	}

	public void setTarget(CharacterPanel target) {
		this.target = target;
		targetLabel.setText(target.getName());
	}

	public void Act() {
		if(actionsBox.getSelectedItem()== "Attack") {
			Damage d = actor.weaponAttack();
			target.takeDamage(d);
			addToLog(actor.getName() + " attacks "+ target.getName() + " for " + d.toString() +"\n");
		}
		else {
			Spell selectedSpell = null;
			for(int i=0;i<actor.getSpellList().size();i++) {
				if(actor.getSpellList().get(i).toString() == actionsBox.getSelectedItem()) {
					selectedSpell = actor.getSpellList().get(i);
					break;
				}
			}
			
			if(selectedSpell.casterType != null) {
				Damage d = selectedSpell.Save(actor, target, 3);
				target.takeDamage(d);
			}
			else {
				Damage d = selectedSpell.Cast(actor);
				target.takeDamage(d);
			}
			

		}

		
		
		
		actor.refresh();
		target.refresh();
	}

}
