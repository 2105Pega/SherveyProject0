package com.revature.daos;

import java.util.ArrayList;
import java.util.UUID;

import com.revature.bank.Account;

public interface AccountDAO {

	public boolean addAccount(Account a); 
	
	public boolean removeAccount(int id);
	
	public boolean removeAccounts();
	
	public Account getAccountByID(int id);
	
	public ArrayList<Account> getAccountsByCoOwnerID (int id);
	
	public ArrayList<Account> getAccountsByOwnerID(int ID);
	
	
	public ArrayList<Account> getAllAccounts();
	
	public boolean withdraw(int id, double ammountChange);
	
	public boolean deposit(int id, double ammountChange);
	
	public boolean transfer(int senderID, int targetID, double ammount);

	public boolean isCoOwned(int clientID, int accountID);
	
	public boolean addCoOwner(int aID, int coID);

}
