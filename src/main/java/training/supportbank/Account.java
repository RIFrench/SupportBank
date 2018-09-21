package training.supportbank;

import java.util.ArrayList;

public class Account {
	
	public String name;
	public double balance;
	public ArrayList<Transaction> transactionHistory;
	public Transaction tempTransaction;
		
	public Account(String name_in, double balance_in) { // constructors
		name = name_in;
		balance  = balance_in;
		transactionHistory = new ArrayList<>();
	}
	
	public Account(String name_in) { // constructors
		name = name_in;
		balance  = 0;
		transactionHistory = new ArrayList<>();
	}
	
//	public String ToString() { //methods
//		return name + " " + balance;
//	}
	
	public void initialise(String name_in, double d) {
		name = name_in;
		balance  = d;
		transactionHistory = new ArrayList<>();
	} 
	
	void addTransaction(String date, String narrative, float amount) {
		tempTransaction.populate(date,narrative,amount);
		transactionHistory.add(tempTransaction);
	}
	
	void updateBalance(double amount) {
		balance += amount;
	}
}
