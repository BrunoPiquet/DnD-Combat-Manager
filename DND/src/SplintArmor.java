import java.util.function.Function;

public class SplintArmor extends Armor{	
	public Function<Integer,Integer> getArmorCalc(){
		return (mod) -> {return 17;}; 
	}
	
}
