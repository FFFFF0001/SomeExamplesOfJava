package employee;
import java.util.Date;
import java.util.GregorianCalendar;
public class Employee {
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee(String n,double s,int year,int month,int day)
	{
		name=n;
		salary=s;
		GregorianCalendar calendar=new GregorianCalendar(year,month-1,day);
		//GregorianCalendar uses 0 for January
		hireDay=calendar.getTime();
		
	}
	public String getName(){
		return name;
	}
	public double getSalary()
	{
		return salary;
		
	}
	public Date getHireDay(){
		return hireDay;
		
	}
	
	public void raiseSalary(double byPercent){
		double raise=salary*byPercent/100;
		salary+=raise;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
