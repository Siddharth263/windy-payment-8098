package com.bank.dao;

import com.bank.exceptions.BankException;

public interface Bank {
	void login() throws BankException;
	void register();
	/*
	 * new method ---> 
	 * showOptions(){
	 * 		System.out.println("1. show balance\n 2. transfer money\n 3. add money\n 4. exit/log out");
	 * 		int o = sc.nextInt();
	 * 		switch(c){
	 * 			case 1: m.showBalance();
	 * 					break;
	 * 			case 2: m.transferMoney();
	 * 					break;
	 * 			case 3: m.addMoney();
	 * 					break;
	 * 			case 4: m.logOut();
	 * 					break;
	 * 			default: System.out.println("Invalid Input"); 
	 * 		}
	 * }
	 * 
	 * show options:
	 * 1. show balance
	 * 2. transfer money
	 * 3. add money 
	 * 4. exit/log out
	 * 
	 */
}
