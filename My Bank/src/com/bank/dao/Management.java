package com.bank.dao;

import com.bank.exceptions.BankException;
import com.bank.model.customers;

public interface Management {
	void authenticate(String email, String password) throws BankException;
	boolean addCustomer(customers c);
	void transferMoney(String rName, customers c, int amount) throws BankException;
	void checkBalance(customers c) throws BankException;
	boolean addMoney(customers c, int amount) throws BankException;
	void logout();
	void showOption(customers c);
}
