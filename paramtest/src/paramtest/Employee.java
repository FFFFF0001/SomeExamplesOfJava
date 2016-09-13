package paramtest;

public class Employee {

	
		// TODO Auto-generated method stub
		private String name;
		private double salary;
		public Employee(String n,double s){
			name=n;
			salary=s;
			
		}
		public String getName(){
			return name;
		}
		public double getSalary(){
			return salary;
		}
		public void raiseSalary(double byPercent){
			double raise =salary*byPercent;
			salary+=raise;
		}
	

}
