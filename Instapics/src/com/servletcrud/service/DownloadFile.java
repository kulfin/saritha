package com.servletcrud.service;
import com.servletcrud.service.IncrementInteger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
	

	
		
		@WebServlet("/downloadFile")
		public class DownloadFile extends HttpServlet{
		
		private final static String bucket_name= "testbucketcreate";
		private final static String file_path= "D:\\vasu\\awsupload\\";
		private final static String Config_path= "D:\\vasu\\awsupload\\config";
		private  String key_name= "AKIARO";
		
		  
		   
		   @POST
		   @Path("/download")
		   @javax.ws.rs.Produces(MediaType.TEXT_HTML)
		   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
			   String key = "AKIAJBAX6ZISLRFMMBXA";
			   
			  
                FileOutputStream fos=null;
                BufferedOutputStream writer=null;
			    String fileName = "slack.csv";


			    String bucketName = "mytestbucket23275";
			    
			    PrintWriter out=response.getWriter();
			    
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
			    
			    
			    byte[] imageBytes = new byte[0];
			    S3Object s3object = s3.getObject(bucketName, key);
			    S3ObjectInputStream inputStream = s3object.getObjectContent();
			    try {
			        imageBytes = IOUtils.toByteArray(inputStream);
			        
			        	 fos = new FileOutputStream("C://Users//NKulkarni//Desktop//NewFile1");
			             writer = new BufferedOutputStream(fos);
			        	 writer.write(imageBytes);
			        	 System.out.println("file is written to the destination");
			           
			        
			        
			        
			        
			    } catch (IOException e) {
			        e.printStackTrace();
			    }finally {
			    	if (writer != null) {
			            writer.close();
			          }
			    	if (fos != null) {
			            fos.close();
			          }
			    	
			    }
			    

		}
		}
		



