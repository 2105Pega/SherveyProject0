package com.revature.daos;

import java.util.ArrayList;
import com.revature.bank.Account;
import com.revature.bank.AccountStatus;

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
	
	public boolean updateStatus(AccountStatus s, int id);

}
