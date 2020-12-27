package com.servletcrud.service;
import com.servletcrud.service.IncrementInteger;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
	

	
		
		@WebServlet("/uploadFile")
		public class UploadFile extends HttpServlet{
		
		private final static String bucket_name= "testbucketcreate";
		private final static String file_path= "D:\\vasu\\awsupload\\";
		private final static String Config_path= "D:\\vasu\\awsupload\\config";
		private  String key_name= "AKIARO";
		
		  
		   @Override
		   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		{
			PrintWriter out = response.getWriter();
			String filename=request.getParameter("filename");	
			String file_path="D:\\vasu\\awsupload\\" + filename;
			out.println("full filepath is:::" +file_path);
		
			 Long lastTime = 0L;
		     Long now = System.currentTimeMillis();
		        if (now > lastTime)
		      lastTime = now;
		      Long currentLong = ++lastTime;
		      int count = currentLong.intValue();
		      int positiveCount = Math.abs(count);
		      
		      List<Integer> countList = new ArrayList<Integer>();
		      countList.add(positiveCount);
		      
		     int maxCount =  Collections.max(countList);
		      
		     System.out.println("current value of count is:::" +positiveCount);
		     key_name=key_name+maxCount;
		     
		     int counter=0;
		     
		  
		     
		     if(key_name.length()>=12) {
		    	 
		    	 key_name=key_name.substring(6,12)+maxCount;
		     }else {
		    	 key_name=key_name+maxCount;
		     }
			
		     
		    System.out.println("value of key name is:::" +key_name);
		    
		    
			out.println("Uploading  to S3 bucket:"+ file_path + bucket_name);
			final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSCredentialsProvider() {
				
				@Override
				public void refresh() {
				}
				@Override
				public AWSCredentials getCredentials() {
					AWSCredentials aWSCredentials = new AWSCredentialsImpl();  
					return aWSCredentials;
				}
			}).withRegion(Regions.US_EAST_1).build();			
			
			try {
				
			    s3.putObject(bucket_name, key_name, new File(file_path));
			    RequestDispatcher rs = request.getRequestDispatcher("UploadSuccess.html");
			} catch (AmazonServiceException e) {
			    out.println(e.getMessage());
			   
			}finally {
				
			}
		}
		}
		
		
		}
	
		



