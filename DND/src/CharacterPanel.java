import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharacterPanel extends Character {
	
	boolean isUiOpen = false;
	
	public CharacterPanel(String n, int str, int dex, int con, int wis, int inte, int cha) {
		super(n, str, dex, con, wis, inte, cha);

	}
	
	public void ShowUI() {
		
		if(!isUiOpen) {
			isUiOpen=true;
			new CharacterSheet(this);
		}
		
	}

	public JPanel getPanel() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(new JLabel(getName()));

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
