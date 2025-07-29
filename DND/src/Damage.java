import java.util.ArrayList;
public class Damage {
	DamageType type;
	int ammount;
	
	Damage(int ammount, DamageType type){
		this.ammount = ammount;
		this.type = type;
	}
	
	public String toString() {
		return ammount+" points of "+ type + " damage";
	}
}
