package com.fd.Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aseema.fourthdimension.ConnectionPool;
import com.fd.utility.Util;

public class DropDown implements Constants {

	static String driver = "com.mysql.jdbc.Driver";
	// static String url = "jdbc:mysql://localhost:3307/fourth_dimension";
	static String url = "jdbc:mysql://localhost:3306/fourth_dimension";
	static String userName = "root";
	static String password = "root";
	static int initialConnections = 5;
	static int maxConnections = 45;
	Connection con = null;
	Connection cp = null;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;

	String outData = "";
	String inData;

	int status;
	String data;
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

	String DATA_INSUFFICIENT = Constants.DATA_INSUFFICIENT;
	String DATA_INSERTED = Constants.DATA_INSERTED;
	String DATA_NOT_INSERTED = Constants.DATA_NOT_INSERTED;

	// Update

	String DATA_UPDATED = Constants.DATA_UPDATED;
	String DATA_NOT_UPDATED = Constants.DATA_NOT_UPDATED;
	// Select
	String NO_DATA = Constants.NO_DATA;
	Logger log=Logger.getLogger(DropDown.class);
	
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

	public String dropDownUnitMaster() {
		outData = "";
		System.out.println("ProjectServices.dropDownUnitMaster()");
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_unit_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				//System.out.println("rs.getString(1)--->" + rs.getString(1));
			/*	if (rs.getString(1).equals("")) {
					return outData;
				}*/
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::dropDownUnitMaster"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}
	
	public String dropDownUnitMasterTemp() {
		outData = "";
		System.out.println("ProjectServices.dropDownUnitMaster()");
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_unit_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				//System.out.println("rs.getString(1)--->" + rs.getString(1));
			/*	if (rs.getString(1).equals("")) {
					return outData;
				}*/
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::dropDownUnitMaster"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String dropDownMeasurementStatus() {
		outData = "";
		System.out.println("ProjectServices.dropDownMeasurementStatus()");
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_measurementStatus_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				System.out.println("rs.getString(1)" + rs.getString(1));
				if (rs.getString(1).equals("")) {
					return outData;
				}
				outData = outData + rs.getInt(1) + Constants.columnSeperator+rs.getString(2)+Constants.rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::dropDownMeasurementStatus::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_elements_of_Project(String Project_id) {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_elements_of_Project);
			ps.setInt(1, Integer.parseInt(Project_id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("rs.getString(1)" + rs.getString(1));
				if (rs.getString(1).equals("")) {
					return outData;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::drop_down_elements_of_Project::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;

	}

	public String drop_down_store_of_Project(String Project_id) {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_store_of_Project);
			ps.setInt(1, Integer.parseInt(Project_id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("rs.getString(1)" + rs.getString(1));
				if (rs.getString(1).equals("")) {
					return outData;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::drop_down_store_of_Project"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String dropdown_fddiv_forsearch() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_fddiv_for_search);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("rs.getString(1)" + rs.getString(1));
				if (rs.getString(1).equals("")) {
					return outData;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("Outdata::" + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_client_name() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_client_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("rs.getString(1)" + rs.getString(1));
				if (rs.getString(1).equals("")) {
					return outData;
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_client_nameByID() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_client_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("rs.getString(1)" + rs.getString(1));
				if (rs.getString(1).equals("")) {
					return outData;
				}
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}
	
	public String dropDownMaterial_name_id(){
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_material_master_id_and_name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("rs.getString(1)" + rs.getInt(1));
				if (rs.getString(2).equals("")) {
					return outData;
				}
				outData = outData + rs.getInt(1) + columnSeperator
						+ rs.getString(2) + rowSeperator;
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
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_Projectname() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_fdemp_name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("rs.getString(1)" + rs.getString(1));
				if (rs.getString(1).equals("")) {
					return outData;
				}
				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::drop_down_Projectname::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_region_against_country(String country) {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_region_against_country);
			ps.setInt(1, Integer.parseInt(country));
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
			log.info("Exception::drop_down_region_against_country::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_state_against_region(String region) {
		System.out.println("DropDown.drop_down_state_against_region()");
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_state_against_region);
			ps.setInt(1, Integer.parseInt(region));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getInt(2) + rowSeperator;

			}

			System.out
					.println("outDAta  in drop_down_state_against_region():: "
							+ outData);

			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception :: drop_down_state_against_region"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_city_against_state(String state) {
		System.out.println("DropDown.drop_down_city_against_state()");
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_city_against_state);
			ps.setInt(1, Integer.parseInt(state));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getInt(2) + rowSeperator;

			}

			System.out.println("outDAta drop_down_city_against_state():: "
					+ outData);

			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("drop_down_city_against_state::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String regionDrop(String country) {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_region_master);
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
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("regionDrop::"+e);
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_region() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_region_master);
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
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			log.info("drop_down_region::"+e);
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_state() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_state_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator+rs.getInt(2)+rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("drop_down_state::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String dropdown_state_withID() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_state_master);
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
			log.info("dropdown_state_withID::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_trade() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_trade_master);
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
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("drop_down_trade::"+e);
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String dropdown_trade_withID() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_trade_master);
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
			log.info("Exception::dropdown_trade_withID"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_chain() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_chain_master);
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
			log.info("drop_down_chain::drop_down_chain::"+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String dropdown_chain_withID() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_chain_master);
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
			log.info(" ");
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("Exception::dropdown_chain_withID "+e);
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_city() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_city_master);
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
			log.info("Exception::drop_down_city "+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("Exception:: "+e);
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String dropdown_city_withID() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_city_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return NO_DATA;
				}
				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getInt(2) + rowSeperator;
			}
			if (!outData.equals("")) {
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::dropdown_city_withID "+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_scope_master() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_scope_master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getInt(2) + rowSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("Area Master--->" + outData);
				return outData;
			}
		} catch (Exception e) {
			log.info("Exception::drop_down_scope_master "+e);
			e.printStackTrace();
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_country() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_country__master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getString(1) + columnSeperator
						+ rs.getInt(2) + rowSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("country Master--->" + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::drop_down_country "+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_area() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_area__master);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData + rs.getString(1) + columnSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("Area Master--->" + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception::drop_down_area "+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_town() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_town__master);
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
			log.info("Exception::drop_down_town "+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_fdhub() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_fdhub_master);
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
			log.info("Exception::drop_down_fdhub "+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	public String drop_down_fdemp() {
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con.prepareStatement(drop_down_fdemp_master);
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
			log.info("Exception::drop_down_fdemp "+e);
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("Exception::drop_down_fdemp "+e);
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}

	
	
	public String drop_down_project_list(int projID){
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_project_List);
			ps.setInt(1, projID);
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
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}
	
	public String drop_down_store_status_list(){
		outData = "";
		try {
			Connection();
			PreparedStatement ps = con
					.prepareStatement(drop_down_store_status_List);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				outData = outData +rs.getInt(1)+columnSeperator +rs.getString(2) + rowSeperator;
			}
			if (!outData.equals("")) {
				System.out.println("StoreStatus List::--->" + outData);
				return outData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return NO_DATA;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}
	
	public String dropDownElementStatus_name_id(){
		try{
			outData="";
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_element_status);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				outData=outData+rs.getInt(1)+columnSeperator+rs.getString(2)+rowSeperator;
			}
			if(!outData.equals("")){
				return outData;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return NO_DATA;
		}finally{
			try{
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return NO_DATA;
		
	}
}
