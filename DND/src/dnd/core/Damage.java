package dnd.core;
import java.util.ArrayList;
public class Damage {
	public ArrayList<Integer> ammountArray = new ArrayList<Integer>();
	public ArrayList<DamageType> typeArray = new ArrayList<DamageType>();

	public Damage(int ammount, DamageType type){
		this.ammountArray.add(ammount);
		this.typeArray.add(type);	
	}
	
	public Damage() {
	}
	
	public void add(int ammount, DamageType type) {
		ammountArray.add(ammount);
		typeArray.add(type);
	}
	
	
	public String toString() {
		String s = "";
		for(int i = 0; i < ammountArray.size(); i++) {
			s = s.concat(ammountArray.get(i)+" points of "+ typeArray.get(i) + " damage");
			if(i != ammountArray.size()-1) {s+=" and ";}
		}
		//return ammount+" points of "+ type + " damage";
		return s;
	}
}
