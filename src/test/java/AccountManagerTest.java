/*
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.bank.Account;
import com.revature.bank.AccountManager;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;
*/
class AccountManagerTest {
	/*
	AccountManager aM;

	@BeforeEach
	void setAccounts()
	{
		ArrayList<Client> cList = new ArrayList<Client>();
		//String userName, String password, String firstName, String lastName, String address
		cList.add(new Client("Test1", "Test1", "Test1", "McTest1", "Test Street 1")); 
		cList.add(new Client("Test2", "Test2", "Test2", "McTest2", "Test Street 2")); 
		
		ArrayList<Account> aList = new ArrayList<Account>();
		
		//Name, Status, Balance, OwnerID, CoOwnerID
		Account a = new Account("Test Account Approved", AccountStatus.APPROVED, 1000, cList.get(0).getClientID());
		aList.add(a);
		
		 a = new Account("Test Account Denied", AccountStatus.DENIED, 1000, cList.get(0).getClientID());
		aList.add(a);
		
		a = new Account("Test Account Pending", AccountStatus.CANCELED, 1000, cList.get(0).getClientID());
		aList.add(a);
		
		a = new Account("Test Account CANCELED", AccountStatus.PENDING, 1000, cList.get(0).getClientID());
		aList.add(a);
		
		a = new Account("Test Account 2", AccountStatus.APPROVED, 1000, cList.get(1).getClientID());
		aList.add(a);
		
		aM = new AccountManager(aList);
	}
	
	@Test
	public void addAccountTest()
	{
		Account a = new Account("Test Account 3", AccountStatus.APPROVED, 1000, aM.getAllAccounts().get(0).getOwnerID());
		
		aM.addAccount(a);
		
		assertEquals(6, aM.getAllAccounts().size());
		assertEquals(0, aM.getAllAccounts().get(0).getOwnerID().compareTo(aM.getAllAccounts().get(5).getOwnerID()));
		
		assertThrows(NullPointerException.class, () -> aM.addAccount(null));
		assertThrows(IllegalArgumentException.class, () -> aM.addAccount(a));	
	}
	
	@Test
	public void removeAccount()
	{
		aM.removeAccounts();
		assertEquals(3, aM.getAllAccounts().size());
		
		aM.removeAccounts();
		assertEquals(3, aM.getAllAccounts().size());
	}
	
	@Test
	public void getAccountsByOwnerID()
	{
		UUID clientID = UUID.randomUUID();
		
		Account a = new Account("Test Account Approved", AccountStatus.APPROVED, 1000, clientID);
		
		aM.addAccount(a);
		
		assertEquals(true, a.getACCOUNT_ID().equals(aM.getAccountsByOwnerID(clientID).get(0).getACCOUNT_ID()));
		assertEquals(1, aM.getAccountsByOwnerID(clientID).size());
		assertEquals(4, aM.getAccountsByOwnerID(aM.getAllAccounts().get(0).getOwnerID()).size());
		assertEquals(0, aM.getAccountsByOwnerID(UUID.randomUUID()).size());
		assertThrows(NullPointerException.class, () -> aM.getAccountsByOwnerID(null));	
	}
	
	@Test
	public void getAccountsByCoOwnerID()
	{
		UUID clientID = UUID.randomUUID(); 
		Account a = aM.getAllAccounts().get(0);
		Account a2 = aM.getAllAccounts().get(1);
		
		aM.getAllAccounts().get(0).addCOwner(clientID);
		
		assertEquals(true, a.getACCOUNT_ID().equals(aM.getAccountsByCoOwnerID(clientID).get(0).getACCOUNT_ID()));
		assertEquals(1, aM.getAccountsByCoOwnerID(clientID).size());
		
		aM.getAllAccounts().get(1).addCOwner(clientID);
		assertEquals(true, a2.getACCOUNT_ID().equals(aM.getAccountsByCoOwnerID(clientID).get(1).getACCOUNT_ID()));
		assertEquals(2, aM.getAccountsByCoOwnerID(clientID).size());
		
		assertThrows(NullPointerException.class, () -> aM.getAccountsByCoOwnerID(null));
	}
	
	@Test
	public void getAccountByAccountID()
	{
		Account a = aM.getAllAccounts().get(0);
		Account a2 = aM.getAllAccounts().get(1);
		
		assertEquals(true, a.getACCOUNT_ID().equals(aM.getAccountByAccountID(a.getACCOUNT_ID()).getACCOUNT_ID()));
		assertEquals(true, a2.getACCOUNT_ID().equals(aM.getAccountByAccountID(a2.getACCOUNT_ID()).getACCOUNT_ID()));
		assertNull(aM.getAccountByAccountID(UUID.randomUUID()));
		assertThrows(NullPointerException.class, () -> aM.getAccountByAccountID(null));
	}
	
	@Test
	public void withdraw()
	{
		
		aM.withdraw(aM.getAllAccounts().get(0), 500);
		assertEquals(500, aM.getAllAccounts().get(0).getBalance());
		
		assertThrows(IllegalArgumentException.class,() -> aM.withdraw(aM.getAllAccounts().get(0), -500));
		assertThrows(IllegalStateException.class,() -> aM.withdraw(aM.getAllAccounts().get(1), 500));
		assertThrows(IllegalStateException.class,() -> aM.withdraw(aM.getAllAccounts().get(2), 500));
		assertThrows(IllegalStateException.class,() -> aM.withdraw(aM.getAllAccounts().get(3), 500));
		assertThrows(NullPointerException.class,() -> aM.withdraw(null, 500));
	}
	
	@Test
	public void deposit()
	{
		aM.deposit(aM.getAllAccounts().get(0), 500);
		assertEquals(1500, aM.getAllAccounts().get(0).getBalance());
		
		assertThrows(IllegalArgumentException.class,() -> aM.deposit(aM.getAllAccounts().get(0), -500));
		assertThrows(IllegalStateException.class,() -> aM.deposit(aM.getAllAccounts().get(1), 500));
		assertThrows(IllegalStateException.class,() -> aM.deposit(aM.getAllAccounts().get(2), 500));
		assertThrows(IllegalStateException.class,() -> aM.deposit(aM.getAllAccounts().get(3), 500));
		assertThrows(NullPointerException.class,() -> aM.deposit(null, 500));
	}
	
	@Test
	public void transfer()
	{
		
		aM.transfer(aM.getAllAccounts().get(4), aM.getAllAccounts().get(0), 500);
		
		assertEquals(1500, aM.getAllAccounts().get(0).getBalance());
		assertEquals(500, aM.getAllAccounts().get(4).getBalance());
		
		assertThrows(IllegalArgumentException.class,() -> aM.transfer(aM.getAllAccounts().get(4), aM.getAllAccounts().get(0), -500));
		assertThrows(NullPointerException.class,() -> aM.transfer(null, aM.getAllAccounts().get(0), 500));
		assertThrows(NullPointerException.class, () -> aM.transfer(aM.getAllAccounts().get(4), null, 500));
		assertThrows(IllegalArgumentException.class,() -> aM.transfer(aM.getAllAccounts().get(4), aM.getAllAccounts().get(4), 500));
		
		assertThrows(IllegalStateException.class,() -> aM.transfer(aM.getAllAccounts().get(4), aM.getAllAccounts().get(1), 500));
		assertThrows(IllegalStateException.class,() -> aM.transfer(aM.getAllAccounts().get(4), aM.getAllAccounts().get(2), 500));
		assertThrows(IllegalStateException.class,() -> aM.transfer(aM.getAllAccounts().get(4), aM.getAllAccounts().get(3), 500));
		
		assertThrows(IllegalStateException.class,() -> aM.transfer(aM.getAllAccounts().get(1), aM.getAllAccounts().get(4), 500));
		assertThrows(IllegalStateException.class,() -> aM.transfer(aM.getAllAccounts().get(2), aM.getAllAccounts().get(4), 500));
		assertThrows(IllegalStateException.class,() -> aM.transfer(aM.getAllAccounts().get(3), aM.getAllAccounts().get(4), 500));
		
	}
	*/
}
