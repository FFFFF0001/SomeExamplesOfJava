package inheritance;



public class ManagerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager boss= new Manager("Bystc",80000,1996,10,10);
		boss.setBonus(5000);
		Employee[]staff=new Employee[3];
		staff[0]=boss;
		staff[1]=new Employee("jzy", 55000, 1997, 4, 15);
		staff[2]=new Employee("zyc", 25000, 1996, 8, 7);
		
		for(Employee e:staff)
			System.out.println("name="+e.getName()+" "+"salary="+e.getSalary());
	}

}
