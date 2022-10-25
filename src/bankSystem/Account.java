package bankSystem;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Account {	
	private int id;
	private Person personalData;
	private double value;
	LocalDateTime accountCreation;
	
	private String[] log = {};
	
	public Account(int id, Person personalData) {
		this.id = id;
		this.personalData = personalData;
		accountCreation = java.time.LocalDateTime.now();
	}
	
	private void addValue(double value) {
		if(value <= 0) return;
		
		this.value += value;
	}
	
	private double subValue(double value) {
		if(value <= 0)
			value = 0;
		
		if(value > this.value)
			value = this.value;
		
		this.value -= value;
		return value;
	}
	
	public void withdraw(double value) {
		subValue(value);
		logOperation("withdraw", value);
	}
	
	public void deposit(double value) {
		addValue(value);
		logOperation("deposit", value);
	}
	
	public void receiveTransact(int from, double value) {
		addValue(value);
		logTransaction("receive-transact", from, value);
	}
	
	public double sendTransact(int to, double value) {
		value = subValue(value);
		logTransaction("send-transact", to, value);
		return value;
	}
	
	private void logTransaction(String type, int otherAcc, double value) {	
		log = Arrays.copyOf(log, log.length + 1);
		
		if(type == "receive-transact")
			log[log.length - 1] = "Acc"+id+": Transaction Received of $ "+value+" from Acc"+otherAcc+" at ("+java.time.LocalDateTime.now()+")";
		if(type == "send-transact")
			log[log.length - 1] = "Acc"+id+": Transaction Sended of $ "+value+" to Acc"+otherAcc+" at ("+java.time.LocalDateTime.now()+")";

		printLastLog();
	}
	
	private void logOperation(String type, double value) {		
		log = Arrays.copyOf(log, log.length + 1);
			
		switch(type) {
			case "deposit":
				log[log.length - 1] = "Acc"+id+": Deposit of $ "+value+" at ("+java.time.LocalDateTime.now()+")";
				break;
			case "withdraw":
				log[log.length - 1] = "Acc"+id+": Withdraw of $ "+value+" at ("+java.time.LocalDateTime.now()+")";
				break;
		}	
		
		printLastLog();
	}

	public void printLastLog() {
		System.out.println(log[log.length - 1]);
	}
	
	public double getValue() {
		return value;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean contains(Account acc) {
		if(acc.personalData.getUniqueID() == this.personalData.getUniqueID()) {
			return true;
		}
		return false;
	}

	public boolean contains(int id) {
		if(id == this.id) {
			return true;
		}
		return false;
	}
}
