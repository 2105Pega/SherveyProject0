package com.revature.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import com.revature.bank.Account;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;

public class BankDriver {
	
	
	//TODO: Fold Employee, Client, and Account into a set of 3 entries in 1 ArrayList to r/w them in all at once.
	static ClientDriver clientDriver;
	
	public static void main(String args[])
	{
		ArrayList<Client> clientList = getClientList();
		ArrayList<Account> accountList = getAccountList();
		clientDriver = new ClientDriver(clientList, accountList);
		
		menu();
		
		saveClients(clientList);
		//saveAccounts(accountList);
	}

	
	private static void saveAccounts(ArrayList<Account> accountList) {
		File accountFile = new File("accounts.txt");
		
		if(!accountFile.exists())
			accountFile.createNewFile();
		
		FileOutputStream fos = new FileOutputStream(accountFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		for(Account a : accountList)
			oos.writeObject(a);
		
		oos.close();
		fos.close();
		
	}


	private static void saveClients(ArrayList<Client> clientList) {
		File clientsFile = new File("clients.txt");
		
		if(!clientsFile.exists())
			clientsFile.createNewFile();
		
		FileOutputStream fos = new FileOutputStream(clientsFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		for(Client c : clientList)
			oos.writeObject(c);
		
		oos.close();
		fos.close();
	}


	private static ArrayList<Account> getAccountList() 
	{
		File accountFile = new File("accounts.txt");
		
		if(!accountFile.exists())
		{
			return new ArrayList<Account>();
		}
		
		FileInputStream fis = new FileInputStream(accountFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Account a;
		ArrayList<Account> accountList = new ArrayList<Account>();
		
		while(fis.available() > 0)
		{
			a = (Account) ois.readObject();
			accountList.add(a);
		}
		
		ois.close();
		fis.close();
		
		return accountList;
		
	}


	private static ArrayList<Client> getClientList()  {
		File clientFile = new File("clients.txt");
		
		if(!clientFile.exists())
		{
			return new ArrayList<Client>();
		}
		
		FileInputStream fis = new FileInputStream(clientFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Client c;
		ArrayList<Client> clientList = new ArrayList<Client>();
		
		while(fis.available() > 0)
		{
			c = (Client) ois.readObject();
			clientList.add(c);
		}
		
		return clientList;
	}


	public static void menu()
	{
		Scanner kIn = new Scanner(System.in);
		int selection;
		
		do
		{
			displayMainMenu();
			selection = kIn.nextInt();
			kIn.nextLine(); //Clears scanner so that futher prompts do not read the linebreak
			switch (selection)
			{
			case 1:
				//Go to ClientManager having Logged IN
				System.out.println("Client Log In Selected.");
				
				System.out.println("Enter Username: ");
				String userName = kIn.nextLine();
				System.out.println("Enter Password: ");
				String password = kIn.nextLine();
				
				clientDriver.logIn(userName, password);
				
				break;
			case 2:
				System.out.println("Create Client Account Selected.");
				clientDriver.createClient();
				break;
			case 3:
				//Go to EmployeeManager and validate login.
				System.out.println("Employee Log In Selected.");
				break;
			case 4:
				System.out.println("Exiting.");
				break;
			default:
				System.out.println("Error, invalid selection made. Please make a valid selection.");
				break;
			}
			
		} while (selection != 4);
		
		kIn.close();
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
