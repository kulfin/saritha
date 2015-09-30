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

public class FilterSevices implements Constants{
	static String driver = "com.mysql.jdbc.Driver";
	//static String url = "jdbc:mysql://localhost:3307/fourth_dimension";
	static String url = "jdbc:mysql://localhost:3306/fourth_dimension";
	static String userName = "root";
	static String password = "root";
	static int initialConnections = 5;
	static int maxConnections = 45;
	Connection con = null;
	static ConnectionPool cp;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	
	
	
	String outData="";
	String inData;
	
	int    status;
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
	//consatnt
	
	String DATA_INSUFFICIENT=Constants.DATA_INSUFFICIENT;
	String DATA_INSERTED=Constants.DATA_INSERTED;
	String DATA_NOT_INSERTED=Constants.DATA_NOT_INSERTED;
	
	//Update
	
	String DATA_UPDATED=Constants.DATA_UPDATED;
	String DATA_NOT_UPDATED=Constants.DATA_NOT_UPDATED;
	//Select
	String NO_DATA=Constants.NO_DATA;
	
	Logger log=Logger.getLogger(FilterSevices.class);
	
	public void Connection(){
		try {
			/*Class.forName(driver);
			con=DriverManager.getConnection(url,userName,password);*/
			Class.forName(Util.driver);
			con=DriverManager.getConnection(Util.url,Util.userName,Util.password);
			
		} catch (Exception e) {

			System.out.println(e);
		}

	}
	/*
	 * Client Filtering 
	 * 
	 */
	
	public String getProjectOnClient(String client_name){
		System.out.println("FilterSevices.getProjectOnClient()"+client_name);
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_Project_name_on_Client);
			ps.setString(1,client_name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				System.out.println("rs.getString(1)"+rs.getString(1));
				if(rs.getString(1).equals(""))
				{
					return outData;
				}
				outData=outData+rs.getString(1)+columnSeperator+rs.getInt(2)+rowSeperator;
			}
			if(!outData.equals("")){
				System.out.println("outDATA getProjectOnClient::"+outData);
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getProjectOnClient::"+e);
			return NO_DATA;
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}
	
	public String getStoresOnArea(String client_name,String Project_name){
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_Stores_On_Area);
			ps.setString(1,client_name);
			ps.setString(2,Project_name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				System.out.println("rs.getString(1)"+rs.getString(1));
				if(rs.getString(1).equals(""))
				{
					return outData;
				}
				outData=outData+rs.getString(1)+columnSeperator+rs.getInt(2)+rowSeperator;
			}
			if(!outData.equals("")){
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info(" getStoresOnArea::"+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}
	
	
	
	/*
	 * Location Filtering 
	 * 
	 */
	public String getRegionOnCountry(String data){
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_region_master);
			ps.setString(1,data);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//System.out.println("rs.getString(1)"+rs.getString(1));
				if(rs.getString(1).equals(""))
				{
					return outData;
				}
				outData=outData+rs.getString(1)+columnSeperator+rs.getInt(2)+rowSeperator;
			}
			if(!outData.equals("")){
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getRegionOnCountry:: "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				log.info(" "+e);
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}
	
	//New Requirement for element status as a drop down
	public String getAllElementStatus(String data){
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_element_status);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//System.out.println("rs.getString(1)"+rs.getString(1));
				if(rs.getString(1).equals(""))
				{
					return outData;
				}
				outData=outData+rs.getString(2)+columnSeperator+rs.getInt(1)+rowSeperator;
			}
			if(!outData.equals("")){
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getAllElementStatus:: "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}
	
	public String getStateOnRegion(String data){
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_state_master);
			ps.setString(1,data);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//System.out.println("rs.getString(1)"+rs.getString(1));
				if(rs.getString(1).equals(""))
				{
					return outData;
				}
				outData=outData+rs.getString(1)+columnSeperator+rs.getInt(2)+rowSeperator;
			}
			if(!outData.equals("")){
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info(" getStateOnRegion::"+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}
	
	public String getCityOnState(String data){
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_city_master);
			ps.setString(1,data);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//System.out.println("rs.getString(1)"+rs.getString(1));
				if(rs.getString(1).equals(""))
				{
					return outData;
				}
				outData=outData+rs.getString(1)+columnSeperator+rs.getInt(2)+rowSeperator;
			}
			if(!outData.equals("")){
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getCityOnState:: "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}
	
	public String getTownOnCity(String data){
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_town_master);
			ps.setString(1,data);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
			//	System.out.println("rs.getString(1)"+rs.getString(1));
				if(rs.getString(1).equals(""))
				{
					return outData;
				}
				outData=outData+rs.getString(1)+columnSeperator+rs.getInt(2)+rowSeperator;
			}
			if(!outData.equals("")){
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getTownOnCity:: "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}
	
	public String getAreaOnTown(String data){
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_area_master);
			ps.setString(1,data);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
			//	System.out.println("rs.getString(1)"+rs.getString(1));
				
				outData=outData+rs.getString(1)+columnSeperator+rs.getInt(2)+rowSeperator;
			}
			if(!outData.equals("")){
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getAreaOnTown:: "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}

	
	public String getStoresOnProject(String project_id){
		
		//System.out.println(Project_id);
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_stores_on_Project);
			ps.setInt(1,Integer.parseInt(project_id));
	
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				outData=outData+rs.getString(1)+columnSeperator+rs.getInt(2)+rowSeperator;
			}
			System.out.println(outData);
			if(!outData.equals("")){
				
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getStoresOnProject:: "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}
	
	public String getFdHub(){
		

		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement("select fd_hub_id,hub_name from fd_hub_master order by hub_name");
		
	
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				outData=outData+rs.getInt(1)+columnSeperator+rs.getString(2)+rowSeperator;
			}
			System.out.println(outData);
			if(!outData.equals("")){
				
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getStoresOnProject:: "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return NO_DATA;
	}
	
	
	
	public String getDivisionOnHub(String hub_id){
		System.out.println(hub_id);
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_division_master);
//			ps.setInt(1,Integer.parseInt(hub_id));
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				outData=outData+rs.getString(1)+columnSeperator+rs.getInt(2)+rowSeperator;
			}
			System.out.println(outData);
			if(!outData.equals("")){
				
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getDivisionOnHub "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}
	
	public String getInstructionOnProject(String project_id){
		//System.out.println(Project_id);
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(InstructionOnProject);
			ps.setInt(1,Integer.parseInt(project_id));
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getString(1)==null||rs.getString(1).equals("null")){
					return NO_DATA;
				}
				
				
				outData=rs.getString(1);
			}
			System.out.println("outdata::"+outData);
			if(!outData.equals("")){
				System.out.println("Instruction for Project  ::"+outData);
				return outData; 
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getInstructionOnProject:: "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}

	
	public String getFDApprovalMgrName(){
		System.out.println("::::::getFDApprovalMgrName::::::");
		outData="";
		try {
			Connection();
			PreparedStatement ps=con.prepareStatement(filter_FDManager_Name);
//			ps.setInt(1,Integer.parseInt(hub_id));
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				outData=outData+rs.getInt(1)+columnSeperator+rs.getString(2)+rowSeperator;
			}
			System.out.println(outData);
			if(!outData.equals("")){
				
				return outData; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("getFDApprovalMgrName "+e);
			return NO_DATA;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	return NO_DATA;
	}
	
}
	

