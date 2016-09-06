package 数数字;

import java.util.Scanner;

public class 数数字 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int number=in.nextInt();
		int count=0;
		while(number>0)
		{
			number=number/10;
			count++;
		}	
		
		System.out.println(count);
	}

}
