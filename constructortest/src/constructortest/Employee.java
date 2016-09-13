package constructortest;

import java.util.Random;

public class Employee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	private static int nextId;
	private int id;
	private String name="";
	private double salary;
	
	
	static{
		Random generator=new Random();
		nextId=generator.nextInt(10000);//set nextId to a random number between 0 and 9999
		
	}
	//object initialization block
	{
		id=nextId;
		nextId++;
	}
	public Employee(String n,double s){
		name=n;
		salary=s;
	}
	public Employee(double s){
		this("Employee #"+nextId,s);
	}
	public Employee(){
		//name initialiazed to ""--see above
		//salary not explicitly set--initialized to 0
		//id initialized in initialization block
	}
	public String getName(){
		return name;
	}
	public double getSalary(){
		return salary;
	}
	public int getId(){
		return id;
	}

}
