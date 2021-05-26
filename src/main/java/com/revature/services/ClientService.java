package com.revature.services;

import java.util.ArrayList;

import com.revature.bank.Client;
import com.revature.daos.ClientDAO;
import com.revature.daos.ClientDOAIMP;

public class ClientService {

	private ClientDAO cD = new ClientDOAIMP();
	
	
	public Client getClientByID(int id)
	{
		return cD.getClientByID(id);
	}
	
	public Client getClientByUserAndPass(String username, String password)
	{
		return cD.getClientByUserAndPass(username, password);
	}
	
	public boolean addClient(Client c)
	{
		return cD.addClient(c);
	}
	
	public boolean removeClientByUserAndPassword(String username, String password)
	{
		return cD.removeClientByUserAndPassword(username, password);
	}
	
	public boolean removeClientByID(int id)
	{
		return cD.removeClientByID(id);
	}
	
	public boolean updateClient(Client c)
	{
		return cD.updateClient(c);
	}
	
	public Client getClientByUsername(String username)
	{
		return cD.getClientByUsername(username);
	}
	
	public ArrayList<Client> getAllClients()
	{
		return cD.getAllClients();
	}
}
