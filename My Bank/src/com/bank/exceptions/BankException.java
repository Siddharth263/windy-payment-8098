package com.bank.exceptions;

public class BankException extends Exception{

	public BankException() {
		super();
	}

	public BankException(String message) {
		System.out.println(message);
	}
}
