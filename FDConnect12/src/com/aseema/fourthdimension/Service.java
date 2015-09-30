package com.aseema.fourthdimension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fd.utility.Util;

public class Service {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/fourth_dimension";
	static String userName = "root";
//	static String url = "jdbc:mysql://localhost:3306/fourth_dimension";
//	static String userName = "root";
	static String password = "root";
	static int initialConnections = 5;
	static int maxConnections = 45;
	Connection con = null;
	static ConnectionPool cp;
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
	String[] parseData;
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
/*	//Drop Down List
public String dropDownMaterialGroup(){
		response_data="";
	try{
		query1="select materialgroupID,materialgroupName from masterialgroupmaster";
		con = cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {
		response_data=response_data+rs.getInt(1)+"-"+rs.getString(2)+columnSeperator;
		}
		if(!response_data.equals(""))
		{
			return response_data;
		}
	}catch (Exception e) {
		e.printStackTrace();
		
	}
		return "Error";
}

public String dropDownMaterialSubGroup(){
		response_data="";
	try{
		
		query1="select materialsubgroupmasterID,materialsubgroupmasterName from materialsubgroupmaster";
		con = cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {
		response_data=response_data+rs.getInt(1)+"-"+rs.getString(2)+columnSeperator;
		}
		if(!response_data.equals(""))
		{
			return response_data;
		}
	}catch (Exception e) {
		e.printStackTrace();
		
	}
		return "Error";
}
*/

public void UpdateLogOutTime(String username,String employee_id){
	try{
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss dd/mm/yyyy");
		Date date=new Date();
			
			
		query1="update logindata set LogoutDateTime=? where EmployeeID=?";
		Connection();
		ps=con.prepareStatement(query1);
		ps.setString(1,sdf.format(date));
		ps.setString(2,employee_id);
		ps.executeUpdate();
	}catch (Exception e) {
			e.printStackTrace();
	} 
	finally{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

	
	public String Authentication(String inData){
		
		String[] split_inData=inData.split("#&#");
		StringBuilder result = new StringBuilder();
		/*Username Alpha-Numeric*/
		boolean hasNonAlpha = split_inData[0].matches("^.*[^a-zA-Z0-9 ].*$");
		System.out.println("boolean username "+hasNonAlpha);
		if(hasNonAlpha)
			return "AlphaNumeric Value";
		
		/*Password Alpha-Numweric*/
		hasNonAlpha = split_inData[1].matches("^.*[^a-zA-Z0-9 ].*$");
		System.out.println("boolean password "+hasNonAlpha);
		if(hasNonAlpha)
			return "AlphaNumeric Value";
			try{
				
				SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss dd/mm/yyyy");
				Date date=new Date();
				
				Connection();
				ps = con.prepareStatement("SELECT employeeid,employee_name,emp.role_id,role_name " +
						" FROM fourth_dimension.fd_employee_master emp,fourth_dimension.fd_role_master role " +
						" WHERE emp.role_id = role.role_id AND username = ? AND password = ?");
				ps.setString(1, split_inData[0]);
				ps.setString(2, split_inData[1]);
				rs = ps.executeQuery();
				if(rs.next()){
					result.append(rs.getString(2));
					result.append(rs.getString(4));
					ps=con.prepareStatement("insert into logindata(EmployeeID,LoginDateTime) values(?,?)");
					ps.setInt(1,rs.getInt(1));
					ps.setString(2,sdf.format(date));
					ps.executeUpdate();
					
				}
			}catch (Exception e) {
				System.out.println("Logs not maintained"); 
				e.printStackTrace();
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "Authenticated";
	}
	
	
	
public List ValidateUser(String login_UserName,String login_Password) throws SQLException
{
		PreparedStatement pstForLog = null;
		System.out.println("Inside service Class login_username is:\t" + login_UserName);
		System.out.println("Inside service Class login_password is:\t" + login_Password);
		List result = new ArrayList();
		try{
			Connection();
			SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss dd/mm/yyyy");
			Date date=new Date();
			ps = con.prepareStatement("SELECT employeeid,employee_name,emp.role_id,role_name " +
					" FROM fourth_dimension.fd_employee_master emp,fourth_dimension.fd_role_master role " +
					" WHERE emp.role_id = role.role_id AND username = ? AND password = ?");
			ps.setString(1, login_UserName);
			ps.setString(2, login_Password);
			rs = ps.executeQuery();
			if(rs.next()){
				result.add(rs.getInt(1));
				result.add(rs.getString(2));
				result.add(rs.getString(4));
				pstForLog = con.prepareStatement("INSERT INTO fourth_dimension.logindata(EmployeeId,LoginDateTime) VALUES (?,?)");
				pstForLog.setInt(1, rs.getInt(1));
				pstForLog.setString(2, sdf.format(date));
				int rowsAffected = pstForLog.executeUpdate(); 
				System.out.println("No of rows Affected:\t" + rowsAffected);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(pstForLog != null){
					pstForLog.close();
			}
		}
			if(result.size()>1)
				return result;
			else
				result.add("Not Valid User");
				return result ;
}
	
	/* Manage Places 

	// Country Administration

	// Output=autoID+columnSeperator+countryId+columnSeperator+countryName+rowSeperator
	public String getCountry(String inData)  {
		
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "select country_id,country_name from fourth_dimension.country_master";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				rowCount++;
				data = data + rs.getInt(1) + columnSeperator + rs.getString(2)
				+ rowSeperator;
			}
			if(!data.equalsIgnoreCase("")){
				return data;
			}
			else
			{
				data = "DataUnavailable";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			status = -4;
			data = "Exception:-" + e;
			data= "Exception";
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				status = -4;
				data = "Exception";
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		return outData;
		return data;
	}

	// Input=countryID+ColumnSeperator+countryName;

	public int setCountry(String inData)  {
		String countryName;
		int countryID;
	    status =0;
		flag1 = 0;
		String[] countryData=inData.split(columnSeperator);
		
		for(int i=0;i<countryData.length;i++){
			if ((countryData[i] == null) || countryData[i].equals("")) {
				status = -2;
			    return status;
			}
		}
		countryName = countryData[1];
		countryID =Integer.parseInt(countryData[0]);
		System.out.println(countryName+"       "+countryID);
		//select country_id,country_name from fourth_dimension.country_master
		
		query1 = "select * from fourth_dimension.country_master where country_name='"
				+ countryName + "' and country_id='"+countryID+"'";
		query2 = "insert into fourth_dimension.country_master(country_id,country_name) values(?,?)";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);

			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, countryID);
			ps.setString(2, countryName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// Input=country_id;

	public int deleteCountry(String inData)  {
		int countryId;
		status =0;
		flag1 = 0;
		
		if ((inData == null) || inData.equals("")) {
			status =-2;
			return status;
		}

		try {
			countryId = Integer.parseInt(inData);
			
			System.out.println("Delete Id"+countryId);
			query1 = "delete from fourth_dimension.country_master where country_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, countryId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
		
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}

		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;
	}

	// Input=autoId+rowSeperator+countryId+columnSeperator+countryName;

	public int updateCountry(String inData){
		status = 0;
		flag1 = 0;

		String[] countryData=inData.split(columnSeperator);
		
		for(int i=0;i<countryData.length;i++){
			if ((countryData[i] == null) || countryData[i].equals("")) {
				status = -2;
			    return status;
			}
		}
		
		try{
		
		String query="update country_master set country_name=? where country_id=?";
		con = cp.getConnection();
		ps = con.prepareStatement(query);
		ps.setString(1,countryData[0]);
		ps.setInt(2,Integer.parseInt(countryData[1]));
		
		flag1 = ps.executeUpdate();
		if(flag1>0){
			status=0;
		}
		
	} catch (Exception e) {
		status=-4;

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
			status=-4;
		}
	}

	return status;
	
}
	
	
	
	
	
	public int updateCountry(String inData)  {
		int countryId;
		status = 0;
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status =-2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				countryId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status =-2;
				
				return status;

			}
			query1 = "describe fourth_dimension.countrymaster";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status =-2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {

				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 0)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.countrymaster set " + columnName[i]
						+ " =?  where id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, countryId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status=-4;

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
				status=-4;
			}

		}
		if ((flag1 < flagCheck)) {
			status=-5;
		}

		return status;
	}
	
	
	
	
	

	// Region Administration

	// Input=countryId
	// Output=regionId+columnSeperator+regionName+columnSeperator+rowSeperator
	public String getRegion(String inData)  {
		int countryId;
		int regionId;
		String regionName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			countryId = Integer.parseInt(inData);
			query1 = "select id,name from fourth_dimension.region where country_id="
					+ countryId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				regionId = rs.getInt(1);
				regionName = rs.getString(2);

				rowCount++;
				data = data + regionId + columnSeperator + regionName
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

	// Input=countryId+columnSeperator+regionName
	public int setRegion(String inData)  {
		int countryId;
		String regionName;
		status = 0;
		    
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {
				countryId = Integer.parseInt(columnData[0]);
				regionName = columnData[1];

			} else {
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.region where name='"
					+ regionName + "'";
			query2 = "insert into fourth_dimension.region values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, countryId);
			ps.setInt(2, 0);
			ps.setString(3, regionName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// Input=regionId
	public int deleteRegion(String inData)  {
		int regionId;
		status = 0;
	
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		try {
			regionId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.region where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, regionId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
		}

	return 	status;
	}

	// input=regionId+rowSeperator+countryId+columnSeperator+regionId+columnSeperator+regionName
	public int updateRegion(String inData)  {
		int regionId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				regionId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;

			}
			query1 = "describe fourth_dimension.region";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.region set " + columnName[i]
						+ " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, regionId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	
	}

	// State Administration

	// input=regionId
	// output=stateId+columnSeperator+stateName+columnSeperator+rowSeperator
 
	public String getState(String inData)  {
		int regionId;
		int stateId;
		String stateName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data="Input data is insufficient for operation";
			outData=status+statusSeperator+data;
			return outData;
		}
		try {
			regionId = Integer.parseInt(inData);
			query1 = "select id,name from fourth_dimension.state where region_id="
					+ regionId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				stateId = rs.getInt(1);
				stateName = rs.getString(2);

				rowCount++;
				data = data + stateId + columnSeperator + stateName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception";
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
				data="exception";
			}

		}
		if (rowCount == 0) {
			status = -1;
			data="Data Unavailable";
		}
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=regionId+columnSeperator+stateName
	public int setState(String inData)  {
		int regionId;
		String stateName;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		columnData = inData.split(columnSeperator);

		try {

			if (columnData.length == 2) {
				regionId = Integer.parseInt(columnData[0]);
				stateName = columnData[1];

			} else {
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.state where name='"
					+ stateName + "'";
			query2 = "insert into fourth_dimension.state values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, regionId);
			ps.setInt(2, 0);
			ps.setString(3, stateName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
		
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

	// input=stateId
	public int deleteState(String inData)  {
		int stateId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		try {
			stateId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.state where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, stateId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
		}

	return	status;
	}

	// input=stateId+rowSeperator+regionId+columnSeperator+stateId+columnSeperator+stateName
	public int updateState(String inData)  {
		int stateId;
		status = 0;

		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				stateId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;

			}
			query1 = "describe fourth_dimension.state";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.state set " + columnName[i]
						+ " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, stateId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
	

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

	
		return status;
	}

	// City Administration

	// input=stateId
	// output=cityId+columnSeperator+cityName+columnSeperator+rowSeperator
	public String getCity(String inData)  {
		int stateId;
		int cityId;
		String cityName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			stateId = Integer.parseInt(inData);
			query1 = "select id,name from fourth_dimension.city where state_id="
					+ stateId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				cityId = rs.getInt(1);
				cityName = rs.getString(2);

				rowCount++;
				data = data + cityId + columnSeperator + cityName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status= -4;
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
				status=-4;
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

	// input=stateId+columnSeperator+cityName
	public int setCity(String inData)  {
		int stateId;
		String cityName;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {
				stateId = Integer.parseInt(columnData[0]);
				cityName = columnData[1];

			} else {
				status=-2;
				return status;
			}
			query1 = "select * from fourth_dimension.city where name='"
					+ cityName + "'";
			query2 = "insert into fourth_dimension.city values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status=-3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, stateId);
			ps.setInt(2, 0);
			ps.setString(3, cityName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status=-4;
			
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
				status=-4;
			
			}

		}
		if ((flag1 == 0)) {
			status=-5;
		
		}

		
		return status;
	}

	// input=cityId
	public int deleteCity(String inData)  {
		int cityId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;
		}
		try {
			cityId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.city where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, cityId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status=-4;
		
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status=-4;
			}
		}
		if ((flag1 == 0)) {
			status=-5;
		}

		return status;
	}

	// input=cityId+rowSeperator+stateId+columnSeperator+cityId+columnSeperator+cityName
	public int updateCity(String inData)  {
		int cityId;
		status = 0;
	
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				cityId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status=-2;
				return status;

			}
			query1 = "describe fourth_dimension.city";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status=-2;
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.city set " + columnName[i]
						+ " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, cityId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status=-4;
			

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
				status=-4;
			}

		}
		if ((flag1 < flagCheck)) {
			status=-5;
		}

		return status;
	}

	// Area Administration

	// input=cityId
	// output=areaId+columnSeperator+areaName+columnSeperator+rowSeperator

	public String getArea(String inData)  {
		int cityId;
		int areaId;
		String areaName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {

			cityId = Integer.parseInt(inData);
			query1 = "select id,name from fourth_dimension.area where city_id="
					+ cityId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				areaId = rs.getInt(1);
				areaName = rs.getString(2);

				rowCount++;
				data = data + areaId + columnSeperator + areaName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
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
				status=-4;
				data="Exception:-"+e;
			}

		}
		if (rowCount == 0) {
			status=-1;
			data="Data Unavailable";
		}
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=cityId+columnSeperator+areaName
	public int setArea(String inData)  {
		int cityId;
		String areaName;
		status = 0;
	
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {
				cityId = Integer.parseInt(columnData[0]);

				areaName = columnData[1];

			} else {
				status=-2;
				return status;
			}
			query1 = "select * from fourth_dimension.area where name='"
					+ areaName + "'";
			query2 = "insert into fourth_dimension.area values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status=-3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, cityId);
			ps.setInt(2, 0);
			ps.setString(3, areaName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status=-4;
			
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
				status=-4;
			}

		}
		if ((flag1 == 0)) {
			status=-5;
		}

		return status;
	}

	// input=areaId
	public int deleteArea(String inData)  {
		int areaId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;
		}
		try {
			areaId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.area where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, areaId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status=-4;
		
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status=-4;
			}
		}
		if ((flag1 == 0)) {
			status=-5;
		}

	return	status;
	}

	// input=areaId+rowSeperator+cityId+columnSeperator+areaId+columnSeperator+areaName
	public int updateArea(String inData)  {
		int areaId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				areaId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status=-2;
				return status;

			}
			query1 = "describe fourth_dimension.area";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status=-2;
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.area set " + columnName[i]
						+ " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, areaId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status=-4;
			

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
				status=-4;
			}

		}
		if ((flag1 < flagCheck)) {
			status=-5;
		}

	return	status;
	}

	 Manage Comapany 

	// Company Administration

	// output=companyId+columnSeperator+companyName+columnSeperator+rowSeperator
	public String getCompany(String inData)  {
		int companyId;
		String companyName;
		status = 0;
		data = "";

		query1 = "select id,name from fourth_dimension.company ";
		rowCount = 0;
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				companyId = rs.getInt(1);
				companyName = rs.getString(2);

				rowCount++;
				data = data + companyId + columnSeperator + companyName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status=-4;
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
				status=-4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status=-1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=companyName
	public int setCompany(String inData)  {
		
		inData.split(columnSeperator);
		String companyName;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
			
		}
		companyName = inData;

		query1 = "select * from fourth_dimension.company where name='"
				+ companyName + "'";
		query2 = "insert into fourth_dimension.company values(?,?)";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, companyName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	return	status ;
	}

	// input=companyId
	public int deleteCompany(String inData)  {
		int companyId;
		status = 0;
	
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		try {
			companyId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.company where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, companyId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			
			}
		}
		if ((flag1 == 0)) {
			status = -5;
		}


		return status;
	}

	// input=companyId+rowSeperator+companyId+columnSeperator+companyName
	public int updateCompany(String inData)  {
		int companyId;
		status = 0;
	
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				companyId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;

			}
			query1 = "describe fourth_dimension.company";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 0)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.company set " + columnName[i]
						+ " =?  where id=? ";

				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, companyId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
		}

		
		return 	status ;
	}

	// Brand Administration

	// input=companyId
	// output=brandId+columnSeperator+brandName+columnseperator+rowSeperator
	public String getBrand(String inData)  {
		int companyId;
		int brandId;
		String brandName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			companyId = Integer.parseInt(inData);
			query1 = "select id,name from fourth_dimension.brand where company_id="
					+ companyId + " ";
			rowCount = 0;

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
			status =-4;
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
				status =-4;
			}

		}
		if (rowCount == 0) {
			status =-1;
			data="Data Unavailable";
		}
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=companyId+columnSeperator+brandName
	public int setBrand(String inData)  {
		int companyId;
		String brandName;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status =-2;
			return status;
				
		}
		columnData = inData.split(columnSeperator);

		try {
			if (columnData.length == 2) {
				companyId = Integer.parseInt(columnData[0]);
				brandName = columnData[1];

			} else {
				status =-2;
				return status;
			}
			query1 = "select * from fourth_dimension.brand where name='"
					+ brandName + "'";
			query2 = "insert into fourth_dimension.brand values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status =-3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, companyId);
			ps.setInt(2, 0);
			ps.setString(3, brandName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status =-4;
	
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
				status =-4;
			}

		}
		if ((flag1 == 0)) {
			status =-5;
		}

		
		return	status;
	}

	// input=brandId
	public int deleteBrand(String inData)  {
		int brandId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status =-2;
			return status;
		}

		try {
			brandId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.brand where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, brandId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status =-4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status =-4;
			}
		}
		if ((flag1 == 0)) {
			status =-5;
		}

		
		return	status;

	}

	// input=brandId+rowSeperator+brandId+columnSeperator+brandName
	public int updateBrand(String inData)  {
		int brandId;
		status = 0;
	
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status =-2;
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				brandId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status =-2;
				return status;

			}
			query1 = "describe fourth_dimension.brand";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status =-2;
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.brand set " + columnName[i]
						+ " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, brandId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status =-4;
			

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
				status =-4;
			}

		}
		if ((flag1 < flagCheck)) {
			status =-5;
		}

		return	status;
	}

	// Depot Administration

	// input=areaId+columnSeperator+companyId
	// output=depotId+columnSeperator+depotName+columnSeperator+depotType+columnSeperator+depotContactPerson+columnSeperator+depotContactNumber+columnSeperator+address+columnSeperator+rowSeperator

	public String getDepot(String inData)  {
		int areaId;
		int companyId;
		int depotId;
		String depotName;
		String depotType;
		String depotContactPerson;
		String depotContactNumber;
		String depotAddress;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {
				areaId = Integer.parseInt(columnData[0]);
				companyId = Integer.parseInt(columnData[1]);

			}

			else {
				status = -2;
				data = "input data is unsufficient for operation";
				outData = status + statusSeperator + data;
				return outData;
			}

			query1 = "select id,name,type,contact_person,contact_number,address from fourth_dimension.depot where area_id="
					+ areaId + " and company_id=" + companyId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				depotId = rs.getInt(1);
				depotName = rs.getString(2);
				depotType = rs.getString(3);
				depotContactPerson = rs.getString(4);
				depotContactNumber = rs.getString(5);
				depotAddress = rs.getString(6);

				rowCount++;
				data = data + depotId + columnSeperator + depotName
						+ columnSeperator + depotType + columnSeperator
						+ depotContactPerson + columnSeperator
						+ depotContactNumber + columnSeperator + depotAddress
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception";
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
				data = "Exception";
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=areaId+columnSeperator+companyId+columnSeperator+depotName+columnSeperator+depotType+columnSeperator+depotContactPerson+columnSeperator+depotContactNumber+columnSeperator+address+columnSeperator+rowSeperator

	public int setDepot(String inData)  {
		int areaId;
		int companyId;
		String depotName;
		String depotType;
		String depotContactPerson;
		String depotContactNumber;
		String depotAddress;
		status = 0;
	
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 7) {
				areaId = Integer.parseInt(columnData[0]);
				companyId = Integer.parseInt(columnData[1]);
				depotName = columnData[2];
				depotType = columnData[3];
				depotContactPerson = columnData[4];
				depotContactNumber = columnData[5];
				depotAddress = columnData[6];

			} else {
				status=-2;
				return status;
			}
			query1 = "select * from fourth_dimension.depot where name='"
					+ depotName + "'";
			query2 = "insert into fourth_dimension.depot values(?,?,?,?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status=-3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, areaId);
			ps.setInt(2, companyId);
			ps.setInt(3, 0);
			ps.setString(4, depotName);
			ps.setString(5, depotType);
			ps.setString(6, depotContactPerson);
			ps.setString(7, depotContactNumber);
			ps.setString(8, depotAddress);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status=-4;
			
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
				status=-4;

			}

		}
		if ((flag1 == 0)) {
			status=-5;

		}

	
		return status;
	}

	// input=depotId
	public int deleteDepot(String inData)  {
		int depotId;
		status = 0;
	
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;

		}

		try {
			depotId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.depot where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, depotId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status=-4;
	
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status=-4;
			}
		}
		if ((flag1 == 0)) {
			status=-5;
		}

	
		return status;
	}

	// input=depotId+rowSeperator+areaId+columnSeperator+companyId+columnSeperator+depotId+columnSeperator+depotName+columnSeperator+depotType+columnSeperator+depotContactPerson+columnSeperator+depotContactNumber+columnSeperator+address+columnSeperator+rowSeperator
	public int updateDepot(String inData)  {
		int depotId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;
		}

		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				depotId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status=-2;
				return status;

			}
			query1 = "describe fourth_dimension.depot";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < columnName.length) {
				status=-2;
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 2)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.depot set " + columnName[i]
						+ " =?  where id=? ";
				ps = con.prepareStatement(query2);

				if ((i == 0) || (i == 1))
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, depotId);
				flag1 = ps.executeUpdate() + flag1;

			}

		} catch (Exception e) {
			status=-4;
			

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
				status=-4;
			}

		}
		if ((flag1 < flagCheck)) {
			status=-5;
		}

		
		return status;
	}

	// Store Administration

	// input=areaId
	// output=storeId+columnSeperator+storeName+columnSeperator+storeType+columnSeperator+storeContactPerson+columnSeperator+storeContactNumber+columnSeperator+address+columnSeperator+rowSeperator

	public String getStore(String inData)  {
		int areaId;
		int storeId;
		String storeName;
		String storeType;
		String storeContactPerson;
		String storeContactNumber;
		String storeAddress;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			areaId = Integer.parseInt(inData);
			query1 = "select id,name,type,contact_person,contact_number,address from fourth_dimension.store where area_id="
					+ areaId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				storeId = rs.getInt(1);
				storeName = rs.getString(2);
				storeType = rs.getString(3);
				storeContactPerson = rs.getString(4);
				storeContactNumber = rs.getString(5);
				storeAddress = rs.getString(6);

				rowCount++;
				data = data + storeId + columnSeperator + storeName
						+ columnSeperator + storeType + columnSeperator
						+ storeContactPerson + columnSeperator
						+ storeContactNumber + columnSeperator + storeAddress
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
			data = "Data Unavailable";
		}
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=areaId+columnSeperator+storeName+columnSeperator+storeType+columnSeperator+storeContactPerson+columnSeperator+storeContactNumber+columnSeperator+address+columnSeperator
	public int setStore(String inData)  {
		int areaId;
		String storeName;
		String storeType;
		String storeContactPerson;
		String storeContactNumber;
		String storeAddress;
		status = 0;
		
		flag1 = 0;

		System.out.println("this is add store " + inData);
		if ((inData == null) || inData.equals("")) {
			status = -2;
		    return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 6) {
				areaId = Integer.parseInt(columnData[0]);
				storeName = columnData[1];
				storeType = columnData[2];
				storeContactPerson = columnData[3];
				storeContactNumber = columnData[4];
				storeAddress = columnData[5];

			} else {
				status = -2;
			    return status;
			}
			query1 = "select * from fourth_dimension.store where name='"
					+ storeName + "'";
			query2 = "insert into fourth_dimension.store values(?,?,?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
			    return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, areaId);
			ps.setInt(2, 0);
			ps.setString(3, storeName);
			ps.setString(4, storeType);
			ps.setString(5, storeContactPerson);
			ps.setString(6, storeContactNumber);
			ps.setString(7, storeAddress);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
		  
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

		
		return	status;
	}

	// input=storeId
	public int deleteStore(String inData)  {
		int storeId;
		status = 0;
		data = "success";
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		try {
			storeId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.store where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, storeId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
		}

		
		return status;
	}

	// input=storeId+rowSeperator+areaId+columnSeperator+storeId+columnSeperator+storeName+columnSeperator+storeType+columnSeperator+storeContactPerson+columnSeperator+storeContactNumber+columnSeperator+address
	public int updateStore(String inData)  {
		int storeId;
		status = 0;
		data = "success";
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status=-2;
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				storeId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status=-2;
				return status;

			}
			query1 = "describe fourth_dimension.store";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < columnName.length) {
				status=-2;
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.store set " + columnName[i]
						+ " =?  where id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, storeId);
				flag1 = ps.executeUpdate() + flag1;

			}

		} catch (Exception e) {
			status=-4;
		

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
				status=-4;
			}

		}
		if ((flag1 < flagCheck)) {
			status=-5;
		}

		
		return status;
	}

	 Manage Material 

	// Material Group Administration

	// output=materialGroupId+columnSeperator+materialGroupName+columnSeperator+materialGroupCode+columnSeperator+rowSeperator

	public String getMaterialGroup(String inData)  {
		int materialGroupId;
		String materialGroupName;
		int materialGroupCode;
		status = 0;
		data = "";

		query1 = "select * from fourth_dimension.material_group ";

		rowCount = 0;
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialGroupId = rs.getInt(1);
				materialGroupName = rs.getString(2);
				materialGroupCode = rs.getInt(3);
				rowCount++;
				data = data + materialGroupId + columnSeperator
						+ materialGroupName + columnSeperator
						+ materialGroupCode + columnSeperator + rowSeperator;
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

	// input=materialName
	public int setMaterialGroup(String inData)  {
		
		status = 0;
		flag1 = 0;
		//parse data
		parseData=inData.split(columnSeperator);
		
		for(int i=0;i<parseData.length;i++){
		if ((parseData[i] == null) || parseData[i].equals("")) {
			status = -2;
		    return status;
		}
	}
		
		
		query1 = "select * from fourth_dimension.masterialgroupmaster where materialgroupName='"
				+ parseData[1] + "' OR materialgroupID='"+parseData[0]+"'";
		query2 = "select masterialgroupID from fourth_dimension.masterialgroupmaster order by masterialgroupID desc";
		query3 = "insert into fourth_dimension.masterialgroupmaster(materialgroupID,materialgroupName) values(?,?)";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			rs = st.executeQuery(query2);
			if (rs.next()) {
				materialGroupCode = rs.getInt(1) + 1;
			} else {
				materialGroupCode = 01;
			}

			ps = con.prepareStatement(query3);
			ps.setInt(1,Integer.parseInt(parseData[0]));
			ps.setString(2, parseData[1]);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			status = -4;
		
		} finally {
			try {
				if (con != null) {
					cp.free(con);  //return outData = status + statusSeperator + data;
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

	// input=materialGroupId
	public int deleteMaterialGroup(String inData)  {
		int materialGroupId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}

		try {
			materialGroupId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.material_group where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, materialGroupId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
		
		}

		
		return status;

	}

	// input=materialGroupId+rowSeperator+materialGroupId+columnSeperator+materialGroupName+columnSeperator+materialGroupCode
	public int updateMaterialGroup(String inData)  {
		int materialGroupId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				materialGroupId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.material_group";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 0)
						|| (i == 2)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.material_group set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, materialGroupId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	}

	// input=materialGroupId
	// output=materialSubGroupId+columnSeperator+materialSubGroupName+columnSeperator+materialSubGroupCode+rowSeperator
	public String getMaterialSubGroup(String inData)  {
		int materialGroupId;
		int materialSubGroupId;
		String materialSubGroupName;
		int materialSubGroupCode;
		status = 0;
		data = "";

		if ((inData == null) || inData.equals("")) {
			status = -2;
			data="Input data is insufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}

		try {
			materialGroupId = Integer.parseInt(inData);

			query1 = "select id,name,code from fourth_dimension.material_sub_group where group_id="
					+ materialGroupId + " ";

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialSubGroupId = rs.getInt(1);
				materialSubGroupName = rs.getString(2);
				materialSubGroupCode = rs.getInt(3);
				rowCount++;
				data = data + materialSubGroupId + columnSeperator
						+ materialSubGroupName + columnSeperator
						+ materialSubGroupCode + columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
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
				data="Exception:-"+e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=materialGroupId+columnSeperator+materialSubGroupName
	public int setMaterialSubGroup(String inData)  {
		parseData=inData.split(columnSeperator);
		status = 0;
		flag1 = 0;

		
		
		for(int i=0;i<parseData.length;i++){
		if ((parseData[i] == null) || parseData[i].equals("")) {
			status = -2;
		    return status;
		}
	}

		try {
			
			query1 = "select * from fourth_dimension.materialsubgroupmaster where materialsubgroupmasterName='"
					+ parseData[2] + "' ";
			query2 = "select code from fourth_dimension.materialsubgroupmaster where group_id="
					+ parseData[0] + " order by code desc";
			query3 = "insert into materialsubgroupmaster(materialgroupmasterID,materialsubgroupmasterID,materialsubgroupmasterName) values (?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
			    return status;
			}
			
			rs = st.executeQuery(query2);
			if (rs.next()) {
				materialSubGroupCode = rs.getInt(1) + 1;
			} else {
				materialSubGroupCode = 01;
			}

			ps = con.prepareStatement(query3);
			ps.setInt(1,Integer.parseInt(parseData[0]));
			ps.setInt(2,Integer.parseInt(parseData[1]));
			ps.setString(3,parseData[2]);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// input=materialSubGroupId
	public int deleteMaterialSubGroup(String inData)  {
		int materialSubGroupId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}

		try {
			materialSubGroupId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.material_sub_group where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, materialSubGroupId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
					status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;

	}

	// input=materialSubGroupId+rowSeperator+materialGroupId+columnSeperator+materialSubGroupId+columnSeperator+materialSubGroupName+columnSeperator+materialSubGroupCode
	public int updateMaterialSubGroup(String inData)  {
		int materialSubGroupId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				materialSubGroupId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.material_sub_group";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)
						|| (i == 3)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.material_sub_group set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, materialSubGroupId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
	
		}

		
		return status;
	}

	// Material Administration

	// input=materialSubGroupId
	// output=materialId+columnSeperator+materialName+columnSeperator+materialCode+columnSeperator+rowSeperator
	public String getMaterial(String inData)  {
		int materialSubGroupId;
		int materialId;
		String materialName;
		int materialCode;
		status = 0;
		data = "";

		if ((inData == null) || inData.equals("")) {
			status = -2;
			data="Input data is insufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}

		try {
			materialSubGroupId = Integer.parseInt(inData);

			query1 = "select id,name,code from fourth_dimension.material where sub_group_id="
					+ materialSubGroupId + " ";

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialId = rs.getInt(1);
				materialName = rs.getString(2);
				materialCode = rs.getInt(3);
				rowCount++;
				data = data + materialId + columnSeperator + materialName
						+ columnSeperator + materialCode + columnSeperator
						+ rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
			
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
				data="Exception:-"+e;
		
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=materialSubGroupId+columnSeperator+materialName

	public int setMaterial(String inData)  {
		int materialSubGroupId;
		String materialName;
		int materialCode;
		status = 0;		
		flag1 = 0;
		parseData=inData.split(columnSeperator);
		for(int i=0;i<parseData.length;i++){
			System.out.println("ParseData"+parseData[i]);
			if ((parseData[i] == null) || parseData[i].equals("")) {
				status = -2;
			    return status;
			}
		}
		//columnData = inData.split(columnSeperator);

		try {
			if (columnData.length == 2) {
				materialSubGroupId = Integer.parseInt(columnData[0]);
				materialName = columnData[1];

			} else {
				status = -2;
				
				return status;
			}
			query1 = "select * from fourth_dimension.material_master where MaterialName='"
					+ parseData[3] + "'";
			query2 = "select code from fourth_dimension.material_master where sub_group_id="
					+ parseData[0] + " order by code desc";
			query3 = "insert into fourth_dimension.material_master(MaterialGroupID,MaterialSubGroupID,MaterialID,MaterialName) values(?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			rs = st.executeQuery(query2);
			if (rs.next()) {
				materialCode = rs.getInt(1) + 01;
			} else {
				materialCode = 01;
			}

			ps = con.prepareStatement(query3);
			ps.setInt(1,Integer.parseInt( parseData[0]));
			ps.setInt(2, Integer.parseInt(parseData[1]));
			ps.setInt(3, Integer.parseInt(parseData[2]));
			ps.setString(4, parseData[3]);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// input=materialId
	public int deleteMaterial(String inData)  {
		int materialId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}

		try {
			materialId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.material where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, materialId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
					}

		
		return status;

	}

	// input=materialId+rowSeperator+materialSubGroupId+columnSeperator+materialId+columnSeperator+materailName+columnSeperator+materialCode
	public int updateMaterial(String inData)  {
		int materialId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				materialId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.material";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)
						|| (i == 3)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.material set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, materialId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	}

	// Material Unit Administration

	// output=materialUnitId+columnSeperator+materialUnitName+columnSeperator+rowSeperator

	public String getMaterialUnit(String inData)  {
		int materialUnitId;
		String materialUnitName;
		status = 0;
		data = "";

		query1 = "select * from fourth_dimension.material_unit ";

		rowCount = 0;
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialUnitId = rs.getInt(1);
				materialUnitName = rs.getString(2);

				rowCount++;
				data = data + materialUnitId + columnSeperator
						+ materialUnitName + columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
	
			
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
				data="Exception:-"+e;
		
	        }

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=materialUnitName
	public int setMaterialUnit(String inData)  {
		String materialUnitName;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		materialUnitName = inData;

		query1 = "select * from fourth_dimension.material_unit where name='"
				+ materialUnitName + "'";
		query2 = "insert into fourth_dimension.material_unit values(?,?)";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
			
				return status;
			}

			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, materialUnitName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// input=materialUnitId
	public int deleteMaterialUnit(String inData)  {
		int materialUnitId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}

		try {
			materialUnitId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.material_unit where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, materialUnitId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;

	}

	// input=materialUnitId+rowSeperator+materialUnitId+columnSeperator+materialUnitName
	public int updateMaterialUnit(String inData)  {
		int materialUnitId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				materialUnitId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.material_unit";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 0)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.material_unit set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);

				ps.setString(1, columnData[i]);
				ps.setInt(2, materialUnitId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
				status = -4;			}

		}
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	}

	// Material Type Administration

	// output=materailTypeId+columnSeperator+materialTypeName+columnSeperator+rowSeperator
	public String getMaterialType(String inData)  {
		int materialTypeId;
		String materialTypeName;
		int materialTypeCode;
		status = 0;
		data = "";

		query1 = "select * from fourth_dimension.material_type ";

		rowCount = 0;
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialTypeId = rs.getInt(1);
				materialTypeName = rs.getString(2);
				materialTypeCode = rs.getInt(3);

				rowCount++;
				data = data + materialTypeId + columnSeperator
						+ materialTypeName + columnSeperator + materialTypeCode
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
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
				status=-4;
				data="Exception:-"+e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=materialTypeName
	public int setMaterialType(String inData)  {
		String materialTypeName;
		int materialTypeCode;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		materialTypeName = inData;

		query1 = "select * from fourth_dimension.material_type where name='"
				+ materialTypeName + "'";
		query2 = "select code from fourth_dimension.material_type  order by code desc";
		query3 = "insert into fourth_dimension.material_type values(?,?,?)";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				
				return status;
			}
			rs = st.executeQuery(query2);
			if (rs.next()) {
				materialTypeCode = rs.getInt(1) + 01;
			} else {
				materialTypeCode = 01;
			}

			ps = con.prepareStatement(query3);
			ps.setInt(1, 0);
			ps.setString(2, materialTypeName);
			ps.setInt(3, materialTypeCode);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// input=materialTypeId
	public int deleteMaterialType(String inData)  {
		int materialTypeId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}

		try {
			materialTypeId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.material_type where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, materialTypeId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;

	}

	// input=
	// materialTypeId+rowSeperator+materialTypeId+columnSeperator+materailTypeName
	public int updateMaterialType(String inData)  {
		int materialTypeId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				materialTypeId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.material_type";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 0)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.material_type set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);

				ps.setString(1, columnData[i]);
				ps.setInt(2, materialTypeId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	}

	// Element Administration

	// output=
	// compositElementId/elementId+columnSeperator+compositElementName/elementName+columnSeperator+elementName+fieldSeperator+elementName+fieldSeperator+columnSeperator+rowSeperator
	public String getElement(String inData)  {
		PreparedStatement ps1 = null;
		ResultSet rs1 = null, rs2 = null;
		int elementId;
		String elementName;
		int compositElementId;
		String compositElementName = "";
		status = 0;
		String data1 = " ";

		query1 = "select * from fourth_dimension.element";
		query2 = "select element_id from fourth_dimension.composit_element where composit_element_id=?";
		query3 = "select name from fourth_dimension.element where id=?";

		rowCount = 0;
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				elementId = rs.getInt(1);
				elementName = rs.getString(2);

				ps = con.prepareStatement(query2);
				ps.setInt(1, elementId);

				rs1 = ps.executeQuery();
				while (rs1.next()) {
					compositElementId = rs1.getInt(1);

					ps1 = con.prepareStatement(query3);
					ps1.setInt(1, compositElementId);

					rs2 = ps1.executeQuery();
					if (rs2.next()) {
						compositElementName = rs2.getString(1);
					}
					data1 = data1 + compositElementName + fieldSeperator;

				}

				rowCount++;
				data = data + elementId + columnSeperator + elementName
						+ columnSeperator + data1 + columnSeperator
						+ rowSeperator;
				data1 = " ";
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
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
				if (ps1 != null)
					ps1.close();
				if (rs != null)
					rs.close();
				if (rs1 != null)
					rs1.close();
				if (rs2 != null)
					rs2.close();
			} catch (Exception e) {
				status = -4;
				data="Exception:-"+e;
			}
		

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=
	// compositElementName/elementName+rowSeperator+elementId+columnSeperator+elementId+columnSeperator
	public int setElement(String inData)  {
		int elementId = 0;
		String elementName;
		int compositElementId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		rowData = inData.split(rowSeperator);

		if (rowData.length == 2) {
			elementName = rowData[0];
			columnData = rowData[1].split(columnSeperator);
		}

		else {
			status = -2;
			
			return status;

		}

		query1 = "select * from fourth_dimension.element where name='"
				+ elementName + "'";
		query2 = "insert into fourth_dimension.element values(?,?)";
		query3 = "select id from fourth_dimension.element where name='"
				+ elementName + "'";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				
				return status;
			}

			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, elementName);
			flag1 = ps.executeUpdate();

			rs = st.executeQuery(query3);
			if (rs.next()) {
				elementId = rs.getInt(1);
			}

			for (int i = 0; i < columnData.length; i++)
				if ((columnData[i] != null) && (!columnData[i].equals(""))
						&& (!columnData[i].equals("null"))) {
					compositElementId = Integer.parseInt(columnData[i]);
					st
							.addBatch("insert into fourth_dimension.composit_element values("
									+ elementId + "," + compositElementId + ")");

				}

			st.executeBatch();

		} catch (Exception e) {
			status = -4;
			
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

	// input=elementId
	public int deleteElement(String inData)  {
		int elementId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}

		try {
			elementId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.composit_element where composit_element_id=?";
			query1 = "delete from fourth_dimension.element where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, elementId);
			flag1 = ps.executeUpdate();

			ps = con.prepareStatement(query2);
			ps.setInt(1, elementId);
			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;

	}

	// input=elementId+rowSeperator+elementId+columnSeperator+elementName
	public int updateElement(String inData)  {
		int elementId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);

			if (rowData.length == 2) {
				elementId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.element";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 0)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.element set " + columnName[i]
						+ " =?  where id=? ";

				ps = con.prepareStatement(query2);

				ps.setString(1, columnData[i]);
				ps.setInt(2, elementId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	}

	// input=compositElementId+rowSeperator+elementId+columnSeperator+elementId
	public int updateCompositElement(String inData)  {
		int elementId;
		int compositElementId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				compositElementId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.composit_element";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if ((columnData != null) && (columnData.length >= 1)) {
				query2 = "delete from fourth_dimension.composit_element where composit_element_id=?";
				ps = con.prepareStatement(query2);
				ps.setInt(1, compositElementId);
				ps.executeUpdate();

			}

			else {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnData.length; i++) {

				elementId = Integer.parseInt(columnData[i]);
				query3 = "insert into fourth_dimension.composit_element values("
						+ compositElementId + "," + elementId + ")";
				st.addBatch(query3);

			}
			flag = st.executeBatch();

		} catch (Exception e) {
			status = -4;
			

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
		for (int i = 0; i < columnData.length; i++)
			if ((flag[i] == 0)) {
				status = -5;
				
			}

		
		return status;
	}

	// Component Administration

	// input=elementId
	// output=componentId+columnSeperator+componentName+columnSeperator+rowSeperator
	public String getComponent(String inData)  {
		int elementId;
		int componentId;
		String componentName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			elementId = Integer.parseInt(inData);
			query1 = "select id,name from fourth_dimension.component where element_id="
					+ elementId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				componentId = rs.getInt(1);
				componentName = rs.getString(2);

				rowCount++;
				data = data + componentId + columnSeperator + componentName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
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
				data="Exception:-"+e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=elementId+columnSeperator+componentName
	public int setComponent(String inData)  {
		int elementId;
		String componentName;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {
				elementId = Integer.parseInt(columnData[0]);
				componentName = columnData[1];

			} else {
				status = -2;
				
				return status;
			}
			query1 = "select * from fourth_dimension.component where name='"
					+ componentName + "'";
			query2 = "insert into fourth_dimension.component values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, elementId);
			ps.setInt(2, 0);
			ps.setString(3, componentName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// input=componentId
	public int deleteComponent(String inData)  {
		int componentId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			componentId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.component where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, componentId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;
	}

	// input=componentId+rowSeperator+elementId+columnSeperator+componentId+columnSeperator+componentName
	public int updateComponent(String inData)  {
		int componentId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				componentId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.component";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.component set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, componentId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
	
		}

		
		return status;
	}

	// input=materialSubGroupId
	// output=serialNumber
	public String getSerialNumber(String inData)  {
		int materialSubGroupId;
		int materialSerialNumber;
		status = 0;
		data = "";
		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			materialSubGroupId = Integer.parseInt(inData);
			query1 = "select serial_number from fourth_dimension.material_serial_number where sub_group_id="
					+ materialSubGroupId + "";

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {

				materialSerialNumber = rs.getInt(1);

				rowCount++;
				data = String.valueOf(materialSerialNumber);
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
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
				data="Exception:-"+e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	 Manage FD 

	// Hub Administration

	// input=areaId
	// output=hubId+columnSeperator+hubName+columnSeperator+hubPhoneNumber+columnSeperator+hubAddress+columnSeperator+rowSeperator
	public String getHub(String inData)  {
		int areaId;
		int hubId;
		String hubName;
		String hubPhoneNumber;
		String hubAddress;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			areaId = Integer.parseInt(inData);
			query1 = "select id,name,phone_number,address from fourth_dimension.hub where area_id="
					+ areaId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				hubId = rs.getInt(1);
				hubName = rs.getString(2);
				hubPhoneNumber = rs.getString(3);
				hubAddress = rs.getString(4);

				rowCount++;
				data = data + hubId + columnSeperator + hubName
						+ columnSeperator + hubPhoneNumber + columnSeperator
						+ hubAddress + columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
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
				data="Exception:-"+e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=areaId+columnSeperator+hubName+columnSeperator+hubPhoneNumber+columnSeperator+hubAddress
	public int setHub(String inData)  {
		int areaId;
		String hubName;
		String hubPhoneNumber;
		String hubAddress;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 4) {
				areaId = Integer.parseInt(columnData[0]);
				hubName = columnData[1];
				hubPhoneNumber = columnData[2];
				hubAddress = columnData[3];

			} else {
				status = -2;
				
				return status;
			}
			query1 = "select * from fourth_dimension.hub where name='"
					+ hubName + "'";
			query2 = "insert into fourth_dimension.hub values(?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
			
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, areaId);
			ps.setInt(2, 0);
			ps.setString(3, hubName);
			ps.setString(4, hubPhoneNumber);
			ps.setString(5, hubAddress);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// input=hubId
	public int deleteHub(String inData)  {
		int hubId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			hubId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.hub where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, hubId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;
	}

	// input=hubId+rowSeperator+areaId+columnSeperator+hubId+columnSeperator+hubName+columnSeperator+hubPhoneNumber+columnSeperator+hubAddress
	public int updateHub(String inData)  {
		int hubId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				hubId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.hub";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.hub set " + columnName[i]
						+ " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, hubId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	}

	// Division Administration

	// input=hubId
	// output=divisionId+columnSeperator+divisionName+columnSeperator+rowSeperator
	public String getDivision(String inData)  {
		int hubId;
		int divisionId;
		String divisionName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			hubId = Integer.parseInt(inData);
			query1 = "select id,name from fourth_dimension.division where hub_id="
					+ hubId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				divisionId = rs.getInt(1);
				divisionName = rs.getString(2);

				rowCount++;
				data = data + divisionId + columnSeperator + divisionName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
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
				data="Exception:-"+e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=hubId+columnSeperator+divisionName
	public int setDivision(String inData)  {
		int hubId;
		String divisionName;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {
				hubId = Integer.parseInt(columnData[0]);
				divisionName = columnData[1];

			} else {
				status = -2;
				
				return status;
			}
			query1 = "select * from fourth_dimension.division where name='"
					+ divisionName + "'";
			query2 = "insert into fourth_dimension.division values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, hubId);
			ps.setInt(2, 0);
			ps.setString(3, divisionName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// input=divisionId
	public int deleteDivision(String inData)  {
		int divisionId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			divisionId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.division where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, divisionId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;
	}

	// input=divisionId+rowSeperator+hubId+columnSeperator+divisionId+columnSeperator+divisionName
	public int updateDivision(String inData)  {
		int divisionId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				divisionId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.division";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.division set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, divisionId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	}

	// Department Administration

	// input=divisionId
	// output=departmentId+columnSeperator+departmentName+columnSeperator+rowSeperator
	public String getDepartment(String inData)  {
		int divisionId;
		int departmentId;
		String departmentName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			divisionId = Integer.parseInt(inData);
			query1 = "select id,name from fourth_dimension.department where division_id="
					+ divisionId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				departmentId = rs.getInt(1);
				departmentName = rs.getString(2);

				rowCount++;
				data = data + departmentId + columnSeperator + departmentName
						+ columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
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
				data="Exception:-"+e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=divisionId+columnSeperator+departmentName
	public int setDepartment(String inData)  {
		int divisionId;
		String departmentName;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {
				divisionId = Integer.parseInt(columnData[0]);
				departmentName = columnData[1];

			} else {
				status = -2;
				
				return status;
			}
			query1 = "select * from fourth_dimension.department where name='"
					+ departmentName + "'";
			query2 = "insert into fourth_dimension.department values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, divisionId);
			ps.setInt(2, 0);
			ps.setString(3, departmentName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// input=departmentId
	public int deleteDepartment(String inData)  {
		int departmentId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			departmentId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.department where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, departmentId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
			
		}

		
		return status;
	}

	// input=departmentId+rowSeperator+divisionId+columnSeperator+departmentId+columnSeperator+departmentName
	public int updateDepartment(String inData)  {
		int departmentId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				departmentId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.department";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.department set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, departmentId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	}

	// Operational Group Administration

	// input=departmentId
	// output=operationalGroupId+columnSeperator+operationalGroupName+columnSeperator+rowSeperator
	public String getOperationalGroup(String inData)  {
		int departmentId;
		int operationalGroupId;
		String operationalGroupName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			departmentId = Integer.parseInt(inData);
			query1 = "select id,name from fourth_dimension.operational_group where department_id="
					+ departmentId + "";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				operationalGroupId = rs.getInt(1);
				operationalGroupName = rs.getString(2);

				rowCount++;
				data = data + operationalGroupId + columnSeperator
						+ operationalGroupName + columnSeperator + rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:-"+e;
			
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
				data="Exception:-"+e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=departmentId+columnSeperator+operationalGroupName
	public int setOperationalGroup(String inData)  {
		int departmentId;
		String operationalGroupName;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {
				departmentId = Integer.parseInt(columnData[0]);
				operationalGroupName = columnData[1];

			} else {
				status = -2;
				
				return status;
			}
			query1 = "select * from fourth_dimension.operational_group where name='"
					+ operationalGroupName + "'";
			query2 = "insert into fourth_dimension.operational_group values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, departmentId);
			ps.setInt(2, 0);
			ps.setString(3, operationalGroupName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
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

	// input=operationalGroupId
	public int deleteOperationalGroup(String inData)  {
		int operationalGroupId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			operationalGroupId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.operational_group where id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, operationalGroupId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}

				if (ps != null)
					ps.close();
			} catch (Exception e) {
				status = -4;
			}
		}
		if ((flag1 == 0)) {
			status = -5;
		
		}

		
		return status;
	}

	// input=operationalGroupId+rowSeperator+departmentId+columnSeperator+operationalGroupId+columnSeperator+operationalGroupName
	public int updateOperationalGroup(String inData)  {
		int operationalGroupId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				operationalGroupId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.operational_group";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < (columnName.length)) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 1)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.operational_group set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);
				if (i == 0)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, operationalGroupId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
			
		}

		
		return status;
	}

	// Employee Administration

	// output=employeeId+columnSeperator+employeeName+columnSeperator+employeeContactNumber+columnSeperator+employeeEmailId+
	// columnSeperator+employeeAddress+columnSeperator+employeeRoleName+fieldSeperator+employeeRoleName+columnSeperator+
	// employeeHubName+fieldSeperator+employeeHubName+columnSeperator+rowSeperator
	public String getEmployee(String inData)  {
		ResultSet rs1 = null;
		String hubName;
		int employeeId;
		String employeeName;
		String employeeContactNumber;
		String employeeEmailId;
		String employeeAddress;
		String roleName;
		status = 0;
		data = "";
		String data1 = " ";
		String data2 = " ";
		query1 = "select * from employee";
		query2 = "select name from employee_role_mapping erm join role r on(erm.role_id=r.id) where erm.employee_id=?";
		query3 = "select name from employee_hub_mapping ehm join hub h on(ehm.hub_id=h.id) where ehm.employee_id=?";
		
		 * query4 =
		 * "select e.id,e.name,e.contact_number,e.e_mail,e.address,r.id,r.name,h.id,h.name "
		 * + "from fourth_dimension.employee e,fourth_dimension.hub h," +
		 * "fourth_dimension.role r,fourth_dimension.employee_hub_mapping ehm,"
		 * + "fourth_dimension.employee_role_mapping erm where " +
		 * "(erm.employee_id=e.id and erm.role_id=r.id) and (ehm.employee_id=e.id and ehm.hub_id=h.id )"
		 * ;
		 
		rowCount = 0;
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				employeeId = rs.getInt(1);
				employeeName = rs.getString(2);
				employeeContactNumber = rs.getString(3);
				employeeEmailId = rs.getString(4);
				employeeAddress = rs.getString(5);

				ps = con.prepareStatement(query2);
				ps.setInt(1, employeeId);
				rs1 = ps.executeQuery();
				while (rs1.next()) {
					roleName = rs1.getString(1);
					data1 = data1 + roleName + fieldSeperator;
				}

				ps = con.prepareStatement(query3);
				ps.setInt(1, employeeId);
				rs1 = ps.executeQuery();
				while (rs1.next()) {
					hubName = rs1.getString(1);
					data2 = data2 + hubName + fieldSeperator;
				}

				rowCount++;
				data = data + employeeId + columnSeperator + employeeName
						+ columnSeperator + employeeContactNumber
						+ columnSeperator + employeeEmailId + columnSeperator
						+ employeeAddress + columnSeperator + data1
						+ columnSeperator + data2 + columnSeperator
						+ rowSeperator;
				data1 = " ";
				data2 = " ";
			}
		} catch (Exception e) {
			status = -4;
			data="Exception:"+e;
			
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
				if (rs1 != null)
					rs1.close();
			} catch (Exception e) {
				status = -4;
				data="Exception:"+e;
			}
			

		}
		if (rowCount == 0) {
			status = -1;
			data = "DataUnavailable";
		}
		
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=employeeId+rowSeperator+employeeName+rowSeperator+employeeContactNumber+rowSeperator+employeeEmailId+
	// rowSeperator+employeeAddress+rowSeperator+employeeRoleId+columnSeperator+employeeRoleId+rowSeperator+
	// employeeHubId+columnSeperator+employeeHubId+rowSeperator
	public int setEmployee(String inData)  {
		int hubId;
		int employeeId = 0;
		String employeeName;
		String employeeContactNumber;
		String employeeEmailId;
		String employeeAddress;
		int roleId;
		String roleIdTemp;
		String hubIdTemp;
		String roleIdArray[];
		String hubIdArray[];
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		rowData = inData.split(rowSeperator);

		if (rowData.length == 6) {

			employeeName = rowData[0];
			employeeContactNumber = rowData[1];
			employeeEmailId = rowData[2];
			employeeAddress = rowData[3];
			roleIdTemp = rowData[4];
			hubIdTemp = rowData[5];

		} else {
			status = -2;
			
			return status;
		}
		query1 = "select * from fourth_dimension.employee where name='"
				+ employeeName + "'";
		query2 = "insert into fourth_dimension.employee values(?,?,?,?,?)";
		query3 = "select id from fourth_dimension.employee where name='"
				+ employeeName + "'";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
			
				return status;
			}

			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, employeeName);
			ps.setString(3, employeeContactNumber);
			ps.setString(4, employeeEmailId);
			ps.setString(5, employeeAddress);
			flag1 = ps.executeUpdate();

			rs = st.executeQuery(query3);
			if (rs.next()) {
				employeeId = rs.getInt(1);
			}
			roleIdArray = roleIdTemp.split(columnSeperator);
			hubIdArray = hubIdTemp.split(columnSeperator);
			for (int i = 0; i < roleIdArray.length; i++) {
				roleId = Integer.parseInt(roleIdArray[i]);
				st
						.addBatch("insert into fourth_dimension.employee_role_mapping values("
								+ employeeId + "," + roleId + ")");
			}

			for (int i = 0; i < hubIdArray.length; i++) {
				hubId = Integer.parseInt(hubIdArray[i]);
				st
						.addBatch("insert into fourth_dimension.employee_hub_mapping values("
								+ employeeId + "," + hubId + ")");
			}

			flag = st.executeBatch();

		} catch (Exception e) {
			status = -4;
			
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
		if ((flag1 == 0) || (flag.length < 2)) {
			status = -5;
			
		}

		
		return status;
	}

	// input=employeeId
	public int deleteEmployee(String inData)  {
		int employeeId;
		status = 0;
		
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {

			employeeId = Integer.parseInt(inData);

			query1 = "delete from fourth_dimension.employee_role_mapping where employee_id="
					+ employeeId + "";
			query2 = "delete from fourth_dimension.employee_hub_mapping where employee_id="
					+ employeeId + "";
			query3 = "delete from fourth_dimension.employee where id="
					+ employeeId + "";

			con = cp.getConnection();
			st = con.createStatement();
			st.addBatch(query1);
			st.addBatch(query2);
			st.addBatch(query3);
			flag = st.executeBatch();

		} catch (Exception e) {
			status = -4;
			
		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (st != null)
					st.close();
			} catch (Exception e) {
				status = -4;
			}
		}

		if ((flag[0] == 0) || (flag[1] == 0) || (flag[2] == 0)) {
			status = -5;
			
		}

		
		return status;

	}

	// input=employeeId+rowSeperator+employeeId+columnSeperator+name+columnSeperator+contactNumber+columnSeperator+email+columnSeperator+address
	public int updateEmployeeGeneralInfo(String inData)  {
		int employeeId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}

		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				employeeId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.employee";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if (columnData.length < columnName.length) {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {
				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 0)) {
					continue;
				}
				flagCheck++;
				query2 = "update fourth_dimension.employee set "
						+ columnName[i] + " =?  where id=? ";

				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, employeeId);
				flag1 = ps.executeUpdate() + flag1;

			}

		} catch (Exception e) {
			status = -4;
			

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
		if ((flag1 < flagCheck)) {
			status = -5;
		
		}

		
		return status;
	}

	// input=employeeId+rowSeperator+hubId+columnSeperator+hubId+columnSeperator
	public int updateEmployeeHubInfo(String inData)  {
		int hubId;
		int employeeId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];

		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}
		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				employeeId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.employee_hub_mapping";
			query2 = "delete from fourth_dimension.employee_hub_mapping where employee_id=?";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if ((columnData != null) && (columnData.length >= 1)) {

				ps = con.prepareStatement(query2);
				ps.setInt(1, employeeId);
				ps.executeUpdate();

			}

			else {
				status = -2;
				
				return status;

			}
			for (int i = 0; i < columnData.length; i++) {

				hubId = Integer.parseInt(columnData[i]);
				query3 = "insert into fourth_dimension.employee_hub_mapping values("
						+ employeeId + "," + hubId + ")";
				st.addBatch(query3);

			}
			flag = st.executeBatch();

		} catch (Exception e) {
			status = -4;
			

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
		for (int i = 0; i < columnData.length; i++)
			if ((flag[i] == 0)) {
				status = -5;
				
			}

		
		return status;
	}

	// input=employeeId+rowSeperator+roleId+columnSeperator+roleId+columnSeperator
	public int updateEmployeeRoleInfo(String inData)  {
		int employeeId;
		int roleId;
		status = 0;
		
		flag1 = 0;

		String columnNameTemp = "";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			
			return status;
		}

		try {
			rowData = inData.split(rowSeperator);
			if (rowData.length == 2) {
				employeeId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				
				return status;

			}
			query1 = "describe fourth_dimension.employee_role_mapping";

			con = cp.getConnection();

			st = con.createStatement();

			rs = st.executeQuery(query1);

			while (rs.next()) {
				columnNameTemp = columnNameTemp + rs.getString(1)
						+ columnSeperator;

			}

			columnName = columnNameTemp.split(columnSeperator);
			if ((columnData != null) && (columnData.length >= 1)) {
				query2 = "delete from fourth_dimension.employee_role_mapping where employee_id=?";
				ps = con.prepareStatement(query2);
				ps.setInt(1, employeeId);
				ps.executeUpdate();

			}
			for (int i = 0; i < columnData.length; i++) {

				roleId = Integer.parseInt(columnData[i]);
				query3 = "insert into fourth_dimension.employee_role_mapping values("
						+ employeeId + "," + roleId + ")";
				st.addBatch(query3);

			}
			flag = st.executeBatch();

		} catch (Exception e) {
			status = -4;
			

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

		for (int i = 0; i < columnData.length; i++)
			if (flag[i] == 0) {
				status = -5;
				
			}

		
		return status;
	}
*/
}
