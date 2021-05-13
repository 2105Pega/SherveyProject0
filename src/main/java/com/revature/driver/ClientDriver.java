package com.revature.driver;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import com.revature.bank.Account;
import com.revature.bank.AccountManagment;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;

public class ClientDriver {

	private ArrayList<Client> clientList;
	private ArrayList<Account> accountList;
	
	private Client currentClient;
	
	public ClientDriver(ArrayList<Client> clientList, ArrayList<Account> accountList)
	{
		this.clientList = clientList;
		this.accountList = accountList;
	}
	
	public Account selectAccount(UUID AccountID) 
	{
		
		for(Account a : accountList)
		{
			if(a.getOwnerID() == AccountID)
			{
				return a;
			}
		}
		
		return null;
			
	}
	
	public int logIn(String userName, String password)
	{
		boolean validLogIn = false;
		for(Client c : clientList)
		{
			if(c.getUsername().compareTo(userName) == 0 && c.getPassword().compareTo(password) == 0)
			{
				currentClient = c;
				validLogIn = true;
			}
		}
		
		if(validLogIn == false)
		{
			return -1;
		}
		
		ClientMenu();
		
		return 1;
	}
	
	private void clientMenu() 
	{
		int selection = -1;
		
		Scanner kIn = new Scanner(System.in);
		
		do {
			displayClientMenu();
			selection = kIn.nextInt();
			kIn.nextLine();
			
			switch (selection)
			{
			case 1:
				accountsSummary();
				break;
			case 2:
				String AID = kIn.nextLine();
				Account a = selectAccount(UUID.fromString(AID));
				
				if(a == null)
				{
					System.out.println("Error, Account ID not found. Please try again.");
				}
				else
				{
					manageAccount(a);
				}
				break;
			case 3:
				break;
			case 4:
				return;
				
			default:
				selection = -1;
				break;
			}
			
		} while(selection < 0);
		kIn.close();
	}

	private void manageAccount(Account a) {
		Scanner kIn = new Scanner(System.in);
		
		System.out.println("1.) Summarize Account \n2.) Withdraw \n.3) Deposist \n4.) Transfer Funds\n5.)Add co-owner \n6.) Exit");
		int selection = kIn.nextInt();
		kIn.nextLine();
		
		do
		{
			switch (selection)
			{
			case 1:
				System.out.println(a.getAccountName());
				System.out.printf("Balance: " + "%.2d", a.getBalance());
				System.out.println("Account ID: " + a.getACCOUNT_ID() + "\n---------------------------------");
				break;
			case 2:
				int withdrawValue = kIn.nextInt();
				kIn.nextLine();
				
				if(withdrawValue < 0)
					System.out.println("Error: Enter value greater then 0 for transaction to go through.");
				else
					a.transaction(-1 * withdrawValue);
				break;
			case 3:
				int depositValue = kIn.nextInt();
				kIn.nextLine();
				
				if(depositValue < 0)
					System.out.println("Error: Enter value greater then 0 for transaction to go through.");
				else
					a.transaction(depositValue);
				break;
			case 4:
				int transferValue = kIn.nextInt();
				kIn.nextLine();
				
				if(transferValue < 0)
					System.out.println("Error: Enter value greater then 0 for transaction to go through.");
				else
				{
					String transferAccountID = kIn.nextLine();
					Account tAccount = selectAccount(UUID.fromString(transferAccountID));
					
					if(tAccount == null)
					{
						System.out.println("Error: ID for transfer target not found.");
					}
					else
					{
						a.transfer(tAccount, transferValue);
					}
					
				}
				break;
			case 5:
				System.out.println("Please Enter co-owner ID: ");
				String ID = kIn.nextLine();
				UUID UserID = UUID.fromString(ID);
				
				for(Client c : clientList)
				{
					if(c.getClientID() == UserID)
						a.getCoOwnerIDs().add(UserID);
				}
				
				break;
			case 6:
				break;
			default:
				selection = -1;
				break;
			}
		}while(selection != 6);
	}

	private void accountsSummary() 
	{
		System.out.println("Accounts you own.");
		for(UUID u : currentClient.getOwnedAccounts())
		{
			for(Account a : accountList)
			{
				if(a.getOwnerID() == u)
				{
					System.out.println(a.getAccountName());
					System.out.printf("Balance: " + "%.2d", a.getBalance());
					System.out.println("Account ID: " + a.getACCOUNT_ID() + "\n---------------------------------");
				}
			}
		}
		
		System.out.println("Accounts you co-own.");
		for(UUID u : currentClient.getCoOwnedAccounts())
		{
			for(Account a : accountList)
			{
				for(UUID u2 : a.getCoOwnerIDs())
				{
					if(u2 == u)
					{
						System.out.println(a.getAccountName() + ", Balance: " );
						System.out.printf("%.2d", a.getBalance());
					}
				}
			}
		}
	}

	private void displayClientMenu() 
	{
		String menu[] = new String[5];
		menu[0] = "Welcome " + currentClient.getUsername() + "!\nPlease select one of the follow options.";
		menu[1] = "1.) Accounts Summary";
		menu[2] = "2.) Access Account";
		menu[3] = "3.) Apply for Account";
		menu[4] = "4.) Exit";
		
		for(String s : menu)
			System.out.println(s);
	}
	
	public void logOut()
	{
		
	}
	
	public void createClient()
	{
		
		Scanner kIn = new Scanner(System.in);
		
		System.out.println("Please your User Name: ");
		
		String userName = kIn.nextLine();
		while(userName.length() == 0)
		{
			System.out.println("Please your User Name: ");
			userName = kIn.nextLine();
		}
		
		System.out.println("Please Enter Password: ");
		String password = kIn.nextLine();
		while(password.length() == 0)
		{
			System.out.println("Please your User Name: ");
			password = kIn.nextLine();
		}
		
		System.out.println("Please Enter your First Name: ");
		String fName = kIn.nextLine();
		while(password.length() == 0)
		{
			System.out.println("Please your User Name: ");
			fName = kIn.nextLine();
		}
		
		System.out.println("Please Enter your Last Name: ");
		String lName = kIn.nextLine();
		while(password.length() == 0)
		{
			System.out.println("Please your User Name: ");
			lName = kIn.nextLine();
		}
		
		System.out.println("Please Enter your adress ");
		String address = kIn.nextLine();
		while(password.length() == 0)
		{
			System.out.println("Please your User Name: ");
			address = kIn.nextLine();
		}
		
		
		boolean userValid = true;
		
		do
		{
			userValid = true;
			for(Client c2 : clientList)
			{
				if(c2.getUsername().equals(userName))
				{
					userValid = false;
					System.out.println("Username not available. Please enter a new name: ");
					userName = kIn.nextLine();
				}
			}			
		}while(userValid == false);
		
		Client c = new Client(userName, password, fName, lName, address);
		clientList.add(c);
		
		for(Client c2 : clientList)
			System.out.println(c2.toString());
		
		
		kIn.close();
	}
	
	public void createAccount()
	{
		Scanner kIn = new Scanner(System.in);
		int selection;
		
		System.out.println("1. Solo Account.\n2. Join Account");
		selection = kIn.nextInt();
		kIn.nextLine();
		
		while(selection != 1 || selection != 2)
		{
			System.out.println("1. Solo Account.\n2. Join Account");
			selection = kIn.nextInt();
			kIn.nextLine();
			
		}
		
		System.out.println("Name your account: ");
		String accountName = kIn.nextLine();
		
		if(selection == 1)
		{	
			Account a = new Account(accountName, AccountStatus.PENDING, 0, currentClient.getClientID());
			accountList.add(a);
			currentClient.getOwnedAccounts().add(a.getACCOUNT_ID());
		}
		else
		{

		}
		
		kIn.close();
	}
}
