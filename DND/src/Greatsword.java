
public class Greatsword extends Weapon {
	
	Greatsword(){
		this.itemName = "Great Sword";
	}
	
	public Damage calcDamage(int mod) {
		return new Damage(Dice.roll(2, 6) + mod,DamageType.Slashing);
	}
	

}

