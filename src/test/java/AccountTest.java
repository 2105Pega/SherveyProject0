import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

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
	
	}
	
	@Test
	void transactionTest() {
		/*
		aList.get(0).transaction(1000);
		assertEquals(aList.get(0).getBalance(), 11000);
		aList.get(0).transaction(-1000);
		assertEquals(aList.get(0).getBalance(), 10000);
		*/
	}
	
	@Test
	void transferTest() {
		/*
		aList.get(0).transfer(aList.get(1), 3000);
		assertEquals(aList.get(0).getBalance(), 7000);
		assertEquals(aList.get(1).getBalance(), 13000);
		*/
	}

}
