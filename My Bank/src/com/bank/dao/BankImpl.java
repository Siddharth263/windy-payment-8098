package com.bank.dao;

import java.util.Scanner;
import com.bank.exceptions.BankException;
import com.bank.model.customers;

public class BankImpl implements Bank{

	Management m = new ManagementImpl();
	Scanner sc = new Scanner(System.in);
	
	@Override
	public void login() throws BankException {

		System.out.println("Enter your email: ");
		String email = sc.next();
		
		System.out.println("Enter your password: ");
		String password = sc.next();
		
		System.out.println();
		System.out.println("==========================");
		
		try {
			m.authenticate(email, password);
		} catch (BankException e) {
			throw new BankException("Invalid Credentials, please try again");
		}
	}

	@Override
	public boolean register() {
		System.out.println("Enter your name: ");
		String name = sc.next();
		
		System.out.println("Enter your email: ");
		String email = sc.next();
		
		System.out.println("Enter your password: ");
		String password = sc.next();
		System.out.println("Confirm your password: ");
		String cPassword = sc.next();
		if(!password.equals(cPassword)) {
			System.out.println("Passwords don't match please try again!");
			System.out.println();
			System.out.println("==========================");
			register();
		}
		else {
			customers c = new customers(name, 0, password, email);
			System.out.println();
			System.out.println("==========================");
			m.addCustomer(c);
			return true;
		}
		return false;
	}
}
