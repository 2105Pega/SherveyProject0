package com.revature.services;

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
		System.out.println("From Client Service " + b);
		return b;
	}
	
	public boolean removeClientByUsernameAndPassword(String user, String pass) {
		// TODO Auto-generated method stub
		return cD.removeClientByUserAndPassword(user, pass);
	}
	
}
