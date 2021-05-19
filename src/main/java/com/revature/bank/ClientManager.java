package com.revature.bank;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientManager {

	private static ArrayList<Client> clientList;
	private static final Logger logger = LogManager.getLogger(ClientManager.class);
	
	public void addClient(Client c)
	{
		clientList.add(c);
	}
	
	public Client createClient()
	{
		return null;
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
	
}
