package com.geektrust.water.management;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.geektrust.water.management.model.Bill;
import com.geektrust.water.management.model.Commands;
import com.geektrust.water.management.model.Member;
import com.geektrust.water.management.model.Rooms;
import com.geektrust.water.management.model.Water;
import com.geektrust.water.management.resources.WaterConstants;
import com.geektrust.water.service.WaterManagementService;
/**
 * Unit test for simple App.
 */
public class WaterManagementTest 
   
{
   
    
	WaterManagementService waterManagementService = new WaterManagementService();
	// Water water = new Water();
    WaterManagementEnum ADD_GUESTS =  WaterManagementEnum.ADD_GUESTS;
    WaterManagementEnum ALLOT_WATER =  WaterManagementEnum.ALLOT_WATER;
    WaterManagementEnum BILL =  WaterManagementEnum.BILL;
    Bill bill = new Bill();
    Water water = new Water();
    Member m = new Member();
    Commands commands = new Commands();
    Rooms rooms = new Rooms();
    File file = new File("src/main/java/com/waterManagement/resources");
	String absolutePath = file.getAbsolutePath();
	
    @SuppressWarnings("deprecation")
	@Test
    public void calculateTankerWaterCostTest(){
    	int numberOfGuests = 8;
    	m = waterManagementService.addGuests(numberOfGuests);
    	System.out.println("junit value::: " +m.getTotalLitres());
    	assertEquals(2400,m.getTotalCost(),0.02);
    	assertEquals(16900,m.getTotalLitres(),0.02);
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void allotWaterTest(){
    	int apartmentType = 3;
    	String corpBoreWaterRatio ="5:4";
    	m = waterManagementService.allotWater(apartmentType, corpBoreWaterRatio);
    	System.out.println("junit value for allot water cost::: " +m.getTotalLitres());
    	assertEquals(1500,m.getTotalCost(),0.02);
    	assertEquals(1500,m.getTotalLitres(),0.02);
    	
    }
    
    @Test
    public void getGuests() {
    	int numberOfGuests = 8;
		assertEquals(8, waterManagementService.calculateGuests());
	}
    
    
    @Test
    public void getApartmentType(){
    	
    	waterManagementService.getApartmentType();
    	assertEquals(3,waterManagementService.getApartmentType());
    }
    

}
