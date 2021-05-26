package com.revature.driver;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.reavture.utils.ConnectionUtils;

class ConnectionTests {

	@Test
	public void connectionTest()
	{
		try {
			Connection c = ConnectionUtils.getConnection();
			
			assertEquals(c.isValid(0), true);
			assertEquals(c.getSchema(), "project1");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
