package com.fd.Admin.bean;

public class Depot {
    
	private int countryId;
	private int regionId;
	private int stateId;
	private int cityId;
	private int townId;
	private int areaId;
	private int clientId;
	
	private int depotId;
	private String depotName;
	private String contactName;
	private String contactPhone;
	private String landMarkDetails;
	private String notes;
    private String address;

	// Setter
    public void setClientId(int clientId) {
		this.clientId = clientId;
	}

    public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
    
    public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
    
    public void setStateId(int stateId) {
		this.stateId = stateId;
	}
    
    public void setCityId(int cityId) {
		this.cityId = cityId;
	}

    public void setTownId(int townId) {
		this.townId = townId;
	}
    
    public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public void setDepotId(int depotId) {
		this.depotId = depotId;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}
	
   public void setContactName(String contactName) {
		this.contactName = contactName;
	}
    
    public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
    
    public void setLandMarkDetails(String landMarkDetails) {
		this.landMarkDetails = landMarkDetails;
	}
    
    public void setNotes(String notes) {
		this.notes = notes;
	}
    
   public void setAddress(String address) {
		this.address = address;
	}

	

	// Getter
	public int getClientId() {
		return clientId;
	}
	
	public int getCountryId() {
		return countryId;
	}
	
	public int getRegionId() {
		return regionId;
	}
	
	public int getStateId() {
		return stateId;
	}
	
	public int getCityId() {
		return cityId;
	}
	
	public int getTownId() {
		return townId;
	}
	
	public int getAreaId() {
		return areaId;
	}
	
	public int getDepotId() {
		return depotId;
	}
	
	public String getDepotName() {
		return depotName;
	}
	
	public String getContactName() {
		return contactName;
	}
	
	public String getContactPhone() {
		return contactPhone;
	}
	
	public String getLandMarkDetails() {
		return landMarkDetails;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public String getAddress() {
		return address;
	}

}
