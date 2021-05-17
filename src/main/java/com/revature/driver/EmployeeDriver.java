package com.revature.driver;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bank.Account;
import com.revature.bank.AccountManager;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;

public class EmployeeDriver extends ClientDriver{

	private static final Logger logger = LogManager.getLogger(EmployeeDriver.class);
	
	public EmployeeDriver(ArrayList<Client> clientList, AccountManager aM) {
		super(clientList, aM);
		
	}

	@Override
	public int logIn(String User, String Password)
	{
		if(User.equals("Admin") && Password.equals("Root"))
		{
			logger.info("Logged Is As Admi.");
			employeMenu();
			return 1;
		}
		else
			return -1;
			
	}
	
	public void employeMenu()
	{
		int selection = -1;
		
		do {
			
			System.out.println("Welcome Admin, " + "!\nPlease select one of the follow options.");
			System.out.println("1.) Clients Summary");
			//System.out.println("2.) Modify Client");
			System.out.println("2.) Accounts Summary");
			System.out.println("3.) Change Account Status");
			System.out.println("4.) Access Accounts As Client");
			System.out.println("5.) Exit");
			
			selection = sc.getInt();
			
			switch (selection)
			{
			case 1:
				for(Client c : clientList)
					System.out.println(c.toString());
				break;
				/*
			case 2:
				System.out.println("Enter Client's User Name:");
				String user = sc.getLine();
				
				Client c = null;
				
				for(Client c2 : clientList)
				{
					if(c2.getUsername().equals(user))
						c = c2;
						
				}
				
				if(c != null)
					modifyClientMenu(c);
				else
					System.out.println("Error User Name not found in System. Please try again.");
				break;
				*/
			case 2:
				for(Account a : super.aM.getAllAccounts())
					System.out.println(a.toString());
				break;
				
			case 3:
				System.out.println("Enter Account ID");
				String id = sc.getLine();
				Account a = null;
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
				
				String user, pass;
				System.out.println("Enter Username: ");
				user = sc.getLine();
				System.out.println("Enter Password: ");
				pass = sc.getLine();
				
				super.logIn(user, pass);
				break;
				
			case 5:
				return;
			default:
				selection = -1;
				break;
			}
			
			
			} while (selection != 5);
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
		
	}
/*
	private void modifyClientMenu(Client c) {
		System.out.println("1.) Change Username");
		System.out.println("2.) Change Password");
		System.out.println("3.) Change First name");
		System.out.println("4.) Change Last Name");
		System.out.println("5.) Change Address");
		System.out.println("6.) Exit");
		
		int selection;
		do
		{
			selection = sc.getInt();
			
			switch (selection)
			{
			case 1:
				
				
				
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				return;
			default:
				selection = -1;
			}
		} while (selection != 4);
		
		
	}
	*/
}
