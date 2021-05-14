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
		accountList.add(a);
	}

	public Account getAccountByAccountID(UUID u)
	{
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
			if(a.getACCOUNT_ID().equals(u));
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
}
