import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.bank.Account;
import com.revature.bank.AccountStatus;

class AccountTest {

	ArrayList<Account> aList;
	
	@BeforeEach
	void setAccounts()
	{
		aList = new ArrayList<Account>();
		
		//Name, Status, Balance, OwnerID, CoOwnerID
		Account a = new Account("Test Account Approved", AccountStatus.APPROVED, 1000, UUID.randomUUID());
		aList.add(a);
		
		 a =new Account("Test Account Denied", AccountStatus.DENIED, 1000, UUID.randomUUID());
		aList.add(a);
		
		a = new Account("Test Account Pending", AccountStatus.CANCELED, 1000, UUID.randomUUID());
		aList.add(a);
		
		a = new Account("Test Account CANCELED", AccountStatus.PENDING, 1000, UUID.randomUUID());
		aList.add(a);
		
		a = new Account("Test Account 2", AccountStatus.APPROVED, 1000, UUID.randomUUID());
		aList.add(a);
	}
	
	@Test
	public void testWithdraw()
	{
		aList.get(0).withdraw(100);
		assertEquals(900, aList.get(0).getBalance());
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> aList.get(0).withdraw(-100));
		Assertions.assertThrows(IllegalArgumentException.class,() -> aList.get(0).withdraw(0));
		
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(1).withdraw(100));
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(2).withdraw(100));
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(3).withdraw(100));
	}
	
	@Test
	public void testDeposit()
	{
		aList.get(0).deposit(100);
		assertEquals(1100, aList.get(0).getBalance());
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> aList.get(0).deposit(-100));
		Assertions.assertThrows(IllegalArgumentException.class,() -> aList.get(0).deposit(0));
		
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(1).deposit(100));
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(2).deposit(100));
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(3).deposit(100));
	}
	
	@Test
	public void testTransfer()
	{
		aList.get(0).transfer(aList.get(4), 500);
		assertEquals(500, aList.get(0).getBalance());
		assertEquals(1500, aList.get(4).getBalance());
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> aList.get(0).transfer(aList.get(4), -500));
		Assertions.assertThrows(IllegalArgumentException.class,() -> aList.get(0).transfer(aList.get(4), 0));
		
		Assertions.assertThrows(NullPointerException.class,() -> aList.get(0).transfer(null, 100));
		//IllegalStateException
		
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(1).transfer(aList.get(0), 100));
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(2).transfer(aList.get(0), 100));
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(3).transfer(aList.get(0), 100));
		
		
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(0).transfer(aList.get(1), 100));
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(0).transfer(aList.get(2), 100));
		Assertions.assertThrows(IllegalStateException.class,() -> aList.get(0).transfer(aList.get(3), 100));
	}
	
	@Test
	public void testAddCoOwner()
	{
		UUID u = UUID.randomUUID();
		UUID u2 = UUID.randomUUID();
		UUID u3 = UUID.randomUUID();
		
		aList.get(0).addCOwner(u);
		aList.get(0).addCOwner(u2);
		aList.get(0).addCOwner(u3);
		
		assertEquals(0, aList.get(0).getCoOwnerIDs().get(0).compareTo(u));
		assertEquals(0, aList.get(0).getCoOwnerIDs().get(1).compareTo(u2));
		assertEquals(0, aList.get(0).getCoOwnerIDs().get(2).compareTo(u3));
		
		Assertions.assertThrows(NullPointerException.class,() -> aList.get(0).addCOwner(null));
	}
	
}
