package com.revature.services;

import java.util.ArrayList;

import com.revature.bank.Account;
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOIMP;

public class AccountService {

	private AccountDAO aD = new AccountDAOIMP();
	
	public boolean addAccount(Account a)
	{
		return aD.addAccount(a);
	}
	
	public boolean removeAccountByID(int id)
	{
		return aD.removeAccount(id);
	}
	
	public boolean removeAccounts()
	{
		return aD.removeAccounts();
	}

	public ArrayList<Integer> getAccountsByOwnerID(int id)
	{
		return aD.getAccountsByOwnerID(id);
	}
	
	public ArrayList<Integer> getAccountsByCoOwnerID(int id)
	{
		return aD.getAccountsByCoOwnerID(id);
	}
}
