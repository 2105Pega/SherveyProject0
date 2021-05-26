package com.revature.utils;

import java.util.Scanner;

public class ScannerSingleton {

	/* Singleton Implementation of Scanner to share across classes as closing a Scanner closes System.in as well,
	 * which results in ALL scanners in program being closed. This just avoids Warnings for unclosed Scanners.
	 * I do not think this is needed but I dislike the warnings.
	 */
	
	private static Scanner kIn;
	
	public ScannerSingleton()
	{
		if(kIn == null)
			kIn = new Scanner(System.in);
	}
	
	public Scanner getScanner()
	{
		return kIn;
	}
	
	public int getInt()
	{
		while(!kIn.hasNextInt())
			kIn.nextLine();
		int x = kIn.nextInt();
		kIn.nextLine();
		return x;
	}
	
	public double getDouble()
	{
		while(!kIn.hasNextDouble())
			kIn.nextLine();
		double x = kIn.nextDouble();
		kIn.nextLine();
		return x;
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