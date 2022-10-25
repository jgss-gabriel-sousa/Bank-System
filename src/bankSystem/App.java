package bankSystem;

import dbConnection.DBConnection;

public class App {	
	private Bank bank = new Bank();
	
	public App() {
		int acc1_ID = bank.createAccount(0, new Person("Gabriel","Sousa",'m',20));
		int acc2_ID = bank.createAccount(500.0, new Person("Jose","Silva",'m',40));
		@SuppressWarnings("unused")
		int acc1_ID2 = bank.createAccount(500.0, new Person("Gabriel","Sousa",'m',20));
		
		//Deposit
		Account jose = bank.getAccount(new Person("Jose","Silva",'m',40));
		//jose.deposit(50);
		bank.deposit(acc2_ID, 50);
		
		bank.transaction(acc1_ID, acc2_ID, 50);
		bank.transaction(acc2_ID, acc1_ID, 10);
		
		bank.accountBalance(acc1_ID);
		bank.accountBalance(acc2_ID);
		
		DBConnection db = new DBConnection();
		
		try {
			db.connect();
			System.out.println("Connected to SQLite");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		db.disconnect();
	}
	
	public static void main(String[] args) {
		new App();
	}

}
