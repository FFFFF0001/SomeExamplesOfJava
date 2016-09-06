package º¯ÊıÇóºÍ;

public class Main {
	public static void sum(int a,int b){
		int i;
		int sum=0;
		for(i=a;i<=b;i++){
			sum+=i;
			
		}
		System.out.println(sum);
	}
//	public static void swap(int a,int b){
//		int t;
//		t=a;
//	    a=b;
//		b=t;
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sum(10,20);
		sum(30,40);
		int a=10;
		int b=20;
		int t;
		t=a;
		a=b;
		b=t;
		
		System.out.println("a="+a+";b="+b);
	}

}
