package pingjunshu;

import java.util.Scanner;

public class Pingjunshu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int count;
		count=0;
		
		int sum=0;
		int number=in.nextInt();
		while(number!=-1)
		{
			sum=sum+number;
			count++;
			number=in.nextInt();
			
		}
		if(count>0)
		{
			System.out.println("Æ½¾ùÊı£º"+(double)sum/count);
		}
	}

}
