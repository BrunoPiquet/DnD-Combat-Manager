import java.awt.GridLayout;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Armor extends Item {
	public abstract Function<Integer,Integer> getArmorCalc();
	
	public JPanel getPanel() {
		JPanel p = new JPanel(new GridLayout(1,3));
		
		p.add(new JLabel(itemName));
		p.add(new JButton("Equip Armor"));

		
		return p;
	}
}
