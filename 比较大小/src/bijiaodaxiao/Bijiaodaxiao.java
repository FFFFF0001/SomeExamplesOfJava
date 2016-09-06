package bijiaodaxiao;

import java.util.Scanner;

public class Bijiaodaxiao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int x,y,z;
		while(true){
		System.out.println("Input three numbes:");
		x=in.nextInt();
		y=in.nextInt();
		z=in.nextInt();
		
		int max=0;
		if(x>y)
		{
			if(x>z){
				max=x;
				}
			else{
				max=z;
			}
			
		}
		else
		{
			if(y>z){
			max=y;}
			
			else{
				max=z;
			}
		}
		System.out.println(max);
		}
		
	}

}
