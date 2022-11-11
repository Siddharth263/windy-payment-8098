package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.bank.connection.DBUtil;
import com.bank.exceptions.BankException;
import com.bank.model.customers;

public class ManagementImpl implements Management{
	
	Scanner sc = new Scanner(System.in);

	@Override
	public void authenticate(String email, String password) throws BankException {
		customers c = null;
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from customers where email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				c = new customers(rs.getString("cname"), rs.getInt("balance"), rs.getString("password"), rs.getString("email"));
				System.out.println("Welcome "+c.getcName()+" to K Finances!");
				showOption(c);
			}	
			
		} catch (SQLException e) {
			System.out.println("at line 40 "+e.getMessage());
		}
	}

	@Override
	public boolean addCustomer(customers c){
			
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into customers(cname,balance,password,email) values(?,?,?,?)");
			ps.setString(1, c.getcName());
			ps.setInt(2, c.getBalance());
			ps.setString(3, c.getPassword());
			ps.setString(4, c.getEmail());
			
			int rs = ps.executeUpdate();
			
			if(rs>0) {
				System.out.println("Account created succesfully, please login to your account to continue.");
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public void transferMoney(String rName, customers c, int amount) throws BankException {
		
		try(Connection conn = DBUtil.provideConnection()) {
						
			if(c.getBalance()<amount) {
				System.out.println("Insufficient Funds");
				showOption(c);
			}
			else {
				
				c.setBalance(c.getBalance()-amount);
				
				PreparedStatement ps = conn.prepareStatement("update customers SET balance=? where email=?");
				ps.setInt(1, c.getBalance());
				ps.setString(2, c.getEmail());
				
				if(ps.executeUpdate()>0) System.out.println("Money Successfully transferred to "+rName+". New Updated Balance is: "+c.getBalance());
				else System.out.println("ps.executeUpdate(): "+ps.executeUpdate());
				showOption(c);
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void checkBalance(customers c) throws BankException {
		System.out.println("Your current balance is: "+c.getBalance());
		showOption(c);
	}

	@Override
	public boolean addMoney(customers c, int amount) throws BankException {
		
		try(Connection conn = DBUtil.provideConnection()) {
			c.setBalance(c.getBalance()+amount);
			
			PreparedStatement ps = conn.prepareStatement("update customers set balance=? where email=?");
			ps.setInt(1, c.getBalance());
			ps.setString(2, c.getEmail());
			
			int x = ps.executeUpdate();
			if(x>0) {
				System.out.println("Your new balance is: "+c.getBalance());
				showOption(c);
				return true;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public void logout() {
		System.out.println("You have successfully logged out. Thank you for using K - Finances services. We hope to see you again.");
	}

	@Override
	public void showOption(customers c) {
		System.out.println(" 1. show balance\n 2. transfer money\n 3. add money\n 4. exit/log out");
  		int o = sc.nextInt();
  		try {
  			switch(o){
  			case 1: checkBalance(c);
  					break;
  			case 2: {
  				System.out.println("Enter the receiver's name: ");
  				String rName = sc.next();
  				System.out.println("Enter amount: ");
  				int amount = sc.nextInt();
  				transferMoney(rName, c, amount);
  				break;
  			}
  			case 3: {
  				System.out.println("Enter the amount: ");
  				int amount = sc.nextInt();
  				addMoney(c, amount);
				break;
  			}
  			case 4: logout();
  					break;
  			default: {
  				System.out.println("Invalid Input");
  				showOption(c);
  			}
  		}
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

}
