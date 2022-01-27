package com.geektrust.water.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.geektrust.water.management.resources.*;
import com.geektrust.water.exceptions.MemberNotFoundException;
import com.geektrust.water.management.model.Bill;
import com.geektrust.water.management.model.Commands;
import com.geektrust.water.management.model.Rooms;
import com.geektrust.water.management.model.Water;
import com.geektrust.water.management.resources.WaterConstants;
import com.geektrust.water.management.model.Member;





public class WaterManagementService implements WaterManagementInterface{
	private WaterRepo waterRepo;
	Water water = new Water();
	Rooms rooms = new Rooms();
	Bill bill = new Bill();
	Commands commands = new Commands();
	Set<String> commandSet = new HashSet<String>();
	
	public WaterManagementService(com.geektrust.water.service.WaterRepo waterRepo2) {
		
	}

	public WaterManagementService() {
		
	}
	
	
	public void printWaterBill(String[] arguments){
		String command = null;
		
		commandSet = commands.getCommands();
		for (int i = 0;i<arguments.length;i++){
	    	if(arguments[i] == null){
	    		break;
	    	}
	    	String[] commandList = new String[arguments.length];
			if(commandSet.contains(arguments[i])){
	    		command = arguments[i];
			}
	    	commandList = commands.getCommands(arguments,i);
	    	i = i + commands.getCommandCount();
	    	if(command != null){
	    		this.executeCommands(command,commandList);
	    	}
	    }
	}

	public void executeCommands(String command, String[] commandList){
		ClassLoader classLoader = getClass().getClassLoader();
	    InputStream inputStream = classLoader.getResourceAsStream("input.txt");
		Scanner sc = new Scanner(inputStream);
		 String s = sc.nextLine();		   
		 System.out.println("string s in execute comamnds is:::" +s);
		// String address = commandArgs;
	//	char[] chars = address.toCharArray();
	//	String allotWater = null;
        String water = new String();
		   
		    	String[] s1 = s.split("");
		    	for(int z=0;z<12;z++){
		    		
		    	water = water + s1[z]+"";
		    	// water = water.trim();	
		    	}
		    
		 System.out.println("allot water string is::::" +water);
		
		if(water.equals(WaterConstants.ALLOT_WATER)){
			System.out.println("command is allot water");
			int aptType = getApartmentType();
        	allotWater(aptType, commandList[0]);
        } else if(command.equals(WaterConstants.ADD_GUESTS)){
        	
        	System.out.println("command is add Guests");
        	int numberOfGuests = calculateGuests();
        	addGuests(numberOfGuests);
        } else if(command.equals(WaterConstants.BILL)){
        	System.out.println("command is bill");
        	this.calculateBill();
        }
		    }
	

	
	
	@SuppressWarnings("resource")
	public int getApartmentType(){	
		System.out.println("coming inside getApartmentType() method");
		ClassLoader classLoader = getClass().getClassLoader();
	    InputStream inputStream = classLoader.getResourceAsStream("input.txt");
		Scanner sc = new Scanner(inputStream);
		 String s = sc.nextLine();		   
		 System.out.println("string is:::" +s);
		 String[] s1= s.split("");
		 String d = null;
		 int total = 0;
		    
		    for(int z=0;z<s1.length;z++){
		    	d = s1[z];
		    	
		    	if(d.equalsIgnoreCase("3")){
		    		total = 3;
		    		
		    	}
		    	
		    	if(d.equalsIgnoreCase("2")){
		    		total = 2;
		    		
		    	}
		    }
		    return total;
		}
	
	
	public Member addGuests(int numberOfGuests){
		Member member = null;
		System.out.println("coming to addGuests method");
		System.out.println("number of guests is::");
		System.out.println(numberOfGuests);
       
	    int totalCostTanker = 0;	
	   
		int totalLitresTanker = numberOfGuests*10*30;
		
		System.out.println("Total Litres of tanker  Consumed is:::" +totalLitresTanker);
		if(totalLitresTanker<=500){
			
		    totalCostTanker =  2*totalLitresTanker;
		    member = new Member(totalLitresTanker, totalCostTanker);
		    System.out.println("Total Cost of Tanker Water  Consumed is:::" +totalCostTanker);
		    
		}
		
        if(totalLitresTanker>500 && totalLitresTanker <=1500){
			
		    totalCostTanker =  2*(totalLitresTanker-500) + 3*(1500-totalLitresTanker);
		    member = new Member(totalLitresTanker, totalCostTanker);
		    System.out.println("Total Cost of Tanker Water is:::" +totalCostTanker);
		    
		}
        
        if(totalLitresTanker>1500 && totalLitresTanker <=3000){
			
		    totalCostTanker = 2*500 + 3*(totalLitresTanker-500) + 3*(totalLitresTanker-1500) + 5*(3000-1500);
		    member = new Member(totalLitresTanker, totalCostTanker);
		    System.out.println("Total Cost of Tanker Water is:::" +totalCostTanker);
		    
		}
        
        if(totalLitresTanker>3000){
			
		    totalCostTanker = 2*500 + 3*(totalLitresTanker-500) + 3*(totalLitresTanker-1500) + 5*(3000-1500)+8*(totalLitresTanker-3000);
		    System.out.println("Total Cost of Tanker Water is:::" +totalCostTanker);
		    member = new Member(totalLitresTanker, totalCostTanker);
		   
		}
return member;
}
	
	public String corpBoreWaterRatio(){
		
		 ClassLoader classloader = Thread.currentThread().getContextClassLoader();		   
		    InputStream inputStream = classloader.getResourceAsStream("input.txt");
		    Scanner in = new Scanner(inputStream); 		    
		    String s = in.nextLine();
			
			System.out.println("string s in calculate bill method is:::" +s);
			int firstColon = s.indexOf(":");
			String parsed = s.substring(firstColon-1, firstColon+2);
			System.out.println("water ratio is::::" +parsed);
		    return parsed;
	}
	
	public int calculateGuests(){
		    ClassLoader classloader = Thread.currentThread().getContextClassLoader();		   
		    InputStream inputStream = classloader.getResourceAsStream("input.txt");
		    Scanner in = new Scanner(inputStream); 		    
		    String s = in.nextLine();
			int nextNumber = 0;
			int totalGuests = 0;
			while (in.hasNext()) {
				while(!in.hasNextInt() || in.nextInt() != 0) {
			    	String next = in.next();
			    	 if(next.equalsIgnoreCase("BILL")){
			    		 break;
			    	 }
			        	nextNumber = in.nextInt();
				        totalGuests = totalGuests + nextNumber;				      
				}			          
			}
			int numberOfGuests = totalGuests;
		
		return numberOfGuests;
	}
	
	public void calculateBill() {	
		Member m = null;
		Member m1 = null;
		List<Member> corpBoreWaterList = new ArrayList<Member>();
		List<Member> tankerWaterList = new ArrayList<Member>();
		int aptType = getApartmentType();
		System.out.println("apt type in calculate bill is:::" +aptType);
		String corpBoreWaterRatio = corpBoreWaterRatio();
		System.out.println("bore ratio in calculate bill is:::" +corpBoreWaterRatio);
		int numberOfGuests = calculateGuests();
		System.out.println("numberOfGuests in calculate bill is:::" +numberOfGuests);
		
		
		m = allotWater(aptType, corpBoreWaterRatio);
		m1 = addGuests(numberOfGuests);
		
		double totalCost = m.getTotalCost() + m1.getTotalCost();
		double totalLitres = m.getTotalLitres() + m1.getTotalLitres();
		
	    System.out.println("final total cost is:::" +totalCost);
	    System.out.println("final total litres  is:::" +totalLitres);
		
			
	}
	

		
	public Member allotWater(int apartmentType, String corpBoreWaterRatio) {
		System.out.println("inside first line of allot water method");
		List<Member> listCorpBoreWater = new ArrayList<Member>();
		Member member = null;
		// String ratioString = null;
		// Scanner in = new Scanner(System.in);  
	//	String s = in.nextLine();
		
		
		 ClassLoader classloader = Thread.currentThread().getContextClassLoader();		   
		 InputStream inputStream = classloader.getResourceAsStream("input.txt");
		 Scanner in = new Scanner(inputStream); 		    
		 String s = in.nextLine();
		
		
		int firstColon = s.indexOf(":");
		String parsed = s.substring(firstColon-1, firstColon+2);
		System.out.println("string  between colons is::::" +parsed);
		String secondString = s.substring(firstColon+1);
		String firstString = s.substring(firstColon-2,firstColon);
		System.out.println("first string is:::" +firstString);
		System.out.println("second string is:::" +secondString);
		corpBoreWaterRatio = parsed;		
		String[] ratio = corpBoreWaterRatio.split(":");
		List<String> corpBoreWaterRatioList = new ArrayList<String>();		
		int length = ratio.length;
		// NumberFormat nf = NumberFormat.getInstance();
		Integer firstInt = Integer.parseInt(firstString.trim());
		Integer secondInt = Integer.parseInt(secondString);
		
	
		
		
		
		
		
		float ratioNumber = (float)(firstInt + secondInt);
		float totalLitres = 0;
		double totalCost = 0;
		
		
		   
		    System.out.println("string is:::" +s);
		    String[] s1= s.split("");
		   
		 int aptType = getApartmentType();
		 System.out.println("apt type is:::" +aptType);
		
	
			
			if(aptType==2){
			    
				totalLitres = 3*10*30*firstInt/ratioNumber + 3*10*30*secondInt/ratioNumber;
				totalCost =  (int)1.5*3*10*30*firstInt/ratioNumber + (int)1.5*1*3*10*30*secondInt/ratioNumber;
				member = new Member(totalLitres, totalCost);
				
				
				
			}
				
			if(aptType==3){
				
				totalLitres = 5*10*30*firstInt/ratioNumber + 5*10*30*secondInt/ratioNumber;
				System.out.println("coming inside 3 and totallitres of borewater is:::" +totalLitres);
				totalCost =   (int)1.5*5*10*30*firstInt/ratioNumber + (int)1.5*5*10*30*secondInt/ratioNumber;
				System.out.println("coming inside 3 and totalcost of borewater is:::" +totalCost);
			    member = new Member(totalLitres, totalCost);
					
				
			}
		
		
		 return member;
	}

	

	

	
		
		
	}


