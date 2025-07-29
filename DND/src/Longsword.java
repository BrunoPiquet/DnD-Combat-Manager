
public class Longsword extends Weapon {
	
	Longsword(){
		itemName = "Longsword";
	}
	
	public Damage calcDamage(int mod) {
		return new Damage(Dice.roll(1, 8) + mod,DamageType.Slashing);
	}
}
