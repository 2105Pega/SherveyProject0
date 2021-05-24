package com.revature.driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.reavture.utils.ConnectionUtils;
import com.revature.bank.Account;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;
import com.revature.services.AccountService;
import com.revature.services.ClientService;

public class tester {

	public static void main(String[] args) throws SQLException 
	{
		//String accountName, AccountStatus status, double balance, int ownerID
		//Account a = new Account("TestAccount", AccountStatus.PENDING, 0, 2);
		//AccountService as = new AccountService();
		
		/*//clientDAOIMP test was done here via a basic ClientService.
		ClientService cS = new ClientService();
		System.out.println(cS.getClientByID(1).toString());
		System.out.println(cS.getClientByUserAndPass("AshMain", "NoBrains").toString());
		
		Client c = new Client("Test1", "Test1", "TestfName", "TestlName", "Test Address");
		
		System.out.println(cS.addClient(c));
		
		System.out.println(cS.getClientByUserAndPass(c.getUsername(), c.getPassword()).toString());
		System.out.println(cS.removeClientByID(c.getClientID()));
		
		cS.addClient(c);
		System.out.println(cS.removeClientByUserAndPassword(c.getUsername(), c.getPassword()));
		
		c = cS.getClientByUserAndPass("Test1", "Test1");
		c.setuserName("NotATest!");
		System.out.println(cS.updateClient(c));
		System.out.println(cS.getClientByID(c.getClientID()));
		*/
		
		//Account a = new Account("TestAccount", AccountStatus.PENDING, 0, 2);
		AccountService aS = new AccountService();
		
		//System.out.println(aS.getAllAccounts().toString());
		//String accountName, AccountStatus status, double balance, int ownerID
		Account a = new Account("TestAccount", AccountStatus.APPROVED, 0, 1);
		
		//System.out.println(aS.deposit(100, 200));
		//System.out.println(aS.addAccount(a));
		//System.out.println(aS.removeAccountByID(5));
		//System.out.println(aS.addAccount(new Account("TestAccount", AccountStatus.DENIED, 0, 1)));
		
		//aS.addAccount(new Account("TestAccount", AccountStatus.CANCELED, 0, 1));
		//System.out.println(aS.removeAccounts());
		/*
		System.out.println("--------");
		ArrayList<Account> aL = aS.getAccountsByOwnerID(1);
		for(Account a2 : aL)
			System.out.println(a2.toString());
		
		System.out.println("--------");
		
		aL = aS.getAccountsByCoOwnerID(1);
		for(Account a2 : aL)
			System.out.println(a2.toString());
		System.out.println("--------");
		
		aL = aS.getAllAccounts();
		for(Account a2 : aL)
			System.out.println(a2.toString());
		System.out.println("--------");
		*/
	}
}
