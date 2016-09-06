package employee;

public class EmployeeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee[]staff=new Employee[3];
		staff[0]=new Employee("by", 75000, 1996, 10, 10);
		staff[1]=new Employee("jzy", 55000, 1997, 4, 15);
		staff[2]=new Employee("zyc", 25000, 1996, 8, 7);
		for(Employee e:staff)
			e.raiseSalary(10);
		for(Employee e:staff)
			System.out.println("name="+e.getName()+"salary="+e.getSalary()+",hireDay="+e.getHireDay());
	}

}
