package com.revature.services;

import java.util.ArrayList;

import com.revature.bank.Client;
import com.revature.daos.ClientDAO;
import com.revature.daos.ClientDOAIMP;

public class ClientService {

	private ClientDAO cD = new ClientDOAIMP();
	
	
	public boolean addClient(Client c)
	{
		return cD.addClient(c);
	}


	public Client getClientByID(int i) {
		return cD.getClientByID(i);
	}


	public boolean removeClientByID(int i) {
		// TODO Auto-generated method stub
		boolean b = cD.removeClientByID(i);
		return b;
	}
	
	public boolean removeClientByUsernameAndPassword(String user, String pass) {
		// TODO Auto-generated method stub
		return cD.removeClientByUserAndPassword(user, pass);
	}
	
	public ArrayList<Integer> getOwnedAccounts(Client c)
	{
		return cD.getOwnedAccounts(c);
	}
	
	public ArrayList<Integer> getCoOwnedAccounts(Client c)
	{
		return cD.getCoOwnedAccounts(c);
	}
}
