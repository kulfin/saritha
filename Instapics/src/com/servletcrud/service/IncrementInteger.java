package com.servletcrud.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IncrementInteger {
	
	
	List<Integer> countList = new ArrayList<Integer>();
	
	
	 Integer getMaxInteger() {
		
		 Integer maxNumber=0;
			
			Integer countMax=0;
		   Integer count = 0;
		   
	  while(countMax <1) {
		  countList.add(count);
		  count++;
		 maxNumber =  Collections.max(countList);
		  
	  }
	 System.out.println("max number is:::" +maxNumber);
	  return maxNumber;
}
	
}	
