package com.fd.Admin.bean;

public class RetailClient {

	private int retailClientId;
	private String retailClientName;
	private String address;

	// Setter
	public void setRetailClientId(int retailClientId) {
		this.retailClientId = retailClientId;
	}

	public void setRetailClientName(String retailClientName) {
		this.retailClientName = retailClientName;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	// Getter
	public int getRetailClientId() {
		return retailClientId;
	}

	public String getRetailClientName() {
		return retailClientName;
	}
	
	public String getAddress() {
		return address;
	}

}
