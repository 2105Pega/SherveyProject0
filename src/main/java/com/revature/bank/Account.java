package com.revature.bank;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = -2189496940240975358L;
	
	private AccountStatus status;
	private String accountName;
	private int ACCOUNT_ID;
	private double balance;
	private int ownerID;
	
	public AccountStatus getStatus() {return status;}

	public void setStatus(AccountStatus status) {this.status = status;}

	public double getBalance() {return balance;}

	public void setBalance(double balance) {this.balance = balance;}

	public int getOwnerID() {return ownerID;}

	public String getAccountName() {return accountName;}

	public void setAccountName(String accountName) {this.accountName = accountName;}

	public void setOwnerID(int ownerID) {this.ownerID = ownerID;}

	public int getACCOUNT_ID() {return ACCOUNT_ID;}

	public Account()
	{
		super();
		this.ACCOUNT_ID = -1;
	}
	
	public Account( String accountName, AccountStatus status, double balance, int ownerID) {
		super();
		this.status = status;
		this.balance = balance;
		this.ownerID = ownerID;
		this.accountName = accountName;
	}
	
	public Account(int accountID, String accountName, AccountStatus status, double balance, int ownerID) {
		super();
		this.ACCOUNT_ID = accountID;
		this.status = status;
		this.balance = balance;
		this.ownerID = ownerID;
		this.accountName = accountName;
	}

	@Override
	public String toString() {
		return "Account [accountName=" + accountName + ", status=" + status + ", ACCOUNT_ID=" + ACCOUNT_ID + ", balance=" + balance + ", ownerID="
				+ ownerID + "]";
	}

}
