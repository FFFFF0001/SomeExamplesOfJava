package dome;

public class Videogame extends Item {
	private int numberOfPlayers;
	
	public Videogame() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Videogame(String title, int playingTime, boolean gotIt, String comment,int number) {
		super(title, playingTime, gotIt, comment);
		numberOfPlayers=number;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.print("Videogame");
		
		super.print();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
	}

}
