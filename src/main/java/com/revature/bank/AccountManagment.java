package com.revature.bank;

import java.util.ArrayList;
import java.util.UUID;

public interface AccountManagment {
	
	//Select Account
	//Withdraw/Deposit
	//Transfer between Accounts
	//Display owned Accounts (By Username & password)
	//Display coowned Accounts (by Username & password)

	public Account SelectAccount (UUID AccountID);
	public int Transaction(int ammount, UUID AccountID);
	public void Transfer(UUID senderAccount, UUID recieverAccount, int amount);
	public ArrayList<Account> ownedAccounts(UUID ownerID);
	public ArrayList<Account> coownedAccounts(UUID ownerID);
	
}
