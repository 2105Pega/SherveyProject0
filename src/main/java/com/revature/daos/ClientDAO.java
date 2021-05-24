package com.revature.daos;

import java.util.ArrayList;
import com.revature.bank.Client;

public interface ClientDAO {

	public Client getClientByID(int id);
	public Client getClientByUserAndPass(String username, String password);
	public Client getClientByUsername(String username);
	public ArrayList<Client> getAllClients();
	public boolean addClient(Client c);
	public boolean removeClientByUserAndPassword(String username, String password);
	public boolean removeClientByID(int id);
	public boolean updateClient(Client c);
}
