package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.connection.DBUtil;
import com.bank.exceptions.BankException;
import com.bank.model.customers;

public class ManagementImpl implements Management{

	@Override
	public customers authenticate(String email, String password) throws BankException {
		customers c = null;
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from customers where email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs != null) {
				c = new customers(rs.getString("cname"), rs.getInt("balance"), rs.getString("password"), rs.getString("email"));
			}
			else throw new BankException("Invalid Credentials!!!");
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return c;
	}

	@Override
	public boolean addCustomer(customers c){
			
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into customers(cname,balance,password,email) values(?,?,?,?)");
			ps.setString(1, c.getcName());
			ps.setInt(2, c.getBalance());
			ps.setString(3, c.getPassword());
			ps.setString(4, c.getEmail());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("Customer added succesfully!!");
				return true;
			}
			else {
				System.out.println("Customer could not be added!!!");
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public boolean transferMoney(String rName, customers c, int amount) throws BankException {
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select balance from customers where email=? ");
			ps.setString(1, c.getEmail());
			
			ResultSet rs = ps.executeQuery();
			
			int prev_balance = rs.getInt("balance");
			
			if(prev_balance==0 || prev_balance<amount) {
				throw new BankException("Insufficient Balance!!");
			}
			else {
				int new_balance = prev_balance-amount;
				c.setBalance(new_balance);
				
				PreparedStatement ps2 = conn.prepareStatement("update customers set balance=? where email=?");
				ps2.setInt(1, c.getBalance());
				ps2.setString(2, c.getEmail());
				int x = ps2.executeUpdate();
				
				if(x>0) return true;
			}		
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public void checkBalance(customers c) throws BankException {
		System.out.println("Your current balance is: "+c.getBalance());
	}

	@Override
	public boolean addMoney(customers c, int amount) throws BankException {
		
		try(Connection conn = DBUtil.provideConnection()) {
			c.setBalance(c.getBalance()+amount);
			
			PreparedStatement ps = conn.prepareStatement("update customers set balance=? where email=?");
			ps.setInt(1, c.getBalance());
			ps.setString(2, c.getEmail());
			
			int x = ps.executeUpdate();
			if(x>0) return true;		
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

}
