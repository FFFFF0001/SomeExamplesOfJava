package clock;

public class clock {
	private Display hour=new Display(24);
	private Display minute=new Display(60);
	
	public void start(){
		while(true){
		minute.increase();
		if(minute.getValue()==0){
			hour.increase();
		}
		System.out.printf("%02d:%02d\n",hour.getValue(),minute.getValue());}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		clock clock=new clock();
		clock.start();
	}

}
