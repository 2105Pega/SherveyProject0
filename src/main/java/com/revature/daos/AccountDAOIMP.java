package com.revature.daos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.UUID;

import com.reavture.utils.ConnectionUtils;
import com.revature.bank.Account;

public class AccountDAOIMP implements AccountDAO{

	@Override
	public boolean addAccount(Account a) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean removeAccount(UUID id) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public void removeAccounts() {
		try (Connection cconn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public Account getAccountByID(UUID id) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ArrayList<Account> getAccountsByCoOwnerID(UUID id) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ArrayList<Account> getAccountsByOwnerID(UUID ID) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ArrayList<Account> getAllAccounts() {
		try (Connection conn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public double withdraw(UUID id, double ammountChange) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public double deposit(UUID id, double ammountChange) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public void transfer(UUID senderID, UUID targetID, double ammount) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
