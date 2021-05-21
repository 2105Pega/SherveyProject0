package com.revature.driver;

import java.sql.Connection;
import java.sql.SQLException;

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
		Account a = new Account( "TestAccount", AccountStatus.PENDING, 0, 2);
		AccountService as = new AccountService();
		
		
		
	}
}
