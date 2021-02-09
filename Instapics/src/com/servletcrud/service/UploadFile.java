package com.servletcrud.service;

import java.io.File;

public class UploadFile {
	
	
	
	private String imageDescription;
	
	private File imageLocation;
	
	private UserLogin login;
	
	public UploadFile(UserLogin login, String imageDescription, File imageLocation) {
		this.login=login;
		this.imageDescription=imageDescription;
		this.imageLocation=imageLocation;
		
		
	}

	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	public File getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(File imageLocation) {
		this.imageLocation = imageLocation;
	}
	
	

	

}
