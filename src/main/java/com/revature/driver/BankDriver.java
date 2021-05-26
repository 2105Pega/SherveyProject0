package com.revature.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reavture.utils.ScannerSingleton;

public class BankDriver {
	
	//TODO: Fold Employee, Client, and Account into a set of 3 entries in 1 ArrayList to r/w them in all at once.
	static ClientDriver clientDriver;
	static EmployeeDriver employeeDriver;
	static ScannerSingleton sc = new ScannerSingleton();
	private static final Logger logger = LogManager.getLogger(BankDriver.class);
	public static void main(String args[])
	{
		logger.info("Starting Main Driver.");
		
		clientDriver = new ClientDriver();
		employeeDriver = new EmployeeDriver();
		
		menu(clientDriver);
	}

	public static void menu(ClientDriver cD)
	{
		int selection = -1;
		
		do
		{
			displayMainMenu();
			selection = sc.getInt();
			String username;
			String password;
			
			
			System.out.println(selection);
			
			switch (selection)
			{
			case 1:
				//Go to ClientManager having Logged IN
				System.out.println("Client Log In Selected.");
				
				System.out.println("Enter Username: ");
				username = sc.getLine();
				System.out.println("Enter Password: ");
				password = sc.getLine();
				
				clientDriver.logIn(username, password);
				
				break;
			case 2:
				System.out.println("Create Client Account Selected.");
				cD.createClient();
				break;
			case 3:
				System.out.println("Employee Log In Selected.");
				
				System.out.println("Employee In Selected.");
				
				System.out.println("Enter Username: ");
				username = sc.getLine();
				System.out.println("Enter Password: ");
				password = sc.getLine();
				
				employeeDriver.logIn(username, password);
				
				break;
			case 4:
				System.out.println("Exiting.");
				break;
			default:
				System.out.println("Error, invalid selection made. Please make a valid selection.");
				break;
			}
		} while (selection != 4);
	}

	private static void displayMainMenu() 
	{
		String menu[] = new String[5];
		menu[0] = "Welcome to the Bank of Moneylovers!\nPlease select one of the follow options.";
		menu[1] = "1.) Client Log In";
		menu[2] = "2.) Create Client Account";
		menu[3] = "3.) Employee Log In";
		menu[4] = "4.) Exit";
		
		for(String s : menu)
			System.out.println(s);
	}
}
