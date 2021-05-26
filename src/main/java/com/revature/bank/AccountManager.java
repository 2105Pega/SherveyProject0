package com.revature.bank;
/*
import java.util.ArrayList;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
*/
public class AccountManager {
/*
	private static ArrayList<Account> accountList;
	private static final Logger logger = LogManager.getLogger(AccountManager.class);
	
	public AccountManager(ArrayList<Account> accountList)
	{
		AccountManager.accountList = accountList;
	}
	
	public void addAccount(Account a)
	{
		if(a == null)
		{
			logger.error("Null Pointer Exception Occured in AccountManager.addAccount");
			throw new NullPointerException();
		}
		else if(this.getAccountByID(a.getACCOUNT_ID()) != null)
		{
			logger.error("Illegal Argument Exception Occured in AccountManager.addAccount");
			throw new IllegalArgumentException();
		}
		logger.info("Added Account " + a.toString());
		accountList.add(a);
	}

	public void removeAccount(UUID id)
	{
		this.getAccountByID(id).setStatus(AccountStatus.CANCELED);
		this.removeAccounts();
	}
	
	public void removeAccounts()
	{
		ArrayList<Account> toRemove = new ArrayList<Account>();
		
		for(Account a : accountList)
		{
			if(a.getStatus() == AccountStatus.CANCELED || a.getStatus() == AccountStatus.DENIED)
			{
				toRemove.add(a);
				logger.info("Removing account " + a.toString());
			}
		}
		accountList.removeAll(toRemove);
	}
	
	public Account getAccountByID(UUID u)
	{
		if(u == null)
		{	
			logger.error("Null Pointer Exception Occured in AccountManager.getAccountByAccountID");
			throw new NullPointerException();
		}
		
		for(Account a : accountList)
		{
			if(a.getACCOUNT_ID().equals(u))
			{
				return a;
			}
		}
		return null;
	}

	public ArrayList<Account> getAccountsByCoOwnerID(UUID u)
	{
		if(u == null)
		{
			logger.error("Null Pointer Exception Occured in AccountManager.getAccountsByCoOwnerID");
			throw new NullPointerException();
		}
		
		ArrayList<Account> aL = new ArrayList<Account>();
		for(Account a : accountList)
		{
			for(UUID cU : a.getCoOwnerIDs())
			{
				if(cU.equals(u));
					{
						aL.add(a);
					}
			}
		}
		return aL;
	}

	public ArrayList<Account> getAccountsByOwnerID(UUID u)
	{
		if(u == null)
		{
			logger.error("Null Pointer Exception Occured in AccountManager.getAccountsByOwnerID");
			throw new NullPointerException();
		}
		
		ArrayList<Account> aL = new ArrayList<Account>();
		for(Account a : accountList)
		{
			if(a.getOwnerID().equals(u))
			{
				aL.add(a);
			}
		}
		return aL;
	}

	public ArrayList<Account> getAllAccounts()
	{
		return accountList;
	}
	
	public String accountSummary(Account a) {
		String s = String.format(a.getAccountName() + ", Balance: " );
		s += String.format("%.2f, ", a.getBalance());
		s += String.format("Status: " + a.getStatus() + ", ID: " + a.getACCOUNT_ID());
		s += "\n------------------------------------------------\n";
		return s;
	}
	
	public String accountsSummary(UUID ClientID) 
	{
		String s = "Accounts you own:\n";
		for(Account a : accountList)
		{
			if(a.getOwnerID().equals(ClientID))
			{
				s += accountSummary(a);
			
			}
		}
		s += "Accounts you co-own:\n";
		for(Account a : accountList)
		{
			for(UUID u : a.getCoOwnerIDs())
			{
				if(u.equals(ClientID))
				{
					s += accountSummary(a);
				}
			}
		}
		return s;
	}

	public double withdraw(Account a, double ammountChange)
	{
		if(ammountChange <= 0)
		{
			logger.error("IllegalArgumentException Occured in AccountManager.withdraw");
			throw new IllegalArgumentException("Cannot Withdraw Negative or Zero Funds");
		}
		else if(a == null)
		{
			logger.error("NullPointerException Occured in AccountManager.withdraw");
			throw new NullPointerException();
		}
		else if(a.getStatus() != AccountStatus.APPROVED)
		{
			logger.error("IllegalStateException Occured in AccountManager.withdraw");
			throw new IllegalStateException("Account Not Approved.");
		}
		
		a.setBalance((a.getBalance() - ammountChange));
		
		logger.info("Withdrew " + ammountChange + " to " + a.getAccountName() + ", ID: " + a.getACCOUNT_ID());
		
		return a.getBalance();
	}
	
	public double deposit(Account a, double ammountChange)
	{
		if(ammountChange <= 0)
		{
			logger.error("IllegalArgumentException Occured in AccountManager.deposit");
			throw new IllegalArgumentException("Cannot Withdraw Negative or Zero Funds");
		}
		else if(a == null)
		{
			logger.error("NullPointerException Occured in AccountManager.deposit");
			throw new NullPointerException();
		}
		else if(a.getStatus() != AccountStatus.APPROVED)
		{
			logger.error("IllegalStateException Occured in AccountManager.deposit");
			throw new IllegalStateException("Account Not Approved.");
		}
		
		a.setBalance((a.getBalance() + ammountChange));
		
		logger.info("Deposited " + ammountChange + " to " + a.getAccountName() + ", ID: " + a.getACCOUNT_ID());
		
		return a.getBalance();
	}
	
	public void transfer(Account sender, Account target, double ammount)
	{
		if(ammount <= 0)
		{
			logger.error("IllegalArgumentException Occured in AccountManager.transfer");
			throw new IllegalArgumentException("Cannot Transfer Negative or Zero Funds");
		}
		else if(sender == null)
		{
			logger.error("NullPointerException Occured in AccountManager.transfer");
			throw new NullPointerException("Cannot Transfer from sending Account. ID Not Found in System");
		}
		else if(target == null) 
		{
			logger.error("NullPointerException Occured in AccountManager.transfer");
			throw new NullPointerException("Cannot Transfer to target Account. ID Not Found in System");
		}
		else if(target.getACCOUNT_ID() == sender.getACCOUNT_ID())
		{
			logger.error("IllegalArgumentException Occured in AccountManager.transfer");
			throw new IllegalArgumentException("Cannot Transfer To Same Account");
		}
		else if(sender.getStatus() != AccountStatus.APPROVED)
		{
			logger.error("IllegalStateException Occured in AccountManager.transfer");
			throw new IllegalStateException("Target Account Not Approved.");
		}
		else if(target.getStatus() != AccountStatus.APPROVED)
		{
			logger.error("IllegalStateException Occured in AccountManager.transfer");
			throw new IllegalStateException("Sending Account Not Approved.");
		}
		
		this.withdraw(sender, ammount);
		this.deposit(target, ammount);
		
		logger.info("Transfered " + ammount + " from " + sender.getAccountName() + ", ID: " + sender.getACCOUNT_ID() + ", to " + target.getAccountName() + ", ID: " + target.getACCOUNT_ID());
	}
*/
}
