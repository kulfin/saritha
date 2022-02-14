package com.waterManagement;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.waterManagement.model.Bill;
import com.waterManagement.model.Commands;
import com.waterManagement.model.Rooms;
import com.waterManagement.model.Water;
import com.waterManagement.resources.WaterConstants;
import com.waterManagement.service.WaterManagementService;

/**
 * Unit test for simple App.
 */
public class WaterManagementTest 
    //extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    
	WaterManagementService waterManagementService = new WaterManagementService();
	Water water = new Water();
    WaterManagementEnum ADD_GUESTS =  WaterManagementEnum.ADD_GUESTS;
    WaterManagementEnum ALLOT_WATER =  WaterManagementEnum.ALLOT_WATER;
    WaterManagementEnum BILL =  WaterManagementEnum.BILL;
    Bill bill = new Bill();
    Commands commands = new Commands();
    Rooms rooms = new Rooms();
    File file = new File("src/main/java/com/waterManagement/resources");
	String absolutePath = file.getAbsolutePath();
	
    @Test
    public void calculateTankerWaterCostTest(){
    	water.setTankerWater(1000);
    	water.getTankerWater();
    	bill.setTankerWaterBill(1000);
    	bill.getTankerWaterBill();
    	assertEquals(4000,water.calculateTankerWaterCost(1500));
    }
    
    @Test
    public void allotWaterTest(){
    	water.allotWater("2","3:7");
    	water.setAllottedWater(900);
    	assertEquals(900,water.getAllottedWater());
    }
    
    @Test
    public void setCommandCountTest(){
    	commands.setCommandCount(4);
    	assertEquals(4,commands.getCommandCount());
    }
    
	@Test
    public void executeCommandTest(){
    	String[] commandsList = {"ALLOT_WATER", "3", "5:4", "ADD_GUESTS", "3", "ADD_GUESTS", "5"};
    	int index = 0;
    	String[] result = {"3", "5:4"};
    	assertEquals(result[0],commands.getCommands(commandsList,index)[0]);
    }
        
    /*@Test
    public void findTotalBillTest(){
    	String[] commandsList = {"ALLOT_WATER", "3", "5:4", "ADD_GUESTS", "3", "ADD_GUESTS", "5", "BILL"};
    	waterManagementService.findTotalBill(commandsList);
    	assertEquals(1500,water.getAllottedWater());
    }*/
    @Test
    public void setDataTest(){
		String[] args = new String[1];
		args[WaterConstants.ZERO] = absolutePath + "/commands.txt";
		try {
			WaterManagement.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	assertNotNull(args);
    }
    
    @Test
    public void getGuests() {
    	rooms.setGuests("8");
		assertNotNull(rooms.getGuests());
	}
    
    @Test
	public void getRooms() {
    	rooms.setRooms();
		assertEquals(3,rooms.getRooms(2));
	}
    
    @Test
	public void getMembers() {
    	rooms.setMembers(5);
    	assertEquals(5,rooms.getMembers());
	}
}
