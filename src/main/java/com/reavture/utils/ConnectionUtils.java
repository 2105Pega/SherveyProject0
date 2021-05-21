package com.reavture.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.driver.BankDriver;

public class ConnectionUtils {

	private static final String[] connectionSetUp = setUp();
	private static final Logger logger = LogManager.getLogger(ConnectionUtils.class);
	
	public static Connection getConnection() throws SQLException
	{
		try {
			Class.forName("org.postgresql.Driver"); //Gives app drivers to connect to PostgreSQL DB
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Sets url to be JDBC, as PostGresql, with connection to the AWS DB endpoint, port, then DB Name
		
		//System.out.println(connectionSetUp[0] + ", " + connectionSetUp[1] +", " + connectionSetUp[2]);
		
		String url = connectionSetUp[0];
		String username = connectionSetUp[1];
		String password = connectionSetUp[2];
		 
		
		//This auto closes the connection at the end of the try-catch
		
		Connection c = DriverManager.getConnection(url, username, password);
		c.setSchema("project1"); //As I am using a Schema. Better to add as a ENV var
		return c;
	}


	private static String[] setUp() {
		
		File propFile = new File("DBProperties.Properties");
		
		try {
		if(!propFile.exists())
		{
			logger.fatal("FATAL ERROR: Database Properites File Is Not Present. Program Cannot Connect to Database.");
			System.exit(1);
		}
		
		FileInputStream fis = new FileInputStream(propFile); //FileNotFoundException
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		String[] sArr = (String[]) ois.readObject();
		
		ois.close();
		fis.close();
		
		return sArr;
		
		}
		catch (IOException e)
		{
			logger.fatal("FATAL ERROR: Database Properites File Is Not Readable. Program Cannot Connect to Database.");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.fatal("FATAL ERROR: Database Properites File Is Not Readable. Program Cannot Connect to Database.");
			System.exit(1);
		}
		
		return null;
	}
	
	
}
