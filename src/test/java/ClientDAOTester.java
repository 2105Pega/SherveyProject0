import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.bank.Client;
import com.revature.services.ClientService;

class ClientDAOTester {
	ClientService cs = new ClientService();
	ArrayList<Client> cList;

	@BeforeEach
	public void setUp()
	{
		//int id, String userName, String password, String firstName, String lastName, String address
		cList.add(new Client(101, "Test1", "Test1", "TestfName", "TestlName", "Test Address"));
		cList.add(new Client(102, "Test1", "Test1", "TestfName", "TestlName", "Test Address"));
	}
	
	@AfterEach
	public void breakDown()
	{
		//int id, String userName, String password, String firstName, String lastName, String address
		System.out.println("Removing First Test Client, Status: " + cs.removeClientByID(101));
		System.out.println("Removing Second Test Client, Status: " + cs.removeClientByID(102));
	}
	
	@Test
	public void testGetClientByID()
	{

	}
	
	public void testGetClientByUserAndPass()
	{
		
	}

	public void testAddClient()
	{
		
	}
	
	public void testRemoveClientByUserAndPassword()
	{
		
	}
	
	public void testRemoveClientByID()
	{
		
	}
	
	public void testUpdateClient()
	{
		
	}
}
