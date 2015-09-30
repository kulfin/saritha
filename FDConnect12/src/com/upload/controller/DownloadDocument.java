package com.upload.controller;

import java.io.DataInputStream;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTPClient;

import com.fd.Connect.ProjectServices;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class Upload
 */
public class DownloadDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside Upload.java");
			
		 
		   
		   try{
		 
		
			  ProjectServices ps =new ProjectServices();
			   int documentId=Integer.parseInt(request.getParameter("documentId"));
			File fileToDownload=ps.getDocument(documentId);
			
		    OutputStream outStream = response.getOutputStream();
			ServletContext context1 = getServletConfig().getServletContext();
//	
			String mimetype = context1.getMimeType(fileToDownload.getPath());

			System.out.println("MiMe type is "+mimetype);
			if (mimetype == null) {
				mimetype = "application/octet-stream";
			}
			response.setContentType(mimetype);
			response.setContentLength((int) fileToDownload.length());
			//String fileName1 = (new File(file1.getPath())).getName();
		//	System.out.println("FIle name is:\t" + fileName);

		
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileToDownload.getName() + "\"");
			int BUFSIZE = 4096;
			byte[] byteBuffer = new byte[BUFSIZE];

			DataInputStream in = new DataInputStream(new FileInputStream(fileToDownload));
          int length;
			// reads the file's bytes and writes them to the response stream
			while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
				outStream.write(byteBuffer, 0, length);
				//System.out.println("file data is "+length);
			}

			in.close();
			outStream.close();
		

     		return;
    
     		
			
		   }catch(FileNotFoundException fnot){
			   fnot.printStackTrace();
		   }
			
	}

}
