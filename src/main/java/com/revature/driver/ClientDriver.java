package com.revature.driver;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reavture.utils.ScannerSingleton;
import com.revature.bank.Account;
import com.revature.bank.AccountManager;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;
import com.revature.bank.ClientManager;
import com.revature.services.AccountService;
import com.revature.services.ClientService;

public class ClientDriver {

	protected ArrayList<Client> clientList;
	
	private Client currentClient;
	private ClientService cS;
	private AccountService aS;
	protected static ScannerSingleton sc = new ScannerSingleton();
	private static final Logger logger = LogManager.getLogger(ClientDriver.class);
	
	public ClientDriver()
	{
		aS = new AccountService();
		cS = new ClientService();
	}
	
	public int logIn(String userName, String password)
	{	
		currentClient = cS.getClientByUserAndPass(userName, password);
		
		if(currentClient == null)
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
				
				for(Account a : aS.getAccountsByOwnerID(currentClient.getClientID()))
				{
					if(a != null)
						a.toString();
				}
				
				break;
			
			case 2:

				System.out.println("Enter Account ID");
				int id = sc.getInt();
				Account a = aS.getAccountByID(id);

				if(a == null)
				{
					logger.info("Account ID not in System.");
					break;
				}
				
				if(a.getOwnerID() == currentClient.getClientID() || aS.isCoOwned(currentClient.getClientID(), a.getACCOUNT_ID()))
				{
					manageAccount(a);
					break;
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

	protected void manageAccount(Account a) {
		int selection = -1;
		
		do
		{
			System.out.println("1.) Summarize Account");
			System.out.println("2.) Withdraw");
			System.out.println("3.) Deposist");
			System.out.println("4.) Transfer Funds");
			System.out.println("5.) Add co-owner");
			System.out.println("6.) Delete Account");
			System.out.println("7.) Exit");
			selection = sc.getInt();
			switch (selection)
			{
			case 1: //Sumaraized Currently Selected Account
				System.out.println();
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
						try {
							aS.addCoOwner(a.getACCOUNT_ID(), cS.getClientByUsername(userName).getClientID());
						logger.info("Added Co-Owner " + c.toString() + " to " + a.toString());
						System.out.println("Added " + userName);
						} catch (Exception e) {
							logger.error(e.getMessage());
						}
						
					}
					else
						System.out.println("Username not in System.");
				}
				break;
				
			case 6:
				try {
					if(a.getBalance() == 0)
						aS.removeAccountByID(a.getACCOUNT_ID());
					else
						System.out.println("Cannot delete Account with");
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				break;
				
			case 7:
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
		int transferAccountID = sc.getInt();
		
		try {
			aS.transfer(a.getACCOUNT_ID(), transferAccountID, transferValue);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

	protected void deposit(Account a) {
		System.out.println("Ammount to Deposit: ");
		double depositValue = sc.getDouble();
		
		try {
			aS.deposit(a.getACCOUNT_ID(), depositValue);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
	}

	protected void withdraw(Account a) {
		System.out.println("Ammount to Withdraw: ");
		double withdrawValue = sc.getDouble();
		try {
			aS.withdraw(a.getACCOUNT_ID(), withdrawValue);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
	public void createAccount()
	{
		System.out.println("Name Account: ");
		String accountName = sc.getLine();
		
		Account a = new Account(accountName, AccountStatus.PENDING, 0, currentClient.getClientID());
		try {
			if(aS.addAccount(a))
				logger.info("New Account created: " + a.toString());
			else
				System.out.println("Account not created, username already in use. Please use another Username.");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
	}
}
