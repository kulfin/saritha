package com.fd.Admin;

import com.fd.Admin.bean.*;
import com.fd.utility.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Service {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3307/fourth_dimension";
	static String userName = "root";
//	 static String url = "jdbc:mysql://localhost:3306/fourth_dimension";
	// static String userName = "root";
	// static String url = "jdbc:mysql://localhost:3306/fourth_dimension"; //for
	// 182.50.142.1
	// static String userName = "root"; //for local
	// static String userName = "root"; // for 182.50.142.1
	static String password = "root";
	static int initialConnections = 1;
	static int maxConnections = 5;
	Connection con;
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

	/* Manage Places */

	// Country Administration

	// Output=countryId+columnSeperator+countryName+columnSeperator+rowSeperator

	public String getCountry(String inData) {
		int countryId;
		String countryName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "select * from fourth_dimension.country_master where country_name !='NA' order by country_name ";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				countryId = rs.getInt(1);
				countryName = rs.getString(2);

				rowCount++;
				data = data + countryId + columnSeperator + countryName
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

	public int setCountry(String inData) {
		String countryName;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		countryName = inData;

		query1 = "select * from fourth_dimension.country_master where country_name='"
				+ countryName + "'";
		query2 = "insert into fourth_dimension.country_master values(?,?)";

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

	// Input=countryId;

	public int deleteCountry(String inData) {
		System.out.println("country id::"+inData);
		Integer countryId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.country_master where country_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				countryId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, countryId);
				flag1 = ps.executeUpdate();

			}

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

	// Input=countryId+rowSeperator+countryId+columnSeperator+countryName;

	public int updateCountry(String inData) {
		int countryId;
		
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
				countryId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;
			}
			query1 = "describe fourth_dimension.country_master";
			String strCheckBeforeUpdate ="SELECT * FROM fourth_dimension.country_master where country_id !=" + "'" + countryId + "'" + ";";

			con = cp.getConnection();

			st = con.createStatement();
			rs = st.executeQuery(strCheckBeforeUpdate);
			while(rs.next()){
				System.out.println("db data" + rs.getString(2) + "and user data" + columnData[1]);
				if(rs.getString(2).equals(columnData[1])){
					status = -3;
					return status;
				}
			}
			
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
				query2 = "update fourth_dimension.country_master set "
						+ columnName[i] + " =?  where country_id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, countryId);
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

	// Region Administration

	// Input=countryId
	// Output=regionId+columnSeperator+regionName+columnSeperator+rowSeperator
	public String getRegion(String inData) {
		System.out.println("indata    " + inData);
		int countryId;
		int regionId;
		String countryName;
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
			System.out.println("it is getstate");
			query1 = "select region_id,region_name,country_name from fourth_dimension.region_master a,country_master c where a.country_id=c.country_id and a.country_id="
					+ countryId+" order by region_name";
			System.out
					.println("select region_id,region_name,country_name from fourth_dimension.region_master a,country_master c where a.country_id=c.country_id and a.country_id="
							+ countryId);
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				regionId = rs.getInt(1);
				regionName = rs.getString(2);

				countryName = rs.getString(3);

				rowCount++;
				data = data + countryName + columnSeperator + regionId
						+ columnSeperator + regionName + columnSeperator
						+ rowSeperator;
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

	// Input=countryId+columnSeperator+regionName
	public int setRegion(String inData) {
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
			query1 = "select * from fourth_dimension.region_master where region_name='"
					+ regionName + "'";
			query2 = "insert into fourth_dimension.region_master values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setInt(2, countryId);
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
	public int deleteRegion(String inData) {
		Integer regionId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.region_master where region_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				regionId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, regionId);
				flag1 = ps.executeUpdate();

			}

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

	// input=regionId+rowSeperator+countryId+columnSeperator+regionId+columnSeperator+regionName
	public int updateRegion(String inData) {
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
			query1 = "describe fourth_dimension.region_master";

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
				query2 = "update fourth_dimension.region_master set "
						+ columnName[i] + " =?  where region_id=? ";

				ps = con.prepareStatement(query2);
				if (i == 1)
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

	public String getState(String inData) {
		int stateId;
		int regionId;
		String countryName;
		String regionName;
		String stateName;

		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {

			regionId = Integer.parseInt(inData);
			System.out.println("it is getstate");
			query1 = "select state_id,state_name,country_name,region_name from fourth_dimension.state_master a,country_master c,region_master r where a.country_id=c.country_id and a.region_id=r.region_id  and a.region_id="
					+ regionId+" order by state_name";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				stateId = rs.getInt(1);
				stateName = rs.getString(2);

				countryName = rs.getString(3);
				regionName = rs.getString(4);

				rowCount++;
				data = data + countryName + columnSeperator + regionName
						+ columnSeperator + stateId + columnSeperator
						+ stateName + columnSeperator + rowSeperator;
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

	// input=regionId+columnSeperator+stateName
	public int setState(String inData) {
		int countryId;
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

			if (columnData.length == 3) {
				countryId = Integer.parseInt(columnData[0]);
				regionId = Integer.parseInt(columnData[1]);
				stateName = columnData[2];

			} else {
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.state_master where state_name='"
					+ stateName + "'";
			query2 = "insert into fourth_dimension.state_master values(?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				 return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setInt(2, countryId);
			ps.setInt(3, regionId);
			ps.setString(4, stateName);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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
	public int deleteState(String inData) {
		Integer stateId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.state_master where state_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				stateId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, stateId);
				flag1 = ps.executeUpdate();

			}

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

	// input=stateId+rowSeperator+regionId+columnSeperator+stateId+columnSeperator+stateName
	public int updateState(String inData) {
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
			query1 = "describe fourth_dimension.state_master";

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
				query2 = "update fourth_dimension.state_master set "
						+ columnName[i] + " =?  where state_id=? ";

				ps = con.prepareStatement(query2);
				if ((i == 1) || (i == 2))
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

	// State Administration

	// input=stateId
	// output=cityId+columnSeperator+cityName+columnSeperator+rowSeperator
	public String getCity(String inData) {
		int stateId;
		int cityId;
		String countryName;
		String regionName;
		String stateName;
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
			// System.out.println("town id is "+cityId);
			query1 = "select city_id,city_name,country_name,region_name,state_name from fourth_dimension.city_master a,country_master c,region_master r,state_master s where a.country_id=c.country_id and a.region_id=r.region_id and a.state_id=s.state_id and a.state_id="
					+ stateId+" order by city_name";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				cityId = rs.getInt(1);
				cityName = rs.getString(2);

				countryName = rs.getString(3);
				regionName = rs.getString(4);
				stateName = rs.getString(5);

				rowCount++;
				data = data + countryName + columnSeperator + regionName
						+ columnSeperator + stateName + columnSeperator +

						cityId + columnSeperator + cityName + columnSeperator
						+ rowSeperator;
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

	// input=stateId+columnSeperator+cityName
	public int setCity(String inData) {
		int countryId;
		int regionId;
		int stateId;
		String cityName;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 4) {
				countryId = Integer.parseInt(columnData[0]);
				regionId = Integer.parseInt(columnData[1]);
				stateId = Integer.parseInt(columnData[2]);
				cityName = columnData[3];

			} else {
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.city_master where city_name='"
					+ cityName + "'" + " and country_id=" + "'" + countryId + "'" + " and region_id=" + "'" + regionId + "'" + " and state_id=" + "'" + stateId + "'" +";";
			query2 = "insert into fourth_dimension.city_master values(?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				 return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, cityName);
			ps.setInt(3, countryId);
			ps.setInt(4, regionId);
			ps.setInt(5, stateId);

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

	// input=cityId
	public int deleteCity(String inData) {
		int cityId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.city_master where city_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {

				cityId = Integer.parseInt((rowData[i].trim()));
				// System.out.println("it is service and values are "+cityId);
				ps = con.prepareStatement(query1);
				ps.setInt(1, cityId);
				flag1 = ps.executeUpdate();

			}

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

	// input=cityId+rowSeperator+stateId+columnSeperator+cityId+columnSeperator+cityName
	public int updateCity(String inData) {
		int cityId;
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
				cityId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;

			}
			query1 = "describe fourth_dimension.city_master";

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
				query2 = "update fourth_dimension.city_master set "
						+ columnName[i] + " =?  where city_id=? ";

				ps = con.prepareStatement(query2);
				if ((i == 2) || (i == 3) || (i == 4))
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, cityId);
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

	// input=cityId
	// output=townId+columnSeperator+townName+columnSeperator+rowSeperator
	public String getTown(String inData) {
		int townId;
		int cityId;
		String countryName;
		String regionName;
		String stateName;
		String cityName;
		String townName;

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
			// System.out.println("town id is "+cityId);
			query1 = "select town_id,town_name,country_name,region_name,state_name,city_name from fourth_dimension.town_master a,country_master c,region_master r,state_master s,city_master ct where a.country_id=c.country_id and a.region_id=r.region_id and a.state_id=s.state_id and a.city_id=ct.city_id and a.city_id="
					+ cityId+" order by town_name";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				townId = rs.getInt(1);
				townName = rs.getString(2);

				countryName = rs.getString(3);
				regionName = rs.getString(4);
				stateName = rs.getString(5);
				cityName = rs.getString(6);

				rowCount++;
				data = data + countryName + columnSeperator + regionName
						+ columnSeperator + stateName + columnSeperator
						+ cityName + columnSeperator +

						townId + columnSeperator + townName + columnSeperator
						+ rowSeperator;
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

	// input=cityId+columnSeperator+townName
	public int setTown(String inData) {
		int countryId;
		int regionId;
		int stateId;
		int cityId;

		String townName;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 5) {
				countryId = Integer.parseInt(columnData[0]);
				regionId = Integer.parseInt(columnData[1]);
				stateId = Integer.parseInt(columnData[2]);
				cityId = Integer.parseInt(columnData[3]);
				townName = columnData[4];

			} else {
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.town_master where town_name='"
					+ townName + "'" + " and country_id=" + "'" + countryId + "'" + " and region_id=" + "'" + regionId + "'" 
					+ " and state_id=" + "'" + stateId + "'" + " and city_id=" + "'" + cityId + "'" ;
			query2 = "insert into fourth_dimension.town_master values(?,?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				 return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, townName);
			ps.setInt(3, countryId);
			ps.setInt(4, regionId);
			ps.setInt(5, stateId);
			ps.setInt(6, cityId);

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

	// input=townId
	public int deleteTown(String inData) {
		int townId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.town_master where town_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {

				townId = Integer.parseInt((rowData[i].trim()));
				// System.out.println("it is service and values are "+townId);
				ps = con.prepareStatement(query1);
				ps.setInt(1, townId);
				flag1 = ps.executeUpdate();

			}

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

	// input=townId+rowSeperator+cityId+columnSeperator+townId+columnSeperator+townName
	public int updateTown(String inData) {
		int townId;
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
				townId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;

			}
			query1 = "describe fourth_dimension.town_master";

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
				query2 = "update fourth_dimension.town_master set "
						+ columnName[i] + " =?  where town_id=? ";

				ps = con.prepareStatement(query2);
				if ((i == 2) || (i == 3) || (i == 4) || (i == 5))
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, townId);
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

	// Area Administration

	// input=cityId
	// output=areaId+columnSeperator+areaName+columnSeperator+rowSeperator

	public String getArea(String inData) {
		int townId;
		int areaId;
		String countryName;
		String regionName;
		String stateName;
		String cityName;
		String townName;
		String areaName;
		String zipCode;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {

			townId = Integer.parseInt(inData);
			System.out.println("town id is " + townId);
			query1 = "select area_id,area_name,zip_code,country_name,region_name,state_name,city_name,town_name from fourth_dimension.area_master a,country_master c,region_master r,state_master s,city_master ct,town_master t where a.country_id=c.country_id and a.region_id=r.region_id and a.state_id=s.state_id and a.city_id=ct.city_id and a.town_id=t.town_id and a.town_id="
					+ townId+" order by area_name";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				areaId = rs.getInt(1);
				areaName = rs.getString(2);
				zipCode = rs.getString(3);
				countryName = rs.getString(4);
				regionName = rs.getString(5);
				stateName = rs.getString(6);
				cityName = rs.getString(7);
				townName = rs.getString(8);

				rowCount++;
				data = data + countryName + columnSeperator + regionName
						+ columnSeperator + stateName + columnSeperator
						+ cityName + columnSeperator + townName
						+ columnSeperator + areaId + columnSeperator + areaName
						+ columnSeperator + zipCode + columnSeperator
						+ rowSeperator;
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

	// input=cityId+columnSeperator+areaName
	public int setArea(String inData) {
		int countryId;
		int regionId;
		int stateId;
		int cityId;
		int townId;
		String areaName;
		String zipCode;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 7) {
				countryId = Integer.parseInt(columnData[0]);
				regionId = Integer.parseInt(columnData[1]);
				stateId = Integer.parseInt(columnData[2]);
				cityId = Integer.parseInt(columnData[3]);
				townId = Integer.parseInt(columnData[4]);
				areaName = columnData[5];
				zipCode = columnData[6];

				System.out.println("This is service for add area " + countryId
						+ regionId + stateId + cityId + townId + areaName
						+ zipCode);

			} else {
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.area_master where area_name='"
					+ areaName + "'" + " and country_id=" + "'" + countryId + "'" + " and region_id=" + "'" + regionId + "'" + " and state_id=" + "'"
					+ stateId + "'" + " and city_id=" + "'" + cityId + "'" + " and town_id=" + "'" + townId + "'" + ";"; 
			query2 = "insert into fourth_dimension.area_master values(?,?,?,?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				 return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, areaName);
			ps.setString(3, zipCode);
			ps.setInt(4, countryId);
			ps.setInt(5, regionId);
			ps.setInt(6, stateId);
			ps.setInt(7, cityId);
			ps.setInt(8, townId);

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

	// input=areaId
	public int deleteArea(String inData) {
		int areaId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			System.out.println("it is service and values are " + inData);

			query1 = "delete from fourth_dimension.area_master where area_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {

				areaId = Integer.parseInt((rowData[i].trim()));
				System.out.println("it is service and values are " + areaId);
				ps = con.prepareStatement(query1);
				ps.setInt(1, areaId);
				flag1 = ps.executeUpdate();

			}

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

	// input=areaId+rowSeperator+cityId+columnSeperator+areaId+columnSeperator+areaName
	public int updateArea(String inData) {
		int areaId;
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
				areaId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;

			}
			query1 = "describe fourth_dimension.area_master";

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
				query2 = "update fourth_dimension.area_master set "
						+ columnName[i] + " =?  where area_id=? ";
				System.out.println("it is service area_update and Id is "
						+ columnData[i]);
				ps = con.prepareStatement(query2);
				if ((i == 3) || (i == 4) || (i == 5) || (i == 6) || (i == 7))
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, areaId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("it is service area_update and areaId is "
					+ areaId);

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

	/* Manage Comapany */

	// Company Administration

	// output=companyId+columnSeperator+companyName+columnSeperator+rowSeperator
	public String getCompany(String inData) {
		int companyId;
		String companyName;
		status = 0;
		data = "";

		query1 = "select id,name from fourth_dimension.company order by name ";
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

	// input=companyName
	public int setCompany(String inData) {
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

		return status;
	}

	// input=companyId
	public int deleteCompany(String inData) {
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
	public int updateCompany(String inData) {
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

		return status;
	}

	// Brand Administration

	// input=companyId
	// output=brandId+columnSeperator+brandName+columnseperator+rowSeperator
	public String getBrand(String inData) {
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
			status = -4;
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
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "Data Unavailable";
		}
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=companyId+columnSeperator+brandName
	public int setBrand(String inData) {
		int companyId;
		String brandName;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;

		}
		columnData = inData.split(columnSeperator);

		try {
			if (columnData.length == 2) {
				companyId = Integer.parseInt(columnData[0]);
				brandName = columnData[1];

			} else {
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.brand where name='"
					+ brandName + "'";
			query2 = "insert into fourth_dimension.brand values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, companyId);
			ps.setInt(2, 0);
			ps.setString(3, brandName);

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

	// input=brandId
	public int deleteBrand(String inData) {
		int brandId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
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

	// input=brandId+rowSeperator+brandId+columnSeperator+brandName
	public int updateBrand(String inData) {
		int brandId;
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
				brandId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
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
				status = -2;
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

	// Depot Administration

	// input=areaId+columnSeperator+companyId
	// output=depotId+columnSeperator+depotName+columnSeperator+depotType+columnSeperator+depotContactPerson+columnSeperator+depotContactNumber+columnSeperator+address+columnSeperator+rowSeperator

	public String getDepot(String inData) {
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
					+ areaId + " and company_id=" + companyId + " order by name";
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

	public int setDepot(String inData) {
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
			status = -2;
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
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.depot where name='"
					+ depotName + "'";
			query2 = "insert into fourth_dimension.depot values(?,?,?,?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
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

	// input=depotId
	public int deleteDepot(String inData) {
		int depotId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
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

	// input=depotId+rowSeperator+areaId+columnSeperator+companyId+columnSeperator+depotId+columnSeperator+depotName+columnSeperator+depotType+columnSeperator+depotContactPerson+columnSeperator+depotContactNumber+columnSeperator+address+columnSeperator+rowSeperator
	public int updateDepot(String inData) {
		int depotId;
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
				depotId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
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
				status = -2;
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

	// Store Administration

	// input=areaId
	// output=storeId+columnSeperator+storeName+columnSeperator+storeType+columnSeperator+storeContactPerson+columnSeperator+storeContactNumber+columnSeperator+address+columnSeperator+rowSeperator

	public String getStore(String inData) {
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
					+ areaId + " order by name";
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

	/*
	 * input=areaId+columnSeperator+storeName+columnSeperator+storeType+columnSeperator
	 * +
	 * storeContactPerson+columnSeperator+storeContactNumber+columnSeperator+address
	 * +columnSeperator
	 */

	/*
	 * public int setStore(String inData) { int areaId; String storeName; String
	 * storeType; String storeContactPerson; String storeContactNumber; String
	 * storeAddress; status = 0;
	 * 
	 * flag1 = 0;
	 * 
	 * System.out.println("this is add store " + inData); if ((inData == null)
	 * || inData.equals("")) { status = -2; return status; } columnData =
	 * inData.split(columnSeperator); try { if (columnData.length == 6) { areaId
	 * = Integer.parseInt(columnData[0]); storeName = columnData[1]; storeType =
	 * columnData[2]; storeContactPerson = columnData[3]; storeContactNumber =
	 * columnData[4]; storeAddress = columnData[5];
	 * 
	 * } else { status = -2; return status; } query1 =
	 * "select * from fourth_dimension.store where name='" + storeName + "'";
	 * query2 = "insert into fourth_dimension.store values(?,?,?,?,?,?,?)";
	 * 
	 * con = cp.getConnection(); st = con.createStatement(); rs =
	 * st.executeQuery(query1); if (rs.next()) { status = -3; return status; }
	 * ps = con.prepareStatement(query2); ps.setInt(1, areaId); ps.setInt(2, 0);
	 * ps.setString(3, storeName); ps.setString(4, storeType); ps.setString(5,
	 * storeContactPerson); ps.setString(6, storeContactNumber); ps.setString(7,
	 * storeAddress);
	 * 
	 * flag1 = ps.executeUpdate();
	 * 
	 * } catch (Exception e) { status = -4;
	 * 
	 * } finally { try { if (con != null) { cp.free(con); con = null; } if (st
	 * != null) st.close(); if (ps != null) ps.close(); if (rs != null)
	 * rs.close(); } catch (Exception e) { status = -4; }
	 * 
	 * } if ((flag1 == 0)) { status = -5; }
	 * 
	 * 
	 * return status; }
	 */

	// input=storeId
	public int deleteStore(String inData) {
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
	public int updateStore(String inData) {
		int storeId;
		status = 0;
		data = "success";
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
				storeId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
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
				status = -2;
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

	/* Manage Material */

	// Material Group Administration

	// output=materialGroupId+columnSeperator+materialGroupName+columnSeperator+materialGroupCode+columnSeperator+rowSeperator

	public String getMaterialGroup(String inData) {
		int materialGroupId;
		String materialGroupName;
		int materialGroupCode;
		String matGroupCode;
		status = 0;
		data = "";

		query1 = "select * from fourth_dimension.material_group_master order by material_group_name";

		rowCount = 0;
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialGroupId = rs.getInt(1);
				materialGroupName = rs.getString(2);
				materialGroupCode = rs.getInt(3);
				if(materialGroupCode<10){
					matGroupCode="0"+materialGroupCode;
				}
				else{
					matGroupCode=String.valueOf(materialGroupCode);
				}
				rowCount++;
				data = data + materialGroupId + columnSeperator
						+ materialGroupName + columnSeperator
						+ matGroupCode + columnSeperator + rowSeperator;
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

	public int validateMaterialGroupCode(String inData) {

		int materialGroupCode;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		materialGroupCode = Integer.parseInt(inData);
		query1 = "select * from fourth_dimension.material_group_master where material_group_code="
				+ materialGroupCode;

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;

				return status;
			}

		} catch (Exception e) {
			status = -4;

		} finally {
			try {
				if (con != null) {
					cp.free(con); // return outData = status + statusSeperator +
									// data;
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

		return status;
	}

	// input=materialGroupName
	public int setMaterialGroup(String inData) {
		String materialGroupName;
		int materialGroupCode;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		if (columnData.length != 2) {
			status = -2;
			return status;
		}
		materialGroupName = columnData[0];
		materialGroupCode = Integer.parseInt(columnData[1]);

		query1 = "select * from fourth_dimension.material_group_master where material_group_name='"
				+ materialGroupName + "'";
		// query2 =
		// "select material_group_code from fourth_dimension.material_group_master order by material_group_code desc";
		query3 = "insert into fourth_dimension.material_group_master values(?,?,?)";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;

				return status;
			}
			/*
			 * rs = st.executeQuery(query2); if (rs.next()) { materialGroupCode
			 * =rs.getInt(1) + 1; } else { materialGroupCode = 01; }
			 */
			ps = con.prepareStatement(query3);
			ps.setInt(1, 0);
			ps.setString(2, materialGroupName);
			ps.setInt(3, materialGroupCode);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;

		} finally {
			try {
				if (con != null) {
					cp.free(con); // return outData = status + statusSeperator +
									// data;
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
	public int deleteMaterialGroup(String inData) {
		Integer materialGroupId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.material_group_master where material_group_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				materialGroupId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, materialGroupId);
				flag1 = flag1 + ps.executeUpdate();

			}

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
		if ((flag1 < rowData.length)) {
			status = -5;

		}

		return status;

	}

	// input=materialGroupId+rowSeperator+materialGroupId+columnSeperator+materialGroupName+columnSeperator+materialGroupCode
	public int updateMaterialGroup(String inData) {
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
			query1 = "describe fourth_dimension.material_group_master";

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
				query2 = "update fourth_dimension.material_group_master set "
						+ columnName[i] + " =?  where material_group_id=? ";

				ps = con.prepareStatement(query2);
				if (i == 2) {
					ps.setInt(1, Integer.parseInt(columnData[i]));
				} else {
					ps.setString(1, columnData[i]);
				}
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
	public String getMaterialSubGroup(String inData) {
		int materialGroupId;
		int materialSubGroupId;
		int materialSubGroupCode;
		String matSubGroupCode;
		String materialSubGroupName;
		String materialGroupName;
		status = 0;
		data = "";

		if ((inData == null) || inData.equals("")) {
			status = -2;
			data = "Input data is insufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		System.out
				.println("service mat sub group materialgroupId is " + inData);
		try {
			materialGroupId = Integer.parseInt(inData);

			query1 = "select material_subrgoup_id,material_subgroup_name,material_subgroup_code,material_group_name from fourth_dimension.material_subgroup_master a,material_group_master c where a.material_group_id=c.material_group_id and a.material_group_id="
					+ materialGroupId+" order by material_subgroup_name";

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialSubGroupId = rs.getInt(1);
				materialSubGroupName = rs.getString(2);
				materialSubGroupCode = rs.getInt(3);
				materialGroupName = rs.getString(4);
				
				if(materialSubGroupCode<10){
					matSubGroupCode="0"+materialSubGroupCode;
				}
				else{
					matSubGroupCode=String.valueOf(materialSubGroupCode);
				}
				rowCount++;
				data = data + materialGroupName + columnSeperator
						+ materialSubGroupId + columnSeperator
						+ materialSubGroupName + columnSeperator
						+ matSubGroupCode + columnSeperator + rowSeperator;
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

	public int validateMaterialSubGroupCode(String inData) {

		int materialSubGroupCode = 0;
		int materialGroupId = 0;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		columnData = inData.split(columnSeperator);
		if (columnData.length == 2) {
			materialGroupId = Integer.parseInt(columnData[0]);
			materialSubGroupCode = Integer.parseInt(columnData[1]);
		}

		query1 = "select * from fourth_dimension.material_subgroup_master where material_group_id="
				+ materialGroupId
				+ " and material_subgroup_code="
				+ materialSubGroupCode;

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;

				return status;
			}

		} catch (Exception e) {
			status = -4;

		} finally {
			try {
				if (con != null) {
					cp.free(con); // return outData = status + statusSeperator +
									// data;
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

		return status;
	}

	// input=materialGroupId+columnSeperator+materialSubGroupName
	public int setMaterialSubGroup(String inData) {
		int materialGroupId;
		String materialSubGroupName;
		int materialSubGroupCode;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}
		columnData = inData.split(columnSeperator);

		try {
			if (columnData.length == 3) {
				materialGroupId = Integer.parseInt(columnData[0]);
				materialSubGroupName = columnData[1];
				materialSubGroupCode = Integer.parseInt(columnData[2]);

			} else {
				status = -2;

				return status;
			}
			query1 = "select * from fourth_dimension.material_subgroup_master where material_group_id="
					+ materialGroupId
					+ " and material_subgroup_name='"
					+ materialSubGroupName + "'";
			/*
			 * query2 =
			 * "select material_subgroup_code from fourth_dimension.material_subgroup_master where material_group_id="
			 * + materialGroupId + " order by material_subgroup_code desc";
			 */
			query3 = "insert into fourth_dimension.material_subgroup_master values(?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			/*
			 * rs = st.executeQuery(query2); if (rs.next()) {
			 * materialSubGroupCode = rs.getInt(1) + 1; } else {
			 * materialSubGroupCode = 01; }
			 */

			ps = con.prepareStatement(query3);
			ps.setInt(1, 0);
			ps.setString(2, materialSubGroupName);
			ps.setInt(3, materialGroupId);
			ps.setInt(4, materialSubGroupCode);

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
	public int deleteMaterialSubGroup(String inData) {
		Integer materialSubGroupId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.material_subgroup_master where material_subrgoup_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				materialSubGroupId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, materialSubGroupId);
				flag1 = flag1 + ps.executeUpdate();

			}

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
		if ((flag1 < rowData.length)) {
			status = -5;

		}

		return status;

	}

	// input=materialSubGroupId+rowSeperator+materialGroupId+columnSeperator+materialSubGroupId+columnSeperator+materialSubGroupName+columnSeperator+materialSubGroupCode
	public int updateMaterialSubGroup(String inData) {
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
				System.out.println("callin service update 1");
				return status;

			}
			query1 = "describe fourth_dimension.material_subgroup_master";

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
				System.out.println("callin service update 2");
				return status;

			}
			for (int i = 0; i < columnName.length; i++) {

				if ((columnData[i] == null) || (columnData[i].equals(""))
						|| (columnData[i].equals("null")) || (i == 0)) {
					continue;
				}

				flagCheck++;
				query2 = "update fourth_dimension.material_subgroup_master set "
						+ columnName[i] + " =?  where material_subrgoup_id=? ";

				System.out.println("callin service update " + query2);

				ps = con.prepareStatement(query2);
				if ((i == 2) || (i == 3)) {
					// System.out.println("callin service update 3");
					ps.setInt(1, Integer.parseInt(columnData[i]));
					System.out.println("callin service update columnData is "
							+ columnData[i]);
				} else
					ps.setString(1, columnData[i]);
				ps.setInt(2, materialSubGroupId);
				flag1 = ps.executeUpdate() + flag1;

			}

		} catch (Exception e) {
			status = -4;
			System.out.println("exception " + e);

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

	// output=materialId+columnSeperator+materialName+columnSeperator+materialCode+columnSeperator+rowSeperator
	public String getAllMaterial(String inData) {

		int materialId;
		int materialHeight;
		int materialWidth;
		String materialName;
		String materialCode;
		status = 0;
		data = "";

		try {

			query1 = "select id,name,code,height,width from fourth_dimension.material_master order by material name";

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialId = rs.getInt(1);
				materialName = rs.getString(2);
				materialCode = rs.getString(3);
				materialHeight = rs.getInt(4);
				materialWidth = rs.getInt(5);
				rowCount++;
				data = data + materialId + columnSeperator + materialName
						+ columnSeperator + materialCode + columnSeperator
						+ materialHeight + columnSeperator + materialWidth
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

	// input=materialId+columnSeperator+materialId+columnSeperator
	// output=materialDimension+columnSeperator+materialDimension+columnSeperator
	public String getMaterialDimension(String inData) {

		int materialDimension;
		String materialName;
		String materialCode;
		status = 0;
		data = "";

		if ((inData == null) || inData.equals("")) {
			status = -2;
			data = "Input data is insufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		columnData = inData.split(columnSeperator);
		try {
			query1 = "select (height*width) as dimension from fourth_dimension.material_master where id in(";

			for (int i = 0; i < columnData.length; i++) {
				if (i == columnData.length - 1) {
					query1 = query1 + Integer.parseInt(columnData[i]) + ")";
					break;
				}
				query1 = query1 + Integer.parseInt(columnData[i]) + ",";

			}

			System.out.println("This is query for getting dimension " + query1);

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialDimension = rs.getInt(1);

				rowCount++;
				data = data + materialDimension + columnSeperator;
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

	// input=materialSubGroupId
	// output=materialId+columnSeperator+materialName+columnSeperator+materialCode+columnSeperator+rowSeperator
	public String getMaterial(String inData) {
		ResultSet rs1;
		int materialSubGroupId;
		int materialId;
		String materialTypeId;
		int serialNumber;
		String sNumber;
		double materialRate;
		String materialTypeName = null;
		String materialName;
		String materialCode;

		double height;
		String heightUnitId;
		String heightUnitName = null;

		double width;
		String widthUnitId;
		String widthUnitName = null;

		double thickness;
		String thicknessUnitId;
		String thicknessUnitName = null;

		double capacity;
		String capacityUnitId;
		String capacityUnitName = null;

		double stdOrderingSize;
		String stdOrderingSizeUnitId;
		String stdOrderingSizeUnitName = null;

		double conversionSize;
		String conversionSizeUnitId;
		String conversionSizeUnitName = null;

		String color;

		String storageSpecs;
		String expirySpecs;

		String consolidationType;
		status = 0;
		data = "";

		if ((inData == null) || inData.equals("")) {
			status = -2;
			data = "Input data is insufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}

		try {
			materialSubGroupId = Integer.parseInt(inData);

			query1 = "select * from fourth_dimension.material_master where material_subgroup_id="
					+ materialSubGroupId + " order by material_name";

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialId = rs.getInt(1);
				serialNumber = rs.getInt(3);
				if(serialNumber<10){
					sNumber="00"+serialNumber;
				}
				else if(serialNumber<100){
					sNumber="0"+serialNumber;
				}
				else{
					sNumber=String.valueOf(serialNumber);
				}
				materialCode = rs.getString(4);
				if (rs.getObject(5) != null)
					materialTypeId = String.valueOf(rs.getInt(5));
				else
					materialTypeId = null;

				materialName = rs.getString(6);
				height = rs.getDouble(7);
				if (rs.getObject(8) != null)
					heightUnitId = String.valueOf(rs.getInt(8));
				else
					heightUnitId = null;

				width = rs.getDouble(9);
				if (rs.getObject(10) != null)
					widthUnitId = String.valueOf(rs.getInt(10));
				else
					widthUnitId = null;

				thickness = rs.getDouble(11);
				if (rs.getObject(12) != null)
					thicknessUnitId = String.valueOf(rs.getInt(12));
				else
					thicknessUnitId = null;

				capacity = rs.getDouble(13);
				if (rs.getObject(14) != null)
					capacityUnitId = String.valueOf(rs.getInt(14));
				else
					capacityUnitId = null;

				stdOrderingSize = rs.getDouble(15);

				if (rs.getObject(16) != null)
					stdOrderingSizeUnitId = String.valueOf(rs.getInt(16));
				else
					stdOrderingSizeUnitId = null;

				conversionSize = rs.getDouble(17);
				if (rs.getObject(18) != null)
					conversionSizeUnitId = String.valueOf(rs.getInt(18));
				else
					conversionSizeUnitId = null;

				color = rs.getString(19);
				storageSpecs = rs.getString(20);
				expirySpecs = rs.getString(21);
				materialRate = rs.getDouble(22);

				if (heightUnitId == null) {
					heightUnitName = null;
				} else {
					ps = con
							.prepareStatement("select unit_name from fourth_dimension.unit_master where id=?");
					ps.setInt(1, Integer.parseInt(heightUnitId));
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						heightUnitName = rs1.getString(1);
					}
				}

				if (widthUnitId == null) {
					widthUnitName = null;
				} else {
					ps = con
							.prepareStatement("select unit_name from fourth_dimension.unit_master where id=?");
					ps.setInt(1, Integer.parseInt(widthUnitId));
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						widthUnitName = rs1.getString(1);
					}
				}

				if (thicknessUnitId == null) {
					thicknessUnitName = null;
				} else {
					ps = con
							.prepareStatement("select unit_name from fourth_dimension.unit_master where id=?");
					ps.setInt(1, Integer.parseInt(thicknessUnitId));
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						thicknessUnitName = rs1.getString(1);
					}
				}

				if (capacityUnitId == null) {
					capacityUnitName = null;
				} else {
					ps = con
							.prepareStatement("select unit_name from fourth_dimension.unit_master where id=?");
					ps.setInt(1, Integer.parseInt(capacityUnitId));
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						capacityUnitName = rs1.getString(1);
					}
				}

				if (stdOrderingSizeUnitId == null) {
					stdOrderingSizeUnitName = null;
				} else {
					ps = con
							.prepareStatement("select unit_name from fourth_dimension.unit_master where id=?");
					ps.setInt(1, Integer.parseInt(stdOrderingSizeUnitId));
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						stdOrderingSizeUnitName = rs1.getString(1);
					}
				}

				if (conversionSizeUnitId == null) {
					conversionSizeUnitName = null;
				} else {
					ps = con
							.prepareStatement("select unit_name from fourth_dimension.unit_master where id=?");
					ps.setInt(1, Integer.parseInt(conversionSizeUnitId));
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						conversionSizeUnitName = rs1.getString(1);
					}
				}

				if (materialTypeId == null) {
					materialTypeName = null;
				} else {
					ps = con
							.prepareStatement("select material_type_name from fourth_dimension.material_type where material_type_id=?");
					ps.setInt(1, Integer.parseInt(materialTypeId));
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						materialTypeName = rs1.getString(1);
					}
				}

				rowCount++;
				data = data + materialId + columnSeperator + sNumber
						+ columnSeperator + materialCode + columnSeperator
						+ materialTypeId + columnSeperator + materialTypeName
						+ columnSeperator + materialName + columnSeperator
						+ height + columnSeperator + heightUnitId
						+ columnSeperator + heightUnitName + columnSeperator
						+ width + columnSeperator + widthUnitId
						+ columnSeperator + widthUnitName + columnSeperator
						+ thickness + columnSeperator + thicknessUnitId
						+ columnSeperator + thicknessUnitName + columnSeperator
						+ capacity + columnSeperator + capacityUnitId
						+ columnSeperator + capacityUnitName + columnSeperator
						+ stdOrderingSize + columnSeperator
						+ stdOrderingSizeUnitId + columnSeperator
						+ stdOrderingSizeUnitName + columnSeperator
						+ conversionSize + columnSeperator
						+ conversionSizeUnitId + columnSeperator
						+ conversionSizeUnitName + columnSeperator + color
						+ columnSeperator + storageSpecs + columnSeperator
						+ expirySpecs + columnSeperator + materialRate
						+ columnSeperator + rowSeperator;
				System.out.println(data);
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

	// input=materialSubGroupId+columnSeperator+materialName

	public int setMaterial(String inData) {

		System.out.println();
		status = 0;
		int materialSubGroupId;
		String materialTypeId;
		String materialName;
		String materialCode;
		int serialNumber;

		String height;
		String heightUnitId;

		String width;
		String widthUnitId;

		String thickness;
		String thicknessUnitId;

		String capacity;
		String capacityUnitId;

		String stdOrderingSize;
		String stdOrderingSizeUnitId;

		String conversionSize;
		String conversionSizeUnitId;

		double materialRate;
		String color;
		String storageSpecs;
		String expirySpecs;

		String consolidationType;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}
		columnData = inData.split(columnSeperator);
		System.out.println("service indata is "+inData);
		try {
			if (columnData.length == 21) {
				materialSubGroupId = Integer.parseInt(columnData[0]);
				serialNumber = Integer.parseInt(columnData[1]);
				materialCode = columnData[2];
				thickness = columnData[3];
				thicknessUnitId = columnData[4];
				height = columnData[5];
				heightUnitId = columnData[6];
				width = columnData[7];
				widthUnitId = columnData[8];
				capacity = columnData[9];
				capacityUnitId = columnData[10];
				stdOrderingSize = columnData[11];
				stdOrderingSizeUnitId = columnData[12];
				conversionSize = columnData[13];
				conversionSizeUnitId = columnData[14];
				color = columnData[15];
				materialRate = Double.parseDouble(columnData[16]);
				materialTypeId = columnData[17];
				materialName = columnData[18];
				storageSpecs = columnData[19];
				expirySpecs = columnData[20];

			} else {
				status = -2;

				return status;
			}
			
			double check=Double.parseDouble(thickness);
			System.out.println("thikness check is "+check);
			query3 = "insert into fourth_dimension.material_master values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			con = cp.getConnection();
			ps = con.prepareStatement(query3);
			ps.setInt(1, 0);
			ps.setInt(2, materialSubGroupId);
			ps.setInt(3, serialNumber);
			ps.setString(4, materialCode);

			if ((materialTypeId == null) || (materialTypeId.equals("null"))) {
				ps.setNull(5, java.sql.Types.INTEGER);
			} else {
				ps.setInt(5, Integer.parseInt(materialTypeId));
			}
			ps.setString(6, materialName);

			ps.setDouble(7, Double.parseDouble(height));

			if ((heightUnitId == null) || (heightUnitId.equals("null"))) {
				ps.setNull(8, java.sql.Types.INTEGER);
			} else {
				ps.setInt(8, Integer.parseInt(heightUnitId));
			}

			ps.setDouble(9, Double.parseDouble(width));

			if ((widthUnitId == null) || (widthUnitId.equals("null"))) {
				ps.setNull(10, java.sql.Types.INTEGER);
			} else {
				ps.setInt(10, Integer.parseInt(widthUnitId));
			}

			ps.setDouble(11, Double.parseDouble(thickness));

			if ((thicknessUnitId == null) || (thicknessUnitId.equals("null"))) {
				ps.setNull(12, java.sql.Types.INTEGER);
			} else {
				ps.setInt(12, Integer.parseInt(thicknessUnitId));
			}

			ps.setDouble(13, Double.parseDouble(capacity));

			if ((capacityUnitId == null) || (capacityUnitId.equals("null"))) {
				ps.setNull(14, java.sql.Types.INTEGER);
			} else {
				ps.setInt(14, Integer.parseInt(capacityUnitId));
			}

			ps.setDouble(15, Double.parseDouble(stdOrderingSize));

			if ((stdOrderingSizeUnitId == null)
					|| (stdOrderingSizeUnitId.equals("null"))) {
				ps.setNull(16, java.sql.Types.INTEGER);
			} else {
				ps.setInt(16, Integer.parseInt(stdOrderingSizeUnitId));
			}

			ps.setDouble(17, Double.parseDouble(conversionSize));

			if ((conversionSizeUnitId == null)
					|| (conversionSizeUnitId.equals("null"))) {
				ps.setNull(18, java.sql.Types.INTEGER);
			} else {
				ps.setInt(18, Integer.parseInt(conversionSizeUnitId));
			}

			if ((color == null) || (color.equals("null")))
				ps.setNull(19, java.sql.Types.VARCHAR);
			else
				ps.setString(19, color);

			if ((storageSpecs == null) || (storageSpecs.equals("null")))
				ps.setNull(20, java.sql.Types.VARCHAR);
			else
				ps.setString(20, storageSpecs);

			if ((expirySpecs == null) || (expirySpecs.equals("null")))
				ps.setNull(21, java.sql.Types.VARCHAR);
			else
				ps.setString(21, expirySpecs);

			ps.setDouble(22, materialRate);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			// System.out.println(e);
			e.printStackTrace();

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
				e.printStackTrace();
			}

		}
		if ((flag1 == 0)) {
			status = -5;

		}

		return status;
	}

	// input=materialId
	public int deleteMaterial(String inData) {
		int materialId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.material_master where material_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+materialId);
				materialId = Integer.parseInt((rowData[i].trim()));
				System.out.println("material Group ID String " + materialId);

				ps = con.prepareStatement(query1);
				ps.setInt(1, materialId);
				flag1 = ps.executeUpdate();

			}

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
	public int updateMaterial(String inData) {
		status = 0;
		int materialSubGroupId;
		int materialId;
		String materialTypeId;
		String materialName;
		String materialCode;
		int serialNumber;

		String height;
		String heightUnitId;

		String width;
		String widthUnitId;

		String thickness;
		String thicknessUnitId;

		String capacity;
		String capacityUnitId;

		String stdOrderingSize;
		String stdOrderingSizeUnitId;

		String conversionSize;
		String conversionSizeUnitId;

		Double materialRate;
		String color;
		String storageSpecs;
		String expirySpecs;

		String consolidationType;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}
		columnData = inData.split(columnSeperator);

		try {
			if (columnData.length == 22) {
				materialId = Integer.parseInt(columnData[0]);
				materialSubGroupId = Integer.parseInt(columnData[1]);
				serialNumber = Integer.parseInt(columnData[2]);
				materialCode = columnData[3];
				thickness = columnData[4];
				thicknessUnitId = columnData[5];
				height = columnData[6];
				heightUnitId = columnData[7];
				width = columnData[8];
				widthUnitId = columnData[9];
				capacity = columnData[10];
				capacityUnitId = columnData[11];
				stdOrderingSize = columnData[12];
				stdOrderingSizeUnitId = columnData[13];
				conversionSize = columnData[14];
				conversionSizeUnitId = columnData[15];
				color = columnData[16];
				materialRate = Double.parseDouble(columnData[17]);
				materialTypeId = columnData[18];
				materialName = columnData[19];
				storageSpecs = columnData[20];
				expirySpecs = columnData[21];

			} else {
				status = -2;

				return status;
			}
			query3 = "update fourth_dimension.material_master set material_subgroup_id=? ,serial_number=? ,"
					+ "material_code=? ,material_type_id=? ,material_name=? ,height=? ,height_unit=? ,width=? ,"
					+ "width_unit=?, thickness=?,thickness_unit=?,capacity=? ,capacity_unit=? ,std_ordering_size=? ,"
					+ "std_ordering_size_unit=? ,conversion_size=? ,conversion_size_unit=? ,color=? ,storage_specs=? ,"
					+ "expiry_specs=? ,rate=? where material_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query3);

			ps.setInt(1, materialSubGroupId);
			ps.setInt(2, serialNumber);
			ps.setString(3, materialCode);

			if ((materialTypeId == null) || (materialTypeId.equals("null"))) {
				ps.setNull(4, java.sql.Types.INTEGER);
			} else {
				ps.setInt(4, Integer.parseInt(materialTypeId));
			}
			ps.setString(5, materialName);

			ps.setDouble(6, Double.parseDouble(height));

			if ((heightUnitId == null) || (heightUnitId.equals("null"))) {
				ps.setNull(7, java.sql.Types.INTEGER);
			} else {
				ps.setInt(7, Integer.parseInt(heightUnitId));
			}

			ps.setDouble(8, Double.parseDouble(width));

			if ((widthUnitId == null) || (widthUnitId.equals("null"))) {
				ps.setNull(9, java.sql.Types.INTEGER);
			} else {
				ps.setInt(9, Integer.parseInt(widthUnitId));
			}

			ps.setDouble(10, Double.parseDouble(thickness));

			if ((thicknessUnitId == null) || (thicknessUnitId.equals("null"))) {
				ps.setNull(11, java.sql.Types.INTEGER);
			} else {
				ps.setInt(11, Integer.parseInt(thicknessUnitId));
			}

			ps.setDouble(12, Double.parseDouble(capacity));

			if ((capacityUnitId == null) || (capacityUnitId.equals("null"))) {
				ps.setNull(13, java.sql.Types.INTEGER);
			} else {
				ps.setInt(13, Integer.parseInt(capacityUnitId));
			}

			ps.setDouble(14, Double.parseDouble(stdOrderingSize));

			if ((stdOrderingSizeUnitId == null)
					|| (stdOrderingSizeUnitId.equals("null"))) {
				ps.setNull(15, java.sql.Types.INTEGER);
			} else {
				ps.setInt(15, Integer.parseInt(stdOrderingSizeUnitId));
			}

			ps.setDouble(16, Double.parseDouble(conversionSize));

			if ((conversionSizeUnitId == null)
					|| (conversionSizeUnitId.equals("null"))) {
				ps.setNull(17, java.sql.Types.INTEGER);
			} else {
				ps.setInt(17, Integer.parseInt(conversionSizeUnitId));
			}

			if ((color == null) || (color.equals("null")))
				ps.setNull(18, java.sql.Types.VARCHAR);
			else
				ps.setString(18, color);

			if ((storageSpecs == null) || (storageSpecs.equals("null")))
				ps.setNull(19, java.sql.Types.VARCHAR);
			else
				ps.setString(19, storageSpecs);

			if ((expirySpecs == null) || (expirySpecs.equals("null")))
				ps.setNull(20, java.sql.Types.VARCHAR);
			else
				ps.setString(20, expirySpecs);

			ps.setDouble(21, materialRate);
			ps.setInt(22, materialId);

			flag1 = ps.executeUpdate();

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

	// Material Unit Administration

	// output=materialUnitId+columnSeperator+materialUnitName+columnSeperator+rowSeperator

	public String getMaterialUnit(String inData) {
		int materialUnitId;
		String materialUnitName;
		status = 0;
		data = "";

		query1 = "select * from fourth_dimension.unit_master order by unit_name";

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

	// input=materialUnitName
	public int setMaterialUnit(String inData) {
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
	public int deleteMaterialUnit(String inData) {
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
	public int updateMaterialUnit(String inData) {
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
				status = -4;
			}

		}
		if ((flag1 < flagCheck)) {
			status = -5;

		}

		return status;
	}

	// Material Type Administration

	// output=materailTypeId+columnSeperator+materialTypeName+columnSeperator+rowSeperator
	public String getMaterialType(String inData) {
		int materialTypeId;
		String materialTypeName;

		status = 0;
		data = "";

		query1 = "select * from fourth_dimension.material_type order by material_type_name ";

		rowCount = 0;
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				materialTypeId = rs.getInt(1);
				materialTypeName = rs.getString(2);

				rowCount++;
				data = data + materialTypeId + columnSeperator
						+ materialTypeName + columnSeperator + rowSeperator;
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

	// input=materialTypeName
	public int setMaterialType(String inData) {
		String materialTypeName;
		int materialTypeCode;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}
		materialTypeName = inData;

		query1 = "select * from fourth_dimension.material_type where material_type_name='"
				+ materialTypeName + "'";

		query3 = "insert into fourth_dimension.material_type values(?,?)";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;

				return status;
			}

			ps = con.prepareStatement(query3);
			ps.setInt(1, 0);
			ps.setString(2, materialTypeName);

			flag1 = ps.executeUpdate();

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

	// input=materialTypeId
	public int deleteMaterialType(String inData) {
		Integer materialTypeId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.material_type where material_type_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				materialTypeId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, materialTypeId);
				flag1 = flag1 + ps.executeUpdate();

			}

		} catch (Exception e) {
			status = -4;
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
				status = -4;
			}
		}
		if ((flag1 < rowData.length)) {
			status = -5;

		}

		return status;

	}

	// input=
	// materialTypeId+rowSeperator+materialTypeId+columnSeperator+materailTypeName
	public int updateMaterialType(String inData) {
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
						+ columnName[i] + " =?  where material_type_id=? ";

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

	public String getMaterialCode(String inData) {
		int materialSubGroupId;
		int materialGroupCode = 0;
		String materialGroupCodeStr;

		int materialSubGroupCode = 0;
		String materialSubGroupCodeStr;
		int serialNumber = 1;
		String serialNumberStr;
		String materialCode;
		status = 0;
		data = "";

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return outData = status + statusSeperator + data;
		}

		try {
			materialSubGroupId = Integer.parseInt(inData);
			System.out
					.println("this is get material Code Service and matsubgroupId is "
							+ materialSubGroupId);
			query1 = "SELECT material_group_code,material_subgroup_code FROM "
					+ "fourth_dimension.material_subgroup_master msm, "
					+ "fourth_dimension.material_group_master mgm "
					+ "where msm.material_group_id=mgm.material_group_id "
					+ "and msm.material_subrgoup_id=" + materialSubGroupId;

			query2 = "SELECT serial_number FROM fourth_dimension.material_master "
					+ "where material_subgroup_id="
					+ materialSubGroupId
					+ " order by serial_number desc";

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {

				materialGroupCode = rs.getInt(1);
				materialSubGroupCode = rs.getInt(2);

				rowCount++;
			}

			rs = st.executeQuery(query2);
			if (rs.next()) {

				serialNumber = rs.getInt(1) + 1;
			}

			if (materialGroupCode < 10) {
				materialGroupCodeStr = "0" + materialGroupCode;
			} else {
				materialGroupCodeStr = String.valueOf(materialGroupCode);
			}

			if (materialSubGroupCode < 10) {
				materialSubGroupCodeStr = "0" + materialSubGroupCode;
			} else {
				materialSubGroupCodeStr = String.valueOf(materialSubGroupCode);
			}

			if (serialNumber < 10) {
				serialNumberStr = "00" + serialNumber;
			}

			else if (serialNumber < 100) {
				serialNumberStr = "0" + serialNumber;
			} else {
				serialNumberStr = String.valueOf(serialNumber);
			}
			materialCode = materialGroupCodeStr + materialSubGroupCodeStr
					+ serialNumberStr;
			data = materialCode;

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
		System.out.println("this is get material Code Service and outdata is "
				+ outData);
		return outData;
	}

	// Validate Material Serial Number
	public int validateMaterialSerialNumber(String inData) {

		int materialSubGroupId;
		int materialSerialNumber;
		status = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}
		columnData = inData.split(columnSeperator);

		try {

			if (columnData.length == 2) {
				materialSubGroupId = Integer.parseInt(columnData[0]);
				materialSerialNumber = Integer.parseInt(columnData[1]);
			}

			else {
				status = -2;

				return status;

			}

			query1 = "select serial_number from fourth_dimension.material_master "
					+ "where material_subgroup_id="
					+ materialSubGroupId
					+ " and serial_number=" + materialSerialNumber;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;

				return status;
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

		return status;
	}

	// Element Administration

	// output=
	// compositElementId/elementId+columnSeperator+compositElementName/elementName+columnSeperator+elementName+fieldSeperator+elementName+fieldSeperator+columnSeperator+rowSeperator
	public String getElement(String inData) {
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
			data = "Exception:-" + e;

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

	// input=materialSubGroupId
	// output=materialId+columnSeperator+materialName+columnSeperator+materialCode+columnSeperator+rowSeperator
	public String getGeneralElement(String inData) {
		int elementId;
		String elementName;
		status = 0;
		data = "";

		try {

			query1 = "select * from fourth_dimension.element where id not in(select composit_element_id from fourth_dimension.composit_element)";

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				elementId = rs.getInt(1);
				elementName = rs.getString(2);

				rowCount++;
				data = data + elementId + columnSeperator + elementName
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

	// input=
	// compositElementName/elementName+rowSeperator+elementId+columnSeperator+elementId+columnSeperator
	public int setElement(String inData) {
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
	public int deleteElement(String inData) {
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
	public int updateElement(String inData) {
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
	public int updateCompositElement(String inData) {
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
	public String getComponent(String inData) {
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

	// input=elementId+columnSeperator+componentName
	public int setComponent(String inData) {
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
	public int deleteComponent(String inData) {
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
	public int updateComponent(String inData) {
		int componentId;
		String componentName= "";
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
			if (rowData.length == 1) {
				columnData = rowData[0].split(columnSeperator);
				componentId = Integer.parseInt(columnData[0]);
				componentName = columnData[1];

			} else {
				status = -2;
				return status;

			}
/*			query1 = "describe fourth_dimension.component";
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
			System.out.println("flag is " + flag1);*/
			query1 = "UPDATE fourth_dimension.component_master SET component_name=? WHERE component_id=?";
			ps = con.prepareStatement(query1);
			ps.setString(1, componentName);
			ps.setInt(2, componentId);
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
		if ((flag1 < flagCheck)) {
			status = -5;

		}

		return status;
	}

	// input=materialSubGroupId
	// output=serialNumber
	public String getSerialNumber(String inData) {
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

	/* Manage FD */

	// FdOrg Administration

	// input=areaId
	// output=hubId+columnSeperator+hubName+columnSeperator+hubPhoneNumber+columnSeperator+hubAddress+columnSeperator+rowSeperator
	public String getFdOrg(String inData) {

		int fdOrgId;
		String fdOrgName;
		String fdOrgDetail;
		status = 0;
		data = "";

		try {

			query1 = "select * from fourth_dimension.fd_org_master order by org_name ";

			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				fdOrgId = rs.getInt(1);
				fdOrgName = rs.getString(2);
				fdOrgDetail = rs.getString(3);

				rowCount++;
				data = data + fdOrgId + columnSeperator + fdOrgName
						+ columnSeperator + fdOrgDetail + columnSeperator
						+ rowSeperator;
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

	// input=areaId+columnSeperator+fdOrgName+columnSeperator+fdOrgPhoneNumber+columnSeperator+fdOrgAddress
	public int setFdOrg(String inData) {

		String fdOrgName;
		String fdOrgDetail;

		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {

				fdOrgName = columnData[0];
				fdOrgDetail = columnData[1];

			} else {
				status = -2;

				return status;
			}
			query1 = "select * from fourth_dimension.fd_org_master where org_name='"
					+ fdOrgName + "'";
			query2 = "insert into fourth_dimension.fd_org_master values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);

			ps.setString(2, fdOrgName);
			ps.setString(3, fdOrgDetail);

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

	// input=fdOrgId
	public int deleteFdOrg(String inData) {
		Integer fdOrgId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.fd_org_master where fd_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				fdOrgId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, fdOrgId);
				flag1 = flag1 + ps.executeUpdate();

			}

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
		if ((flag1 < rowData.length)) {
			status = -5;

		}

		return status;

	}

	// input=fdOrgId+rowSeperator+areaId+columnSeperator+fdOrgId+columnSeperator+fdOrgName+columnSeperator+fdOrgPhoneNumber+columnSeperator+fdOrgAddress
	public int updateFdOrg(String inData) {
		int fdOrgId;
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
				fdOrgId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;

				return status;

			}
			query1 = "describe fourth_dimension.fd_org_master";

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
				query2 = "update fourth_dimension.fd_org_master set "
						+ columnName[i] + " =?  where fd_id=? ";

				ps = con.prepareStatement(query2);

				ps.setString(1, columnData[i]);
				ps.setInt(2, fdOrgId);
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

	// Hub Administration

	// input=areaId
	// output=hubId+columnSeperator+hubName+columnSeperator+hubPhoneNumber+columnSeperator+hubAddress+columnSeperator+rowSeperator
	public String getFdHub(String inData) {
		int fdOrgId;
		int fdHubId;
		int countryId;
		int regionId;
		//int stateId;
		//int cityId;
		//int townId;
		//int areaId;
		String countryName;
		String regionName;
		//String stateName;
		//String cityName;
		//String townName;
		//String areaName;
		String hubName;
		String address;
		String contactName;
		String contactPhone;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {

			fdOrgId = Integer.parseInt(inData);
			// System.out.println("town id is "+townId);
			/*query1 = "select fd_hub_id,hub_name,address,contact_name,contact_phone,"
					+ "a.country_id,country_name,a.region_id,region_name,a.state_id,"
					+ "state_name,a.city_id,city_name,a.town_id,town_name,a.area_id,"
					+ "area_name from fourth_dimension.fd_hub_master a,country_master c,"
					+ "region_master r,state_master s,city_master ct,town_master t,"
					+ "area_master ar where a.country_id=c.country_id and"
					+ " a.region_id=r.region_id and a.state_id=s.state_id and"
					+ " a.city_id=ct.city_id and a.town_id=t.town_id and"
					+ " a.area_id=ar.area_id and a.fd_id=" + fdOrgId;*/
			query1 = "select fd_hub_id,hub_name,address,contact_name,contact_phone,a.country_id,country_name,a.region_id,region_name  " +
					 "from fourth_dimension.fd_hub_master a,country_master c, region_master r where a.country_id=c.country_id and a.region_id=r.region_id  " +
					 "and a.fd_id= " + fdOrgId+" order by hub_name"; 
			rowCount = 0;
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				fdHubId = rs.getInt(1);
				hubName = rs.getString(2);
				address = rs.getString(3);
				contactName = rs.getString(4);
				contactPhone = rs.getString(5);
				countryId = rs.getInt(6);
				countryName = rs.getString(7);
				regionId = rs.getInt(8);
				regionName = rs.getString(9);
				//stateId = rs.getInt(10);
				//stateName = rs.getString(11);
				//cityId = rs.getInt(12);
				//cityName = rs.getString(13);
				//townId = rs.getInt(14);
				//townName = rs.getString(15);
				//areaId = rs.getInt(10);
				//areaName = rs.getString(11);

				rowCount++;
				/*data = data + fdHubId + columnSeperator + hubName
						+ columnSeperator + address + columnSeperator
						+ contactName + columnSeperator + contactPhone
						+ columnSeperator + countryId + columnSeperator
						+ countryName + columnSeperator + regionId
						+ columnSeperator + regionName + columnSeperator
						+ stateId + columnSeperator + stateName
						+ columnSeperator + cityId + columnSeperator + cityName
						+ columnSeperator + townId + columnSeperator + townName
						+ columnSeperator + areaId + columnSeperator + areaName
						+ columnSeperator + rowSeperator;*/
				data = data + fdHubId + columnSeperator + hubName
				+ columnSeperator + address + columnSeperator
				+ contactName + columnSeperator + contactPhone
				+ columnSeperator + countryId + columnSeperator
				+ countryName + columnSeperator + regionId
				+ columnSeperator + regionName 
				+ columnSeperator + rowSeperator;
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
				e.printStackTrace();
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "Data Unavailable";
		}
		outData = status + statusSeperator + data;
		System.out.println("outData in service class" + outData);
		return outData;
	}

	// input=cityId+columnSeperator+areaName
	public int setFdHub(String inData) {
		int countryId;
		int regionId;
		/*int stateId;
		int cityId;
		int townId;
		int areaId;*/
		int fdOrgId;
		String hubName;
		String address;
		String contactName;
		String contactPhone;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 7) {
				countryId = Integer.parseInt(columnData[0]);
				regionId = Integer.parseInt(columnData[1]);
				/*stateId = Integer.parseInt(columnData[2]);
				cityId = Integer.parseInt(columnData[3]);
				townId = Integer.parseInt(columnData[4]);
				areaId = Integer.parseInt(columnData[5]);*/
				fdOrgId = Integer.parseInt(columnData[2]);
				hubName = columnData[3];
				address = columnData[4];
				contactName = columnData[5];
				contactPhone = columnData[6];

				/*System.out.println("This is service for add area " + countryId
						+ regionId + stateId + cityId + townId + areaId
						+ fdOrgId);*/
				System.out.println("This is service for add FdHub " + countryId
						+ regionId + fdOrgId);

			} else {
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.fd_hub_master where fd_id="
					+ fdOrgId + " and hub_name='" + hubName + "'";
//			query2 = "insert into fourth_dimension.fd_hub_master values(?,?,?,?,?,?,?,?,?,?,?,?)";
			query2 = "insert into fourth_dimension.fd_hub_master values(?,?,?,?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				 return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, hubName);
			ps.setString(3, address);
			ps.setString(4, contactName);
			ps.setString(5, contactPhone);
			/*ps.setInt(6, areaId);
			ps.setInt(7, townId);
			ps.setInt(8, cityId);
			ps.setInt(9, regionId);
			ps.setInt(10, stateId);*/
			ps.setInt(6, regionId);
			ps.setInt(7, countryId);
			ps.setInt(8, fdOrgId);
			System.out.println("Prepared statement is :: " + ps.toString());
			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			status = -4;
			System.out.println("error " + e);

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

	// input=areaId
	public int deleteFdHub(String inData) {
		int fdHubId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			System.out.println("it is service and values are " + inData);

			query1 = "delete from fourth_dimension.fd_hub_master where fd_hub_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {

				fdHubId = Integer.parseInt((rowData[i].trim()));
				// System.out.println("it is service and values are "+areaId);
				ps = con.prepareStatement(query1);
				ps.setInt(1, fdHubId);
				flag1 = flag1 + ps.executeUpdate();

			}

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
		if ((flag1 < rowData.length)) {
			status = -5;

		}

		return status;

	}

	// input=areaId+rowSeperator+cityId+columnSeperator+areaId+columnSeperator+areaName
	public int updateFdHub(String inData) {
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
			query1 = "describe fourth_dimension.fd_hub_master ";

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
				query2 = "update fourth_dimension.fd_hub_master  set "
						+ columnName[i] + " =?  where fd_hub_id=? ";
				System.out.println("it is service area_update and Id is "
						+ columnData[i]);
				ps = con.prepareStatement(query2);
				if ((i == 5) || (i == 6) || (i == 7))
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, hubId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("it is service FdHub_update is " + hubId);

		} catch (Exception e) {
			e.printStackTrace();
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
	public String getFdDivision(String inData) {
		int orgId;
		int divisionId;
		String divisionName;
		String orgName;
		//String hubName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			orgId = Integer.parseInt(inData);
			/*query1 = "select fd_division_id,division_name,org_name from fourth_dimension.fd_division_master a,fd_org_master o,fd_hub_master h where a.fd_id=o.fd_id and a.hub_id=h.fd_hub_id and a.hub_id="
					+ hubId + "";*/
			query1 = "select fd_division_id,division_name,org_name from " +
					"fourth_dimension.fd_division_master a,fd_org_master o where a.fd_id=o.fd_id " +
					"and a.fd_id ="+orgId+" order by division_name";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				divisionId = rs.getInt(1);
				divisionName = rs.getString(2);
				orgName = rs.getString(3);
				//hubName = rs.getString(4);
				rowCount++;
				/*data = data + orgName + columnSeperator + hubName
						+ columnSeperator + divisionId + columnSeperator
						+ divisionName + columnSeperator + rowSeperator;*/
				data = data + orgName + columnSeperator + divisionId + columnSeperator
				+ divisionName + columnSeperator + rowSeperator;
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

	// input=hubId+columnSeperator+divisionName
	public int setFdDivision(String inData) {
		int fdOrgId = 0;
		//int hubId = 0;
		String divisionName = null;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 2) {
				fdOrgId = Integer.parseInt(columnData[0]);
				//hubId = Integer.parseInt(columnData[1]);
				divisionName = columnData[1];

			} else {
				status = -2;
				return status;
			}
			/*query1 = "select * from fourth_dimension.fd_division_master where hub_id="
					+ hubId + " and division_name='" + divisionName + "'";*/
			query1 = "select * from fourth_dimension.fd_division_master where division_name='" + divisionName + "'";
//			query2 = "insert into fourth_dimension.fd_division_master values(?,?,?,?)";
			query2 = "insert into fourth_dimension.fd_division_master values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, divisionName);
			ps.setInt(3, fdOrgId);
			//ps.setInt(4, hubId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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
	public int deleteFdDivision(String inData) {
		int divisionId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			System.out.println("it is service and values are " + inData);

			query1 = "delete from fourth_dimension.fd_division_master where fd_division_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {

				divisionId = Integer.parseInt((rowData[i].trim()));
				// System.out.println("it is service and values are "+areaId);
				ps = con.prepareStatement(query1);
				ps.setInt(1, divisionId);
				flag1 = flag1 + ps.executeUpdate();

			}

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
		if ((flag1 < rowData.length)) {
			status = -5;

		}

		return status;

	}

	// input=divisionId+rowSeperator+hubId+columnSeperator+divisionId+columnSeperator+divisionName
	public int updateFdDivision(String inData) {
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
			query1 = "describe fourth_dimension.fd_division_master";

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
				query2 = "update fourth_dimension.fd_division_master set "
						+ columnName[i] + " =?  where fd_division_id=? ";

				ps = con.prepareStatement(query2);
				if ((i == 2) || (i == 3))
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
	public String getFdDepartment(String inData) {
		System.out.println("in getFdDepartment");
		int divisionId;
		int hubId;
		int departmentId;
		String departmentName;
		String fdOrgName;
		String fdHubName;
		String fdDivisionName;
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
			// hubId = Integer.parseInt(inData);
			query1 = "select fd_department_id,department_name,org_name,hub_name,division_name "
					+ "from fourth_dimension.fd_department_master a,fd_org_master o,fd_hub_master h,fd_division_master d "
					+ "where a.fd_id=o.fd_id and a.hub_id=h.fd_hub_id and a.division_id=d.fd_division_id and a.division_id="
					+ divisionId + " order by department_name";
			/*
			 * query1 =
			 * "select fd_department_id,department_name,org_name,hub_name,division_name from fourth_dimension.fd_department_master "
			 * +
			 * "a,fd_org_master o,fd_hub_master h,fd_division_master d where a.fd_id=o.fd_id "
			 * + "and a.hub_id=h.fd_hub_id and a.hub_id =" + hubId + ";";
			 */
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				departmentId = rs.getInt(1);
				departmentName = rs.getString(2);
				fdOrgName = rs.getString(3);
				fdHubName = rs.getString(4);
				fdDivisionName = rs.getString(5);

				rowCount++;
				data = data + fdOrgName + columnSeperator + fdHubName
						+ columnSeperator + fdDivisionName + columnSeperator
						+ departmentId + columnSeperator + departmentName
						+ columnSeperator + rowSeperator;
				// System.out.println("Data is:\t" + data);
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

	// New method while knocking division filter.
	public String getFdDepartmentDetails(String inData) {
		System.out.println("in getFdDepartmentDetails" + inData);
		int divisionId;
		int hubId;
		int departmentId;
		String departmentName;
		String fdOrgName;
		String fdHubName;
		String fdDivisionName;
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {
			// divisionId = Integer.parseInt(inData);
			hubId = Integer.parseInt(inData);
			/*
			 * query1 =
			 * "select fd_department_id,department_name,org_name,hub_name,division_name "
			 * +
			 * "from fourth_dimension.fd_department_master a,fd_org_master o,fd_hub_master h,fd_division_master d "
			 * +
			 * "where a.fd_id=o.fd_id and a.hub_id=h.fd_hub_id and a.division_id=d.fd_division_id and a.division_id="
			 * + divisionId + "";
			 */
			/*query1 = "select fd_department_id,department_name,org_name,hub_name,division_name from fourth_dimension.fd_department_master "
					+ "a,fd_org_master o,fd_hub_master h,fd_division_master d where a.fd_id=o.fd_id "
					+ "and a.hub_id=h.fd_hub_id and a.division_id = d.fd_division_id and a.hub_id ="
					+ hubId + ";";*/
			query1 = "select fd_department_id,department_name,org_name,hub_name from fourth_dimension.fd_department_master a," +
					"fd_org_master o,fd_hub_master h where a.fd_id=o.fd_id	and a.hub_id=h.fd_hub_id and  a.hub_id ="+hubId;
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				departmentId = rs.getInt(1);
				departmentName = rs.getString(2);
				fdOrgName = rs.getString(3);
				fdHubName = rs.getString(4);
//				fdDivisionName = rs.getString(5);

				rowCount++;
				/*data = data + fdOrgName + columnSeperator + fdHubName
						+ columnSeperator + fdDivisionName + columnSeperator
						+ departmentId + columnSeperator + departmentName
						+ columnSeperator + rowSeperator;*/
				data = data + fdOrgName + columnSeperator + fdHubName
				+ columnSeperator + departmentId + columnSeperator + departmentName
				+ columnSeperator + rowSeperator;
				// System.out.println("Data is:\t" + data);
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

	// input=divisionId+columnSeperator+departmentName
	public int setFdDepartment(String inData) {
		System.out.println("In setFdDepartment");
//		int divisionId;
		int fdOrgId;
		int hubId;
		String departmentName;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 3) {
				fdOrgId = Integer.parseInt(columnData[0]);
				hubId = Integer.parseInt(columnData[1]);
//				divisionId = Integer.parseInt(columnData[2]);
				departmentName = columnData[2];

			} else {
				status = -2;

				return status;
			}
			query1 = "select * from fourth_dimension.fd_department_master where department_name='"
					+ departmentName
					+ "' and fd_id="+fdOrgId+" and hub_id="+hubId;
			query2 = "insert into fourth_dimension.fd_department_master values(?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				 return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, departmentName);
			ps.setInt(3, fdOrgId);
//			ps.setInt(4, divisionId);
			ps.setInt(4, hubId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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
	public int deleteFdDepartment(String inData) {
		int departmentId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			// System.out.println("it is service and values are "+inData);

			query1 = "delete from fourth_dimension.fd_department_master where fd_department_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {

				departmentId = Integer.parseInt((rowData[i].trim()));
				// System.out.println("it is service and values are "+areaId);
				ps = con.prepareStatement(query1);
				ps.setInt(1, departmentId);
				flag1 = flag1 + ps.executeUpdate();

			}

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
		if ((flag1 < rowData.length)) {
			status = -5;

		}

		return status;

	}

	// input=departmentId+rowSeperator+divisionId+columnSeperator+departmentId+columnSeperator+departmentName
	public int updateFdDepartment(String inData) {
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
			query1 = "describe fourth_dimension.fd_department_master";

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
				query2 = "update fourth_dimension.fd_department_master set "
						+ columnName[i] + " =?  where fd_department_id=? ";

				ps = con.prepareStatement(query2);
				if ((i == 2) || (i == 3) || (i == 4))
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, departmentId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			e.printStackTrace();
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
	public String getOperationalGroup(String inData) {
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

	// input=departmentId+columnSeperator+operationalGroupName
	public int setOperationalGroup(String inData) {
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
	public int deleteOperationalGroup(String inData) {
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
	public int updateOperationalGroup(String inData) {
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
	public String getEmployee(String inData) {
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
		/*
		 * query4 =
		 * "select e.id,e.name,e.contact_number,e.e_mail,e.address,r.id,r.name,h.id,h.name "
		 * + "from fourth_dimension.employee e,fourth_dimension.hub h," +
		 * "fourth_dimension.role r,fourth_dimension.employee_hub_mapping ehm,"
		 * + "fourth_dimension.employee_role_mapping erm where " +
		 * "(erm.employee_id=e.id and erm.role_id=r.id) and (ehm.employee_id=e.id and ehm.hub_id=h.id )"
		 * ;
		 */
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
			data = "Exception:" + e;

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
				data = "Exception:" + e;
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
	public int setEmployee(String inData) {
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
	public int deleteEmployee(String inData) {
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
	public int updateEmployeeGeneralInfo(String inData) {
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
	public int updateEmployeeHubInfo(String inData) {
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
	public int updateEmployeeRoleInfo(String inData) {
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

	/* Manage Places */

	// Courier Administration

	// Output=courierId+columnSeperator+courierName+columnSeperator+courierAddress+columnSeperator+courierPinCode+columnSeperator+courier_tel_number+columnSeperator
	// courier_fax_number+columnSeperator+courier_tin_number+columnSeperator+courier_pan_number+columnSeperator+rowSeperator

	public String getCourier(String inData) {

		System.out.println("In getCourier98964164164");
		/*String input[] = inData.split(columnSeperator);
		int cityId = Integer.parseInt(input[0]);
		int stateId = Integer.parseInt(input[1]);
		int regionId = Integer.parseInt(input[2]);
		int countryId = Integer.parseInt(input[3]);
*/
		int courierId;
		String courierName;
		String courierAddress;
		String courierPinCode;
		String courierTelNumber;
		String courierFaxNumber;
		String couriertTinNumber;
		String courierPanNumber;
		status = 0;
		data = "";
		rowCount = 0;
//		query1 = "select * from fourth_dimension.courier_master where city_id =" + cityId + " and state_id =" + stateId + " and region_id =" + regionId + " and country_id =" + countryId + ";";
		query1 = "select * from fourth_dimension.courier_master order by courier_name ";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				courierId = rs.getInt(1);
				courierName = rs.getString(2);
				courierAddress = rs.getString(3);
				courierPinCode = rs.getString(4);
				courierTelNumber = rs.getString(5);
				courierFaxNumber = rs.getString(6);
				couriertTinNumber = rs.getString(7);
				courierPanNumber = rs.getString(8);

				rowCount++;
				data = data + courierId + columnSeperator + courierName
						+ columnSeperator + courierAddress + columnSeperator
						+ courierPinCode + columnSeperator + courierTelNumber
						+ columnSeperator + courierFaxNumber + columnSeperator
						+ couriertTinNumber + columnSeperator
						+ courierPanNumber + columnSeperator + rowSeperator;
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

	// New Courier Data with no filter

	public String getCourier() {

		System.out.println("In getCourier98964164164");

		int courierId;
		String courierName;
		String courierAddress;
		String courierPinCode;
		String courierTelNumber;
		String courierFaxNumber;
		String couriertTinNumber;
		String courierPanNumber;
		int cityId;
		int StateId;
		int RegionId;
		int CountryId;
		String cityName;
		String StateName;
		String RegionName;
		String CountryName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = " select courier_id,courier_name,courier_address,courier_pin_code,courier_tel_number,courier_fax_number,"
				+ " courier_tin_number,courier_pan_number,cour.city_id,cour.state_id,cour.region_id,cour.country_id,city_name,state_name,region_name,country_name "
				+ " from fourth_dimension.courier_master cour,fourth_dimension.city_master city, "
				+ " fourth_dimension.state_master state,fourth_dimension.region_master region, "
				+ " fourth_dimension.country_master country where cour.city_id=city.city_id and "
				+ " cour.state_id=state.state_id and cour.region_id=region.region_id and cour.country_id=country.country_id order by courier_name";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				courierId = rs.getInt(1);
				courierName = rs.getString(2);
				courierAddress = rs.getString(3);
				courierPinCode = rs.getString(4);
				courierTelNumber = rs.getString(5);
				courierFaxNumber = rs.getString(6);
				couriertTinNumber = rs.getString(7);
				courierPanNumber = rs.getString(8);
				cityId = rs.getInt(9);
				StateId = rs.getInt(10);
				RegionId = rs.getInt(11);
				CountryId = rs.getInt(12);
				cityName = rs.getString(13);
				StateName = rs.getString(14);
				RegionName = rs.getString(15);
				CountryName = rs.getString(16);

				rowCount++;
				data = data + courierId + columnSeperator + courierName
						+ columnSeperator + courierAddress + columnSeperator
						+ courierPinCode + columnSeperator + courierTelNumber
						+ columnSeperator + courierFaxNumber + columnSeperator
						+ couriertTinNumber + columnSeperator
						+ courierPanNumber + columnSeperator + cityId
						+ columnSeperator + StateId + columnSeperator
						+ RegionId + columnSeperator + CountryId
						+ columnSeperator + cityName + columnSeperator
						+ StateName + columnSeperator + RegionName
						+ columnSeperator + CountryName + columnSeperator
						+ rowSeperator;
				// System.out.println("output" + data);
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

	/*
	 * Input : Courier Name output : return > 0 if courier created
	 * successfully,else 0 will be returned.
	 */
	public int setCourier(String inData) {
		String input[] = inData.split(columnSeperator);
		String courierName = "";
		String courierAddress = "";
		String courier_pin_code = "";
		String courier_tel_no = "";
		String courier_fax_no = "";
		String courier_tin_no = "";
		String courier_pan_no = "";
		String city_id = "";
		String state_id = "";
		String region_id = "";
		String country_id = "";
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		country_id = input[0];
		region_id = input[1];
		state_id = input[2];
		city_id = input[3];
		courierName = input[4];
		courierAddress = input[5];
		courier_pin_code = input[6];
		courier_tel_no = input[7];
		courier_fax_no = input[8];
		courier_tin_no = input[9];
		courier_pan_no = input[10];

		query1 = "select * from fourth_dimension.courier_master where courier_name='"
				+ courierName + "'";
		query2 = "insert into fourth_dimension.courier_master(courier_name,courier_address,courier_pin_code,courier_tel_number,courier_fax_number,"
				+ "courier_tin_number,courier_pan_number,city_id,state_id,region_id,country_id) values(?,?,?,?,?,?,?,?,?,?,?);";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);

			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setString(1, courierName);
			ps.setString(2, courierAddress);
			ps.setString(3, courier_pin_code);
			ps.setString(4, courier_tel_no);
			ps.setString(5, courier_fax_no);
			ps.setString(6, courier_tin_no);
			ps.setString(7, courier_pan_no);
			ps.setString(8, city_id);
			ps.setString(9, state_id);
			ps.setString(10, region_id);
			ps.setString(11, country_id);

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

	// Input=countryId;

	public int deleteCourier(String inData) {
		Integer courierId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);
			query1 = "delete from fourth_dimension.courier_master where courier_id=?";
			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				courierId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, courierId);
				flag1 = ps.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
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

	/*
	 * Update Courier_Master Table Details
	 * 
	 * Input=courierId+rowSeperator+courierName+columnSeperator+courierAddress+
	 * columnSeperator+pinCode+columnSeperator+ telNumber + columnSeperator +
	 * faxNumber +columnSeperator + tinNumber + columnSeperator + panNumber;
	 */
	public int updateCourier(String inData) {
		// String input[] = inData.split(columnSeperator);
		int courierId;
		// String courierName ;
		// String courierAddress;
		// String pinCode;
		// String telNumber;
		// String faxNumber;
		// String tinNumber;
		// String panNumber;
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
				courierId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);
			} else {
				status = -2;
				return status;
			}
			query1 = "describe fourth_dimension.courier_master ";
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
				query2 = "update fourth_dimension.courier_master  set "
						+ columnName[i] + " =?   where courier_id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, courierId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			status = -4;
			// e.printStackTrace();
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

	/*
	 * Manage PlacesCountry Administration
	 * Output=countryId+columnSeperator+countryName+columnSeperator+rowSeperator
	 */
	public String getAssemblyUnit(String inData) {
		int assemblyUnitId;
		String assemblyUnitName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "select * from fourth_dimension.assembly_unit_master order by assembly_unit_name";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				assemblyUnitId = rs.getInt(1);
				assemblyUnitName = rs.getString(2);

				rowCount++;
				data = data + assemblyUnitId + columnSeperator
						+ assemblyUnitName + columnSeperator + rowSeperator;
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

	public int setAssemblyUnit(String inData) {
		String assemblyUnitName;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		assemblyUnitName = inData;

		query1 = "select * from fourth_dimension.assembly_unit_master where assembly_unit_name='"
				+ assemblyUnitName + "'";
		query2 = "insert into fourth_dimension.assembly_unit_master values(?,?)";

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
			ps.setString(2, assemblyUnitName);

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

	// Input=countryId;

	public int deleteAssemblyUnit(String inData) {
		Integer assemblyUnitId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);
			query1 = "delete from fourth_dimension.assembly_unit_master where assembly_unit_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				assemblyUnitId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, assemblyUnitId);
				flag1 = ps.executeUpdate();

			}

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

	// Input=countryId+rowSeperator+countryId+columnSeperator+countryName;

	public int updateAssemblyUnit(String inData) {
		int assemblyUnitId;
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
				assemblyUnitId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;

			}
			query1 = "describe fourth_dimension.assembly_unit_master";
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
				query2 = "update fourth_dimension.assembly_unit_master set "
						+ columnName[i] + " =?  where assembly_unit_id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, assemblyUnitId);
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

	/*
	 * Manage Places Organisation Administration
	 * Output=organisationId+columnSeperator
	 * +organisationName+columnSeperator+rowSeperator
	 */
	public String getOrganisation(String inData) {
		int roleId;
		String roleName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "SELECT * FROM fourth_dimension.fd_org_master order by org_name;";

		// System.out.println("Before Executing query");
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				roleId = rs.getInt(1);
				roleName = rs.getString(2);

				rowCount++;
				data = data + roleId + columnSeperator + roleName
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

	/*
	 * Manage Places Hub Administration
	 * Output=hubId+columnSeperator+hubName+columnSeperator+rowSeperator
	 */
	public String getHub(String inData) {

		int organisationId;
		int hubId;
		String hubName;
		String organisationName;

		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {

			organisationId = Integer.parseInt(inData);
			System.out.println("it is getstate");
			query1 = "select fd_hub_id,hub_name,org_name from fourth_dimension.fd_hub_master hub,fd_org_master org where hub.fd_id = org.fd_id and hub.fd_id ="
					+ organisationId+" order by hub_name";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				hubId = rs.getInt(1);
				hubName = rs.getString(2);

				organisationName = rs.getString(3);

				rowCount++;
				data = data + organisationName + columnSeperator + hubId
						+ columnSeperator + hubName + columnSeperator
						+ rowSeperator;
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

	/*
	 * Manage Places Division Administration
	 * Output=divisionId+columnSeperator+divisionName
	 * +columnSeperator+rowSeperator
	 */
	public String getDivisoin(String inData) {
		System.out.println("Inside GetDivision");
		String input[] = inData.split(columnSeperator);
		// System.out.println("Org id is--------"+ input[0]);
		// System.out.println("Hub id is--------"+ input[1]);
		int hubId;
		int orgId = 0;
		int divisoinId = 0;
		String divisionName = null;
		String hubName = null;
		System.out.println("InData is" + inData);
		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			System.out.println("InData is null");
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {

			System.out.println("Inside try block");

			hubId = Integer.parseInt(input[0]);
			orgId = Integer.parseInt(input[1]);
			System.out.println("it is getstate");
			query1 = "select fd_division_id,division_name,hub_name from fourth_dimension.fd_hub_master a,fd_division_master c where a.fd_hub_id = c.hub_id and a.fd_hub_id = "
					+ hubId + "and c.fd_id=" + orgId;
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			PreparedStatement pst = con
					.prepareStatement("select fd_division_id,division_name,hub_name from fourth_dimension.fd_hub_master a,fd_division_master c where a.fd_hub_id = c.hub_id and a.fd_hub_id= ? and c.fd_id=?" +
							" order by division_name");
			pst.setInt(1, hubId);
			pst.setInt(2, orgId);
			// rs = st.executeQuery(query1);
			rs = pst.executeQuery();
			while (rs.next()) {

				divisoinId = rs.getInt(1);
				divisionName = rs.getString(2);

				hubName = rs.getString(3);

				rowCount++;
				data = data + columnSeperator + hubName + columnSeperator
						+ divisoinId + columnSeperator + divisionName
						+ columnSeperator + rowSeperator;
				System.out.println("RowCount is " + rowCount);
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
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

	/*
	 * Department Administration input=divisionId
	 * output=departmentId+columnSeperator
	 * +departmentName+columnSeperator+rowSeperator
	 */

	public String getDepartment(String inData) {
		int departmentId;
		int regionId;
		int hubId;
		int orgId;
		String hubName;
		String divisionName;
		String departmentName;
		String input[] = inData.split(columnSeperator);
		System.out.println();
//		System.out.println("Input [0] division id " + input[0]);
		System.out.println("Input [1] hub id is  " + input[0]);
		System.out.println("Input [2]  org id is" + input[1]);

		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {

//			regionId = Integer.parseInt(input[0]);
			hubId = Integer.parseInt(input[0]);
			orgId = Integer.parseInt(input[1]);
			System.out.println("it is getstate");
			// query1 =
			// "select fd_department_id,department_name,hub_name,division_name from fourth_dimension.fd_department_master d,fd_hub_master h,fd_division_master di "
			// +
			// "where d.fd_department_id = h.fd_hub_id  and d.division_id = di.fd_division_id and d.division_id = "+regionId+" and d.hub_id ="+hubId
			// +"and d.fd_id = "+orgId;

			/*query1 = "select fd_department_id,department_name,hub_name,division_name from fourth_dimension.fd_department_master d,fd_hub_master h,fd_division_master di "
					+ "where d.fd_department_id = h.fd_hub_id  and d.division_id = di.fd_division_id and d.division_id = ? and d.hub_id =? and d.fd_id = ?";*/
			
			query1 = "select fd_department_id,department_name,hub_name from fourth_dimension.fd_department_master d,fd_hub_master h " +
					"where d.hub_id = h.fd_hub_id and d.hub_id =? and d.fd_id = ? order by department_name";
			rowCount = 0;

			con = cp.getConnection();
			PreparedStatement pst = con.prepareStatement(query1);
//			pst.setInt(1, regionId);
			pst.setInt(1, hubId);
			pst.setInt(2, orgId);
			// st = con.createStatement();
			// rs = st.executeQuery(query1);
			System.out.println("Pst is ********" + pst);
			rs = pst.executeQuery();
			while (rs.next()) {

				departmentId = rs.getInt(1);
				departmentName = rs.getString(2);

				hubName = rs.getString(3);
//				divisionName = rs.getString(4);

				rowCount++;
				/*data = data + columnSeperator + hubName + columnSeperator
						+ divisionName + columnSeperator + departmentId
						+ columnSeperator + departmentName + columnSeperator
						+ rowSeperator;*/
				data = data + columnSeperator + hubName + columnSeperator + departmentId
				+ columnSeperator + departmentName + columnSeperator
				+ rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
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

	// Employee Administration

	// input=employeeId
	// output=employeeId+columnSeperator+employeeName+columnSeperator+rowSeperator
	public String getManageEmployee(String inData) {

		String input[] = inData.split(columnSeperator);
		System.out.println("input[0]" + input[0]);
		System.out.println("input[1]" + input[1]);
		System.out.println("input[2]" + input[2]);
//		System.out.println("input[3]" + input[3]);
		int employeeId;
		int deptId;
		int divId;
		int hubId;
		String userName;
		String userPassword;
		String designation;
		String contactAddress;
		String contact_phone;
		String employeeName;
		String employeeTypeName;
		String roleName;

		status = 0;
		data = "";

		if (inData == null || inData.equals("")) {
			status = -2;
			data = "input data is unsufficient for operation";
			outData = status + statusSeperator + data;
			return outData;
		}
		try {

			deptId = Integer.parseInt(input[0]);
			//divId = Integer.parseInt(input[1]);
			hubId = Integer.parseInt(input[1]);
			// System.out.println("town id is "+cityId);
			// query1 =
			// "SELECT * FROM fourth_dimension.fd_employee_master where department_id = "+deptId+" and division_id ="+divId+" and hub_id ="
			// +hubId;
			/*query1 = "SELECT employeeid,employee_name,username,password,designation,contact_address,employee_type_name,role_name FROM fourth_dimension.fd_employee_master emp,fd_employee_type_master empType,fd_role_master rol "
					+ "WHERE emp.type_id = empType.employee_type_id and emp.role_id = rol.role_id and emp.department_id ="
					+ deptId
					+ "and emp.division_id ="
					+ divId
					+ "and emp.hub_id =" + hubId;*/
			query1 = "SELECT employeeid,employee_name,username,password,designation,contact_address,employee_type_name,role_name " +
					"FROM fourth_dimension.fd_employee_master emp,fd_employee_type_master empType,fd_role_master rol " +
					"WHERE emp.type_id = empType.employee_type_id and emp.role_id = rol.role_id and emp.department_id ="+deptId +" and emp.hub_id ="+hubId+" order by employee_name";
			rowCount = 0;

			con = cp.getConnection();
			/*PreparedStatement pst = con
					.prepareStatement("SELECT employeeid,employee_name,username,password,designation,contact_address,contact_phone,employee_type_name,role_name FROM fourth_dimension.fd_employee_master emp,fd_employee_type_master empType,fd_role_master rol "
							+ "WHERE emp.type_id = empType.employee_type_id and emp.role_id = rol.role_id and emp.department_id = ? and emp.division_id =? and emp.hub_id =?");*/
			PreparedStatement pst = con.prepareStatement("SELECT employeeid,employee_name,username,password,designation,contact_address,contact_phone,employee_type_name,role_name FROM fourth_dimension.fd_employee_master emp,fd_employee_type_master empType,fd_role_master rol " +
					"WHERE emp.type_id = empType.employee_type_id and emp.role_id = rol.role_id and emp.department_id =? and emp.hub_id =?");
			pst.setInt(1, deptId);
//			pst.setInt(2, divId);
			pst.setInt(2, hubId);
			// st = con.createStatement();
			// rs = st.executeQuery(query1);
			rs = pst.executeQuery();
			while (rs.next()) {

				employeeId = rs.getInt(1);
				employeeName = rs.getString(2);

				userName = rs.getString(3);
				userPassword = rs.getString(4);
				designation = rs.getString(5);
				contactAddress = rs.getString(6);
				contact_phone = rs.getString(7);
				employeeTypeName = rs.getString(8);
				roleName = rs.getString(9);

				rowCount++;
				data = data + employeeId + columnSeperator + employeeName
						+ columnSeperator + userName + columnSeperator
						+ userPassword + columnSeperator + designation
						+ columnSeperator + contactAddress + columnSeperator
						+ contact_phone + columnSeperator + employeeTypeName
						+ columnSeperator + roleName + columnSeperator
						+ rowSeperator;
			}
		} catch (Exception e) {
			status = -4;
			data = "Exception:-" + e;
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
				status = -4;
				data = "Exception:-" + e;
			}

		}
		if (rowCount == 0) {
			status = -1;
			data = "Data Unavailable";
		}
		System.out.println("Status is " + status);
		outData = status + statusSeperator + data;
		return outData;
	}

	// input=cityId+columnSeperator+townName
	public int setManageEmployee(String inData) {
		System.out.println("InSide setManageEmployee**\t" + inData);
		int orgId;
		int hubId;
		int divisionId;
		int departmentId;

		String empName;
		String userName;
		String password;
		String designation;
		String contactAddress;
		String contactPhone;
		String empType;
		String role;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		columnData = inData.split(columnSeperator);
		try {
			if (columnData.length == 11) {
				orgId = Integer.parseInt(columnData[0]);
				hubId = Integer.parseInt(columnData[1]);
//				divisionId = Integer.parseInt(columnData[2]);
				departmentId = Integer.parseInt(columnData[2]);
				empName = columnData[3];
				userName = columnData[4];
				password = columnData[5];
				designation = columnData[6];
				contactAddress = columnData[7];
				contactPhone = columnData[8];
				empType = columnData[9];
				role = columnData[10];

			} else {
				status = -2;
				return status;
			}
			query1 = "select * from fourth_dimension.fd_employee_master where employee_name ='"
					+ empName + "'";
			query2 = "insert into fourth_dimension.fd_employee_master values(?,?,?,?,?,?,?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				 return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setString(2, empName);
			ps.setString(3, userName);
			ps.setString(4, password);
			ps.setString(5, designation);
			ps.setString(6, contactAddress);
			ps.setString(7, contactPhone);
			ps.setString(8, empType);
			ps.setString(9, role);
			// ps.setInt(10, countryId);
			ps.setInt(10, departmentId);
			//ps.setInt(11, divisionId);
			ps.setInt(11, hubId);

			flag1 = ps.executeUpdate();

		} catch (Exception e) {
			status = -4;
			e.printStackTrace();
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
				e.printStackTrace();
			}

		}
		if ((flag1 == 0)) {
			status = -5;

		}

		return status;
	}

	// input=employeeId
	public int deleteManageEmployee(String inData) {
		int employeeId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {
			System.out.println("Inside try block");
			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.fd_employee_master where EmployeeID=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {

				employeeId = Integer.parseInt((rowData[i].trim()));
				System.out.println("Employee Id is" + employeeId);
				// System.out.println("it is service and values are "+townId);
				ps = con.prepareStatement(query1);
				ps.setInt(1, employeeId);
				flag1 = ps.executeUpdate();

			}

		} catch (Exception e) {
			status = -4;

			e.printStackTrace();
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

	// input=townId+rowSeperator+cityId+columnSeperator+townId+columnSeperator+townName
	public int updateManageEmployee(String inData) {
		System.out.println("inside manage employee");
		int townId;
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
				townId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;

			}
			query1 = "describe fourth_dimension.fd_employee_master";

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
				query2 = "update fourth_dimension.fd_employee_master set "
						+ columnName[i] + " =?  where EmployeeID=? ";

				ps = con.prepareStatement(query2);
				if ((i == 7)||(i == 8))
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, townId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			e.printStackTrace();
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

	/*
	 * Manage PlacesEmployeeType Administration
	 * Output=EmployeeTypeId+columnSeperator
	 * +EmployeeTypeName+columnSeperator+rowSeperator
	 */
	public String getEmployeeType(String inData) {
		int countryId;
		String countryName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "select * from fourth_dimension.fd_employee_type_master order by employee_type_name";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				countryId = rs.getInt(1);
				countryName = rs.getString(2);

				rowCount++;
				data = data + countryId + columnSeperator + countryName
						+ columnSeperator + rowSeperator;
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

	// Input=countryName;

	public int setEmployeeType(String inData) {
		String employeeTypeName;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		employeeTypeName = inData;

		query1 = "select * from fourth_dimension.fd_employee_type_master where employee_type_name='"
				+ employeeTypeName + "'";
		query2 = "insert into fourth_dimension.fd_employee_type_master values(?,?)";

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
			ps.setString(2, employeeTypeName);

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

	// Input=countryId;

	public int deleteEmployeeType(String inData) {
		Integer employeeTypeId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.fd_employee_type_master where employee_type_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				employeeTypeId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, employeeTypeId);
				flag1 = ps.executeUpdate();

			}

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

	// Input=countryId+rowSeperator+countryId+columnSeperator+countryName;

	public int updateEmployeeType(String inData) {
		int countryId;
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
				countryId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;

				return status;

			}
			query1 = "describe fourth_dimension.fd_employee_type_master";

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
				query2 = "update fourth_dimension.fd_employee_type_master set "
						+ columnName[i] + " =?  where employee_type_id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, countryId);
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

	/*
	 * Manage PlacesRole Administration
	 * Output=roleId+columnSeperator+roleName+columnSeperator+rowSeperator
	 */
	public String getRole(String inData) {
		int roleId;
		String roleName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "select * from fourth_dimension.fd_role_master order by role_name";

		// System.out.println("Before Executing query");
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				roleId = rs.getInt(1);
				roleName = rs.getString(2);

				rowCount++;
				data = data + roleId + columnSeperator + roleName
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

	// Input=RoleName;

	public int setRole(String inData) {
		String roleName;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		roleName = inData;

		query1 = "select * from fourth_dimension.fd_role_master where role_name='"
				+ roleName + "'";
		query2 = "insert into fourth_dimension.fd_role_master values(?,?)";

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
			ps.setString(2, roleName);

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

	// Input=RoleId;

	public int deleteRole(String inData) {
		Integer roleId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.fd_role_master where role_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				roleId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, roleId);
				flag1 = ps.executeUpdate();

			}

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

	// Input=roleId+rowSeperator+roleId+columnSeperator+roleName;

	public int updateRole(String inData) {
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
				roleId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;

				return status;

			}
			query1 = "describe fourth_dimension.fd_role_master";

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
				query2 = "update fourth_dimension.fd_role_master set "
						+ columnName[i] + " =?  where role_id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, roleId);
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

	/* Manage Places */

	// Country Administration

	// Output=countryId+columnSeperator+countryName+columnSeperator+rowSeperator

	public String getManageElement(String inData) {
		int elementId;
		String elementName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "select * from fourth_dimension.element_master order by element_name";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				elementId = rs.getInt(1);
				elementName = rs.getString(2);

				rowCount++;
				data = data + elementId + columnSeperator + elementName
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

	public int setManageElement(String inData) {
		String elementName;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		elementName = inData;

		query1 = "select * from fourth_dimension.element_master where element_name='"
				+ elementName + "'";
		query2 = "insert into fourth_dimension.element_master values(?,?)";

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

	// Input=countryId+rowSeperator+countryId+columnSeperator+countryName;

	public int updateManageElement(String inData) {
		int countryId;
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
				countryId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;

				return status;

			}
			query1 = "describe fourth_dimension.element_master";

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
				query2 = "update fourth_dimension.element_master set "
						+ columnName[i] + " =?  where element_id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, countryId);
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

	// Input=countryId;

	public int deleteManageElement(String inData) {
		Integer countryId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.element_master where element_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				countryId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, countryId);
				flag1 = ps.executeUpdate();

			}

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

	public String getManageElementStatus(String inData) {
		int elementId;
		String elementName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "select * from fourth_dimension.element_status_master order by element_status_name";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				elementId = rs.getInt(1);
				elementName = rs.getString(2);

				rowCount++;
				data = data + elementId + columnSeperator + elementName
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

	public int setManageElementStatus(String inData) {
		String elementName;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		elementName = inData;

		query1 = "select * from fourth_dimension.element_status_master where element_status_name='"
				+ elementName + "'";
		query2 = "insert into fourth_dimension.element_status_master values(?,?)";

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

	// Input=countryId+rowSeperator+countryId+columnSeperator+countryName;

	public int updateManageElementStatus(String inData) {
		int countryId;
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
				countryId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;

				return status;

			}
			query1 = "describe fourth_dimension.element_status_master";

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
				query2 = "update fourth_dimension.element_status_master set "
						+ columnName[i] + " =?  where element_status_id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, countryId);
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

	// Input=countryId;

	public int deleteManageElementStatus(String inData) {
		Integer countryId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.element_status_master where element_status_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				countryId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, countryId);
				flag1 = ps.executeUpdate();

			}

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

	/*
	 * Component
	 * 
	 * Input=elementId
	 * Output=componentId+columnSeperator+componentName+columnSeperator
	 * +columnSeparator+element_name+rowSeperator
	 */

	public String getManageComponent() {		
		int componentId;		
		String componentName;

		status = 0;
		data = "";
		
		try {		
			System.out.println("it is getManageComponent");
			query1 = "SELECT component_id,component_name FROM fourth_dimension.component_master;";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			
			while (rs.next()) {
				componentId = rs.getInt(1);
				componentName = rs.getString(2);
				rowCount++;
				data = data + componentId + columnSeperator 
							+ componentName + rowSeperator;
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

	public int setManageComponent(String inData) {
	//	String elementId;
		String compName;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		String input[] = inData.split(columnSeperator);
		//elementId = input[0];
		compName = input[0];

		query1 = "SELECT * FROM fourth_dimension.component_master where component_name = '"+ compName + "'";
		query2 = "insert into fourth_dimension.component_master values(?,?)";

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
		//	ps.setString(2, elementId);
			ps.setString(2, compName);

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

	// Input=RoleId;

	public int deleteManageComponent(String inData) {
		Integer roleId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "DELETE FROM fourth_dimension.component_master where component_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				roleId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, roleId);
				flag1 = ps.executeUpdate();

			}

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

	// input=componentId+rowSeperator+elementId+columnSeperator+componentName
	public int updateManageComponent(String inData) {
		System.out.println("Inside updateManageComponent");
		int componentId;
		status = 0;
		flag1 = 0;

		String columnNameTemp = "";
		String componentName="";
		String columnName[];
		int flagCheck = 0;
		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}
		try {
			
			
			rowData = inData.split(rowSeperator);
			
			if (rowData.length == 1) {
				columnData = rowData[0].split(columnSeperator);
				componentId = Integer.parseInt(columnData[0]);
				componentName = columnData[1];
				

			} else {
				status = -2;
				return status;

			}
			query1 = "describe fourth_dimension.component_master";
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
				query2 = "update fourth_dimension.component_master set "
						+ columnName[i] + " =?  where component_id=? ";

				ps = con.prepareStatement(query2);
				if (i == 1)
					ps.setString(1, columnData[i]);
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, componentId);
				flag1 = ps.executeUpdate() + flag1;

			}
			System.out.println("flag is " + flag1);

		} catch (Exception e) {
			e.printStackTrace();
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

	/*
	 * Manage Project AspectsCountry Administration
	 * Output=countryId+columnSeperator+countryName+columnSeperator+rowSeperator
	 */
	public String getDeptForProjectStatus(String inData) {
		int scopeId;
		String scopeName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "SELECT * FROM fourth_dimension.fd_department_master order by department_name";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				scopeId = rs.getInt(1);
				scopeName = rs.getString(2);

				rowCount++;
				data = data + scopeId + columnSeperator + scopeName
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
		System.out.println("OutData is\t" + outData);
		return outData;
	}

	// Region Administration

	// Input=countryId
	// Output=regionId+columnSeperator+regionName+columnSeperator+rowSeperator
	public String getProjectScope(String inData) {
		int countryId;
		int regionId;
		String countryName;
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
			System.out.println("it is getstate");
			query1 = "select scope_id,scope_name,department_name from fourth_dimension.scope_master a,fd_department_master c where a.fd_department_id = c.fd_department_id and a.fd_department_id ="
					+ countryId+" order by scope_name";
			rowCount = 0;

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				regionId = rs.getInt(1);
				regionName = rs.getString(2);

				countryName = rs.getString(3);

				rowCount++;
				// data = data + countryName + columnSeperator +
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
			data = "Data Unavailable";
		}
		outData = status + statusSeperator + data;
		return outData;
	}

	// Input=scopeId+columnSeperator+scopeName+columnSeperator+departmentId
	public int setProjectScope(String inData) {
		int departmentId;
		String scopeName;
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
				scopeName = columnData[1];

			} else {
				status = -2;
				return status;
			}
			query1 = "SELECT * FROM fourth_dimension.scope_master where scope_name ='"
					+ scopeName + "'";
			query2 = "insert into fourth_dimension.scope_master values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			if (rs.next()) {
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query2);
			ps.setInt(1, 0);
			ps.setInt(2, departmentId);
			ps.setString(3, scopeName);

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
	public int deleteProjectScope(String inData) {
		int scopeId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.scope_master where scope_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				scopeId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, scopeId);
				flag1 = ps.executeUpdate();

			}

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

	// input=regionId+rowSeperator+countryId+columnSeperator+regionId+columnSeperator+regionName
	public int updateProjectScope(String inData) {
		// Modify the Method to check the scope name against Database before
		// updating
		int scopeId;
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
				scopeId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;
				return status;
			}
			query1 = "describe fourth_dimension.scope_master";

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
				query2 = "update fourth_dimension.scope_master set "
						+ columnName[i] + " =?  where scope_id=? ";

				ps = con.prepareStatement(query2);
				if (i == 1)
					ps.setInt(1, Integer.parseInt(columnData[i]));
				else
					ps.setString(1, columnData[i]);
				ps.setInt(2, scopeId);
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

	/*
	 * Manage PlacesProject Status Administration
	 * Output=roleId+columnSeperator+roleName+columnSeperator+rowSeperator
	 */
	public String getProjectStatus(String inData) {
		System.out.println("Inside Project Status ***********");
		int roleId;
		String roleName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "SELECT * FROM fourth_dimension.Project_status_master order by Project_status_name";

		System.out.println("Before Executing query");
		try {
			System.out.println("Inside try block");
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				roleId = rs.getInt(1);
				roleName = rs.getString(2);

				rowCount++;
				data = data + roleId + columnSeperator + roleName
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
		System.out.println("outData is**" + outData);
		return outData;

	}

	// Input=RoleId;

	public int deleteProjectStatus(String inData) {
		int roleId;
		status = 0;

		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {

			rowData = inData.split(rowSeperator);

			query1 = "delete from fourth_dimension.Project_status_master where Project_status_id=?";

			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				roleId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, roleId);
				flag1 = ps.executeUpdate();

			}

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

	// Input=RoleName;

	public int setProjectStatus(String inData) {
		String roleName;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		roleName = inData;

		query1 = "select * from fourth_dimension.Project_status_master where Project_status_name='"
				+ roleName + "'";
		query2 = "insert into fourth_dimension.Project_status_master values(?,?)";

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
			ps.setString(2, roleName);

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

	// Input=roleId+rowSeperator+roleId+columnSeperator+roleName;

	public int updateProjectStatus(String inData) {
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
				roleId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;

				return status;

			}
			query1 = "describe fourth_dimension.Project_status_master";

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
				query2 = "update fourth_dimension.Project_status_master set "
						+ columnName[i] + " =?  where Project_status_id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, roleId);
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

	/* Manage Places */

	// Country Administration

	// Output=countryId+columnSeperator+countryName+columnSeperator+rowSeperator

	public String getUnit(String inData) {
		int countryId;
		String countryName;
		status = 0;
		data = "";
		rowCount = 0;
		query1 = "SELECT * FROM fourth_dimension.unit_master order by unit_name";

		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				countryId = rs.getInt(1);
				countryName = rs.getString(2);

				rowCount++;
				data = data + countryId + columnSeperator + countryName
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

	public int setUnit(String inData) {
		String unitName;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;
			return status;
		}

		unitName = inData;

		query1 = "select * from fourth_dimension.unit_master where unit_name='"
				+ unitName + "'";
		query2 = "insert into fourth_dimension.unit_master values(?,?)";

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
			ps.setString(2, unitName);

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

	// Input=countryId+rowSeperator+countryId+columnSeperator+countryName;

	public int updateUnit(String inData) {
		int unitId;
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
				unitId = Integer.parseInt(rowData[0]);
				columnData = rowData[1].split(columnSeperator);

			} else {
				status = -2;

				return status;

			}
			query1 = "describe fourth_dimension.unit_master";
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
				query2 = "update fourth_dimension.unit_master set "
						+ columnName[i] + " =?  where id=? ";
				System.out.println(query2);
				ps = con.prepareStatement(query2);
				ps.setString(1, columnData[i]);
				ps.setInt(2, unitId);
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

	// Input=countryId;

	public int deleteUnit(String inData) {
		Integer unitId;
		status = 0;
		flag1 = 0;

		if ((inData == null) || inData.equals("")) {
			status = -2;

			return status;
		}

		try {
			rowData = inData.split(rowSeperator);
			query1 = "delete from fourth_dimension.unit_master where id=?";
			con = cp.getConnection();

			for (int i = 0; i < rowData.length; i++) {
				// System.out.println("material Group ID String "+matGroupId);
				unitId = Integer.parseInt((rowData[i].trim()));

				ps = con.prepareStatement(query1);
				ps.setInt(1, unitId);
				flag1 = ps.executeUpdate();

			}

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

	public List<HashMap<String, Object>> getBrandCategory() {
		int brandCategoryId;
		String brandCategoryName;
		List<HashMap<String, Object>> bcList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select * from fourth_dimension.brand_category_master order by brand_category_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				brandCategoryId = rs.getInt(1);
				brandCategoryName = rs.getString(2);
				HashMap<String, Object> bcHashMap = new HashMap<String, Object>();
				bcHashMap.put("brandCategoryId", brandCategoryId);
				bcHashMap.put("brandCategoryName", brandCategoryName);
				bcList.add(bcHashMap);
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
		return bcList;
	}

	public int setBrandCategory(BrandCategory bc) {
		int status = -1;
		int flag = 0;
		int brandCategoryId = 0;
		String brandCategoryName = bc.getBrandCategoryName();
		String strCheckForExistance ="SELECT * FROM fourth_dimension.brand_category_master WHERE brand_category_name=" + "'" + bc.getBrandCategoryName() + "'" +";";
		String query1 = "insert into fourth_dimension.brand_category_master values(?,?)";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(strCheckForExistance);
			if(rs.next()){
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query1);
			ps.setInt(1, brandCategoryId);
			ps.setString(2, brandCategoryName);
			flag = ps.executeUpdate();
			if (flag > 0)
				status = 0;
		} catch (Exception e) {
			e.printStackTrace();
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

	public int deleteBrandCategoryByBrandCategoryIds(int brandCategoryIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < brandCategoryIds.length; i++) {

				String query1 = "delete from fourth_dimension.brand_category_master where brand_category_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, brandCategoryIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == brandCategoryIds.length)
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

	public int updateBrandCategory(BrandCategory bc) {
		int status = -1;
		int flag = 0;
		int brandCategoryId = bc.getBrandCategoryId();
		String brandCategoryName = bc.getBrandCategoryName();

		String query1 = "update fourth_dimension.brand_category_master set brand_category_name=? where  brand_category_id=?";
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setString(1, brandCategoryName);
			ps.setInt(2, brandCategoryId);

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

	// Manage Client

	public String getClientData() {
		int clientId;
		String ClientName;
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
				ClientName = rs.getString(2);

				rowCount++;
				data = data + clientId + columnSeperator + ClientName
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

	public List<HashMap<String, Object>> getClient() {
		int clientId;
		String clientName;
		String localCurrency;
		String baseCurrency;
		String tinNumber;
		String cstNumber;

		int countryId;
		String countryName;
		int stateId;
		String stateName;
		int cityId;
		String cityName;
		String pinCode;
		String address;
		List<HashMap<String, Object>> cltList = new ArrayList<HashMap<String, Object>>();

		String query1 = "SELECT client_id,client_name,local_currency,base_currency,TIN_number,"
				+ "CST_number,clt.country_id,country_name,clt.state_id,state_name,clt.city_id,"
				+ "city_name,pin_code,address FROM fourth_dimension.client_master clt,"
				+ "country_master ctry,state_master st,city_master cty "
				+ "where clt.country_id=ctry.country_id and clt.state_id=st.state_id"
				+ " and clt.city_id=cty.city_id order by client_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				clientId = rs.getInt(1);
				clientName = rs.getString(2);
				localCurrency = rs.getString(3);
				baseCurrency = rs.getString(4);
				tinNumber = rs.getString(5);
				cstNumber = rs.getString(6);
				countryId = rs.getInt(7);
				countryName = rs.getString(8);
				stateId = rs.getInt(9);
				stateName = rs.getString(10);
				cityId = rs.getInt(11);
				cityName = rs.getString(12);
				pinCode = rs.getString(13);
				address = rs.getString(14);
				HashMap<String, Object> cltHashMap = new HashMap<String, Object>();
				cltHashMap.put("clientId", clientId);
				cltHashMap.put("clientName", clientName);
				cltHashMap.put("localCurrency", localCurrency);
				cltHashMap.put("baseCurrency", baseCurrency);
				cltHashMap.put("tinNumber", tinNumber);
				cltHashMap.put("cstNumber", cstNumber);
				cltHashMap.put("countryId", countryId);
				cltHashMap.put("countryName", countryName);
				cltHashMap.put("stateId", stateId);
				cltHashMap.put("stateName", stateName);
				cltHashMap.put("cityId", cityId);
				cltHashMap.put("cityName", cityName);
				cltHashMap.put("pinCode", pinCode);
				cltHashMap.put("address", address);
				cltList.add(cltHashMap);
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
		return cltList;
	}

	public int setClient(Client clt) {
		int status = -1;
		int flag = 0;
		int clientId = 0;
		try {

			String clientName = clt.getClientName();
			String localCurrency = clt.getLocalCurrency();
			String baseCurrency = clt.getBaseCurrency();
			String tinNumber = clt.getTinNumber();
			String cstNumber = clt.getCstNumber();
			String pinCode = clt.getPinCode();
			String address = clt.getAddress();

			int countryId = clt.getCountryId();
			int stateId = clt.getStateId();
			int cityId = clt.getCityId();
			String strQueryCheckForExistance = "SELECT * FROM fourth_dimension.client_master where client_name=" + "'" + clientName + "'";
			String query1 = "insert into fourth_dimension.client_master values(?,?,?,?,?,?,?,?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(strQueryCheckForExistance);
			if(rs.next()){
				status = -3;
				return status;
			}
			
			ps = con.prepareStatement(query1);
			ps.setInt(1, clientId);
			ps.setString(2, clientName);
			ps.setString(3, address);
			ps.setInt(4, cityId);
			ps.setInt(5, stateId);
			ps.setString(6, pinCode);
			ps.setInt(7, countryId);
			ps.setString(8, localCurrency);
			ps.setString(9, baseCurrency);
			ps.setString(10, tinNumber);
			ps.setString(11, cstNumber);
			ps.setString(12, null);
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

	public int deleteClientByClientIds(int clientIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < clientIds.length; i++) {

				String query1 = "delete from fourth_dimension.client_master where client_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, clientIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == clientIds.length)
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

	public int updateClient(Client clt) {
		int status = -1;
		int flag = 0;
		try {
			int clientId = clt.getClientId();
			String clientName = clt.getClientName();
			String localCurrency = clt.getLocalCurrency();
			String baseCurrency = clt.getBaseCurrency();
			String tinNumber = clt.getTinNumber();
			String cstNumber = clt.getCstNumber();
			String pinCode = clt.getPinCode();
			String address = clt.getAddress();

			int countryId = clt.getCountryId();
			int stateId = clt.getStateId();
			int cityId = clt.getCityId();

			String query1 = "update fourth_dimension.client_master set "
					+ "client_name=? ,address=? ,city_id=? ,state_id=? ,pin_code=? ,"
					+ "country_id=? ,local_currency=? ,base_currency=? ,tin_number=? ,"
					+ "cst_number=? ,logo=? where client_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);

			ps.setString(1, clientName);
			ps.setString(2, address);
			ps.setInt(3, cityId);
			ps.setInt(4, stateId);
			ps.setString(5, pinCode);
			ps.setInt(6, countryId);
			ps.setString(7, localCurrency);
			ps.setString(8, baseCurrency);
			ps.setString(9, tinNumber);
			ps.setString(10, cstNumber);
			ps.setString(11, null);
			ps.setInt(12, clientId);
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

	// Manage Brand

	public List<HashMap<String, Object>> getBrandByClientId(int clientId) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
		try {

			String query1 = "SELECT brnd.client_id,client_name,brnd.category_id,brand_category_name,brand_id,"
					+ "brand_name,notes FROM fourth_dimension.brand_master brnd,"
					+ "client_master clnt,brand_category_master brndctg "
					+ "where brnd.client_id=clnt.client_id and brnd.category_id=brndctg.brand_category_id"
					+ " and brnd.client_id=" + clientId+" order by brand_name";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("clientId", rs.getInt(1));
				outDataHashMap.put("clientName", rs.getString(2));
				outDataHashMap.put("brandCategoryId", rs.getInt(3));
				outDataHashMap.put("brandCategoryName", rs.getString(4));
				outDataHashMap.put("brandId", rs.getInt(5));
				outDataHashMap.put("brandName", rs.getString(6));
				outDataHashMap.put("notes", rs.getString(7));

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

	public int setBrand(Brand brnd) {
		int status = -1;
		int flag = 0;
		int brandId = 0;
		Statement pstForExistance = null;
		ResultSet rsForExistance = null;
		try {
			int clientId = brnd.getClientId();
			int brandCategoryId = brnd.getBrandCategoryId();
			String brandName = brnd.getBrandName();
			String notes = brnd.getNotes();
			
			String strQuery = "SELECT * FROM fourth_dimension.brand_master WHERE brand_name="+"'"+brandName+"'"+" and category_id="+"'"+brandCategoryId+"'";
			System.out.println("Statement query is " + strQuery);
			con = cp.getConnection();
			pstForExistance = con.createStatement();
			rsForExistance = pstForExistance.executeQuery(strQuery);
			if(rsForExistance.next()){
				status = -3;
				return status;
			}
			String query1 = "insert into fourth_dimension.brand_master values(?,?,?,?,?)";

//			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, brandId);
			ps.setString(2, brandName);
			ps.setString(3, notes);
			ps.setInt(4, brandCategoryId);
			ps.setInt(5, clientId);

			flag = ps.executeUpdate();

			if (flag > 0)
				status = 0;
		} catch (Exception e) {
			e.printStackTrace();
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

	public int deleteBrandByBrandIds(int brandIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < brandIds.length; i++) {

				String query1 = "delete from fourth_dimension.brand_master where brand_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, brandIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == brandIds.length)
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

	public int updateBrand(Brand brnd) {
		int status = -1;
		int flag = 0;
		try {
			int clientId = brnd.getClientId();
			int brandCategoryId = brnd.getBrandCategoryId();
			int brandId = brnd.getBrandId();
			String brandName = brnd.getBrandName();
			String notes = brnd.getNotes();

			String query1 = "update fourth_dimension.brand_master set brand_name=?,"
					+ "notes=?,category_id=?,client_id=? where brand_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setString(1, brandName);
			ps.setString(2, notes);
			ps.setInt(3, brandCategoryId);
			ps.setInt(4, clientId);
			ps.setInt(5, brandId);

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

	// Manage Depot
	public List<HashMap<String, Object>> getDepotByAreaId(int areaId) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
		try {

			String query1 = "SELECT dpt.country_id,country_name,dpt.region_id,region_name,"
					+ "dpt.state_id,state_name,dpt.city_id,city_name,dpt.town_id,town_name,dpt.area_id,area_name,"
					+ " depot_id,depot_name,contact_name,contact_phone,landmark_details,notes,address "
					+ " FROM fourth_dimension.depot_master dpt,country_master cntry,region_master rgn,"
					+ " state_master st,city_master cty,town_master twn,area_master ara "
					+ " where dpt.country_id=cntry.country_id and dpt.region_id=rgn.region_id and"
					+ " dpt.state_id=st.state_id and dpt.city_id=cty.city_id and dpt.town_id=twn.town_id and "
					+ "dpt.area_id=ara.area_id and dpt.area_id=" + areaId+" order by depot_name";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("countryId", rs.getInt(1));
				outDataHashMap.put("countryName", rs.getString(2));
				outDataHashMap.put("regionId", rs.getInt(3));
				outDataHashMap.put("regionName", rs.getString(4));
				outDataHashMap.put("stateId", rs.getInt(5));
				outDataHashMap.put("stateName", rs.getString(6));
				outDataHashMap.put("cityId", rs.getInt(7));
				outDataHashMap.put("cityName", rs.getString(8));
				outDataHashMap.put("townId", rs.getInt(9));
				outDataHashMap.put("townName", rs.getString(10));
				outDataHashMap.put("areaId", rs.getInt(11));
				outDataHashMap.put("areaName", rs.getString(12));
				outDataHashMap.put("depotId", rs.getInt(13));
				outDataHashMap.put("depotName", rs.getString(14));
				outDataHashMap.put("contactName", rs.getString(15));
				outDataHashMap.put("contactPhone", rs.getString(16));
				outDataHashMap.put("landMarkDetails", rs.getString(17));
				outDataHashMap.put("notes", rs.getString(18));
				outDataHashMap.put("address", rs.getString(19));

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

	// Manage Depot
	public List<HashMap<String, Object>> getDepotByClientId(int clientId) {
		System.out.println("clientID   :: " + clientId);
		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
		try {

			String query1 = "SELECT dpt.country_id,country_name,dpt.region_id,region_name,"
					+ "dpt.state_id,state_name,dpt.city_id,city_name,dpt.town_id,town_name,dpt.area_id,area_name,"
					+ " depot_id,depot_name,contact_name,contact_phone,landmark_details,notes,address "
					+ " FROM fourth_dimension.depot_master dpt,country_master cntry,region_master rgn,"
					+ " state_master st,city_master cty,town_master twn,area_master ara "
					+ " where dpt.country_id=cntry.country_id and dpt.region_id=rgn.region_id and"
					+ " dpt.state_id=st.state_id and dpt.city_id=cty.city_id and dpt.town_id=twn.town_id and "
					+ "dpt.area_id=ara.area_id and dpt.client_id=" + clientId+" order by depot_name";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("countryId", rs.getInt(1));
				outDataHashMap.put("countryName", rs.getString(2));
				outDataHashMap.put("regionId", rs.getInt(3));
				outDataHashMap.put("regionName", rs.getString(4));
				outDataHashMap.put("stateId", rs.getInt(5));
				outDataHashMap.put("stateName", rs.getString(6));
				outDataHashMap.put("cityId", rs.getInt(7));
				outDataHashMap.put("cityName", rs.getString(8));
				outDataHashMap.put("townId", rs.getInt(9));
				outDataHashMap.put("townName", rs.getString(10));
				outDataHashMap.put("areaId", rs.getInt(11));
				outDataHashMap.put("areaName", rs.getString(12));
				outDataHashMap.put("depotId", rs.getInt(13));
				outDataHashMap.put("depotName", rs.getString(14));
				outDataHashMap.put("contactName", rs.getString(15));
				outDataHashMap.put("contactPhone", rs.getString(16));
				outDataHashMap.put("landMarkDetails", rs.getString(17));
				outDataHashMap.put("notes", rs.getString(18));
				outDataHashMap.put("address", rs.getString(19));

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

	public int setDepot(Depot dpt) {
		int status = -1;
		int flag = 0;

		try {

			String query1 = "insert into fourth_dimension.depot_master values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, 0);
			ps.setString(2, dpt.getDepotName());
			ps.setInt(3, dpt.getAreaId());
			ps.setInt(4, dpt.getTownId());
			ps.setInt(5, dpt.getCityId());
			ps.setInt(6, dpt.getRegionId());
			ps.setInt(7, dpt.getStateId());
			ps.setInt(8, dpt.getCountryId());
			ps.setString(9, dpt.getAddress());
			ps.setString(10, dpt.getContactName());
			ps.setString(11, dpt.getContactPhone());
			ps.setString(12, dpt.getLandMarkDetails());
			ps.setString(13, dpt.getNotes());
			ps.setInt(14, dpt.getClientId());

			System.out.println("Depot ID:\t" + dpt.getClientId());
			System.out.println("pst string is :\t" + ps.toString() );
			flag = ps.executeUpdate();

			if (flag > 0)
				status = 0;
		} catch (Exception e) {
			e.printStackTrace();
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

	public int deleteDepotByDepotIds(int depotIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < depotIds.length; i++) {

				String query1 = "delete from fourth_dimension.depot_master where depot_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, depotIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == depotIds.length)
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

	public int updateDepot(Depot dpt) {
		int status = -1;
		int flag = 0;
		try {

			String query1 = "update fourth_dimension.depot_master set depot_name=?,"
					+ " area_id=?,town_id=?,city_id=?,region_id=?,state_id=?,country_id=?,"
					+ " address=?,contact_name=?,contact_phone=?,landmark_details=?,"
					+ " notes=?,client_id=? where depot_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);

			ps.setString(1, dpt.getDepotName());
			ps.setInt(2, dpt.getAreaId());
			ps.setInt(3, dpt.getTownId());
			ps.setInt(4, dpt.getCityId());
			ps.setInt(5, dpt.getRegionId());
			ps.setInt(6, dpt.getStateId());
			ps.setInt(7, dpt.getCountryId());
			ps.setString(8, dpt.getAddress());
			ps.setString(9, dpt.getContactName());
			ps.setString(10, dpt.getContactPhone());
			ps.setString(11, dpt.getLandMarkDetails());
			ps.setString(12, dpt.getNotes());
			ps.setInt(13, dpt.getClientId());
			ps.setInt(14, dpt.getDepotId());

			flag = ps.executeUpdate();
			if (flag > 0)
				status = 0;
		} catch (Exception e) {
			e.printStackTrace();
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

	public List<HashMap<String, Object>> getStoreByAreaName(String areaName) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
		try {

			String query1 = "SELECT country_name,region_name,"
					+ " state_name,city_name,town_name,area_name,chain_name,store_id,"
					+ " store_name,contact_name,contact_phone,landmark_details,notes,address,store_flag "
					+ " FROM fourth_dimension.store_master where area_name='"
					+ areaName + "' order by store_name";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("countryName", rs.getString(1));
				outDataHashMap.put("regionName", rs.getString(2));
				outDataHashMap.put("stateName", rs.getString(3));
				outDataHashMap.put("cityName", rs.getString(4));
				outDataHashMap.put("townName", rs.getString(5));
				outDataHashMap.put("areaName", rs.getString(6));
				outDataHashMap.put("chainName", rs.getString(7));
				outDataHashMap.put("storeId", rs.getInt(8));
				outDataHashMap.put("storeName", rs.getString(9));
				outDataHashMap.put("contactName", rs.getString(10));
				outDataHashMap.put("contactPhone", rs.getString(11));
				outDataHashMap.put("landMarkDetails", rs.getString(12));
				outDataHashMap.put("notes", rs.getString(13));
				outDataHashMap.put("address", rs.getString(14));
				outDataHashMap.put("storeFlag", rs.getString(15));

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

	public int setStore(Store str) {
		int status = -1;
		int flag = 0;

		try {
			String strCheckForExistance = "SELECT * FROM fourth_dimension.store_master WHERE store_name=" + "'" + str.getStoreName() +"'"
			+ " and chain_name=" + "'" + str.getChainName() + "'" + ";";
			String query1 = "insert into fourth_dimension.store_master values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(strCheckForExistance);
			if(rs.next()){
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query1);
			ps.setInt(1, 0);
			ps.setString(2, str.getStoreName());
			ps.setString(3, str.getChainName());
			ps.setString(4, str.getAreaName());
			ps.setString(5, str.getTownName());
			ps.setString(6, str.getCityName());
			ps.setString(7, str.getRegionName());
			ps.setString(8, str.getStateName());
			ps.setString(9, str.getCountryName());
			ps.setString(10, str.getAddress());
			ps.setString(11, str.getContactName());
			ps.setString(12, str.getContactPhone());
			ps.setString(13, str.getLandMarkDetails());
			ps.setString(14, str.getNotes());
			ps.setString(15, str.getStoreFlag());

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

	public int deleteStoreByStoreIds(int storeIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < storeIds.length; i++) {

				String query1 = "delete from fourth_dimension.store_master where store_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, storeIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == storeIds.length)
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

	public int updateStore(Store str) {
		int status = -1;
		int flag = 0;
		try {

			String query1 = "update fourth_dimension.store_master set store_name=?,chain_name=?,"
					+ "area_name=?,town_name=?,city_name=?,region_name=?,state_name=?,country_name=?,"
					+ "address=?,contact_name=?,contact_phone=?,landmark_details=?,"
					+ "notes=?,store_flag=? where store_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);

			ps.setString(1, str.getStoreName());
			ps.setString(2, str.getChainName());
			ps.setString(3, str.getAreaName());
			ps.setString(4, str.getTownName());
			ps.setString(5, str.getCityName());
			ps.setString(6, str.getRegionName());
			ps.setString(7, str.getStateName());
			ps.setString(8, str.getCountryName());
			ps.setString(9, str.getAddress());
			ps.setString(10, str.getContactName());
			ps.setString(11, str.getContactPhone());
			ps.setString(12, str.getLandMarkDetails());
			ps.setString(13, str.getNotes());
			ps.setString(14, str.getStoreFlag());
			ps.setInt(15, str.getStoreId());

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

	public List<HashMap<String, Object>> getChain() {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
		try {

			String query1 = "select cm.trade_id,trade_name,cm.retail_client_id,"
					+ "retail_client_name,chain_id,chain_name from "
					+ "fourth_dimension.chain_master cm,trade_master tm,retail_client_master rcm "
					+ "where cm.trade_id=tm.trade_id and cm.retail_client_id=rcm.retail_client_id order by chain_name";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("tradeId", rs.getInt(1));
				outDataHashMap.put("tradeName", rs.getString(2));
				outDataHashMap.put("retailClientId", rs.getInt(3));
				outDataHashMap.put("retailClientName", rs.getString(4));
				outDataHashMap.put("chainId", rs.getInt(5));
				outDataHashMap.put("chainName", rs.getString(6));

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

	// Manage Chain

	public List<HashMap<String, Object>> getChainByTradeId(int tradeId) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
		try {

			/*String query1 = "select cm.trade_id,trade_name, "
					+ " chain_id,chain_name from "
					+ "fourth_dimension.chain_master cm,trade_master tm "
					+ "where cm.trade_id=tm.trade_id and cm.trade_id="
					+ tradeId;*/
			String query1 = "select tm.trade_id,chain_id,chain_name,rtl.retail_client_id,rtl.retail_client_name " +
					"from  chain_master cm,trade_master tm,retail_client_master rtl where cm.trade_id=tm.trade_id " +
					"and rtl.retail_client_id = cm.retail_client_id and cm.trade_id ="+tradeId+" order by chain_name";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				/*outDataHashMap.put("tradeId", rs.getInt(1));
				outDataHashMap.put("tradeName", rs.getString(2));

				outDataHashMap.put("chainId", rs.getInt(3));
				outDataHashMap.put("chainName", rs.getString(4));*/
				outDataHashMap.put("tradeId", rs.getInt(1));
				outDataHashMap.put("chainId",rs.getInt(2));
				outDataHashMap.put("chainName",rs.getString(3));
				outDataHashMap.put("retailClientId",rs.getInt(4));
				outDataHashMap.put("retailClientName",rs.getString(5));

				outDataList.add(outDataHashMap);
			}
		} catch (Exception e) {
//			System.out.println(e);
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

	public int setChain(Chain chn) {
		int status = -1;
		int flag = 0;
		int chainId = 0;
		try {
			int tradeId = chn.getTradeId();
			int retailClientId = chn.getRetailClientId();
			String chainName = chn.getChainName();
			String strCheckForExistance = "SELECT * FROM fourth_dimension.chain_master WHERE trade_id=" + "'" + chn.getTradeId() +"'"
			+ " and retail_client_id=" + "'" + chn.getRetailClientId() + "'" + " and chain_name=" + "'" + chn.getChainName() + "'" + ";";
			String query1 = "insert into fourth_dimension.chain_master values(?,?,?,?)";
			
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(strCheckForExistance);
			if(rs.next()){
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query1);
			ps.setInt(1, chainId);
			ps.setInt(2, tradeId);
			ps.setInt(3, retailClientId);
			ps.setString(4, chainName);

			flag = ps.executeUpdate();
			if (flag > 0)
				status = 0;
		} catch (Exception e) {
//			System.out.println(e);
			e.printStackTrace();

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

	public int deleteChainByChainIds(int chainIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < chainIds.length; i++) {

				String query1 = "delete from fourth_dimension.chain_master where chain_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, chainIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == chainIds.length)
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

	public int updateChain(Chain chn) {
		int status = -1;
		int flag = 0;
		try {
			int chainId = chn.getChainId();
			int tradeId = chn.getTradeId();
			int retailClientId = chn.getRetailClientId();
			String chainName = chn.getChainName();

			String query1 = "update fourth_dimension.chain_master set "
					+ "trade_id=?,retail_client_id=?,chain_name=? where chain_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, tradeId);
			ps.setInt(2, retailClientId);
			ps.setString(3, chainName);
			ps.setInt(4, chainId);

			flag = ps.executeUpdate();
			if (flag > 0)
				status = 0;
		} catch (Exception e) {
//			System.out.println(e);
			e.printStackTrace();

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

	// Manage Trade
	public List<HashMap<String, Object>> getTrade() {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
		try {

			String query1 = "SELECT trade_id,trade_name from fourth_dimension.trade_master order by trade_name";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("tradeId", rs.getInt(1));
				outDataHashMap.put("tradeName", rs.getString(2));

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

	public int setTrade(Trade trd) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			String strExists = "SELECT * FROM fourth_dimension.trade_master WHERE trade_name="+"'"+trd.getTradeName()+"'"+";";
			System.out.println("strExists query is:\t" + strExists);
			st = con.createStatement();
			rs = st.executeQuery(strExists);
			if(rs.next()){
				status = -3;
				return status;
			}
			String query1 = "insert into fourth_dimension.trade_master values(?,?)";

			ps = con.prepareStatement(query1);
			ps.setInt(1, 0);
			ps.setString(2, trd.getTradeName());

			flag = ps.executeUpdate();

			if (flag > 0)
				status = 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		} finally {
			try {
				if (con != null) {
					cp.free(con);
					con = null;
				}
				if (ps != null)
					ps.close();
				if(st != null)
					st.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return status;
	}

	public int deleteTradeByTradeIds(int tradeIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < tradeIds.length; i++) {

				String query1 = "delete from fourth_dimension.trade_master where trade_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, tradeIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == tradeIds.length)
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

	public int updateTrade(Trade trd) {
		int status = -1;
		int flag = 0;
		try {

			String query1 = "update fourth_dimension.trade_master set trade_name=? where trade_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);

			ps.setString(1, trd.getTradeName());
			ps.setInt(2, trd.getTradeId());

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

	// Manage Retail Client
	public List<HashMap<String, Object>> getRetailClient() {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
		try {

			String query1 = "SELECT retail_client_id,retail_client_name,address from fourth_dimension.retail_client_master " +
					" order by retail_client_name";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("retailClientId", rs.getInt(1));
				outDataHashMap.put("retailClientName", rs.getString(2));
				outDataHashMap.put("address", rs.getString(3));

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

	public int setRetailClient(RetailClient rc) {
		int status = -1;
		int flag = 0;

		try {

			String query1 = "insert into fourth_dimension.retail_client_master values(?,?,?)";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setInt(1, 0);
			ps.setString(2, rc.getRetailClientName());
			ps.setString(3, rc.getAddress());

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

	public int deleteRetailClientByRetailClientIds(int retailClientIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < retailClientIds.length; i++) {

				String query1 = "delete from fourth_dimension.retail_client_master where retail_client_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, retailClientIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == retailClientIds.length)
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

	public int updateRetailClient(RetailClient rc) {
		int status = -1;
		int flag = 0;
		try {

			String query1 = "update fourth_dimension.retail_client_master set retail_client_name=?,address=? where retail_client_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);

			ps.setString(1, rc.getRetailClientName());
			ps.setString(2, rc.getAddress());
			ps.setInt(3, rc.getRetailClientId());

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

	// Manage Store Status
	public List<HashMap<String, Object>> getStoreStatus() {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
		try {

			String query1 = "SELECT store_status_id,store_status_name,description from fourth_dimension.store_status_master" +
					" order by store_status_name";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("storeStatusId", rs.getInt(1));
				outDataHashMap.put("storeStatusName", rs.getString(2));
				outDataHashMap.put("description", rs.getString(3));

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

	public int setStoreStatus(StoreStatus ss) {
		int status = -1;
		int flag = 0;

		try {
			String strCheckForExistance = "SELECT * FROM fourth_dimension.store_status_master WHERE store_status_name=" + "'" + ss.getStoreStatusName() +"'";
			String query1 = "insert into fourth_dimension.store_status_master values(?,?,?)";

			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(strCheckForExistance);
			if(rs.next()){
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query1);
			ps.setInt(1, 0);
			ps.setString(2, ss.getStoreStatusName());
			ps.setString(3, ss.getDescription());

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

	public int deleteStoreStatusByStoreStatusIds(int storeStatusIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < storeStatusIds.length; i++) {

				String query1 = "delete from fourth_dimension.store_status_master where store_status_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, storeStatusIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == storeStatusIds.length)
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

	public int updateStoreStatus(StoreStatus ss) {
		int status = -1;
		int flag = 0;
		try {
			
			String query1 = "update fourth_dimension.store_status_master set store_status_name=?,description=? where store_status_id=?";

			con = cp.getConnection();
			
			ps = con.prepareStatement(query1);

			ps.setString(1, ss.getStoreStatusName());
			ps.setString(2, ss.getDescription());
			ps.setInt(3, ss.getStoreStatusId());

			flag = ps.executeUpdate();
			if (flag > 0)
				status = 0;
		} catch (Exception e) {
			e.printStackTrace();
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

	public List<HashMap<String, Object>> getCountry() {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select * from fourth_dimension.country_master order by country_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("countryId", rs.getInt(1));
				outDataHashMap.put("countryName", rs.getString(2));
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

	public List<HashMap<String, Object>> getRegionByCountryId(int countryId) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select rm.country_id,country_name,region_id,region_name "
				+ " from fourth_dimension.region_master rm,country_master cm where "
				+ " rm.country_id=cm.country_id and rm.country_id=" + countryId+" order by region_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("countryId", rs.getInt(1));
				outDataHashMap.put("countryName", rs.getString(2));
				outDataHashMap.put("regionId", rs.getInt(3));
				outDataHashMap.put("regionName", rs.getString(4));
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

	public List<HashMap<String, Object>> getStateByCountryId(int countryId) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();

		try {

			String query1 = "select state_id,state_name from fourth_dimension.state_master where country_id="
					+ countryId+" order by state_name";
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {

				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("stateId", rs.getInt(1));
				outDataHashMap.put("stateName", rs.getString(2));
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

	public List<HashMap<String, Object>> getStateByRegionId(int regionId) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select st.country_id,country_name,st.region_id,region_name, "
				+ " state_id,state_name from fourth_dimension.state_master st,country_master "
				+ " cm ,region_master rm where "
				+ " st.country_id=cm.country_id and st.region_id =rm.region_id and st.region_id="
				+ regionId+" order by state_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("countryId", rs.getInt(1));
				outDataHashMap.put("countryName", rs.getString(2));
				outDataHashMap.put("regionId", rs.getInt(3));
				outDataHashMap.put("regionName", rs.getString(4));
				outDataHashMap.put("stateId", rs.getInt(5));
				outDataHashMap.put("stateName", rs.getString(6));
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

	public List<HashMap<String, Object>> getCityByStateId(int stateId) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select cty.country_id,country_name,cty.region_id,region_name, "
				+ " cty.state_id,state_name,city_id,city_name from fourth_dimension.city_master cty,"
				+ " country_master cm ,region_master rm,state_master st where "
				+ " cty.country_id=cm.country_id and cty.region_id =rm.region_id"
				+ " and cty.state_id=st.state_id and cty.state_id=" + stateId+" order by city_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("countryId", rs.getInt(1));
				outDataHashMap.put("countryName", rs.getString(2));
				outDataHashMap.put("regionId", rs.getInt(3));
				outDataHashMap.put("regionName", rs.getString(4));
				outDataHashMap.put("stateId", rs.getInt(5));
				outDataHashMap.put("stateName", rs.getString(6));
				outDataHashMap.put("cityId", rs.getInt(7));
				outDataHashMap.put("cityName", rs.getString(8));
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

	public List<HashMap<String, Object>> getTownByCityId(int cityId) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select tm.country_id,country_name,tm.region_id,region_name, "
				+ " tm.state_id,state_name,tm.city_id,city_name,town_id,town_name "
				+ " from fourth_dimension.town_master tm,"
				+ " country_master cm ,region_master rm,state_master st,city_master cty where "
				+ " tm.country_id=cm.country_id and tm.region_id =rm.region_id"
				+ " and tm.state_id=st.state_id and tm.city_id=cty.city_id and tm.city_id="
				+ cityId+" order by town_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("countryId", rs.getInt(1));
				outDataHashMap.put("countryName", rs.getString(2));
				outDataHashMap.put("regionId", rs.getInt(3));
				outDataHashMap.put("regionName", rs.getString(4));
				outDataHashMap.put("stateId", rs.getInt(5));
				outDataHashMap.put("stateName", rs.getString(6));
				outDataHashMap.put("cityId", rs.getInt(7));
				outDataHashMap.put("cityName", rs.getString(8));
				outDataHashMap.put("townId", rs.getInt(9));
				outDataHashMap.put("townName", rs.getString(10));

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

	public List<HashMap<String, Object>> getAreaByTownId(int townId) {

		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select am.country_id,country_name,am.region_id,region_name, "
				+ " am.state_id,state_name,am.city_id,city_name,am.town_id,town_name, "
				+ " area_id,area_name from fourth_dimension.area_master am,"
				+ " country_master cm ,region_master rm,state_master st,city_master cty,"
				+ " town_master tm where "
				+ " am.country_id=cm.country_id and am.region_id =rm.region_id and "
				+ " am.state_id=st.state_id and am.city_id=cty.city_id and am.town_id=tm.town_id"
				+ " and am.town_id=" + townId+" order by area_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("countryId", rs.getInt(1));
				outDataHashMap.put("countryName", rs.getString(2));
				outDataHashMap.put("regionId", rs.getInt(3));
				outDataHashMap.put("regionName", rs.getString(4));
				outDataHashMap.put("stateId", rs.getInt(5));
				outDataHashMap.put("stateName", rs.getString(6));
				outDataHashMap.put("cityId", rs.getInt(7));
				outDataHashMap.put("cityName", rs.getString(8));
				outDataHashMap.put("townId", rs.getInt(9));
				outDataHashMap.put("townName", rs.getString(10));
				outDataHashMap.put("areaId", rs.getInt(11));
				outDataHashMap.put("areaName", rs.getString(12));

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

	// Manage Measurement Status
	public List<HashMap<String, Object>> getMeasurementStatus() {
		int measurementStatusId;
		String measurementStatusName;
		List<HashMap<String, Object>> msList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select * from fourth_dimension.measurement_status_master order by measurement_status_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				measurementStatusId = rs.getInt(1);
				measurementStatusName = rs.getString(2);
				HashMap<String, Object> msHashMap = new HashMap<String, Object>();
				msHashMap.put("measurementStatusId", measurementStatusId);
				msHashMap.put("measurementStatusName", measurementStatusName);
				msList.add(msHashMap);
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
		return msList;
	}

	public int setMeasurementStatus(MeasurementStatus ms) {
		int status = -1;
		int flag = 0;
		int measurementStatusId = 0;
		String measurementStatusName = ms.getMeasurementStatusName();
		String strCheckForExistance = "SELECT * FROM fourth_dimension.measurement_status_master WHERE measurement_status_name=" + "'" + ms.getMeasurementStatusName() +"'"+";";

		String query1 = "insert into fourth_dimension.measurement_status_master values(?,?)";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(strCheckForExistance);
			if(rs.next()){
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query1);
			ps.setInt(1, measurementStatusId);
			ps.setString(2, measurementStatusName);
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

	public int deleteMeasurementStatusByMeasurementStatusIds(
			int measurementStatusIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < measurementStatusIds.length; i++) {

				String query1 = "delete from fourth_dimension.measurement_status_master where measurement_status_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, measurementStatusIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == measurementStatusIds.length)
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

	public int updateMeasurementStatus(MeasurementStatus ms) {
		int status = -1;
		int flag = 0;
		int measurementStatusId = ms.getMeasurementStatusId();
		String measurementStatusName = ms.getMeasurementStatusName();

		String query1 = "update fourth_dimension.measurement_status_master set measurement_status_name=? where  measurement_status_id=?";
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setString(1, measurementStatusName);
			ps.setInt(2, measurementStatusId);

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

	// Manage Job Card Status
	public List<HashMap<String, Object>> getJobCardStatus() {
		int jobCardStatusId;
		int roleId;
		String jobCardStatusName;
		String roleName;
		List<HashMap<String, Object>> msList = new ArrayList<HashMap<String, Object>>();

		String query1 = "SELECT * FROM fourth_dimension.jobcard_status_master jcs,fourth_dimension.fd_role_master role " +
				" WHERE jcs.fd_role_id = role.role_id ORDER BY jobcard_status_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				jobCardStatusId = rs.getInt(1);
				jobCardStatusName = rs.getString(2);
				roleId = rs.getInt(3);
				roleName =  rs.getString(5);
				HashMap<String, Object> msHashMap = new HashMap<String, Object>();
				msHashMap.put("jobCardStatusId", jobCardStatusId);
				msHashMap.put("jobCardStatusName", jobCardStatusName);
				msHashMap.put("roleId",roleId);
				msHashMap.put("roleName",roleName);
				msList.add(msHashMap);
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
		return msList;
	}

	
	// Manage Job Card Status
	public List<HashMap<String, Object>> getRoleForJobCardStatus() {
		int roleId;
		String roleName;
		List<HashMap<String, Object>> msList = new ArrayList<HashMap<String, Object>>();

		String query1 = "SELECT * FROM fourth_dimension.fd_role_master ORDER BY role_name;";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				roleId = rs.getInt(1);
				roleName =  rs.getString(2);
				HashMap<String, Object> msHashMap = new HashMap<String, Object>();
				msHashMap.put("roleId",roleId);
				msHashMap.put("roleName",roleName);
				msList.add(msHashMap);
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
		return msList;
	}
	
	public int setJobCardStatus(JobCardStatus ms) {
		int status = -1;
		int flag = 0;
		int jobCardStatusId = 0;
		String jobCardStatusName = ms.getJobCardStatusName();
		
		String strCheckForExistance = "SELECT * FROM fourth_dimension.jobcard_status_master WHERE jobcard_status_name=" + "'" + jobCardStatusName +"'" + ";";
		String query1 = "insert into fourth_dimension.jobcard_status_master values(?,?,?)";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(strCheckForExistance);
			if(rs.next()){
				status = -3;
				return status;
			}
			ps = con.prepareStatement(query1);
			ps.setInt(1, jobCardStatusId);
			ps.setString(2, jobCardStatusName);
			ps.setInt(3, ms.getRoleId());
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

	public int deleteJobCardStatusByJobCardStatusIds(int jobCardStatusIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < jobCardStatusIds.length; i++) {

				String query1 = "delete from fourth_dimension.jobcard_status_master where jobcard_status_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, jobCardStatusIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == jobCardStatusIds.length)
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

	public int updateJobCardStatus(JobCardStatus ms) {
		int status = -1;
		int flag = 0;
		int jobCardStatusId = ms.getJobCardStatusId();
		String jobCardStatusName = ms.getJobCardStatusName();

		String query1 = "update fourth_dimension.jobcard_status_master set jobcard_status_name=?,fd_role_id=? where  jobcard_status_id=?";
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setString(1, jobCardStatusName);
			ps.setInt(2, ms.getRoleId());
			ps.setInt(3, jobCardStatusId);

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

	public List<HashMap<String, Object>> getCostType() {
		int costTypeId;
		String costTypeName;
		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select * from fourth_dimension.cost_type_master order by cost_type_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				costTypeId = rs.getInt(1);
				costTypeName = rs.getString(2);
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("costTypeId", costTypeId);
				outDataHashMap.put("costTypeName", costTypeName);
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

	public int setCostType(CostType ct) {
		int status = -1;
		int flag = 0;
		int costTypeId = 0;
		String costTypeName = ct.getCostTypeName();
		String strExists = "SELECT * FROM fourth_dimension.cost_type_master WHERE cost_type_name=" + "'" + costTypeName + "'" + ";";
		String query1 = "insert into fourth_dimension.cost_type_master values(?,?)";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(strExists);
			if(rs.next()){
				status = -3;
				return status;
			}
			
			ps = con.prepareStatement(query1);
			ps.setInt(1, costTypeId);
			ps.setString(2, costTypeName);
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
				if (ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return status;
	}

	public int deleteCostTypeByCostTypeIds(int costTypeIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < costTypeIds.length; i++) {

				String query1 = "delete from fourth_dimension.cost_type_master where cost_type_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, costTypeIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == costTypeIds.length)
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

	public int updateCostType(CostType ct) {
		int status = -1;
		int flag = 0;
		int costTypeId = ct.getCostTypeId();
		String costTypeName = ct.getCostTypeName();

		String query1 = "update fourth_dimension.cost_type_master set cost_type_name=? where  cost_type_id=?";
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setString(1, costTypeName);
			ps.setInt(2, costTypeId);

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

	// Manage Cost Item
	public List<HashMap<String, Object>> getCostItemByCostTypeId(int costTypeId) {
		int costItemId;
		String costItemName;
		String costTypeName;
		List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select outData.cost_type_id,cost_type_name ,cost_item_id,cost_item_name"
				+ " from fourth_dimension.cost_item_master outData,cost_type_master ct "
				+ "where outData.cost_type_id=ct.cost_type_id and outData.cost_type_id="
				+ costTypeId+" order by cost_item_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				costTypeId = rs.getInt(1);
				costTypeName = rs.getString(2);
				costItemId = rs.getInt(3);
				costItemName = rs.getString(4);
				HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
				outDataHashMap.put("costTypeId", costTypeId);
				outDataHashMap.put("costTypeName", costTypeName);
				outDataHashMap.put("costItemId", costItemId);
				outDataHashMap.put("costItemName", costItemName);
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

	public int setCostItem(CostItem ci) {
		int status = -1;
		int flag = 0;
		int costItemId = 0;
		String strCheckForExistance = "SELECT * FROM fourth_dimension.cost_item_master WHERE cost_item_name=" + "'" + ci.getCostItemName() + "'" 
				+ " and cost_type_id=" + "'" + ci.getCostTypeId() + "'";
		String query1 = "insert into fourth_dimension.cost_item_master values(?,?,?)";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(strCheckForExistance);
			if(rs.next()){
				status = -3;
				return status;
			}
			
			ps = con.prepareStatement(query1);
			ps.setInt(1, costItemId);
			ps.setString(2, ci.getCostItemName());
			ps.setInt(3, ci.getCostTypeId());
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
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return status;
	}

	public int deleteCostItemByCostItemIds(int costItemIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < costItemIds.length; i++) {

				String query1 = "delete from fourth_dimension.cost_item_master where cost_item_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, costItemIds[i]);
				flag = flag + ps.executeUpdate();

			}

			if (flag == costItemIds.length)
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

	public int updateCostItem(CostItem ci) {
		int status = -1;
		int flag = 0;

		String query1 = "update fourth_dimension.cost_item_master set cost_item_name=?,"
				+ "cost_type_id=? where  cost_item_id=?";
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setString(1, ci.getCostItemName());
			ps.setInt(2, ci.getCostTypeId());
			ps.setInt(3, ci.getCostItemId());

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
	
	/*
	 * will get all data from associates table
	 */
	public List<HashMap<String, Object>> getAssociates() {
		
		System.out.println("Inside getAssociates");
		int associateId;
		String associateName;
		String associateAddress;
		String associateCity;
		String associateEmailId;
		String associateContactName;
		String associateContactPhone;
		String associateTinNo;
		String associateCstNo;
		String associateExcisable;
		
		List<HashMap<String, Object>> associateList = new ArrayList<HashMap<String, Object>>();

		String query1 = "select * from fourth_dimension.associates order by associate_name";
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				associateId = rs.getInt(1);
				associateName = rs.getString(2);
				associateAddress = rs.getString(3);
				associateCity = rs.getString(4);
				associateEmailId = rs.getString(5);
				associateContactName = rs.getString(6);
				associateContactPhone = rs.getString(7);
				associateTinNo = rs.getString(8);
				associateCstNo = rs.getString(9);
				associateExcisable = rs.getString(10);
				HashMap<String, Object> bcHashMap = new HashMap<String, Object>();
				bcHashMap.put("associateId", associateId);
				bcHashMap.put("associateName", associateName);
				bcHashMap.put("associateAddress",associateAddress);
				bcHashMap.put("associateCity",associateCity);
				bcHashMap.put("associateEmailId", associateEmailId);
				bcHashMap.put("associateContactName", associateContactName);
				bcHashMap.put("associateContactPhone",associateContactPhone);
				bcHashMap.put("associateTinNo",associateTinNo);
				bcHashMap.put("associateCstNo",associateCstNo);
				bcHashMap.put("associateExcisable",associateExcisable);
				associateList.add(bcHashMap);
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
		return associateList;
	}
	
	
	public int setAssociates(Associates associates) {
			int status = -1;
			int flag = 0;
			int associateId = 0;
			try {
				String strCheckForExistance = "SELECT * FROM fourth_dimension.associates" +
						" WHERE associate_name=" + "'" + associates.getName() +"'" +";";
				

				String query1 = "insert into fourth_dimension.associates values(?,?,?,?,?,?,?,?,?,?)";
				System.out.println("Excisable value is " + associates.getExcisable());
				con = cp.getConnection();
				st = con.createStatement();
				rs = st.executeQuery(strCheckForExistance);
				if(rs.next()){
					status = -3;
					return status;
				}
				ps = con.prepareStatement(query1);
				ps.setInt(1, associateId);
				ps.setString(2, associates.getName());
				ps.setString(3, associates.getAddress());
				ps.setString(4, associates.getCity());
				ps.setString(5, associates.getEmail());
				ps.setString(6, associates.getContactName());
				ps.setString(7, associates.getContactPhone());
				ps.setString(8, associates.getTinNumber());
				ps.setString(9, associates.getCstNumber());
				ps.setString(10,associates.getExcisable());

				flag = ps.executeUpdate();

				if (flag > 0)
					status = 0;
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println(e);

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
	
	public int deleteAssociateByAssociateIds(int associateIds[]) {
		int status = -1;
		int flag = 0;

		try {
			con = cp.getConnection();
			for (int i = 0; i < associateIds.length; i++) {

				String query1 = "delete from fourth_dimension.associates where associate_id=?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, associateIds[i]);
				flag = flag + ps.executeUpdate();
			}

			if (flag == associateIds.length)
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
	
	public int updateAssociate(Associates associates) {
		int status = -1;
		int flag = 0;
		try {

			String query1 = "update fourth_dimension.associates set associate_name=?,"
					+ "address=?,city=?,email_id=?,contact_name=?,contact_phone=?,tin_number=?,cst_number=?,excisable=? where associate_id=?";

			con = cp.getConnection();
			ps = con.prepareStatement(query1);
			ps.setString(1, associates.getName());
			ps.setString(2, associates.getAddress());
			ps.setString(3, associates.getCity());
			ps.setString(4, associates.getEmail());
			ps.setString(5, associates.getContactName());
			ps.setString(6, associates.getContactPhone());
			ps.setString(7, associates.getTinNumber());
			ps.setString(8, associates.getCstNumber());
			ps.setString(9, associates.getExcisable());
			ps.setInt(10, associates.getId());

			flag = ps.executeUpdate();
			if (flag > 0)
				status = 0;
		} catch (Exception e) {
			e.printStackTrace();
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

public List<HashMap<String, Object>> getAssociate_Capacity_Details(Associates associates) {
		
		System.out.println("Inside getAssociate_capacity details");
		int associateId;
		String associateName;
		String processName;
		int capacity;
		List<HashMap<String, Object>> associateList = new ArrayList<HashMap<String, Object>>();

		String query1 = "SELECT associate_name,pro_m.process_name,capacity " +
				"FROM fourth_dimension.associate_process asso_p,process_master pro_m,associates asso " +
				"WHERE asso_p.process_id = pro_m.process_id AND asso_p.associate_id = asso.associate_id " +
				"AND asso_p.associate_id =" + associates.getId();
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query1);
			while (rs.next()) {
				//associateId = rs.getInt(1);
				associateName = rs.getString(1);
				processName = rs.getString(2);
				capacity = rs.getInt(3);
				
				HashMap<String, Object> bcHashMap = new HashMap<String, Object>();
				//bcHashMap.put("associateId", associateId);
				bcHashMap.put("associateName", associateName);
				bcHashMap.put("processName",processName);
				bcHashMap.put("capacity",capacity);
				
				associateList.add(bcHashMap);
			}
			System.out.println("Before response" + associateList.toString());
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
		return associateList;
	}



//Manage Document Type
public List<HashMap<String, Object>> getDocumentType() {

	List<HashMap<String, Object>> outDataList = new ArrayList<HashMap<String, Object>>();
	try {

		String query1 = "SELECT document_type_id,document_type_name,description,owner from fourth_dimension.document_type_master" +
				" order by document_type_name";

		con = cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query1);
		while (rs.next()) {

			HashMap<String, Object> outDataHashMap = new HashMap<String, Object>();
			outDataHashMap.put("documentTypeId", rs.getInt(1));
			outDataHashMap.put("documentTypeName", rs.getString(2));
			outDataHashMap.put("description", rs.getString(3));
			outDataHashMap.put("owner", rs.getString(4));

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

public int setDocumentType(DocumentType ss) {
	int status = -1;
	int flag = 0;

	try {
		String strCheckForExistance = "SELECT * FROM fourth_dimension.document_type_master WHERE document_type_name=" + "'" + ss.getDocumentTypeName() +"'";
		String query1 = "insert into fourth_dimension.document_type_master values(?,?,?,?)";

		con = cp.getConnection();
		st = con.createStatement();
		rs = st.executeQuery(strCheckForExistance);
		if(rs.next()){
			status = -3;
			return status;
		}
		ps = con.prepareStatement(query1);
		ps.setInt(1, 0);
		ps.setString(2, ss.getDocumentTypeName());
		ps.setString(3, ss.getDescription());
		ps.setString(4, ss.getOwner());

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

public int deleteDocumentTypeByDocumentTypeIds(int documentTypeIds[]) {
	int status = -1;
	int flag = 0;

	try {
		con = cp.getConnection();
		for (int i = 0; i < documentTypeIds.length; i++) {

			String query1 = "delete from fourth_dimension.document_type_master where document_type_id=?";
			ps = con.prepareStatement(query1);
			ps.setInt(1, documentTypeIds[i]);
			flag = flag + ps.executeUpdate();

		}

		if (flag == documentTypeIds.length)
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

public int updateDocumentType(DocumentType ss) {
	int status = -1;
	int flag = 0;
	try {
		
		String query1 = "update fourth_dimension.document_type_master set document_type_name=?,description=?,owner=? where document_type_id=?";

		con = cp.getConnection();
		
		ps = con.prepareStatement(query1);

		ps.setString(1, ss.getDocumentTypeName());
		ps.setString(2, ss.getDescription());
		ps.setString(3, ss.getOwner());
		ps.setInt(4, ss.getDocumentTypeId());

		flag = ps.executeUpdate();
		if (flag > 0)
			status = 0;
	} catch (Exception e) {
		e.printStackTrace();
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
