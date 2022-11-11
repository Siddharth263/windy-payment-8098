package com.bank.model;

public class customers {
	private String cName;
	private int balance;
	private String password;
	private String email;
	public customers() {
		super();
	}
	public customers(String cName, int balance, String password, String email) {
		super();
		this.cName = cName;
		this.balance = balance;
		this.password = password;
		this.email = email;
	}
	@Override
	public String toString() {
		return "customers [cName=" + cName +"]";
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
