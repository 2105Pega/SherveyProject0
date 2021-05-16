package com.revature.bank;

import java.util.ArrayList;
import java.util.UUID;

public class AccountManager {

	private static ArrayList<Account> accountList;
	
	public AccountManager(ArrayList<Account> accountList)
	{
		AccountManager.accountList = accountList;
	}
	
	public void addAccount(Account a)
	{
		if(a == null)
			throw new NullPointerException();
		else if(this.getAccountByAccountID(a.getACCOUNT_ID()) != null)
			throw new IllegalArgumentException();
		
		accountList.add(a);
	}

	public void removeAccounts()
	{
		ArrayList<Account> toRemove = new ArrayList<Account>();
		
		for(Account a : accountList)
		{
			if(a.getStatus() == AccountStatus.CANCELED || a.getStatus() == AccountStatus.DENIED)
			{
				toRemove.add(a);
			}
		}
		accountList.removeAll(toRemove);
	}
	
	public Account getAccountByAccountID(UUID u)
	{
		if(u == null)
			throw new NullPointerException();
		
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
			throw new NullPointerException();
		
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
			throw new IllegalArgumentException("Cannot Withdraw Negative or Zero Funds");
		}
		else if(a == null)
		{
			throw new NullPointerException();
		}
		else if(a.getStatus() != AccountStatus.APPROVED)
		{
			throw new IllegalStateException("Account Not Approved.");
		}
		
		a.setBalance((a.getBalance() - ammountChange));
		return a.getBalance();
	}
	
	public double deposit(Account a, double ammountChange)
	{
		if(ammountChange <= 0)
		{
			throw new IllegalArgumentException("Cannot Withdraw Negative or Zero Funds");
		}
		else if(a == null)
		{
			throw new NullPointerException();
		}
		else if(a.getStatus() != AccountStatus.APPROVED)
		{
			throw new IllegalStateException("Account Not Approved.");
		}
		
		a.setBalance((a.getBalance() + ammountChange));
		return a.getBalance();
	}
	
	public void transfer(Account sender, Account target, double ammount)
	{
		if(ammount <= 0)
		{
			throw new IllegalArgumentException("Cannot Transfer Negative or Zero Funds");
		}
		else if(sender == null)
		{
			throw new NullPointerException("Cannot Transfer from sending Account. ID Not Found in System");
		}
		else if(target == null) 
		{
			throw new NullPointerException("Cannot Transfer to target Account. ID Not Found in System");
		}
		else if(target.getACCOUNT_ID().equals(sender.getACCOUNT_ID()))
		{
			throw new IllegalArgumentException("Cannot Transfer To Same Account");
		}
		else if(sender.getStatus() != AccountStatus.APPROVED)
		{
			throw new IllegalStateException("Target Account Not Approved.");
		}
		else if(target.getStatus() != AccountStatus.APPROVED)
		{
			throw new IllegalStateException("Sending Account Not Approved.");
		}
		
		this.withdraw(sender, ammount);
		this.deposit(target, ammount);
	}

}
