package com.revature.driver;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reavture.utils.ScannerSingleton;
import com.revature.bank.Account;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;
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
		
		System.out.println("User: " + currentClient.getUsername() + ", ID: " + currentClient.getClientID());
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
						System.out.println("Account: " + a.getAccountName() + ", Balance: " + a.getBalance() + " ID: " + a.getACCOUNT_ID() + " Status: " + a.getStatus());
				}
				System.out.println("-------- Co-Owned Accounts: ");
				for(Account a : aS.getAccountsByCoOwnerID(currentClient.getClientID()))
				{
					if(a != null)
						System.out.println("Account: " + a.getAccountName() + ", Balance: " + a.getBalance() + " ID: " + a.getACCOUNT_ID() + " Status: " + a.getStatus());
				}
				System.out.println("------------------------------- ");
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
				System.out.println("Account: " + a.getAccountName() + ", Balance: " 
			+ a.getBalance() + " ID: " + a.getACCOUNT_ID() + " Status: " + a.getStatus());
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
				
				try {
					Client c = cS.getClientByUsername(userName);
					aS.addCoOwner(a.getACCOUNT_ID(), c.getClientID());
					logger.info("Added Co-Owner " + c.toString() + " to " + a.toString());
					System.out.println("Added " + userName);
				} catch (Exception e) {
					logger.error(e.getMessage());
					System.out.println("Username not in System.");
				}	
				
				break;
				
			case 6:
				try {
					if(a.getBalance() == 0)
					{
						if(aS.removeAccountByID(a.getACCOUNT_ID()));
						{
							logger.info("Account removed.");
							selection = 7;
							return;
						}
					}
					else
						System.out.println("Account Balance must be 0 to delete.");
					
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				break;
				
			case 7:
				return;
			default:
				break;
			}
		}while(selection != 7);
	}
	
	protected void transfer(Account a) {
		System.out.println("Ammount to Transfer: ");
		double transferValue = sc.getDouble();
		
		System.out.println("Enter Target Account's ID: ");
		int transferAccountID = sc.getInt();
		
		try {
			if(aS.transfer(a.getACCOUNT_ID(), transferAccountID, transferValue));
				a.setBalance(a.getBalance() - transferValue);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

	protected void deposit(Account a) {
		System.out.println("Ammount to Deposit: ");
		double depositValue = sc.getDouble();
		
		try {
			if(aS.deposit(a.getACCOUNT_ID(), depositValue));
				a.setBalance(a.getBalance() + depositValue);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
	}

	protected void withdraw(Account a) {
		System.out.println("Ammount to Withdraw: ");
		double withdrawValue = sc.getDouble();
		try {
			if(aS.withdraw(a.getACCOUNT_ID(), withdrawValue));
				a.setBalance(a.getBalance() - withdrawValue);
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
				System.out.println("Account not created.");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void createClient() {
		String username, password, fName, lName, address;
		
		System.out.println("Enter Username: ");
		username = sc.getLine();
		
		while(cS.getClientByUsername(username) != null)
		{
			System.out.println("Username already in use. Enter a new name: ");
			username = sc.getLine();
		}
		System.out.println("Enter Password: ");
		password = sc.getLine();
		System.out.println("Enter First Name: ");
		fName = sc.getLine();
		System.out.println("Enter Last Name: ");
		lName = sc.getLine();
		System.out.println("Enter Address");
		address = sc.getLine();
		
		Client c = new Client(username, password, fName, lName, address);
		try {
			cS.addClient(c);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		logger.info("New Client Created.");
	}
}
