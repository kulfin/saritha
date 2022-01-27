package com.geektrust.water.management;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.geektrust.water.management.resources.WaterConstants;
import com.geektrust.water.service.WaterManagementService;




public class WaterManagement 
{
	
	public static void main( String[] args ) throws Exception
    {
		
		    ClassLoader classloader = Thread.currentThread().getContextClassLoader();		   
		    InputStream inputStream = classloader.getResourceAsStream("input.txt");
		    InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		    BufferedReader reader = new BufferedReader(streamReader);
		    String[] arguments = new String[19];
		    int commands = 0;
		   
		    WaterManagementService waterManagementService = new WaterManagementService();
		    Scanner in = new Scanner(inputStream); 		    
			
			 String s = in.nextLine();
			   
			    System.out.println("string is:::" +s);
			    String[] s1= s.split("");
			    String d = null;
			    int total = 0;
		    
		    // int total = 0;
	        String line=null;
	        while (in.hasNext()){ {
	        	System.out.println("scanner has next row");
	        	arguments[commands++] = in.next();
	        }
	        
        if (arguments.length > WaterConstants.ZERO)
        {
        	System.out.println("arguments length is::::" +arguments.length);
        	waterManagementService.printWaterBill(arguments);
        }
	        }  
    }
}
	

