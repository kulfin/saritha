package com.fd.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fd.Admin.ConnectionPool;
import com.fd.utility.Util;

public class Service {
	
	Connection con ;
	static ConnectionPool cp;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	String statusSeperator = "@&@";
	String rowSeperator = "!&!";

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

	static {
		try {
			/*cp = new ConnectionPool(driver, url, userName, password,
					initialConnections, maxConnections, true);*/
			cp = new ConnectionPool(Util.driver, Util.url, Util.userName, Util.password,
					Util.initialConnections, Util.maxConnections, true);
		} catch (Exception e) {

			System.out.println(e);
		}

	}
	
	public List<HashMap<String,Object>> getClientDropDown()  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String query1 = "SELECT client_id,client_name FROM fourth_dimension.client_master order by client_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			    outDataHashMap.put("clientId", rs.getInt(1));
			    outDataHashMap.put("clientName", rs.getString(2));
			   
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
	
public List<HashMap<String,Object>> getClientByProjectDivision(String divisionName)  {
	
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
//	    String query1 = "SELECT client_id,client_name FROM fourth_dimension.client_master";
	    String query1 = "SELECT distinct client.client_id,client.client_name FROM fourth_dimension.client_master client,fourth_dimension.Project pgm," +
	    		"fourth_dimension.fd_division_master divi WHERE client.client_id = pgm.client_id " +
	    		"AND pgm.division_id = divi.fd_division_id AND divi.division_name ="+"'"+divisionName+"' order by client_name";
	    String query2 = "SELECT client_id,client_name FROM fourth_dimension.client_master";
	    try {
	    	if(divisionName != "NA"){
		    	con=cp.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				while (rs.next()) {
		        
					HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
				    outDataHashMap.put("clientId", rs.getInt(1));
				    outDataHashMap.put("clientName", rs.getString(2));
				   
					outDataList.add(outDataHashMap);
				}
	    	}else{
	    		con=cp.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(query2);
				while (rs.next()) {
		        
					HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
				    outDataHashMap.put("clientId", rs.getInt(1));
				    outDataHashMap.put("clientName", rs.getString(2));
				   
					outDataList.add(outDataHashMap);
				}
	    	}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}

	public List<HashMap<String,Object>> getProjectDropDownByClientId(int clientId)  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String query1 = "SELECT Project_id,Project_name FROM " +
	    		"fourth_dimension.Project where client_id="+clientId+" order by Project_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			    outDataHashMap.put("ProjectId", rs.getInt(1));
			    outDataHashMap.put("ProjectName", rs.getString(2));
			   
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
public List<HashMap<String,Object>> getProjectDropDownByClientIdAndProjectDivisionName(int clientId,String divisionName)  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
//	    String query1 = "SELECT Project_id,Project_name FROM fourth_dimension.Project where client_id="+clientId;
	    String query1 = "SELECT Project_id,Project_name FROM fourth_dimension.Project pgm,fourth_dimension.fd_division_master divi " +
	    		"where pgm.client_id="+"'"+clientId+"'"+" AND pgm.division_id = divi.fd_division_id AND divi.division_name ="+"'"+divisionName+"'" +
	    				" order by Project_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			    outDataHashMap.put("ProjectId", rs.getInt(1));
			    outDataHashMap.put("ProjectName", rs.getString(2));
			   
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}

	public List<HashMap<String,Object>> getDivisionByProjectId(int ProjectId)  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String query1="SELECT division_id, division_name FROM fourth_dimension.Project p,fd_division_master d where " +
		"fd_division_id=division_id and Project_id="+ProjectId+" order by division_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if(rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			    outDataHashMap.put("divisionId", rs.getInt(1));
			    outDataHashMap.put("divisionName", rs.getString(2));
			   
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
	public List<HashMap<String,Object>> getProjectStoreDropDownByProjectId(int ProjectId)  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String query1 = "SELECT Project_store_id,store_name FROM " +
	    		"fourth_dimension.Project_stores where Project_id="+ProjectId+" order by store_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			    outDataHashMap.put("storeId", rs.getInt(1));
			    outDataHashMap.put("storeName", rs.getString(2));
			   
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
	
	public List<HashMap<String,Object>> getUnmappedProjectStoreDropDownByProjectId(int ProjectId)  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String query1 = "SELECT Project_store_id,store_name FROM " +
	    		"fourth_dimension.Project_stores where Project_id="+ProjectId+"" +
	    		 " and Project_store_id not in(select distinct Project_store_id " +
	    		 " FROM fourth_dimension.Project_store_element_mapping_mnd) order by store_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			    outDataHashMap.put("storeId", rs.getInt(1));
			    outDataHashMap.put("storeName", rs.getString(2));
			   
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
	
	public List<HashMap<String,Object>> getStoreStatusDropDown()  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String query1 = "SELECT * FROM fourth_dimension.store_status_master order by store_status_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			    outDataHashMap.put("storestatusId", rs.getInt(1));
			    outDataHashMap.put("storestatusName", rs.getString(2));
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
	/*public List<HashMap<String,Object>> getMappedProjectStoreDropDownByProjectId(int ProjectId)  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String query1 = "SELECT ps.Project_store_id,store_name,store_code,region_name,state_name,city_name,address," +
	    "ps.Project_store_status_id,Project_store_status_name,fdhub_name " +
	    "FROM fourth_dimension.Project_stores ps" +
	    ",fourth_dimension.Project_stores_status_master pssm where " +
	    "ps.Project_store_status_id=pssm.Project_store_status_id " +
	    "and ps.Project_store_id in(select distinct Project_store_id " +
	    "from fourth_dimension.Project_store_element_mapping_mnd) and Project_id="+ProjectId+" order by store_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
	        
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("storeId", rs.getInt(1));
				outDataHashMap.put("storeName", rs.getString(2));
				if(rs.getString(3)!= null){
					outDataHashMap.put("storeCode", rs.getString(3));	
				}else{
					outDataHashMap.put("storeCode", "NA");
				}
				
				outDataHashMap.put("regionName", rs.getString(4));
				outDataHashMap.put("stateName", rs.getString(5));
				outDataHashMap.put("cityName", rs.getString(6));
				if(rs.getString(7)!= null){
					outDataHashMap.put("address", rs.getString(7));	
				}else{
					outDataHashMap.put("address", "NA");
				}
				
				outDataHashMap.put("storeStatusId", rs.getInt(8));
				outDataHashMap.put("storeStatusName", rs.getString(9));
				if(rs.getString(10) != null){
					outDataHashMap.put("fdhubName", rs.getString(10));
				}else{
					outDataHashMap.put("fdhubName", "NA");
				}
				SimpleDateFormat fmt = new java.text.SimpleDateFormat(
						"dd/MM/yy");
				SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -100);
				sdf.set2DigitYearStart(cal.getTime());

				String query2 = "SELECT job_card_number,job_card_date FROM "
						+ " fourth_dimension.Project_store_element_mapping_mnd"
						+ " where Project_store_id=" + rs.getInt(1);
				st = con.createStatement();
				ResultSet rs1 = st.executeQuery(query2);
				if (rs1.next()) {
					outDataHashMap.put("jcNumber", rs1.getInt(1));
					System.out.println("date is"+ rs1.getString(2));
					
					if(rs1.getString(2)!=null)
					outDataHashMap.put("jcDate", fmt.format(sdf.parse(rs1
							.getString(2))));
					else
					outDataHashMap.put("jcDate", "");
						
				}

				List<HashMap<String, Object>> elementDataList = new ArrayList<HashMap<String, Object>>();
				String query3 = "SELECT psemm.Project_element_id,element_name,bm.brand_name,pe.project_code,psemm.element_status," +
						" psemm.quantity,psemm.comments,pst.project_status_name FROM fourth_dimension.Project_store_element_mapping_mnd psemm," +
						" Project_elements pe,project_status_master pst,brand_master bm ,element_master em where  psemm.Project_element_id=pe.Project_element_id=em.element_id " +
						" and  pst.project_status_id = pe.project_status_id  and pe.brand_id=bm.brand_id and psemm.Project_store_id="+rs.getInt(1);
	   
	    	
			st = con.createStatement();
			ResultSet rs2 = st.executeQuery(query3);
			
			while (rs2.next()) {
	        
				HashMap<String,Object> elementDataHashMap=new HashMap<String,Object>();
			
				elementDataHashMap.put("elementId", rs2.getInt(1));
			    elementDataHashMap.put("elementName",rs2.getString(2));
			    
			    elementDataHashMap.put("brandName", rs2.getString(3));
			    elementDataHashMap.put("projectCode", rs2.getString(4));
			    
			    elementDataHashMap.put("elementStatusId", rs2.getString(5));
			    elementDataHashMap.put("quantity", rs2.getInt(6));
//			    System.out.println("Comments data77777" + rs2.getString(7));
			    if(rs2.getString(7) != null || rs2.getString(7) != ""){
//			    	System.out.println("Under if");
			    		elementDataHashMap.put("comments", rs2.getString(7));
			    }else{
			    	elementDataHashMap.put("comments", "NA");
			    }
			    elementDataHashMap.put("elementStatusName", rs2.getString(8));
				elementDataList.add(elementDataHashMap);
			}
	             outDataHashMap.put("element", elementDataList);
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
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
				System.out.println(e);
			}

		}
	return outDataList;
	}*/
	
public List<HashMap<String,Object>> getMappedProjectStoreDropDownByProjectId(int ProjectId)  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String queryString = "SELECT ps.project_store_id,ps.state_name,ps.city_name,ps.store_name,ps.address,ps.store_code,staM.store_status_id,staM.store_status_name, " +
	    		" br.brand_name,pel.project_name,pelm.quantity,ps.fdhub_name,ele.element_status_name,pel.project_element_id,pelm.project_element_mapping_id ,pelm.comments " +
	    		" FROM fourth_dimension.project_stores ps, fourth_dimension.project_elements pel, fourth_dimension.project_store_element_mapping pelm, fourth_dimension.store_status_master staM, " +
	    		" fourth_dimension.brand_master br, fourth_dimension.element_status_master ele WHERE ps.project_store_id = pelm.project_store_id AND pel.project_element_id = pelm.project_element_id " +
	    		" AND ps.project_store_id = pelm.project_store_id AND ps.store_status_id = staM.store_status_id AND ele.element_status_id = pelm.element_status_id " +
	    		" AND br.brand_id = pel.brand_id AND ps.project_id =?";
	    
	    String query1 = "SELECT ps.project_store_id,ps.state_name,ps.city_name,ps.store_name,ps.address,ps.store_code,staM.store_status_id,staM.store_status_name," +
	    		" br.brand_name,pel.project_name,pelm.quantity,ps.fdhub_name,ele.element_status_name,pel.project_element_id,pelm.project_element_mapping_id" +
	    		" FROM fourth_dimension.project_stores ps, fourth_dimension.project_elements pel, fourth_dimension.project_store_element_mapping pelm, " +
	    		" fourth_dimension.store_status_master staM, fourth_dimension.brand_master br,fourth_dimension.element_status_master ele WHERE ps.project_store_id = pelm.project_store_id " +
	    		" AND pel.project_element_id = pelm.project_element_id AND ps.project_store_id = pelm.project_store_id AND ps.store_status_id = staM.store_status_id " +
	    		" AND ele.element_status_id = pelm.element_status_id AND br.brand_id = pel.brand_id AND ps.project_id = ?";
	    
	    try {
	    	con=cp.getConnection();
			ps = con.prepareStatement(queryString);
			ps.setInt(1, ProjectId);
			rs = ps.executeQuery();
			while (rs.next()) {
	        System.out.println("Store Id " + rs.getInt(1));
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("storeId", rs.getInt(1));
				outDataHashMap.put("stateName", rs.getString(2));
				outDataHashMap.put("cityName", rs.getString(3));
				outDataHashMap.put("storeName", rs.getString(4));
				if(rs.getString(5)!= null){
					outDataHashMap.put("address", rs.getString(5));	
				}else{
					outDataHashMap.put("address", "NA");
				}
				if(rs.getString(6)!= null){
					outDataHashMap.put("storeCode", rs.getString(6));	
				}else{
					outDataHashMap.put("storeCode", "NA");
				}
				outDataHashMap.put("storeStatusId", rs.getInt(7));
				outDataHashMap.put("storeStatusName", rs.getString(8));
				outDataHashMap.put("brandName", rs.getString(9));
				outDataHashMap.put("elementName", rs.getString(10));
				outDataHashMap.put("quantity", rs.getString(11));
				if(rs.getString(12) != null){
					outDataHashMap.put("fdhubName", rs.getString(12));
				}else{
					outDataHashMap.put("fdhubName", "NA");
				}
				outDataHashMap.put("elementStatusName", rs.getString(13));
				outDataHashMap.put("elementId", rs.getString(14));
				outDataHashMap.put("project_element_mapping_Id", rs.getString(15));
				SimpleDateFormat fmt = new java.text.SimpleDateFormat("dd/MM/yy");
				SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
				PreparedStatement pstForJobCard = con.prepareStatement("SELECT * FROM fourth_dimension.job_cards where project_store_id = ?");
				pstForJobCard.setInt(1, rs.getInt(1));
				ResultSet rsForJobCard = pstForJobCard.executeQuery();
				if(rsForJobCard.next()){
					outDataHashMap.put("jcNumber", rsForJobCard.getString(3));
					
					if(rsForJobCard.getString(4)!=null){
						outDataHashMap.put("jcDate", fmt.format(sdf.parse(rsForJobCard.getString(4))));
					}
					else{
						outDataHashMap.put("jcDate", "NA");
					}
				}else{
					outDataHashMap.put("jcNumber", "NA");
					outDataHashMap.put("jcDate", "NA");
				}
				
				
				
				
				System.out.println("Comments data is " + rs.getString(16) );
				
				outDataHashMap.put("comments", rs.getString(16));

				outDataList.add(outDataHashMap);	
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
	
public List<HashMap<String,Object>> get_job_card_by_Project_select_mode(int ProjectId,String fd_division_name)  {
	System.out.println("get_job_card_by_Project_select_mode  ProjectId::"+ProjectId);
	System.out.println("fd_division_name=======>"+fd_division_name);
    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
	String query1="";
	String query2="";
    
     query1 = "select a.Project_store_id, job_card_id,job_card_number,store_name,address,city_name,divm.division_name " +
    		" from fourth_dimension.job_cards a,Project_stores s ,fourth_dimension.fd_division_master divm ,fourth_dimension.project p " +
    		" where a.Project_store_id=s.Project_store_id and divm.fd_division_id = p.division_id and s.project_id = p.project_id and a.Project_id=?";
    
   /**
    * change the query2 for getting the quantity from project_store_element_mapping
    * **/
    /*String query2 = "SELECT pe.project_name,cm.component_name,mm.material_name,pe.quantity,br.brand_name " +
	" FROM fourth_dimension.measurement_data md,Project_elements pe,element_master em, " +
	" component_master cm,material_master mm,brand_master br where md.Project_element_id=pe.Project_element_id " +
	" and pe.brand_id = br.brand_id  and pe.element_id=em.element_id and pe.component_id=cm.component_id  " +
	" and pe.material_id=mm.material_id and md.Project_store_id= ?";*/
    if(fd_division_name.equalsIgnoreCase("Store Branding")){
     query2="SELECT distinct pe.project_name,cm.component_name,mm.material_name,COALESCE(psem.quantity,0)quantity,br.brand_name FROM fourth_dimension.measurement_data md," +
    		"Project_elements pe,element_master em,component_master cm,material_master mm,brand_master br,project_store_element_mapping psem where " +
    		"md.Project_element_id=pe.Project_element_id and pe.brand_id = br.brand_id and pe.element_id=em.element_id and " +
    		"pe.component_id=cm.component_id and pe.material_id=mm.material_id and md.project_element_id=psem.project_element_id " +
    		"and md.project_store_id=psem.project_store_id and md.Project_store_id=?";
    	}else{
    		query2="SELECT pe.project_name,cm.component_name,mm.material_name,COALESCE(md.quantity,0)quantity ,br.brand_name " +
    				"FROM fourth_dimension.measurement_data md," +
    				"Project_elements pe," +
    				"element_master em," +
    				"component_master cm," +
    				"material_master mm," +
    				"brand_master br " +
    				"where md.Project_element_id=pe.Project_element_id" +
    				" and pe.brand_id = br.brand_id " +
    				"and pe.element_id=em.element_id " +
    				"and pe.component_id=cm.component_id " +
    				"and pe.material_id=mm.material_id " +
    				" and md.Project_store_id=?";
    		}
    
    
    try {
	    	con=cp.getConnection();
	    	
			ps = con.prepareStatement(query1);
			ps.setInt(1, ProjectId);
			rs = ps.executeQuery();
			while (rs.next()) {
	        System.out.println("Store Id " + rs.getInt(1));
	      
				PreparedStatement pstForJobCardDetails = con.prepareStatement(query2);
				pstForJobCardDetails.setInt(1, rs.getInt(1));
				ResultSet rsForJobCardDetails = pstForJobCardDetails.executeQuery();
				while(rsForJobCardDetails.next()){
					HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
					outDataHashMap.put("Project_store_id", rs.getInt(1));
					outDataHashMap.put("job_card_id", rs.getInt(2));
					if(rs.getString(7).equals("MnD")){
						outDataHashMap.put("job_card_number", "Mnd" + rs.getString(3));
					}else if(rs.getString(7).equals("Store Branding")){
						outDataHashMap.put("job_card_number", "SB" +rs.getString(3));
					}
					
					outDataHashMap.put("storeName", rs.getString(4));
					if(rs.getString(5)!= null){
						outDataHashMap.put("address", rs.getString(5));	
					}else{
						outDataHashMap.put("address", "NA");
					}
					if(rs.getString(6)!= null){
						outDataHashMap.put("city_name", rs.getString(6));	
					}else{
						outDataHashMap.put("city_name", "NA");
					}
					outDataHashMap.put("element_name", rsForJobCardDetails.getString(1));
					outDataHashMap.put("component_name", rsForJobCardDetails.getString(2));
					outDataHashMap.put("material_name", rsForJobCardDetails.getString(3));
					outDataHashMap.put("quantity", rsForJobCardDetails.getString(4));
					outDataHashMap.put("brandName",rsForJobCardDetails.getString(5));
					
					outDataList.add(outDataHashMap);
				}
		}	
	} catch (Exception e) {
		System.out.println(e);
		e.printStackTrace();
		
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
			System.out.println(e);
		}

	}
return outDataList;
}

	
	public boolean isProjectStoreMapping(int storeId)  {
		
		boolean isMapping=false;
		
		  String query1 = "SELECT * FROM fourth_dimension.Project_store_element_mapping_mnd " +
		  		"where Project_store_id="+storeId;
		    try {
		    	con=cp.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				if (rs.next()) {
			
					isMapping=true;
				    
				   }
			} catch (Exception e) {
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
					System.out.println(e);
				}

			}
		return isMapping;
		}
	
	public HashMap<String,Object>getProjectStoreByStoreId(int storeId)  {
	
	 HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
	  String query1 = "SELECT city_name,region_name,state_name,address,Project_store_status_id FROM " +
	    		"fourth_dimension.Project_stores ps where" +
	    		" Project_store_id="+storeId;
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
	        
				
			    outDataHashMap.put("cityName", rs.getString(1));
			    outDataHashMap.put("regionName", rs.getString(2));
			    outDataHashMap.put("stateName", rs.getString(3));
			    outDataHashMap.put("address", rs.getString(4));
			    outDataHashMap.put("storeStatusId", rs.getInt(5));
			   
			
			}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataHashMap;
	}
	
	
	public HashMap<String,Object>getMappedProjectStoreByStoreId(int storeId)  {
		
		 HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		  String query1 = "SELECT city_name,region_name,state_name,job_card_number,job_card_date,address,Project_store_status_id FROM " +
		    		"fourth_dimension.Project_stores ps,Project_store_element_mapping_mnd psemm  " +
		    		"where ps.Project_store_id=psemm.Project_store_id  and ps.Project_store_id="+storeId;
		    try {
		    	con=cp.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				if (rs.next()) {
		        
					
				    outDataHashMap.put("cityName", rs.getString(1));
				    outDataHashMap.put("regionName", rs.getString(2));
				    outDataHashMap.put("stateName", rs.getString(3));
				    outDataHashMap.put("jcNumber", rs.getString(4));
				SimpleDateFormat fmt = new java.text.SimpleDateFormat(
						"dd/MM/yy");
				SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -100);
				sdf.set2DigitYearStart(cal.getTime());

		
				    
				    outDataHashMap.put("jcDate", fmt.format(sdf.parse(rs.getString(5))));
				    outDataHashMap.put("address", rs.getString(6));
				    outDataHashMap.put("storeStatusId", rs.getInt(7));
				   
				
				}
			} catch (Exception e) {
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
					System.out.println(e);
				}

			}
		return outDataHashMap;
		}
	
	public List<HashMap<String,Object>> getProjectElementDropDownByProjectId(int ProjectId)  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String query1 = "SELECT pe.Project_element_id,em.element_name from "+
            "fourth_dimension.Project_elements pe,element_master em "+
            "where  pe.element_id=em.element_id and "+
            "pe.Project_id="+ProjectId+" order by em.element_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			    outDataHashMap.put("elementId", rs.getInt(1));
			    outDataHashMap.put("elementName", rs.getString(2));
			   
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
	public List<HashMap<String,Object>> getMappedProjectElementDropDownByProjectIdAndStoreId(int ProjectId,int storeId)  {
	
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
	    
		
	    String query1 = "SELECT psemm.Project_element_id,element_name,bm.brand_name,pe.project_code," +
	    		"psemm.element_status,psemm.quantity,psemm.comments " +
	    		"FROM fourth_dimension.Project_store_element_mapping_mnd psemm,Project_elements pe ," +
	    		"brand_master bm ,element_master em where  psemm.Project_element_id=pe.Project_element_id=em.element_id " +
	    		"and pe.brand_id=bm.brand_id and psemm.Project_store_id="+storeId+" order by element_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			
			while (rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			
				outDataHashMap.put("elementId", rs.getInt(1));
			    outDataHashMap.put("elementName",rs.getString(2));
			    
			    outDataHashMap.put("brandName", rs.getString(3));
			    outDataHashMap.put("projectCode", rs.getString(4));
			    
			    outDataHashMap.put("elementStatus", rs.getString(5));
			    outDataHashMap.put("quantity", rs.getInt(6));
			    outDataHashMap.put("comments", rs.getString(7));
			   
				outDataList.add(outDataHashMap);
			}
	

		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
	public boolean isProjectStoreElementMapping(int storeId,int elementId)  {
		
		boolean isMapping=false;
		
		  String query1 = "SELECT * FROM fourth_dimension.Project_store_element_mapping_mnd " +
		  		"where Project_store_id="+storeId+" and Project_element_id="+elementId;
		    try {
		    	con=cp.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				if (rs.next()) {
			
					isMapping=true;
				    
				   }
			} catch (Exception e) {
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
					System.out.println(e);
				}

			}
		return isMapping;
		}
	public HashMap<String,Object>getProjectStoreElementByElementId(int storeId,int elementId)  {
		
		 HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		  String query1 = "SELECT bm.brand_name,pe.project_code,psemm.element_status,psemm.quantity,psemm.comments "+ 
                          "FROM fourth_dimension.Project_store_element_mapping_mnd psemm,Project_elements pe "+
                          ",brand_master bm where psemm.Project_element_id=pe.Project_element_id "+
                          "and pe.brand_id=bm.brand_id and psemm.Project_store_id="+storeId+"" +
                          " and psemm.Project_element_id="+elementId;
		    try {
		    	con=cp.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				if (rs.next()) {
					
					outDataHashMap.put("brand", rs.getString(1));
				    outDataHashMap.put("projectCode", rs.getString(2));
				    outDataHashMap.put("elementStatus", rs.getString(3));
				    outDataHashMap.put("quantity", rs.getString(4));
				    outDataHashMap.put("comments", rs.getString(5));
					
				    
				   }
			} catch (Exception e) {
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
					System.out.println(e);
				}

			}
		return outDataHashMap;
		}
	
	
	public HashMap<String,Object>getProjectElementByElementId(int elementId)  {
		
		 HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		  String query1 = "SELECT bm.brand_name,pe.project_code "+ 
                         "FROM fourth_dimension.Project_elements pe "+
                         ",brand_master bm where  "+
                         " pe.brand_id=bm.brand_id and pe.Project_element_id="+elementId;
		    try {
		    	con=cp.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				if (rs.next()) {
					
					outDataHashMap.put("brand", rs.getString(1));
				    outDataHashMap.put("projectCode", rs.getString(2));
				
					
				    
				   }
			} catch (Exception e) {
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
					System.out.println(e);
				}

			}
		return outDataHashMap;
		}
	
	
	public int getJobCardNumberForMnd()  {
		
		 int jobCardNumber = 1;
		  /*String query1 = "SELECT job_card_number FROM fourth_dimension.Project_store_element_mapping_mnd" +
		  		"  where Project_store_id in(select Project_store_id from " +
		  		" fourth_dimension.Project_stores where Project_id="+ProjectId+") order by job_card_number desc";*/
		  String query1 = "SELECT * FROM fourth_dimension.job_cards order by job_card_number desc"; 
		    try {
		    	con=cp.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(query1);
				if (rs.next()) {
					jobCardNumber= Integer.parseInt(rs.getString(3)) + 1;
			   }
			} catch (Exception e) {
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
					System.out.println(e);
				}

			}
		return jobCardNumber;
		}
	
	public List<HashMap<String,Object>> getElementStatus()  {
		
	    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
		
	    String query1 = "SELECT * FROM fourth_dimension.element_status_master order by element_status_name";
	    try {
	    	con=cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
	        
				HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
			    outDataHashMap.put("elementId", rs.getInt(1));
			    outDataHashMap.put("elementName", rs.getString(2));
			   
				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return outDataList;
	}
	
	public int setJobCardForMnd(int ProjectId,int storeId[],String jcNumber[],String jcDate[])  {
		status=-1;
		
      try {
    	  con=cp.getConnection();
	    	     
    		for (int i = 0; i < storeId.length; i++) {
    			ps = con.prepareStatement("SELECT * FROM fourth_dimension.job_cards WHERE project_store_id =?");
    			ps.setInt(1, storeId[i]);
    			rs = ps.executeQuery();
    			if(!rs.next()){
    		   			ps=con.prepareStatement("insert into fourth_dimension.job_cards values " +
			    					"(?,?,?,?,?) ");
						ps.setInt(1, 0);
						ps.setInt(2, storeId[i]);
						ps.setString(3, jcNumber[i]);
							SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yy");
							SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
							Calendar cal = Calendar.getInstance();
							cal.add(Calendar.YEAR, -100);
							sdf.set2DigitYearStart(cal.getTime());
						ps.setString(4, fmt.format(sdf.parse(jcDate[i])));
						ps.setInt(5, ProjectId);
						flag1 = flag1 + ps.executeUpdate();
			    		}
				    	
//						if(flag1==storeId.length){
							status=0;
//						}
    		}
			
		} catch (Exception e) {
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
					System.out.println(e);
				}

			}
		return status;
		}
	
	
	
	
	public int setElementStatus(int ProjectId,int storeId[],
			int elementId[],String elementStatus[])  {
		
		status=-1;
		flag1=0;
		
	      try {
	    	  con=cp.getConnection();
	    	  
	    	     
	    	    	 
	    	    		
    			
	    	    	 
	    	     
	    		for (int i = 0; i < storeId.length; i++) {
	    			
	    			st=con.createStatement();
	    			rs=st.executeQuery("select * from fourth_dimension.Project_store_element_mapping_mnd " +
	    					" where Project_store_id ="+storeId[i]+" and Project_element_id="+elementId[i]+"");
	    			
				if (rs.next()) {
					
					System.out.println("if condition1 "+storeId[i]);
					ps = con.prepareStatement("update fourth_dimension.Project_store_element_mapping_mnd set "
						+ "element_status=? where Project_store_id =? and Project_element_id=?");
					ps.setString(1, elementStatus[i]);
					ps.setInt(2, storeId[i]);
					ps.setInt(3, elementId[i]);
		
					flag1 = flag1 + ps.executeUpdate();

				}
				
				else{
                  System.out.println("else condition1"+storeId[i]);
	    			ps=con.prepareStatement("insert into fourth_dimension.Project_store_element_mapping_mnd values " +
	    					"(?,?,?,?,?,?,?,?) ");
	    			ps.setInt(1, 0);
	    			ps.setInt(2,elementId[i]);
	    			ps.setInt(3,storeId[i]);
	    			ps.setString(4, elementStatus[i]);
	    			ps.setInt(5, 0);
	    			ps.setString(6, "");
	    			ps.setInt(7, 0);
	    			ps.setString(8, null);
	    		 flag1=flag1+ps.executeUpdate();
	    		 System.out.println("else condition2");
				}
	    		}
		    	System.out.println("flag is "+flag1);
				if(flag1>=storeId.length){
					status=0;
				}
			
			
			} catch (Exception e) {
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
					System.out.println(e);
				}

			}
		return status;
		}
	
	
	
	
	public int setStoreStatus(int ProjectId,int storeId[],int storeStatusId[])  {
		
		status=-1;
		
	      try {
	    	  con=cp.getConnection();
	        for (int i = 0; i < storeId.length; i++) {
	    			
	    		
	    			ps=con.prepareStatement("update  fourth_dimension.Project_stores" +
	    			" set Project_store_status_id=? where Project_id=? and Project_store_id=?");
	    			ps.setInt(1, storeStatusId[i]);
	    			ps.setInt(2,ProjectId);
	    			ps.setInt(3,storeId[i]);
	    			
	    		 flag1=flag1+ps.executeUpdate();
	    		}
		    	
				if(flag1==storeId.length){
					status=0;
				}
			
			
			} catch (Exception e) {
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
					System.out.println(e);
				}

			}
		return status;
		}
	
public String updateMndDetails(int ProjectId,int storeId,int storeStatusId,int quantity,String comments,int elementStatusId,int elementId,int element_mapping_Id)  {
		System.out.println("Inside updateMndDetails()");
		boolean flag =false;
		String response = "Data Not Updated";
	      try {
	    	  con=cp.getConnection();
	    		System.out.println("projectId" + ProjectId);
	    		System.out.println("storeId" + storeId);
	    		System.out.println("storeStatusid" + storeStatusId);
	    		System.out.println("quantity" + quantity);
	    		System.out.println("comments" + comments);
	    		System.out.println("elementSStatusId" + elementStatusId);
	    		System.out.println("elementId" + elementId);
	    		System.out.println("element_mappingIdId" + element_mapping_Id);
	    		
	    			ps=con.prepareStatement("update  fourth_dimension.Project_stores" +
	    			" set store_status_id=? where Project_id=? and Project_store_id=?");
	    			ps.setInt(1, storeStatusId);
	    			ps.setInt(2,ProjectId);
	    			ps.setInt(3,storeId);
	    			
	    		  int result =  ps.executeUpdate();
	    		  if(result > 0){
	    			  System.out.println("Status related to store updated");
	    			  ps = con.prepareStatement("update fourth_dimension.project_store_element_mapping" +
	    			  		" set quantity=?,element_status_id = ?, comments= ? where project_store_id=? and project_element_id=? and project_element_mapping_id=?");
	    			  ps.setInt(1, quantity);
	    			  ps.setInt(2, elementStatusId);
	    			  ps.setString(3, comments);
	    			  ps.setInt(4, storeId);
	    			  ps.setInt(5, elementId);
	    			  ps.setInt(6, element_mapping_Id);
	    			  int val = ps.executeUpdate();
	    			  System.out.println("value of return" + val);
	    			  if(val >0){
	    				  	flag = true;
	    				  System.out.println("Status related to mapping updated");
	    			  }else{
	    				  flag = false;
	    			  }
	    		  }
				if(flag){
					response = "Data Updated";
				}
			
			
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
				
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
					System.out.println(e);
				}

			}
		return response;
		}

	


public List<HashMap<String,Object>> getProjectDropDown()  {
	
    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
	
    String query1 = "SELECT Project_id, Project_name FROM fourth_dimension.Project order by Project_name";
    try {
    	con=cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {
        
			HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		    outDataHashMap.put("ProjectId", rs.getInt(1));
		    outDataHashMap.put("ProjectName", rs.getString(2));
		   
			outDataList.add(outDataHashMap);
		}
	} catch (Exception e) {
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
			System.out.println(e);
		}

	}
return outDataList;
}



public int setProjectStatus(int ProjectId,String ProjectStatus,String statusDate)  {
	
	status=-1;
	
      try {
    	  con=cp.getConnection();

    	    ps=con.prepareStatement("update  fourth_dimension.Project set status=?," +
    	    		" status_date=? where Project_id=?");
    			
			ps.setString(1, ProjectStatus);
			
			SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd/MM/yy");
			SimpleDateFormat fmt = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -100);
			sdf.set2DigitYearStart(cal.getTime());

			ps.setString(2, fmt.format(sdf.parse(statusDate)));
			ps.setInt(3,ProjectId);
			 
			flag1 =  ps.executeUpdate();
    		
	    	
			if(flag1>=1){
				status=0;
			}
		
		
		} catch (Exception e) {
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
				System.out.println(e);
			}

		}
	return status;
	}

public List<HashMap<String,Object>> getProjectDropDownByProjectId(int ProjectId)  {
	
    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
	
    String query1 = "SELECT Project_element_id, project_name FROM " +
    		"fourth_dimension.Project_elements where Project_id="+ProjectId+" order by project_name";
    try {
    	con=cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {
        
			HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		    outDataHashMap.put("projectId", rs.getInt(1));
		    outDataHashMap.put("projectName", rs.getString(2));
		   
			outDataList.add(outDataHashMap);
		}
	} catch (Exception e) {
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
			System.out.println(e);
		}

	}
return outDataList;
}


public List<HashMap<String,Object>> getProjectStageDropDown()  {
	
    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
	
    String query1 = "SELECT stage_id, stage_name FROM " +
    		"fourth_dimension.stage_master order by stage_name ";
    try {
    	con=cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {
        
			HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		    outDataHashMap.put("stageId", rs.getInt(1));
		    outDataHashMap.put("stageName", rs.getString(2));
		   
			outDataList.add(outDataHashMap);
		}
	} catch (Exception e) {
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
			System.out.println(e);
		}

	}
return outDataList;
}


public List<HashMap<String,Object>> getProjectStatusDropDownByStageId(int stageId)  {
	
    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
	
    String query1 = "SELECT project_status_id, project_status_name FROM " +
    		"fourth_dimension.project_status_master where stage_id="+stageId+" order by project_status_name";
    try {
    	con=cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {
        
			HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		    outDataHashMap.put("statusId", rs.getInt(1));
		    outDataHashMap.put("statusName", rs.getString(2));
		   
			outDataList.add(outDataHashMap);
		}
	} catch (Exception e) {
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
			System.out.println(e);
		}

	}
return outDataList;
}


public List<HashMap<String,Object>> getProjectScopeOfWorkDropDownByStageId(int stageId)  {
	
    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
	
    String query1 = "SELECT project_scope_of_work_id, project_scope_of_work_name FROM " +
    		"fourth_dimension.project_scope_of_work where stage_id="+stageId+" order by project_scope_of_work_name";
    try {
    	con=cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {
        
			HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		    outDataHashMap.put("sowId", rs.getInt(1));
		    outDataHashMap.put("sowName", rs.getString(2));
		   
			outDataList.add(outDataHashMap);
		}
	} catch (Exception e) {
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
			System.out.println(e);
		}

	}
return outDataList;
}

public HashMap<String,Object> getProjectDetailByProjectId(int projectId)  {
	
	 
	HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
    String query1 = "SELECT brand_name,project_code,hub_name,stage_id,project_status_id,project_scope_of_work_id, " +
    		" project_status_date from fourth_dimension.Project_elements pe,fourth_dimension.brand_master bm," +
    		"fourth_dimension.fd_hub_master fhm where pe.brand_id=bm.brand_id and pe.hub_id=fhm.fd_hub_id" +
    		" and Project_element_id="+projectId;
    try {
    	con=cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		if(rs.next()) {
        

		    outDataHashMap.put("brandName", rs.getString(1));
		    outDataHashMap.put("projectCode", rs.getString(2));
		    outDataHashMap.put("hubName", rs.getString(3));
		    outDataHashMap.put("projectStageId", rs.getInt(4));
		    outDataHashMap.put("projectStatusId", rs.getInt(5));
		    outDataHashMap.put("psowId", rs.getString(6));
	
		 	    
				SimpleDateFormat fmt = new java.text.SimpleDateFormat(
						"dd/MM/yy");
				SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -100);
				sdf.set2DigitYearStart(cal.getTime());
				
				outDataHashMap.put("statusDate", fmt.format(sdf.parse(rs.getString(7))));
			
		   
		}
	} catch (Exception e) {
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
			System.out.println(e);
		}

	}
return outDataHashMap;
}

public List<HashMap<String,Object>> getElementDropDownByProjectId(int ProjectId)  {
	
    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
	
    String query1 = "SELECT Project_element_id, element_name FROM " +
    		"fourth_dimension.Project_elements pe,element_master em" +
    		" where pe.element_id=em.element_id and Project_id="+ProjectId+" order by element_name";
    try {
    	con=cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {
        
			HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		    outDataHashMap.put("elementId", rs.getInt(1));
		    outDataHashMap.put("elementName", rs.getString(2));
		   
			outDataList.add(outDataHashMap);
		}
	} catch (Exception e) {
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
			System.out.println(e);
		}

	}
return outDataList;
}

public List<HashMap<String,Object>> getElementStatusDropDown()  {
	
    List<HashMap<String,Object>>outDataList=new ArrayList<HashMap<String,Object>>();
	
    String query1 = "SELECT element_status_id, element_status_name FROM " +
    		"fourth_dimension.element_status_master order by element_status_name";
    try {
    	con=cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {
        
			HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
		    outDataHashMap.put("statusId", rs.getInt(1));
		    outDataHashMap.put("statusName", rs.getString(2));
		   
			outDataList.add(outDataHashMap);
		}
	} catch (Exception e) {
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
			System.out.println(e);
		}

	}
return outDataList;
}



public HashMap<String,Object> getElementDetailByElementId(int elementId)  {
	
	 
	HashMap<String,Object> outDataHashMap=new HashMap<String,Object>();
    String query1 = "SELECT brand_name,project_code,es.status_id,es.status_date " +
    		"  from fourth_dimension.Project_elements pe,fourth_dimension.brand_master bm," +
    		"fourth_dimension.element_status es where pe.brand_id=bm.brand_id and pe.Project_element_id=es.element_id" +
    		" and pe.Project_element_id="+elementId;
    try {
    	con=cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		if(rs.next()) {
        

		    outDataHashMap.put("brandName", rs.getString(1));
		    outDataHashMap.put("elementCode", rs.getString(2));
		    outDataHashMap.put("elementStatusId", rs.getInt(3));
	
	
		 	    
				SimpleDateFormat fmt = new java.text.SimpleDateFormat(
						"dd/MM/yy");
				SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -100);
				sdf.set2DigitYearStart(cal.getTime());
				
				outDataHashMap.put("statusDate", fmt.format(sdf.parse(rs.getString(4))));
			
		   
		}
	} catch (Exception e) {
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
			System.out.println(e);
		}

	}
return outDataHashMap;
}

}
