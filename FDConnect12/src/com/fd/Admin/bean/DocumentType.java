package com.fd.Admin.bean;

public class DocumentType {

	private int documentTypeId;
	private String documentTypeName;
	private String description;
	private String owner;
   
	// Setter
	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}

	// Getter
	public int getDocumentTypeId() {
		return documentTypeId;
	}

	public String getDocumentTypeName() {
		return documentTypeName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getOwner() {
		return owner;
	}


}
