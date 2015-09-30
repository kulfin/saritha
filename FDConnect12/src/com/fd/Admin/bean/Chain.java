package com.fd.Admin.bean;

public class Chain {

	private int clientId;
	private int retailClientId;
	private int chainId;
	
	private String chainName;
	

	// Setter
	public void setTradeId(int clientId) {
		this.clientId = clientId;
	}

	 public void setRetailClientId(int retailClientId) {
		this.retailClientId = retailClientId;
	}
    
    public void setChainId(int chainId) {
		this.chainId = chainId;
	}
    
    public void setChainName(String chainName) {
		this.chainName = chainName;
	}
    
 
    
    
	// Getter
	public int getTradeId() {
		return clientId;
	}

	public int getRetailClientId() {
		return retailClientId;
	}
	
	public int getChainId() {
		return chainId;
	}
	
	public String getChainName() {
		return chainName;
	}
	

	
	
}
