package com.geektrust.water.management.model;

import com.geektrust.water.management.resources.WaterConstants;

public class Water {
	private int borewellWater;
	private int corporationWater;
	private int tankerWater;
	private int allottedWater;
	Rooms rooms = new Rooms();
	
	public int getBorewellWater() {
		return this.borewellWater;
	}
	public void setBorewellWater(int borewellWater) {
		this.borewellWater = borewellWater;
	}
	public int getCorporationWater() {
		return this.corporationWater;
	}
	public void setCorporationWater(int corporationWater) {
		this.corporationWater = corporationWater;
	}
	public int getTankerWater() {
		return this.tankerWater;
	}
	public void setTankerWater(int tankerWater) {
		this.tankerWater = tankerWater;
	}
	public int getAllottedWater() {
		return this.allottedWater;
	}
	public void setAllottedWater(int allottedWater) {
		this.allottedWater = allottedWater;
	}

	public int calculateTankerWaterCost(int extraWater){
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
	public void allotWater(String bedrooms, String ratio) {
		String[] str = ratio.split(":");
		this.setCorporationWater(Integer.parseInt(str[WaterConstants.ZERO]));
		this.setBorewellWater(Integer.parseInt(str[WaterConstants.ONE]));
		int members = rooms.getRooms(Integer.parseInt(bedrooms));
		this.setAllottedWater(members*WaterConstants.TEN*WaterConstants.THIRTY);
	}
	
}
