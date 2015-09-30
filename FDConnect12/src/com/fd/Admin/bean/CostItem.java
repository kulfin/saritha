package com.fd.Admin.bean;

public class CostItem {

	private int costTypeId;
    private int costItemId;
	private String costItemName;
	

	// Setter
	public void setCostTypeId(int costTypeId) {
		this.costTypeId = costTypeId;
	}


    
    public void setCostItemId(int costItemId) {
		this.costItemId = costItemId;
	}
    
    public void setCostItemName(String costItemName) {
		this.costItemName = costItemName;
	}
    
 
    
    
	// Getter
	public int getCostTypeId() {
		return costTypeId;
	}

	
	public int getCostItemId() {
		return costItemId;
	}
	
	public String getCostItemName() {
		return costItemName;
	}
	

	
	
}
