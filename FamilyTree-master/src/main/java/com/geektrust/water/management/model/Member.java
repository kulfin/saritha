package com.geektrust.water.management.model;


public class Member {
	private String waterRatio;
	private int apartmentType;
	private int numberOfGuests;
	private double totalCost;
	private double totalLitres;
	
	

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalLitres() {
		return totalLitres;
	}

	public void setTotalLitres(int totalLitres) {
		this.totalLitres = totalLitres;
	}
	
	Member(int numberOfGuests){
		this.numberOfGuests = numberOfGuests;
	}

	public Member(double totalCapacityLitres, double totalCost2){
		this.totalLitres = totalCost2;
		this.totalCost = totalCapacityLitres;
		
	}
	
	
	Member(String waterRatio, int apartmentType, int numberOfGuests,int totalCost, int totalLitres){
		this.waterRatio = waterRatio;
		this.apartmentType = apartmentType;
		this.numberOfGuests = numberOfGuests;
		this.totalLitres = totalLitres;
		this.totalCost = totalCost;
		
	}



	public Member(int apartmentType, String waterRatio,int numberOfGuests) {
		this.apartmentType = apartmentType;
		this.waterRatio = waterRatio;
		this.numberOfGuests = numberOfGuests;
		
	}

	public Member() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((waterRatio == null) ? 0 : waterRatio.hashCode());
		return result;
	}

	public String getWaterRatio() {
		return waterRatio;
	}

	public void setWaterRatio(String waterRatio) {
		this.waterRatio = waterRatio;
	}

	public int getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(int apartmentType) {
		this.apartmentType = apartmentType;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (waterRatio == null) {
			if (other.waterRatio != null)
				return false;
		} else if (!waterRatio.equals(other.waterRatio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Member [name=" + waterRatio + "]";
	}
	
	
}
