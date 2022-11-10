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
		int c = sc.nextInt();
		
		Bank b = new BankImpl();
		
		try {
			switch(c) {
			case 1: {
				b.login();
				break;
			}
			case 2: {
				b.register();
				Main m = new Main();
				m.main(null);
				break;
			}
			default: {
					System.out.println("Invalid selection, please try again!!");
					Main m = new Main();
					m.main(null);
				}
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}		
	}
}
