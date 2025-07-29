
public interface Dice {
	public static int roll(int nDices, int nFaces) {
		int total=0;
		for(int i=0; i< nDices;i++) {
			total+=(int)(Math.random()*nFaces+1);
		}
		return total;
	}
	
	public static int roll(String command) {
		int nDices=Integer.parseInt(command.substring(0, command.indexOf('d')));
		int nFaces=Integer.parseInt(command.substring(command.indexOf('d')+1,command.length()));
		int total=0;
		for(int i=0; i< nDices;i++) {
			total+=(int)(Math.random()*nFaces+1);
		}
		return total;		
	}
}
