package com.upload.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside Upload.java");
			
		   File file ;	
		   int maxFileSize = 5000 * 1024;
		   int maxMemSize = 5000 * 1024;
		   
		   try{
		   ServletContext context = getServletContext();
//			ServletContext context = PageContext.getServletContext();
		   String filePath = context.getInitParameter("file-upload");
				 
			// Verify the content type
			String contentType = request.getContentType();
			File dir = new File("c:/temp");
			if(!dir.exists())
			{
				System.out.println("creating directory ");
				boolean result = dir.mkdir();
				if(result){
					System.out.println("Directory created");
				}else{
					System.out.println("Failed to create directory");
				}
			}
			
			MultipartRequest processFile  = new MultipartRequest(request, filePath);
			
			file = processFile.getFile("file");
			System.out.println("File name is :\t" + file.toString());
			String fileName = file.getName();
			String ProjectId = processFile.getParameter("ProjectIdForStore");
			String clientId = processFile.getParameter("clientIdForStore");
			System.out.println("ProjectIdForStore id is:\t " + ProjectId);
			System.out.println("clientIdForStore id is:\t " + clientId);
			
			ReadExcel excel = new ReadExcel();
     		System.out.println("File path is:\t" + file.getPath());
//			excel.readExcelSheet(file.getPath());
//     		String reportFilePath = excel.uploadMNDDataFromExcel(file.getPath());
     		HttpSession session = request.getSession();
     		session.setAttribute("fileName", fileName);
     		System.out.println("File Name is:\t" + fileName);
     		session.setAttribute("filePath", file.getPath());
     		System.out.println("File Path is:\t" + file.getPath());
     		System.out.println("Before Forwarding request");
     		request.setAttribute("filePath", file.getPath());
     		request.setAttribute("ProjectId", ProjectId);
     		request.setAttribute("clientId", clientId);
     		RequestDispatcher dispatch = request.getRequestDispatcher("/Download");
     		dispatch.forward(request, response);
     		return;
     		
     		/*System.out.println("At the end");
             
//             out.println("Uploaded Filename: "  +  fileName );
     		System.out.println("Upload SuccessFul:\t" + fileName);*/
     		
			
		   }catch(FileNotFoundException fnot){
			   fnot.printStackTrace();
		   }
			 /*if ((contentType.indexOf("multipart/form-data") >= 0)) {

			      DiskFileItemFactory factory = new DiskFileItemFactory();
			      // maximum size that will be stored in memory
			      factory.setSizeThreshold(maxMemSize);
			      // Location to save data that is larger than maxMemSize.
			      factory.setRepository(new File("d:\\temp"));
			      	
			      //isMultipart content
			      boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			      System.out.println("Status" + isMultipart);
			      
			      
			      // Create a new file upload handler
			      ServletFileUpload upload = new ServletFileUpload(factory);
			      // maximum file size to be uploaded.
			      upload.setSizeMax( maxFileSize );
			      try{ 
			          // Parse the request to get file items.
			          List fileItems = upload.parseRequest(request);
			          
			          // Process the uploaded file items
			          Iterator i = fileItems.iterator();
			          
			          
			          while ( i.hasNext () ) 
			          {
			             FileItem fi = (FileItem)i.next();
			             if ( !fi.isFormField () )	
			             {
			             // Get the uploaded file parameters
			             String fieldName = fi.getFieldName();
			             String fileName = fi.getName();
			             boolean isInMemory = fi.isInMemory();
			             long sizeInBytes = fi.getSize();
			             // Write the file
			             if( fileName.lastIndexOf("\\") >= 0 )
			             {
			            	 file = new File( filePath +"\\"+ fileName.substring( fileName.lastIndexOf("\\"))) ;
			             }
			             else
			             {
				             file = new File( filePath +"\\"+ fileName.substring(fileName.lastIndexOf("\\")+1)) ;
			             }
			             fi.write( file ) ;
			             System.out.println("File written successfully");
			             //Read File contents 
			     		ReadExcel excel = new ReadExcel();
			     		System.out.println("File path is:\t" + file.getPath());
//						excel.readExcelSheet(file.getPath());
//			     		String reportFilePath = excel.uploadMNDDataFromExcel(file.getPath());
			     		HttpSession session = request.getSession();
			     		session.setAttribute("fileName", fileName);
			     		session.setAttribute("filePath", file.getPath());
			     		System.out.println("Before Forwarding request");
			     		request.setAttribute("filePath", file.getPath());
			     		RequestDispatcher dispatch = request.getRequestDispatcher("/Download");
			     		dispatch.forward(request, response);
			     		
			     		System.out.println("At the end");
			             
//			             out.println("Uploaded Filename: "  +  fileName );
			     		System.out.println("Upload SuccessFul:\t" + fileName);
			             }
			          }
			          
			       }catch(Exception ex) {
//			          System.out.println(ex);
			    	   ex.printStackTrace();
			       }
			    }else{
//			      writer.println("Upload Failed..!");
			    	System.out.println("Upload Failed..!");
			    }*/
		
	}

}
