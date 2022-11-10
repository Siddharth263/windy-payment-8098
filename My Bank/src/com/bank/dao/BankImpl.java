package com.bank.dao;

import java.util.Scanner;
import com.bank.exceptions.BankException;
import com.bank.model.customers;

public class BankImpl implements Bank{

	Scanner sc = new Scanner(System.in);
	Management m = new ManagementImpl();
	@Override
	public boolean login() throws BankException {

		System.out.println("Enter your email: ");
		String email = sc.next();
		
		System.out.println("Enter your password: ");
		String password = sc.next();
		
		try {
			customers c = m.authenticate(email, password);
			System.out.println("Welcome "+c.getcName()+" to K Finances!");
			return true;
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
			register();
		}
		else {
			customers c = new customers(name, 0, password, email);
			if(m.addCustomer(c)) return true;
		}
		return false;
	}

}
