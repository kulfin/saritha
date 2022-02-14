package com.waterManagement.service;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.waterManagement.model.Bill;
import com.waterManagement.model.Commands;
import com.waterManagement.model.Member;
import com.waterManagement.model.Rooms;
import com.waterManagement.model.Water;
import com.waterManagement.model.WaterBillNotFoundException;
import com.waterManagement.resources.WaterConstants;

public class WaterManagementService implements WaterManagementInterface{
	Water water = new Water();
	Rooms rooms = new Rooms();
	Bill bill = new Bill();
	Commands commands = new Commands();
	Set<String> commandSet = new HashSet<String>();
	
	public void findTotalBill(String[] arguments){
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
	    		this.executeCommand(command,commandList);
	    	}
	    }
	}
	
public Scanner getFileScannerInstance(){
		
		ClassLoader classLoader = getClass().getClassLoader();
	    InputStream inputStream = classLoader.getResourceAsStream("commands.txt");
		Scanner fileScanner = new Scanner(inputStream);
		return fileScanner;
		
		
	}
	
	public int calculateGuests() throws WaterBillNotFoundException{
	    
	    Scanner guestData = getFileScannerInstance(); 		    
	    String guestDataString = guestData.nextLine();
		int nextNumber = 0;
		int totalGuests = 0;
		int numberOfGuests = 0;
		try{
		while (guestData.hasNext()) {
			while(!guestData.hasNextInt() || guestData.nextInt() != 0) {
		    	String next = guestData.next();
		    	 if(next.equalsIgnoreCase(WaterConstants.BILL)){
		    		 break;
		    	 }
		        	nextNumber = guestData.nextInt();
			        totalGuests = totalGuests + nextNumber;				      
			}			          
		}
		numberOfGuests = totalGuests;
		}catch(WaterBillNotFoundException e){
			e.getMessage();
		}
	
	return numberOfGuests;
}

	public void executeCommand(String command, String[] commandList){
		if(command.equals(WaterConstants.ALLOT_WATER)){
        	water.allotWater(commandList[WaterConstants.ZERO], commandList[WaterConstants.ONE]);
        } else if(command.equals(WaterConstants.ADD_GUESTS)){
        	rooms.setGuests(commandList[WaterConstants.ZERO]);
        } else if(command.equals(WaterConstants.BILL)){
        	this.calculateBill();
        }
	}
	
	
	public int addGuests(int numberOfGuests) throws WaterBillNotFoundException{
		Member member = null;       
	    int totalCostTanker = 0;	   
		int extraWater = numberOfGuests*10*30;
		
		int rate = WaterConstants.ZERO, surplus = WaterConstants.ZERO;
		if(extraWater>WaterConstants.THREE_THOUSAND){
			surplus = extraWater - WaterConstants.THREE_THOUSAND;
			rate += surplus * WaterConstants.EIGHT;
			extraWater -= surplus;
		} 
		if(extraWater>WaterConstants.THOUSAND_FIVE_HUNDRED && extraWater <=WaterConstants.THREE_THOUSAND){
			surplus = extraWater - WaterConstants.THOUSAND_FIVE_HUNDRED;
			rate += surplus * WaterConstants.FIVE;
			extraWater -= surplus;
		} 
		if(extraWater>WaterConstants.FIVE_HUNDRED && extraWater <=WaterConstants.THOUSAND_FIVE_HUNDRED){
			surplus = extraWater - WaterConstants.FIVE_HUNDRED;
			rate += surplus * WaterConstants.THREE;
			extraWater -= surplus;
		} 
		if(extraWater>WaterConstants.ZERO && extraWater <=WaterConstants.FIVE_HUNDRED){
			rate += extraWater*WaterConstants.TWO;
		}
		return rate;
	}

	
	
	public void calculateBill() throws WaterBillNotFoundException {
		int extraWater = WaterConstants.ZERO, totalBill = WaterConstants.ZERO;
		int guests = rooms.getGuests();
		try{		
		int corporationWater = water.getCorporationWater();
		int borewellWater = water.getBorewellWater();
		 int allottedWater = water.getAllottedWater();		
		double corporationWaterSum = WaterConstants.ZERO, borewellWaterSum = WaterConstants.ZERO;
		float ratio = (float)allottedWater/(corporationWater+borewellWater);
		corporationWaterSum = (ratio)*corporationWater;
		borewellWaterSum = (ratio)*borewellWater*1.5;
		totalBill = (int)Math.ceil(corporationWaterSum + borewellWaterSum);		
		extraWater = guests*10*30;
		 int tankerBill = (int)addGuests(guests);
		 totalBill += tankerBill;
		bill.setTotalWaterBill(totalBill);
		System.out.println( (allottedWater + extraWater) + " " + totalBill);
		
	}catch(WaterBillNotFoundException e){
		e.getMessage();
	}
	}
}
