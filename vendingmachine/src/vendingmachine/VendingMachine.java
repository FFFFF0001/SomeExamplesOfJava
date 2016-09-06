package vendingmachine;

public class VendingMachine {
	int price=50;
	int balance;
	int total;
	void setPrice(){
		this.price=price;
		this.getFood();
	}
	void showPrompt(){
		System.out.println("Welcome!");
	}
	void insertMoney(int amount){
		balance=balance+amount;
		showBalance();
		
	}
	void showBalance(){
		System.out.println(balance);
	}
	void getFood()
	{
		if(balance>=price){
		System.out.println("Here you are");
		balance=balance-price;
		total=total+price;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VendingMachine vm=new VendingMachine();
		vm.showPrompt();
		vm.showBalance();
		
		vm.insertMoney(100);
		vm.getFood();
		vm.showBalance();
	}

}
