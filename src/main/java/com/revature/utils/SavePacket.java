package com.revature.utils;

import java.io.Serializable;
import java.util.ArrayList;

import com.revature.bank.Account;
import com.revature.bank.Client;

public class SavePacket implements Serializable{

	private static final long serialVersionUID = 1680268286770098386L;
	public ArrayList<Account> accounts;
	public ArrayList<Client> clients;
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	public ArrayList<Client> getClients() {
		return clients;
	}
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	public SavePacket(ArrayList<Account> accounts, ArrayList<Client> clients) {
		super();
		this.accounts = accounts;
		this.clients = clients;
	}
}
