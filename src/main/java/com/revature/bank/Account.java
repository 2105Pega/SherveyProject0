package com.revature.bank;

import java.io.Serializable;
import java.util.ArrayList;
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
		coOwnerIDs = new ArrayList<UUID>();
	}
	
	public double withdraw(double ammountChange)
	{
		
		if(ammountChange <= 0)
		{
			throw new IllegalArgumentException("Cannot Withdraw Negative or Zero Funds");
		}
		
		balance = balance + ammountChange;
		return balance;

	}
	
	public double deposit (double ammountChange)
	{
		if(ammountChange <= 0)
		{
			throw new IllegalArgumentException("Cannot Deposit Negative or Zero Funds");
		}
		else if(this.getStatus() != AccountStatus.APPROVED)
		{
			throw new IllegalStateException("Sending Account Not Approved.");
		}
		
		balance = balance + ammountChange;
		return balance;
	}
	
	public void transfer(Account a, double ammount)
	{
		if(ammount <= 0)
		{
			throw new IllegalArgumentException("Cannot Transfer Negative or Zero Funds");
		}
		else if(a == null)
		{
			throw new NullPointerException("Cannot Transfer to target Account. ID Not Found in System");
		}
		else if(a.getACCOUNT_ID() == this.getACCOUNT_ID())
		{
			throw new IllegalArgumentException("Cannot Transfer To Same Account");
		}
		else if(a.getStatus() != AccountStatus.APPROVED)
		{
			throw new IllegalStateException("Target Account Not Approved.");
		}
		else if(this.getStatus() != AccountStatus.APPROVED)
		{
			throw new IllegalStateException("Sending Account Not Approved.");
		}
		
		
		
		this.withdraw(ammount);
		a.deposit(ammount);

	}

	public void addCOwner(UUID coOwnerID)
	{
		if(coOwnerID == null)
			throw new NullPointerException("Co-Owner ID is Null");
		
		coOwnerIDs.add(coOwnerID);
	}
	
	@Override
	public String toString() {
		return "Account [accountName=" + accountName + ", status=" + status + ", ACCOUNT_ID=" + ACCOUNT_ID + ", balance=" + balance + ", ownerID="
				+ ownerID + ", coOwnerIDs=" + coOwnerIDs.toString() + "]";
	}
	
	
	
}
