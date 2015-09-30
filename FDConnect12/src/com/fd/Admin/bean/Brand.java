package com.fd.Admin.bean;

public class Brand {

	private int clientId;
	private int brandCategoryId;
	private int brandId;
	
	private String brandName;
	private String notes;

	// Setter
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	 public void setBrandCategoryId(int brandCategoryId) {
		this.brandCategoryId = brandCategoryId;
	}
    
    public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
    
    public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
    
    public void setNotes(String notes) {
		this.notes = notes;
	}
    
    
	// Getter
	public int getClientId() {
		return clientId;
	}

	public int getBrandCategoryId() {
		return brandCategoryId;
	}
	
	public int getBrandId() {
		return brandId;
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public String getNotes() {
		return notes;
	}
	
	
}
