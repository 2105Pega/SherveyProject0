package com.revature.driver;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bank.Account;
import com.revature.bank.AccountManager;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;

public class ClientDriver {

	protected ArrayList<Client> clientList;
	
	private Client currentClient;
	protected static ScannerSingleton sc = new ScannerSingleton();
	protected AccountManager aM;
	private static final Logger logger = LogManager.getLogger(ClientDriver.class);
	
	public ClientDriver(ArrayList<Client> clientList, AccountManager aM)
	{
		this.clientList = clientList;
		this.aM = aM;
	}
	
	public int logIn(String userName, String password)
	{
		boolean validLogIn = false;
		for(Client c : clientList)
		{
			if(c.getUsername().equals(userName) && c.getPassword().equals(password))
			{
				currentClient = c;
				validLogIn = true;
				logger.info("Loged in as " + c.toString());
			}
		}
		
		if(validLogIn == false)
		{
			logger.warn("Log in Failed with " + userName + " " + password);
			return -1;
		}
		
		System.out.println("User Info: " + currentClient.toString());
		clientMenu();
		
		return 1;
	}
	
	private void clientMenu() 
	{
		int selection = -1;
		
		do {
			
			System.out.println("Welcome " + currentClient.getUsername() + "!\nPlease select one of the follow options.");
			System.out.println("1.) Accounts Summary");
			System.out.println("2.) Access Account");
			System.out.println("3.) Apply for Account");
			System.out.println("4.) Exit");
			
			selection = sc.getInt();
			
			switch (selection)
			{
			case 1:
				System.out.println(aM.accountsSummary(currentClient.getClientID()));
				break;
			case 2:

				System.out.println("Enter Account ID");
				String id = sc.getLine();
				Account a = null;
				try {
					a = aM.getAccountByAccountID(UUID.fromString(id));
				}
				catch(IllegalArgumentException e)
				{
					logger.error("caught IllegalArgumentException in clientManager, Account Access " + e.getMessage());
					System.out.println("ID invalid. Please try again.");
				}
				
				if(a == null)
				{
					System.out.println("Account ID not in System.");
					break;
				}
				
				if(a.getOwnerID().equals(currentClient.getClientID()))
				{
					try {
						manageAccount(a);
					} catch (Exception e) {
						logger.warn("error in ClientManager, Access Account: " + e.getMessage());
						e.printStackTrace();
					}
					break;
				}
				else
				{
					for(UUID u : a.getCoOwnerIDs())
					{
						if(u.equals(currentClient.getClientID()))
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
				break;
			}
			selection = -1;
		} while(selection != 4);

	}

	protected void manageAccount(Account a) throws Exception {
		int selection = -1;
		
		do
		{
			System.out.println("1.) Summarize Account \n2.) Withdraw \n3.) Deposist \n4.) Transfer Funds\n5.) Add co-owner \n6.) Exit");
			selection = sc.getInt();
			switch (selection)
			{
			case 1: //Sumaraized Currently Selected Account
				System.out.println(aM.accountSummary(a));
				break;
				
			case 2: //Withdars Money from Current Account
				this.withdraw(a);
				break;
				
			case 3: //Deposits money to current Account
				
				this.deposit(a);
				break;
			case 4: //Transfers Money from Current Account to Target Account
				this.transfer(a);
				break;
				
			case 5: //Adds a Co-Owner to Current Account
				System.out.println("Please Enter co-owner User Name: ");
				String userName = sc.getLine();
				
				for(Client c : clientList)
				{
					if(userName.equals(c.getUsername()))
					{
						a.getCoOwnerIDs().add(c.getClientID());
						logger.info("Added Co-Owner " + c.toString() + " to " + a.toString());
						System.out.println("Added " + userName);
					}
					else
						System.out.println("Username not in System.");
				}
				break;
				
			case 6:
				return;
				
			default:
				break;

			}
			selection = -1;
		}while(selection != 6);
	}
	
	protected void transfer(Account a) {
		System.out.println("Ammount to Transfer: ");
		double transferValue = sc.getDouble();
		
		System.out.println("Enter Target Account's ID: ");
		String transferAccountID = sc.getLine();
		Account tAccount = aM.getAccountByAccountID(UUID.fromString(transferAccountID));
		
		try {
		aM.transfer(a, tAccount, transferValue);
		}
		catch (IllegalArgumentException e) {
			logger.error("caught IllegalArgumentException in clientManager, Transfer " + e.getMessage());
			System.out.println(e.getMessage());
		}
		catch (NullPointerException e){
			logger.error("caught NullPointerException in clientManager, Transfer " + e.getMessage());
			System.out.println(e.getMessage());
		}
		catch (IllegalStateException e) {
			logger.error("caught IllegalStateException in clientManager, Transfer " + e.getMessage());
			System.out.println(e.getMessage());
		}	
		
	}

	protected void deposit(Account a) {
		System.out.println("Ammount to Deposit: ");
		double depositValue = sc.getDouble();
		
		try {
		aM.deposit(a, depositValue);
		}
		catch (IllegalArgumentException e){
			logger.error("caught IllegalArgumentException in clientManager, Deposit " + e.getMessage());
			System.out.println(e.getMessage());
		}
		catch (IllegalStateException e)
		{
			logger.error("caught IllegalStateException in clientManager, Deposit " + e.getMessage());
			System.out.println(e.getMessage());
		}
		
	}

	protected void withdraw(Account a) {
		System.out.println("Ammount to Withdraw: ");
		double withdrawValue = sc.getDouble();
		
		try {
		aM.withdraw(a, withdrawValue);
		}
		catch (IllegalArgumentException e){
			logger.error("caught IllegalArgumentException in clientManager, Withdraw " + e.getMessage());
			System.out.println(e.getMessage());
		}
		catch (IllegalStateException e)
		{
			logger.error("caught IllegalStateException in clientManager, Withdraw " + e.getMessage());
			System.out.println(e.getMessage());
		}
		
	}

	public void createClient()
	{	
		System.out.println("Please enter User Name: ");
		
		String userName = sc.getLine();
		while(userName.length() == 0)
		{
			System.out.println("Please enter User Name: ");
			userName = sc.getLine();
		}
		
		System.out.println("Please Enter Password: ");
		String password = sc.getLine();
		while(password.length() == 0)
		{
			System.out.println("Please Enter Password: ");
			password = sc.getLine();
		}
		
		System.out.println("Please Enter First Name: ");
		String fName = sc.getLine();
		while(password.length() == 0)
		{
			System.out.println("Please Enter First Name: ");
			fName = sc.getLine();
		}
		
		System.out.println("Please Enter Last Name: ");
		String lName = sc.getLine();
		while(password.length() == 0)
		{
			System.out.println("Please Enter Last Name: ");
			lName = sc.getLine();
		}
		
		System.out.println("Please Enter Address: ");
		String address = sc.getLine();
		while(password.length() == 0)
		{
			System.out.println("Please Enter Address: ");
			address = sc.getLine();
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
					userName = sc.getLine();
				}
			}			
		}while(userValid == false);
	
		Client c = new Client(userName, password, fName, lName, address);
		clientList.add(c);
		
		logger.info("New Client created: " + c.toString());
	}
	
	public void createAccount()
	{
		System.out.println("Name Account: ");
		String accountName = sc.getLine();
		
		Account a = new Account(accountName, AccountStatus.PENDING, 0, currentClient.getClientID());
		aM.addAccount(a);
		currentClient.getOwnedAccounts().add(a.getACCOUNT_ID());
		logger.info("New Account created: " + a.toString());
	}
}
