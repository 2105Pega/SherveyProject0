package com.reavture.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.driver.BankDriver;

public class ConnectionUtils {

	//private static final String[] connectionSetUp = setUp();
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
	
		String url = "";
		String username = "";
		String password = "";
		 
		try {
			
			File f = new File("../SherveyProject0/src/main/resources/DB_Properties.properties");

			FileInputStream fis = new FileInputStream(f);

			Properties prop = new Properties();
			prop.load(fis);
			
			url = prop.getProperty("DB_URL");
			username = prop.getProperty("DB_Username");
			password = prop.getProperty("DB_Password");
			
		} 
		catch(FileNotFoundException e)
		{
			logger.fatal("FATAL ERROR: Database Properites File Is Not Present. Program Cannot Connect to Database.");
			System.exit(1);
		}
		catch(IOException e)
		{
			logger.fatal("FATAL ERROR: Unable to load Properties file. Program Cannot Connect to Database.");
			System.exit(1);
		}
		catch (Exception e) {
			logger.fatal("FATAL ERROR: Unable to load Properties file. Program Cannot Connect to Database.");
			System.exit(1);
		}
		
		//This auto closes the connection at the end of the try-catch
		
		Connection c = DriverManager.getConnection(url, username, password);
		c.setSchema("project1"); //As I am using a Schema. Better to add as a ENV var
		//System.out.println(c.isValid(1));
		
		return c;
	}

/*
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
	*/
	
}
