package interfaces;

//import java.util.Arrays;
import java.util.*;
public class EmployeeSortTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee[]staff=new Employee[3];
		staff[0]=new Employee("bystc",50000);
		staff[1]=new Employee("jzystc",30000);
		staff[2]=new Employee("zycstc",20000);
		
		Arrays.sort(staff);
		
		for(Employee e:staff)
		System.out.println("name"+e.getName()+",salary="+e.getSalary());
	}

}
