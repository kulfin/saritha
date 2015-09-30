package com.upload.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fd.Connect.ProjectServices;
import com.fd.utility.Util;

public class UploadService {

	static String driver = "com.mysql.jdbc.Driver";
//	static String url = "jdbc:mysql://localhost:3307/fourth_dimension";
	static String url = "jdbc:mysql://localhost:3307/fourth_dimension";
	static String userName = "root";
	static String password = "root";
	static Connection con ; 
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	String statusSeperator = "@&@";
	String rowSeperator = "!&!";
	String columnSeperator = "#&#";
	String fieldSeperator = "$&$";
	String rowData[];
	String columnData[];
	String outData;
	String inData;

	int status;
	String data;
	String query1 = "";
	int rowCount;
	
//	private static final int BUFSIZE = 4096;
	private String filePath;

	public void getConnection() {
		try {

			/*Class.forName(driver);
			con = DriverManager.getConnection(url, userName, password);*/
			Class.forName(Util.driver);
			con = DriverManager.getConnection(Util.url, Util.userName, Util.password);
			// System.out.println("Connection successful");

		} catch (Exception e) {

			// System.out.println(e);
			e.printStackTrace();
		}
	}


	public String setMndDetails(String str,String filePath,String FileName,int ProjectId) 
		throws IOException {
		
		System.out.println("File Path in setMndDetails" + filePath);
		String strReportFilePath = filePath + "\\"+FileName+"_Report.csv";
		//String strFileName = "";
		File reportFile = new File(strReportFilePath);
		FileWriter writer = new FileWriter(reportFile);
		System.out.println("Inside setMndDetails");
		int status = -1;
		int flag = 0;
		System.out.println("input is:\t" + str);
		String[] lines = null;
		boolean queryResult= false;
		boolean imported = false;
//		File file = new File(filePath);
        int length   = 0;
        
		try {
			
			
			lines = str.split("#&#");
			System.out.println("First Line is" + lines[0]);
			getConnection();
			con.setAutoCommit(false);
			System.out.println("Line " + lines.length);
			String[] elements = lines[0].split("#");
			String headers = "";
			for(int i=0;i<elements.length;i++)
			{
				headers = headers+ elements[i] +",";
			}
			headers += "Status" + ",";
			
			
			StringBuffer n=new StringBuffer(); 
			n.append(headers.toCharArray());
			//String strElementStatus = "\n" + ",";
			String statusLineValue = "\n";
			System.out.println("Total lines :|\t" + ((lines.length)-1 ));
			for(int lineValues=1;lineValues<lines.length;lineValues++){
				String[] rowValues = lines[lineValues].split("#");
				/*for(int test=0;test<rowValues.length -1 ; test++){
					System.out.println("rowValues ::\t" + rowValues[test]);	
				}*/
//				if(rowValues[0] != "" || rowValues[1] != ""){
					PreparedStatement pstForstore = con.prepareStatement("SELECT * FROM fourth_dimension.Project_stores WHERE store_name=? " +
							" and Project_id=?");
					System.out.println("store_name is " + rowValues[0]);
					pstForstore.setString(1, rowValues[0]);
					pstForstore.setInt(2, ProjectId);
					ResultSet rsForStore = pstForstore.executeQuery();
					if(rsForStore.next()){
						System.out.println("Inside store resultSet");
						int Existing_Project_store_id = rsForStore.getInt("Project_store_id");
						System.out.println("Exists Project_store_id:\t" + Existing_Project_store_id);
						String elementName="";
						for(int elementCount=4;elementCount<elements.length;elementCount++){
								String strquantity = rowValues[elementCount];
								int quantity=0;
								if(strquantity.equals("")){
								 quantity = 0;
								}else{
									
									quantity = Integer.parseInt(strquantity);
									System.out.println("Quantity value is:\t" + quantity);
								}
								
//								PreparedStatement pstForProgamElementExistance = con.prepareStatement("SELECT * FROM fourth_dimension.Project_elements WHERE Project_id=? and project_code=?;");
								PreparedStatement pstForProgamElementExistance = con.prepareStatement("SELECT * FROM fourth_dimension.Project_elements WHERE Project_id=? and project_name=?;");
								pstForProgamElementExistance.setInt(1, ProjectId);
								pstForProgamElementExistance.setString(2, elements[elementCount]);
								ResultSet rsForProgamElementExistance = pstForProgamElementExistance.executeQuery();
								if(rsForProgamElementExistance.next()){
									
									elementName = rsForProgamElementExistance.getString(4); 
									int exisiting_Project_element_id = rsForProgamElementExistance.getInt(1);
									/*System.out.println("Project_element entry exists and the id is :\t" + exisiting_Project_element_id);
									PreparedStatement pstForProjectElementUpdate = con.prepareStatement("UPDATE fourth_dimension.Project_elements set quantity=? " +
											"WHERE Project_element_id=?;");
									pstForProjectElementUpdate.setInt(1, quantity);
									pstForProjectElementUpdate.setInt(2, exisiting_Project_element_id);
									System.out.println("update query is"+ pstForProjectElementUpdate);
									int updateStatus = pstForProjectElementUpdate.executeUpdate();
									if(updateStatus>0){*/
										
										System.out.println("Quantity is update for Project_Elements ");
										PreparedStatement pstForelementMapExistance = con.prepareStatement("SELECT * FROM fourth_dimension.project_store_element_mapping " +
										"WHERE Project_element_id=? and Project_store_id=?");
										pstForelementMapExistance.setInt(1, exisiting_Project_element_id);
										pstForelementMapExistance.setInt(2, Existing_Project_store_id);
										ResultSet rsForMndExistance = pstForelementMapExistance.executeQuery();
										if(rsForMndExistance.next()){
											int existing_Project_element_id_in_Mnd = rsForMndExistance.getInt(1);
											PreparedStatement pstForUpdateelementMnd = con.prepareStatement("UPDATE fourth_dimension.project_store_element_mapping set quantity=? " +
	//												"where Project_element_id=? and Project_store_id =?;");
											"where project_element_mapping_id=?");
											pstForUpdateelementMnd.setInt(1, quantity);
											pstForUpdateelementMnd.setInt(2, existing_Project_element_id_in_Mnd);
	//										pstForUpdateelementMnd.setInt(2, Project_element_id);
	//										pstForUpdateelementMnd.setInt(3, Project_store_id);
											int mndMapResult = pstForUpdateelementMnd.executeUpdate();
											if(mndMapResult>0){
												imported = true;
												System.out.println("Data updated to MndElementMapping");
												con.commit();
												ProjectServices services = new ProjectServices();
												services.insert_project_store_element_mapping_into_measurement_data(Existing_Project_store_id, exisiting_Project_element_id);
												/*for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
													statusLineValue += rowValues[rowForstatus] + ",";
												}
												statusLineValue += "imported" + "," + "\n";*/
												
											}else{
												System.out.println("Failed to  update Data to MndElementMapping");
											}
											
										}else{
											PreparedStatement pstForelementMnd = con.prepareStatement("INSERT INTO fourth_dimension.project_store_element_mapping" +
													"(project_element_mapping_id,Project_element_id,Project_store_id,quantity)values(?,?,?,?);");
											pstForelementMnd.setInt(1, 0);
											pstForelementMnd.setInt(2, exisiting_Project_element_id);
											pstForelementMnd.setInt(3, Existing_Project_store_id);
											pstForelementMnd.setInt(4, quantity);
											int mndMapResult = pstForelementMnd.executeUpdate();
											if(mndMapResult>0){
												imported = true;
												System.out.println("Data inserted to MndElementMapping");
												con.commit();
												ProjectServices services = new ProjectServices();
												services.insert_project_store_element_mapping_into_measurement_data(Existing_Project_store_id, exisiting_Project_element_id);
												/*for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
													statusLineValue += rowValues[rowForstatus] + ",";
												}
												statusLineValue += "imported" + "," + "\n";*/
												
											}else{
												System.out.println("Failed to  insert Data to MndElementMapping");
											}
										}
								/*	}
									else{
										System.out.println("Failed to update the quantity to Project_elements table");
									}*/
							
						}else{
							elementName = elements[elementCount];
							for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
								statusLineValue += rowValues[rowForstatus] + ",";
							}
							statusLineValue += elements[elementCount] +"  Element Does not exists" + "," + "\n";
						}
					}
					if(imported){
						for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
							statusLineValue += rowValues[rowForstatus] + ",";
						}
						statusLineValue += "imported" + "," + "\n";
					}
					}else{
						System.out.println("Inside resultset else block");
						
						PreparedStatement pstForRegion = con.prepareStatement("SELECT * FROM fourth_dimension.region_master WHERE region_name=?");
						pstForRegion.setString(1, rowValues[2]);
						ResultSet rsForRegion = pstForRegion.executeQuery();
						if(rsForRegion.next()){
							PreparedStatement pstForState = con.prepareStatement("SELECT * FROM fourth_dimension.state_master WHERE state_name=?");
							pstForState.setString(1, rowValues[3]);
							ResultSet rsForState = pstForState.executeQuery();
							if(rsForState.next()){
								PreparedStatement pstForCity = con.prepareStatement("SELECT * FROM fourth_dimension.city_master WHERE city_name=?");
								pstForCity.setString(1, rowValues[1]);
								ResultSet rsForCity = pstForCity.executeQuery();
								if(rsForCity.next()){
									
									PreparedStatement pstForstoreInsert = con.prepareStatement("INSERT INTO fourth_dimension.Project_stores(Project_id,store_name,city_name,region_name,state_name) " +
									"values(?,?,?,?,?)");
							System.out.println("Store PreparedStatement" + pstForstoreInsert);
							System.out.println("values[0]" + rowValues[0] );
							System.out.println("values[0]" + rowValues[1] );
							System.out.println("values[0]" + rowValues[2] );
							System.out.println("values[0]" + rowValues[3] );
							pstForstoreInsert.setInt(1, ProjectId);
							pstForstoreInsert.setString(2, rowValues[0]);
							pstForstoreInsert.setString(3, rowValues[1]);
							pstForstoreInsert.setString(4, rowValues[2]);
							pstForstoreInsert.setString(5, rowValues[3]);
							int storeInsertResult =  pstForstoreInsert.executeUpdate();
							if(storeInsertResult>0){
								System.out.println("Inside create store resultSet");
								Statement lastInsertid =con.createStatement();
								ResultSet rsForLastInsertId = lastInsertid.executeQuery("SELECT LAST_INSERT_ID() as id");
								int inserted_Project_store_id = 0;
								if(rsForLastInsertId.next()){
									inserted_Project_store_id = rsForLastInsertId.getInt("id");
								}
								System.out.println("Exists Project_store_id:\t" + inserted_Project_store_id);
								String elementName="";
								for(int elementCount=4;elementCount<elements.length;elementCount++){
//									
									String element_name;
									System.out.println("Row VALue is ::::\t" + rowValues[elementCount]);
									String strquantity = rowValues[elementCount];
									int quantity=0;
									if(strquantity.equals("") || (strquantity.equals("NA"))){
									 quantity = 0;
									}else{
										quantity = Integer.parseInt(strquantity);
										System.out.println("Quantity value is:\t" + quantity);
									}
										PreparedStatement pstForProgamElementExistance = con.prepareStatement("SELECT * FROM fourth_dimension.Project_elements WHERE Project_id=? and project_name=?;");
//										PreparedStatement pstForProgamElementExistance = con.prepareStatement("SELECT * FROM fourth_dimension.Project_elements WHERE Project_id=? and project_code=?;");
										pstForProgamElementExistance.setInt(1, ProjectId);
										pstForProgamElementExistance.setString(2, elements[elementCount]);
										ResultSet rsForProgamElementExistance = pstForProgamElementExistance.executeQuery();
										if(rsForProgamElementExistance.next()){
											int exisiting_Project_element_id = rsForProgamElementExistance.getInt(1);
											element_name = rsForProgamElementExistance.getString(4);
											System.out.println("Project_element entry exists and the id is :\t" + exisiting_Project_element_id);
											/*PreparedStatement pstForProjectElementUpdate = con.prepareStatement("UPDATE fourth_dimension.Project_elements set quantity=? " +
													"WHERE Project_element_id=?;");
											pstForProjectElementUpdate.setInt(1, quantity);
											pstForProjectElementUpdate.setInt(2, exisiting_Project_element_id);
											System.out.println("update query is"+ pstForProjectElementUpdate);
											int updateStatus = pstForProjectElementUpdate.executeUpdate();
											if(updateStatus>0){*/
													
												System.out.println("Quantity is update for Project_Elements ");
												PreparedStatement pstForelementMapExistance = con.prepareStatement("SELECT * FROM fourth_dimension.project_store_element_mapping " +
												"WHERE Project_element_id=? and Project_store_id=?");
												pstForelementMapExistance.setInt(1, exisiting_Project_element_id);
												pstForelementMapExistance.setInt(2, inserted_Project_store_id);
												ResultSet rsForMndExistance = pstForelementMapExistance.executeQuery();
												if(rsForMndExistance.next()){
													int existing_Project_element_id_in_Mnd = rsForMndExistance.getInt(1);
													PreparedStatement pstForUpdateelementMnd = con.prepareStatement("UPDATE fourth_dimension.project_store_element_mapping set quantity=? " +
			//												"where Project_element_id=? and Project_store_id =?;");
													"where project_element_mapping_id=?");
													pstForUpdateelementMnd.setInt(1, quantity);
													pstForUpdateelementMnd.setInt(2, existing_Project_element_id_in_Mnd);
			//										pstForUpdateelementMnd.setInt(2, Project_element_id);
			//										pstForUpdateelementMnd.setInt(3, Project_store_id);
													int mndMapResult = pstForUpdateelementMnd.executeUpdate();
													if(mndMapResult>0){
														imported = true;
														System.out.println("Data updated to MndElementMapping");
														con.commit();
														ProjectServices services = new ProjectServices();
														services.insert_project_store_element_mapping_into_measurement_data(inserted_Project_store_id, exisiting_Project_element_id);
														/*for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
															statusLineValue += rowValues[rowForstatus] + ",";
														}
														statusLineValue += "imported" + "," + "\n";*/
														
													}else{
														System.out.println("Failed to  update Data to MndElementMapping");
													}
													
												}else{
													PreparedStatement pstForelementMnd = con.prepareStatement("INSERT INTO fourth_dimension.project_store_element_mapping" +
															"(project_element_mapping_id,Project_element_id,Project_store_id,quantity)values(?,?,?,?);");
													pstForelementMnd.setInt(1, 0);
													pstForelementMnd.setInt(2, exisiting_Project_element_id);
													pstForelementMnd.setInt(3, inserted_Project_store_id);
													pstForelementMnd.setInt(4, quantity);
													int mndMapResult = pstForelementMnd.executeUpdate();
													if(mndMapResult>0){
														imported = true;
														System.out.println("Data inserted to MndElementMapping");
														con.commit();
														ProjectServices services = new ProjectServices();
														services.insert_project_store_element_mapping_into_measurement_data(inserted_Project_store_id, exisiting_Project_element_id);
														/*for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
															statusLineValue += rowValues[rowForstatus] + ",";
														}
														statusLineValue += "imported" + "," + "\n";*/
														
													}else{
														System.out.println("Failed to  insert Data to MndElementMapping");
													}
												}
											/*}else{
												System.out.println("Failed to update the quantity to Project_elements table");
											}*/
										
									}else{
										elementName = elements[elementCount];
										for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
											statusLineValue += rowValues[rowForstatus] + ",";
										}
										statusLineValue += elements[elementCount] +"  Element Does not exists" + "," + "\n";
									}
								}
								if(imported){
									for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
										statusLineValue += rowValues[rowForstatus] + ",";
									}
									statusLineValue += "imported" + "," + "\n";
								}
							}else{
								System.out.println("Failed to create store");
								for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
									statusLineValue += rowValues[rowForstatus] + ",";
								}
								statusLineValue += "Failed to create Store" + "," + "\n";
							}
									
								}else{
									for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
										statusLineValue += rowValues[rowForstatus] + ",";
									}
									statusLineValue +=  "City\t"+ rowValues[1] +"\t Does not exists.Date not imported\n";
								}
							}else{
								for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
									statusLineValue += rowValues[rowForstatus] + ",";
								}
								statusLineValue +=  "State\t" + rowValues[3] +"\tDoes not exists.Date not imported\n";
							}
						}else{
							for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
								statusLineValue += rowValues[rowForstatus] + ",";
							}
							statusLineValue +=  "Region\t" + rowValues[2] +"\tDoes not exists.Date not imported\n";
						}
						
					}
					/*if(imported){
						for(int rowForstatus=0;rowForstatus<rowValues.length;rowForstatus++){
							statusLineValue += rowValues[rowForstatus] + ",";
						}
						statusLineValue += "imported" + "," + "\n";
					}*/
			//}
		}
//			writer.append(headers);
			writer.append(statusLineValue);
			
		} catch (Exception e) {
			// System.out.println(e);
			try {
				con.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
				if (ps != null)
					ps.close();
				if (ps != null)
					ps.close();
				if(writer != null)
				{
					writer.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return reportFile.getPath();
	}

}
