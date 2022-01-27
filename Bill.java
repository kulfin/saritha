package com.geektrust.water.management.model;

public class Bill extends Water{
	private   int totalWaterBill;
	private   int tankerWaterBill;
	Water water = new Water();
	
	public   int getTotalWaterBill() {
		return this.totalWaterBill;
	}
	public   void setTotalWaterBill(int totalWaterBill) {
		this.totalWaterBill = totalWaterBill;
	}
	public   int getTankerWaterBill() {
		return this.tankerWaterBill;
	}
	public   void setTankerWaterBill(int tankerWaterBill) {
		 this.tankerWaterBill = tankerWaterBill;
	}
}
