package com.fd.Connect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.aseema.fourthdimension.ConnectionPool;
import com.fd.Admin.bean.Client;
import com.fd.utility.Util;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class ProjectServices implements Constants {

	private static final String DUPLICATE_JOB_CARD = "DUPLICATE JOB CARD";
	private static final String FOREIGN_KEY_CONSTRANIT_FAIL = "FOREIGN KEY CONSTRANIT FAIL";
	static String driver = "com.mysql.jdbc.Driver";
	// static String url = "jdbc:mysql://localhost:3307/fourth_dimension";
	static String userName = "root";
	// static String url = "jdbc:mysql://localhost:3307/fourth_dimension";
	static String url = "jdbc:mysql://localhost:3306/fourth_dimension";
	// static String userName = "root";

	static String password = "root";
	static int initialConnections = 1;
	static int maxConnections = 5;
	Connection con = null;
	static ConnectionPool cp;
	Statement st;

	ResultSet rs = null;
	PreparedStatement ps = null;

	String outData = "";
	String inData;

	int status;
	String data = "";
	String query1;
	String query2;
	String query3;
	String query4;
	int flag[];
	int flag1;
	int flag2;
	int flag3;
	int rowCount;
	String response_data;
	// consatnt
	String statusSeperator = "@&@";
	String DATA_INSUFFICIENT = Constants.DATA_INSUFFICIENT;
	String DATA_INSERTED = Constants.DATA_INSERTED;
	String DATA_NOT_INSERTED = Constants.DATA_NOT_INSERTED;

	// Update
	String DATA_UPDATED = Constants.DATA_UPDATED;
	String DATA_NOT_UPDATED = Constants.DATA_NOT_UPDATED;
	// Select
	String NO_DATA = Constants.NO_DATA;
	// Deleted
	String DATA_NOT_DELETED = Constants.DATA_NOT_DELETED;
	String DATA_DELETED = Constants.DATA_DELETED;

	/*
	 * static { try { cp = new ConnectionPool(driver, url, userName, password,
	 * initialConnections, maxConnections, true); } catch (Exception e) {
	 * 
	 * System.out.println(e); }
	 * 
	 * }
	 */
	Logger log=Logger.getLogger(ProjectServices.class);
	public void Connection() {
		try {
			/*
			 * Class.forName(driver);
			 * con=DriverManager.getConnection(url,userName,password);
			 */
			Class.forName(Util.driver);
			con = DriverManager.getConnection(Util.url, Util.userName,
					Util.password);

		} catch (Exception e) {
			
			System.out.println(e);
		}
	}

	public String getScopeDetailforScopeId(String Project_ele_scode_id) {
		System.out.println("ProjectServices.getScopeDetailforScopeId()");
		outData = "";

		String query = " SELECT id , element_id ,country_id , region_id , state_id ,"
				+ " city_id , trade_id ,chain_id, "
				+ " clientApprovalReq , fd_hub_id , responsible_person ,"
				+ " no_of_store , scope_of_work, client_national_target_date , client_national_actual_date ,"
				+ " client_regional_target_date , client_regional_actual_date , fd_national_target_date ,"
				+ " fd_national_actual_date , fd_regional_target_date , fd_regional_actual_date "
				+ " FROM  fourth_dimension.element_scopes_dateplan  where id=? ";

		try {
			Connection();

			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(Project_ele_scode_id));
			rs = ps.executeQuery();
			while (rs.next()) {
				outData = outData
						+ rs.getInt(1)
						+ columnSeperator
						+ rs.getInt(2)
						+ columnSeperator
						+ rs.getInt(3)
						+ columnSeperator
						+ rs.getInt(4)
						+ columnSeperator
						+ rs.getInt(5)// state
						+ columnSeperator
						+ rs.getInt(6)
						+ columnSeperator
						+ rs.getInt(7)
						+ columnSeperator
						+ rs.getInt(8)// chain_id
						+ columnSeperator
						+ rs.getString(9)
						+ columnSeperator
						+ rs.getInt(10)
						+ columnSeperator
						+ rs.getString(11)// resp_person
						+ columnSeperator + rs.getInt(12) + columnSeperator
						+ rs.getInt(13) + columnSeperator + rs.getDate(14)
						+ columnSeperator + rs.getDate(15) + columnSeperator
						+ rs.getDate(16) + columnSeperator + rs.getDate(17)
						+ columnSeperator + rs.getDate(18) + columnSeperator
						+ rs.getDate(19) + columnSeperator + rs.getDate(20)
						+ columnSeperator + rs.getDate(21);
			}
			if (!outData.equals("")) {
				System.out.println("outdata: in heree" + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::getScopeDetailforScopeId::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {

			}
		}
		return NO_DATA;

	}

	
	
	public String getScopeDetailsForElement(String Project_element) {
		System.out.println("ProjectServices.getScopeDetailsForElement()");

		outData = "";

		String query = "SELECT id ,country_name,COALESCE(region_name,'NO REGION') region_name, "
				+ " COALESCE(state_name,'NO STATE') state_name,COALESCE(city_name,'NO CITY') city_name, "
				+ " COALESCE(trade_name,'NO TRADE') trade_name,COALESCE(chain_name,'NO CHAIN') chain_name,"
				+ " no_of_store ,(select scope_name from scope_master where scope_id=esd.scope_of_work) scope_name,"
				+ " clientApprovalReq , COALESCE(hub_name,'NO HUB') fd_hub_id ,responsible_person ,"
				+ " client_national_target_date , client_national_actual_date ,"
				+ " client_regional_target_date ,client_regional_actual_date ,fd_national_target_date ,"
				+ " fd_national_actual_date , fd_regional_target_date , fd_regional_actual_date," 
				+"esd.region_id,esd.state_id,esd.city_id  FROM "
				+ " fourth_dimension. element_scopes_dateplan esd "
				+ " LEFT JOIN fourth_dimension.country_master coun "
				+ " ON esd.country_id= coun.country_id  "
				+ " LEFT JOIN fourth_dimension.region_master region"
				+ " ON esd.region_id= region.region_id "
				+ " LEFT JOIN fourth_dimension.state_master state "
				+ " ON esd.state_id= state.state_id"
				+ " LEFT JOIN fourth_dimension.city_master city "
				+ " ON esd.city_id= city.city_id "
				+ " LEFT JOIN fourth_dimension.trade_master trade "
				+ " ON esd.trade_id= trade.trade_id "
				+ " LEFT JOIN fourth_dimension.chain_master chain"
				+ " ON esd.chain_id= chain.chain_id "
				+ " LEFT JOIN fourth_dimension.fd_hub_master fd_hub "
				+ " ON esd.fd_hub_id= fd_hub.fd_hub_id where  element_id=? order by id desc ";
		try {
			Connection();

			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(Project_element));
			rs = ps.executeQuery();
			while (rs.next()) {
				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rs.getString(3)
						+ columnSeperator + rs.getString(4) + columnSeperator
						+ rs.getString(5)
						+ columnSeperator
						+ rs.getString(6)
						+ columnSeperator
						+ rs.getString(7)
						+ columnSeperator
						+ rs.getInt(8)
						+ columnSeperator
						+ // no_of_store
						rs.getString(9)
						+ columnSeperator
						+ rs.getString(10)
						+ columnSeperator
						+ rs.getString(11)
						+ columnSeperator
						+ // hub_name
						rs.getString(12) + columnSeperator + rs.getDate(13)
						+ columnSeperator + rs.getDate(14) + columnSeperator
						+ rs.getDate(15) + columnSeperator + rs.getDate(16)
						+ columnSeperator + rs.getDate(17) + columnSeperator
						+ rs.getDate(18) + columnSeperator + rs.getDate(19)
						+ columnSeperator + rs.getDate(20) +columnSeperator
						+rs.getInt(21)+columnSeperator+rs.getInt(22)+columnSeperator
						+rs.getInt(23)+ rowSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("outdata: " + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception getScopeDetailsForElement::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
					log.info("Exception "+e);
			}
		}
		return NO_DATA;

	}

	/*
	 * Project
	 * 
	 * List ,Insert ,Delete and Update
	 */

	public String getJobCardByProjectSelectMode(int ProjectId) {
		System.out.println("search_Project_by_clientname()");
		outData = "";
		try {
			Connection();
			String query1 = "select a.Project_store_id, job_card_id,job_card_number,store_name,address,city_name "
					+ "from fourth_dimension.job_cards a,Project_stores s "
					+ "where a.Project_store_id=s.Project_store_id and a.Project_id=?";
			PreparedStatement ps = con.prepareStatement(query1);
			ps.setInt(1, ProjectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ProjectStoreId = rs.getInt(1);

				String query2 = "SELECT em.element_name,cm.component_name,mm.material_name,pe.quantity "
						+ "FROM fourth_dimension.measurement_data md,Project_elements pe,element_master em," +
								"component_master cm,material_master mm "
						+ "where "
						+ "md.Project_element_id=pe.Project_element_id "
						+ "and pe.element_id=em.element_id "
						+ "and pe.component_id=cm.component_id "
						+ "and pe.material_id=mm.material_id "
						+ "and md.Project_store_id=" + ProjectStoreId;

				
				String data = "";
				String fieldColumnSeperator = "!#&!";
				String fieldRowSeperator = "!@!";
				Statement st = con.createStatement();
				ResultSet rs1 = st.executeQuery(query2);
				while (rs1.next()) {
				
					data = data + rs1.getString(1) + fieldColumnSeperator
							+ rs1.getString(2) + fieldColumnSeperator
							+ rs1.getString(3) + fieldColumnSeperator
							+ rs1.getInt(4) + fieldColumnSeperator
							+ fieldRowSeperator;

				}

				outData = outData + data + columnSeperator + rs.getInt(2)
						+ columnSeperator + rs.getString(3) + columnSeperator
						+ rs.getString(4) + columnSeperator + rs.getString(5)
						+ columnSeperator + rs.getString(6) + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			log.info("Exception::getJobCardByProjectSelectMode:: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getJobCardByProjectBulkEntryMode(String inData) {

		try {
			int ProjectId = Integer.parseInt(inData);
			Connection();
			String query1 = "select job_card_id,job_card_number  from fourth_dimension.job_cards where Project_id=?";

			PreparedStatement ps = con.prepareStatement(query1);
			ps.setInt(1, ProjectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception:: getJobCardByProjectBulkEntryMode::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getStoreByProjectBulkEntryMode(String inData) {

		try {
			int ProjectId = Integer.parseInt(inData);
			Connection();
			String query1 = "select Project_store_id,store_name  from fourth_dimension.Project_stores where  Project_id=?"
					+ " and Project_store_id in(select Project_store_id from fourth_dimension.job_cards where Project_id=?) order by store_name";

			PreparedStatement ps = con.prepareStatement(query1);
			ps.setInt(1, ProjectId);
			ps.setInt(2, ProjectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception ::getStoreByProjectBulkEntryMode::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getJobCardByClient(String inData) {

		try {
			int clientId = Integer.parseInt(inData);
			Connection();
			String query1 = "select job_card_id,job_card_number  from fourth_dimension.job_cards where Project_id in("
					+ "select Project_id from "
					+ "fourth_dimension.Project where client_id=?)";

			PreparedStatement ps = con.prepareStatement(query1);
			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::getJobCardByClient "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getJobCardByJobCardNumber(String jobCardId) {
		System.out.println("job card Id is " + jobCardId);
		outData = "";
		try {
			Connection();
			String query1 = "select a.Project_store_id,job_card_id,job_card_number,store_name,address,city_name,Project_name "
					+ "from fourth_dimension.job_cards a,Project_stores s ,Project p "
					+ "where a.Project_store_id=s.Project_store_id and a.Project_id=p.Project_id and "
					+ "a.job_card_id=?";
			PreparedStatement ps = con.prepareStatement(query1);
			ps.setInt(1, Integer.parseInt(jobCardId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ProjectStoreId = rs.getInt(1);

				String query2 = "SELECT em.element_name,cm.component_name,mm.material_name,pe.quantity "
						+ "FROM fourth_dimension.measurement_data md,Project_elements pe,element_master em,component_master cm," +
								"material_master mm "
						+ "where "
						+ "md.Project_element_id=pe.Project_element_id "
						+ "and pe.element_id=em.element_id "
						+ "and pe.component_id=cm.component_id "
						+ "and pe.material_id=mm.material_id "
						+ "and md.Project_store_id=" + ProjectStoreId;

				String data = "";
				String fieldColumnSeperator = "!#&!";
				String fieldRowSeperator = "!@!";
				Statement st = con.createStatement();
				ResultSet rs1 = st.executeQuery(query2);
				while (rs1.next()) {
				
					data = data + rs1.getString(1) + fieldColumnSeperator
							+ rs1.getString(2) + fieldColumnSeperator
							+ rs1.getString(3) + fieldColumnSeperator
							+ rs1.getInt(4) + fieldColumnSeperator
							+ fieldRowSeperator;

				}

				outData = outData + data + columnSeperator + rs.getInt(1)
						+ columnSeperator + rs.getInt(2) + columnSeperator
						+ rs.getString(3) + columnSeperator + rs.getString(4)
						+ columnSeperator + rs.getString(5) + columnSeperator
						+ rs.getString(6) + columnSeperator + rs.getString(7)
						+ rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::getJobCardByJobCardNumber "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getJobCardByStore(String ProjectStoreId) {
		System.out.println("job card Id is " + ProjectStoreId);
		outData = "";
		try {
			Connection();
			String query1 = "select job_card_id,job_card_number,store_name,address,city_name,Project_name "
					+ "from fourth_dimension.job_cards a,Project_stores s ,Project p "
					+ "where a.Project_store_id=s.Project_store_id and a.Project_id=p.Project_id and "
					+ "a.Project_store_id=?";
			PreparedStatement ps = con.prepareStatement(query1);
			ps.setInt(1, Integer.parseInt(ProjectStoreId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String query2 = "SELECT em.element_name,cm.component_name,mm.material_name,pe.quantity "
						+ "FROM fourth_dimension.measurement_data md,Project_elements pe,element_master em," +
								"component_master cm,material_master mm "
						+ "where "
						+ "md.Project_element_id=pe.Project_element_id "
						+ "and pe.element_id=em.element_id "
						+ "and pe.component_id=cm.component_id "
						+ "and pe.material_id=mm.material_id "
						+ "and md.Project_store_id=" + ProjectStoreId;

				String data = "";
				String fieldColumnSeperator = "!#&!";
				String fieldRowSeperator = "!@!";
				Statement st = con.createStatement();
				ResultSet rs1 = st.executeQuery(query2);
				while (rs1.next()) {
				
					data = data + rs1.getString(1) + fieldColumnSeperator
							+ rs1.getString(2) + fieldColumnSeperator
							+ rs1.getString(3) + fieldColumnSeperator
							+ rs1.getInt(4) + fieldColumnSeperator
							+ fieldRowSeperator;

				}

				outData = outData + data + columnSeperator + rs.getInt(1)
						+ columnSeperator + rs.getString(2) + columnSeperator
						+ rs.getString(3) + columnSeperator + rs.getString(4)
						+ columnSeperator + rs.getString(5) + columnSeperator
						+ rs.getString(6) + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::getJobCardByStore "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getElementByStore(int ProjectStoreId) {

		System.out.println("search_Project_by_clientname()");
		outData = "";
		try {
			Connection();
			String query1 = "select md.Project_element_id,em.element_name  "
					+ "from fourth_dimension.measurement_data md,Project_elements pe,element_master em "
					+ "where " + "md.Project_element_id=pe.Project_element_id "
					+ "and pe.element_id=em.element_id "
					+ "and md.Project_store_id=" + ProjectStoreId+" order by element_name";

			PreparedStatement ps = con.prepareStatement(query1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ProjectElementId = rs.getInt(1);
				String ProjectElementName = rs.getString(2);

				String query2 = "SELECT cm.component_name,mm.material_name,pe.quantity "
						+ "FROM fourth_dimension.Project_elements pe,material_master mm,component_master cm "
						+ "where "
						+ "pe.component_id=cm.component_id "
						+ "and pe.material_id=mm.material_id "
						+ "and pe.Project_element_id=" + ProjectElementId;

				String data = "";
				String fieldColumnSeperator = "!#&!";
				String fieldRowSeperator = "!@!";
				Statement st = con.createStatement();
				ResultSet rs1 = st.executeQuery(query2);
				while (rs1.next()) {
					data = data + rs1.getString(1) + fieldColumnSeperator
							+ rs1.getString(2) + fieldColumnSeperator
							+ rs1.getInt(3) + fieldColumnSeperator
							+ fieldRowSeperator;

				}

				outData = outData + data + columnSeperator + rs.getInt(1)
						+ columnSeperator + rs.getString(2) + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::getElementByStore "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getJCStatus() {
		System.out.println("search_Project_by_clientname()");
		outData = "";
		try {
			Connection();
			String query1 = "SELECT * FROM fourth_dimension.jobcard_status_master order by jobcard_status_name";
			PreparedStatement ps = con.prepareStatement(query1);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: getJCStatus::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getElementStatus() {
		System.out.println("search_Project_by_clientname()");
		outData = "";
		try {
			Connection();
			String query1 = "select * from fourth_dimension.element_status_master order by element_status_name";
			PreparedStatement ps = con.prepareStatement(query1);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: getElementStatus"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getAssemblyUnit() {
		System.out.println("search_Project_by_clientname()");
		outData = "";
		try {
			Connection();
			String query1 = "select *  from fourth_dimension.assembly_unit_master order by assembly_unit_name";
			PreparedStatement ps = con.prepareStatement(query1);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: getAssemblyUnit::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String search_Project_by_divisionname(String division_name) {
		System.out.println("search_Project_by_divisionname()");

		try {
			Connection();

			PreparedStatement ps = con
					.prepareStatement(search_Projectby_divisionname);
			ps.setString(1, division_name);
			ResultSet res_search_project = ps.executeQuery();
			while (res_search_project.next()) {

				outData = outData + res_search_project.getString(1)
						+ columnSeperator + res_search_project.getString(2)
						+ columnSeperator + res_search_project.getString(3)
						+ columnSeperator + res_search_project.getString(4)
						+ columnSeperator + res_search_project.getInt(5)
						+ rowSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("OUTdata in division serach ::  " + outData);
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: search_Project_by_divisionname"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getClient() {

		try {

			Connection();
			System.out.println("working");
			String query1 = "SELECT client_id, client_name FROM fourth_dimension.client_master order by client_name";
			PreparedStatement ps = con.prepareStatement(query1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				if (rs.getString(2) == null) {

					return NO_DATA;
				}

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;

			}

			if (!outData.equals("")) {
				return outData;
			}
			System.out.println("out data is6 " + outData);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: getClient::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getProject(String inData) {

		try {

			int clientId = Integer.parseInt(inData.trim());

			Connection();

			String query1 = "SELECT Project_id, Project_name FROM fourth_dimension.Project where client_id="
					+ clientId+" order by Project_name" ;
			PreparedStatement ps = con.prepareStatement(query1);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getString(2) == null) {
					System.out.println("working4");
					return NO_DATA;
				}

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;

			}

			if (!outData.equals("")) {

				return outData;
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception :: getProject :: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getDivision(String inData) {

		try {

			int ProjectId = Integer.parseInt(inData.trim());

			Connection();

			String query1 = "SELECT division_id, division_name FROM fourth_dimension.Project p,fd_division_master d where "
					+ "fd_division_id=division_id and Project_id=" + ProjectId+" order by division_name";
			PreparedStatement ps = con.prepareStatement(query1);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getString(2) == null) {
					System.out.println("working4");
					return NO_DATA;
				}

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;

			}

			if (!outData.equals("")) {

				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: getDivision"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String setElementStatus(String inData) {

		// System.out.println("ProjectServices.insert_project_store()");
		outData = "";

		String[] split_data = inData.split(columnSeperator);
		String query1 = "insert into fourth_dimension.element_status values(?,?,?,?,?)";
		try {
			if (split_data.length == 4) {
				String elementId[] = split_data[0].split(",");
				String statusId[] = split_data[1].split(",");
				String date[] = split_data[2].split(",");
				String comments[] = split_data[3].split(",");

				Connection();
				for (int i = 0; i < elementId.length; i++) {
					ps = con.prepareStatement(query1);
					ps.setInt(1, 0);
					ps.setInt(2, Integer.parseInt(elementId[i]));
					ps.setInt(3, Integer.parseInt((statusId[i]).trim()));

					SimpleDateFormat sdf = new java.text.SimpleDateFormat(
							"dd/MM/yy");
					SimpleDateFormat fmt = new java.text.SimpleDateFormat(
							"yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR, -100);
					sdf.set2DigitYearStart(cal.getTime());

					ps.setString(4, fmt.format(sdf.parse(date[i])));
					ps.setString(5, comments[i]);

					status = ps.executeUpdate();
				}

			}
			if (status > 0) {
				return DATA_INSERTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Excption::setElementStatus::"+e);
			return DATA_NOT_INSERTED;

		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {

			}
		}
		return DATA_NOT_INSERTED;
	}
	
	

	public String setJcStatus(String inData) {

		// System.out.println("ProjectServices.insert_project_store()");
		outData = "";

		String[] split_data = inData.split(columnSeperator);
		String query1 = "insert into fourth_dimension.jobcard_status values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			if (split_data.length == 10) {
				String jcId[] = split_data[0].split(",");

				Connection();
				for (int i = 0; i < jcId.length; i++) {
					ps = con.prepareStatement(query1);
					ps.setInt(1, 0);
					ps.setInt(2, Integer.parseInt((jcId[i]).trim()));
					ps.setInt(3, Integer.parseInt(split_data[1]));

					SimpleDateFormat sdf = new java.text.SimpleDateFormat(
							"dd/MM/yy");
					SimpleDateFormat fmt = new java.text.SimpleDateFormat(
							"yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR, -100);
					sdf.set2DigitYearStart(cal.getTime());

					ps.setString(4, fmt.format(sdf.parse(split_data[2])));
					ps.setString(5, split_data[3]);
					if ((split_data[4] == null)
							|| (split_data[4].equals("null"))) {

						ps.setNull(6, java.sql.Types.BIGINT);
					} else {

						ps.setInt(6, Integer.parseInt(split_data[4]));
					}

					if ((split_data[5] == null)
							|| (split_data[5].equals("null"))) {

						ps.setNull(7, java.sql.Types.BIGINT);
					} else {
						ps.setString(7, fmt.format(sdf.parse(split_data[5])));
					}

					if ((split_data[6] == null)
							|| (split_data[6].equals("null"))) {

						ps.setNull(8, java.sql.Types.BIGINT);
					}

					else {
						ps.setString(8, fmt.format(sdf.parse(split_data[6])));
					}

					ps.setString(9, split_data[7]);

					if ((split_data[8] == null)
							|| (split_data[8].equals("null"))) {

						ps.setNull(10, java.sql.Types.BIGINT);
					}

					else {
						ps.setString(10, fmt.format(sdf.parse(split_data[8])));
					}

					if ((split_data[9] == null)
							|| (split_data[9].equals("null"))) {

						ps.setNull(11, java.sql.Types.BIGINT);
					} else {

						ps.setInt(11, Integer.parseInt(split_data[9]));
					}

					status = ps.executeUpdate();
				}

			}
			if (status > 0) {
				return DATA_INSERTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_INSERTED;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
		return DATA_NOT_INSERTED;
	}

	/*public String getProjectStores(String Project) {
		System.out.println("ProjectServices.getProjectStores()");

		outData = "";

		try {
			Connection();

			ps = con.prepareStatement(getProjectStore_query);
			ps.setInt(1, Integer.parseInt(Project));
			rs = ps.executeQuery();
			while (rs.next()) {
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rs.getString(3)
						+ columnSeperator + rs.getString(4) + columnSeperator
						+ rs.getString(5) + columnSeperator + rs.getString(6)
						+ columnSeperator + rs.getString(7) + columnSeperator
						+ rs.getString(8) + columnSeperator + rs.getString(9)
						+ columnSeperator + rs.getString(10) + columnSeperator
						+ rs.getString(12) + columnSeperator + rs.getString(11)
						+ columnSeperator + rs.getString(13) + columnSeperator
						+ rs.getString(14) + columnSeperator + rs.getString(15)
						+ columnSeperator + rs.getString(16) + columnSeperator
						+ rs.getString(17) + columnSeperator + rs.getInt(18)
						+ columnSeperator + rs.getInt(19) + columnSeperator
						+ rs.getInt(20) + columnSeperator + rs.getInt(21)
						+ columnSeperator + rs.getInt(22) + columnSeperator
						+ rs.getInt(23) +columnSeperator+rs.getString(24)
						+columnSeperator+rs.getString(25)+ rowSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("outdata: " + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
		return NO_DATA;

	}*/
	
	public String getProjectStores(String Project) {
		System.out.println("ProjectServices.getProjectStores() project val-------"+Project);
		
		outData = "";

		try {
			
			Connection();
			PreparedStatement pstForProjectStores = con.prepareStatement("SELECT * FROM fourth_dimension.Project_stores WHERE Project_id =?");
			pstForProjectStores.setInt(1, Integer.parseInt(Project));
			ResultSet rsForProjectStores = pstForProjectStores.executeQuery();
			while(rsForProjectStores.next()){
				
				PreparedStatement pstJob = con.prepareStatement("SELECT * FROM fourth_dimension.job_cards where Project_store_id =?");
				pstJob.setInt(1, rsForProjectStores.getInt(1));
				ResultSet rsJob = pstJob.executeQuery();
				if(rsJob.next()){
					
					System.out.println("its have jobcard:::::");
					ps = con.prepareStatement(getProjectStore_query);
					ps.setInt(1, Integer.parseInt(rsJob.getString("Project_store_id")));
					
					rs = ps.executeQuery();
					while (rs.next()) {
						outData = outData + rs.getString(1) + columnSeperator
								+ rs.getString(2) + columnSeperator + rs.getString(3)
								+ columnSeperator + rs.getString(4) + columnSeperator
								+ rs.getString(5) + columnSeperator + rs.getString(6)
								+ columnSeperator + rs.getString(7) + columnSeperator
								+ rs.getString(8) + columnSeperator + rs.getString(9)
								+ columnSeperator + rs.getString(10) + columnSeperator
								+ rs.getString(12) + columnSeperator + rs.getString(11)
								+ columnSeperator + rs.getString(13) + columnSeperator
								+ rs.getString(14) + columnSeperator + rs.getString(15)
								+ columnSeperator + rs.getString(16) + columnSeperator
								+ rs.getString(17) +columnSeperator+rs.getString(18)
								+columnSeperator+rs.getString(19)+columnSeperator
								+rs.getString(20)+columnSeperator+rs.getInt(21)+columnSeperator
								+rs.getInt(22)+ rowSeperator;
					}
					/*if (!outData.equals("")) {
						System.out.println("outdata: " + outData);
						return outData;
					}*/
				}else{
					System.out.println("its haven't job card::::");
					ps = con.prepareStatement(getProjectStore_no_job_card);
					ps.setInt(1,rsForProjectStores.getInt(1));
					rs = ps.executeQuery();
					while (rs.next()) {
						outData = outData + rs.getString(1) + columnSeperator
								+ rs.getString(2) + columnSeperator + rs.getString(3)
								+ columnSeperator + rs.getString(4) + columnSeperator
								+ rs.getString(5) + columnSeperator + rs.getString(6)
								+ columnSeperator + rs.getString(7) + columnSeperator
								+ rs.getString(8) + columnSeperator + rs.getString(9)
								+ columnSeperator + rs.getString(10) + columnSeperator
								+ rs.getString(12) + columnSeperator + rs.getString(11)
								+ columnSeperator + rs.getString(13) + columnSeperator
								+ rs.getString(14) + columnSeperator + rs.getString(15)
								+ columnSeperator + rs.getString(16) + columnSeperator
								+ rs.getString(17)  +columnSeperator+"NA"
								+columnSeperator+"NA"+ columnSeperator+rs.getString(18)
								+columnSeperator+rs.getInt(19)+columnSeperator+rs.getInt(20)
								+rowSeperator;
					}
					
				}
			}
			if (!outData.equals("")) {
				System.out.println("outdata: " + outData);
				return outData;
			}
			
			/*PreparedStatement pstJob = con.prepareStatement("SELECT * FROM fourth_dimension.job_cards where Project_store_id =?");
			pstJob.setInt(1, Integer.parseInt(Project));
			ResultSet rsJob = pstJob.executeQuery();
			if(rsJob.next()){
				ps = con.prepareStatement(getProjectStore_query);
				ps.setInt(1, Integer.parseInt(Project));
				rs = ps.executeQuery();
				while (rs.next()) {
					outData = outData + rs.getString(1) + columnSeperator
							+ rs.getString(2) + columnSeperator + rs.getString(3)
							+ columnSeperator + rs.getString(4) + columnSeperator
							+ rs.getString(5) + columnSeperator + rs.getString(6)
							+ columnSeperator + rs.getString(7) + columnSeperator
							+ rs.getString(8) + columnSeperator + rs.getString(9)
							+ columnSeperator + rs.getString(10) + columnSeperator
							+ rs.getString(12) + columnSeperator + rs.getString(11)
							+ columnSeperator + rs.getString(13) + columnSeperator
							+ rs.getString(14) + columnSeperator + rs.getString(15)
							+ columnSeperator + rs.getString(16) + columnSeperator
							+ rs.getString(17) + columnSeperator + rs.getInt(18)
							+ columnSeperator + rs.getInt(19) + columnSeperator
							+ rs.getInt(20) + columnSeperator + rs.getInt(21)
							+ columnSeperator + rs.getInt(22) + columnSeperator
							+ rs.getInt(23) +columnSeperator+rs.getString(24)
							+columnSeperator+rs.getString(25)+ rowSeperator;
				}
				if (!outData.equals("")) {
					System.out.println("outdata: " + outData);
					return outData;
				}
			}else{
				ps = con.prepareStatement(getProjectStore_no_job_card);
				ps.setInt(1, Integer.parseInt(Project));
				rs = ps.executeQuery();
				while (rs.next()) {
					outData = outData + rs.getString(1) + columnSeperator
							+ rs.getString(2) + columnSeperator + rs.getString(3)
							+ columnSeperator + rs.getString(4) + columnSeperator
							+ rs.getString(5) + columnSeperator + rs.getString(6)
							+ columnSeperator + rs.getString(7) + columnSeperator
							+ rs.getString(8) + columnSeperator + rs.getString(9)
							+ columnSeperator + rs.getString(10) + columnSeperator
							+ rs.getString(12) + columnSeperator + rs.getString(11)
							+ columnSeperator + rs.getString(13) + columnSeperator
							+ rs.getString(14) + columnSeperator + rs.getString(15)
							+ columnSeperator + rs.getString(16) + columnSeperator
							+ rs.getString(17) + columnSeperator + rs.getInt(18)
							+ columnSeperator + rs.getInt(19) + columnSeperator
							+ rs.getInt(20) + columnSeperator + rs.getInt(21)
							+ columnSeperator + rs.getInt(22) + columnSeperator
							+ rs.getInt(23) +columnSeperator+"NA"
							+columnSeperator+"NA"+ rowSeperator;
				}
				if (!outData.equals("")) {
					System.out.println("outdata: " + outData);
					return outData;
				}
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
		return NO_DATA;

	}

	public String list_Project_store(String Project_list, String areaSelect) {
		outData = "";
		System.out.println("ProjectServices.list_project_store()");
		try {
			Connection();

			ps = con.prepareStatement(list_Project_store);
			ps.setInt(1, Integer.parseInt(Project_list));
			ps.setString(2, areaSelect);
			rs = ps.executeQuery();
			while (rs.next()) {
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rs.getString(3)
						+ columnSeperator + rs.getString(4) + columnSeperator
						+ rs.getString(5) + columnSeperator + rs.getString(6)
						+ columnSeperator + rs.getString(7) + columnSeperator
						+ rs.getString(8) + columnSeperator + rs.getString(9)
						+ rowSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("outdata: " + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
		return NO_DATA;
	}

	public String delete_Project_store(String indata) {

		System.out.println("ProjectServices.delete_project_store()");
		String id_seperated = "";
		String[] split_data = indata.split(",");

		System.out.println("split_data.length---> " + split_data.length);

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("id ---->  " + split_data[i]);

			if (i == split_data.length - 1) {
				id_seperated = id_seperated + split_data[i];
			} else {
				id_seperated = id_seperated + split_data[i] + ",";
			}

		}
		System.out.println("id_seperated---->  " + id_seperated);

		try {
			Connection();

			PreparedStatement ps = con
					.prepareStatement("DELETE FROM fourth_dimension.Project_stores "
							+ "WHERE Project_store_id in(" + id_seperated + ")");

			// System.out.println("Query :::::"+"DELETE FROM fourth_dimension.project_store_master "
			// +
			// "WHERE project_store_id in("+id_seperated+")");
			// ps.setString(1,id_seperated);
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_DELETED;
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return FOREIGN_KEY_CONSTRANIT_FAIL;
		}

		catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_DELETED;
		}

		return DATA_NOT_DELETED;
	}

	public String delete_scopes_for_element(String indata) {

		System.out.println("ProjectServices.delete_scopes_for_element()");
		String id_seperated = "";
		String[] split_data = indata.split(",");

		System.out.println("split_data.length---> " + split_data.length);

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("id ---->  " + split_data[i]);

			if (i == split_data.length - 1) {
				id_seperated = id_seperated + split_data[i];
			} else {
				id_seperated = id_seperated + split_data[i] + ",";
			}

		}
		System.out.println("id_seperated---->  " + id_seperated);

		try {
			Connection();

			PreparedStatement ps = con
					.prepareStatement("DELETE FROM fourth_dimension.element_scopes_dateplan "
							+ " WHERE id in (" + id_seperated + ")");

			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_DELETED;
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return FOREIGN_KEY_CONSTRANIT_FAIL;
		}

		catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_DELETED;
		}

		return DATA_NOT_DELETED;
	}

	/*
	 * public String insert_project_store(String inData,String project_id){
	 * 
	 * System.out.println("ProjectServices.insert_project_store()"); outData="";
	 * String[] split_data=inData.split("!@!");
	 * 
	 * for(int i=0;i<split_data.length;i++){
	 * 
	 * System.out.println("Split data "+i+" --> "+split_data[i]); if
	 * ((split_data[i] == null) || split_data[i].equals("")) { return
	 * DATA_INSUFFICIENT; }
	 * 
	 * } try{ Connection();
	 * 
	 * PreparedStatement ps=con.prepareStatement(insert_project_store);
	 * ps.setInt(1,Integer.parseInt(project_id)); ps.setString(2,split_data[0]);
	 * ps.setString(3,split_data[1]); ps.setString(4,split_data[2]);
	 * ps.setString(5,split_data[3]); ps.setString(6,split_data[4]);
	 * ps.setString(7,split_data[5]); ps.setString(8,split_data[6]);
	 * ps.setString(9,split_data[7]); status=ps.executeUpdate(); if(status>0){
	 * return DATA_INSERTED; } }catch (Exception e) { e.printStackTrace();
	 * return DATA_NOT_INSERTED; }
	 * 
	 * return DATA_NOT_INSERTED; }
	 */

	public int insert_Project_store(String inData, String Project_id) {

		System.out.println("ProjectServices.insert_Project_store()" + inData);
		outData = "";

		String[] split_data = inData.split("@!@");

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("Split data " + i + " --> " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return -1;
			}

		}
		int storeId = 0;
		try {
			Connection();
            PreparedStatement ps1=con.prepareStatement(query_for_already_exist_store);
		
			ps1.setInt(1, Integer.parseInt(Project_id));
			ps1.setString(2, split_data[0]);
			ps1.setString(3, split_data[1]);
			ps1.setString(4, split_data[9]);
			ps1.setString(5, split_data[10]);
			
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next()){
				storeId=rs1.getInt(1);
			}
			else{
				
			
			
			ps = con.prepareStatement(insert_Project_store);
			ps.setInt(1, Integer.parseInt(Project_id));
			ps.setString(2, split_data[0]);
			ps.setString(3, split_data[1]);
			ps.setString(4, split_data[2]);
			ps.setString(5, split_data[3]);
			ps.setString(6, split_data[4]);
			ps.setString(7, split_data[5]);
			ps.setString(8, split_data[6]);
			ps.setString(9, split_data[7]);
			ps.setString(10, split_data[8]);
			//ps.setString(11, split_data[9]);
			//ps.setString(12, split_data[10]);
			ps.setString(11, split_data[9]);
			ps.setString(12, split_data[10]);
			ps.setString(13, split_data[11]);
			ps.setString(14, split_data[12]);
			ps.setString(15, split_data[13]);
			status = ps.executeUpdate();
			if(status==1){
			    PreparedStatement ps2=con.prepareStatement(query_for_getting_latest_inserted_store);
				
				ps2.setInt(1, Integer.parseInt(Project_id));
				ps2.setString(2, split_data[0]);
				ps2.setString(3, split_data[1]);
				ps2.setString(4, split_data[9]);
				ps2.setString(5, split_data[10]);
				
				ResultSet rs2=ps2.executeQuery();
				if(rs2.next()){
					storeId=rs2.getInt(1);
				}
			}
			}
			
			return storeId;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return -1;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
		
	}

	/*
	 * public String update_project_store(String inData,String
	 * project_store_id){
	 * 
	 * System.out.println("ProjectServices.update_project_store"); outData="";
	 * String[] split_data=inData.split("!@!");
	 * 
	 * for(int i=0;i<split_data.length;i++){
	 * 
	 * System.out.println("Split data "+i+" --> "+split_data[i]); if
	 * ((split_data[i] == null) || split_data[i].equals("")) { return
	 * DATA_INSUFFICIENT; } } try{ Connection();
	 * 
	 * PreparedStatement ps=con.prepareStatement(update_project_store);
	 * 
	 * ps.setString(1,split_data[0]); ps.setString(2,split_data[1]);
	 * ps.setString(3,split_data[2]); ps.setString(4,split_data[3]);
	 * ps.setString(5,split_data[4]); ps.setString(6,split_data[5]);
	 * ps.setString(7,split_data[6]); ps.setString(8,split_data[7]);
	 * ps.setString(9,split_data[8]); ps.setInt(10,1);
	 * status=ps.executeUpdate(); if(status>0){ return DATA_UPDATED; } }catch
	 * (Exception e) { e.printStackTrace(); return DATA_NOT_UPDATED; }
	 * 
	 * return DATA_NOT_UPDATED; }
	 */

	public String update_Project_store(String inData, String Project_store_id) {

		System.out.println("ProjectServices.update_project_store");
		outData = "";
		String[] split_data = inData.split("!@!");

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("Split data " + i + " --> " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return DATA_INSUFFICIENT;
			}
		}
		try {
			Connection();

			PreparedStatement ps = con.prepareStatement(update_Project_store);

			ps.setString(1, split_data[0]);
			ps.setString(2, split_data[1]);
			ps.setString(3, split_data[2]);
			ps.setString(4, split_data[3]);
			ps.setString(5, split_data[4]);
			ps.setString(6, split_data[5]);
			ps.setString(7, split_data[6]);
			ps.setString(8, split_data[7]);
			ps.setString(9, split_data[8]);

			//ps.setString(10, split_data[9]);
			//ps.setString(11, split_data[10]);
			ps.setString(10, split_data[9]);
			ps.setString(11, split_data[10]);
			ps.setString(12, split_data[11]);
			ps.setString(13, split_data[12]);
			ps.setString(14, split_data[13]);
			ps.setInt(15, Integer.parseInt(Project_store_id));
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_UPDATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_UPDATED;
		}

		return DATA_NOT_UPDATED;
	}

	public String updateStoreMeasuremntDetails(String inData) {
		System.out.println("ProjectServices.update_store_measuremnt_details()");
		outData = "";
		String[] split_data = inData.split("@!@");

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("Split data " + i + " --> " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return DATA_INSUFFICIENT;
			}
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date dateTime = formatter.parse(split_data[2] + " 00:00:00");

			Timestamp timeStampDate = new Timestamp(dateTime.getTime());
			System.out.println("timeStampDate" + timeStampDate);
			Connection();

			PreparedStatement ps = con
					.prepareStatement(update_store_measuremnt_details);
			ps.setString(1, split_data[0]);
			ps.setString(2, split_data[1]);
			ps.setTimestamp(3, timeStampDate);
			ps.setString(4, split_data[3]);
			ps.setInt(5, Integer.parseInt(split_data[4]));
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_UPDATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_UPDATED;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.info("Exception::"+e);
				e.printStackTrace();
			}
		}

		return DATA_NOT_UPDATED;
	}

	public String updateMeasuremntTrailforStore(String inData) {
		System.out.println("ProjectServices.updateMeasuremntTrailforStore()");
		outData = "";

		String[] split_data = inData.split("@!@");

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("Split data " + i + " --> " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return DATA_INSUFFICIENT;
			}
		}
		try {
			//NEW CODE ADDED
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy");
		/*	SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date dateTime = formatter.parse(split_data[2] + " 00:00:00");
			Date dateTime_curr = new Date();
			dateTime_curr = formatter.parse(formatter.format(dateTime_curr));

			Timestamp timeStampDate_curr = new Timestamp(
					dateTime_curr.getTime());
			Timestamp timeStampDate = new Timestamp(dateTime.getTime());*/
			
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.YEAR, -100);
			sdf.set2DigitYearStart(cal.getTime());
			
			String timeStampDate=fmt.format(sdf.parse(split_data[2]));
			
			Date dateTime_curr = new Date();
			dateTime_curr = fmt.parse(fmt.format(dateTime_curr));
			Timestamp timeStampDate_curr = new Timestamp(
			dateTime_curr.getTime());
			
			System.out.println("timeStampDate::"+timeStampDate);
			System.out.println("timeStampDate_curr::"+timeStampDate_curr);
			Connection();
			/*
			 * INSERT INTO fourth_dimension.measurement_status " +
			 * " ( comments,measurement_status,status_date,measured_by,Project_store_id , date ) "
			 * + " VALUES (?,?,?,?,?,?)
			 * 
			 * var
			 * data=comments_u+"@!@"+measurement_status_u+"@!@"+measured_on+"@!@"
			 * +measured_by+"@!@"+store_code;
			 */
			
			/*st=con.createStatement();
			rs=st.executeQuery("select * from measurement_status where Project_store_id="+Integer.parseInt(split_data[4]));
			
			if(rs.next()){

				PreparedStatement ps = con
						.prepareStatement(updateMeasuremntTrailforStore_query);
				ps.setString(1, split_data[0]);
				ps.setInt(2, Integer.parseInt(split_data[1]));
				// ps.setTimestamp(3, timeStampDate);
				ps.setString(3, timeStampDate);
				ps.setString(4, split_data[3]);
				ps.setTimestamp(5, timeStampDate_curr);
				ps.setInt(6, Integer.parseInt(split_data[4]));
				status = ps.executeUpdate();
			}
			else{*/
			PreparedStatement ps = con
					.prepareStatement(insertMeasuremntTrailforStore_query);
			ps.setString(1, split_data[0]);
			ps.setInt(2, Integer.parseInt(split_data[1]));
			//ps.setTimestamp(3, timeStampDate);
			ps.setString(3, timeStampDate);
			ps.setString(4, split_data[3]);
			ps.setInt(5, Integer.parseInt(split_data[4]));
			ps.setTimestamp(6, timeStampDate_curr);
			status = ps.executeUpdate();
		//	}
			if (status > 0) {
				return DATA_INSERTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::updateMeasuremntTrailforStore::"+e);
			return DATA_NOT_INSERTED;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.info("Exception::updateMeasuremntTrailforStore::"+e);
				e.printStackTrace();
			}
		}

		return DATA_NOT_INSERTED;
	}

	public String getStoresDetails(String store) {

		System.out.println("ProjectServices.getStoresDetails()");
		//String status_date = "1970-01-01 00:00:00";
		String status_date = "";
		String measured_by = "empty";
		String measurement_status = "empty";
		String comments = "empty";
		String conatct_phone="";
		try {
			Connection();

			PreparedStatement ps = con.prepareStatement(getStoresDetails);

			ps.setString(1, store);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				/*
				 * SELECT measurement_status_id ,status_date , measured_by ,
				 * measurement_status ,comments FROM fourth_dimension .
				 * measurement_status WHERE Project_store_id =?"
				 */

				ps = con.prepareStatement(getLastMeasurementStaus);
				ps.setString(1, store);
				ResultSet rs_measurementStatus = ps.executeQuery();
				while (rs_measurementStatus.next()) {

					status_date = rs_measurementStatus.getString("status_date");
					measured_by = rs_measurementStatus.getString("measured_by");
					measurement_status = rs_measurementStatus
							.getString("measurement_status_name");
					comments = rs_measurementStatus.getString("comments");
					break;
				}
				System.out.println(rs.getString("store_name") + columnSeperator
						+ rs.getString("address") + columnSeperator
						+ rs.getString("contact_name") + columnSeperator
						+ rs.getString("contact_phone") + columnSeperator
						+ rs.getString("store_flag") + columnSeperator
						+ rs.getString("fdhub_name") + columnSeperator
						+ rs.getString("handle_by") + columnSeperator
						+ rs.getString("Project_store_id") + columnSeperator
						+ status_date + columnSeperator + measured_by
						+ columnSeperator + measurement_status
						+ columnSeperator + comments);
				conatct_phone = rs.getString("contact_phone");
				if(conatct_phone == null){
					conatct_phone = "";
				}
				return rs.getString("store_name") + columnSeperator
						+ rs.getString("address") + columnSeperator
						+ rs.getString("contact_name") + columnSeperator
						+ conatct_phone + columnSeperator
						+ rs.getString("store_flag") + columnSeperator
						+ rs.getString("fdhub_name") + columnSeperator
						+ rs.getString("handle_by") + columnSeperator
						+ rs.getString("Project_store_id") + columnSeperator
					    + status_date + columnSeperator + measured_by
						+ columnSeperator + measurement_status
						+ columnSeperator + comments
						+ columnSeperator
						+ rs.getString("store_code") + columnSeperator
						+ rs.getString("tsi_name") + columnSeperator
						+ rs.getString("tsi_phone") ;

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.info("Exception::"+e);
				e.printStackTrace();
			}
		}

		return NO_DATA;
	}
	
	public int updateStoreDetail(String inData) {


		try {
			System.out.println("it is service");

			String columnData[] = inData.split(Constants.columnSeperator);
			if (columnData.length != 8) {
				System.out.println("calling if block");
				return -1;
			}
			Connection();
			System.out.println("it is service1");
			PreparedStatement ps = con
					.prepareStatement("update Project_stores set address=?,contact_name=?,contact_phone=?,"
							+ "fdhub_name=?,store_code=?,tsi_name=?,tsi_phone=? where Project_store_id=?");

			ps.setString(1, columnData[1]);
			ps.setString(2, columnData[2]);
			ps.setString(3, columnData[3]);
			ps.setString(4, columnData[4]);
			ps.setString(5, columnData[5]);
			ps.setString(6, columnData[6]);
			ps.setString(7, columnData[7]);
			ps.setInt(8, Integer.parseInt(columnData[0]));

			int flag1 = ps.executeUpdate();

			/* ps = con
					.prepareStatement("update measurement_status set status_date=?,measured_by=?,measurement_status=?,"
							+ "comments=? where Project_store_id=?");

			ps.setString(1, columnData[5]);
			ps.setString(2, columnData[6]);
			ps.setString(3, columnData[7]);
			ps.setString(4, columnData[8]);
			ps.setInt(5, Integer.parseInt(columnData[0]));

			int flag2 = ps.executeUpdate();
			System.out.println("value of flags "+flag1+"  "+flag2);*/
		    if((flag1==1)){
		    	return 0;
		    }
		 

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return -1;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.info("Exception::"+e);
				e.printStackTrace();
			}
		}

		return -1 ;
	}


	/*
	 * Search Project
	 */

	public String search_Project_by_clientname(String client_name) {
		System.out.println("search_Project_by_clientname()");
		outData = "";
		try {
			Connection();

			PreparedStatement ps = con
					.prepareStatement(search_Projectby_clientname);
			ps.setString(1, client_name);
			ResultSet res_search_project = ps.executeQuery();
			while (res_search_project.next()) {

				outData = outData + res_search_project.getString(1)
						+ columnSeperator + res_search_project.getString(2)
						+ columnSeperator + res_search_project.getString(3)
						+ columnSeperator + res_search_project.getString(4)
						+ columnSeperator + res_search_project.getInt(5)
						+ rowSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("outdata :: " + outData);
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getJobCard(int ProjectId) {
		System.out.println("search_Project_by_clientname()");
		outData = "";
		try {
			Connection();
			String query1 = "select a.Project_store_id, job_card_id,job_card_number,store_name,address,city_name "
					+ "from fourth_dimension.job_cards a,Project_stores s "
					+ "where a.Project_store_id=s.Project_store_id and a.Project_id=?";
			PreparedStatement ps = con.prepareStatement(query1);
			ps.setInt(1, ProjectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ProjectStoreId = rs.getInt(1);

				String query2 = "SELECT em.element_name,cm.component_name,mm.material_name,pe.quantity "
						+ "FROM fourth_dimension.measurement_data md,Project_elements pe,element_master em,component_master cm,material_master mm "
						+ "where "
						+ "md.Project_element_id=pe.Project_element_id "
						+ "and pe.element_id=em.element_id "
						+ "and pe.component_id=cm.component_id "
						+ "and pe.material_id=mm.material_id "
						+ "and md.Project_store_id=" + ProjectStoreId;

				String data = "";
				String fieldColumnSeperator = "!#&!";
				String fieldRowSeperator = "!@!";
				Statement st = con.createStatement();
				ResultSet rs1 = st.executeQuery(query2);
				while (rs1.next()) {
					data = data + rs1.getString(1) + fieldColumnSeperator
							+ rs1.getString(2) + fieldColumnSeperator
							+ rs1.getString(3) + fieldColumnSeperator
							+ rs1.getInt(4) + fieldColumnSeperator
							+ fieldRowSeperator;

				}

				outData = outData + data + columnSeperator + rs.getInt(2)
						+ columnSeperator + rs.getString(3) + columnSeperator
						+ rs.getString(4) + columnSeperator + rs.getString(5)
						+ columnSeperator + rs.getString(6) + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getCourier() {
		System.out.println("search_Project_by_clientname()");
		outData = "";
		try {
			Connection();
			String query1 = "select courier_id,courier_name from fourth_dimension.courier_master order by courier_name";
			PreparedStatement ps = con.prepareStatement(query1);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String search_Project_by_name(String Project_name) {
		System.out.println("search_Project_by_name()");

		try {
			Connection();

			PreparedStatement ps = con.prepareStatement(search_Projectby_name);
			ps.setString(1, Project_name);
			ResultSet res_search_project = ps.executeQuery();
			if (res_search_project.next()) {

				return res_search_project.getString(1) + columnSeperator
						+ res_search_project.getString(2) + columnSeperator
						+ res_search_project.getString(3) + columnSeperator
						+ res_search_project.getString(4) + columnSeperator
						+ res_search_project.getInt(5);
			} else {
				return NO_DATA;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
	}

	public String search_Project(String Project_id) {
		System.out.println("ProjectServices.search_project()");

		try {
			Connection();

			PreparedStatement ps = con.prepareStatement(search_Project);
			ps.setString(1, Project_id);
			ResultSet res_search_project = ps.executeQuery();
			if (res_search_project.next()) {

				return res_search_project.getString(1) + columnSeperator
						+ res_search_project.getString(2) + columnSeperator
						+ res_search_project.getString(3) + columnSeperator
						+ res_search_project.getString(4) + columnSeperator
						+ res_search_project.getInt(5);
			} else {
				return NO_DATA;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
	}

	public String filter_Project(String ClientName, String DivisionName) {

		System.out.println("ProjectServices.filter_project()");
		String outData = "";
		try {
			Connection();
			String search_Project = "SELECT Project_number,Project_name FROM Project WHERE "
					+ " client_id =(SELECT CLIENT_ID FROM CLIENT_MASTER WHERE CLIENT_NAME= '"
					+ ClientName
					+ "' ) OR "
					+ " division_id=(SELECT fd_division_id FROM fd_divison_master WHERE division_name= '"
					+ DivisionName + "' ) order by Project_name";
			PreparedStatement ps = con.prepareStatement(search_Project);
			System.out.println("query filter :::: " + search_Project);
			ResultSet res_search_project = ps.executeQuery();
			while (res_search_project.next()) {

				outData = outData + res_search_project.getString(1)
						+ columnSeperator + res_search_project.getString(2)
						+ rowSeperator;
			}
			if (!outData.equalsIgnoreCase("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String filter_Project(int clientId) {

		System.out.println("ProjectServices.filter_project()");
		String outData = "";
		try {
			Connection();
			String search_Project = "SELECT Project_id,Project_name,division_name FROM Project a,fd_division_master d WHERE "
					+ "a.division_id=d.fd_division_id and client_id="
					+ clientId+" order by Project_name";
			PreparedStatement ps = con.prepareStatement(search_Project);
			System.out.println("query filter :::: " + search_Project);
			ResultSet res_search_project = ps.executeQuery();
			while (res_search_project.next()) {

				outData = outData + res_search_project.getInt(1)
						+ columnSeperator + res_search_project.getString(2)
						+ columnSeperator + res_search_project.getString(3)
						+ columnSeperator + rowSeperator;
			}
			if (!outData.equalsIgnoreCase("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String insert_payment_term(String inData, String Project_id) {
		System.out.println("ProjectServices.insert_payment_term()");
		String[] split_data = inData.split(",");

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("Split data " + i + " --> " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return DATA_INSUFFICIENT;
			}

		}

		try {

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date dateTime = formatter.parse(split_data[0] + " 00:00:00");
			System.out.println("dateTime" + dateTime);

			Timestamp timeStampDate = new Timestamp(dateTime.getTime());

			dateTime = formatter.parse(split_data[1] + " 00:00:00");
			Timestamp timeStampDate1 = new Timestamp(dateTime.getTime());
			System.out.println("dateTime" + dateTime);

			dateTime = formatter.parse(split_data[2] + " 00:00:00");
			Timestamp timeStampDate2 = new Timestamp(dateTime.getTime());
			System.out.println("dateTime" + dateTime);

			dateTime = formatter.parse(split_data[3] + " 00:00:00");
			Timestamp timeStampDate3 = new Timestamp(dateTime.getTime());
			System.out.println("dateTime" + dateTime);

			Connection();
			PreparedStatement ps = con
					.prepareStatement(insert_payment_term_query);
			ps.setInt(1, Integer.parseInt(Project_id));
			ps.setTimestamp(2, timeStampDate);
			ps.setTimestamp(3, timeStampDate1);
			ps.setTimestamp(4, timeStampDate2);
			ps.setTimestamp(5, timeStampDate3);
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_INSERTED;
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_INSERTED;
		}

		return DATA_NOT_INSERTED;
	}

	// insert into
	// Project(Project_number,Project_name,client_id,division_id,start_date,end_date)
	// values(?,?,?,?,?,?)

	/*
	 * public String Project_insert(String inData){
	 * System.out.println("ProjectServices.Project_insert()"); String[]
	 * split_data=inData.split(",");
	 * 
	 * for(int i=0;i<split_data.length;i++){
	 * 
	 * System.out.println("Split data "+i+" --> "+split_data[i]); if
	 * ((split_data[i] == null) || split_data[i].equals("")) { return
	 * DATA_INSUFFICIENT; }
	 * 
	 * } try {
	 * 
	 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * Date dateTime = formatter.parse(split_data[4]+" 00:00:00");
	 * System.out.println("dateTime" + dateTime);
	 * 
	 * Timestamp timeStampDate = new Timestamp(dateTime.getTime());
	 * 
	 * dateTime = formatter.parse(split_data[5]+" 23:59:59");
	 * System.out.println("dateTime" + dateTime);
	 * 
	 * Timestamp timeStampDate1 = new Timestamp(dateTime.getTime());
	 * 
	 * 
	 * 
	 * Connection(); PreparedStatement
	 * ps=con.prepareStatement(Project_insert_query);
	 * ps.setString(1,split_data[0]); ps.setString(2,split_data[1]);
	 * ps.setString(3,split_data[3]); ps.setString(4,split_data[2]);
	 * ps.setTimestamp(5,timeStampDate); ps.setTimestamp(6,timeStampDate1);
	 * status=ps.executeUpdate(); if(status>0){ return DATA_INSERTED; } } catch
	 * (SQLException e) {
	 * 
	 * e.printStackTrace(); return DATA_NOT_INSERTED; } catch (Exception e) {
	 * e.printStackTrace(); return DATA_NOT_INSERTED; }
	 * 
	 * 
	 * return DATA_NOT_INSERTED; }
	 */

	public String Project_insert(String inData, String inClientData) {
		System.out.println("ProjectServices.Project_insert()");

		String[] split_data = inData.split("!#!");
		for (int i = 0; i < split_data.length; i++) {

			System.out.println("Split data " + i + " --> " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return DATA_INSUFFICIENT;
			}
		}

		String[] split_inClientdata = inClientData.split("!#!");
		for (int i = 0; i < split_inClientdata.length; i++) {

			System.out.println("split_inClientdata " + i + " --> "
					+ split_inClientdata[i]);
			if ((split_inClientdata[i] == null)
					|| split_inClientdata[i].equals("")) {
				return DATA_INSUFFICIENT;
			}
		}

		try {

			//SimpleDateFormat formatter = new SimpleDateFormat(
			//		"yyyy-MM-dd HH:mm:ss");

			String Project_insert_details = "INSERT INTO  fourth_dimension.Project "
					+ " ( Project_name ,  Project_number , client_id , division_id , start_date ,"
					+ " end_date , notes , crm_name , crm_phone , crm_email , estimated_budget ,"
					+ " billing_narration , special_instruction , total_stores,approval_mgr_id,status,Project_status_date ) VALUES ( "
					+ " ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			Connection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(Project_insert_details);
			ps.setString(1, split_data[0]);
			ps.setString(2, split_data[1]);
			ps.setString(3, split_data[2]);
			ps.setString(4, split_data[3]);

			// Dates
		
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			
			String timeStampDate = null;
			Timestamp timestamp=null;
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.YEAR, -100);
			formatter.set2DigitYearStart(cal.getTime());

			if (!split_data[4].equalsIgnoreCase("empty")) {
				timeStampDate=fmt.format(formatter.parse(split_data[4]));
				ps.setString(5, timeStampDate);
			} else {
				//Date dateTime = fmt.parse("1970-01-01 00:00:00");
				//timestamp = new Timestamp(dateTime.getTime());
				ps.setTimestamp(5, null);	

			}

			if (!split_data[5].equalsIgnoreCase("empty")) {
				timeStampDate=fmt.format(formatter.parse(split_data[5]));
				ps.setString(6, timeStampDate);
			} else {
				//Date dateTime = fmt.parse("1970-01-01 00:00:00");
				//timestamp = new Timestamp(dateTime.getTime());
				ps.setTimestamp(6, null);	

			}

		//	ps.setTimestamp(5, timeStampDate);
		//	ps.setTimestamp(6, timeStampDate1);
			ps.setString(7, split_data[6]);
			ps.setString(8, split_data[7]);
			ps.setString(9, split_data[8]);
			ps.setString(10, split_data[9]);

			// Estimated Budget
			float estimat_budget = 0;
			if (split_data[10].equals("empty")) {

			} else {
				estimat_budget = Float.parseFloat(split_data[10]);
			}
			ps.setFloat(11, estimat_budget);
			ps.setString(12, split_data[11]);
			ps.setString(13, split_data[12]);

			// total Store
			int store_no = 0;
			if (split_data[13].equals("empty")) {

			} else {
				store_no = Integer.parseInt(split_data[13]);
			}

			ps.setInt(14, store_no);
			ps.setString(15, split_data[14]);
			
			ps.setString(16, split_data[15]);
			
			if (!split_data[16].equalsIgnoreCase("empty")) {
				timeStampDate=fmt.format(formatter.parse(split_data[16]));
				ps.setString(17, timeStampDate);
			} else {
				//Date dateTime = fmt.parse("1970-01-01 00:00:00");
				//timestamp = new Timestamp(dateTime.getTime());
				ps.setTimestamp(17, null);	

			}
			
			
			status = ps.executeUpdate();
			if (status > 0) {
				System.out.println("Inserted");

				ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					int Project_id = rs.getInt(1);
					System.out.println("LAST ID-->" + Project_id);
					PreparedStatement ps_client = con
							.prepareStatement("INSERT INTO fourth_dimension . Project_client_details "
									+ " ( Project_id , client_manager_name , client_manager_phone , client_manager_email ,"
									+ " po_reference , po_date,po_amount )VALUES (?,?,?,?,?,?,?) ");
					ps_client.setInt(1, Project_id);
					ps_client.setString(2, split_inClientdata[0]);
					ps_client.setString(3, split_inClientdata[1]);
					ps_client.setString(4, split_inClientdata[2]);
					ps_client.setString(5, split_inClientdata[3]);
					

					// po date
					/*Date dateTime = formatter.parse("1970-01-01 00:00:00");
					Timestamp timeStampDate_po = new Timestamp(
							dateTime.getTime());

					if (!split_inClientdata[4].equalsIgnoreCase("empty")) {
						dateTime = formatter.parse(split_inClientdata[4]
								+ " 00:00:00");
						System.out.println("dateTime" + dateTime);
						timeStampDate_po = new Timestamp(dateTime.getTime());
					} else {

					}*/
					if (!split_inClientdata[4].equalsIgnoreCase("empty")) {
						timeStampDate=fmt.format(formatter.parse(split_inClientdata[4]));
						ps_client.setString(6, timeStampDate);
					} else {
						//Date dateTime = fmt.parse("1970-01-01 00:00:00");
						//timestamp = new Timestamp(dateTime.getTime());
						ps_client.setTimestamp(6, null);	

					}
					
					//ps_client.setTimestamp(6, timeStampDate_po);
					
					ps_client.setFloat(7, ((split_inClientdata[5] != null) && (split_inClientdata[5].length() > 0))? Float.parseFloat(split_inClientdata[5]) : 0);
					
					int client_state = ps_client.executeUpdate();
					if (client_state > 0) {
						System.out.println("Inserted");
						con.setAutoCommit(true);
						return DATA_INSERTED + Project_id;
					}
				} else {

				}

			}
			return DATA_NOT_INSERTED;

		} catch (SQLException e) {

			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_INSERTED;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_INSERTED;
		} finally {

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				log.info("Exception::"+e);
			}
		}
	}

	public String Project_open() {

		try {
			outData = "";
			int n = 0;
			Connection();
			PreparedStatement ps = con.prepareStatement(Project_open_query);
			rs = ps.executeQuery();

			while (rs.next()) {

				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rs.getString(3)
						+ columnSeperator + rs.getString(4) + columnSeperator
						+ rs.getString(5) + Constants.rowSeperator;
				++n;
				if (n > 30)
					break;
			}
			if (outData.length() > 0) {
				System.out.println("outData---->" + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outData = null;
		}

		outData = null;
		return outData;
	}

	public String Project_header_details(int Project_id) {
		System.out.println("Project_id " + Project_id);

		// String
		// query="SELECT p.project_number,p.project_name,c.client_name,fd_div.division_name,p.start_date,p.end_date FROM fourth_dimension.project p ,fourth_dimension.client_master c,fourth_dimension.fd_divison_master fd_div where p.client_id=c.client_id and fd_div.fd_division_id=p.division_id and p.project_number="+project_id;

		try {

			Connection();
			PreparedStatement ps = con
					.prepareStatement(Project_header_details_query);
			ps.setInt(1, Project_id);
			rs = ps.executeQuery();
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			
		    String startDate;
		    String endDate;
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.YEAR, -100);
			formatter.set2DigitYearStart(cal.getTime());

		
			while (rs.next()) {
				/*String start_date = rs.getString("start_date");
				if(start_date!= "" || start_date != null){
					start_date = changeNewDateFormat(start_date);
				}
				String end_date = rs.getString("end_date");
				if(end_date!= "" || end_date != null){
					end_date = changeNewDateFormat(end_date);
				}*/
				
				if((rs.getString(5)==null)||(rs.getString(5).equals("null"))){
				startDate=" ";	
				}
				else{
					startDate=formatter.format(fmt.parse(rs.getString(5)));
				}
				
				if((rs.getString(6)==null)||(rs.getString(6).equals("null"))){
					endDate=" ";	
					}
					else{
						endDate=formatter.format(fmt.parse(rs.getString(6)));
					}
				
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rs.getString(3)
						+ columnSeperator + rs.getString(4) + columnSeperator
						+ startDate + columnSeperator + endDate;
			}
			if (outData.length() > 0) {
				System.out.println("outData---->" + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			outData = null;
		}

		return outData;
	}

	/*
	 * public String project_scope_work(String inData ,String
	 * scope_set_as_hold){
	 * 
	 * System.out.println("ProjectServices.project_scope_work()"); String[]
	 * split_data=inData.split(columnSeperator);
	 * 
	 * for(int i=0;i<split_data.length;i++){
	 * 
	 * System.out.println("split_data"+i+"   "+split_data[i]); if
	 * ((split_data[i] == null) || split_data[i].equals("")) { return
	 * DATA_INSUFFICIENT; } }
	 * 
	 * 
	 * 
	 * try { PreparedStatement ps_last_pr_ele_id=null; int project_element_id=0;
	 * Connection(); PreparedStatement
	 * ps=con.prepareStatement(project_scope_work);
	 * System.out.println(project_scope_work);
	 * ps.setInt(1,Integer.parseInt(split_data[0]));
	 * ps.setString(2,split_data[1]); ps.setString(3,split_data[2]);
	 * ps.setString(4,split_data[3]); ps.setString(5,split_data[4]);
	 * ps.setInt(6,Integer.parseInt(split_data[5]));
	 * ps.setString(7,split_data[6]); ps.setString(8,split_data[7]);
	 * status=ps.executeUpdate(); if(status>0){
	 * 
	 * ps_last_pr_ele_id=con.prepareStatement(
	 * "SELECT MAX(project_element_id) FROM fourth_dimension.project_elements");
	 * ResultSet rs=ps_last_pr_ele_id.executeQuery(); if(rs.next()){
	 * project_element_id=rs.getInt(1);
	 * System.out.println("project_element_id--->"+project_element_id); }
	 * project_elements_scope_of_work
	 * (project_element_id,split_data[8],split_data[9],scope_set_as_hold);
	 * 
	 * return DATA_INSERTED; } } catch (SQLException e) {
	 * 
	 * e.printStackTrace(); return DATA_NOT_INSERTED; }
	 * 
	 * return DATA_NOT_INSERTED;
	 * 
	 * }
	 */

	public String Project_scope_of_work_insert(String inData,
			String scope_set_as_hold, String project_name) {

		System.out.println("ProjectServices.Project_scope_of_work_insert()");
		String[] split_data = inData.split(columnSeperator);

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("split_data" + i + "   " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return DATA_INSUFFICIENT;
			}
		}

		try {
			Connection();

			/*// For checking duplicate project entry
			PreparedStatement ps_check = con
					.prepareStatement("select project_code from fourth_dimension.Project_elements"
							+ " where project_code=?");
			ps_check.setString(1, project_number);
			ResultSet rs = ps_check.executeQuery();
			while (rs.next()) {
				return "PROJECT CODE EXIST";
			}*/

			PreparedStatement ps = con
					.prepareStatement(Project_scope_of_work_insert);

			ps.setInt(1, Integer.parseInt(split_data[0]));
			ps.setString(2, split_data[1]);
			ps.setString(3, split_data[2]);
			System.out.println("insert service $$$$$$$$$$ component_id is####"+split_data[4]);
			ps.setString(4, split_data[3]);
			ps.setString(5, split_data[4]);
			ps.setInt(6, Integer.parseInt(split_data[5]));
			ps.setString(7, split_data[6]);
			// ps.setString(8,split_data[7]);
			ps.setString(8, scope_set_as_hold);
			//ps.setString(9, project_number);
			ps.setString(9, project_name);
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_INSERTED;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_INSERTED;
		}

		return DATA_NOT_INSERTED;

	}

	public void Project_elements_scope_of_work(int Project_element_id,
			String scopeID, String element_scope_dates, String scope_set_as_hold) {
		System.out.println("ProjectServices.Project_elements_scope_of_work()");
		String hold = "NO";
		String[] scope_id = scopeID.split(",");
		System.out.println("Scope" + scope_id);
		System.out.println("element_scope_dates" + element_scope_dates);
		String[] scope_date = element_scope_dates.split("@!@");
		System.out.println("scope_set_as_hold----> " + scope_set_as_hold);
		String[] scope_set_hold = scope_set_as_hold.split(",");
		try {

			PreparedStatement ps = con
					.prepareStatement(Project_elements_scope_of_work_query);

			/*
			 * PreparedStatement ps=con.prepareStatement(
			 * "insert into fourth_dimension.project_elements_scope_of_work" +
			 * "(project_elements_id,set_as_hold,scope_id) values(?,?,?)");
			 */

			for (int i = 0; i < scope_id.length; i++) {
				hold = "NO";

				System.out.println("Scope Date--->" + scope_date[i]);
				String date[] = scope_date[i].split(",");

				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				Date dateTime = formatter.parse(date[0] + " 00:00:00");
				System.out.println("dateTime" + dateTime);
				Timestamp timeStampDate = new Timestamp(dateTime.getTime());

				dateTime = formatter.parse(date[1] + " 23:59:59");
				System.out.println("dateTime" + dateTime);
				Timestamp timeStampDate1 = new Timestamp(dateTime.getTime());

				dateTime = formatter.parse(date[2] + " 00:00:00");
				System.out.println("dateTime" + dateTime);
				Timestamp timeStampDate2 = new Timestamp(dateTime.getTime());

				dateTime = formatter.parse(date[3] + " 23:59:59");
				System.out.println("dateTime" + dateTime);
				Timestamp timeStampDate3 = new Timestamp(dateTime.getTime());

				dateTime = formatter.parse(date[4] + " 00:00:00");
				System.out.println("dateTime" + dateTime);
				Timestamp timeStampDate4 = new Timestamp(dateTime.getTime());

				dateTime = formatter.parse(date[5] + " 23:59:59");
				System.out.println("dateTime" + dateTime);
				Timestamp timeStampDate5 = new Timestamp(dateTime.getTime());

				dateTime = formatter.parse(date[6] + " 00:00:00");
				System.out.println("dateTime" + dateTime);
				Timestamp timeStampDate6 = new Timestamp(dateTime.getTime());

				dateTime = formatter.parse(date[7] + " 23:59:59");
				System.out.println("dateTime" + dateTime);
				Timestamp timeStampDate7 = new Timestamp(dateTime.getTime());

				ps.setInt(1, Project_element_id);
				ps.setInt(2, Integer.parseInt(scope_id[i]));

				for (int j = 0; j < scope_set_hold.length; j++) {
					if (scope_set_hold[j].equals(scope_id[i])) {
						hold = "YES";
						break;
					}
				}

				ps.setString(3, hold);
				ps.setTimestamp(4, timeStampDate);
				ps.setTimestamp(5, timeStampDate1);
				ps.setTimestamp(6, timeStampDate2);
				ps.setTimestamp(7, timeStampDate3);
				ps.setTimestamp(8, timeStampDate4);
				ps.setTimestamp(9, timeStampDate5);
				ps.setTimestamp(10, timeStampDate6);
				ps.setTimestamp(11, timeStampDate7);
				ps.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
		}
	}

	public String CRM_details_update(String inData) {
		System.out.println("ProjectServices.CRM_details_update()" + inData);

		String[] colStrings = inData.split(columnSeperator);

		/*
		 * for(int i=0;i<colStrings.length;i++){ if(colStrings[i].equals("")) {
		 * return DATA_INSUFFICIENT; } System.out.println(colStrings[i]); }
		 */
		// String
		// query="update fourth_dimension.project set crm_name=?,crm_phone=?,crm_email=?,notes=? where project_id=?";

		try {

			Connection();
			PreparedStatement ps = con
					.prepareStatement(CRM_details_update_query);
			ps.setString(1, colStrings[0]);
			ps.setString(2, colStrings[1]);
			ps.setString(3, colStrings[2]);
			ps.setString(4, colStrings[3]);
			ps.setInt(5, Integer.parseInt(colStrings[4]));
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Updated---->" + i);
				return DATA_UPDATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return DATA_NOT_UPDATED;
		}
		return DATA_NOT_UPDATED;
	}

	public String update_payment_term(String inData, String Project_id) {
		System.out.println("update_payment_term() called");
		String[] split_data = inData.split(",");

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("Split data " + i + " --> " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return DATA_INSUFFICIENT;
			}

		}

		try {

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date dateTime = formatter.parse(split_data[0] + " 00:00:00");
			System.out.println("dateTime" + dateTime);

			Timestamp timeStampDate = new Timestamp(dateTime.getTime());

			dateTime = formatter.parse(split_data[1] + " 00:00:00");
			Timestamp timeStampDate1 = new Timestamp(dateTime.getTime());
			System.out.println("dateTime" + dateTime);

			dateTime = formatter.parse(split_data[2] + " 00:00:00");
			Timestamp timeStampDate2 = new Timestamp(dateTime.getTime());
			System.out.println("dateTime" + dateTime);

			dateTime = formatter.parse(split_data[3] + " 00:00:00");
			Timestamp timeStampDate3 = new Timestamp(dateTime.getTime());
			System.out.println("dateTime" + dateTime);

			Connection();
			PreparedStatement ps = con
					.prepareStatement(update_payment_term_query);

			ps.setTimestamp(1, timeStampDate);
			ps.setTimestamp(2, timeStampDate1);
			ps.setTimestamp(3, timeStampDate2);
			ps.setTimestamp(4, timeStampDate3);
			ps.setInt(5, Integer.parseInt(Project_id));
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_UPDATED;
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_UPDATED;
		}

		return DATA_NOT_UPDATED;
	}

	// Checking detils
	public String check_TermDays_details(int Project_id) {
		outData = "";

		try {

			Connection();
			PreparedStatement ps = con
					.prepareStatement(check_TermDays_details_query);
			ps.setInt(1, Project_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = rs.getDate(1) + columnSeperator + rs.getDate(2)
						+ columnSeperator + rs.getDate(3) + columnSeperator
						+ rs.getDate(4);
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: check_TermDays_details::"+e);
			return NO_DATA;
		}
		return NO_DATA;

	}

	public String check_Project_element(int Project_id) {
		outData = "";

		try {

			Connection();
			PreparedStatement ps = con
					.prepareStatement(check_Project_element_query);
			ps.setInt(1, Project_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = rs.getDate(1) + columnSeperator + rs.getDate(2)
						+ columnSeperator + rs.getString(3) + columnSeperator
						+ rs.getString(4);
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: check_Project_element"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	/*
	 * Retrieve Element Sopce related to a project
	 * 
	 * SELECT project_name ,brand_name, element_name
	 * ,component_name,material_name ,quantity , depot_name , print_mode_name
	 * FROM project,project_elements,brand_master
	 * ,element_master,component_master,
	 * material_master,depot_master,print_mode_master WHERE
	 * project_elements.project_id=2 AND
	 * project_elements.project_id=project.project_id AND
	 * project_elements.brand_id=brand_master.brand_id AND
	 * project_elements.element_id=element_master.element_id AND
	 * project_elements.component_id=component_master.component_id AND
	 * project_elements.material_id=material_master.material_id AND
	 * project_elements.depot_id=depot_master.depot_id AND
	 * project_elements.print_mode_id=print_mode_master.print_mode_id;
	 */

	public String retrieve_Project_scope_list(int Project_id) {
		outData = "";

		try {

			Connection();
			PreparedStatement ps = con
					.prepareStatement(retrieve_Project_scope_list_query);
			ps.setInt(1, Project_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rs.getString(3)
						+ columnSeperator + rs.getString(4) + columnSeperator
						+ rs.getInt(5) + columnSeperator + rs.getString(6)
						+ columnSeperator + rs.getInt(7) + columnSeperator
						+ rs.getString(8) + columnSeperator + rs.getString(9)
						+ columnSeperator + rs.getString(10) + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: "+e);
			return NO_DATA;
		}
		return NO_DATA;

	}

	/*
	 * public String retrieve_project_scope_list(int project_id){ outData="";
	 * String
	 * query="SELECT brand_name, element_name ,component_name,material_name ," +
	 * "quantity , depot_name ,print_mode_name,project_element_id FROM project,project_elements,brand_master ,"
	 * +
	 * "element_master,component_master, material_master,depot_master,print_mode_master "
	 * +
	 * "WHERE project_elements.project_id=? AND project_elements.project_id=project.project_id "
	 * +
	 * " AND project_elements.brand_id=brand_master.brand_id AND project_elements.element_id=element_master.element_id"
	 * +
	 * " AND project_elements.component_id=component_master.component_id AND project_elements.material_id=material_master.material_id "
	 * +
	 * "AND project_elements.depot_id=depot_master.depot_id AND project_elements.print_mode_id=print_mode_master.print_mode_id"
	 * ; try {
	 * 
	 * Connection(); PreparedStatement ps=con.prepareStatement(query);
	 * ps.setInt(1,project_id); ResultSet rs=ps.executeQuery();
	 * while(rs.next()){
	 * 
	 * 
	 * outData=outData+rs.getString(1)+columnSeperator+rs.getString(2)
	 * +columnSeperator
	 * +rs.getString(3)+columnSeperator+rs.getString(4)+columnSeperator+
	 * rs.getInt
	 * (5)+columnSeperator+rs.getString(6)+columnSeperator+rs.getString(7)+
	 * columnSeperator+rs.getInt(8)+Constants.rowSeperator; }
	 * if(!outData.equals("")){ return outData; } } catch (Exception e) {
	 * e.printStackTrace(); return NO_DATA; } return NO_DATA;
	 * 
	 * }
	 */
	/*
	 * Retrieve Element Scopes Date Plan
	 * 
	 * SELECT project_element_id , scope_name , set_as_hold ,
	 * client_planned_start_date , client_planned_end_date ,
	 * client_actual_start_date , client_actual_end_date , fd_planned_start_date
	 * , fd_planned_end_date , fd_actual_start_date , fd_actual_end_date FROM
	 * project_element_scope_dates ,scope_master WHERE
	 * project_element_scope_dates.scope_id=scope_master.scope_id AND
	 * project_element_scope_dates.project_element_id=?
	 */

	public String retrieve_element_scope_date_plans(int Project_element_id) {
		outData = "";

		try {

			Connection();
			PreparedStatement ps = con
					.prepareStatement(retrieve_element_scope_date_plans_query);
			ps.setInt(1, Project_element_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rs.getString(3)
						+ columnSeperator + rs.getString(4) + columnSeperator
						+ rs.getString(5) + columnSeperator + rs.getString(6)
						+ columnSeperator + rs.getString(7) + columnSeperator
						+ rs.getString(8) + columnSeperator + rs.getString(9)
						+ columnSeperator + rs.getString(10) + columnSeperator
						+ rs.getString(11) + Constants.rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: "+e);
			return NO_DATA;
		}
		return NO_DATA;

	}

	public String check_Project_Approval(int Project_id) {
		outData = "";

		try {

			Connection();
			PreparedStatement ps = con
					.prepareStatement(check_Project_Approval_query);
			ps.setInt(1, Project_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				// client_manager_approval_date,
				// fd_manager_approval_date,client_manager_comments,fd_manager_comments
				outData = rs.getDate(1) + columnSeperator + rs.getDate(2)
						+ columnSeperator + rs.getString(3) + columnSeperator
						+ rs.getString(4) + columnSeperator + rs.getString(5)
						+ columnSeperator + rs.getString(6);
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String insert_Project_approval(String inData, String Project_id) {
		System.out.println("insert_Project_approval()");
		String[] split_data = inData.split(columnSeperator);

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("Split data " + i + " --> " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return DATA_INSUFFICIENT;
			}

		}

		try {

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			Date dateTime = formatter.parse(split_data[0] + " 00:00:00");
			System.out.println("dateTime" + dateTime);
			Timestamp timeStampDate = new Timestamp(dateTime.getTime());

			dateTime = formatter.parse(split_data[3] + " 00:00:00");
			Timestamp timeStampDate1 = new Timestamp(dateTime.getTime());
			System.out.println("dateTime" + dateTime);

			Connection();
			PreparedStatement ps = con
					.prepareStatement(insert_ProjectApproval_query);
			ps.setInt(1, Integer.parseInt(Project_id));
			ps.setTimestamp(2, timeStampDate);
			ps.setString(3, split_data[1]);
			ps.setString(4, split_data[2]);
			ps.setTimestamp(5, timeStampDate1);
			ps.setString(6, split_data[4]);
			ps.setString(7, split_data[5]);

			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_INSERTED;
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception :: "+e);
			return DATA_NOT_INSERTED;
		}

		return DATA_NOT_INSERTED;
	}

	public String check_CRM_details(int Project_id) {
		outData = "";

		try {

			Connection();
			PreparedStatement ps = con
					.prepareStatement(check_CRM_details_query);
			ps.setInt(1, Project_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = rs.getString(1) + columnSeperator + rs.getString(2)
						+ columnSeperator + rs.getString(3) + columnSeperator
						+ rs.getString(4);
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	// Drop Down
	public String drop_down_fddiv() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_fddiv_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getInt(2) + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String drop_down_client() {

		try {

			Connection();
			System.out.println("working");
			PreparedStatement ps = con
					.prepareStatement(drop_down_client_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				if (rs.getString(2) == null) {

					return NO_DATA;
				}

				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getString(2) + rowSeperator;

			}

			if (!outData.equals("")) {
				return outData;
			}
			System.out.println("out data is6 " + outData);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String drop_down_brandOnClient(String id) {
		System.out.println("ProjectServices.drop_down_brandOnClient() --> "
				+ id);
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_brand_master_onClient);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String drop_down_brand(String clientName) {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_brand_master);
			ps.setString(1, clientName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception ::drop down brand:: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String drop_down_element() {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_element_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String drop_down_component() {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_component_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String drop_down_material() {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_material_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception ::drop_down_material "+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String drop_down_fdhubID() {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_fdhub_master_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getInt(2) + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
log.info("Exception:: drop_down_fd hub::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String drop_down_depot() {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_depot_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String drop_down_print_mode_master() {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_print_mode_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;

	}

	// Select Project Scope list

	public String getBrandFromProjectId(String projectId) {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_brand_on_ProjectId);
			ps.setString(1, projectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getInt(2) + rowSeperator;
			}
			if (!outData.equals("")) {

				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getElementFromBrand(String brandId, String projectId) {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_element_on_brandId);
			ps.setInt(1, Integer.parseInt(brandId));
			ps.setInt(2, Integer.parseInt(projectId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getString(2) + rowSeperator;
			}
			if (!outData.equals("")) {

				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}
	
	public String getComponentFromBrand(String brandId, String projectId) {
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_component_on_brandId);
			ps.setInt(1, Integer.parseInt(brandId));
			ps.setInt(2, Integer.parseInt(projectId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				/*if (rs.getString(1) == null) {
					return NO_DATA;
				}*/
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getString(2) + rowSeperator;
			}
			if (!outData.equals("")) {

				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String getComponentMaterialOnElement(String ProjectId,
			String brandId, String elementId) {
		System.out.println("ProjectServices.getComponentMaterialOnElement()");
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_component_material_on_element);
			ps.setInt(1, Integer.parseInt(elementId));
			ps.setInt(2, Integer.parseInt(brandId));
			ps.setInt(3, Integer.parseInt(ProjectId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getString(2) + columnSeperator + rs.getString(3);
				System.out.println("outdata ---->" + outData);
			}
			if (!outData.equals("")) {

				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {

			}

		}
		return NO_DATA;

	}

	public String getProjectElementIDbyProjectElement(String Project_store_id,
			String element_id) {

		//System.out
			//	.println("ProjectServices.getProjectElementIDbyProjectElement()"
					//	+ Project_id + "   " + element_id);
		/*
		 * String
		 * query="Select Project_element_id from fourth_dimension.Project_elements "
		 * +
		 * "where Project_id=(select Project_id from fourth_dimension.Project where Project_name=?)"
		 * + " and Project_element_id=?";
		 */
		String query = "Select Project_element_id from fourth_dimension.Project_elements "
				+ "where Project_id=(select Project_id from Project_stores where Project_store_id=?)  and element_id=?";

		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(Project_store_id));
			ps.setInt(2, Integer.parseInt(element_id));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				outData = rs.getString(1);
				System.out.println("outdata ---->" + outData);
			}
			if (!outData.equals("")) {

				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {

			}

		}
		return NO_DATA;
	}

	// Select Project Scope list

	public String scope_list() {
		System.out.println("ProjectServices.scope_list()");
		outData = "";

		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(scope_list_query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("outData" + outData);
				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + Constants.rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		}
		return NO_DATA;
	}

	public String insertMeasurementDataForElement(String indata,
			String Project_elementID, String store_ID) {

		System.out.println("ProjectServices.insertMeasurementDataForElement()");
		System.out.println("indata" + indata);
		System.out.println("Project_elementID" + Project_elementID);
		System.out.println("store_ID" + store_ID);
		String[] split_String = indata.split("@!@");
		for (int i = 0; i < split_String.length; i++) {
			if (split_String[i].equals("") || split_String[i] == null) {
				return DATA_INSUFFICIENT;
			}
		}
		if (Project_elementID.equals("") || Project_elementID == null) {
			return DATA_INSUFFICIENT;
		}
		if (store_ID.equals("") || store_ID == null) {
			return DATA_INSUFFICIENT;
		}
		try {
			Connection();

			ps = con.prepareStatement(insert_Measurement_Data_For_Element);
			ps.setInt(1, Integer.parseInt(Project_elementID));
			ps.setString(2, store_ID);
			ps.setString(3, split_String[0]);
			ps.setString(4, split_String[1]);
			ps.setString(5, split_String[2]);
			ps.setString(6, split_String[3]);
			ps.setString(7, split_String[4]);
			ps.setString(8, split_String[5]);
			ps.setString(9, split_String[6]);
			ps.setString(10, split_String[7]);
			ps.setString(11, split_String[8]);
			ps.setInt(12, Integer.parseInt(split_String[9]));
			ps.setInt(13, Integer.parseInt(split_String[10]));
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_INSERTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_INSERTED;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {

			}
		}
		return DATA_NOT_INSERTED;
	}

	public String selectMeasurementDisplayOnProjectElementID_StoreID(
			 String StoreID) {
		System.out
				.println("ProjectServices.selectMeasurementDisplayOnProjectElementID_StoreID()");
		//System.out.println("ProjectID " + ProjectID);
		System.out.println("StoreID " + StoreID);
		String outData = "";
		String ProjectElementsIDs = "";
		String query_ProjectElementsIDsFromProjectID = "select Project_element_id from Project_elements where Project_id=" +
				"(select Project_id from Project_stores where Project_store_id=?) ";

		try {
			Connection();

			ps = con.prepareStatement(query_ProjectElementsIDsFromProjectID);
			ps.setInt(1, Integer.parseInt(StoreID));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProjectElementsIDs = ProjectElementsIDs + rs.getInt(1) + ",";

			}

			ProjectElementsIDs = ProjectElementsIDs.substring(0,
					ProjectElementsIDs.length() - 1);

			System.out.println("ProjectElementsIDs -->" + ProjectElementsIDs);

			if (!ProjectElementsIDs.equals("")) {

				String query_measurmentData="SELECT  measurement_id , Project_store_id ,  surface_number ,  surface_detail ,height,width," +
						"(Select unit_name from unit_master where id=width_unit) width_unit_name,thickness,(Select unit_name from unit_master " +
						"where id=thickness_unit)thickness_unit_name,m.quantity,width_unit,thickness_unit,(select brand_name from brand_master" +
						" where brand_id=pe.brand_id)brand_name,(select element_name from element_master where element_id=pe.element_id)" +
						" element_name,(select component_name from component_master where component_id=m.component_id) component_name," +
						"(select material_name from material_master where material_id=m.material_id) material_name ,m.Project_element_id," +
						"esm.element_status_name,esm.element_status_id,m.component_id,pe.project_code,pe.project_name from " +
						"fourth_dimension.element_status_master esm,fourth_dimension . measurement_data m,fourth_dimension .Project_elements pe " +
						"where m.element_status=esm.element_status_id and m.Project_element_id in ("+ProjectElementsIDs+") and m.Project_store_id=? " +
						"and m.Project_element_id = pe.Project_element_id";	
			
				ps = con.prepareStatement(query_measurmentData);
				// ps.setString(1,ProjectElementsIDs);
				ps.setString(1, StoreID);
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {

					outData = outData + resultSet.getInt("measurement_id")
							+ columnSeperator
							+ resultSet.getString("surface_number")
							+ columnSeperator
							+ resultSet.getString("surface_detail")
							+ columnSeperator + resultSet.getFloat("height")
							+ columnSeperator + resultSet.getFloat("width")
							+ columnSeperator
							+ resultSet.getString("width_unit_name")
							+ columnSeperator + resultSet.getFloat("thickness")
							+ columnSeperator
							+ resultSet.getString("thickness_unit_name")
							+ columnSeperator + resultSet.getInt("quantity")
							+ columnSeperator
							+ resultSet.getString("width_unit")
							+ columnSeperator
							+ resultSet.getString("thickness_unit")
							+ columnSeperator
							+ resultSet.getString("brand_name")
							+ columnSeperator
							+ resultSet.getString("element_name")
							+ columnSeperator
							+ resultSet.getString("component_name")
							+ columnSeperator
							+ resultSet.getString("material_name")
							+ columnSeperator
							+ resultSet.getString("Project_element_id")
							+ columnSeperator
							+ resultSet.getString("element_status_name")
							+columnSeperator
							+resultSet.getString("element_status_id")
							+columnSeperator
							+resultSet.getInt("component_id")
							+columnSeperator
							+resultSet.getString("project_code")
							+columnSeperator
							+resultSet.getString("project_name")
							+ rowSeperator;

				}

				if (!outData.equals("")) {
					System.out.println("outData-->" + outData);
					return outData;
				}
			}

			else {
				return NO_DATA;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
		return NO_DATA;

	}

	public String selectProjectElementsData(String projectElementID) {
		System.out.println("ProjectServices.selectProjectElementsData()"
				+ projectElementID);

		try {
			Connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT (select brand_name from brand_master where brand_id=pe.brand_id) brand_name,"
							+ "(select element_name from element_master where element_id=pe.element_id) element_name,"
							+ "(select component_name from component_master where component_id=pe.component_id) component_name,"
							+ "(select material_name from material_master where material_id=pe.material_id) material_name "
							+ ",set_as_hold FROM  fourth_dimension.Project_elements pe where  Project_element_id=?");
			ps.setInt(1, Integer.parseInt(projectElementID));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("brand_name") + "@!@"
						+ rs.getString("element_name") + "@!@"
						+ rs.getString("component_name") + "@!@"
						+ rs.getString("material_name") + "@!@"
						+ rs.getString("set_as_hold");
			} else {

				return NO_DATA;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}

	}

	public String unit_retrieve(String inData) {

		System.out.println("ProjectServices.unit_retrieve()" + inData);

		try {
			Connection();
			if (inData.equals(null) || inData == " ") {
				inData = "0";
			}
			System.out.println("value of inData is\t" + inData);
			PreparedStatement ps = con
					.prepareStatement("select unit_name from unit_master where id=?");
			ps.setInt(1, Integer.parseInt(inData));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("unit_name");
			} else {

				return NO_DATA;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
	}

	public String delete_measurement_data(String indata) {

		System.out.println("ProjectServices.delete_measurement_data()");
		String id_seperated = "";
		String[] split_data = indata.split(",");

		System.out.println("split_data.length---> " + split_data.length);

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("id ---->  " + split_data[i]);

			if (i == split_data.length - 1) {
				id_seperated = id_seperated + split_data[i];
			} else {
				id_seperated = id_seperated + split_data[i] + ",";
			}

		}
		System.out.println("id_seperated---->  " + id_seperated);

		try {
			Connection();

			PreparedStatement ps = con
					.prepareStatement("DELETE FROM fourth_dimension.measurement_data "
							+ " WHERE measurement_id in(" + id_seperated + ") ");
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_DELETED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_DELETED;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
		return DATA_NOT_DELETED;
	}

	public String updateMeasurementData(String indata, String measureId) {

		System.out.println("ProjectServices.updateMeasurementData()-->"
				+ indata);

		String[] data = indata.split("@!@");
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
			if (data[i] == null || data[i].equals(""))
				return NO_DATA;
		}
			if(data[1].equalsIgnoreCase("empty")){
				data[1]="";
			}
		/*
		 * surface_number =2, surface_detail = 3, height = 6, " + " width=
		 * ?,width_unit=? ,thickness= ?,thickness_unit = ?, quantity = ? WHERE
		 * measurement_id = ?;
		 */
		try {
			
			Connection();
			PreparedStatement ps = con
					.prepareStatement(updateMeasurementData_query);
			ps.setString(1, data[0]);
			ps.setString(2, data[1]);
			ps.setString(3, data[2]);
			ps.setString(4, data[3]);
			ps.setString(5, data[4]);
			ps.setString(6, data[5]);
			ps.setString(7, data[6]);
			ps.setString(8, data[7]);
			ps.setString(9, data[8]);
		    ps.setString(10, measureId);
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_UPDATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_UPDATED;
		}

		return DATA_NOT_UPDATED;
	}

	public String insertJobCard(String inData) {

		String[] split_String = inData.split("@!@");

		for (int i = 0; i < split_String.length; i++) {
			if (split_String[i].equals("") || split_String[i] == null) {
				return DATA_INSUFFICIENT;
			}
		}

		try {
			
			Connection();
             st=con.createStatement();
             rs=st.executeQuery("select * from measurement_data where Project_store_id="+Integer.parseInt(split_String[2]));
             if(!rs.next()){
            	 return "NO_MEASUREMENT_DATA_FOR_STORE";
             }
			ps = con.prepareStatement("INSERT INTO  fourth_dimension . job_cards ( job_card_date ,"
					+ " job_card_number ,  Project_id, Project_store_id ) VALUES( ? , ?, "
					+ "(select Project_id from Project_stores where Project_store_id=?),?)");

			/*SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date dateTime = formatter.parse(split_String[0] + " 00:00:00");
			System.out.println("dateTime" + dateTime);

			Timestamp timeStampDate = new Timestamp(dateTime.getTime());
*/
			
			
			
			
			
			
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			
			String timeStampDate = null;
			Timestamp timestamp=null;
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.YEAR, -100);
			formatter.set2DigitYearStart(cal.getTime());

			timeStampDate=fmt.format(formatter.parse(split_String[0]));
			
			
			
			
			
			
			
			
			
			
			
			
			
			ps.setString(1, timeStampDate);
			ps.setString(2, split_String[1]);
			ps.setString(3, split_String[2]);
			ps.setString(4, split_String[2]);

			status = ps.executeUpdate();
			if (status > 0) {
				System.out.println("Inserted");
				return DATA_INSERTED;
			}
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			System.out.println(DUPLICATE_JOB_CARD);
		log.info("Exception::"+e);
			return DUPLICATE_JOB_CARD;
		} catch (Exception e) {
			e.printStackTrace();
			return DATA_NOT_INSERTED;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {

			}
		}
		return DATA_NOT_INSERTED;

	}

	public String checkJobCardDetail(String Project_store_id) {



		String JobCardDetail_query = "select job_card_number , job_card_date FROM fourth_dimension . job_cards where Project_store_id="
				+ "? ";

		try {
			Connection();

			ps = con.prepareStatement(JobCardDetail_query);

			ps.setString(1, Project_store_id);
		

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String jcNumber=rs.getString(1);
				String jcDate=rs.getString(2);
				
				SimpleDateFormat fmt = new java.text.SimpleDateFormat(
				"dd/MM/yy");
		SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -100);
		sdf.set2DigitYearStart(cal.getTime());


		    
		     
				
				return jcNumber + columnSeperator + fmt.format(sdf.parse(jcDate));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::checkJobCardDetail::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {

			}
		}
		return NO_DATA;
	}

	public String delete_Project_element(String indata) {

		System.out.println("delete_Project_element()");
		String id_seperated = "";
		String[] split_data = indata.split(",");

		System.out.println("split_data.length---> " + split_data.length);

		for (int i = 0; i < split_data.length; i++) {

			System.out.println("id ---->  " + split_data[i]);

			if (i == split_data.length - 1) {
				id_seperated = id_seperated + split_data[i];
			} else {
				id_seperated = id_seperated + split_data[i] + ",";
			}

		}
		System.out.println("id_seperated---->  " + id_seperated);

		try {
			Connection();

			PreparedStatement ps = con
					.prepareStatement("DELETE FROM fourth_dimension.Project_elements "
							+ " WHERE Project_element_id in("
							+ id_seperated
							+ ") ");
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_DELETED;
			}
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return FOREIGN_KEY_CONSTRANIT_FAIL;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_DELETED;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {

			}
		}
		return DATA_NOT_DELETED;
	}

	public String getElementsForProject(String Project_element) {

		System.out.println("ProjectServices.getElementsForProject()");

		String query = "SELECT  brand_name ,element_name ,component_name ,material_name ,"
				+ "quantity ,print_mode_name,set_as_hold,project_name FROM"
				+ " Project_elements p,brand_master b,element_master e,component_master c,material_master m,print_mode_master pm "
				+ " where p.brand_id=b.brand_id and p.element_id=e.element_id and p.component_id=c.component_id and p.material_id=m.material_id and "
				+ " p.print_mode_id=pm.print_mode_id and Project_element_id=? order by element_name ";

		try {
			Connection();

			ps = con.prepareStatement(query);
			ps.setString(1, Project_element);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("OUTDATA:: " + rs.getString(1)
						+ columnSeperator + rs.getString(2) + columnSeperator
						+ rs.getString(3) + columnSeperator + rs.getString(4)
						+ columnSeperator + rs.getInt(5) + columnSeperator
						+ rs.getString(6) + columnSeperator + rs.getString(7)
						+ columnSeperator + rs.getString(8));

				return rs.getString(1) + columnSeperator + rs.getString(2)
						+ columnSeperator + rs.getString(3) + columnSeperator
						+ rs.getString(4) + columnSeperator + rs.getInt(5)
						+ columnSeperator + rs.getString(6) + columnSeperator
						+ rs.getString(7)+ columnSeperator + rs.getString(8);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {

			}
		}
		return NO_DATA;
	}

	public String UpdateProjectElement(String inData, String Project_element_id) {

		System.out.println("ProjectServices.getElementsForProject()");
		System.out.println("project_element_id===>"+Project_element_id);
		String[] split_String = inData.split("@!@");

		for (int i = 0; i < split_String.length; i++) {
			System.out.println("split_String :" + i + " :" + split_String[i]);
			if (split_String[i].equals("") || split_String[i] == null) {
				return DATA_INSUFFICIENT;
			}
		}

		String query = "update fourth_dimension.Project_elements "
				+ " set brand_id=(SELECT brand_id FROM  brand_master where brand_name=?), "
				+ // along with brand name pass client id
				" element_id=(SELECT element_id FROM  element_master where element_name=?), "
				+ "component_id=(SELECT component_id FROM  component_master where component_name=?), "
				+ " material_id=(SELECT material_id FROM  material_master where material_name=?), "
				+ " quantity=?, print_mode_id=(SELECT print_mode_id FROM  print_mode_master where print_mode_name=?), "
				+ " set_as_hold=?,project_name=? where Project_element_id=?";

		try {
			Connection();

			ps = con.prepareStatement(query);
			ps.setString(1, split_String[0]);
			ps.setString(2, split_String[1]);
			ps.setString(3, split_String[2]);
			ps.setString(4, split_String[3]);
			ps.setString(5, split_String[4]);
			ps.setString(6, split_String[5]);
			ps.setString(7, split_String[6]);
			ps.setString(8, split_String[7]);
			ps.setString(9, Project_element_id);
			
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_UPDATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_UPDATED;
		}

		finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {

			}
		}
		return DATA_NOT_UPDATED;

	}

	public String Project_header_data_select(int Project_id) {

		System.out.println("ProjectServices.Project_header_data_select()  "
				+ Project_id);

		/*
		 * SELECT Project_name , Project_number , client_id , division_id ,
		 * start_date , end_date , notes , crm_name , crm_phone , crm_email ,
		 * estimated_budget , billing_narration , status , special_instruction ,
		 * total_stores , client_manager_name,
		 * client_manager_phone,client_manager_email,po_reference, po_date FROM
		 * fourth_dimension . Project ,fourth_dimension.Project_client_details
		 * where Project.Project_id =29 and
		 * Project.Project_id=Project_client_details.Project_id ;
		 */

		try {
			Connection();

			ps = con.prepareStatement(Project_header_data);
			ps.setInt(1, Project_id);
			ResultSet rs = ps.executeQuery();
			
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			
		    Calendar cal=Calendar.getInstance();
			cal.add(Calendar.YEAR, -100);
			formatter.set2DigitYearStart(cal.getTime());
			 
			String startDate;
			String endDate;
			
			String poDate;
			String psDate;
		
		
			
				
			if (rs.next()) {

				
				
				if((rs.getString("start_date")==null)||(rs.getString("start_date").equals("null"))){
				startDate=" ";	
				}
				else{
					startDate=formatter.format(fmt.parse(rs.getString("start_date")));
				}
				
				if((rs.getString("end_date")==null)||(rs.getString("end_date").equals("null"))){
					endDate=" ";	
					}
					else{
						endDate=formatter.format(fmt.parse(rs.getString("end_date")));
					}
				
				if((rs.getString("po_date")==null)||(rs.getString("po_date").equals("null"))){
					poDate=" ";	
					}
					else{
						poDate=formatter.format(fmt.parse(rs.getString("po_date")));
					}
				
				if((rs.getString("project_status_date")==null)||(rs.getString("project_status_date").equals("null"))){
					psDate=" ";	
					}
					else{
						psDate=formatter.format(fmt.parse(rs.getString("project_status_date")));
					}
				
				
				
				System.out.println(rs.getString("Project_name")
						+ columnSeperator+ rs.getString("Project_number")
						+ columnSeperator+ rs.getInt("client_id")
						+ columnSeperator+ rs.getInt("division_id")
						+ columnSeperator+ startDate
						+ columnSeperator+ endDate
						+ columnSeperator+ rs.getString("notes")
						+ columnSeperator+ rs.getString("crm_name")
						+ columnSeperator+ rs.getString("crm_phone")
						+ columnSeperator+ rs.getString("crm_email")
						+ columnSeperator+ rs.getFloat("estimated_budget")
						+ columnSeperator+ rs.getString("billing_narration")
						+ columnSeperator+ rs.getString("special_instruction")
						+ columnSeperator+ rs.getInt("total_stores")
						+ columnSeperator+ rs.getString("client_manager_name")
						+ columnSeperator+ rs.getString("client_manager_phone")
						+ columnSeperator+ rs.getString("client_manager_email")
						+ columnSeperator+ 	rs.getString("po_reference") 
						+ columnSeperator+ poDate
						+ columnSeperator+ rs.getInt("fd_hub_id")
						+ columnSeperator+rs.getFloat("po_amount")
						+ columnSeperator+rs.getString("status")
						+ columnSeperator+psDate
						+ columnSeperator+rs.getInt("approval_mgr_id")
				);

				return rs.getString("Project_name")
				+ columnSeperator+ rs.getString("Project_number")
				+ columnSeperator+ rs.getInt("client_id")
				+ columnSeperator+ rs.getInt("division_id")
				+ columnSeperator+ startDate
				+ columnSeperator+ endDate
				+ columnSeperator+ rs.getString("notes")
				+ columnSeperator+ rs.getString("crm_name")
				+ columnSeperator+ rs.getString("crm_phone")
				+ columnSeperator+ rs.getString("crm_email")
				+ columnSeperator+ rs.getFloat("estimated_budget")
				+ columnSeperator+ rs.getString("billing_narration")
				+ columnSeperator+ rs.getString("special_instruction")
				+ columnSeperator+ rs.getInt("total_stores")
				+ columnSeperator+ rs.getString("client_manager_name")
				+ columnSeperator+ rs.getString("client_manager_phone")
				+ columnSeperator+ rs.getString("client_manager_email")
				+ columnSeperator+ 	rs.getString("po_reference") 
				+ columnSeperator+ poDate
				+ columnSeperator+ rs.getInt("fd_hub_id")
				+ columnSeperator+rs.getFloat("po_amount")
				+ columnSeperator+rs.getString("status")
				+ columnSeperator+psDate
				+ columnSeperator+rs.getInt("approval_mgr_id");
				
			} else {
				return NO_DATA;

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
	}

	public String UpdateProjectHeader(String inData, String inClientData,
			int Project_id) {

		System.out.println("ProjectServices.UpdateProjectHeader()::::::::::"+Project_id);

		String[] split_data = inData.split("!#!");
		for (int i = 0; i < split_data.length; i++) {

			System.out.println("Split data " + i + " --> " + split_data[i]);
			if ((split_data[i] == null) || split_data[i].equals("")) {
				return DATA_INSUFFICIENT;
			}
		}

		String[] split_inClientdata = inClientData.split("!#!");
		for (int i = 0; i < split_inClientdata.length; i++) {

			System.out.println("split_inClientdata " + i + " --> "
					+ split_inClientdata[i]);
			if ((split_inClientdata[i] == null)
					|| split_inClientdata[i].equals("")) {
				return DATA_INSUFFICIENT;
			}
		}
		/*
		 * Project_name , Project_number , client_id , division_id , start_date
		 * , end_date , notes , crm_name , crm_phone , crm_email ,
		 * estimated_budget , billing_narration , special_instruction ,
		 * total_stores
		 */

		String query = " UPDATE  fourth_dimension . Project SET Project_name  = ?,"
				+ " Project_number  = ?, client_id  = ?, division_id  = ?,"
				+ " start_date  = ?, end_date  = ?, notes  = ?, crm_name  = ?, crm_phone  = ?,"
				+ " crm_email  = ?, estimated_budget  = ?, billing_narration  = ?,"
				+ " special_instruction  = ?, total_stores  = ?, status=?, project_status_date=? ,approval_mgr_id=? WHERE Project_id =? ";
		try {
			Connection();
			
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			
			String timeStampDate = null;
			Timestamp timestamp=null;
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.YEAR, -100);
			formatter.set2DigitYearStart(cal.getTime());

			
	
		
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setString(1, split_data[0]);
			ps.setString(2, split_data[1]);
			ps.setString(3, split_data[2]);
			ps.setString(4, split_data[3]);
				
			if (!split_data[4].equalsIgnoreCase("empty")) {
				timeStampDate=fmt.format(formatter.parse(split_data[4]));
				ps.setString(5, timeStampDate);
			} else {
				/*Date dateTime = fmt.parse("1970-01-01 00:00:00");
				timestamp = new Timestamp(dateTime.getTime());*/
				ps.setTimestamp(5, null);	

			}
			
			if (!split_data[5].equalsIgnoreCase("empty")) {
				timeStampDate=fmt.format(formatter.parse(split_data[5]));
				ps.setString(6, timeStampDate);
			} else {
			/*	Date dateTime = fmt.parse("1970-01-01 00:00:00");
				timestamp = new Timestamp(dateTime.getTime());*/
				ps.setTimestamp(6, null);	
				
			}
			
			
			ps.setString(7, split_data[6]);
			ps.setString(8, split_data[7]);
			ps.setString(9, split_data[8]);
			ps.setString(10, split_data[9]);

			// Estimated Budget
			float estimat_budget = 0;
			if (split_data[10].equals("empty")) {

			} else {
				estimat_budget = Float.parseFloat(split_data[10]);
			}
			ps.setFloat(11, estimat_budget);
			ps.setString(12, split_data[11]);
			ps.setString(13, split_data[12]);

			// total Store
			int store_no = 0;
			if (split_data[13].equals("empty")) {

			} else {
				store_no = Integer.parseInt(split_data[13]);
			}

			ps.setInt(14, store_no);
			
			ps.setString(15,split_data[14] );
			
			if (!split_data[15].equalsIgnoreCase("empty")) {
				timeStampDate=fmt.format(formatter.parse(split_data[15]));
				ps.setString(16, timeStampDate);
			} else {
				/*Date dateTime = fmt.parse("1970-01-01 00:00:00");
				timestamp = new Timestamp(dateTime.getTime());*/
				ps.setTimestamp(16, null);	
				
			}
			
			int appMgrId=Integer.parseInt(split_data[16]);
			ps.setInt(17, appMgrId);
			
			ps.setInt(18, Project_id);
			
			
			
			

			status = ps.executeUpdate();
			if (status > 0) {
				PreparedStatement ps_client = con
						.prepareStatement("UPDATE  fourth_dimension . Project_client_details "
								+ " SET  client_manager_name  = ?, client_manager_phone  = ?,client_manager_email  = ?,"
								+ " po_reference  = ?, po_date  = ?, po_amount=? WHERE Project_id =?");

				ps_client.setString(1, split_inClientdata[0]);
				ps_client.setString(2, split_inClientdata[1]);
				ps_client.setString(3, split_inClientdata[2]);
				ps_client.setString(4, split_inClientdata[3]);

				if (!split_inClientdata[4].equalsIgnoreCase("empty")) {
					timeStampDate=fmt.format(formatter.parse(split_inClientdata[4]));
					ps_client.setString(5, timeStampDate);
				} else {
					/*Date dateTime = fmt.parse("1970-01-01 00:00:00");
					timestamp = new Timestamp(dateTime.getTime());*/
					ps_client.setTimestamp(5, null);	
					
				}
				float po_amount=Float.parseFloat(split_inClientdata[5]);
				ps_client.setFloat(6, po_amount);
				ps_client.setInt(7, Project_id);
				int client_state = ps_client.executeUpdate();
				if (client_state > 0) {

					con.setAutoCommit(true);
					return DATA_UPDATED;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return DATA_NOT_UPDATED;
		}

		finally {
			try {

				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();

			} catch (Exception e) {
				log.info("Exception::"+e);
			}
		}
		return DATA_NOT_UPDATED;

	}

	/*
	 * get Jobcard Report by ProjectId.
	 */

	public String getJobCardByProjectForReport(int ProjectId) {
		System.out.println("-----------------------get job cards by Project id::\t" + ProjectId);
		outData = "";
		PreparedStatement psForPrgName = null;
		PreparedStatement pstPrgmEle = null;
		PreparedStatement pstForJobCardStatus = null;
		PreparedStatement pstForprgmStores = null;
		PreparedStatement pstForJobCard = null;
		ResultSet rsForPrgName = null;
		ResultSet rsPrgmEle = null;
		ResultSet rsForPrgmStores = null;
		ResultSet rsForJobCard = null;
		ResultSet rsForJobCards = null;
		/*String strQueryForProjectStores = "SELECT Project_store_id,chain_name,store_name,address,city_name,state_name,fdhub_name,contact_name," +
				" handle_by,measured_on ,region_name,contact_phone,pgm.crm_name,pgm.crm_phone,area_name,measured_by,status.Project_store_status_name,tsi_name,tsi_phone " +
				" FROM fourth_dimension.Project_stores pgs,fourth_dimension.Project pgm,fourth_dimension.Project_stores_status_master status WHERE pgs.Project_id = pgm.Project_id " +
				" AND pgs.Project_id = ?  AND pgs.Project_store_status_id = status.Project_store_status_id";*/
		
		String strQueryForProjectStores = "SELECT ps.Project_store_id,ps.store_name,esdp.region_id,esdp.state_id,esdp.city_id," +
				" ps.address,stStatus.Project_store_status_name, ps.tsi_name,ps.tsi_phone,ps.handle_by,p.crm_phone," +
				" ps.fdhub_name,ps.state_name ,ps.city_name, ps.measured_by,ps.measured_on," +
				" pele.project_name,pele.quantity,pele.Project_element_id FROM fourth_dimension.element_scopes_dateplan esdp, fourth_dimension.Project_stores ps, " +
				" fourth_dimension.Project_elements pele, fourth_dimension.Project_stores_status_master stStatus, " +
				" fourth_dimension.Project p WHERE ps.Project_id = pele.Project_id AND p.Project_id = ps.Project_id " +
				" AND stStatus.Project_store_status_id = ps.Project_store_status_id AND esdp.element_id = pele.Project_element_id " +
				" AND stStatus.Project_store_status_id = ps.Project_store_status_id AND p.Project_id = ?  order by store_name,region_name ";
		
		String strQueryForProjectStores1 = "SELECT ps.Project_store_id,ps.store_name,ps.address, ps.tsi_name,ps.tsi_phone,ps.handle_by,p.crm_phone, " +
				" ps.fdhub_name,ps.state_name ,ps.city_name, ps.measured_by,ps.measured_on, pele.project_name,pele.quantity,pele.Project_element_id,status.store_status_name " +
				" FROM fourth_dimension.Project_stores ps, fourth_dimension.Project_elements pele, fourth_dimension.Project p, " +
				" fourth_dimension.project_store_element_mapping map,fourth_dimension.store_status_master status WHERE ps.Project_id = pele.Project_id AND p.Project_id = ps.Project_id " +
				" AND pele.project_element_id = map.project_element_id AND ps.project_store_id = map.project_store_id " +
				" AND ps.store_status_id = status.store_status_id AND p.Project_id = ? " +
				" order by store_name,region_name";
		
		String strQueryForJobCard = "SELECT * FROM fourth_dimension.job_cards WHERE Project_store_id=? and Project_id=?";
		
//		int city_id = 0;
//		int state_id =0;
		try {
			
			// JobCardReport jobCardReport = new JobCardReport();
			Connection();
			System.out.println("got connection in try block");
			/*String queryPrgmName = "SELECT * FROM fourth_dimension.Project where Project_id =?";
			psForPrgName = con.prepareStatement(queryPrgmName);
			psForPrgName.setInt(1, ProjectId);
			rsForPrgName = psForPrgName.executeQuery();
			while (rsForPrgName.next()) {
				strPrgName = rsForPrgName.getString("Project_name");
				break;
			}*/
			
			int ProjectStoreId=0;
//			String strPrgName = null;
//			int strPrgmEleCount = 0;
			String strJobCardStatus = "";
			int jobCardId=0;
			String strMeasured = "No"; //Yes/No
			String strMeasureOn = "NA";
			String strActualDate_of_Measurement = "";
			String strReasonForNotMeasuring = "";
			String strNoOfVisitsMade = "";
//			String strPermission_received = "";
			String strVisited = "No";
			String strCompleted_by = "";
			String strDamage_Missing_Resent = "NA";
			String strPermission_Recd_date = "NA";
			String strAlternative_outlet_measured = "NA";
			String strTentative_measured_date = "NA";
			String strTentative_implement_date = "NA";
			String strPlannec_impl_date_measurement = "NA";
			String strChain_Name = "NA";
			String strOutlet_Name = "NA";
			String stroutlet_status ="NA";
			String strAddress  = "NA";
			String strLocation_CityName = "NA";
			String strStateName = "NA";
			String strFdhubName = "NA";
			String strTSI_SO_Name = "NA";
			String strArea = "NA";
			String strMeasuredBy = "NA";
			String strMeasuredOn = "NA";
			String strScope_Region_name = "NA";
			String strScope_State_name = "NA";
			String strScope_City_name ="NA";
			String strTSI_SO_phone = "NA";
			String strCRM_Name = "NA";
			String strCRM_Phone = "NA";
			String strFd_Supervisor = "NA";
			String strCompleted = "NA";
			String strJobCardNo = "NA";
			String strJC_Date = "NA";
			String strElement_name ="NA";
			String strElementQty ="NA";
			String sentForApprovalStatus = "NA";
			String artworkcomplete_Status = "NA";
			String readyForDispatch = "NA";
			String materialDispatchedStatus = "NA";
			String materialReceivedStatus = "NA";
			String jcPermissionReceivedStatus = "NA";
			String ImplementByStatus = "NA";  // Not  Clear who is responsible for this implementation
			String Alligned_to_measure = "NA";
			String implementedOnStatus = "NA";
			String billedStatus = "NA";
			String strVisited_with_Material = "NA";
//			String strImplemented = "";
			String strNo_Permission = "NA";
			String strCancelled = "NA";
//			String strImplemented_by = "";
			String strPhotos = "NA";
			String strDC_imple_sheet = "NA";
			String strPhotos_DC_Sent = "NA";
			String strRemarks = "NA";
			String strPhotos_DC_Recvd = "NA";
			String strStore_state_name = "NA";
			String strStore_city_name = "NA";
			String strMSheetReceived = "NA";
			String strCurrentMeasurementStatus ="NA";
			String strRe_Measured_date ="NA";
			String strAW_Sent_Approval_date = "NA"; 
			String strAW_Approved_date = "NA";
			String strAW_Received_date = "NA";
			
			String strPlanned_dispatch_start_date = "NA";
			String strPlanned_dispatch_end_date = "NA";
			String strActual_dispatch_start_date = "NA";
			String strActual_dispatch_end_date = "NA";
			
			String strJcStore_imple_date = "NA";
			String strImpl_Sheet_DC_sent = "NA";
			int Project_Element_Id = 0;
			int region_id =0;
			
			pstForprgmStores = con.prepareStatement(strQueryForProjectStores1);
			pstForprgmStores.setInt(1, ProjectId);
			System.out.println("ProjectStores PreparedStatement \t" + pstForprgmStores );
			rsForPrgmStores = pstForprgmStores.executeQuery();
			while(rsForPrgmStores.next()){
				ProjectStoreId = rsForPrgmStores.getInt(1);
				strOutlet_Name = rsForPrgmStores.getString(2);
				if(rsForPrgmStores.getString("address") != null){
					strAddress = rsForPrgmStores.getString("address");
				}else{
					strAddress = "NA";
				}
				
				strMSheetReceived = getStatusBymeasurement(ProjectStoreId,"",MeasurementReportConstants.MSHEET_RECEIVED);
				strMeasured = getStatusBymeasurement(ProjectStoreId,"",MeasurementReportConstants.MEASURED);
				strMeasureOn = getStatusBymeasurement(ProjectStoreId,"measured_date",MeasurementReportConstants.MEASURED);
				strActualDate_of_Measurement = getStatusBymeasurement(ProjectStoreId,"Actual", MeasurementReportConstants.MEASURED);
//							strMeasureOn = getStatusBymeasurement(prgmStoreId, MeasurementReportConstants.MEASURED);
				strPermission_Recd_date = getStatusBymeasurement(ProjectStoreId,MeasurementReportConstants.PERMISSION);

				System.out.println("strPermission_Recd_Date" + strPermission_Recd_date);
				System.out.println("After Call" + strMeasured);
				strReasonForNotMeasuring = getStatusBymeasurement(ProjectStoreId, MeasurementReportConstants.REASON_FOR_NOT_MEASURING);
				strRe_Measured_date = getStatusBymeasurement(ProjectStoreId, "", MeasurementReportConstants.REVISITED_AND_RE_MEASURED);
				System.out.println("Re_Measured by 9945854646411\t"+ strRe_Measured_date);
				strMeasuredBy = getStatusBymeasurement(ProjectStoreId, "measured_by", MeasurementReportConstants.MEASURED);
				if(strMeasuredBy == "" || strMeasuredBy.equalsIgnoreCase("empty")){
					strMeasuredBy = "NA";
				}
				System.out.println("Re_Measured by 9945854646411\t"+ strRe_Measured_date);
				strCurrentMeasurementStatus = getCurrentMeasurementStatus(ProjectStoreId);
				strNoOfVisitsMade = getStatusBymeasurement(ProjectStoreId, MeasurementReportConstants.NO_OF_VISITS_MADE);
				if(strNoOfVisitsMade != ""){
					int temp = Integer.parseInt(strNoOfVisitsMade);
					if(temp>0){
						strVisited = "Yes";
					}
				}
				strAlternative_outlet_measured = getStatusBymeasurement(ProjectStoreId, "", MeasurementReportConstants.ALTERNATIVE_STORE_MEASURED);
				Project_Element_Id = rsForPrgmStores.getInt("Project_element_id");
				
//				strFdhubName = rsForPrgmStores.getString("fdhub_name");
				if(rsForPrgmStores.getString("fdhub_name") != null){
					strFdhubName = rsForPrgmStores.getString("fdhub_name");
				}
				strTSI_SO_Name = rsForPrgmStores.getString("tsi_name");
				if(strTSI_SO_Name == null || strTSI_SO_Name == ""){
					strTSI_SO_Name = "NA";
				}
//				strMeasuredBy = rsForPrgmStores.getString("measured_by");
				strMeasuredOn = rsForPrgmStores.getString("measured_on");

				if(strScope_Region_name == null)	strScope_Region_name = "NA";
					
				strTSI_SO_phone = rsForPrgmStores.getString("tsi_phone");
				if(strTSI_SO_phone == null) {strTSI_SO_phone = "NA";}
//						strCRM_Name = rsForPrgmStores.getString("crm_name");
				strCRM_Phone = rsForPrgmStores.getString("crm_phone");
				/*strArea = rsForPrgmStores.getString("area_name");
				if(strArea == null || strArea == ""){
					strArea = "NA";
				}*/
				/*if(!rsForPrgmStores.getString("handle_by").equals(null) || !rsForPrgmStores.getString("handle_by").equals("null") || 
						!rsForPrgmStores.getString("handle_by").equals("empty") || rsForPrgmStores.getString("handle_by") != "empty"){*/
					strFd_Supervisor = rsForPrgmStores.getString("handle_by");  // Fd Supervisor
					if(strFd_Supervisor.equals("empty") || strFd_Supervisor.equals("null") || strFd_Supervisor.equals(null)){
						strFd_Supervisor = "NA";
					}
					System.out.println("FD_Supervisor is ::\t" + strFd_Supervisor);
//				}
				strStore_state_name = rsForPrgmStores.getString("state_name");
				strStore_city_name = rsForPrgmStores.getString("city_name");
				strLocation_CityName = rsForPrgmStores.getString("city_name");
				System.out.println("Store city name is\t 9945858353\t" + strStore_city_name);
				strElement_name = rsForPrgmStores.getString("project_name");
				strElementQty = rsForPrgmStores.getString("quantity");
				stroutlet_status = rsForPrgmStores.getString("store_status_name");
				
				System.out.println("MeasureOn Value is\t" + strMeasureOn);
				if (strMeasuredBy == "" || strMeasuredBy == null) {
					strMeasuredBy = "NA";
				}
				if (strMeasuredOn == "" || strMeasuredOn == null) {
					strMeasuredOn = "NA";
				}
				
				PreparedStatement pstForTentMesuredDate = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
						"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
						" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state" +
						" where sd.element_id = ?  AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
				pstForTentMesuredDate.setInt(1, Project_Element_Id);
				pstForTentMesuredDate.setString(2, MeasurementReportConstants.TENTATIVE_MEASURED);
				ResultSet rsForTentMeasuredDate = pstForTentMesuredDate.executeQuery();
				while(rsForTentMeasuredDate.next()){
					if(strStore_city_name.equalsIgnoreCase(rsForTentMeasuredDate.getString("city_name"))){
						strLocation_CityName = rsForTentMeasuredDate.getString("city_name");
						strStateName = rsForTentMeasuredDate.getString("state_name");
						strTentative_measured_date = rsForTentMeasuredDate.getString("client_national_target_date");
						if(strTentative_measured_date != "NA"){
							strTentative_measured_date = changeDateFormat(strTentative_measured_date);
							break;
						}
					}else{
						
						strStateName = rsForTentMeasuredDate.getString("state_name");
//						strLocation_CityName = "NA";
						strTentative_measured_date = rsForTentMeasuredDate.getString("client_national_target_date");
						if(strTentative_measured_date != "NA"){
							strTentative_measured_date = changeDateFormat(strTentative_measured_date);
							break;
						}
					}
				}
//				strTentative_measured_date = getStatusForClientAndFD(ProjectId,"client_Targer_Date", MeasurementReportConstants.TENTATIVE_MEASURED);
//				strTentative_implement_date = getStatusForClientAndFD(ProjectId,"client_Target_Date", MeasurementReportConstants.IMPLEMENTATION);
				PreparedStatement pstForTentImplementDate = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
						"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
						" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state where sd.element_id = ?" +
						" AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
				pstForTentImplementDate.setInt(1, Project_Element_Id);
				pstForTentImplementDate.setString(2, MeasurementReportConstants.IMPLEMENTATION);
				ResultSet rsForTentImplementDate = pstForTentImplementDate.executeQuery();
				if(rsForTentImplementDate.next()){
					if(strStore_city_name.equalsIgnoreCase(rsForTentImplementDate.getString("city_name"))){
						strLocation_CityName = rsForTentImplementDate.getString("city_name");
						strTentative_implement_date = rsForTentImplementDate.getString("client_national_target_date");
						if(strTentative_implement_date != "NA"){
							strTentative_implement_date = changeDateFormat(strTentative_implement_date);
						}
					}else{
						
						if(strStateName != "NA"){
							strStateName = rsForTentImplementDate.getString("state_name");
						}
						strLocation_CityName = "NA";
						strTentative_implement_date = rsForTentImplementDate.getString("client_national_target_date");
						if(strTentative_implement_date != "NA"){
							strTentative_implement_date = changeDateFormat(strTentative_implement_date);
						}
					}
				}
//				strPlannec_impl_date_measurement = getStatusForClientAndFD(ProjectId,"FD_Target_Date", MeasurementReportConstants.IMPLEMENTATION);
				PreparedStatement pstForTentImplementDateMeasurement = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
						"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
						" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state where sd.element_id = ?" +
						" AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
				pstForTentImplementDateMeasurement.setInt(1, Project_Element_Id);
				pstForTentImplementDateMeasurement.setString(2, MeasurementReportConstants.IMPLEMENTATION);
				ResultSet rsForTentImplementDateMeasurement = pstForTentImplementDateMeasurement.executeQuery();
				while(rsForTentImplementDateMeasurement.next()){
					if(strStore_city_name.equalsIgnoreCase(rsForTentImplementDateMeasurement.getString("city_name"))){
						strLocation_CityName = rsForTentImplementDateMeasurement.getString("city_name");
						strPlannec_impl_date_measurement = rsForTentImplementDateMeasurement.getString("client_national_target_date");
						if(strPlannec_impl_date_measurement != "NA"){
							strPlannec_impl_date_measurement = changeDateFormat(strPlannec_impl_date_measurement);
						}
					}else{
						
						if(strStateName != "NA"){
							strStateName = rsForTentImplementDateMeasurement.getString("state_name");
						}
						strLocation_CityName = "NA";
						strPlannec_impl_date_measurement = rsForTentImplementDateMeasurement.getString("client_national_target_date");
						if(strPlannec_impl_date_measurement != "NA"){
							strPlannec_impl_date_measurement = changeDateFormat(strPlannec_impl_date_measurement);
						}
					}
				}
				/*PreparedStatement pstForScopeDetails = con.prepareStatement("SELECT esdp.region_id,esdp.state_id,esdp.city_id," +
						" stStatus.Project_store_status_name FROM fourth_dimension.element_scopes_dateplan esdp, fourth_dimension.Project_stores ps, " +
						" fourth_dimension.Project_elements pele, fourth_dimension.Project_stores_status_master stStatus, fourth_dimension.Project p " +
						" WHERE ps.Project_id = pele.Project_id AND p.Project_id = ps.Project_id  AND stStatus.Project_store_status_id = ps.store_status_id " +
						" AND esdp.element_id = pele.Project_element_id AND pele.project_element_id = ?");*/
				PreparedStatement pstForScopeDetails = con.prepareStatement("SELECT esdp.region_id,esdp.state_id,esdp.city_id" +
						" FROM fourth_dimension.element_scopes_dateplan esdp, fourth_dimension.Project_stores ps, " +
						" fourth_dimension.Project_elements pele, fourth_dimension.Project p " +
						" WHERE ps.Project_id = pele.Project_id AND p.Project_id = ps.Project_id  " +
						" AND esdp.element_id = pele.Project_element_id AND pele.project_element_id = ?");
				pstForScopeDetails.setInt(1, Project_Element_Id);
				ResultSet rsForScopeDetails = pstForScopeDetails.executeQuery();
				while(rsForScopeDetails.next())
				{
//					stroutlet_status = rsForScopeDetails.getString("Project_store_status_name");
					region_id = rsForScopeDetails.getInt("region_id");
					PreparedStatement pstForRegionName = con.prepareStatement("SELECT region_name FROM fourth_dimension.region_master WHERE region_id = ?");
					pstForRegionName.setInt(1, region_id);
					ResultSet rsForRegionName = pstForRegionName.executeQuery();
					while(rsForRegionName.next()){
						strScope_Region_name = rsForRegionName.getString("region_name");
					}
				}
				System.out.println("Project_Element_Id-------------------------"+Project_Element_Id);
//				strPlanned_dispatch_start_date = getStatusForClientAndFD(ProjectId, "target_start_date", ScopeReportConstants.DISPATCH);
				/*PreparedStatement pstForPlanned_dispatch_start_date = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
						"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
						" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state where sd.element_id = ?" +
						" AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");*/
				PreparedStatement pstForPlanned_dispatch_start_date = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date, " +
						" client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id " +
						" FROM fourth_dimension.element_scopes_dateplan sd, fourth_dimension.project_elements pele, fourth_dimension.scope_master sm , " +
						" fourth_dimension.city_master city, fourth_dimension.state_master state where sd.city_id = city.city_id AND pele.project_element_id = sd.element_id " +
						" AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id AND sm.scope_name = ? AND pele.project_element_id = ? order by id desc");
				pstForPlanned_dispatch_start_date.setString(1, MeasurementReportConstants.DISPATCH);
				pstForPlanned_dispatch_start_date.setInt(2,Project_Element_Id );
				System.out.println("Before pstForPlanned_dispatch_start_date " + pstForPlanned_dispatch_start_date);
				ResultSet rsForPlanned_dispatch_start_date = pstForPlanned_dispatch_start_date.executeQuery();
				while(rsForPlanned_dispatch_start_date.next()){
					if(strStore_city_name.equalsIgnoreCase(rsForPlanned_dispatch_start_date.getString("city_name"))){
						strLocation_CityName = rsForPlanned_dispatch_start_date.getString("city_name");
						strPlanned_dispatch_start_date = rsForPlanned_dispatch_start_date.getString("fd_national_actual_date");
						if(strPlanned_dispatch_start_date != "NA"){
							strPlanned_dispatch_start_date = changeDateFormat(strPlanned_dispatch_start_date);
						}
					}else{
						
						if(strStateName != "NA"){
							strStateName = rsForPlanned_dispatch_start_date.getString("state_name");
						}
						strLocation_CityName = "NA";
						strPlanned_dispatch_start_date = rsForPlanned_dispatch_start_date.getString("fd_national_actual_date");
						if(strPlanned_dispatch_start_date != "NA"){
							strPlanned_dispatch_start_date = changeDateFormat(strPlanned_dispatch_start_date);
						}
					}
					System.out.println("strPlanned_dispatch_start_date" +  strPlanned_dispatch_start_date);
				}
				
				PreparedStatement pstForPlanned_dispatch_end_date = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
						"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
						" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state where sd.element_id = ?" +
						" AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
				pstForPlanned_dispatch_end_date.setInt(1, Project_Element_Id);
				pstForPlanned_dispatch_end_date.setString(2, MeasurementReportConstants.DISPATCH);
				ResultSet rsForPlanned_dispatch_end_date = pstForPlanned_dispatch_end_date.executeQuery();
				while(rsForPlanned_dispatch_end_date.next()){
					if(strStore_city_name.equalsIgnoreCase(rsForPlanned_dispatch_end_date.getString("city_name"))){
						strLocation_CityName = rsForPlanned_dispatch_end_date.getString("city_name");
						strPlanned_dispatch_end_date = rsForPlanned_dispatch_end_date.getString("fd_national_target_date");
						if(strPlanned_dispatch_end_date != "NA"){
							strPlanned_dispatch_end_date = changeDateFormat(strPlanned_dispatch_end_date);
						}
					}else{
						
						if(strStateName != "NA"){
							strStateName = rsForPlanned_dispatch_end_date.getString("state_name");
						}
						strLocation_CityName = "NA";
						strPlanned_dispatch_end_date = rsForPlanned_dispatch_end_date.getString("fd_national_target_date");
						if(strPlanned_dispatch_end_date != "NA"){
							strPlanned_dispatch_end_date = changeDateFormat(strPlanned_dispatch_end_date);
						}
					}
				}
//				strActual_dispatch_start_date = getStatusForClientAndFD(ProjectId, "target_start_date", ScopeReportConstants.DISPATCH);
				PreparedStatement pstForActual_dispatch_start_date = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
						"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
						" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state where sd.element_id = ?" +
						" AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
				pstForActual_dispatch_start_date.setInt(1, Project_Element_Id);
				pstForActual_dispatch_start_date.setString(2, MeasurementReportConstants.DISPATCH);
				ResultSet rsForActual_dispatch_start_date = pstForActual_dispatch_start_date.executeQuery();
				while(rsForActual_dispatch_start_date.next()){
					if(strStore_city_name.equalsIgnoreCase(rsForActual_dispatch_start_date.getString("city_name"))){
						strLocation_CityName = rsForActual_dispatch_start_date.getString("city_name");
						strActual_dispatch_start_date = rsForActual_dispatch_start_date.getString("fd_national_actual_date");
						if(strActual_dispatch_start_date != "NA"){
							strActual_dispatch_start_date = changeDateFormat(strActual_dispatch_start_date);
						}
					}else{
						
						if(strStateName != "NA"){
							strStateName = rsForActual_dispatch_start_date.getString("state_name");
						}
						strLocation_CityName = "NA";
						strActual_dispatch_start_date = rsForActual_dispatch_start_date.getString("fd_national_actual_date");
						if(strActual_dispatch_start_date != "NA"){
							strActual_dispatch_start_date = changeDateFormat(strActual_dispatch_start_date);
						}
					}
				}
//				strActual_dispatch_end_date = getStatusForClientAndFD(ProjectId, "target_start_date", ScopeReportConstants.DISPATCH);
				PreparedStatement pstForActual_dispatch_end_date = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
						"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
						" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state where sd.element_id = ?" +
						" AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
				pstForActual_dispatch_end_date.setInt(1, Project_Element_Id);
				pstForActual_dispatch_end_date.setString(2, MeasurementReportConstants.DISPATCH);
				ResultSet rsForActual_dispatch_end_date = pstForActual_dispatch_end_date.executeQuery();
				while(rsForActual_dispatch_end_date.next()){
					if(strStore_city_name.equalsIgnoreCase(rsForActual_dispatch_end_date.getString("city_name"))){
						strLocation_CityName = rsForActual_dispatch_end_date.getString("city_name");
						strActual_dispatch_end_date = rsForActual_dispatch_end_date.getString("fd_national_target_date");
						if(strActual_dispatch_end_date != "NA"){
							strActual_dispatch_end_date = changeDateFormat(strActual_dispatch_end_date);
						}
					}else{
						
						if(strStateName != "NA"){
							strStateName = rsForActual_dispatch_end_date.getString("state_name");
						}
						strLocation_CityName = "NA";
						strActual_dispatch_end_date = rsForActual_dispatch_end_date.getString("fd_national_target_date");
						if(strActual_dispatch_end_date != "NA"){
							strActual_dispatch_end_date = changeDateFormat(strActual_dispatch_end_date);
						}
					}
				}
				
				/*city_id = rsForPrgmStores.getInt("city_id");
				PreparedStatement pstForCityName = con.prepareStatement("SELECT city_name FROM fourth_dimension.city_master WHERE city_id = ?");
				pstForCityName.setInt(1, city_id);
				ResultSet rsForCityName = pstForCityName.executeQuery();
				while(rsForCityName.next()){
					strScope_City_name = rsForCityName.getString("city_name");
				}
				state_id = rsForPrgmStores.getInt("state_id");
				PreparedStatement pstForStateName = con.prepareStatement("SELECT state_name FROM fourth_dimension.state_master WHERE state_id = ?");
				pstForStateName.setInt(1, state_id);
				ResultSet rsForStateName = pstForStateName.executeQuery();
				while(rsForStateName.next()){
					strScope_State_name = rsForStateName.getString("state_name");
				}*/
				
//				strLocation_CityName = rsForPrgmStores.getString("city_name");
//				strStateName = rsForPrgmStores.getString("state_name");
				
			
				System.out.println("Before Job Card query Execution");
				pstForJobCard = con.prepareStatement(strQueryForJobCard);
				pstForJobCard.setInt(1, ProjectStoreId);
				pstForJobCard.setInt(2, ProjectId);
				rsForJobCards = pstForJobCard.executeQuery();
				if(rsForJobCards.next()){
//					String strJobCardNo = "";
					jobCardId = rsForJobCards.getInt(1);
					strJobCardNo = rsForJobCards.getString("job_card_number");
					strJC_Date = rsForJobCards.getString("job_card_date");
					if(strJC_Date != "NA" || strJC_Date != ""){
						strJC_Date = changeDateFormat(strJC_Date);
					}
					System.out.println("Job Card is*** " + jobCardId);
					System.out.println("Job Card Number is*** " + strJobCardNo + "\tAnd Date:\t" + strJC_Date);
					// JobCardReport.getStatusValues(jobCardId);
					sentForApprovalStatus = getStatus(jobCardId,"",JobCardReportConstants.SUP_IMP_SENT_FOR_APPROVAL);
					strAW_Sent_Approval_date = getStatus(jobCardId,"", JobCardReportConstants.ARTWORK_SENT_FOR_APPROVAL);
					strAW_Approved_date = getStatus(jobCardId,"", JobCardReportConstants.ARTWORK_APPROVED);
					strAW_Received_date = getStatus(jobCardId, "",JobCardReportConstants.ARTWORK_RECEIVED);
					strJcStore_imple_date = getStatus(jobCardId,"", JobCardReportConstants.IMPLEMENTED);
					System.out.println("Approval Status *****************"+ sentForApprovalStatus);
					artworkcomplete_Status = getStatus(jobCardId,"",JobCardReportConstants.ARTWORK_COMPLETE);
					readyForDispatch = getStatus(jobCardId,"",JobCardReportConstants.READY_FOR_DESPATCH);
					materialDispatchedStatus = getStatus(jobCardId, "",	JobCardReportConstants.MATERIAL_DESPATCHED);
					materialReceivedStatus = getStatus(jobCardId, "", JobCardReportConstants.MATERIAL_RECEIVED);
					jcPermissionReceivedStatus = getStatus(jobCardId, "", JobCardReportConstants.PERMISSION_RECEIVED);
					ImplementByStatus = getStatus(jobCardId, "comments",  JobCardReportConstants.IMPLEMENTED);  // Not  Clear who is responsible for this implementation
					Alligned_to_measure = getStatus(jobCardId,"", JobCardReportConstants.ALLIGNED_TO_MEASURE);
					System.out.println("Alligned to measure:\t" + Alligned_to_measure);
					implementedOnStatus = getStatus(jobCardId, "", JobCardReportConstants.ELEMENTS_IMPLEMENTED);
					billedStatus = getStatus(jobCardId, "",JobCardReportConstants.BILLED);
					for(int st=0;st<JobCardReportConstants.DAMAGE_MISSING_RESENT.length;st++) {
						 String strTemp = getStatus(jobCardId, "", JobCardReportConstants.DAMAGE_MISSING_RESENT[st]);
						 if(strTemp != "NA"){
							 strDamage_Missing_Resent = strTemp;
						 }
					}
					
//							String strDamage_Missing_Resent = getStatus(jobCardId, JobCardReportConstants.DAMAGE_MISSING_RESENT);
					strVisited_with_Material = getStatus(jobCardId,"", JobCardReportConstants.VISITEDWITHMATERIAL);
//							String strImplemented = "";
					strNo_Permission = getStatus(jobCardId,"", JobCardReportConstants.NOPERMISSION);;
					strCancelled = getStatus(jobCardId, "" , JobCardReportConstants.CANCELLED);
//							String strImplemented_by = "";
					strPhotos = getStatus(jobCardId,"",  JobCardReportConstants.PHOTOS);
//					strDC_imple_sheet = getStatus(jobCardId,"", JobCardReportConstants.DC_IMPLEMENTATION);
					strPhotos_DC_Sent = getStatus(jobCardId,"", JobCardReportConstants.PHOTOS_DC_SENT);
					strRemarks = getStatus(jobCardId,"", JobCardReportConstants.REMARKS);
					strPhotos_DC_Recvd = getStatus(jobCardId,"", JobCardReportConstants.PHOTOS_DC_RECEIVED);
					strImpl_Sheet_DC_sent = getStatus(jobCardId, "", JobCardReportConstants.IMPLEMENTATION_SHEET_DC_SENT);

					String queryJobCardStatus = "SELECT jsmaster.jobcard_status_name,jcstatus.date FROM fourth_dimension.job_cards jc,fourth_dimension.jobcard_status jcstatus, "
							+ "fourth_dimension.jobcard_status_master jsmaster WHERE jc.job_card_id = jcstatus.jobcard_id "
//									+ "AND jcstatus.jobcard_status = jsmaster.jobcard_status_id AND jcstatus.jobcard_id =? order by jcstatus.dc_date desc";
							+ "AND jcstatus.jobcard_status = jsmaster.jobcard_status_id AND jcstatus.jobcard_id =? order by jcstatus.date desc";
					pstForJobCardStatus = con.prepareStatement(queryJobCardStatus);
					pstForJobCardStatus.setInt(1, jobCardId);
					System.out.println("job card id is---> " + jobCardId);
					ResultSet rsForJobCardStaus = pstForJobCardStatus
							.executeQuery();
					while (rsForJobCardStaus.next()) {
						strJobCardStatus = rsForJobCardStaus.getString(1);
						break;
					}
					if (strJobCardStatus == "") {
						strJobCardStatus = "NA";
					}
					System.out.println("-----------Job Card status:::"
							+ strJobCardStatus);
//					strMSheetReceived = getStatus(jobCardId,"", JobCardReportConstants.MSHEET_RECEIVED);
					System.out.println("Mshet Received value:\t" + strMSheetReceived);
				
						outData = outData + data + strScope_Region_name
						+ columnSeperator + strStateName
						+ columnSeperator + strLocation_CityName
						+ columnSeperator + strOutlet_Name + "\n" + strAddress
						+ columnSeperator + stroutlet_status
						+ columnSeperator + strTSI_SO_Name
						+ columnSeperator + strTSI_SO_phone
						+ columnSeperator + strFd_Supervisor
						+ columnSeperator + strTSI_SO_phone
						+ columnSeperator + strFdhubName
						+ columnSeperator + strMSheetReceived
						+ columnSeperator + strJobCardNo
						+ columnSeperator + strJC_Date
						+ columnSeperator + strElement_name
						+ columnSeperator + strElementQty
						+ columnSeperator + strTentative_measured_date //strTentative_measured_date or Alligned_to_measure
						+ columnSeperator + strTentative_implement_date
						+ columnSeperator + strPlannec_impl_date_measurement
						+ columnSeperator + strNoOfVisitsMade
						+ columnSeperator + strReasonForNotMeasuring
						+ columnSeperator + strCurrentMeasurementStatus
						+ columnSeperator + strActualDate_of_Measurement
						+ columnSeperator + strRe_Measured_date
						+ columnSeperator + strMeasuredBy
						+ columnSeperator + strAW_Sent_Approval_date 
						+ columnSeperator + strAW_Approved_date
						+ columnSeperator + strAW_Received_date
						+ columnSeperator + strPlanned_dispatch_start_date
						+ columnSeperator + strPlanned_dispatch_end_date
						+ columnSeperator + strActual_dispatch_start_date
						+ columnSeperator + strActual_dispatch_end_date
						+ columnSeperator + strDamage_Missing_Resent
						+ columnSeperator + materialReceivedStatus 	//material_received_date
						+ columnSeperator + jcPermissionReceivedStatus //permission_received_date
						+ columnSeperator + strVisited_with_Material
						+ columnSeperator + strJcStore_imple_date
						+ columnSeperator + strCancelled
						+ columnSeperator + ImplementByStatus		//Implementated by status -Implemented_by
						+ columnSeperator + strImpl_Sheet_DC_sent
						+ columnSeperator + strPhotos_DC_Sent
						+ columnSeperator + strPhotos_DC_Recvd
						+ columnSeperator + billedStatus + rowSeperator;
						System.out.println("outdata from if block:\t" + outData);
					}else{
						
						strJobCardNo = "NA";
						strJC_Date = "NA";
//						strMSheetReceived = "NA";
						strDamage_Missing_Resent = "NA";
						sentForApprovalStatus = "NA";
						artworkcomplete_Status = "NA";
						readyForDispatch = "NA";
						materialDispatchedStatus = "NA";
						strAW_Sent_Approval_date = "NA";
						strAW_Approved_date = "NA";
						strAW_Received_date = "NA";
						materialReceivedStatus = "NA";
//						strRe_Measured_date ="NA";
						strJcStore_imple_date = "NA";
						jcPermissionReceivedStatus = "NA";
						ImplementByStatus = "NA";  // Not  Clear who is responsible for this implementation
						Alligned_to_measure = "NA";
						implementedOnStatus = "NA";
						billedStatus = "NA";
						strVisited_with_Material = "NA";
						strNo_Permission = "NA";
						strCancelled = "NA";
						strPhotos = "NA";
						strPhotos_DC_Sent = "NA";
						strImpl_Sheet_DC_sent ="NA";
						strRemarks = "NA";
						strPhotos_DC_Recvd = "NA";
						billedStatus = "NA";
						System.out.println("No job card assigned for Project_store_id" + ProjectStoreId);
						outData = outData + data + strScope_Region_name
						+ columnSeperator + strStateName
						+ columnSeperator + strLocation_CityName
						+ columnSeperator + strOutlet_Name + "\n" + strAddress
						+ columnSeperator + stroutlet_status
						+ columnSeperator + strTSI_SO_Name
						+ columnSeperator + strTSI_SO_phone
						+ columnSeperator + strFd_Supervisor
						+ columnSeperator + strTSI_SO_phone
						+ columnSeperator + strFdhubName
						+ columnSeperator + strMSheetReceived
						+ columnSeperator + strJobCardNo
						+ columnSeperator + strJC_Date
						+ columnSeperator + strElement_name
						+ columnSeperator + strElementQty
						+ columnSeperator + strTentative_measured_date //strTentative_measured_date or Alligned_to_measure
						+ columnSeperator + strTentative_implement_date
						+ columnSeperator + strPlannec_impl_date_measurement
						+ columnSeperator + strNoOfVisitsMade
						+ columnSeperator + strReasonForNotMeasuring
						+ columnSeperator + strCurrentMeasurementStatus
						+ columnSeperator + strActualDate_of_Measurement
						+ columnSeperator + strRe_Measured_date
						+ columnSeperator + strMeasuredBy
						+ columnSeperator + strAW_Sent_Approval_date 
						+ columnSeperator + strAW_Approved_date
						+ columnSeperator + strAW_Received_date
						+ columnSeperator + strPlanned_dispatch_start_date
						+ columnSeperator + strPlanned_dispatch_end_date
						+ columnSeperator + strActual_dispatch_start_date
						+ columnSeperator + strActual_dispatch_end_date
						+ columnSeperator + strDamage_Missing_Resent
						+ columnSeperator + materialReceivedStatus 	//material_received_date
						+ columnSeperator + jcPermissionReceivedStatus //permission_received_date
						+ columnSeperator + strVisited_with_Material
						+ columnSeperator + strJcStore_imple_date
						+ columnSeperator + strCancelled
						+ columnSeperator + ImplementByStatus		//Implementated by status -Implemented_by
						+ columnSeperator + strImpl_Sheet_DC_sent
						+ columnSeperator + strPhotos_DC_Sent
						+ columnSeperator + strPhotos_DC_Recvd
						+ columnSeperator + billedStatus + rowSeperator;
					}
				}
			
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (rsForPrgName != null) {
					rsForPrgName.close();
				}
				if (rsForJobCards != null) {
					rsForJobCards.close();
				}
				if (rsForPrgmStores != null) {
					rsForPrgmStores.close();
				}
				if (psForPrgName != null) {
					psForPrgName.close();
				}
				if (pstForJobCardStatus != null) {
					pstForJobCardStatus.close();
				}
				if (pstForprgmStores != null) {
					pstForprgmStores.close();
				}
				if (pstPrgmEle != null) {
					pstPrgmEle.close();
				}

			} catch (SQLException sql) {
				log.info("Exception::"+sql);
				sql.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String getStatus(int jobCardId,String by, String statusName) {
		System.out.println("getStatus(" + jobCardId + "," + statusName + ")");
		// System.out.println(con);
		String responseAsDate = "";
		String Comments = "NA";
		Connection();
		PreparedStatement pstForJobCardStatus = null;
		ResultSet rsForJobCardStatus = null;
		try {

			String queryJobCardStatus = "SELECT jcstatus.date,comments FROM fourth_dimension.jobcard_status jcstatus, "
					+ "fourth_dimension.jobcard_status_master jsmaster WHERE  jcstatus.jobcard_status = jsmaster.jobcard_status_id "
					+ "AND jcstatus.jobcard_id = ? AND jsmaster.jobcard_status_name = ? order by jcstatus.dc_date desc";
			pstForJobCardStatus = con.prepareStatement(queryJobCardStatus);
			pstForJobCardStatus.setInt(1, jobCardId);
			pstForJobCardStatus.setString(2, statusName);
			System.out.println("job card id is---> " + jobCardId);
			System.out.println("Status Name ---> " + statusName);
			rsForJobCardStatus = pstForJobCardStatus.executeQuery();
			while (rsForJobCardStatus.next()) {
				if (rsForJobCardStatus.getString(1) != null || rsForJobCardStatus.getString(1) != "") {
					// responseAsDate =
					// (rsForJobCardStatus.getString(1)).substring(0, 10);
					responseAsDate = rsForJobCardStatus.getString(1);
					System.out.println("Status is *************"
							+ responseAsDate);
					break;
				}
				if(rsForJobCardStatus.getString(2) != null || rsForJobCardStatus.getString(2) != ""){
					Comments = rsForJobCardStatus.getString(2);
				}
			}
			if (responseAsDate == null || responseAsDate == "") {
				responseAsDate = "NA";
				System.out
						.println("respose from status was null,so value set to NA");
			} else {

				responseAsDate = changeDateFormat(responseAsDate);
				System.out.println(" Converted Date is........"
						+ responseAsDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			log.info("Exception::"+e);
		} catch (Exception e) {
			log.info("Exception::"+e);
			e.printStackTrace();
		} 
			
		if(by.equalsIgnoreCase("comments")){
			System.out.println("User Comments :\t" + Comments);
			return Comments;
		}else{
			return responseAsDate;
		}
	}

	public String getStatusBymeasurement(int ProjectStoreId,String status, String statusName) {
		System.out.println("getStatusBymeasurement(" + ProjectStoreId + "," + statusName + ")");
		// System.out.println(con);
		String strMeasurementStatus = "NO";  // yes/no
		String strMeasurementStatusDate = "NA";
		String strMeasured_By="NA";
		Connection();
		PreparedStatement pstForMeasurementStatus = null;
		ResultSet rsForMeasurementStatus = null;
		try {
			
			String queryJobCardStatus = "SELECT stM.measurement_status_name,st.status_date,st.measured_by FROM fourth_dimension.measurement_status st, " +
					"fourth_dimension.measurement_status_master stM,fourth_dimension.Project_stores pgmSt WHERE " +
					"st.measurement_status = stM.measurement_status_id AND pgmSt.Project_store_id = st.Project_store_id " +
					"AND stM.measurement_status_name =? AND pgmSt.Project_store_id =? order by status_date desc"; 
			pstForMeasurementStatus = con.prepareStatement(queryJobCardStatus);
			pstForMeasurementStatus.setString(1, statusName);
			pstForMeasurementStatus.setInt(2, ProjectStoreId);
			System.out.println("Project StoreId is---> " + ProjectStoreId);
			System.out.println("Status Name ---> " + statusName);
			System.out.println("Prepared Statement is " + pstForMeasurementStatus);
			rsForMeasurementStatus = pstForMeasurementStatus.executeQuery();
			while (rsForMeasurementStatus.next()) {
				
				System.out.println("Data return from Database" + rsForMeasurementStatus.getString(1) );
				if(statusName.equalsIgnoreCase(JobCardReportConstants.MEASURED)){
					if(status.equalsIgnoreCase("measured_by")){
						System.out.println("Measured by:::::::::::::::::::" + rsForMeasurementStatus.getString(3));
						if(!rsForMeasurementStatus.getString(3).trim().equals("empty") || !rsForMeasurementStatus.getString(3).trim().equals(""))
							strMeasured_By = rsForMeasurementStatus.getString(3);
							System.out.println("Here ::\t" + strMeasured_By);
							break;
					}else if (rsForMeasurementStatus.getString(1) != "" || rsForMeasurementStatus.getString(1) != null){
						strMeasurementStatus = "Yes";
						strMeasurementStatusDate = rsForMeasurementStatus.getString(2);
						if(strMeasurementStatusDate != null || strMeasurementStatusDate != ""){
							strMeasurementStatusDate = changeDateFormat(strMeasurementStatusDate);
						}
							System.out.println("measurement status date is "  + strMeasurementStatusDate);
							System.out.println(" *************" + strMeasurementStatus + " And strMeasurementStatus value set to Yes" + "Date is " + strMeasurementStatusDate);
							break;
						}else{
							strMeasurementStatus = "No";
							strMeasurementStatusDate = rsForMeasurementStatus.getString(2);
							if(strMeasurementStatusDate != null || strMeasurementStatusDate != ""){
							strMeasurementStatusDate = changeDateFormat(strMeasurementStatusDate);
						}
						System.out.println("measurement status date is "  + strMeasurementStatusDate);
						System.out.println("Project Store Status is *************" + strMeasurementStatus + " And strMeasurementStatus value set to No" + "Date is " + strMeasurementStatusDate);
						break;
					}
				}else if(statusName.equals(MeasurementReportConstants.VISITED_AND_MEASURED)){
					if(rsForMeasurementStatus.getString(2) != null || rsForMeasurementStatus.getString(2) != "")
						strMeasurementStatusDate = changeDateFormat( rsForMeasurementStatus.getString(2) );
						break;
				}else if(statusName.equals(MeasurementReportConstants.CANCELLED)){
					strMeasurementStatusDate = rsForMeasurementStatus.getString(2);
					if(strMeasurementStatusDate != "NA" || strMeasurementStatusDate != "")
						strMeasurementStatusDate = changeDateFormat(strMeasurementStatusDate);
				
				}else if(statusName.equals(MeasurementReportConstants.NO_PERMISSION)){
					strMeasurementStatusDate = rsForMeasurementStatus.getString(2);
					if(strMeasurementStatusDate != "NA" || strMeasurementStatusDate != "")
						strMeasurementStatusDate = changeDateFormat(strMeasurementStatusDate);
				}else if(statusName.equals(MeasurementReportConstants.ALTERNATIVE_STORE_MEASURED)){
					strMeasurementStatusDate = rsForMeasurementStatus.getString(2);
					if(strMeasurementStatusDate != "NA" || strMeasurementStatusDate != "")
						strMeasurementStatusDate = changeDateFormat(strMeasurementStatusDate);
				}else if(statusName.equals(MeasurementReportConstants.IMPLEMENTATION)){
					if(status.equals("impl_by")){
						strMeasured_By = rsForMeasurementStatus.getString(3);
					}else{
						strMeasurementStatusDate = rsForMeasurementStatus.getString(2);
						if(strMeasurementStatusDate != "NA" || strMeasurementStatusDate != "")
							strMeasurementStatusDate = changeDateFormat(strMeasurementStatusDate);
					}
				}else if(statusName.equals(MeasurementReportConstants.REVISITED_AND_RE_MEASURED)){
					if(status.equalsIgnoreCase("measured_by")){
						strMeasured_By = rsForMeasurementStatus.getString(3);
						break;
					}else{
						if(rsForMeasurementStatus.getString(2) != null || rsForMeasurementStatus.getString(2) != "")
							strMeasurementStatusDate = changeDateFormat( rsForMeasurementStatus.getString(2) );
							break;
						}
				}else if(statusName.equals(MeasurementReportConstants.VISITED_BUT_NOT_MEASURED)){
					
					if(rsForMeasurementStatus.getString(2) != null || rsForMeasurementStatus.getString(2) != "")
						strMeasurementStatusDate = changeDateFormat( rsForMeasurementStatus.getString(2) );
						break;
					}else if(statusName.equals(MeasurementReportConstants.REVISITED_BUT_NOT_MEASURED)){
						
						if(rsForMeasurementStatus.getString(2) != null || rsForMeasurementStatus.getString(2) != "")
							strMeasurementStatusDate = changeDateFormat( rsForMeasurementStatus.getString(2) );
							break;
						}else if(statusName.equals(MeasurementReportConstants.MSHEET_RECEIVED)){
							
							if(rsForMeasurementStatus.getString(2) != null || rsForMeasurementStatus.getString(2) != "")
								strMeasurementStatusDate = changeDateFormat( rsForMeasurementStatus.getString(2) );
								break;
							}
				}
			
		}catch (ParseException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
			
			if(status.equals("measured_date")){
				System.out.println("return measured status date is " + strMeasurementStatusDate);
				return strMeasurementStatusDate;
			}else if(status.equalsIgnoreCase("measured_by")){
				System.out.println("getStatusBymeasurement returned is "+ strMeasured_By);
				return strMeasured_By;
			}else if(status.equalsIgnoreCase("Actual")){
				return strMeasurementStatusDate;
			}else if(statusName.equalsIgnoreCase(MeasurementReportConstants.MSHEET_RECEIVED)){
				return strMeasurementStatusDate;
			}else if(statusName.equalsIgnoreCase(MeasurementReportConstants.MEASURED)){
				return strMeasurementStatus;
			}else if(statusName.equals(MeasurementReportConstants.VISITED_AND_MEASURED)){
				return strMeasurementStatusDate;
			}else if(statusName.equals(MeasurementReportConstants.CANCELLED)){
				return strMeasurementStatusDate;
			}else if(statusName.equals(MeasurementReportConstants.REVISITED_AND_RE_MEASURED)){
					return strMeasurementStatusDate;
			}else if(statusName.equals(MeasurementReportConstants.VISITED_BUT_NOT_MEASURED)){
				return strMeasurementStatusDate;
			}else if(statusName.equals(MeasurementReportConstants.REVISITED_BUT_NOT_MEASURED)){
				return strMeasurementStatusDate;
			}else if(status.equals("impl_by")){
				return strMeasured_By;
			}else if(status.equals("impl_on")){
				return strMeasurementStatusDate;
			}
			/*else if(statusName.equals(MeasurementReportConstants.CANCELLED)){
				return strMeasurementStatusDate;
			}else if(statusName.equals(MeasurementReportConstants.NO_PERMISSION)){
				System.out.println("Staus returned is in No Permission " +strMeasurementStatusDate );
				return strMeasurementStatusDate;
			}*/else{
				return strMeasurementStatusDate;
			}
//		return strMeasurementStatus;
	}
	
	public String getCurrentMeasurementStatus(int ProjectStoreId){
		System.out.println("getCurrentMeasurementStatus(" + ProjectStoreId + ")");
		Connection();
		PreparedStatement pstForMeasurementStatus = null;
		ResultSet rsForMeasurementStatus = null;
		String measurementStatus = "NA";
		try {
			
			String queryJobCardStatus = "SELECT stM.measurement_status_name,stM.measurement_status_id,st.status_date" +
					" FROM fourth_dimension.measurement_status st,fourth_dimension.measurement_status_master stM,fourth_dimension.Project_stores pgmSt " +
					" WHERE	st.measurement_status = stM.measurement_status_id AND pgmSt.Project_store_id = st.Project_store_id " +
					" AND pgmSt.Project_store_id =? order by st.measurement_status_id  desc"; 
			pstForMeasurementStatus = con.prepareStatement(queryJobCardStatus);
			pstForMeasurementStatus.setInt(1, ProjectStoreId);
			System.out.println("job card id is---> " + ProjectStoreId);
			rsForMeasurementStatus = pstForMeasurementStatus.executeQuery();
			while (rsForMeasurementStatus.next()) {
				if (rsForMeasurementStatus.getString(1) != null || rsForMeasurementStatus.getString(1) != "")
				 {
					measurementStatus = rsForMeasurementStatus.getString(1);
					break;
				 }
			}
		}catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return measurementStatus;
	}
	
	public String getStatusBymeasurement(int ProjectStoreId, String[] statusNames) {
		System.out.println("getStatus(" + ProjectStoreId + "," + statusNames + ")");
		// System.out.println(con);
		String strMeasurementComment = "NA";  //Comments
		String strMeasurementPermission="NA";
		int intNoofVisitsMade = 0;
		String strNo_of_Visits = "";
		String in = "";
		Connection();
		PreparedStatement pstForMeasurementStatus = null;
		ResultSet rsForMeasurementStatus = null;
		try {
				for(int st =0;st<statusNames.length;st++){
					
					if(st==statusNames.length-1){
						in  = in +"'"+ statusNames[st] + "'" ;
					}else{
						in = in +"'"+ statusNames[st] + "'" + "," ;
					}
				}
			
			if(statusNames.equals(MeasurementReportConstants.REASON_FOR_NOT_MEASURING)){
//				for(int cnt =0;cnt<statusNames.length;cnt++){
					String queryJobCardStatus = "SELECT DISTINCT st.comments,st.measurement_status_id FROM fourth_dimension.measurement_status st, " +
							" fourth_dimension.measurement_status_master stM,fourth_dimension.Project_stores pgmSt WHERE " +
							" st.measurement_status = stM.measurement_status_id AND pgmSt.Project_store_id = st.Project_store_id " +
							" AND stM.measurement_status_name in ("+ in +") AND pgmSt.Project_store_id =? order by measurement_status_id desc"; 
					pstForMeasurementStatus = con.prepareStatement(queryJobCardStatus);
//					pstForMeasurementStatus.setString(1, statusNames[cnt]);
					pstForMeasurementStatus.setInt(1, ProjectStoreId);
					System.out.println("job card id is---> " + ProjectStoreId);
					System.out.println("Status Name ---> " + in);
					rsForMeasurementStatus = pstForMeasurementStatus.executeQuery();
					while (rsForMeasurementStatus.next()) {
						if (rsForMeasurementStatus.getString(1) != null || rsForMeasurementStatus.getString(1) != "" )
						 {
							strMeasurementComment = rsForMeasurementStatus.getString(1);
							if(strMeasurementComment.equals("empty")) strMeasurementComment = "NA";
							System.out.println("Status is 8888888888888" + strMeasurementComment + " And strMeasurementComment value set to "+ strMeasurementComment);
							break;
						}
					}
					System.out.println("ReasonFOr NOT MEasuring" + strMeasurementComment);
//				}
			}else if(statusNames.equals(MeasurementReportConstants.NO_OF_VISITS_MADE)){
//				for(int st =0;st<statusNames.length;st++){
					String queryJobCardStatus = "SELECT st.measurement_status FROM fourth_dimension.measurement_status st, " +
							"fourth_dimension.measurement_status_master stM,fourth_dimension.Project_stores pgmSt WHERE " +
							"st.measurement_status = stM.measurement_status_id AND pgmSt.Project_store_id = st.Project_store_id " +
							"AND stM.measurement_status_name in ("+ in +") AND pgmSt.Project_store_id =?"; 
					pstForMeasurementStatus = con.prepareStatement(queryJobCardStatus);
//					pstForMeasurementStatus.setString(1, in);
					pstForMeasurementStatus.setInt(1, ProjectStoreId);
					System.out.println("job card id is---> " + ProjectStoreId);
					System.out.println("Status Name ---> " + in);
					System.out.println("no of visits made " + pstForMeasurementStatus.toString() );
					rsForMeasurementStatus = pstForMeasurementStatus.executeQuery();
					while (rsForMeasurementStatus.next()) {
						if (rsForMeasurementStatus.getString(1) != null || rsForMeasurementStatus.getString(1) != "")
						 {
							intNoofVisitsMade++;
							System.out.println("Status is *************" + in  + " And intNoofVisitsMade value count is" + intNoofVisitsMade);
//							break;
						}
					}
//				}
				strNo_of_Visits = strNo_of_Visits + intNoofVisitsMade;;
			}else if(statusNames.equals(MeasurementReportConstants.PERMISSION)){
				/*String strTempPermssion="";
				for(int st =0;st<statusNames.length;st++){
					
					if(st==statusNames.length-1){
						strTempPermssion = strTempPermssion +"'"+ statusNames[st] + "'" ;
					}else{
						strTempPermssion = strTempPermssion +"'"+ statusNames[st] + "'" + "," ;
					}
				}*/
					String queryJobCardStatus = "SELECT stM.measurement_status_name,st.status_date" +
							" FROM fourth_dimension.measurement_status st,fourth_dimension.measurement_status_master stM," +
							" fourth_dimension.Project_stores pgmSt WHERE st.measurement_status = stM.measurement_status_id " +
							" AND pgmSt.Project_store_id = st.Project_store_id AND " +
							" stM.measurement_status_name in ("+ in +") AND pgmSt.Project_store_id = ?"; 
					pstForMeasurementStatus = con.prepareStatement(queryJobCardStatus);
//					pstForMeasurementStatus.setString(1, statusNames[st]);
					pstForMeasurementStatus.setInt(1, ProjectStoreId);
					System.out.println("job card id is---> " + ProjectStoreId);
					System.out.println("Status Name ---> " + in);
					System.out.println("QueryString " + pstForMeasurementStatus );
					rsForMeasurementStatus = pstForMeasurementStatus.executeQuery();
					while (rsForMeasurementStatus.next()) {
						if (rsForMeasurementStatus.getString(1) != null || rsForMeasurementStatus.getString(1) != "")
						 {
							strMeasurementPermission = rsForMeasurementStatus.getString(1) + "  " + changeDateFormat(rsForMeasurementStatus.getString(2));
							System.out.println("Permission Status in Measurement" + strMeasurementPermission);
//							break;
						}
					}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} /*
		 * finally { try { if (con != null) { con.close(); } if
		 * (pstForJobCardStatus != null) { pstForJobCardStatus.close(); } if
		 * (rsForJobCardStatus != null) { rsForJobCardStatus.close(); } } catch
		 * (SQLException sql) { sql.printStackTrace(); } }
		 */
		if(statusNames.equals(MeasurementReportConstants.REASON_FOR_NOT_MEASURING)){
			System.out.println("returned Data for REASON_FOR_NOT_MEASURING is :\t" + strMeasurementPermission);
			return strMeasurementComment;
		}else if(statusNames.equals(MeasurementReportConstants.PERMISSION)){
			System.out.println("returned Data is " + strMeasurementPermission);
			return strMeasurementPermission;
		}else{
			return strNo_of_Visits;
		}
	}
	
	public String getStatusForClientAndFD(int ProjectId,String against, String statusName) {
		System.out.println("getStatus(" + ProjectId + "," + statusName + "," + against + ")");
		// System.out.println(con);
//		String strClientTargetDate = "";
		System.out.println("getStatusForClientAndFD");
		String client_national_target_date = "";
		String client_national_actual_date = "";
		String client_regional_target_date = "";
		String client_regional_actual_date = "";
		String fd_national_target_date = "";
		String fd_national_actual_date = "";
		String fd_regional_target_date = "";
		String fd_regional_actual_date = "";
		
//		String strFdTargetDate = "";
		String strActualMeasurementDate = "";
		Connection();
		PreparedStatement pstForJobCardStatus = null;
		ResultSet rsForJobCardStatus = null;
		try {
				
			if(statusName.equals(MeasurementReportConstants.VISITED_AND_MEASURED)){
				
				String queryJobCardStatus = "SELECT client_planned_start_date,client_planned_end_date,client_actual_start_date," +
				"client_actual_end_date,fd_actual_start_date,fd_actual_end_date FROM fourth_dimension.job_cards jc," +
				"fourth_dimension.Project pgm,fourth_dimension.Project_elements pgele,fourth_dimension.Project_stores pgst," +
				" fourth_dimension.Project_element_scope_dates pgstsc, fourth_dimension.measurement_status mst,fourth_dimension.measurement_status_master mstM " +
				"WHERE pgm.Project_id = pgst.Project_id AND   jc.Project_store_id = pgst.Project_store_id " +
				" AND pgele.Project_element_id = pgstsc.Project_element_id AND mst.measurement_status = mstM.measurement_status_id" +
				" AND   pgst.Project_store_id =? AND mstM.measurement_status_name = ?";
				pstForJobCardStatus = con.prepareStatement(queryJobCardStatus);
				pstForJobCardStatus.setInt(1, ProjectId);
				pstForJobCardStatus.setString(2, statusName);
				System.out.println("job card id is---> " + ProjectId);
				System.out.println("Status Name ---> " + statusName);
				rsForJobCardStatus = pstForJobCardStatus.executeQuery();
				while (rsForJobCardStatus.next()) {
					if (rsForJobCardStatus.getString(3) != null
							|| rsForJobCardStatus.getString(3) != "") {
						strActualMeasurementDate = rsForJobCardStatus.getString(3);
						System.out.println("Status is *************" + strActualMeasurementDate);
						break;
					}
				}
				if (strActualMeasurementDate == null || strActualMeasurementDate == "") {
					strActualMeasurementDate = "NA";
					System.out
							.println("respose from status was null,so value set to NA");
				} else {
	
					strActualMeasurementDate = changeDateFormat(strActualMeasurementDate);
					System.out.println(" Actual Date of Measurement Converted Date is........" + strActualMeasurementDate);
				}
				return strActualMeasurementDate;
			}else if(statusName.equals(MeasurementReportConstants.TENTATIVE_MEASURED)){
				
				String QueryElementId = "SELECT Project_element_id FROM fourth_dimension.Project_elements pgele,fourth_dimension.Project pg, " +
						" fourth_dimension.Project_stores pgst WHERE pgst.Project_id = pg.Project_id AND   pgele.Project_id = pg.Project_id " +
						" AND   pg.Project_id = ?";
				PreparedStatement pstElement = con.prepareStatement(QueryElementId);
				pstElement.setInt(1, ProjectId);
				ResultSet eleRs = pstElement.executeQuery();
				if(eleRs.next()){
					int elementId = eleRs.getInt(1);
					System.out.println("Element id in \t" + statusName + "\t" + elementId);
					String measurementStatus = "SELECT city.city_name,state.state_name,client_national_target_date,client_national_actual_date,client_regional_target_date,client_regional_actual_date," +
							" fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date " +
							" FROM fourth_dimension.element_scopes_dateplan sd, fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state" +
							" where sd.element_id = ? AND sd.city_id = city.city_id AND sd.state_id = state.state_id" +
							" AND  sd.scope_of_work = sm.scope_id AND scope_name = ?";
					PreparedStatement pstMeasurementSheet = con.prepareStatement(measurementStatus);
					pstMeasurementSheet.setInt(1, elementId);
					pstMeasurementSheet.setString(2, statusName);
					System.out.println("scopre pst\t" + pstMeasurementSheet);
					ResultSet rsMeasureStatus = pstMeasurementSheet.executeQuery();
					if(rsMeasureStatus.next()){
						 client_national_target_date = rsMeasureStatus.getString(3);
						 client_national_actual_date = rsMeasureStatus.getString(4);
						 client_regional_target_date = rsMeasureStatus.getString(5);
						 client_regional_actual_date = rsMeasureStatus.getString(6);
						 fd_national_target_date = rsMeasureStatus.getString(7);
						 fd_national_actual_date = rsMeasureStatus.getString(8);
						 fd_regional_target_date = rsMeasureStatus.getString(9);
						 fd_regional_actual_date = rsMeasureStatus.getString(10);
					}
				}
				
				if(client_national_target_date == null || client_national_target_date == ""){
					client_national_target_date = "NA";
				}else{
					client_national_target_date = changeDateFormat(client_national_target_date);
					System.out.println("for date format changed" + "client_national_target_date "  + client_national_target_date );
				}
				if(client_national_actual_date == null || client_national_actual_date == ""){
					client_national_actual_date = "NA";
				}else{
					client_national_actual_date = changeDateFormat(client_national_actual_date);
					System.out.println("for date format changed" + "client_national_actual_date "  + client_national_actual_date );
				}
				if(client_regional_target_date == null || client_regional_target_date == ""){
					client_regional_target_date = "NA";
				}else{
					client_regional_target_date = changeDateFormat(client_regional_target_date);
					System.out.println("for date format changed" + "client_regional_target_date "  + client_regional_target_date );
				}
				if(client_regional_actual_date == null || client_regional_actual_date == ""){
					client_regional_actual_date = "NA";
				}else{
					client_regional_actual_date = changeDateFormat(client_regional_actual_date);
					System.out.println("for date format changed" + "client_regional_actual_date "  + client_regional_actual_date );
				}
				if(fd_national_target_date == null || fd_national_target_date == ""){
					fd_national_target_date = "NA";
				}else{
					fd_national_target_date = changeDateFormat(fd_national_target_date);
					System.out.println("for date format changed" + "fd_national_target_date "  + fd_national_target_date );
				}
				if(fd_national_actual_date == null || fd_national_actual_date == ""){
					fd_national_actual_date = "NA";
				}else{
					fd_national_actual_date = changeDateFormat(fd_national_actual_date);
					System.out.println("for date format changed" + "fd_national_actual_date "  + fd_national_actual_date );
				}
				if(fd_regional_target_date == null || fd_regional_target_date == ""){
					fd_regional_target_date = "NA";
				}else{
					fd_regional_target_date = changeDateFormat(fd_regional_target_date);
					System.out.println("for date format changed" + "fd_regional_target_date "  + fd_regional_target_date );
				}
				if(fd_regional_actual_date == null || fd_regional_actual_date == ""){
					fd_regional_actual_date = "NA";
				}else{
					fd_regional_actual_date = changeDateFormat(fd_regional_actual_date);
					System.out.println("for date format changed" + "fd_regional_actual_date "  + fd_regional_actual_date );
				}
				
			}else if(statusName.equals(MeasurementReportConstants.IMPLEMENTATION)){
				
				String QueryElementId = "SELECT Project_element_id FROM fourth_dimension.Project_elements pgele,fourth_dimension.Project pg, " +
						" fourth_dimension.Project_stores pgst WHERE pgst.Project_id = pg.Project_id AND   pgele.Project_id = pg.Project_id " +
						" AND   pg.Project_id = ?";
				PreparedStatement pstElement = con.prepareStatement(QueryElementId);
				pstElement.setInt(1, ProjectId);
				System.out.println("QueryElementId" + pstElement.toString());
				ResultSet eleRs = pstElement.executeQuery();
				if(eleRs.next()){
					int elementId = eleRs.getInt(1);
					System.out.println("Element id in \t" + statusName + "\t" + elementId);
					String measurementStatus = "SELECT client_national_target_date,client_national_actual_date,client_regional_target_date,client_regional_actual_date," +
							" fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date " +
							" FROM fourth_dimension.element_scopes_dateplan sd, fourth_dimension.scope_master sm where sd.element_id = ?" +
							" AND  sd.scope_of_work = sm.scope_id AND scope_name = ?";
					PreparedStatement pstMeasurementSheet = con.prepareStatement(measurementStatus);
					pstMeasurementSheet.setInt(1, elementId);
					pstMeasurementSheet.setString(2, statusName);
					System.out.println("scopre pst\t" + pstMeasurementSheet);
					ResultSet rsMeasureStatus = pstMeasurementSheet.executeQuery();
					if(rsMeasureStatus.next()){
						 client_national_target_date = rsMeasureStatus.getString(1);
						 client_national_actual_date = rsMeasureStatus.getString(2);
						 client_regional_target_date = rsMeasureStatus.getString(3);
						 client_regional_actual_date = rsMeasureStatus.getString(4);
						 fd_national_target_date = rsMeasureStatus.getString(5);
						 fd_national_actual_date = rsMeasureStatus.getString(6);
						 fd_regional_target_date = rsMeasureStatus.getString(7);
						 fd_regional_actual_date = rsMeasureStatus.getString(8);
					}
				}
	
				if(client_national_target_date == null || client_national_target_date == ""){
					client_national_target_date = "NA";
				}else{
					client_national_target_date = changeDateFormat(client_national_target_date);
					System.out.println("for date format changed" + "client_national_target_date "  + client_national_target_date );
				}
				if(client_national_actual_date == null || client_national_actual_date == ""){
					client_national_actual_date = "NA";
				}else{
					client_national_actual_date = changeDateFormat(client_national_actual_date);
					System.out.println("for date format changed" + "client_national_actual_date "  + client_national_actual_date );
				}
				if(client_regional_target_date == null || client_regional_target_date == ""){
					client_regional_target_date = "NA";
				}else{
					client_regional_target_date = changeDateFormat(client_regional_target_date);
					System.out.println("for date format changed" + "client_regional_target_date "  + client_regional_target_date );
				}
				if(client_regional_actual_date == null || client_regional_actual_date == ""){
					client_regional_actual_date = "NA";
				}else{
					client_regional_actual_date = changeDateFormat(client_regional_actual_date);
					System.out.println("for date format changed" + "client_regional_actual_date "  + client_regional_actual_date );
				}
				if(fd_national_target_date == null || fd_national_target_date == ""){
					fd_national_target_date = "NA";
				}else{
					fd_national_target_date = changeDateFormat(fd_national_target_date);
					System.out.println("for date format changed" + "fd_national_target_date "  + fd_national_target_date );
				}
				if(fd_national_actual_date == null || fd_national_actual_date == ""){
					fd_national_actual_date = "NA";
				}else{
					fd_national_actual_date = changeDateFormat(fd_national_actual_date);
					System.out.println("for date format changed" + "fd_national_actual_date "  + fd_national_actual_date );
				}
				if(fd_regional_target_date == null || fd_regional_target_date == ""){
					fd_regional_target_date = "NA";
				}else{
					fd_regional_target_date = changeDateFormat(fd_regional_target_date);
					System.out.println("for date format changed" + "fd_regional_target_date "  + fd_regional_target_date );
				}
				if(fd_regional_actual_date == null || fd_regional_actual_date == ""){
					fd_regional_actual_date = "NA";
				}else{
					fd_regional_actual_date = changeDateFormat(fd_regional_actual_date);
					System.out.println("for date format changed" + "fd_regional_actual_date "  + fd_regional_actual_date );
				}	
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		if(against.equals("FD_Target_Date")){
			System.out.println("return status date against\t" + against +"\tis\t" + fd_national_target_date);
			return fd_national_target_date ;
		}else if (against.equals("client_Targer_Date")){
			System.out.println("return status date against\t" + against +"\tis\t" + client_national_target_date);
			return client_national_target_date;
		}else{
			return "NA";
		}
	}
	
	
	public String changeDateFormat(String format) throws java.text.ParseException {
		String reformattedStr = "";
		try {
			SimpleDateFormat fromDB = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat myFormat = new SimpleDateFormat("dd-MMM-yy");
			if(format != null){
				reformattedStr = myFormat.format(fromDB.parse(format));
			}
		} catch (ParseException parse) {
			parse.printStackTrace();
		}
		return reformattedStr;
	}
	
	public String changeNewDateFormat(String format) throws java.text.ParseException {
		String reformattedStr = "";
		try {
			SimpleDateFormat fromDB = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat myFormat = new SimpleDateFormat("MM-dd-yy");
			if(format != null){
				reformattedStr = myFormat.format(fromDB.parse(format));
			}
		} catch (ParseException parse) {
			parse.printStackTrace();
		}
		return reformattedStr;
	}
	
	public String getJobCardByProjectForExceptionReport(int ProjectId) {
		System.out.println("-----------------------get job cards by Project id::\t" + ProjectId);
		outData = "";
		PreparedStatement psForPrgName = null;
		PreparedStatement pstPrgmEle = null;
		PreparedStatement pstForJobCardStatus = null;
		PreparedStatement pstForprgmStores = null;
		PreparedStatement pstForJobCard = null;
		ResultSet rsForPrgName = null;
//		ResultSet rsPrgmEle = null;
		ResultSet rsForPrgmStores = null;
		ResultSet rsForJobCard = null;
		ResultSet rsForJobCards = null;
		String strQueryForProjectStores = "SELECT Project_store_id,chain_name,store_name,address,city_name,state_name,fdhub_name,contact_name," +
				" handle_by,measured_on ,region_name,contact_phone,pgm.crm_name,pgm.crm_phone,area_name,measured_by " +
				" FROM fourth_dimension.Project_stores pgs,fourth_dimension.Project pgm WHERE pgs.Project_id = pgm.Project_id " +
				" AND pgs.Project_id = ? order by state_name,city_name,store_name";
		
		String strQueryForProjectStores1 = "SELECT ps.Project_store_id,ps.store_name,ps.address, ps.tsi_name,ps.contact_name,ps.contact_phone,ps.tsi_phone,ps.handle_by,p.crm_phone, " +
				" ps.fdhub_name,ps.state_name ,ps.city_name, ps.measured_by,ps.measured_on, pele.project_name,pele.quantity,pele.Project_element_id " +
				" FROM fourth_dimension.Project_stores ps, fourth_dimension.Project_elements pele, fourth_dimension.Project p ,fourth_dimension.project_store_element_mapping map " +
				" WHERE ps.Project_id = pele.Project_id AND p.Project_id = ps.Project_id AND pele.project_element_id = map.project_element_id " +
				" AND ps.project_store_id = map.project_store_id AND p.Project_id = ? order by store_name,region_name";
		
		String strQueryForJobCard = "SELECT * FROM fourth_dimension.job_cards WHERE Project_store_id=? and Project_id=?";
		
		String strRegion_name = "NA";
		String strScopeStateName = "NA";
		String strCityName = "NA";
		String strOutlet_Name = "NA";
		String strAddress = "NA";
		String strArea = "NA";
		String strTSI_SO_Name = "NA";
		String strTSI_SO_phone = "NA";
		String strFd_Supervisor = "NA";
		String strCRM_Phone = "NA";
		String strTentative_measured_date = "NA";
		String strTentative_implement_date = "NA";
		String strVisited_But_Not_Measured = "NA";
		String strRevisited_But_Not_Measured = "NA";
		String strPlanned_measurement_date = "NA";
		String strPlanned_implementation_date = "NA";
		String strReasonForNotMeasuring = "";
		String strNoOfVisitsMade = "";
		String strMeasurement_No_Permission = "NA";
		String strMeasurementCancelled = "NA";
		String strJobCardNo = "NA";
		String strElementName = "NA";
		String strJC_No_Permission = "NA";
		String strCancelled = "NA";
		String strElementDamaged = "NA";
		String strElementMissing = "NA";
		String strMaterialResent = "NA";
		
		int ProjectStoreId = 0;
		int project_element_id = 0;
		int jobCardId = 0;
		int state_id = 0;
		try {
			
			// JobCardReport jobCardReport = new JobCardReport();
			Connection();
			System.out.println("got connection in MIS TrackerException_Report try block");
			
			System.out.println("MISException Before strQueryForProjectStores" + strQueryForProjectStores1);
			pstForprgmStores = con.prepareStatement(strQueryForProjectStores1);
			pstForprgmStores.setInt(1, ProjectId);
			rsForPrgmStores = pstForprgmStores.executeQuery();
			//if(rsForPrgmStores.next()){
				System.out.println("inside strQueryForProjectStores" + strQueryForProjectStores1);
				while(rsForPrgmStores.next()){
					ProjectStoreId = rsForPrgmStores.getInt(1);
					strElementName = rsForPrgmStores.getString("project_name");
					project_element_id = rsForPrgmStores.getInt("Project_element_id");
					System.out.println("inside while loop" + ProjectStoreId);
					if(ProjectStoreId != 0){
						
						strReasonForNotMeasuring = getStatusBymeasurement(ProjectStoreId, MeasurementReportConstants.REASON_FOR_NOT_MEASURING);
						strNoOfVisitsMade = getStatusBymeasurement(ProjectStoreId, MeasurementReportConstants.NO_OF_VISITS_MADE);
//						strAlternative_outlet_measured = getStatusBymeasurement(ProjectStoreId, "", MeasurementReportConstants.ALTERNATIVE_STORE_MEASURED);
						strVisited_But_Not_Measured = getStatusBymeasurement(ProjectStoreId,"", MeasurementReportConstants.VISITED_BUT_NOT_MEASURED);
						strRevisited_But_Not_Measured = getStatusBymeasurement(ProjectStoreId,"", MeasurementReportConstants.REVISITED_BUT_NOT_MEASURED);
						strMeasurement_No_Permission = getStatusBymeasurement(ProjectStoreId,"", MeasurementReportConstants.NO_PERMISSION);
						strMeasurementCancelled = getStatusBymeasurement(ProjectStoreId,"", MeasurementReportConstants.CANCELLED);
						System.out.println("Measurement Cancelled:\t" + strMeasurementCancelled);
						strTentative_measured_date = getStatusForClientAndFD(ProjectId,"client_Targer_Date", MeasurementReportConstants.TENTATIVE_MEASURED);
						strTentative_implement_date = getStatusForClientAndFD(ProjectId,"client_Target_Date", MeasurementReportConstants.IMPLEMENTATION);
						strPlanned_measurement_date = getStatusForClientAndFD(ProjectId,"FD_Target_Date", MeasurementReportConstants.IMPLEMENTATION);
					}
//					strChain_Name = rsForPrgmStores.getString("chain_name");
					strOutlet_Name = rsForPrgmStores.getString("store_name");
//					strAddress = rsForPrgmStores.getString("address");
					strCityName = rsForPrgmStores.getString("city_name");
					
//					strScopeStateName = rsForPrgmStores.getString("state_name");
					strTSI_SO_Name = rsForPrgmStores.getString("contact_name");
					if(strTSI_SO_Name == null || strTSI_SO_Name == ""){
						strTSI_SO_Name = "NA";
					}
//					strRegion_name = rsForPrgmStores.getString("region_name");
					if(strRegion_name == null)	strRegion_name = "NA";
					strAddress = rsForPrgmStores.getString("address");	
					strTSI_SO_phone = rsForPrgmStores.getString("contact_phone");
					if(strTSI_SO_phone == null) {strTSI_SO_phone = "NA";}
//					strCRM_Name = rsForPrgmStores.getString("crm_name");
					strCRM_Phone = rsForPrgmStores.getString("crm_phone");
					strFd_Supervisor = rsForPrgmStores.getString("handle_by");  // Fd Supervisor
					
					if(strFd_Supervisor == "" || strFd_Supervisor == "" || strFd_Supervisor == null)
					{
						strFd_Supervisor = "NA";
					}
					
					PreparedStatement pstForScopeDetails = con.prepareStatement("SELECT esdp.region_id,esdp.state_id,esdp.city_id," +
							" stStatus.Project_store_status_name FROM fourth_dimension.element_scopes_dateplan esdp, fourth_dimension.Project_stores ps, " +
							" fourth_dimension.Project_elements pele, fourth_dimension.Project_stores_status_master stStatus, fourth_dimension.Project p " +
							" WHERE ps.Project_id = pele.Project_id AND p.Project_id = ps.Project_id  AND stStatus.Project_store_status_id = ps.store_status_id " +
							" AND esdp.element_id = pele.Project_element_id AND pele.project_element_id = ?");
					pstForScopeDetails.setInt(1, project_element_id);
					ResultSet rsForScopeDetails = pstForScopeDetails.executeQuery();
					while(rsForScopeDetails.next())
					{
						if(rsForScopeDetails.getString("state_id")!= null || rsForScopeDetails.getString("state_id")!= "null" ){
							state_id = rsForScopeDetails.getInt("state_id");
							PreparedStatement pstForRegionName = con.prepareStatement("SELECT state_name FROM fourth_dimension.state_master WHERE state_id = ?");
							pstForRegionName.setInt(1, state_id);
							ResultSet rsForRegionName = pstForRegionName.executeQuery();
							while(rsForRegionName.next()){
								strScopeStateName = rsForRegionName.getString("state_name");
							}
						}
					}
					
					
					PreparedStatement pstForTentMesuredDate = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
							"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
							" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state" +
							" where sd.element_id = ?  AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
					pstForTentMesuredDate.setInt(1, project_element_id);
					pstForTentMesuredDate.setString(2, MeasurementReportConstants.TENTATIVE_MEASURED);
					ResultSet rsForTentMeasuredDate = pstForTentMesuredDate.executeQuery();
					while(rsForTentMeasuredDate.next()){
						if(strCityName.equalsIgnoreCase(rsForTentMeasuredDate.getString("city_name"))){
							strCityName = rsForTentMeasuredDate.getString("city_name");
							strScopeStateName = rsForTentMeasuredDate.getString("state_name");
							strTentative_measured_date = rsForTentMeasuredDate.getString("client_national_target_date");
							if(strTentative_measured_date != "NA"){
								strTentative_measured_date = changeDateFormat(strTentative_measured_date);
								break;
							}
						}else{
							
							strScopeStateName = rsForTentMeasuredDate.getString("state_name");
//							strLocation_CityName = "NA";
							strTentative_measured_date = rsForTentMeasuredDate.getString("client_national_target_date");
							if(strTentative_measured_date != "NA"){
								strTentative_measured_date = changeDateFormat(strTentative_measured_date);
								break;
							}
						}
					}
//					strTentative_measured_date = getStatusForClientAndFD(ProjectId,"client_Targer_Date", MeasurementReportConstants.TENTATIVE_MEASURED);
//					strTentative_implement_date = getStatusForClientAndFD(ProjectId,"client_Target_Date", MeasurementReportConstants.IMPLEMENTATION);
					PreparedStatement pstForTentImplementDate = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
							"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
							" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state where sd.element_id = ?" +
							" AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
					pstForTentImplementDate.setInt(1, project_element_id);
					pstForTentImplementDate.setString(2, MeasurementReportConstants.IMPLEMENTATION);
					ResultSet rsForTentImplementDate = pstForTentImplementDate.executeQuery();
					while(rsForTentImplementDate.next()){
						if(strCityName.equalsIgnoreCase(rsForTentImplementDate.getString("city_name"))){
							strCityName = rsForTentImplementDate.getString("city_name");
							strTentative_implement_date = rsForTentImplementDate.getString("client_national_target_date");
							if(strTentative_implement_date != "NA"){
								strTentative_implement_date = changeDateFormat(strTentative_implement_date);
							}
						}else{
							
							if(strScopeStateName != "NA"){
								strScopeStateName = rsForTentImplementDate.getString("state_name");
							}
							strCityName = "NA";
							strTentative_implement_date = rsForTentImplementDate.getString("client_national_target_date");
							if(strTentative_implement_date != "NA"){
								strTentative_implement_date = changeDateFormat(strTentative_implement_date);
							}
						}
					}
					
					PreparedStatement pstForPlanMesuredDate = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
							"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
							" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state" +
							" where sd.element_id = ?  AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
					pstForPlanMesuredDate.setInt(1, project_element_id);
					pstForPlanMesuredDate.setString(2, MeasurementReportConstants.TENTATIVE_MEASURED);
					ResultSet rsForPlanMeasuredDate = pstForPlanMesuredDate.executeQuery();
					while(rsForPlanMeasuredDate.next()){
						if(strCityName.equalsIgnoreCase(rsForPlanMeasuredDate.getString("city_name"))){
							strCityName = rsForPlanMeasuredDate.getString("city_name");
							strScopeStateName = rsForPlanMeasuredDate.getString("state_name");
							strPlanned_measurement_date = rsForPlanMeasuredDate.getString("fd_national_target_date");
							if(strTentative_measured_date != "NA"){
								strPlanned_measurement_date = changeDateFormat(strPlanned_measurement_date);
								break;
							}
						}else{
							
							strScopeStateName = rsForPlanMeasuredDate.getString("state_name");
//							strLocation_CityName = "NA";
							strPlanned_measurement_date = rsForPlanMeasuredDate.getString("fd_national_target_date");
							if(strTentative_measured_date != "NA"){
								strPlanned_measurement_date = changeDateFormat(strPlanned_measurement_date);
								break;
							}
						}
					}
//					strTentative_measured_date = getStatusForClientAndFD(ProjectId,"client_Targer_Date", MeasurementReportConstants.TENTATIVE_MEASURED);
//					strTentative_implement_date = getStatusForClientAndFD(ProjectId,"client_Target_Date", MeasurementReportConstants.IMPLEMENTATION);
					PreparedStatement pstForPlanImplementDate = con.prepareStatement("SELECT city.city_name,state.state_name,sm.scope_name,client_national_target_date,client_national_actual_date,client_regional_target_date," +
							"client_regional_actual_date,fd_national_target_date,fd_national_actual_date, fd_regional_target_date,fd_regional_actual_date,id" +
							" FROM fourth_dimension.element_scopes_dateplan sd,fourth_dimension.scope_master sm ,fourth_dimension.city_master city,fourth_dimension.state_master state where sd.element_id = ?" +
							" AND sd.city_id = city.city_id AND sd.state_id = state.state_id AND sd.scope_of_work = sm.scope_id  AND sm.scope_name = ? order by id desc");
					pstForPlanImplementDate.setInt(1, project_element_id);
					pstForPlanImplementDate.setString(2, MeasurementReportConstants.IMPLEMENTATION);
					ResultSet rsForPlanImplementDate = pstForPlanImplementDate.executeQuery();
					while(rsForPlanImplementDate.next()){
						if(strCityName.equalsIgnoreCase(rsForPlanImplementDate.getString("city_name"))){
							strCityName = rsForPlanImplementDate.getString("city_name");
							strPlanned_implementation_date = rsForPlanImplementDate.getString("fd_national_target_date");
							if(strPlanned_implementation_date != "NA"){
								System.out.println("date:\t" + strPlanned_implementation_date);
								strPlanned_implementation_date = changeDateFormat(strPlanned_implementation_date);
							}
						}else{
							
							if(strScopeStateName != "NA"){
								strScopeStateName = rsForPlanImplementDate.getString("state_name");
							}
							strCityName = "NA";
							strPlanned_implementation_date = rsForPlanImplementDate.getString("fd_national_target_date");
							if(strPlanned_implementation_date != "NA"){
								strPlanned_implementation_date = changeDateFormat(strPlanned_implementation_date);
							}
						}
					}
					
					System.out.println("Before Job Card query Execution");
					pstForJobCard = con.prepareStatement(strQueryForJobCard);
					pstForJobCard.setInt(1, ProjectStoreId);
					pstForJobCard.setInt(2, ProjectId);
					rsForJobCards = pstForJobCard.executeQuery();
					if(rsForJobCards.next()){
						
							jobCardId = rsForJobCards.getInt(1);
							strJobCardNo = rsForJobCards.getString("job_card_number");
							System.out.println("Job Card is*** " + jobCardId);
							// JobCardReport.getStatusValues(jobCardId);
							/*for(int st=0;st<JobCardReportConstants.DAMAGE_MISSING_RESENT.length;st++) {
								 String strTemp = getStatus(jobCardId, "", JobCardReportConstants.DAMAGE_MISSING_RESENT[st]);
								 if(strTemp != "NA"){
									 strDamage_Missing_Resent = strTemp;
								 }
							}*/
							
							
							strJC_No_Permission = getStatus(jobCardId, "" , JobCardReportConstants.NOPERMISSION);;
							strElementDamaged = getStatus(jobCardId, "" , JobCardReportConstants.ELEMENT_DAMAGED);
							strCancelled = getStatus(jobCardId, "" ,JobCardReportConstants.CANCELLED);
							strElementMissing = getStatus(jobCardId, "" , JobCardReportConstants.ELEMENT_MISSING);
							strMaterialResent = getStatus(jobCardId, "" , JobCardReportConstants.MATERIAL_RESENT);
						
							outData = outData + data + strScopeStateName
							+ columnSeperator + strCityName
							+ columnSeperator + strOutlet_Name
							+ columnSeperator + strAddress
							+ columnSeperator + strTSI_SO_Name
							+ columnSeperator + strTSI_SO_phone
							+ columnSeperator + strFd_Supervisor
							+ columnSeperator + strTSI_SO_phone
							+ columnSeperator + strTentative_measured_date
							+ columnSeperator + strTentative_implement_date  //strTentative_measured_date or Alligned_to_measure  
							+ columnSeperator + strVisited_But_Not_Measured
							+ columnSeperator + strRevisited_But_Not_Measured
							+ columnSeperator + strPlanned_measurement_date
							+ columnSeperator + strPlanned_implementation_date
							+ columnSeperator + strReasonForNotMeasuring
							+ columnSeperator + strNoOfVisitsMade
							+ columnSeperator + strMeasurement_No_Permission 
							+ columnSeperator + strMeasurementCancelled
							+ columnSeperator + strJobCardNo
							+ columnSeperator + strElementName
							+ columnSeperator + strJC_No_Permission
							+ columnSeperator + strElementDamaged
							+ columnSeperator + strElementMissing
							+ columnSeperator + strCancelled
							+ columnSeperator + strMaterialResent
							+ rowSeperator;
						
					}else{
						strJobCardNo = "NA";
						strJC_No_Permission ="NA";
						strElementDamaged ="NA";
						strCancelled = "NA";
						strElementMissing ="NA";
						strMaterialResent = "NA";
						
						System.out.println("No job card assigned for Project_store_id in Mis Exception Report\t" + ProjectStoreId);
						
						outData = outData + data + strScopeStateName
						+ columnSeperator + strCityName
						+ columnSeperator + strOutlet_Name
						+ columnSeperator + strAddress
						+ columnSeperator + strTSI_SO_Name
						+ columnSeperator + strTSI_SO_phone
						+ columnSeperator + strFd_Supervisor
						+ columnSeperator + strTSI_SO_phone
						+ columnSeperator + strTentative_measured_date
						+ columnSeperator + strTentative_implement_date  //strTentative_measured_date or Alligned_to_measure  
						+ columnSeperator + strVisited_But_Not_Measured
						+ columnSeperator + strRevisited_But_Not_Measured
						+ columnSeperator + strPlanned_measurement_date
						+ columnSeperator + strPlanned_implementation_date
						+ columnSeperator + strReasonForNotMeasuring
						+ columnSeperator + strNoOfVisitsMade
						+ columnSeperator + strMeasurement_No_Permission 
						+ columnSeperator + strMeasurementCancelled
						+ columnSeperator + strJobCardNo
						+ columnSeperator + strElementName
						+ columnSeperator + strJC_No_Permission
						+ columnSeperator + strElementDamaged
						+ columnSeperator + strElementMissing
						+ columnSeperator + strCancelled
						+ columnSeperator + strMaterialResent
						+ rowSeperator;
					}
				}
			//}
			//End
						
			
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (rsForPrgName != null) {
					rsForPrgName.close();
				}
				if (rsForJobCards != null) {
					rsForJobCards.close();
				}
				if (rsForPrgmStores != null) {
					rsForPrgmStores.close();
				}
				if (psForPrgName != null) {
					psForPrgName.close();
				}
				if (pstForJobCardStatus != null) {
					pstForJobCardStatus.close();
				}
				if (pstForprgmStores != null) {
					pstForprgmStores.close();
				}
				if (pstPrgmEle != null) {
					pstPrgmEle.close();
				}

			} catch (SQLException sql) {
				log.info("Exception::"+sql);
				sql.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String getMIS_Report_For_Central_Co_Ordinator(int ProjectId) {
		System.out.println("-----------------------get job cards by Project id::\t" + ProjectId);
		outData = "";
		PreparedStatement psForPrgName = null;
		PreparedStatement pstPrgmEle = null;
		PreparedStatement pstForJobCardStatus = null;
		PreparedStatement pstForReport = null;
//		PreparedStatement pstForJobCard = null;
		ResultSet rsForPrgName = null;
//		ResultSet rsPrgmEle = null;
		ResultSet rsForReport = null;
//		ResultSet rsForJobCard = null;
		ResultSet rsForJobCards = null;
	
//		String strQueryForJobCard = "SELECT * FROM fourth_dimension.job_cards WHERE Project_store_id=? and Project_id=?";
		
		String strQueryForReport ="SELECT distinct jc.job_card_number,p.project_name,ps.store_name,ps.address,ps.city_name, " +
				" ps.state_name,ps.fdhub_name,pel.quantity,job_card_id,ps.Project_store_id FROM fourth_dimension.Project_elements pel,fourth_dimension.Project p," +
				" fourth_dimension.Project_stores ps,fourth_dimension.job_cards jc,fourth_dimension.project_store_element_mapping map" +
				" WHERE pel.Project_id = p.Project_id AND ps.Project_id = p.Project_id AND pel.project_element_id = map.project_element_id" +
				" AND ps.project_store_id = map.project_store_id AND ps.Project_store_id = jc.Project_store_id  AND p.Project_id = ? order by store_name";
		String strQueryForReport1 = "SELECT ps.Project_store_id,ps.store_name,ps.address, ps.tsi_name,ps.tsi_phone,ps.handle_by,p.crm_phone, " +
				" ps.fdhub_name,ps.state_name ,ps.city_name, ps.measured_by,ps.measured_on, pele.project_name,pele.quantity,pele.Project_element_id " +
				" FROM    fourth_dimension.Project_stores ps, fourth_dimension.Project_elements pele, fourth_dimension.Project p " +
				" WHERE ps.Project_id = pele.Project_id AND p.Project_id = ps.Project_id AND p.Project_id = ? order by store_name,region_name";
		String strJobCardNo = "NA";
		String strProjectname = "NA";
		String strOutlet_Name = "NA";
		String strAddress = "NA";
		String strCityName = "NA";
		String strStateName = "NA";
		String strFdHub = "NA";
		String strElement_Qty = "NA"; 
		String strSheets_Received = "NA";
		String strCancel_Hold = "NA";
		String strAWSent_For_Approval = "NA";
		String strArtworks_Complete = "NA";
		String strReady_For_Dispatch = "NA";
		String strMaterial_Dispatched = "NA";
		String strAudit_Photo_Received = "NA";
		String strBilled = "NA";
		
//		int ProjectStoreId = 0;
		int jobCardId = 0;
		try {
			
			// JobCardReport jobCardReport = new JobCardReport();
			Connection();
			System.out.println("got connection in MIS Report For Central co-ordinator try block");
			
			System.out.println("Before strQueryForProjectStores" + strQueryForReport);
			pstForReport = con.prepareStatement(strQueryForReport);
			pstForReport.setInt(1, ProjectId);
			rsForReport = pstForReport.executeQuery();
				while(rsForReport.next()){
					
					strJobCardNo = rsForReport.getString("job_card_number");
					strProjectname = rsForReport.getString("project_name");
					strOutlet_Name = rsForReport.getString("store_name");
					strAddress = rsForReport.getString("address");
					strCityName = rsForReport.getString("city_name");
					strStateName = rsForReport.getString("state_name");
					strFdHub = rsForReport.getString("fdhub_name");
					strElement_Qty = rsForReport.getString("quantity");
					jobCardId = Integer.parseInt(rsForReport.getString("job_card_id"));
//					ProjectStoreId = Integer.parseInt(rsForReport.getString("Project_store_id"));
					
					
						
							strJobCardNo = rsForReport.getString("job_card_number");
							System.out.println("Job Card is*** " + jobCardId);
							// JobCardReport.getStatusValues(jobCardId);
							
							strSheets_Received = getStatus(jobCardId,"", JobCardReportConstants.SHEETS_RECEIVED);;
							strCancel_Hold = getStatus(jobCardId, "", JobCardReportConstants.CANCELLED);;
							strArtworks_Complete = getStatus(jobCardId, "",JobCardReportConstants.ARTWORK_COMPLETE);
							strAWSent_For_Approval = getStatus(jobCardId,"", JobCardReportConstants.ARTWORK_SENT_FOR_APPROVAL);
							strReady_For_Dispatch = getStatus(jobCardId, "", JobCardReportConstants.READY_FOR_DESPATCH);
							strMaterial_Dispatched = getStatus(jobCardId,"", JobCardReportConstants.MATERIAL_DESPATCHED);
							strAudit_Photo_Received = getStatus(jobCardId,"", JobCardReportConstants.AUDIT_PHOTOS_RECEIVED);
							strBilled = getStatus(jobCardId,"", JobCardReportConstants.BILLED);

						
							outData = outData + data + strJobCardNo
							+ columnSeperator + strProjectname
							+ columnSeperator + strOutlet_Name
							+ columnSeperator + strAddress
							+ columnSeperator + strCityName
							+ columnSeperator + strStateName
							+ columnSeperator + strFdHub
							+ columnSeperator + strElement_Qty
							+ columnSeperator + strSheets_Received
							+ columnSeperator + strCancel_Hold
							+ columnSeperator + strAWSent_For_Approval  
							+ columnSeperator + strArtworks_Complete
							+ columnSeperator + strReady_For_Dispatch
							+ columnSeperator + strMaterial_Dispatched
							+ columnSeperator + strAudit_Photo_Received
							+ columnSeperator + strBilled
							+ rowSeperator;
						
					}
			
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (rsForPrgName != null) {
					rsForPrgName.close();
				}
				if (rsForJobCards != null) {
					rsForJobCards.close();
				}
				if (rsForReport != null) {
					rsForReport.close();
				}
				if (psForPrgName != null) {
					psForPrgName.close();
				}
				if (pstForJobCardStatus != null) {
					pstForJobCardStatus.close();
				}
				if (pstForReport != null) {
					pstForReport.close();
				}
				if (pstPrgmEle != null) {
					pstPrgmEle.close();
				}

			} catch (SQLException sql) {
				log.info("Exception::"+sql);
				sql.printStackTrace();
			}
		}
		return NO_DATA;
	}
	
	public String getMIS_Report_For_Regional_Co_Ordinator(int ProjectId) {
		System.out.println("-----------------------get job cards by Project id::\t" + ProjectId);
		outData = "";
		PreparedStatement psForPrgName = null;
		PreparedStatement pstPrgmEle = null;
		PreparedStatement pstForJobCardStatus = null;
		PreparedStatement pstForReport = null;
		ResultSet rsForPrgName = null;
		ResultSet rsForReport = null;
		ResultSet rsForJobCards = null;
	
//		String strQueryForJobCard = "SELECT * FROM fourth_dimension.job_cards WHERE Project_store_id=? and Project_id=?";
		
		String strQueryForReport ="SELECT distinct jc.job_card_number,p.project_name,ps.store_name,ps.address,ps.city_name, " +
				" ps.state_name,ps.fdhub_name,pel.quantity,job_card_id,ps.Project_store_id,ps.tsi_name,ps.measured_by,ps.measured_on" +
				" FROM fourth_dimension.Project_elements pel,fourth_dimension.Project p, fourth_dimension.project_store_element_mapping map," +
				" fourth_dimension.Project_stores ps,fourth_dimension.job_cards jc WHERE pel.project_id = p.project_id AND ps.Project_id = p.Project_id " +
				" AND ps.Project_store_id = jc.Project_store_id  AND pel.project_element_id = map.project_element_id AND " +
				" ps.project_store_id = map.project_store_id AND p.Project_id = ?  order by store_name";
		String strJobCardNo = "NA";
		String strProjectname = "NA";
		String strOutlet_Name = "NA";
		String strAddress = "NA";
		String strCityName = "NA";
		String strStateName = "NA";
		String strFdHub = "NA";
		String strElement_Qty = "NA"; 
		String strAlligned_to_Measure = "NA";
		String strTsi_so_Name = "NA";
		String strMeasured_By = "NA";
		String strElements = "NA";
		String strMeasured_On = "NA";
		String strMaterial_Reached = "NA";
		String strPermission_Received = "NA";
		String strImplemented_By = "NA";
		String strImplemented_On = "NA";
		String strProduce_Cancel ="NA";
		String strAudit = "NA";
		String strAudit_Photos_Couriered ="NA";
		String strMaterial_Received = "NA";
		
		
		int ProjectStoreId = 0;
		int jobCardId = 0;
		try {
			
			// JobCardReport jobCardReport = new JobCardReport();
			Connection();
			System.out.println("got connection in MIS Report For Regional co-ordinator try block");
			
			System.out.println("Before strQueryForProjectStores" + strQueryForReport);
			pstForReport = con.prepareStatement(strQueryForReport);
			pstForReport.setInt(1, ProjectId);
			rsForReport = pstForReport.executeQuery();
				while(rsForReport.next()){
					
					strJobCardNo = rsForReport.getString("job_card_number");
					strProjectname = rsForReport.getString("project_name");
					strOutlet_Name = rsForReport.getString("store_name");
					strAddress = rsForReport.getString("address");
					strCityName = rsForReport.getString("city_name");
					strStateName = rsForReport.getString("state_name");
					strFdHub = rsForReport.getString("fdhub_name");
					strElement_Qty = rsForReport.getString("quantity");
					jobCardId = Integer.parseInt(rsForReport.getString("job_card_id"));
					ProjectStoreId = Integer.parseInt(rsForReport.getString("Project_store_id"));
					strAlligned_to_Measure = "NA";
					strTsi_so_Name = rsForReport.getString("tsi_name");
					if(strTsi_so_Name == "" || strTsi_so_Name == null){
						strTsi_so_Name = "NA";
					}
					System.out.println("Measured By value is 9656165416" + rsForReport.getString("measured_by"));
					if(rsForReport.getString("measured_by") == null || rsForReport.getString("measured_by") == "null" || rsForReport.getString("measured_by").equalsIgnoreCase("") ){
						strMeasured_By = "NA";
					}else{
						strMeasured_By = rsForReport.getString("measured_by");
					}
					System.out.println("Measured By value is " + strMeasured_By);
					if(rsForReport.getString("measured_on") == null || rsForReport.getString("measured_on") == "null"){
						strMeasured_On = "NA";
					}else{
						strMeasured_On = rsForReport.getString("measured_on");
						strMeasured_On = changeDateFormat(strMeasured_On);
					}
					System.out.println("Value of measured on :\t" + strMeasured_On + "asdsd");
								
					strElements = "NA";
					strMaterial_Reached = getStatus(jobCardId, "", JobCardReportConstants.MATERIAL_RECEIVED);
					strPermission_Received = getStatus(jobCardId, "", JobCardReportConstants.PERMISSION_RECEIVED);
					strImplemented_By = getStatusBymeasurement(ProjectStoreId, "impl_by", MeasurementReportConstants.IMPLEMENTATION);
					strImplemented_On = getStatusBymeasurement(ProjectStoreId, "impl_on", MeasurementReportConstants.IMPLEMENTATION);
					strProduce_Cancel = "NA";
					strAudit = getStatus(jobCardId, "", JobCardReportConstants.AUDIT);
					strAudit_Photos_Couriered = getStatus(jobCardId,"", JobCardReportConstants.AUDIT_PHOTOS_COURIERED_UPLOADED);
					strMaterial_Received = getStatus(jobCardId,"", JobCardReportConstants.MATERIAL_RECEIVED);	
					strJobCardNo = rsForReport.getString("job_card_number");
					System.out.println("Job Card is*** " + jobCardId);
					// JobCardReport.getStatusValues(jobCardId);
								
					outData = outData + data + strJobCardNo
					+ columnSeperator + strProjectname
					+ columnSeperator + strOutlet_Name
					+ columnSeperator + strAddress
					+ columnSeperator + strCityName
					+ columnSeperator + strStateName
					+ columnSeperator + strFdHub
					+ columnSeperator + strElement_Qty
					+ columnSeperator + strAlligned_to_Measure
					+ columnSeperator + strTsi_so_Name
					+ columnSeperator + strMeasured_By  
					+ columnSeperator + strElements
					+ columnSeperator + strMeasured_On
					+ columnSeperator + strMaterial_Reached
					+ columnSeperator + strPermission_Received
					+ columnSeperator + strImplemented_By
					+ columnSeperator + strImplemented_On
					+ columnSeperator + strProduce_Cancel
					+ columnSeperator + strAudit
					+ columnSeperator + strAudit_Photos_Couriered
					+ columnSeperator + strMaterial_Received
					+ rowSeperator;
						
					}
			
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (rsForPrgName != null) {
					rsForPrgName.close();
				}
				if (rsForJobCards != null) {
					rsForJobCards.close();
				}
				if (rsForReport != null) {
					rsForReport.close();
				}
				if (psForPrgName != null) {
					psForPrgName.close();
				}
				if (pstForJobCardStatus != null) {
					pstForJobCardStatus.close();
				}
				if (pstForReport != null) {
					pstForReport.close();
				}
				if (pstPrgmEle != null) {
					pstPrgmEle.close();
				}

			} catch (SQLException sql) {
				log.info("Exception::"+sql);
				sql.printStackTrace();
			}
		}
		return NO_DATA;
	}

/*
 * public String Project_insert(String inData,String inClientData){
 * System.out.println("ProjectServices.Project_insert()");
 * 
 * String[] split_data=inData.split("!#!"); for(int
 * i=0;i<split_data.length;i++){
 * 
 * System.out.println("Split data "+i+" --> "+split_data[i]); if ((split_data[i]
 * == null) || split_data[i].equals("")) { return DATA_INSUFFICIENT; } }
 * 
 * String[] split_inClientdata=inClientData.split("!#!"); for(int
 * i=0;i<split_inClientdata.length;i++){
 * 
 * System.out.println("split_inClientdata "+i+" --> "+split_inClientdata[i]); if
 * ((split_inClientdata[i] == null) || split_inClientdata[i].equals("")) {
 * return DATA_INSUFFICIENT; } }
 * 
 * try {
 * 
 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 * 
 * 
 * String Project_insert_details="INSERT INTO  fourth_dimension . Project " +
 * " ( Project_name ,  Project_number , client_id , division_id , start_date ,"
 * + " end_date , notes , crm_name , crm_phone , crm_email , estimated_budget ,"
 * + " billing_narration , special_instruction , total_stores ) VALUES ( " +
 * " ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 * 
 * 
 * Connection(); con.setAutoCommit(false); PreparedStatement
 * ps=con.prepareStatement(Project_insert_details);
 * ps.setString(1,split_data[0]); ps.setString(2,split_data[1]);
 * ps.setString(3,split_data[2]); ps.setString(4,split_data[3]);
 * 
 * 
 * //Dates Timestamp timeStampDate=null; Timestamp timeStampDate1=null;
 * if(!split_data[4].equalsIgnoreCase("empty")) { Date dateTime =
 * formatter.parse(split_data[4]+" 00:00:00"); System.out.println("dateTime" +
 * dateTime); timeStampDate = new Timestamp(dateTime.getTime()); } else { Date
 * dateTime = formatter.parse("1970-01-01 00:00:00"); timeStampDate=new
 * Timestamp(dateTime.getTime());
 * 
 * }
 * 
 * if(!split_data[5].equalsIgnoreCase("empty")) { Date dateTime =
 * formatter.parse(split_data[5]+" 23:59:59"); System.out.println("dateTime" +
 * dateTime); timeStampDate1 = new Timestamp(dateTime.getTime()); } else { Date
 * dateTime = formatter.parse("1970-01-01 00:00:00"); timeStampDate1=new
 * Timestamp(dateTime.getTime()); }
 * 
 * ps.setTimestamp(5,timeStampDate); ps.setTimestamp(6,timeStampDate1);
 * ps.setString(7,split_data[6]); ps.setString(8,split_data[7]);
 * ps.setString(9,split_data[8]); ps.setString(10,split_data[9]);
 * 
 * //Estimated Budget float estimat_budget=0;
 * if(split_data[10].equals("empty")){
 * 
 * }else{ estimat_budget=Float.parseFloat(split_data[10]); }
 * ps.setFloat(11,estimat_budget); ps.setString(12,split_data[11]);
 * ps.setString(13,split_data[12]);
 * 
 * //total Store int store_no=0; if(split_data[13].equals("empty")){
 * 
 * }else{ store_no=Integer.parseInt(split_data[13]); }
 * 
 * ps.setInt(14,store_no);
 * 
 * 
 * status=ps.executeUpdate(); if(status>0){ System.out.println("Inserted");
 * 
 * ps=con.prepareStatement("SELECT LAST_INSERT_ID()"); ResultSet
 * rs=ps.executeQuery(); if (rs.next()) { int Project_id=rs.getInt(1);
 * System.out.println("LAST ID-->"+Project_id); PreparedStatement
 * ps_client=con.prepareStatement
 * ("INSERT INTO fourth_dimension . Project_client_details " +
 * " ( Project_id , client_manager_name , client_manager_phone , client_manager_email ,"
 * + " po_reference , po_date )VALUES (?,?,?,?,?,?) ");
 * ps_client.setInt(1,Project_id); ps_client.setString(2,split_inClientdata[0]);
 * ps_client.setString(3,split_inClientdata[1]);
 * ps_client.setString(4,split_inClientdata[2]);
 * ps_client.setString(5,split_inClientdata[3]);
 * 
 * //po date Date dateTime = formatter.parse("1970-01-01 00:00:00"); Timestamp
 * timeStampDate_po=new Timestamp(dateTime.getTime());
 * 
 * if(!split_inClientdata[4].equalsIgnoreCase("empty")) { dateTime =
 * formatter.parse(split_inClientdata[4]+" 00:00:00");
 * System.out.println("dateTime" + dateTime); timeStampDate_po = new
 * Timestamp(dateTime.getTime()); } else {
 * 
 * 
 * }
 * 
 * 
 * ps_client.setTimestamp(6,timeStampDate_po); int
 * client_state=ps_client.executeUpdate(); if(client_state>0){
 * System.out.println("Inserted"); con.setAutoCommit(true); return
 * DATA_INSERTED+Project_id; } } else {
 * 
 * }
 * 
 * } return DATA_NOT_INSERTED;
 * 
 * } catch (SQLException e) {
 * 
 * e.printStackTrace(); return DATA_NOT_INSERTED; } catch (Exception e) {
 * e.printStackTrace(); return DATA_NOT_INSERTED; } finally{
 * 
 * try { con.close(); } catch (SQLException e) { e.printStackTrace(); } } }
 */

public String updateScopeDetails(String data, String scope_list) {

	String[] split_data = data.split("@!@");
	for (int i = 0; i < split_data.length; i++) {

		System.out.println("i=" + (i + 1) + "  " + split_data[i]);
	}

	try {
		Connection();
		ps = con.prepareStatement("Update fourth_dimension.element_scopes_dateplan "
				+ " set country_id=?, region_id =?, state_id=? , city_id=?, "
				+ " trade_id=?, chain_id=? , no_of_store=? ,scope_of_work=?, "
				+ " clientApprovalReq=? ,responsible_person=?, fd_hub_id=?,"
				+ " client_national_target_date=?, client_national_actual_date=?,"
				+ " client_regional_target_date=? ,client_regional_actual_date=? ,"
				+ " fd_national_target_date=? , fd_national_actual_date=? , "
				+ " fd_regional_target_date=? , fd_regional_actual_date =? where id=?");

		/*
		 * country+columnSeperator+region+columnSeperator+state+columnSeperator
		 * +
		 * city+columnSeperator+trade+columnSeperator+chain+columnSeperator+
		 * no_of_store +columnSeperator+scope_of_work+columnSeperator+
		 * client_aprvl_req
		 * +columnSeperator+fd_hub+columnSeperator+responsible_person+
		 * columnSeperator+
		 * cl_reg_act+columnSeperator+cl_reg_tar+columnSeperator+cl_np_act+
		 * columnSeperator
		 * +cl_np_tar+columnSeperator+fd_np_tar+columnSeperator+
		 * fd_np_act+columnSeperator+fd_reg_tar+columnSeperator+fd_reg_act
		 */

		ps.setInt(1, Integer.parseInt(split_data[0]));
		ps.setInt(2, Integer.parseInt(split_data[1]));

		// ps.setInt(3,Integer.parseInt(split_data[2]));//state
		if (split_data[2].equals("empty")) {
			ps.setNull(3, java.sql.Types.NUMERIC);// state
		} else {
			ps.setInt(3, Integer.parseInt(split_data[2]));// state
		}

		// ps.setInt(4,Integer.parseInt(split_data[3]));//city
		if (split_data[3].equals("empty")) {
			ps.setNull(4, java.sql.Types.NUMERIC);// city
		} else {
			ps.setInt(4, Integer.parseInt(split_data[3]));// city
		}

		// ps.setInt(5,Integer.parseInt(split_data[4]));
		if (split_data[4].equals("empty")) {
			ps.setNull(5, java.sql.Types.NUMERIC);// trade
		} else {
			ps.setInt(5, Integer.parseInt(split_data[4]));// trade
		}

		// ps.setInt(6,Integer.parseInt(split_data[5]));//chain_id
		if (split_data[5].equals("empty")) {
			ps.setNull(6, java.sql.Types.NUMERIC);// chain_id
		} else {
			ps.setInt(6, Integer.parseInt(split_data[5]));// chain_id
		}

		ps.setInt(7, Integer.parseInt(split_data[6]));// no_of_store
		ps.setInt(8, Integer.parseInt(split_data[7]));// scope_of_work

		ps.setString(9, split_data[8]);// client_aprvl_req
		ps.setString(10, split_data[10]);// responsible _person

		// ps.setInt(11,Integer.parseInt(split_data[9]));//fd_hub_id
		if (split_data[9].equals("empty")) {
			ps.setNull(11, java.sql.Types.NUMERIC);// chain_id
		} else {
			ps.setInt(11, Integer.parseInt(split_data[9]));// chain_id
		}
		// dates
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		
		String timeStampDate = null;
		Timestamp timestamp=null;
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.YEAR, -100);
		formatter.set2DigitYearStart(cal.getTime());



		if (!split_data[11].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[11]));
			System.out.println("dateTime"+"\t" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(12, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(12, timestamp);	
		}
		

		if (!split_data[12].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[12]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(13, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp= new Timestamp(dateTime.getTime());
			ps.setTimestamp(13, timestamp);
		}
		

		if (!split_data[13].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[13]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(14, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(14, timestamp);
		}
		

		if (!split_data[14].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[14]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(15, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(15, timestamp);
		}
		

		if (!split_data[15].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[15]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(16, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(16, timestamp);
		}
		

		if (!split_data[16].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[16]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(17, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(17, timestamp);
		}
		

		if (!split_data[17].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[17]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(18, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(18, timestamp);
		}
		

		if (!split_data[18].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[18]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(19, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(19, timestamp);
		}
		
		ps.setInt(20, Integer.parseInt(scope_list));

		status = ps.executeUpdate();
		if (status > 0) {
			System.out.println(DATA_UPDATED);
			return DATA_UPDATED;
		}
	} catch (Exception e) {
		e.printStackTrace();
		log.info("Exception::updateScopeDetails::"+e);
		return DATA_NOT_UPDATED;
	} finally {
		try {

			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (ps != null)
				ps.close();

		} catch (Exception e) {

		}
	}
	return DATA_NOT_UPDATED;
}

public String insertScopeDetailsForElement(String data, String element_list) {

	String[] split_data = data.split("@!@");

	for (int i = 0; i < split_data.length; i++) {

		System.out.println("i=" + (i) + "  " + split_data[i]);
	}

	try {
		Connection();
		ps = con.prepareStatement("INSERT INTO  fourth_dimension.element_scopes_dateplan "
				+ "( element_id, country_id , region_id , state_id , "
				+ " city_id , trade_id , chain_id ,"
				+ " no_of_store,scope_of_work, clientApprovalReq ,  "
				+ " responsible_person , fd_hub_id ,client_national_target_date , "
				+ " client_national_actual_date , client_regional_target_date , "
				+ " client_regional_actual_date , fd_national_target_date , "
				+ " fd_national_actual_date , fd_regional_target_date ,"
				+ " fd_regional_actual_date ) VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?, "
				+ " ?,?," + // clinet national
				" ?,?," + // clinet regional
				" ?,?," + // fd national
				" ?,?)"); // fd regional

		/*
		 * country+columnSeperator+region+columnSeperator+state+columnSeperator
		 * +city+columnSeperator+trade+columnSeperator+chain+
		 * columnSeperator+ no_of_store+columnSeperator+scope_of_work+
		 * columnSeperator+ client_aprvl_req+columnSeperator+fd_hub+
		 * columnSeperator+responsible_person+columnSeperator+
		 * cl_reg_act+columnSeperator+cl_reg_tar+columnSeperator+cl_np_act+
		 * columnSeperator
		 * +cl_np_tar+columnSeperator+fd_np_tar+columnSeperator+
		 * fd_np_act+columnSeperator+fd_reg_tar+columnSeperator+fd_reg_act
		 */
		ps.setInt(1, Integer.parseInt(element_list));
		ps.setInt(2, Integer.parseInt(split_data[0]));
		ps.setInt(3, Integer.parseInt(split_data[1]));
		if (split_data[2].equals("empty")) {
			ps.setNull(4, java.sql.Types.NUMERIC);// state
		} else {
			ps.setInt(4, Integer.parseInt(split_data[2]));// state
		}

		if (split_data[3].equals("empty"))
			ps.setNull(5, java.sql.Types.NUMERIC);// city
		else
			ps.setInt(5, Integer.parseInt(split_data[3]));// city

		if (split_data[4].equals("empty"))
			ps.setNull(6, java.sql.Types.NUMERIC);
		else
			ps.setInt(6, Integer.parseInt(split_data[4]));// trade

		if (split_data[5].equals("empty"))
			ps.setNull(7, java.sql.Types.NUMERIC);
		else
			ps.setInt(7, Integer.parseInt(split_data[5]));// chain_id

		ps.setInt(8, Integer.parseInt(split_data[6]));// no_of_store
		ps.setInt(9, Integer.parseInt(split_data[7]));// scope_of_work
		ps.setString(10, split_data[8]);// client_aprvl_req
		ps.setString(11, split_data[10]);// responsible _person

		if (split_data[9].equals("empty"))
			ps.setNull(12, java.sql.Types.NUMERIC);// fd_hub_id
		else
			ps.setInt(12, Integer.parseInt(split_data[9]));// fd_hub_id

		// dates
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		
		String timeStampDate = null;
		Timestamp timestamp=null;
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.YEAR, -100);
		formatter.set2DigitYearStart(cal.getTime());

		if (!split_data[11].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[11]));
			System.out.println("dateTime"+"\t" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(13, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(13, timestamp);	
		}
		

		if (!split_data[12].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[12]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(14, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp= new Timestamp(dateTime.getTime());
			ps.setTimestamp(14, timestamp);
		}
		

		if (!split_data[13].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[13]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(15, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(15, timestamp);
		}
		

		if (!split_data[14].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[14]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(16, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(16, timestamp);
		}
		

		if (!split_data[15].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[15]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(17, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(17, timestamp);
		}
		

		if (!split_data[16].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[16]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(18, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(18, timestamp);
		}
		

		if (!split_data[17].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[17]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(19, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(19, timestamp);
		}
		

		if (!split_data[18].equalsIgnoreCase("empty")) {
			timeStampDate = fmt.format(formatter.parse(split_data[18]));
			System.out.println("dateTime" + timeStampDate);
			//timeStampDate = new Timestamp(dateTime.getTime());
			ps.setString(20, timeStampDate);
		} else {
			Date dateTime = fmt.parse("1970-01-01 00:00:00");
			timestamp = new Timestamp(dateTime.getTime());
			ps.setTimestamp(20, timestamp);
		}
		

		status = ps.executeUpdate();
		if (status > 0) {
			return DATA_INSERTED;
		}
	} catch (Exception e) {
		e.printStackTrace();
		log.info("Exception insertScopeDetailsForElement::"+e);
		return DATA_NOT_INSERTED;
	} finally {
		try {

			if (con != null) {
				cp.free(con);
				con = null;
			}
			if (ps != null)
				ps.close();

		} catch (Exception e) {
			log.info("Exception insertScopeDetailsForElement::"+e);
		}
	}
	return DATA_NOT_INSERTED;
}


public String getprojCodeWithQty(String inData){
	
	try {
		int ProjectId = Integer.parseInt(inData);
		Connection();
		String query1 = "select quantity from fourth_dimension.project_elements where project_element_id=?";

		PreparedStatement ps = con.prepareStatement(query1);
		ps.setInt(1, ProjectId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			outData = outData + rs.getInt(1);
		}
		if (!outData.equals("")) {
			return outData;
		}

	} catch (Exception e) {
		e.printStackTrace();
		return NO_DATA;
	}
	return NO_DATA;
	
}

	public String getLatestInsertedStoreId(){
		try {
			Connection();
			String query1 = "SELECT max(project_store_id) FROM fourth_dimension.project_stores";

			PreparedStatement ps = con.prepareStatement(query1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData+rs.getInt(1);
			}
			if (!outData.equals("")) {
				return outData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return NO_DATA;
		}
		return NO_DATA;
	}

		public String insert_project_store_element_mapping(int StoreId,int element_project_id,int quantity){
			System.out.println("StoreID:::"+StoreId);
			System.out.println("project_id:::"+element_project_id);
			System.out.println("quantity::"+quantity);
				try{
					Connection();
					
				String query="insert into fourth_dimension.project_store_element_mapping (project_store_id,project_element_id,quantity)" +
						"values (?,?,?)";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setInt(1, StoreId);
				ps.setInt(2, element_project_id);
				ps.setInt(3, quantity);
				status = ps.executeUpdate();
				insert_project_store_element_mapping_into_measurement_data( StoreId, element_project_id);
				if (status > 0) {
					return DATA_INSERTED;
				}
				}catch(Exception e){
				e.printStackTrace();	
				}
				return NO_DATA;
		}
		
		
		public String insert_project_store_element_mapping_into_measurement_data(int StoreId,int element_project_id){
			System.out.println("StoreID:::"+StoreId);
			System.out.println("project_id:::"+element_project_id);
			//System.out.println("quantity::"+quantity);
				try{
					System.out.println("Insert Query for measurement Data1;;;;");
					Connection();
					System.out.println("Insert Query for measurement Data2;;;;");
					
					String projectName="";
					int projectId=0;
					String query4="select project_id,project_name from fourth_dimension.project_elements where project_element_id= " +
					element_project_id;
			       Statement st1=con.createStatement();
			      ResultSet rs2=st1.executeQuery(query4);
			      if(rs2.next()){
			    	  projectId=rs2.getInt(1);
			    	  projectName=rs2.getString(2);
			      }
				  
				String query1="select component_id,material_id from fourth_dimension.project_elements where project_name= '" +
						projectName+"' and project_id="+projectId;
				
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query1);
				while(rs.next()){
					
					System.out.println("Insert Query for measurement Data3;;;;");
					
				String query="insert into fourth_dimension.measurement_data (project_store_id,project_element_id,height_unit,width_unit," +
						"thickness_unit,element_status,component_id,material_id)" +
						"values (?,?,1,1,1,3,?,?)";
				
				PreparedStatement ps=con.prepareStatement(query);
				ps.setInt(1, StoreId);
				ps.setInt(2, element_project_id);
				ps.setInt(3, rs.getInt(1));
				ps.setInt(4, rs.getInt(2));
				System.out.println("Insert Query for measurement Data;;;;\t" + ps);
				status = ps.executeUpdate();
				}
				if (status > 0) {
					System.out.println("Insert Query for measurement Data4;;;;");
					return DATA_INSERTED;
				}
				}catch(Exception e){
					System.out.println(e);
				e.printStackTrace();	
				}
				return NO_DATA;
		}
	
		
		
		
		public String insert_project_store_element_mapping_into_measurement_data_for_import(int StoreId,int element_project_id,int componentId,int materialId){
			System.out.println("StoreID:::"+StoreId);
			System.out.println("project_id:::"+element_project_id);
			//System.out.println("quantity::"+quantity);
				try{
					System.out.println("Insert Query for measurement Data1;;;;");
					Connection();
					System.out.println("Insert Query for measurement Data2;;;;");
				
			/*	String query1="select component_id,material_id from fourth_dimension.project_elements where project_element_id= " +
						element_project_id;
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query1);
				while(rs.next()){*/
					
					System.out.println("Insert Query for measurement Data3;;;;");
					
				String query="insert into fourth_dimension.measurement_data (project_store_id,project_element_id,height_unit,width_unit," +
						"thickness_unit,element_status,component_id,material_id)" +
						"values (?,?,1,1,1,1,?,?)";
				
				PreparedStatement ps=con.prepareStatement(query);
				ps.setInt(1, StoreId);
				ps.setInt(2, element_project_id);
				ps.setInt(3, componentId);
				ps.setInt(4, materialId);
				System.out.println("Insert Query for measurement Data;;;;\t" + ps);
				status = ps.executeUpdate();
				//}
				if (status > 0) {
					System.out.println("Insert Query for measurement Data4;;;;");
					return DATA_INSERTED;
				}
				}catch(Exception e){
					System.out.println(e);
				e.printStackTrace();	
				}
				return NO_DATA;
		}
	
	
		public String update_project_store_element_mapping(int project_element_id,int qty_val,int project_element_mapping_id){
			System.out.println("project_name_id==="+project_element_id+"==qty_val=="+qty_val);
			try{
				Connection();
				System.out.println("Project_element_mapping_id00000007" + project_element_mapping_id);
				
			String query="update fourth_dimension.project_store_element_mapping SET project_element_id=?,quantity=? " +
					" where project_element_mapping_id=?";
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setInt(1, project_element_id);
			ps.setInt(2, qty_val);
			ps.setInt(3, project_element_mapping_id);
			
			status = ps.executeUpdate();
			if (status > 0) {
				return DATA_UPDATED;
			}
			}catch(Exception e){
			e.printStackTrace();	
			}
			
			return NO_DATA;
			
			
		}
		
		public List<HashMap<String, Object>> getProjectDropDownByClientId(int  projectId) {

			List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
			try {

				String query1 = "select project_id,project_name from project where client_id=" +
						"(select client_id from project where project_id="+projectId+")";

				//con = cp.getConnection();
				Connection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				while (rs.next()) {

					HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				    outDataHashMap.put("projectId", rs.getInt(1));
					outDataHashMap.put("projectName",rs.getString(2));
				    outDataList.add(outDataHashMap);
				}
			} catch (Exception e) {
     			System.out.println(e);
				e.printStackTrace();

			} finally {
				try {
					if (con != null) {
						con.close();
						
					}
					if (st != null)
						st.close();
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			return outDataList;
		}
		
		
		public List<HashMap<String, Object>> getBrandDropDownByProjectId(int  projectId) {

			List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
			try {

				String query1 = "select distinct bm.brand_id,bm.brand_name from project_elements pe," +
						" brand_master bm where pe.brand_id=bm.brand_id and" +
						" pe.project_id="+projectId;

				//con = cp.getConnection();
				Connection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				while (rs.next()) {

					HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				    outDataHashMap.put("brandId", rs.getInt(1));
					outDataHashMap.put("brandName",rs.getString(2));
				    outDataList.add(outDataHashMap);
				}
			} catch (Exception e) {
     			System.out.println(e);
				e.printStackTrace();

			} finally {
				try {
					if (con != null) {
						con.close();
						
					}
					if (st != null)
						st.close();
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			return outDataList;
		}
		
		
		public HashMap<String, Object> getDivisionNameByProjectId(int  projectId) {

			HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
			try {

				String query1 = "select division_name  from project p," +
						" fd_division_master fdm where p.division_id=fdm.fd_division_id and" +
						" p.project_id="+projectId;

				//con = cp.getConnection();
				Connection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				if (rs.next()) {

					outDataHashMap.put("divisionName",rs.getString(1));
				   
				}
			} catch (Exception e) {
     			System.out.println(e);
				e.printStackTrace();

			} finally {
				try {
					if (con != null) {
						con.close();
						
					}
					if (st != null)
						st.close();
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			return outDataHashMap;
		}
		
		public List<HashMap<String, Object>> getElementDropDownByProjectIdAndBrandId(int projectId,int brandId) {

			List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
			try {

				String query1 = "select distinct project_name,project_element_id from project_elements " +
						" where brand_id="+brandId+" and" +
						" project_id="+projectId;

				//con = cp.getConnection();
				Connection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				while (rs.next()) {

					HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				    outDataHashMap.put("elementId", rs.getInt(2));
					outDataHashMap.put("elementName",rs.getString(1));
				    outDataList.add(outDataHashMap);
				}
			} catch (Exception e) {
     			System.out.println(e);
				e.printStackTrace();

			} finally {
				try {
					if (con != null) {
						con.close();
						
					}
					if (st != null)
						st.close();
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			return outDataList;
		}
		
		public List<HashMap<String, Object>> getHubDropDown() {

			List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
			try {

				String query1 = "select fd_hub_id,hub_name from fd_hub_master";

				//con = cp.getConnection();
				Connection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				while (rs.next()) {

					HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				    outDataHashMap.put("hubId", rs.getInt(1));
					outDataHashMap.put("hubName",rs.getString(2));
				    outDataList.add(outDataHashMap);
				}
			} catch (Exception e) {
     			System.out.println(e);
				e.printStackTrace();

			} finally {
				try {
					if (con != null) {
						con.close();
						
					}
					if (st != null)
						st.close();
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			return outDataList;
		}
		public List<HashMap<String, Object>> getSowDropDown() {

			List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
			try {

				String query1 = "select scope_id,scope_name from scope_master";

				//con = cp.getConnection();
				Connection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				while (rs.next()) {

					HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				    outDataHashMap.put("sowId", rs.getInt(1));
					outDataHashMap.put("sowName",rs.getString(2));
				    outDataList.add(outDataHashMap);
				}
			} catch (Exception e) {
     			System.out.println(e);
				e.printStackTrace();

			} finally {
				try {
					if (con != null) {
						con.close();
						
					}
					if (st != null)
						st.close();
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			return outDataList;
		}	
		
		public List<HashMap<String, Object>> getDtDropDown() {

			List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
			try {

				String query1 = "select document_type_id,document_type_name from document_type_master";

				//con = cp.getConnection();
				Connection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				while (rs.next()) {

					HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				    outDataHashMap.put("dtId", rs.getInt(1));
					outDataHashMap.put("dtName",rs.getString(2));
				    outDataList.add(outDataHashMap);
				}
			} catch (Exception e) {
     			System.out.println(e);
				e.printStackTrace();

			} finally {
				try {
					if (con != null) {
						con.close();
						
					}
					if (st != null)
						st.close();
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			return outDataList;
		}
		
		
		public int setDocumentLibrary(File file,int projectId,int brandId,int elementId,int hubId,int sowId,int dtId,String comments) throws IOException {

			 System.out.println("inside document library "+file);
			outData = "";
			
			InputStream reader;
			FileInputStream	fis = null;
			String query1 = "insert into fourth_dimension.document_master values(?,?,?,?,?,?,?,?,?,?,NOW())";
			try {
				
					Connection();
			
						ps = con.prepareStatement(query1);
						ps.setInt(1, 0);
						ps.setInt(2, dtId);
						ps.setString(3, file.getName());

	                  	fis = new FileInputStream(file);
						ps.setBinaryStream(4, (InputStream)fis, (int)(file.length()));
						ps.setInt(5, projectId);
						ps.setInt(6, brandId);
						ps.setInt(7, elementId);
						
						ps.setInt(8, hubId);
						ps.setInt(9, sowId);
						ps.setString(10, comments);
						status = ps.executeUpdate();
		
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Excption::setElementStatus::"+e);
				//return DATA_NOT_INSERTED;

			} finally {
				try {

					if (con != null) {
						cp.free(con);
						con = null;
					}
					if (ps != null)
						ps.close();
					
					if(fis != null)
					{
						fis.close();
				
					}

				} catch (Exception e) {

				}
			}
			//return DATA_NOT_INSERTED;
			return status;
		}

		
		public List<HashMap<String, Object>> getDocumentLibrary(int projectId) {

			List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
			try {
			SimpleDateFormat fmt = new java.text.SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -100);
			sdf.set2DigitYearStart(cal.getTime());

			

				String query1 = "SELECT dm.document_id,dm.document_name ,dm.upload_date,p.project_name,bm.brand_name," +
						"pe.project_name,fhm.hub_name,sm.scope_name,dtm.document_type_name,dm.comments,fdm.division_name  " +
						"FROM fourth_dimension.document_master dm,project p,brand_master bm,project_elements pe," +
						"fd_hub_master fhm,scope_master sm,document_type_master dtm,fd_division_master fdm " +
						"where dm.project_id=p.project_id and dm.brand_id=bm.brand_id and dm.element_id=pe.project_element_id" +
						" and dm.hub_id=fhm.fd_hub_id and dm.sow_id=sm.scope_id and dm.document_type_id=dtm.document_type_id " +
						"and p.division_id=fdm.fd_division_id and  dm.project_id="+projectId;

				//con = cp.getConnection();
				Connection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				while (rs.next()) {

					HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				    outDataHashMap.put("documentId", rs.getInt(1));
					outDataHashMap.put("documentName",rs.getString(2));
					outDataHashMap.put("dateTime",(fmt.format(sdf.parse(rs.getString(3))))+" "+rs.getTime(3));
					outDataHashMap.put("projectName",rs.getString(4));
					outDataHashMap.put("brandName",rs.getString(5));
					outDataHashMap.put("elementName",rs.getString(6));
					outDataHashMap.put("hubName",rs.getString(7));
					outDataHashMap.put("sowName",rs.getString(8));
					outDataHashMap.put("dtName",rs.getString(9));
					outDataHashMap.put("comments",rs.getString(10));
					outDataHashMap.put("divisionName",rs.getString(11));
				
				    outDataList.add(outDataHashMap);
				}
			} catch (Exception e) {
     			System.out.println(e);
				e.printStackTrace();

			} finally {
				try {
					if (con != null) {
						con.close();
						
					}
					if (st != null)
						st.close();
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			return outDataList;
		}	
		
		public File getDocument(int documentId) throws IOException {

			
			InputStream reader = null;
			String documentName = null;
			FileOutputStream fileWriter;
			File file = null;
			String data;
			try {
				
					Connection();
		              st=con.createStatement();
						ResultSet rs=st.executeQuery("select * from document_master where  document_id ="+documentId);
						if (rs.next()) {
						 reader=rs.getBinaryStream("document");
						 documentName=rs.getString("document_name");
			
						}
						 file=new File(documentName);
						fileWriter=new FileOutputStream(file);
					 /*  BufferedWriter bw=new BufferedWriter(fileWriter);
					   BufferedReader br=new BufferedReader(reader);*/
						int read;
					   while ((read = reader.read()) != -1 ) {
						 //  System.out.println(" this is data "+read);
						   fileWriter.write(read);
						  // bw.newLine();
						   }
					   reader.close();
					   fileWriter.close();
					   //fileWriter.close();
					   

			} catch (Exception e) {
				e.printStackTrace();
				log.info("Excption::setElementStatus::"+e);
				//return DATA_NOT_INSERTED;

			} finally {
				try {

					if (con != null) {
						//cp.free(con);
						con.close();
					}
					if (ps != null)
						ps.close();
					
					if(reader != null)
					{
						reader.close();
				
					}

				} catch (Exception e) {

				}
			}
			
			return file;
		}
		
		public int updateDocument(int documentId,String comments) {
			int status = -1;
			int flag = 0;
			try {

				String query1 = "update fourth_dimension.document_master set "
						+ "comments=?  where document_id=?";

				Connection();
			
				ps = con.prepareStatement(query1);

				ps.setString(1, comments);
			    ps.setInt(2, documentId);
				flag = ps.executeUpdate();

				flag = ps.executeUpdate();
				if (flag > 0)
					status = 0;
			} catch (Exception e) {
				System.out.println(e);

			} finally {
				try {
					if (con != null) {
						cp.free(con);
						con = null;
					}
					if (ps != null)
						ps.close();

				} catch (Exception e) {
					System.out.println(e);
				}

			}
			return status;
		}
}
