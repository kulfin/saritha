package com.geektrust.water.service;

public interface WaterManagementInterface {
	public void executeCommands(String command, String[] commandList);
	public void calculateBill();
}
