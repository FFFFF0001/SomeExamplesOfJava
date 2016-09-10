package dome;

public class DVD extends Item {
	//private String title;
	private String director;
	//private int numofTracks;
	//private int playingTime;
	//private boolean gotIt;
	//private String comment;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DVD dvd=new DVD("a","b",1,"....");
		dvd.print();
	}
	public DVD(String title, String director, int playingTime, String comment) {
		super(title,playingTime,false,comment);
		//this.title = title;
		setTitle("b");
		this.director = director;
		//this.playingTime = playingTime;
		//this.comment = comment;
	}
	public void print() {
		// TODO Auto-generated method stub
		System.out.print("DVD:");
		super.print();//Item
		System.out.print(director);
	}

}
