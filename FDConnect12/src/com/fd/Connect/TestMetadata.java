package com.fd.Connect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMetadata {
  public static void main(String[] args) throws Exception {
    String driver = "com.mysql.jdbc.Driver";
    Class.forName(driver).newInstance();
    //static String url = "jdbc:mysql://192.168.1.20:3306/fourth_dimension"; //for 182.50.142.1
    String jdbcUrl = "jdbc:mysql://localhost:3307/fourth_dimension";
    Connection conn = DriverManager.getConnection(jdbcUrl, "root", "root");

    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM unit_master");

    printColumnInfo(rs);

    printColumnNames(rs);
    processRs(rs);
    System.out.println("Call Routine OR Stored Procedure :: ");
    rs = stmt.executeQuery("call select_routine");
    printColumnInfo(rs);

    conn.close();
  }

  public static void processRs(ResultSet rs) throws SQLException {
    ResultSetMetaData rmd = rs.getMetaData();
    while (rs.next()) {
      for (int col = 1; col <= rmd.getColumnCount(); col++)
        getData(rs, rmd.getColumnType(col), col);
    }
  }

  public static void printColumnNames(ResultSet rs) throws SQLException {
    ResultSetMetaData rmd = rs.getMetaData();
    for (int col = 1; col <= rmd.getColumnCount(); col++)
      System.out.println(rmd.getColumnName(col) + " ");
  }

  public static void getData(ResultSet rs, int type, int colIdx) throws SQLException {
    switch (type) {
    case java.sql.Types.CHAR:
    case java.sql.Types.VARCHAR:
      System.out.println(rs.getString(colIdx));
      break;

    case java.sql.Types.INTEGER:
      int i = rs.getInt(colIdx);
      System.out.println(i);
      break;

    case java.sql.Types.NUMERIC:
      BigDecimal bd = rs.getBigDecimal(colIdx);
      System.out.println(bd.toString());
      break;

    case java.sql.Types.TIMESTAMP:
    case java.sql.Types.DATE:
      java.sql.Date d = rs.getDate(colIdx);
      System.out.println(d.toString());
      break;

    }
  }

  public static void printColumnInfo(ResultSet rs) throws SQLException {
    ResultSetMetaData rsmd = rs.getMetaData();
    int cols = rsmd.getColumnCount();
    System.out.println("No_of_Columns "+cols);
    System.out.println("Table Details ::");
    for (int colIdx = 1; colIdx <= cols; colIdx++) {
      String name = rsmd.getColumnName(colIdx);
      int type = rsmd.getColumnType(colIdx);
      String typeName = rsmd.getColumnTypeName(colIdx);
      System.out.println(name + ", " + type + ", " + typeName);
    }
  }
}