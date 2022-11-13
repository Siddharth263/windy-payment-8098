package com.bank.dao;

import com.bank.exceptions.BankException;

public interface Bank {
	void login() throws BankException;
	boolean register();
}
