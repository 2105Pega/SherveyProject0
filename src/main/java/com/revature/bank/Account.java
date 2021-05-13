package com.revature.bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Account implements Serializable {

	private static final long serialVersionUID = -2189496940240975358L;
	
	private AccountStatus status;
	private String accountName;
	private final UUID ACCOUNT_ID;
	private double balance;
	private UUID ownerID;
	private ArrayList<UUID> coOwnerIDs;
	
	public AccountStatus getStatus() {return status;}

	public void setStatus(AccountStatus status) {this.status = status;}

	public double getBalance() {return balance;}

	public void setBalance(int balance) {this.balance = balance;}

	public UUID getOwnerID() {return ownerID;}

	public String getAccountName() {return accountName;}

	public void setAccountName(String accountName) {this.accountName = accountName;}

	public void setOwnerID(UUID ownerID) {this.ownerID = ownerID;}

	public ArrayList<UUID> getCoOwnerIDs() {return coOwnerIDs;}

	public void setCoOwnerIDs(ArrayList<UUID> coOwnerIDs) {this.coOwnerIDs = coOwnerIDs;}

	public UUID getACCOUNT_ID() {return ACCOUNT_ID;}

	public Account()
	{
		super();
		this.ACCOUNT_ID = UUID.randomUUID();
	}
	
	public Account(String accountName, UUID ownerID, ArrayList<UUID> coOwnerIDs)
	{
		this.ACCOUNT_ID = UUID.randomUUID();
		this.ownerID = ownerID;
		this.coOwnerIDs = coOwnerIDs;
		this.status = AccountStatus.PENDING;
		this.accountName = accountName;
	}

	public Account(String accountName, AccountStatus status, double balance, UUID ownerID, ArrayList<UUID> coOwnerIDs) {
		super();
		this.status = status;
		ACCOUNT_ID = UUID.randomUUID();
		this.balance = balance;
		this.ownerID = ownerID;
		this.coOwnerIDs = coOwnerIDs;
		this.accountName = accountName;
	}
	
	public Account(String accountName, AccountStatus status, double balance, UUID ownerID) {
		super();
		this.status = status;
		ACCOUNT_ID = UUID.randomUUID();
		this.balance = balance;
		this.ownerID = ownerID;
		this.accountName = accountName;
	}
	
	public void transaction(double ammountChange)
	{
		balance = balance + ammountChange;
	}
	
	public void transfer(Account a, double ammount)
	{
		this.transaction(-1 * ammount);
		a.transaction(ammount);
	}

	@Override
	public String toString() {
		return "Account [accountName=" + accountName + ", status=" + status + ", ACCOUNT_ID=" + ACCOUNT_ID + ", balance=" + balance + ", ownerID="
				+ ownerID + ", coOwnerIDs=" + Arrays.toString(coOwnerIDs) + "]";
	}
	
	
	
}
