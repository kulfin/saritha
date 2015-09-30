package com.upload.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;


/**
 * Servlet implementation class Download
 */
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;
	String filePath;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {

		String destFile = "";
		String strTempFile = "";
		String strTempFileName = "";
		String outPutFilePath = "";
		HttpSession session = null;
		try {
			session = request.getSession();
			strTempFile=  session.getAttribute("fileName").toString();
			strTempFileName =  strTempFile.substring(0, strTempFile.length()-4);
			
			destFile = session.getAttribute("filePath").toString();
			System.out.println("Reading :\t" + destFile);
			Workbook wb = Workbook.getWorkbook(new File(destFile));
//			System.out.println(wb.getNumberOfSheets()); // Total Sheet numbers
			String line = "";										// available
			for (int sheetNo = 0; sheetNo < wb.getNumberOfSheets(); sheetNo++) {
				
				Sheet sheet = wb.getSheet(sheetNo);
				System.out.println("Sheet Name is:\t" + sheet.getName());
				if(sheet.getName().trim().equals("Store List")){
					int columns = sheet.getColumns();
					int rows = sheet.getRows();
					String data;
	
					// for(int row = 1;row < rows - 1;row++) {
					for (int row = 0; row < rows; row++) {
						
						for (int col = 1; col < columns; col++) {
							data = sheet.getCell(col, row).getContents();
							if (data != null && data.length() != 0)
							{
								System.out.println("data for row :\t" + row + "\tand column:\t" + col +"\t and the value is:\t" + data );
								line = line + data + "#";
							}else{
								
								System.out.println("data for row :\t" + row + "\tand column:\t" + col +"\t and the value is set to :\t NA" );
								line = line + "NA" + "#";
							}
							
	//						System.out.print(data + " ");
						}
	//					 System.out.println("Row ["+row+"] =>  " + line);
						 line += "#&#";
					}
					System.out.println("Entire line is" + line);
				}
			}
			UploadService src = new UploadService();
			// src.setStore(line); //bulk store create
			String strReportFilePath = "c:\\DMS\\Report";
			int ProjectId = Integer.parseInt(request.getAttribute("ProjectId").toString());
			System.out.println("Project id in Download.java:\t" + ProjectId);
			outPutFilePath = src.setMndDetails(line,strReportFilePath,strTempFileName,ProjectId);
			System.out.println("Report File path is " + outPutFilePath);
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		
		
		
		System.out.println("output file path is" + outPutFilePath);
		
		//value from session object
		
		/*System.out.println("Temp File name is " + strTempFileName);
		System.out.println("File Name is" + session.getAttribute("fileName").toString());
		System.out.println("In Download Servlet");*/
		
		// File file = new File(filePath);
//		File file = new File(request.getAttribute("filePath").toString());
		File file = new File(outPutFilePath);
		System.out.println("File Name is " + request.getAttribute("filePath").toString() );
		int length = 0;
		ServletOutputStream outStream = response.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
//		filePath = context.getInitParameter("file-download");
//		filePath = request.getAttribute("filePath").toString();
//		filePath = outPutFilePath;
		System.out.println("File path is " +file.getPath() );
		String mimetype = context.getMimeType(file.getPath());

		// sets response content type
		if (mimetype == null) {
			mimetype = "application/octet-stream";
		}
		response.setContentType(mimetype);
		response.setContentLength((int) file.length());
		String fileName = (new File(file.getPath())).getName();
		System.out.println("FIle name is:\t" + fileName);

		// sets HTTP header
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");

		byte[] byteBuffer = new byte[BUFSIZE];

		DataInputStream in = new DataInputStream(new FileInputStream(file));

		// reads the file's bytes and writes them to the response stream
		while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
			outStream.write(byteBuffer, 0, length);
		}

		in.close();
		outStream.close();
	
	}

}
