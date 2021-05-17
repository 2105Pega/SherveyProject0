import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.bank.Client;
import com.revature.driver.ClientDriver;

class ClientAndEmployeeMenuTester {

	ArrayList<Client> cList;
	
	@BeforeEach
	void setAccounts()
	{
		cList = new ArrayList<Client>();

		cList.add(new Client("User", "Pass", "Fname", "Lname", "Address"));
		cList.add(new Client("User2", "Pass", "Fname", "Lname", "Address"));
		
	}
	
	@Test
	public void logInTest()
	{
		ClientDriver cm = new ClientDriver(cList, null);
		
		assertEquals(-1,cm.logIn("Nope", "Invalid"));
		assertEquals(1,cm.logIn("User", "Pass"));
	}
	
}
