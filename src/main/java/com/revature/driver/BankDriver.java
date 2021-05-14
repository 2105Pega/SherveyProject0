package com.revature.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bank.Account;
import com.revature.bank.Client;
import com.revature.bank.SavePacket;

public class BankDriver {
	
	
	//TODO: Fold Employee, Client, and Account into a set of 3 entries in 1 ArrayList to r/w them in all at once.
	static ClientDriver clientDriver;
	
	public static void main(String args[])
	{
		
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
			clientList = new ArrayList<Client>();
			accountList = new ArrayList<Account>();

		}
		clientDriver = new ClientDriver(clientList, accountList);
		
		menu();
		
		save(clientList, accountList);
		//saveAccounts(accountList);
	}

	
	private static void save(ArrayList<Client> clientList, ArrayList<Account> accountList) {
		File accountFile = new File("Information.txt");
		
		try {
		if(!accountFile.exists())
			accountFile.createNewFile(); //IOexception
		
		FileOutputStream fos = new FileOutputStream(accountFile); //FileNotFoundException
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		SavePacket save = new SavePacket(accountList, clientList);
		
		oos.writeObject(save);
		
		oos.close();
		fos.close();
		}
		catch (IOException e)
		{
			System.out.println("Warning: Exception Occured During Save. Information May Be Lost.");
			System.out.println(e.getMessage());
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
			System.out.println("Warning: Exception Occured During Load. Account and Client Lists Could Not Be Imported.");
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Warning: Exception Occured During Load. Could Not Read Save File Correctly.");
			System.out.println(e.getMessage());
		}
		return null;
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
