package com.revature.driver;

import java.util.ArrayList;
import java.util.UUID;

import com.revature.bank.Account;
import com.revature.bank.AccountManager;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;

public class ClientDriver {

	private ArrayList<Client> clientList;
	
	private Client currentClient;
	static ScannerSingleton sc = new ScannerSingleton();
	AccountManager aM;
	
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
			}
		}
		
		if(validLogIn == false)
		{
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
						// TODO Auto-generated catch block
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
			case 555:
				System.out.println("Current Client: " + currentClient.toString());
				System.out.println("Client List: ");
					
				for(Client c : clientList)
						System.out.println(c.toString());
				
				System.out.println("---------------------------------------------");
				System.out.println("Account List: ");
				
				for(Account a2 : aM.getAllAccounts())
					System.out.println(a2.toString());
				
				System.out.println("---------------------------------------------");

				
				break;
			default:
				break;
			}
			selection = -1;
		} while(selection != 4);

	}

	private void manageAccount(Account a) throws Exception {
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
				int withdrawValue = sc.getInt();

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
				System.out.printf("Balance: " + "%.2f \n", a.getBalance());

				break;
				
			case 3: //Deposits money to current Account
				int depositValue = sc.getInt();
				
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
				int transferValue = sc.getInt();
				
				System.out.println("Enter target account's ID: ");
				String transferAccountID = sc.getLine();
				Account tAccount = aM.getAccountByAccountID(UUID.fromString(transferAccountID));
				
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
				String userName = sc.getLine();
				
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
				return;
				
			case 1001:
				a.setStatus(AccountStatus.APPROVED);
				break;
				
			default:
				break;

			}
			selection = -1;
		}while(selection != 6);
	}
	

	public void createClient()
	{	
		System.out.println("Please your User Name: ");
		
		String userName = sc.getLine();
		while(userName.length() == 0)
		{
			System.out.println("Please your User Name: ");
			userName = sc.getLine();
		}
		
		System.out.println("Please Enter Password: ");
		String password = sc.getLine();
		while(password.length() == 0)
		{
			System.out.println("Please your User Name: ");
			password = sc.getLine();
		}
		
		System.out.println("Please Enter your First Name: ");
		String fName = sc.getLine();
		while(password.length() == 0)
		{
			System.out.println("Please your User Name: ");
			fName = sc.getLine();
		}
		
		System.out.println("Please Enter your Last Name: ");
		String lName = sc.getLine();
		while(password.length() == 0)
		{
			System.out.println("Please your User Name: ");
			lName = sc.getLine();
		}
		
		System.out.println("Please Enter your Address: ");
		String address = sc.getLine();
		while(password.length() == 0)
		{
			System.out.println("Please your User Name: ");
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
		
		for(Client c2 : clientList)
			System.out.println(c2.toString());
	}
	
	public void createAccount()
	{
		System.out.println("Name your account: ");
		String accountName = sc.getLine();
		
		Account a = new Account(accountName, AccountStatus.PENDING, 0, currentClient.getClientID());
		aM.addAccount(a);
		currentClient.getOwnedAccounts().add(a.getACCOUNT_ID());
	}
}
