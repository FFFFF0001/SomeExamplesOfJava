package display;

public class Display {
	private int limit=0;
	private int value=0;
	private static int step=1;
	
	public Display(int limit){
		this.limit=limit;
	}
	public void increase(){
		value++;
		if(value==limit){
			value=0;
		}
	}
	public int  getValue(){
		return value;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display d=new Display(24);
		for( ;; ){
			d.increase();
			System.out.println(d.getValue());
			
		}
		
	}

}
