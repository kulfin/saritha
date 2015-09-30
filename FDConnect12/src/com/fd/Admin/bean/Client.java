package com.fd.Admin.bean;

public class Client {

	private int clientId;
	private int countryId;
	private int stateId;
	private int cityId;
	
	private String clientName;
	private String localCurrency;
	private String baseCurrency;
	private String tinNumber;
	private String cstNumber;
	private String pinCode;
	private String address;

	// Setter
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
    public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
    
    public void setStateId(int stateId) {
		this.stateId = stateId;
	}
    
    public void setCityId(int cityId) {
		this.cityId = cityId;
	}
    
    public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;
	}
    
    public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
    
    public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}
    
    public void setCstNumber(String cstNumber) {
		this.cstNumber = cstNumber;
	}
    
    public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
    
    public void setAddress(String address) {
		this.address = address;
	}

	

	// Getter
	public int getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName;
	}
	
	public int getCountryId() {
		return countryId;
	}
	
	public int getStateId() {
		return stateId;
	}
	
	public int getCityId() {
		return cityId;
	}
	
	public String getLocalCurrency() {
		return localCurrency;
	}
	
	public String getBaseCurrency() {
		return baseCurrency;
	}
	
	public String getTinNumber() {
		return tinNumber;
	}
	
	public String getCstNumber() {
		return cstNumber;
	}
	
	public String getPinCode() {
		return pinCode;
	}
	
	public String getAddress() {
		return address;
	}

}
