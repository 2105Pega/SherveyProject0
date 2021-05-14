package com.revature.driver;

public class TestDriver {

	static ScannerSingleton sc;
	
	public static void main (String args[])
	{
		sc = new ScannerSingleton();
		
		int x = sc.getInt();
		System.out.println(x);
	}
	
}
