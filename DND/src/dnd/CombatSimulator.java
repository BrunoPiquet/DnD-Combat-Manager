package dnd;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import dnd.core.*;

public class CombatSimulator {
	private CharacterPanel actor;
	private CharacterPanel target;
	
	public ArrayList<String> combatLogs = new ArrayList<String>();
	public JTextArea logArea;
	public JPanel simulatorPanel;
	
	CombatSimulator(JTextArea logArea, JPanel simulatorPanel){
		this.logArea = logArea;
		this.simulatorPanel = simulatorPanel;
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
	}

	public void setActor(CharacterPanel actor) {
		this.actor = actor;
		//this.simulatorPanel.add(actor.makePanel());
	}

	public CharacterPanel getTarget() {
		return target;
	}

	public void setTarget(CharacterPanel target) {
		this.target = target;
	}

	public void Attack() {
		Damage d = actor.Attack();
		target.TakeDamage(d);
		
		addToLog(actor.getName() + " attacks "+ target.getName() + " for " + d.toString() +"\n");
		
		actor.refresh();
		target.refresh();
	}

}
