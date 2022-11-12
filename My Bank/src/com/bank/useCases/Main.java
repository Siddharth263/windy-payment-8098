package com.bank.useCases;

import java.util.Scanner;

import com.bank.dao.Bank;
import com.bank.dao.BankImpl;
import com.bank.exceptions.BankException;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws BankException {
		
		System.out.println("Welcome to K Finances!!!!");
		System.out.println("1. Login to your Account");
		System.out.println("2. Create new Account");
		System.out.println("5. Exit");
		System.out.println("Enter your input: ");
		int c = sc.nextInt();
		System.out.println("==========================");
		System.out.println();
		
		Bank b = new BankImpl();
		
		try {
			switch(c) {
			case 1: b.login();
					break;
			case 2: if(b.register()) {
				System.out.println("==========================");
				System.out.println();
				Main m = new Main();
				m.main(null);
			}
					break;
			case 5: System.out.println("Thank you for using K-Finances services. We hope to see you again");
					System.out.println("==========================");
					System.out.println();
			  		break;
			default: System.out.println("Invalid selection, please try again!!");
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}
	}
}
