package dnd;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharacterPanel extends Character {
	
	boolean isUiOpen = false;
	private JPanel panel;
	private JLabel nameLabel;
	private JLabel healthLabel;
	
	public CharacterPanel(String n, int str, int dex, int con, int wis, int inte, int cha) {
		super(n, str, dex, con, wis, inte, cha);

	}
	
	public void ShowUI() {
		if(!isUiOpen) {
			isUiOpen=true;
			new CharacterSheet(this);
		}
		
	}
	
	public void refresh() {
		healthLabel.setText(getCurrentHealth()+"/"+getMaxHealth());
		
		panel.revalidate();
		panel.repaint();
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public JPanel makePanel() {
		panel = new JPanel(new GridLayout(1,3));
		
		nameLabel = new JLabel(getName());
		panel.add(nameLabel);
		
		healthLabel = new JLabel(getCurrentHealth()+"/"+getMaxHealth());
		panel.add(healthLabel);
		
		JButton button = new JButton("Edit");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowUI();
			}});
		
		panel.add(button);
		
				
		return panel;
		
	}
}
