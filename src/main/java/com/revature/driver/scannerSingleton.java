package com.revature.driver;

import java.util.Scanner;

public class scannerSingleton {

	/* Singleton Implementation of Scanner to share across classes as closing a Scanner closes System.in as well,
	 * which results in ALL scanners in program being closed. This just avoids Warnings for unclosed Scanners.
	 * I do not think this is needed but I dislike the warnings.
	 */
	
	private static Scanner kIn;
	
	public scannerSingleton()
	{
		if(kIn == null)
			kIn = new Scanner(System.in);
	}
	
	public Scanner getScanner()
	{
		return kIn;
	}
	
	public void clear()
	{
		kIn.nextLine();
	}
	
	public int getInt()
	{
		kIn.nextLine();
		return kIn.nextInt();
	}
	
	public String getLine()
	{
		return kIn.nextLine();
	}
	
	public void closeScanner()
	{
		kIn.close();
	}
	
}