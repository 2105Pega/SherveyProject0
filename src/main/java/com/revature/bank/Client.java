package com.revature.bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7421988102066457787L;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private final UUID clientID;
	private ArrayList<UUID> ownedAccounts;
	private ArrayList<UUID> coOwnedAccounts;
	
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	public UUID getClientID() {	return clientID;}
	public void setuserName(String userName) {this.userName = userName;}
	public String getUsername() {return this.userName;}
	public void setuserPassword(String password) {this.password = password;}
	public String getPassword() {return this.password;}
	public void setOwnedAccounts(ArrayList<UUID> newList) {this.ownedAccounts = newList;}
	public ArrayList<UUID> getOwnedAccounts() {return this.ownedAccounts;}
	public void setCoOwnedAccounts(ArrayList<UUID> newList) {this.ownedAccounts = newList;}
	public ArrayList<UUID> getCoOwnedAccounts() {return this.coOwnedAccounts;}
	
	public Client()
	{
		super();
		this.clientID = UUID.randomUUID();
	}
	
	public Client(String userName, String password, String firstName, String lastName, String address) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.clientID = UUID.randomUUID();
		this.ownedAccounts = new ArrayList<UUID>();
		this.coOwnedAccounts = new ArrayList<UUID>();
	}
	@Override
	public String toString() {
		return "Client [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", clientID=" + clientID + "]";
	}
	
	public boolean equals(Client c)
	{
		if(c.getClientID() == this.clientID)
			return true;
		else
			return false;
	}
	
}
