package com.revature.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bank.Account;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;
import com.revature.services.AccountService;
import com.revature.services.ClientService;

public class EmployeeDriver extends ClientDriver{

	private static final Logger logger = LogManager.getLogger(EmployeeDriver.class);
	private AccountService aS = new AccountService();
	private ClientService cS = new ClientService();
	public EmployeeDriver() {
		super();
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
		int id, amount;
		Client c;
		Account a;
		do {
			
			System.out.println("Welcome Admin, " + "!\nPlease select one of the follow options.");
			System.out.println("1.) Clients Summary");
			System.out.println("2.) Update/Modify Client");
			System.out.println("3.) Create Client");
			System.out.println("4.) Accounts Summary");
			System.out.println("5.) Change Account Status");
			System.out.println("6.) Withdraw From Account");
			System.out.println("7.) Deposit Into Account");
			System.out.println("8.) Transfer from Account");
			System.out.println("9.) Create Account");
			System.out.println("10.) Access Accounts As Client");
			System.out.println("11.) Remove Canceled and Denied Accounts");
			System.out.println("12.) Exit");
			
			selection = sc.getInt();
			
			switch (selection)
			{
			case 1:
				for(Client c2 : cS.getAllClients())
				{
					System.out.println(c2.toString());
				}
				break;
			case 2:
				System.out.println("Enter ID of Client: ");
				id = sc.getInt();
				
				c = cS.getClientByID(id);
				if(c != null)
					modifyCLient(c);
				else
					logger.warn("Client not found in system. Try again.");
				
				break;
			case 3:
				super.createClient();
				break;
			case 4:
				for(Account a2 : aS.getAllAccounts())
				{
					System.out.println(a2.toString());
				}
				break;
				
			case 5:
				System.out.println("Enter ID of Account to be updated: ");
				id = sc.getInt();
				
				a = aS.getAccountByID(id);
				
				if(a != null)
					this.modifyAccountStatus(a);
				else
					logger.warn("Account not found in system. Try again.");
				
				break;
			case 6:
				
				System.out.println("ID of Account: ");
				id = sc.getInt();
				System.out.println("Amount to Withdraw: ");
				amount = sc.getInt();
				
				aS.withdraw(id, amount);
				
				break;
			case 7:
				
				System.out.println("ID of Account: ");
				id = sc.getInt();
				System.out.println("Amount to Deposit: ");
				amount = sc.getInt();
				
				aS.deposit(id, amount);
				
				break;
			case 8:
				
				System.out.println("Sending Account ID: ");
				id = sc.getInt();
				System.out.println("Target Account ID: ");
				int targetID = sc.getInt();
				System.out.println("Amount to Transfer: ");
				amount = sc.getInt();
				
				aS.transfer(id, targetID, amount);
				
				break;
			case 9:
				this.createAccount();
				break;
			case 10:
				
				String user, pass;
				System.out.println("Enter Username: ");
				user = sc.getLine();
				System.out.println("Enter Password: ");
				pass = sc.getLine();
				
				super.logIn(user, pass);
				break;
				
			case 11:
				aS.removeAccounts();
				break;
			case 12:
				return;
			default:
				selection = -1;
				break;
			}
			
			
			} while (selection != 12);
	}

	private void modifyCLient(Client c) {
		
		int selection = -1;
		
		do {
		System.out.println("1.) Change Username");
		System.out.println("2.) Change Password");
		System.out.println("3.) Change First name");
		System.out.println("4.) Change Last Name");
		System.out.println("5.) Change Address");
		System.out.println("6.) Exit. Warning, Will commit all current changes.");
		
		selection = sc.getInt();
		
		String s;
	
		switch(selection)
		{
		case 1:
			System.out.println("Enter new Username:");
			s = sc.getLine();
			
			if(cS.getClientByUsername(s) != null)
				System.out.println("User name already in use. Select a different name.");
			else
			{
				logger.info("New username set for" + c.getClientID() + "changed from " + c.getUsername() + " to " + s);
				c.setuserName(s);
			}
			
			break;
			
		case 2:
			System.out.println("Enter new Password:");
			s = sc.getLine();
			logger.info("New password set for" + c.getClientID() + "changed from " + c.getPassword() + " to " + s);
			c.setuserPassword(s);
			break;
			
		case 3:
			System.out.println("Enter updated First Name:");
			s = sc.getLine();
			logger.info("First Name set for" + c.getClientID() + "changed from " + c.getFirstName() + " to " + s);
			c.setFirstName(s);
			
			break;
			
		case 4:
			System.out.println("Enter updated Last Name:");
			s = sc.getLine();
			logger.info("Last Name set for" + c.getClientID() + "changed from " + c.getLastName() + " to " + s);
			c.setLastName(s);
			break;
			
		case 5:
			System.out.println("Enter new Address:");
			s = sc.getLine();
			logger.info("Address set for" + c.getClientID() + "changed from " + c.getAddress() + " to " + s);
			c.setAddress(s);
			break;
			
		case 6:
			cS.updateClient(c);
			return;
		}
		
		
		} while (selection != 7);
		return;
	}

	private void modifyAccountStatus(Account a)
	{
		System.out.println(a.getAccountName() + " has a Status of: " + a.getStatus());
		
		
		
		int selection;
		do
		{
			System.out.println("1.) Set to APPROVED");
			System.out.println("2.) Set to DENIED");
			System.out.println("3.) Set to CANCELED");
			System.out.println("4.) Exit");
			
			selection = sc.getInt();
			
			switch (selection)
			{
			case 1:
				aS.updateStatus(AccountStatus.APPROVED, a.getACCOUNT_ID());
				System.out.println(a.getAccountName() + "Set to " + a.getStatus());
				logger.info("Account " + a.getAccountName() + "Status Updated to APPROVED");
				break;
			case 2:
				aS.updateStatus(AccountStatus.DENIED, a.getACCOUNT_ID());
				System.out.println(a.getAccountName() + "Set to " + a.getStatus());
				logger.info("Account " + a.getAccountName() + "Status Updated to DENIED");
				break;
			case 3:
				aS.updateStatus(AccountStatus.CANCELED, a.getACCOUNT_ID());
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

	@Override
	public void createAccount()
	{
		
		System.out.println("Select Client ID: ");
		int clientID = sc.getInt();
		
		Client c = cS.getClientByID(clientID);
		
		if(c == null)
		{
			System.out.println("Client not found. Please try again");
			return;
		}
		
		System.out.println("Name Account: ");
		String accountName = sc.getLine();
		
		Account a = new Account(accountName, AccountStatus.PENDING, 0, c.getClientID());
		
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
