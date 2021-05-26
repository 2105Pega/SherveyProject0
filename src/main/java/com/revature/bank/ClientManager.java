package com.revature.bank;
/*
import java.util.ArrayList;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reavture.utils.ScannerSingleton;
*/
public class ClientManager {
/*
	private static ArrayList<Client> clientList;
	private static final Logger logger = LogManager.getLogger(ClientManager.class);
	
	public ClientManager(ArrayList<Client> clientList)
	{
		ClientManager.clientList = clientList;
	}
	
	public void createClient()
	{
		ScannerSingleton sc = new ScannerSingleton();
		
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

	public void addClient(Client c)
	{
		clientList.add(c);
	}
	
	public void removeClient(Client c)
	{
		clientList.remove(c);
	}
	
	public Client getClientByID(UUID u)
	{
		for (Client c : clientList)
		{
			if(c.getClientID().equals(u))
				return c;
		}
		return null;
	}
	
	public ArrayList<Client> getClientsByID(ArrayList<UUID> idList)
	{
		ArrayList<Client> list2 = new ArrayList<Client>();
		
		for(Client c : clientList)
		{
			for(UUID u : idList)
			{
				if(u.equals(c.getClientID()))
					list2.add(c);
			}
		}
		return list2;
	}
	
	
	public Client getClientByUserName(String user)
	{
		for(Client c : clientList)
		{
			if(c.getUsername().equals(user))
				return c;
		}
		return null;
	}
	
	public ArrayList<Client> getAllClients()
	{
		return clientList;
	}
	
	public String getClientsSummary()
	{
		return null;
	}
	
	public void changeUserName(Client c, String newUserName)
	{
		c.setuserName(newUserName);
	}
	public void changePassword(Client c, String newPassword)
	{
		c.setuserPassword(newPassword);
	}
	public void changeFirstName(Client c, String newFirstName)
	{
		c.setFirstName(newFirstName);
	}
	public void changeLastName(Client c, String newLastName)
	{
		c.setLastName(newLastName);
	}
	public void changeAddress(Client c, String newAddress)
	{
		c.setAddress(newAddress);
	}
	*/
}
