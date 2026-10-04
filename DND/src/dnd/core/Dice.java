package dnd.core;

public class Dice {
	
	public int nFaces;
	public int nDices;
	
	public Dice(int nDices, int nFaces) {
		this.nDices = nDices;
		this.nFaces = nFaces;
	}
	
	public int roll() {
		int total=0;
		for(int i=0; i< this.nDices;i++) {
			total+=(int)(Math.random()*this.nFaces+1);
		}
		return total; 
	}
	
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
	
	public static int d20() {
		return (int)(Math.random()*20+1);
	}
	public static int d12() {
		return (int)(Math.random()*12+1);
	}
	public static int d10() {
		return (int)(Math.random()*12+1);
	}
	public static int d8() {
		return (int)(Math.random()*12+1);
	}
	public static int d6() {
		return (int)(Math.random()*12+1);
	}
	public static int d4() {
		return (int)(Math.random()*12+1);
	}
}
