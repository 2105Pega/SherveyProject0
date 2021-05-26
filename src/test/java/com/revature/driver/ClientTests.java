package com.revature.driver;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.reavture.utils.ConnectionUtils;
import com.revature.bank.Client;
import com.revature.daos.ClientDOAIMP;

class ClientTests {

	ClientDOAIMP cD = new ClientDOAIMP();
	Client cL;
	
	@BeforeEach
	public void setUp() throws SQLException
	{
		Connection c = ConnectionUtils.getConnection();
		
		cL = new Client(1001, "JClient", "JPass", "Jerry", "Jack", "Juntiville");
		
		String sql = "insert into clients(client_id, username, password, firstname, lastname, address) "
				+ "values (?, ?, ?, ?, ?, ?);"; //REMEMBER SMART ONE, INDEX STARTS AT 1 FOR SQL!!!
		
		PreparedStatement statement = c.prepareStatement(sql);
		statement.setInt(1, cL.getClientID());
		statement.setString(2, cL.getUsername());
		statement.setString(3, cL.getPassword());
		statement.setString(4, cL.getFirstName());
		statement.setString(5, cL.getLastName());
		statement.setString(6, cL.getAddress());
		
		
		statement.execute();
	}
	
	@AfterEach
	public void tearDown() throws SQLException
	{
		Connection c = ConnectionUtils.getConnection();
		
		cL = new Client(1001, "JClient", "JPass", "Jerry", "Jack", "Juntiville");
		
		String sql = "delete from clients where client_id =?;";
		PreparedStatement statement = c.prepareStatement(sql);
		statement.setInt(1, 1001);
		
		statement.execute();
	}
	
	@Test
	public void getClientByID()
	{
		Client c2 = cD.getClientByID(1001);
		
		assertEquals(cL.getClientID(), c2.getClientID());
		assertEquals(cL.getFirstName(), c2.getFirstName());
		assertEquals(cL.getLastName(), c2.getLastName());
		assertEquals(cL.getPassword(), c2.getPassword());
		assertEquals(cL.getAddress(), c2.getAddress()); 
	}
	public void getClientByUserAndPass()
	{
		Client c2 = cD.getClientByUserAndPass("JClient", "JPass");
		
		assertEquals(cL.getClientID(), c2.getClientID());
		assertEquals(cL.getFirstName(), c2.getFirstName());
		assertEquals(cL.getLastName(), c2.getLastName());
		assertEquals(cL.getPassword(), c2.getPassword());
		assertEquals(cL.getAddress(), c2.getAddress());
	}
	public void getClientByUsername()
	{
		Client c2 = cD.getClientByUsername("JClient");
		
		assertEquals(cL.getClientID(), c2.getClientID());
		assertEquals(cL.getFirstName(), c2.getFirstName());
		assertEquals(cL.getLastName(), c2.getLastName());
		assertEquals(cL.getPassword(), c2.getPassword());
		assertEquals(cL.getAddress(), c2.getAddress());
	}
	public void getAllClients()
	{
		ArrayList<Client> cList = cD.getAllClients();
		assertEquals(cList.isEmpty(), false);
		assertEquals(cList.get(0).getClass(), cL.getClass());
	}
	public void addClient() throws SQLException
	{
		Client cl2 = new Client(1002, "JClient2", "JPass", "Jerry", "Jack", "Juntiville");
		assertEquals(cD.addClient(cl2), true);
	
		Client c2 = cD.getClientByID(1002);
		
		assertEquals(cl2.getClientID(), c2.getClientID());
		assertEquals(cl2.getFirstName(), c2.getFirstName());
		assertEquals(cl2.getLastName(), c2.getLastName());
		assertEquals(cl2.getPassword(), c2.getPassword());
		assertEquals(cl2.getAddress(), c2.getAddress());
		
		Connection c = ConnectionUtils.getConnection();
		String sql = "delete from clients where client_id =1002;";
		PreparedStatement statement = c.prepareStatement(sql);
		
		statement.execute();
	}
	public void removeClientByUserAndPassword()
	{
		assertEquals(true, cD.removeClientByUserAndPassword("JClient", "JPass"));
		assertEquals(false, cD.removeClientByUserAndPassword("NA", "NA"));
	}
	public void removeClientByID()
	{
		assertEquals(true, cD.removeClientByID(1001));
		assertEquals(false, cD.removeClientByID(1500));
	}
	public void updateClient()
	{
		cL = new Client(1001, "JClient2", "JPass2", "Jerry2", "Jack2", "Juntiville2");
		
		assertEquals(true, cD.updateClient(cL));
		
		Client c2 = cD.getClientByID(1001);
		
		assertEquals(cL.getClientID(), c2.getClientID());
		assertEquals(cL.getFirstName(), c2.getFirstName());
		assertEquals(cL.getLastName(), c2.getLastName());
		assertEquals(cL.getPassword(), c2.getPassword());
		assertEquals(cL.getAddress(), c2.getAddress()); 
	}

}
