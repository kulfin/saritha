package com.fd.Rfpr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fd.utility.Util;

public class Service {
	static String driver = "com.mysql.jdbc.Driver";
//	static String url = "jdbc:mysql://localhost:3306/fourth_dimension";
	static String url = "jdbc:mysql://localhost:3306/fourth_dimension";
	static String userName = "root";
//	static String userName = "root";
	static String password = "root";
	static int initialConnections = 1;
	static int maxConnections = 5;
	Connection con = null;
	static ConnectionPool cp;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	String statusSeperator = "@&@";
	String rowSeperator = "!&!";
	String columnSeperator = "#&#";
	String fieldSeperator = "!&#!";
	
	String rowData[];
	String columnData[];
	String outData;
	String inData;
	
	int    status;
	String data;
	String data1;
	String query1;
	String query2;
	String query3;
	String query4;
	int flag[];
	int flag1;
	int flag2;
	int flag3;
	int rowCount;

	static {
		try {
			/*cp = new ConnectionPool(driver, url, userName, password,
					initialConnections, maxConnections, true);*/
			
			cp = new ConnectionPool(Util.driver, Util.url, Util.userName, Util.password ,
					Util.initialConnections, Util.maxConnections, true);
		} catch (Exception e) {

			System.out.println(e);
		}

	}

	/* Manage Places */

	// Country Administration

	// Output=countryId+columnSeperator+countryName+columnSeperator+rowSeperator

	public String getClient(String inData)  {
		int clientId;
		String clientName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "select client_id,client_name from fourth_dimension.client_master order by client_name";
           

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				clientId = rs.getInt(1);
				clientName = rs.getString(2);

				rowCount++;
				data = data + clientId + columnSeperator + clientName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	
	
	public String getProject(String inData)  {
		int clientId;
		int projectId;
		String projectName;
		status = 0;
		data = "";
		rowCount = 0;
		
		if ((inData == null) || inData.equals("")) {
			status = -2;
			data="Data is insufficient to Operation";
			outData=status + statusSeperator+data;
			return outData;
		}
		try {
		clientId=Integer.parseInt(inData);
		query1 = "select project_id,project_name from fourth_dimension.project where client_id="+clientId;
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				projectId = rs.getInt(1);
				projectName = rs.getString(2);

				rowCount++;
				data = data + projectId + columnSeperator + projectName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	
	public String getElement(String inData)  {
		int projectId;
		int projectElementId;
		String projectElementName;
		status = 0;
		data = "";
		rowCount = 0;
		
		if ((inData == null) || inData.equals("")) {
			status = -2;
			data="Data is insufficient to Operation";
			outData=status + statusSeperator+data;
			return outData;
		}
		try {
		projectId=Integer.parseInt(inData);
		query1 = "select project_element_id,project_name from fourth_dimension.project_elements where project_id="+projectId;
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				projectElementId = rs.getInt(1);
				projectElementName = rs.getString(2);

				rowCount++;
				data = data + projectElementId + columnSeperator + projectElementName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
			System.out.println(e);
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	public String getElementNameAndQuantity(String inData)  {
		int quantity;
		int ProjectElementId;
		String elementName;
		status = 0;
		data = "";
		rowCount = 0;
		
		if ((inData == null) || inData.equals("")) {
			status = -2;
			data="Data is insufficient to Operation";
			outData=status + statusSeperator+data;
			return outData;
		}
		try {
		ProjectElementId=Integer.parseInt(inData);
		System.out.println("this is service and Project element id is "+ProjectElementId);
		query1 = "select element_name,quantity from fourth_dimension.Project_elements pe,element_master em where" +
				" pe.element_id=em.element_id and Project_element_id="+ProjectElementId;
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {

				elementName= rs.getString(1);
				quantity = rs.getInt(2);

				rowCount++;
				data =  elementName + columnSeperator + quantity ;
						
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	
	public String getBrand(String inData)  {
		int brandId;
		
		String brandName;
		status = 0;
		data = "";
		rowCount = 0;
		
	
		try {
		
		query1 = "select brand_id,brand_name from fourth_dimension.brand_master order by brand_name ";
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				brandId = rs.getInt(1);
				brandName = rs.getString(2);

				rowCount++;
				data = data + brandId + columnSeperator + brandName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	public String getMaterial(String inData)  {
		int materialId;
		
		String materialName;
		status = 0;
		data = "";
		rowCount = 0;
		
	
		try {
		
		query1 = "select material_id,material_name from fourth_dimension.material_master order by material_name ";
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialId = rs.getInt(1);
				materialName = rs.getString(2);

				rowCount++;
				data = data + materialId + columnSeperator + materialName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	
	public String getUnit(String inData)  {
		int unitId;
		
		String unitName;
		status = 0;
		data = "";
		rowCount = 0;
		
	
		try {
		
		query1 = "select id,unit_name from fourth_dimension.unit_master order by unit_name ";
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				unitId = rs.getInt(1);
				unitName = rs.getString(2);

				rowCount++;
				data = data + unitId + columnSeperator + unitName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	public String getProcess(String inData)  {
		int processId;
		
		String processName;
		status = 0;
		data = "";
		rowCount = 0;
		
	
		try {
		
		query1 = "select process_id,process_name from fourth_dimension.process_master order by process_name ";
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				processId = rs.getInt(1);
				processName = rs.getString(2);

				rowCount++;
				data = data + processId + columnSeperator + processName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}


	// Input=countryName;
	
	public String getBomByProjectElementId(String inData)  {
		int ProjectElementId;
		int bomId;
		String bomCode;
		String bomVersionNumber;
		int ProjectId;
		String ProjectName;
		int clientId;
		String clientName;
		String projectCode;
		String elementName;
		int brandId;
		String brandName;
		String bomDate;
		float costAmount;
		
		status = 0;
		data = "";
		rowCount = 0;
		
		if ((inData == null) || inData.equals("")) {
			status = -2;
			data="Data is insufficient to Operation";
			outData=status + statusSeperator+data;
			return outData;
		}
		try {
		ProjectElementId=Integer.parseInt(inData);
		System.out.println("FROM GET BOM BY PROJECT ELEMENTID________"+ProjectElementId);
		/*query1 = " Select Distinct b.bom_id, b.bom_code, b.bom_version_number, p.Project_id,p.Project_name," +
				" clt.client_id, clt.client_name, pgl.Project_element_id, pgl.project_code, " +
				" elm.element_name, b.brand_id, bm.brand_name, b.bom_date ,bcost.amount " +
				" From  fourth_dimension.bom b," +
				" fourth_dimension.Project p," +
				" fourth_dimension.client_master clt," +
				" fourth_dimension.Project_elements pgl," +
				" fourth_dimension.element_master elm," +
				" fourth_dimension.brand_master bm," +
				" fourth_dimension.bom_cost bcost " +
				" where b.Project_id = p.Project_id" +
				" and b.Project_element_id = pgl.Project_element_id	and b.client_id = clt.client_id	and b.brand_id = bm.brand_id" +
				" and pgl.element_id = elm.element_id " +
				" and b.Project_element_id="+ProjectElementId;*/
		query1 = " Select Distinct b.bom_id, b.bom_code, b.bom_version_number, p.Project_id,p.Project_name," +
		" clt.client_id, clt.client_name, pgl.Project_element_id, pgl.project_code, " +
		" elm.element_name, b.brand_id, bm.brand_name, b.bom_date " +
		" From  fourth_dimension.bom b," +
		" fourth_dimension.Project p," +
		" fourth_dimension.client_master clt," +
		" fourth_dimension.Project_elements pgl," +
		" fourth_dimension.element_master elm," +
		" fourth_dimension.brand_master bm" +
		" where b.Project_id = p.Project_id" +
		" and b.Project_element_id = pgl.Project_element_id	and b.client_id = clt.client_id	and b.brand_id = bm.brand_id" +
		" and pgl.element_id = elm.element_id " +
		" and b.Project_element_id="+ProjectElementId;
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				bomId = rs.getInt(1);
				bomCode = rs.getString(2);
				bomVersionNumber = rs.getString(3);
				ProjectId = rs.getInt(4);
				ProjectName = rs.getString(5);
				clientId = rs.getInt(6);
				clientName = rs.getString(7);
				ProjectElementId = rs.getInt(8);
				projectCode = rs.getString(9);
				elementName = rs.getString(10);
				brandId = rs.getInt(11);
				brandName = rs.getString(12);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				bomDate = sdf.format(rs.getDate(13));
				//costAmount = rs.getFloat(14);

				rowCount++;
				data = data + bomId + columnSeperator + bomCode +columnSeperator
						+ bomVersionNumber+ columnSeperator + ProjectId +columnSeperator
						+ ProjectName+ columnSeperator + clientId +columnSeperator
						+ clientName+ columnSeperator + ProjectElementId +columnSeperator
						+ projectCode+ columnSeperator + elementName +columnSeperator
						+ brandId+ columnSeperator + brandName +columnSeperator
						+ bomDate + columnSeperator + rowSeperator;
//						+ bomDate + columnSeperator + costAmount + columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = -4;
			data = "Exception:-" + e;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	public String getBomByProjectName(String inData)  {
		int ProjectElementId;
		int bomId;
		String bomCode;
		String bomVersionNumber;
		int ProjectId=0;
		String ProjectName;
		int clientId;
		String clientName;
		String projectCode;
		String elementName;
		int brandId;
		String brandName;
		String bomDate;
		
		status = 0;
		data = "";
		rowCount = 0;
		
		if ((inData == null) || inData.equals("")) {
			status = -2;
			data="Data is insufficient to Operation";
			outData=status + statusSeperator+data;
			return outData;
		}
		try {
		ProjectName=inData;
		con = cp.getConnection();
		
		query2="select Project_id from fourth_dimension.Project where Project_name='"+ProjectName+"'";
		st = con.createStatement();
		rs = st.executeQuery(query2);
		if(rs.next()){
		ProjectId=rs.getInt(1);	
		}
		
		query1 = " Select Distinct "
		+ " fourth_dimension.bom.bom_id, "
		+ " fourth_dimension.bom.bom_code, "
		+ " fourth_dimension.bom.bom_version_number, "
		+ " fourth_dimension.Project.Project_id,"
		+ " fourth_dimension.Project.Project_name, "
		+ " fourth_dimension.client_master.client_id, "
		+ " fourth_dimension.client_master.client_name, "
		+ " fourth_dimension.Project_elements.Project_element_id, "
		+ " fourth_dimension.Project_elements.project_code, "
		+ " fourth_dimension.element_master.element_name, "
		+ " fourth_dimension.bom.brand_id, "
		+ " fourth_dimension.brand_master.brand_name, "
		+ " fourth_dimension.bom.bom_date "
		+ " From "
		+ " fourth_dimension.bom, "
		+ " fourth_dimension.Project, "
		+ " fourth_dimension.client_master,"
		+ " fourth_dimension.Project_elements, "
		+ " fourth_dimension.element_master, "
		+ " fourth_dimension.brand_master "
		+ " where "
		+ " fourth_dimension.bom.Project_id = fourth_dimension.Project.Project_id "
		+ " and "
		+ " fourth_dimension.bom.Project_element_id = fourth_dimension.Project_elements.Project_element_id " 
		+ " and "
		+ " fourth_dimension.bom.client_id = fourth_dimension.client_master.client_id "
		+ " and "
		+ " fourth_dimension.bom.brand_id = fourth_dimension.brand_master.brand_id "
		+ " and "
		+ " fourth_dimension.Project_elements.element_id = fourth_dimension.element_master.element_id "
		+ " and "
		+ " fourth_dimension.bom.Project_id="+ProjectId;
		
			
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				bomId = rs.getInt(1);
				bomCode = rs.getString(2);
				bomVersionNumber = rs.getString(3);
				ProjectId = rs.getInt(4);
				ProjectName = rs.getString(5);
				clientId = rs.getInt(6);
				clientName = rs.getString(7);
				ProjectElementId = rs.getInt(8);
				projectCode = rs.getString(9);
				elementName = rs.getString(10);
				brandId = rs.getInt(11);
				brandName = rs.getString(12);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				bomDate = sdf.format(rs.getDate(13));
				

				rowCount++;
				data = data + bomId + columnSeperator + bomCode +columnSeperator
						+bomVersionNumber+ columnSeperator + ProjectId +columnSeperator
						+ProjectName+ columnSeperator + clientId +columnSeperator
						+clientName+ columnSeperator + ProjectElementId +columnSeperator
						+projectCode+ columnSeperator + elementName +columnSeperator
						+brandId+ columnSeperator + brandName +columnSeperator
						+bomDate+columnSeperator+rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	public String getBomByBomCode(String inData)  {
		int ProjectElementId;
		int bomId;
		String bomCode;
		String bomVersionNumber;
		int ProjectId;
		String ProjectName;
		int clientId;
		String clientName;
		String projectCode;
		String elementName;
		int brandId;
		String brandName;
		String bomDate;
		
		status = 0;
		data = "";
		rowCount = 0;
		
		if ((inData == null) || inData.equals("")) {
			status = -2;
			data="Data is insufficient to Operation";
			outData=status + statusSeperator+data;
			return outData;
		}
		bomCode=inData;
		System.out.println("This is service by bom code "+bomCode);
		try {
		
		query1 = " Select Distinct "
		+ " fourth_dimension.bom.bom_id, "
		+ " fourth_dimension.bom.bom_code, "
		+ " fourth_dimension.bom.bom_version_number, "
		+ " fourth_dimension.Project.Project_id,"
		+ " fourth_dimension.Project.Project_name, "
		+ " fourth_dimension.client_master.client_id, "
		+ " fourth_dimension.client_master.client_name, "
		+ " fourth_dimension.Project_elements.Project_element_id, "
		+ " fourth_dimension.Project_elements.project_code, "
		+ " fourth_dimension.element_master.element_name, "
		+ " fourth_dimension.bom.brand_id, "
		+ " fourth_dimension.brand_master.brand_name, "
		+ " fourth_dimension.bom.bom_date "
		+ " From "
		+ " fourth_dimension.bom, "
		+ " fourth_dimension.Project, "
		+ " fourth_dimension.client_master,"
		+ " fourth_dimension.Project_elements, "
		+ " fourth_dimension.element_master, "
		+ " fourth_dimension.brand_master "
		+ " where "
		+ " fourth_dimension.bom.Project_id = fourth_dimension.Project.Project_id "
		+ " and "
		+ " fourth_dimension.bom.Project_element_id = fourth_dimension.Project_elements.Project_element_id " 
		+ " and "
		+ " fourth_dimension.bom.client_id = fourth_dimension.client_master.client_id "
		+ " and "
		+ " fourth_dimension.bom.brand_id = fourth_dimension.brand_master.brand_id "
		+ " and "
		+ " fourth_dimension.Project_elements.element_id = fourth_dimension.element_master.element_id "
		+ " and "
		+ " fourth_dimension.bom.bom_code='"+bomCode+"'";
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				bomId = rs.getInt(1);
				bomCode = rs.getString(2);
				bomVersionNumber = rs.getString(3);
				ProjectId = rs.getInt(4);
				ProjectName = rs.getString(5);
				clientId = rs.getInt(6);
				clientName = rs.getString(7);
				ProjectElementId = rs.getInt(8);
				projectCode = rs.getString(9);
				elementName = rs.getString(10);
				brandId = rs.getInt(11);
				brandName = rs.getString(12);
				 
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				bomDate = sdf.format(rs.getDate(13));
				
				

				rowCount++;
				data = data + bomId + columnSeperator + bomCode +columnSeperator
						+bomVersionNumber+ columnSeperator + ProjectId +columnSeperator
						+ProjectName+ columnSeperator + clientId +columnSeperator
						+clientName+ columnSeperator + ProjectElementId +columnSeperator
						+projectCode+ columnSeperator + elementName +columnSeperator
						+brandId+ columnSeperator + brandName +columnSeperator
						+bomDate+columnSeperator+rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
			System.out.println(data);
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	public String getBomElements(String inData)  {
	    int bomId;

		
		status = 0;
		data = "";
		rowCount = 0;
		
		if ((inData == null) || inData.equals("")) {
			status = -2;
			data="Data is insufficient to Operation";
			outData=status + statusSeperator+data;
			return outData;
		}
	    bomId=Integer.parseInt(inData);
		System.out.println("This is service by bom code "+bomId);
		try {
		
		query1 = " select distinct " 
			+ " bom_element_id,element_section_name,"
			+ " be.material_id, "
			+ " (select material_name "
			+ " from material_master "
			+ " where material_master.material_id= be.material_id) material_name,"

			+ " be.height,be.height_unit,"
			+ " (select unit_name "
			+ "  from unit_master "
			+ " where id= be.height_unit) height_unit_name, "
			 
			+ " be.width,be.width_unit,"
			+ " (select unit_name from unit_master"
			+ "  where unit_master.id= be.width_unit) width_unit_name," 
					
			+ " be.thickness, be.thickness_unit, "
			+ "(select unit_name from unit_master "
			+ " where unit_master.id= be.thickness_unit) thickness_unit_name, "
			    
			+ " be.capacity,be.capacity_unit,"
			+ " (select unit_name from unit_master "
			+ "  where   unit_master.id=be.capacity_unit) capacity_unit_name, "
			    
			+ " be.quantity," 
			+ "be.order_quantity,be.order_quantity_unit," 
			+ " (select unit_name from unit_master "
			+ "  where   unit_master.id=be.order_quantity_unit) order_quantity_unit_name, "
			
			+"be.process_id ,"
			+ " (select process_master.process_name "
			+ " from process_master "
			+ " where process_master.process_id=be.process_id) process_name"
					
			+ " from bom_element be "
			+ " where be.bom_id="+bomId;
		
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				   bomId= rs.getInt(1);
	        	   String elementSection=rs.getString(2);
	        	   int materialId= rs.getInt(3);
	        	   String materialName=rs.getString(4);
	        	   double height= rs.getDouble(5);
	        	   
	        	   String heightUnit;
	        	   if (rs.getObject(6) != null) {
	        	   heightUnit = String.valueOf(rs.getInt(6));
	        	   }
	        	   else{
	        	   heightUnit=null;   
	        	   }
	        	  
	        	   String heightUnitName= rs.getString(7);
	        	   double width=rs.getDouble(8);
	        	   
	        	   String widthUnit;
	        	   if (rs.getObject(9) != null) {
	        	   widthUnit = String.valueOf(rs.getInt(9));
	        	   }
	        	   else{
	        	   widthUnit=null;   
	        	   }
	        	   
	        	   String widthUnitName=  rs.getString(10);
	        	   double thickness =rs.getDouble(11);
	        	   
	        	   String thicknessUnit;
	        	   if (rs.getObject(12) != null) {
	        	   thicknessUnit = String.valueOf(rs.getInt(12));
	        	   }
	        	   else{
	        	   thicknessUnit=null;   
	        	   }
	        	   
	        	   String thicknessUnitName = rs.getString(13);
	        	   double capacity=rs.getDouble(14);
	        	   
	        	   String capacityUnit;
	        	   if (rs.getObject(15) != null) {
	        	   capacityUnit = String.valueOf(rs.getInt(15));
	        	   }
	        	   else{
	        	   capacityUnit=null;   
	        	   }
	        	   
	        	   String capacityUnitName= rs.getString(16);
	        	   int bomQuantity=rs.getInt(17);
	        	   int orderQuantity= rs.getInt(18);
	        	   
	        	   String orderQuantityUnit;
	        	   if (rs.getObject(19) != null) {
	        		 orderQuantityUnit = String.valueOf(rs.getInt(19));
	        	   }
	        	   else{
	        		 orderQuantityUnit=null;   
	        	   }
	        	   
	        	   String orderQuantityUnitName= rs.getString(20);
	        	   
	        	   int processId= rs.getInt(21);
	        	   String processName= rs.getString(22);
	        	   
	        	  		

				rowCount++;
				data = data + bomId + columnSeperator + elementSection +columnSeperator
						+materialId+ columnSeperator + materialName +columnSeperator
						+height+ columnSeperator + heightUnit +columnSeperator
						+heightUnitName+ columnSeperator + width +columnSeperator
						+widthUnit+ columnSeperator + widthUnitName +columnSeperator
						+thickness+ columnSeperator + thicknessUnit +columnSeperator
						+thicknessUnitName+ columnSeperator + capacity +columnSeperator
						+capacityUnit+ columnSeperator + capacityUnitName +columnSeperator
						+bomQuantity+ columnSeperator + orderQuantity +columnSeperator
						+ orderQuantityUnit +columnSeperator
						+orderQuantityUnitName+ columnSeperator 
						+processId+ columnSeperator + processName +columnSeperator
						+rowSeperator;
			
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
			System.out.println(data);
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		
		return outData;
		
	}
	
	
	
	
	public int setBom(String inData)  {
		int bomId;   
		int clientId;
		int ProjectId;
		int ProjectElementId;
		int brandId;
		String bomCode;
		String bomVersionNumber;
		String bomDate;
		
		String materialIdData[];
		String heightUnitIdData[];
		String widthUnitIdData[];
		String thicknessUnitIdData[];
		String orderQuantityUnitIdData[] = null;
		String capacityUnitIdData[];
	    String processIdData[];
	    
		String elementSectionData[];
		String bomQuantityData[];
		String orderQuantityData[];
		
		String heightData[];
		String widthData[];
		String thicknessData[];
		String capacityData[];
		
	    status =0;
		flag1 = 0;
		

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		//System.out.println("update bomCost indata is "+inData);
		try {
			
		rowData=inData.split(rowSeperator);
		
		if(rowData.length==21){
		
		clientId=Integer.parseInt(rowData[0].trim());
	    ProjectId=Integer.parseInt(rowData[1].trim());
		ProjectElementId=Integer.parseInt(rowData[2].trim());
		//brandId=Integer.parseInt(rowData[3].trim());
	    bomCode=rowData[4];
		bomVersionNumber=rowData[5];
		bomDate=rowData[6];
		
		 elementSectionData=rowData[7].split(columnSeperator);
		 materialIdData=rowData[8].split(columnSeperator);
		 heightData=rowData[9].split(columnSeperator);
		 heightUnitIdData=rowData[10].split(columnSeperator);
		 widthData=rowData[11].split(columnSeperator);
		 widthUnitIdData=rowData[12].split(columnSeperator);
		 thicknessData=rowData[13].split(columnSeperator);
		 thicknessUnitIdData=rowData[14].split(columnSeperator);
		 orderQuantityUnitIdData=rowData[15].split(columnSeperator);
		 capacityData=rowData[16].split(columnSeperator);
		 capacityUnitIdData=rowData[17].split(columnSeperator);
		 bomQuantityData=rowData[18].split(columnSeperator);
		 orderQuantityData=rowData[19].split(columnSeperator);
		 processIdData=rowData[20].split(columnSeperator);
		
		
		 
		
		
		}
		
		else{
			status = -2;
			return status;
		}
		  query1="select bom_id from fourth_dimension.bom order by bom_id desc ";
		  con=cp.getConnection();
		  
		  st=con.createStatement();
		  rs=st.executeQuery(query1);
		  
		  if(rs.next()){
			bomId=rs.getInt(1);
			bomId=bomId+1;
		  }
		  else{
				bomId=1;  
			  }
		 
		  query2="select brand_id from fourth_dimension.Project_elements where Project_element_id="+ProjectElementId;
		  
		  
		  st=con.createStatement();
		  rs=st.executeQuery(query2);
		  if(rs.next()){
				brandId=rs.getInt(1);
				
			  }
		  else{
			  brandId=1;
		  }
			
		  query3 = "insert into fourth_dimension.bom values(?,?,?,?,?,?,CURDATE(),?,?,?,?,?,?,?,?,?,?,?)";
		
          ps = con.prepareStatement(query3);
          ps.setInt(1, bomId);
          if(bomCode.equals("null")||bomCode==null){
        	ps.setString(2, null);
          }
          else{
           ps.setString(2, bomCode);
          }
        
          ps.setInt(3, ProjectId);
          ps.setInt(4, clientId);
          ps.setInt(5, ProjectElementId);
          ps.setInt(6, brandId);
         // ps.setString(7, bomDate);
          if(bomVersionNumber.equals("null")||bomVersionNumber==null){
          	ps.setString(7, null);
            }
            else{
             ps.setString(7, bomVersionNumber);
            }
         
          ps.setFloat(8, 0);
          ps.setFloat(9, 0);
          ps.setFloat(10, 0);
          ps.setFloat(11, 0);
          ps.setFloat(12, 0);
          ps.setFloat(13, 0);
          ps.setFloat(14, 0);
          ps.setFloat(15, 0);
          ps.setFloat(16, 0);
          ps.setFloat(17, 0);
          
          flag1 = ps.executeUpdate();
		
        
         query3 = "insert into fourth_dimension.bom_element values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,(select rate from" +
         		" material_master where material_id=?))";
		
         if(elementSectionData!=null){
		for(int i=0;i<elementSectionData.length;i++){
			
		if(elementSectionData[i]!=""){
		ps = con.prepareStatement(query3);
		ps.setInt(1, 0);
		ps.setInt(2, bomId);
	
		ps.setString(3, elementSectionData[i]);

		ps.setString(4, "5");
	
		ps.setDouble(5, Double.parseDouble(heightData[i].trim()));
		
		
		if(heightUnitIdData[i].equals(" ")){
		
		ps.setNull(6, java.sql.Types.BIGINT); 
		}
		else{
		
		ps.setInt(6, Integer.parseInt(heightUnitIdData[i].trim()));
		}
	
	
		
		ps.setString(7, "10");
		
		if(widthUnitIdData[i].equals(" ")){
			
			ps.setNull(8, java.sql.Types.BIGINT); 
			}
			else{
			ps.setInt(8, Integer.parseInt(widthUnitIdData[i].trim()));
			}
	
		ps.setDouble(9, Double.parseDouble(thicknessData[i].trim()));
	
		
		if(thicknessUnitIdData[i].equals(" ")){
			
			ps.setNull(10, java.sql.Types.BIGINT); 
			}
			else{
			ps.setInt(10, Integer.parseInt(thicknessUnitIdData[i].trim()));
			}
		
		ps.setDouble(11, Double.parseDouble(capacityData[i].trim()));
		
		if(capacityUnitIdData[i].equals(" ")){
			
			ps.setNull(12, java.sql.Types.BIGINT); 
			}
			else{
			ps.setInt(12, Integer.parseInt(capacityUnitIdData[i].trim()));
			}
		
		ps.setInt(13, Integer.parseInt(bomQuantityData[i].trim()));
		
		ps.setInt(14, Integer.parseInt(orderQuantityData[i].trim()));
		
		
		if(processIdData[i].equals(" ")){
			
			ps.setNull(15, java.sql.Types.BIGINT); 
			}
			else{
			
			ps.setInt(15, Integer.parseInt(processIdData[i].trim()));
			}
		
	      if(orderQuantityUnitIdData[i].equals(" ")){
			
			ps.setNull(16, java.sql.Types.BIGINT); 
			}
			else{
			ps.setInt(16, Integer.parseInt(orderQuantityUnitIdData[i].trim()));
			}
	  	ps.setInt(17, Integer.parseInt(materialIdData[i].trim()));
		
        flag1 = ps.executeUpdate();
		}
		}
         }
	
	
	    } catch (SQLException e) {
			status = -4;
			e.getMessage();
		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;
	}	


	
	public int updateBom(String inData)  {
		int bomId;
		int clientId;
		int ProjectId;
		int ProjectElementId;
		int brandId;
		String bomCode;
		String bomVersionNumber;
		String bomDate;
		
		String materialIdData[];
		String heightUnitIdData[];
		String widthUnitIdData[];
		String thicknessUnitIdData[];
		String orderQuantityUnitIdData[];
		String capacityUnitIdData[];
	    String processIdData[];
	    
		String elementSectionData[];
		String bomQuantityData[];
		String orderQuantityData[];
		
		String heightData[];
		String widthData[];
		String thicknessData[];
		String capacityData[];
		
	    status =0;
		flag1 = 0;
		System.out.println("update bomCost indata is ");

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		//System.out.println("update bomCost indata is "+inData);
		try {
			
		rowData=inData.split(rowSeperator);
		
		if(rowData.length==22){
		bomId=Integer.parseInt(rowData[0].trim());
		clientId=Integer.parseInt(rowData[1].trim());
	    ProjectId=Integer.parseInt(rowData[2].trim());
		ProjectElementId=Integer.parseInt(rowData[3].trim());
		//brandId=Integer.parseInt(rowData[4].trim());
	    bomCode=rowData[5];
		bomVersionNumber=rowData[6];
		bomDate=rowData[7];
		
		 elementSectionData=rowData[8].split(columnSeperator);
		 materialIdData=rowData[9].split(columnSeperator);
		 heightData=rowData[10].split(columnSeperator);
		 heightUnitIdData=rowData[11].split(columnSeperator);
		 widthData=rowData[12].split(columnSeperator);
		 widthUnitIdData=rowData[13].split(columnSeperator);
		 thicknessData=rowData[14].split(columnSeperator);
		 thicknessUnitIdData=rowData[15].split(columnSeperator);
		 orderQuantityUnitIdData=rowData[16].split(columnSeperator);
		 capacityData=rowData[17].split(columnSeperator);
		 capacityUnitIdData=rowData[18].split(columnSeperator);
		 bomQuantityData=rowData[19].split(columnSeperator);
		 orderQuantityData=rowData[20].split(columnSeperator);
		 processIdData=rowData[21].split(columnSeperator);
		
		
		 
		
		
		}
		
		else{
			status = -2;
			return status;
		}
		
			
		con = cp.getConnection();
		query1 = "delete from fourth_dimension.bom_element where bom_id=?";
		ps = con.prepareStatement(query1);
		ps.setInt(1, bomId);
		flag1 = ps.executeUpdate();
		
		  query2 = "insert into fourth_dimension.bom_element values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,(select rate from" +
   		" material_master where material_id=?))";
		
		for(int i=0;i<elementSectionData.length;i++){
	
		ps = con.prepareStatement(query2);
		ps.setInt(1, 0);
		ps.setInt(2, bomId);
	
		ps.setString(3, elementSectionData[i]);
		ps.setInt(4, Integer.parseInt(materialIdData[i].trim()));
	
		ps.setDouble(5, Double.parseDouble(heightData[i].trim()));
		
		
		if(heightUnitIdData[i].equals(" ")){
		
		ps.setNull(6, java.sql.Types.BIGINT); 
		}
		else{
		
		ps.setInt(6, Integer.parseInt(heightUnitIdData[i].trim()));
		}
	
	
		
		ps.setDouble(7, Double.parseDouble(widthData[i].trim()));
		
		if(widthUnitIdData[i].equals(" ")){
			
			ps.setNull(8, java.sql.Types.BIGINT); 
			}
			else{
			ps.setInt(8, Integer.parseInt(widthUnitIdData[i].trim()));
			}
	
		ps.setDouble(9, Double.parseDouble(thicknessData[i].trim()));
	
		
		if(thicknessUnitIdData[i].equals(" ")){
			
			ps.setNull(10, java.sql.Types.BIGINT); 
			}
			else{
			ps.setInt(10, Integer.parseInt(thicknessUnitIdData[i].trim()));
			}
		
		ps.setDouble(11, Double.parseDouble(capacityData[i].trim()));
		
		if(capacityUnitIdData[i].equals(" ")){
			
			ps.setNull(12, java.sql.Types.BIGINT); 
			}
			else{
			ps.setInt(12, Integer.parseInt(capacityUnitIdData[i].trim()));
			}
		
		ps.setInt(13, Integer.parseInt(bomQuantityData[i].trim()));
		
		ps.setInt(14, Integer.parseInt(orderQuantityData[i].trim()));
		
         if(processIdData[i].equals(" ")){
			
		ps.setNull(15, java.sql.Types.BIGINT); 
			}
		else{
			
		ps.setInt(15, Integer.parseInt(processIdData[i].trim()));
		}
		
	    if(orderQuantityUnitIdData[i].equals(" ")){
			
			ps.setNull(16, java.sql.Types.BIGINT); 
			}
			else{
			ps.setInt(16, Integer.parseInt(orderQuantityUnitIdData[i].trim()));
			}
	    
		ps.setInt(17, Integer.parseInt(materialIdData[i].trim()));
		
        flag1 = ps.executeUpdate();
		
		}
		
		query1 = "update  fourth_dimension.bom set bom_code=? ,Project_id=? ," +
				"client_id=?,Project_element_id=?,bom_version_number=? where bom_id=?";
		ps = con.prepareStatement(query1);
		
		
		
	    if(bomCode.equals("null")||bomCode==null){
		 ps.setString(1, null);
		  }
		    else{
	    ps.setString(1, bomCode);
		  }
		ps.setInt(2, ProjectId);
		ps.setInt(3, clientId);
		ps.setInt(4, ProjectElementId);
		  if(bomVersionNumber.equals("null")||bomVersionNumber==null){
				 ps.setString(5, null);
				  }
				    else{
			    ps.setString(5, bomVersionNumber);
				  }
		ps.setInt(6, bomId);
		flag1 = ps.executeUpdate();
	
	    } catch (Exception e) {
			status = -4;
			System.out.println("Exception "+e);
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				status = -4;
				
			}

		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;
	}	



public int deleteBom(String inData)  {
	int bomId;
    status =0;
	flag1 = 0;

	if ((inData == null) || inData.equals("")) {
		status = -2;
		return status;
	}
	try {
	bomId = Integer.parseInt(inData);

	query1 = "delete from fourth_dimension.bom_cost where bom_id="
			+ bomId ;
	query2 = "delete from fourth_dimension.bom_element where bom_id="
		+ bomId ;
	
	query3 = "delete from fourth_dimension.bom where bom_id="
		+ bomId ;


		con = cp.getConnection();
		st = con.createStatement();
		st.executeUpdate(query1);
		
		st = con.createStatement();
		st.executeUpdate(query2);
		
		st = con.createStatement();
		flag1=st.executeUpdate(query3);

		

	} catch (Exception e) {
		status = -4;
		System.out.println(e);
		
	} finally {
		try {
			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			status = -4;
			
		}

	}
	if ((flag1 == 0)) {
		status = -5;
		
	}

	
	return status;
}


/*Material Consolidation And Costing Methods*/

public String getBomDropDownData(String inData)  {
	int bomId;
	String bomCode;
	status = 0;
	data = "";
	rowCount = 0;
	query1 = "select bom_id,bom_code from fourth_dimension.bom order by bom_code";
       

	try {
		con = cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {

			bomId = rs.getInt(1);
			bomCode = rs.getString(2);

			rowCount++;
			data = data + bomId + columnSeperator + bomCode
					+ columnSeperator + rowSeperator;
		}
	} catch (Exception e) {
		status = -4;
		data = "Exception:-" + e;
	} finally {
		try {

			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		}

	}
	if (rowCount == 0) {
		status = -1;
		data = "DataUnavailable";
	}
	outData = status + statusSeperator + data;
	
	return outData;
	
}


public String getBomElementData(String inData)  {
	
	if(inData.equals(null)||inData.equals("")){
		
		status=-2;
		data="input data is insufficient for operation";
		outData=status+statusSeperator+data;
		return outData;
	}
	int bomId=Integer.parseInt(inData);
	System.out.println("this is service and bom id is1 "+bomId);
	String materialName;
	Double materialRate;
	int orderQuantity;
	
	status = 0;
	data = "";
	data1=" ";
	rowCount = 0;
	query1 = "select material_name,material_rate,order_quantity from fourth_dimension.bom_element be,material_master mm where " +
			"be.material_id=mm.material_id and bom_id="+bomId;
	
	query2 = "select cim.cost_type_id,bc.cost_item_id,amount,amount_percentage from fourth_dimension.bom_cost bc,cost_item_master cim,cost_type_master ctm where " +
	"bc.cost_item_id=cim.cost_item_id and cim.cost_type_id=ctm.cost_type_id and bom_id="+bomId;  

	query3="select max_discount_percentage,max_discount_amount from bom where bom_id="+bomId;
	try {
		System.out.println("this is service and bom id is2 "+bomId);
		con = cp.getConnection();
		System.out.println("this is service and bom id is3 "+bomId);
		st = con.createStatement();
		System.out.println("this is service and bom id is4 "+bomId);
		rs = st.executeQuery(query1);
		System.out.println("this is service and bom id is5 "+bomId);
		while (rs.next()) {
			System.out.println("this is service and bom id is6 "+bomId);
			materialName = rs.getString(1);
			materialRate = rs.getDouble(2);
			orderQuantity = rs.getInt(3);

			rowCount++;
			data = data + materialName + columnSeperator + materialRate + columnSeperator + orderQuantity
					+ columnSeperator + rowSeperator;
			
			
		}
		
		int costTypeId;
		int costItemId;
		double amount;
		double amountPercentage;
		rs = st.executeQuery(query2);
		while (rs.next()) {

			costTypeId = rs.getInt(1);
			costItemId = rs.getInt(2);
			amount = rs.getDouble(3);
			amountPercentage = rs.getDouble(4);

			//rowCount++;
			data1 = data1 + costTypeId + columnSeperator + costItemId + columnSeperator + amount
					+ columnSeperator + amountPercentage + columnSeperator + rowSeperator;
			
			
		}
		String data2="";
		double bomDiscountAmount;
		double bomDiscountPercentage;
		rs = st.executeQuery(query3);
		if(rs.next()) {
			
			bomDiscountPercentage=rs.getDouble(1);
			bomDiscountAmount = rs.getDouble(2);
		

			rowCount++;
			data2 = bomDiscountAmount + columnSeperator + bomDiscountPercentage ; 
					
			
			
		}
		data=data+ fieldSeperator +data1+ fieldSeperator +data2;
		
	} catch (Exception e) {
		status = -4;
		data = "Exception:-" + e;
	} finally {
		try {

			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
			System.out.println("this is error:- "+e);
		}

	}
	if (rowCount == 0) {
		status = -1;
		data = "DataUnavailable";
	}
	outData = status + statusSeperator + data;
	//System.out.println("this is service outdata is "+outData);
	return outData;
	
}


public String getBomElementDataForViewOperation(String inData)  {
	
	if(inData.equals(null)||inData.equals("")){
		
		status=-2;
		data="input data is insufficient for operation";
		outData=status+statusSeperator+data;
		return outData;
	}
	int bomId=Integer.parseInt(inData);
	System.out.println("this is service and bom id is1 "+bomId);
	String materialName;
	Double materialRate;
	int orderQuantity;
	
	status = 0;
	data = "";
	data1=" ";
	rowCount = 0;
	query1 = "select material_name,rate,order_quantity from fourth_dimension.bom_element be,material_master mm where " +
			"be.material_id=mm.material_id and bom_id="+bomId;
	
	query2 = "select ctm.cost_type_name,cim.cost_item_name,amount,amount_percentage from fourth_dimension.bom_cost bc,cost_item_master cim,cost_type_master ctm where " +
	"bc.cost_item_id=cim.cost_item_id and cim.cost_type_id=ctm.cost_type_id and bom_id="+bomId;  

	query3="select max_discount_percentage,max_discount_amount from bom where bom_id="+bomId;
	try {
		System.out.println("this is service and bom id is2 "+bomId);
		con = cp.getConnection();
		System.out.println("this is service and bom id is3 "+bomId);
		st = con.createStatement();
		System.out.println("this is service and bom id is4 "+bomId);
		rs = st.executeQuery(query1);
		System.out.println("this is service and bom id is5 "+bomId);
		while (rs.next()) {
			System.out.println("this is service and bom id is6 "+bomId);
			materialName = rs.getString(1);
			materialRate = rs.getDouble(2);
			orderQuantity = rs.getInt(3);

			rowCount++;
			data = data + materialName + columnSeperator + materialRate + columnSeperator + orderQuantity
					+ columnSeperator + rowSeperator;
			
			
		}
		
		String costTypeName;
		String costItemName;
		double amount;
		double amountPercentage;
		rs = st.executeQuery(query2);
		while (rs.next()) {

			costTypeName = rs.getString(1);
			costItemName = rs.getString(2);
			amount = rs.getDouble(3);
			amountPercentage = rs.getDouble(4);

			//rowCount++;
			data1 = data1 + costTypeName + columnSeperator + costItemName+ columnSeperator + amount
					+ columnSeperator + amountPercentage + columnSeperator + rowSeperator;
			
			
		}
		String data2="";
		int bomDiscountAmount;
		int bomDiscountPercentage;
		rs = st.executeQuery(query3);
		if(rs.next()) {
			
			bomDiscountPercentage=rs.getInt(1);
			bomDiscountAmount = rs.getInt(2);
		

			rowCount++;
			data2 = bomDiscountAmount + columnSeperator + bomDiscountPercentage ; 
					
			
			
		}
		data=data+ fieldSeperator +data1+ fieldSeperator +data2;
		
	} catch (Exception e) {
		status = -4;
		data = "Exception:-" + e;
	} finally {
		try {

			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
			System.out.println("this is error:- "+e);
		}

	}
	if (rowCount == 0) {
		status = -1;
		data = "DataUnavailable";
	}
	outData = status + statusSeperator + data;
	//System.out.println("this is service outdata is "+outData);
	return outData;
	
}


public String getMaterialCostType(String inData)  {
	int costTypeId;
	String costTypeName;
	status = 0;
	data = "";
	rowCount = 0;
	query1 = "select cost_type_id,cost_type_name from fourth_dimension.cost_type_master order by cost_type_name";
       

	try {
		con = cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {

			costTypeId = rs.getInt(1);
			costTypeName = rs.getString(2);

			rowCount++;
			data = data + costTypeId + columnSeperator + costTypeName
					+ columnSeperator + rowSeperator;
		}
	} catch (Exception e) {
		status = -4;
		data = "Exception:-" + e;
	} finally {
		try {

			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		}

	}
	if (rowCount == 0) {
		status = -1;
		data = "DataUnavailable";
	}
	outData = status + statusSeperator + data;
	
	return outData;
	
}


public String getMaterialCostItem(String inData)  {
	int costItemId;
	String costItemName ;
	status = 0;
	data = "";
	rowCount = 0;
   if(inData.equals(null)||inData.equals("")){
		
		status=-2;
		data="input data is insufficient for operation";
		outData=status+statusSeperator+data;
		return outData;
	}
	int costTypeId=Integer.parseInt(inData);
	query1 = "select cost_item_id,cost_item_name from fourth_dimension.cost_item_master where cost_type_id="+costTypeId+" " +
			"order by cost_item_name";
       

	try {
		con = cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {

			costItemId = rs.getInt(1);
			costItemName = rs.getString(2);

			rowCount++;
			data = data + costItemId + columnSeperator + costItemName
					+ columnSeperator + rowSeperator;
		}
	} catch (Exception e) {
		status = -4;
		data = "Exception:-" + e;
	} finally {
		try {

			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
		}

	}
	if (rowCount == 0) {
		status = -1;
		data = "DataUnavailable";
	}
	outData = status + statusSeperator + data;
	
	return outData;
	
}



public int setBomCost(String inData)  {
	int bomId;
	int costItemId;
	Double amount;
	Double amountPercentage;
	Double discountAmount;
	Double discountPercentage;
	String costItemData[];
	String amountData[];
	String amountPercentageData[];
    status =0;
	flag1 = 0;

	if ((inData == null) || inData.equals("")) {
		status = -2;
		return status;
	}
	try {
		
	rowData=inData.split(rowSeperator);
	
	if(rowData.length==6){
	bomId=Integer.parseInt(rowData[0]);
	costItemData=rowData[1].split(columnSeperator);
	amountData=rowData[2].split(columnSeperator);
	amountPercentageData=rowData[3].split(columnSeperator);
	discountAmount=Double.parseDouble(rowData[4]);
	discountPercentage=Double.parseDouble(rowData[5]);
	}
	
	else{
		status = -2;
		return status;
	}
	
		
	con = cp.getConnection();
	query1 = "insert into fourth_dimension.bom_cost values(?,?,?,?)";
	
	for(int i=0;i<costItemData.length;i++){
	//System.out.println("material Group ID String "+matGroupId);
	costItemId=Integer.parseInt((costItemData[i].trim()));
	amount=Double.parseDouble((amountData[i].trim()));
	amountPercentage=Double.parseDouble((amountPercentageData[i].trim()));
	
	ps = con.prepareStatement(query1);
	ps.setInt(1, bomId);
	ps.setInt(2, costItemId);
	ps.setDouble(3, amount);
	ps.setDouble(4, amountPercentage);
    flag1 = ps.executeUpdate();
	
	}
	
	query2 = "update fourth_dimension.bom set max_discount_percentage=?,max_discount_amount=? where bom_id=?";
	ps = con.prepareStatement(query2);
	ps.setDouble(1, discountPercentage);
	ps.setDouble(2, discountAmount);
	ps.setInt(3, bomId);

    flag2 = ps.executeUpdate();

    } catch (Exception e) {
		status = -4;
		System.out.println("Exception "+e);
	} finally {
		try {
			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			status = -4;
			
		}

	}
	if ((flag1 == 0)||(flag2==0)) {
		status = -5;
		
	}

	
	return status;
}


public int updateMaterialRate(String inData)  {
	int bomId;
	String materialName;
	double rate;

    status =0;
	flag1 = 0;

	if ((inData == null) || inData.equals("")) {
		status = -2;
		return status;
	}
	try {
		System.out.println("in data for service is "+inData);
	columnData=inData.split(columnSeperator);
	
	if(columnData.length==3){
	bomId=Integer.parseInt(columnData[0]);
	materialName=columnData[1];
	rate=Double.parseDouble(columnData[2]);


	}
	else{
		status = -2;
		return status;
	}
	System.out.println("in data for service is "+inData+" columndata[2] is "+columnData[2]);
		
	con = cp.getConnection();
	query1 = "update fourth_dimension.bom_element set material_rate=? where material_id=(select material_id from " +
			" fourth_dimension.material_master where material_name=?) and bom_id=?";
	
	
	ps = con.prepareStatement(query1);
	ps.setDouble(1, rate);
	ps.setString(2, materialName);
	ps.setInt(3, bomId);
    flag1 = ps.executeUpdate();


    } catch (Exception e) {
		status = -4;
		System.out.println("Exception "+e);
	} finally {
		try {
			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			status = -4;
			
		}

	}
	if ((flag1 == 0)) {
		status = -5;
		
	}

	
	return status;
}

public int updateBomCost(String inData)  {
	int bomId;
	int costItemId;
	Double amount;
	Double amountPercentage;
	Double discountAmount;
	Double discountPercentage;
	String costItemData[];
	String amountData[];
	String amountPercentageData[];
    status =0;
	flag1 = 0;

	if ((inData == null) || inData.equals("")) {
		status = -2;
		return status;
	}
	try {
		
	rowData=inData.split(rowSeperator);
	
	if(rowData.length==6){
	bomId=Integer.parseInt(rowData[0]);
	costItemData=rowData[1].split(columnSeperator);
	amountData=rowData[2].split(columnSeperator);
	amountPercentageData=rowData[3].split(columnSeperator);
	discountAmount=Double.parseDouble(rowData[4]);
	discountPercentage=Double.parseDouble(rowData[5]);
	}
	
	else{
		status = -2;
		return status;
	}
	
		
	con = cp.getConnection();
	
	query1 = "delete from fourth_dimension.bom_cost where bom_id=?";
	ps = con.prepareStatement(query1);
	ps.setInt(1, bomId);
	flag1 = ps.executeUpdate();
	
	query1 = "insert into fourth_dimension.bom_cost values(?,?,?,?)";
	
	for(int i=0;i<costItemData.length;i++){
	//System.out.println("material Group ID String "+matGroupId);
	costItemId=Integer.parseInt((costItemData[i].trim()));
	amount=Double.parseDouble((amountData[i].trim()));
	amountPercentage=Double.parseDouble((amountPercentageData[i].trim()));
	
	ps = con.prepareStatement(query1);
	ps.setInt(1, bomId);
	ps.setInt(2, costItemId);
	ps.setDouble(3, amount);
	ps.setDouble(4, amountPercentage);
    flag1 = ps.executeUpdate();
	
	}
	
	query2 = "update fourth_dimension.bom set max_discount_percentage=?,max_discount_amount=? where bom_id=?";
	ps = con.prepareStatement(query2);
	ps.setDouble(1, discountPercentage);
	ps.setDouble(2, discountAmount);
	ps.setInt(3, bomId);

    flag2 = ps.executeUpdate();
    
    } catch (Exception e) {
		status = -4;
		System.out.println("Exception "+e);
	} finally {
		try {
			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			status = -4;
			
		}

	}
	if ((flag1 == 0)) {
		status = -5;
		
	}

	
	return status;
}

}
