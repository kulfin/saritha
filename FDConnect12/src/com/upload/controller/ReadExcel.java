package com.upload.controller;

import java.io.File;


import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {

	// jxl.read.biff.BiffException: Unable to recognize OLE stream
	public void readExcelSheet(String destFile) {
		// System.out.println("DestFile is\t" + destFile);

		try {

			Workbook wb = Workbook.getWorkbook(new File(destFile));
			System.out.println(wb.getNumberOfSheets()); // Total Sheet numbers
														// available
			String line = "";
			for (int sheetNo = 0; sheetNo < wb.getNumberOfSheets(); sheetNo++) {
				Sheet sheet = wb.getSheet(sheetNo);
				int columns = sheet.getColumns();
				// System.out.println("Total coulmns are\t" + columns);
				int rows = sheet.getRows();
				// System.out.println("Total rows are\t" + rows);
				String data;

				// System.out.println("Sheet Name\t"+wb.getSheet(sheetNo).getName());

				// for(int row = 1;row < rows - 1;row++) {
				for (int row = 1; row < rows; row++) {
					for (int col = 1; col < columns; col++) {
						data = sheet.getCell(col, row).getContents();
						line = line + data + "#";
						// System.out.print(data+ " ");
					}
					// System.out.println("Line by line\t" + line);
					line += "#&#";
					System.out.println("Line is:\t" + line);

					// System.out.println("\n");
				}

				UploadService src = new UploadService();
				// src.setStore(line); //bulk store create
			}
			// System.out.println("entire line is:\t" + line);
			/*
			 * String[] lines = line.split("#&#"); for(int
			 * i=0;i<lines.length;i++) { String[] values = lines[i].split("#");
			 * for(int j=0;j<values.length;j++) {
			 * System.out.println("Values in line "+ i +":\t" + values[j]); } }
			 */
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ReadExcel excel = new ReadExcel();
//		excel
//				.readExcelSheet("d:\\FD Task\\upload job card details from excel\\MND-Storelist-Elements_copy.xls");
		String report = excel
		.uploadMNDDataFromExcel("d:\\FD Task\\upload job card details from excel\\MND-Storelist-Elements_copy.xls");
		// excel.readExcelSheet("d:\\DMS\\Temp\\MND-Storelist-Elements.xls");

	}

	public String uploadMNDDataFromExcel(String destFile) {
		
		System.out.println("Reading " + destFile);
		try {

			Workbook wb = Workbook.getWorkbook(new File(destFile));
//			System.out.println(wb.getNumberOfSheets()); // Total Sheet numbers
			String line = "";										// available
			for (int sheetNo = 0; sheetNo < wb.getNumberOfSheets(); sheetNo++) {
				Sheet sheet = wb.getSheet(sheetNo);
				int columns = sheet.getColumns();
				int rows = sheet.getRows();
				String data;

			
				// for(int row = 1;row < rows - 1;row++) {
				for (int row = 0; row < rows; row++) {
					
					for (int col = 1; col < columns; col++) {
						data = sheet.getCell(col, row).getContents();
						line = line + data + "#";
//						System.out.print(data + " ");
					}
//					 System.out.println("Row ["+row+"] =>  " + line);
					 line += "#&#";
				}
				System.out.println("Entire line is" + line);
				
			}
			UploadService src = new UploadService();
			// src.setStore(line); //bulk store create
//			src.setMndDetails(line);
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return "";
	}

}
