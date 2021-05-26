package com.revature.driver;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.reavture.utils.ConnectionUtils;
import com.revature.bank.Account;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;
import com.revature.daos.AccountDAOIMP;

class AccountTests {

	Account a;
	AccountDAOIMP aD = new AccountDAOIMP();
	
	@BeforeEach
	public void setUp() throws SQLException
	{
		Connection c = ConnectionUtils.getConnection();
		//int accountID, String accountName, AccountStatus status, double balance, int ownerI
		a = new Account(1001, "JTestAccount", AccountStatus.APPROVED, 1000, 1);
		
		String sql = "insert into accounts (account_id, owner_id, account_name, balance, current_status) values(?, ?, ?, ?, cast (? as status));";
		PreparedStatement statement = c.prepareStatement(sql);
		statement.setInt(1, a.getACCOUNT_ID());
		statement.setInt(2, a.getOwnerID());
		statement.setString(3, a.getAccountName());
		statement.setFloat(4, (float) a.getBalance());
		statement.setString(5, a.getStatus().toString());
		
		statement.execute();
	}
	
	@AfterEach
	public void tearDown() throws SQLException
	{
		Connection c = ConnectionUtils.getConnection();
		//int accountID, String accountName, AccountStatus status, double balance, int ownerI
		a = new Account(1001, "JTestAccount", AccountStatus.APPROVED, 1000, 1);
		
		String sql = "delete from accounts where account_id=1001";
	}

}
