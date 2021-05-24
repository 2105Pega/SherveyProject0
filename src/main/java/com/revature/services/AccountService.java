package com.revature.services;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bank.Account;
import com.revature.bank.AccountStatus;
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOIMP;

public class AccountService {

	private AccountDAO aD = new AccountDAOIMP();
	private static final Logger logger = LogManager.getLogger(AccountService.class);
	
	public Account getAccountByID(int id)
	{
		try {
			aD.getAccountByID(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	public boolean addAccount(Account a)
	{
		try {
			if(a == null)
			{
				throw new NullPointerException("Attempted to add Null Account.");
			}
		
			aD.addAccount(a);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}
	
	public boolean removeAccountByID(int id)
	{
	try {
			aD.removeAccount(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	
	public boolean removeAccounts()
	{
	try {
				aD.removeAccounts();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}
	
	public ArrayList<Account> getAccountsByOwnerID(int id)
	{
	try {
			aD.getAccountsByOwnerID(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public ArrayList<Account> getAccountsByCoOwnerID(int id)
	{
	try {
			aD.getAccountsByCoOwnerID(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public ArrayList<Account> getAllAccounts()
	{
	try {
			aD.getAllAccounts();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public boolean isCoOwned(int clientID, int accountID)
	{
		try {
			return aD.isCoOwned(clientID, accountID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean withdraw(int id, double ammountChange)
	{
		try {
			Account a = aD.getAccountByID(id);
			
			
			if(a == null)
			{
				throw new NullPointerException("Account is not within the system.");
			}
			else if(a.getStatus() == AccountStatus.DENIED || a.getStatus() == AccountStatus.CANCELED || a.getStatus() == AccountStatus.PENDING)
			{
				throw new IllegalStateException("Target Account Status is " + a.getStatus() + " Transfers are not allowed with this status.");
			}
			else if(ammountChange < 0)
			{
				throw new IllegalArgumentException("Cannot deposit negative Funds.");
			}
			else if(a.getBalance() < ammountChange)
			{
				throw new IllegalStateException("Cannot Overdraw funds from Account.");
			}
				return aD.withdraw(id, ammountChange);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deposit(int id, double ammountChange)
	{
		try {
			Account a = aD.getAccountByID(id);
			
			if(a == null)
			{
				throw new NullPointerException("Account is not within the system.");
			}
			else if(a.getStatus() == AccountStatus.DENIED || a.getStatus() == AccountStatus.CANCELED || a.getStatus() == AccountStatus.PENDING)
			{
				throw new IllegalStateException("Target Account Status is " + a.getStatus() + " Transfers are not allowed with this status.");
			}
			else if(ammountChange < 0)
			{
				throw new IllegalArgumentException("Cannot deposit negative Funds.");
			}
		
		
			return aD.deposit(id, ammountChange);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return false;
	}
	
	public boolean transfer(int senderID, int targetID, double ammount)
	{
		
		try {
		Account target = aD.getAccountByID(targetID);
		Account sender = aD.getAccountByID(senderID);
		
		if(ammount < 0)
		{
			throw new IllegalArgumentException("Cannot transfer negative Funds.");
		}
		else if(target == null)
		{
			throw new NullPointerException("Target Account is not within the system.");
		}
		else if(sender == null)
		{
			throw new NullPointerException("Sending Account is not within the system.");
		}
		else if(aD.getAccountByID(senderID).getBalance() < ammount)
		{
			throw new IllegalArgumentException("Cannot withdraw more funds then are available in account.");
		}
		else if(sender.getStatus() == AccountStatus.DENIED || sender.getStatus() == AccountStatus.CANCELED || sender.getStatus() == AccountStatus.PENDING)
		{
			throw new IllegalStateException("Target Account Status is " + target.getStatus() + " Transfers are not allowed with this status.");
		}
		else if(target.getStatus() == AccountStatus.DENIED || target.getStatus() == AccountStatus.CANCELED || target.getStatus() == AccountStatus.PENDING)
		{
			throw new IllegalStateException("Target Account Status is " + target.getStatus() + " Transfers are not allowed with this status.");
		}
			return aD.transfer(senderID, targetID, ammount);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	public boolean addCoOwner(int aID, int coID)
	{
		return aD.addCoOwner(aID, coID);
	}
}
