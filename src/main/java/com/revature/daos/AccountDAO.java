package com.revature.daos;

import java.util.ArrayList;
import java.util.UUID;

import com.revature.bank.Account;

public interface AccountDAO {

	public boolean addAccount(Account a); 
	
	public boolean removeAccount(UUID id);
	
	public void removeAccounts();
	
	public Account getAccountByID(UUID id);
	
	public ArrayList<Account> getAccountsByCoOwnerID (UUID id);
	
	public ArrayList<Account> getAccountsByOwnerID(UUID ID);
	
	public ArrayList<Account> getAllAccounts();
	
	public double withdraw(UUID id, double ammountChange);
	
	public double deposit(UUID id, double ammountChange);
	
	public void transfer(UUID senderID, UUID targetID, double ammount);
}
