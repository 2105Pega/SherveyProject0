package com.revature.driver;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import com.revature.bank.Account;
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
			if(a.getOwnerID().compareTo(AccountID) == 0);
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
		
		clientMenu();
		
		return 1;
	}
	
	private void clientMenu() 
	{
		int selection = -1;
		
		Scanner kIn = new Scanner(System.in);
		
		do {
			
			System.out.println("Welcome " + currentClient.getUsername() + "!\nPlease select one of the follow options.");
			System.out.println("1.) Accounts Summary");
			System.out.println("2.) Access Account");
			System.out.println("3.) Apply for Account");
			System.out.println("4.) Exit");
			
			while(kIn.hasNextInt() == false)
			{
				kIn.nextLine();
			}
			
			selection = kIn.nextInt();
			
			switch (selection)
			{
			case 1:
				accountsSummary();
				break;
			case 2:
				System.out.println("Enter Account ID");
				String id = kIn.nextLine();
				
				Account a = selectAccount(UUID.fromString(id));
				
				if(a == null)
				{
					System.out.println("Account ID not in System.");
					break;
				}
				
				if(a.getACCOUNT_ID().compareTo(currentClient.getClientID()) == 0)
				{
					try {
						manageAccount(a);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				else
				{
					for(UUID u : a.getCoOwnerIDs())
					{
						if(u.compareTo(currentClient.getClientID()) == 0)
						{
							try {
								manageAccount(a);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						}
					}
					
				}
				System.out.println("You are not a registered Owner or Co-Owner. Access Denied.");
				break;
			case 3:
				createAccount();
				break;
			case 4:
				return;
				
			default:
				selection = -1;
				break;
			}
			
		} while(selection != 4);

	}

	private void manageAccount(Account a) throws Exception {
		Scanner kIn = new Scanner(System.in);
		
		System.out.println("1.) Summarize Account \n2.) Withdraw \n.3) Deposist \n4.) Transfer Funds\n5.)Add co-owner \n6.) Exit");
		int selection = kIn.nextInt();
		kIn.nextLine();
		
		do
		{
			switch (selection)
			{
			case 1: //Sumaraized Currently Selected Account
				accountSummary(a);
				break;
			case 2: //Withdars Money from Current Account
				int withdrawValue = kIn.nextInt();
				kIn.nextLine();
				
				if(withdrawValue < 0)
					throw new IllegalArgumentException("Cannot Withdraw Negative Funds");
				else
				{
					try {
					a.withdraw(withdrawValue);
					}
					catch (IllegalArgumentException e){
						System.out.println(e.getMessage());
					}
					catch (IllegalStateException e)
					{
						System.out.println(e.getMessage());
					}
					if(a.getBalance() < 0)
						System.out.println("Warning! You have overdrawn.");
					System.out.print("Balance is now: " );
					System.out.printf("Balance: " + "%.2d \n", a.getBalance());
				}
				break;
			case 3: //Deposits money to current Account
				int depositValue = kIn.nextInt();
				kIn.nextLine();
				try {
				a.deposit(depositValue);
				}
				catch (IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
				catch (IllegalStateException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case 4: //Transfers Money from Current Account to Target Account
				int transferValue = kIn.nextInt();
				kIn.nextLine();
				
				System.out.println("Enter target account's ID: ");
				String transferAccountID = kIn.nextLine();
				Account tAccount = selectAccount(UUID.fromString(transferAccountID));
				
				try {
				a.transfer(tAccount, transferValue);
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (NullPointerException e){
					System.out.println(e.getMessage());
				}
				catch (IllegalStateException e) {
					System.out.println(e.getMessage());
				}
					
				break;
			case 5: //Adds a Co-Owner to Current Account
				System.out.println("Please Enter co-owner User Name: ");
				String userName = kIn.nextLine();
				
				for(Client c : clientList)
				{
					if(userName.equals(c.getUsername()))
					{
						a.getCoOwnerIDs().add(c.getClientID());
						System.out.println("Added " + userName);
					}
					else
						System.out.println("Username not in System.");
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

	private void accountSummary(Account a) {
		System.out.println(a.getAccountName());
		System.out.printf("Balance: " + "%.2d \n", a.getBalance());
		System.out.println("Account ID: " + a.getACCOUNT_ID() + "\n---------------------------------");
	}

	private void accountsSummary() 
	{
		System.out.println("Accounts you own:");
		for(Account a : accountList)
		{
			if(a.getOwnerID().compareTo(currentClient.getClientID()) == 0)
			{
				System.out.print(a.getAccountName() + ", Balance: " );
				System.out.printf("%.2f, ", a.getBalance());
				System.out.println("Status: " + a.getStatus() + ", ID: " + a.getACCOUNT_ID());
			}

		}
		
		System.out.println("Accounts you co-own.");
		for(UUID u : currentClient.getCoOwnedAccounts())
		{
			for(Account a : accountList)
			{
				for(UUID u2 : a.getCoOwnerIDs())
				{
					if(u2.compareTo(u) == 0)
					{
						System.out.println(a.getAccountName() + ", Balance: " );
						System.out.printf("%.2d", a.getBalance());
					}
				}
			}
		}
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
		
		System.out.println("Please Enter your Address: ");
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
	}
	
	public void createAccount()
	{
		Scanner kIn = new Scanner(System.in);
		
		System.out.println("Name your account: ");
		String accountName = kIn.nextLine();
		
		Account a = new Account(accountName, AccountStatus.PENDING, 0, currentClient.getClientID());
		accountList.add(a);
		currentClient.getOwnedAccounts().add(a.getACCOUNT_ID());

		System.out.println(a.toString());
		System.out.println(accountList.get(accountList.size() -1).toString());
	}
}
