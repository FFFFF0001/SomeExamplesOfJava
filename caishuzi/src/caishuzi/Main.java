package caishuzi;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
	    int number=(int)(Math.random()*100+1);
	    int a;
	    int count=0;
	    do{
	    	a=in.nextInt();
	    	count++;
	    	if(a>number)
	    	{
	    		System.out.println("caidale");
	    	}
	    	else if(a<number)
	    	{
	    		System.out.println("caixiaole");
	    		
	    	}
	    }while(a!=number);
	    System.out.println("duile! caile "+count+"ci");
	}

}
