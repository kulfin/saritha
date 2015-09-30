package com.fd.Admin.bean;

public class StoreStatus {

	private int storeStatusId;
	private String storeStatusName;
	private String description;

	// Setter
	public void setStoreStatusId(int storeStatusId) {
		this.storeStatusId = storeStatusId;
	}

	public void setStoreStatusName(String storeStatusName) {
		this.storeStatusName = storeStatusName;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	// Getter
	public int getStoreStatusId() {
		return storeStatusId;
	}

	public String getStoreStatusName() {
		return storeStatusName;
	}
	
	public String getDescription() {
		return description;
	}

}
