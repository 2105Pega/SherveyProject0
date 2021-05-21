package com.revature.driver;

import java.sql.Connection;
import java.sql.SQLException;

import com.reavture.utils.ConnectionUtils;
import com.revature.bank.Client;
import com.revature.services.ClientService;

public class tester {

	public static void main(String[] args) 
	{
		
		Client nC = new Client(15, "TestUserName", "TestPass", "Test FName", "Test LName", "Test Address");
		ClientService cs = new ClientService();
		
		//System.out.println(cs.addClient(nC));
		//System.out.println(cs.getClientByID(15).toString());
		
		System.out.println(cs.removeClientByID(5));
	}
}
