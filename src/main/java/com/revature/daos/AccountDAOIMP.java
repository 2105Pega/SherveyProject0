package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.reavture.utils.ConnectionUtils;
import com.revature.bank.Account;
import com.revature.bank.AccountStatus;

public class AccountDAOIMP implements AccountDAO{

	@Override
	public boolean addAccount(Account a) {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "insert into accounts (account_name, owner_id, balance, current_status) values (?, ?, ?, cast (? as status));";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, a.getAccountName());
			statement.setInt(2, a.getOwnerID());
			statement.setFloat(3, (float) a.getBalance());
			statement.setString(4, a.getStatus().toString());
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeAccount(int id) {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "delete from accounts where account_id =?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeAccounts() {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "delete from accounts where current_status = 'CANCELED' or current_status = 'DENIED';";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account getAccountByID(int id) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
			String sql = "select * from accounts where account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet r = statement.executeQuery();
			
			Account a = makeAccount(r);
			return a;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Account> getAccountsByCoOwnerID(int id) {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "select * from accounts a inner join co_owners co on co.account_id = a.account_id where co.co_owner_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet r = statement.executeQuery();
			
			ArrayList<Account> aList = new ArrayList<Account>();
			
			while(r.next())
			{
				Account a = makeAccount(r);
				aList.add(a);
				
			}
			return aList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ArrayList<Account> getAccountsByOwnerID(int id) {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "select * from accounts where owner_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet r = statement.executeQuery();
			
			ArrayList<Account> aList = new ArrayList<Account>();
			
			while(r.next())
			{
				Account a = makeAccount(r);
				aList.add(a);
				
			}
			return aList;
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public ArrayList<Account> getAllAccounts() {
		try (Connection conn = ConnectionUtils.getConnection()){
			
			String sql = "select * from accounts;";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			ArrayList<Account> aList = new ArrayList<Account>();
			
			while(r.next())
			{
				Account a = makeAccount(r);
				aList.add(a);				
			}
			return aList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean withdraw(int id, double ammountChange) {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "update accounts set balance = balance + ? where account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setFloat(1, (float) ammountChange);
			statement.setInt(2, id);
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deposit(int id, double ammountChange) {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "update accounts set balance = balance - ? where account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setFloat(1, (float) ammountChange);
			statement.setInt(2, id);
			statement.execute();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean transfer(int senderID, int targetID, double ammount) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
			String sql = "update accounts set balance = balance - ? where account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setFloat(1, (float) ammount);
			statement.setInt(2, senderID);
			statement.execute();
			
			sql = "update accounts set balance = balance + ? where account_id = ?;";
			statement = conn.prepareStatement(sql);
			
			statement.setFloat(1, (float) ammount);
			statement.setInt(2, targetID);
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private Account makeAccount(ResultSet r) throws SQLException
	{
		AccountStatus as;
		
		as = AccountStatus.valueOf(r.getString("account_status"));
		
		return new Account(r.getString("account_name"), as, (double) r.getFloat("balance"),r.getInt("account_id") );
	}

}
