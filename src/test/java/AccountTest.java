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
