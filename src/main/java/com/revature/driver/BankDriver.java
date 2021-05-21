package com.revature.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reavture.utils.SavePacket;
import com.reavture.utils.ScannerSingleton;
import com.revature.bank.Account;
import com.revature.bank.AccountManager;
import com.revature.bank.Client;
import com.revature.bank.ClientManager;

public class BankDriver {
	
	//TODO: Fold Employee, Client, and Account into a set of 3 entries in 1 ArrayList to r/w them in all at once.
	static ClientDriver clientDriver;
	static EmployeeDriver employeeDriver;
	static ScannerSingleton sc = new ScannerSingleton();
	private static final Logger logger = LogManager.getLogger(BankDriver.class);
	public static void main(String args[])
	{
		logger.info("Starting Main Driver.");
		
		SavePacket s = loadSave();
		ArrayList<Client> clientList;
		ArrayList<Account> accountList;
		
		if(s != null)
		{
			clientList = s.getClients();
			accountList = s.getAccounts();
		}
		else
		{	
			logger.warn("Could not load save file. Creating new client and account list.");
			clientList = new ArrayList<Client>();
			accountList = new ArrayList<Account>();
		}
		
		AccountManager accountManager = new AccountManager(accountList);
		ClientManager clientManager = new ClientManager(clientList);
		clientDriver = new ClientDriver(clientManager, accountManager);
		employeeDriver = new EmployeeDriver(clientManager, accountManager);
		
		menu(clientManager);
		
		save(clientList, accountList);
	}

	
	private static void save(ArrayList<Client> clientList, ArrayList<Account> accountList) {
		File accountFile = new File("Information.txt");
		
		logger.info("Starting Save.");
		
		try {
		if(!accountFile.exists())
			accountFile.createNewFile(); //IOexception
		
		FileOutputStream fos = new FileOutputStream(accountFile); //FileNotFoundException
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		SavePacket save = new SavePacket(accountList, clientList);
		
		oos.writeObject(save);
		
		oos.close();
		fos.close();
		logger.info("Save Completed.");
		}
		catch (IOException e)
		{
			logger.error("Save Failed to Complete." + e.getMessage());
		}
		
	}

	private static SavePacket loadSave() 
	{
		File accountFile = new File("Information.txt");
		
		try {
		if(!accountFile.exists())
			accountFile.createNewFile(); //IOexception
		
		FileInputStream fis = new FileInputStream(accountFile); //FileNotFoundException
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		SavePacket save = (SavePacket) ois.readObject();
		
		ois.close();
		fis.close();
		return save;
		}
		catch (IOException e)
		{
			logger.error("Load Failed to Complete Load. Could not Import Client or Account List" + e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			logger.error("Load Failed to Complete Load when reading from file. Could not Import Client or Account List" + e.getMessage());
		}
		return null;
	}

	public static void menu(ClientManager cM)
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
				cM.createClient();
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
