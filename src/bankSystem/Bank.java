package bankSystem;

import java.util.ArrayList;

public class Bank {
	ArrayList<Account> accounts = new ArrayList<Account>();
	
	public int createAccount(double initialValue, Person personalData){
		int accID = accounts.size();
		
		Account newAcc = new Account(accID, personalData);
		
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).contains(newAcc)) {
				System.out.println("Account creation error: Existing Account");
				return 1;
			}
		}

		System.out.println("Account "+accID+" created");
		if(initialValue > 0)
			newAcc.deposit(initialValue);
		accounts.add(newAcc);
		return accID;
	}
	
	public void transaction(int accID1, int accID2, double value){
		Account acc1 = null;
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).contains(accID1)) {
				acc1 = accounts.get(i);
			}
		}
		if(acc1 == null) {
			System.out.println("Transaction error: Account not exist");
			return;
		}
		
		Account acc2 = null;
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).contains(accID2)) {
				acc2 = accounts.get(i);
			}
		}
		if(acc2 == null) {
			System.out.println("Transaction error: Account not exist");
			return;
		}
		
		if(acc1.getValue() < value) {
			System.out.println("Transaction error: Insufficient funds");
			return;
		}
		
		acc2.receiveTransact(acc1.getID(), acc1.sendTransact(acc2.getID(), value));
	}

	public double accountBalance(int accID) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).contains(accID)) {
				System.out.println("Acc"+accID+" balance: $ "+accounts.get(i).getValue());
				return accounts.get(i).getValue();
			}
		}

		System.out.println("Transaction error: Account not exist");
		return 0;
	}
	
	public void withdraw(int accID, double value) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).contains(accID)) {
				accounts.get(i).withdraw(value);
			}
		}
	}
	
	public void deposit(int accID, double value) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).contains(accID)) {
				accounts.get(i).withdraw(value);
			}
		}
	}
	
	public Account getAccount(Person personalData) {
		Account acc = new Account(-1,personalData);
				
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).contains(acc)) {
				return accounts.get(i);
			}
		}
		
		System.out.println("Get Account: Account not found");
		return null;
	}
}
