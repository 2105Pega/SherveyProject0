package com.revature.driver;

import java.util.ArrayList;
import java.util.UUID;

import com.revature.bank.Account;
import com.revature.bank.AccountManager;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;

public class EmployeeDriver extends ClientDriver{

	public EmployeeDriver(ArrayList<Client> clientList, AccountManager aM) {
		super(clientList, aM);
		
	}

	@Override
	public int logIn(String User, String Password)
	{
		if(User.equals("Admin") && Password.equals("Root"))
		{
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
				
				user = sc.getLine();
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
				break;
			case 2:a.setStatus(AccountStatus.DENIED);
				break;
			case 3:
				a.setStatus(AccountStatus.CANCELED);
				break;
			case 4:
				return;
			default:
				selection = -1;
			}
		} while (selection != 4);
		
		
	}

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
}
