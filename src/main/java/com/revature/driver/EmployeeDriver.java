package com.revature.driver;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bank.Account;
import com.revature.bank.AccountManager;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;
import com.revature.bank.ClientManager;

public class EmployeeDriver extends ClientDriver{

	private static final Logger logger = LogManager.getLogger(EmployeeDriver.class);
	
	public EmployeeDriver(ClientManager clientManager, AccountManager aM) {
		super(clientManager, aM);
		
	}

	@Override
	public int logIn(String User, String Password)
	{
		if(User.equals("Admin") && Password.equals("Root"))
		{
			logger.info("Logged Is As Admim.");
			employeMenu();
			return 1;
		}
		else
			return -1;
			
	}
	
	public void employeMenu()
	{
		int selection = -1;
		Account a;
		String s;
		do {
			
			System.out.println("Welcome Admin, " + "!\nPlease select one of the follow options.");
			System.out.println("1.) Clients Summary");
			System.out.println("2.) Accounts Summary");
			System.out.println("3.) Change Account Status");
			System.out.println("4.) Withdraw From Account");
			System.out.println("5.) Deposit Into Account");
			System.out.println("6.) Transfer from Account");
			System.out.println("7.) Access Accounts As Client");
			System.out.println("8.) Remove Canceled and Denied Accounts");
			System.out.println("9.) Exit");
			
			selection = sc.getInt();
			
			switch (selection)
			{
			case 1:
				for(Client c : clientList)
					System.out.println(c.toString());
				break;
				
			case 2:
				for(Account a2 : super.aM.getAllAccounts())
					System.out.println(a2.toString());
				break;
				
			case 3:
				System.out.println("Enter Account ID: ");
				String id = sc.getLine();
				a = null;
				try {
					a = aM.getAccountByAccountID(UUID.fromString(id));
				}
				catch(IllegalArgumentException e)
				{
					logger.error("Invlaid ID in EmployeeManager.Menu, Changin Account Status.");
					System.out.println("ID invalid. Please try again.");
				}
				
				if(a == null)
				{
					System.out.println("Account ID not in System.");
					break;
				}
				else
					modifyAccountStatus(a);
				
				break;
			case 4:
				
				System.out.println("Enter Account ID to Withdraw From: ");
				s = sc.getLine();
				
				try {
					a = aM.getAccountByAccountID(UUID.fromString(s));
					this.withdraw(a);
				}
				catch (IllegalArgumentException e)
				{
					logger.error("Account Not Found.") ;
				}
				
				
				break;
			case 5:
				
				System.out.println("Enter Account ID to Deposit To: ");
				s = sc.getLine();
				try {
					a = aM.getAccountByAccountID(UUID.fromString(s));
					this.deposit(a);
				}
				catch (IllegalArgumentException e)
				{
					logger.error("Account Not Found.") ;
				}
				
				break;
			case 6:
				
				System.out.print("Enter Account ID to Transfer From");
				
				s = sc.getLine();
				try {
				a = aM.getAccountByAccountID(UUID.fromString(s));
				this.transfer(a);
				} catch (IllegalArgumentException e)
				{
					logger.error("Account Not Found.") ;
				}
				
				break;
			case 7:
				
				String user, pass;
				System.out.println("Enter Username: ");
				user = sc.getLine();
				System.out.println("Enter Password: ");
				pass = sc.getLine();
				
				super.logIn(user, pass);
				break;
				
			case 8:
				System.out.println("Removing All Denied and Canceled Accounts");
				aM.removeAccounts();
				break;
			case 9:
				return;
			default:
				selection = -1;
				break;
			}
			
			
			} while (selection != 9);
	}

	private void modifyAccountStatus(Account a)
	{
		System.out.println(a.getAccountName() + " has a Status of: " + a.getStatus());
		
		System.out.println("1.) Set to APPROVED");
		System.out.println("2.) Set to DENIED");
		System.out.println("3.) Set to CANCELED");
		System.out.println("4.) Exit");
		
		int selection;
		do
		{
			selection = sc.getInt();
			
			switch (selection)
			{
			case 1:
				a.setStatus(AccountStatus.APPROVED);
				System.out.println(a.getAccountName() + "Set to " + a.getStatus());
				logger.info("Account " + a.getAccountName() + "Status Updated to APPROVED");
				break;
			case 2:
				a.setStatus(AccountStatus.DENIED);
				System.out.println(a.getAccountName() + "Set to " + a.getStatus());
				logger.info("Account " + a.getAccountName() + "Status Updated to DENIED");
				break;
			case 3:
				a.setStatus(AccountStatus.CANCELED);
				System.out.println(a.getAccountName() + "Set to " + a.getStatus());
				logger.info("Account " + a.getAccountName() + "Status Updated to CANCELED");
				break;
			case 4:
				return;
			default:
				selection = -1;
			}
		} while (selection != 4);
		aM.removeAccounts();
	}

}
