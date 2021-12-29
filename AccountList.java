package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class AccountList {
	ArrayList <Accounts> AccountList = new ArrayList<Accounts>();
	String Profile;
	String Email;
	String Username;
	String Password;
	//---------------------------------------------------------------------------
	/** 
	 * Constructor that reads info from a file and sorts it into an ArrayList
	 * 
	 * @param fileName - name of the file where profiles are stored
	 * 
	 */
public AccountList(String fileName) {
		//System.out.println("The filename you entered was: "+fileName);
		  
		Scanner myFileIn = null;
	    try
	    {
	        myFileIn= new Scanner(new File(fileName));
	    } catch (FileNotFoundException e)
	        {
	            System.out.println("File: "+fileName+" is not found");
	        }
	    
	    
	      while (myFileIn.hasNextLine()){
	    	  Email = myFileIn.next();
	    	  Username = myFileIn.next();
	    	  Password = myFileIn.next();
	    	  
	    	  Accounts a = new Accounts(Email,Username,Password);
	    	  AccountList.add(a);
	  	      }
}
}
