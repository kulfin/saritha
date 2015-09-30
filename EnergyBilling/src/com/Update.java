package com;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Update extends HttpServlet
{
public void doGet(HttpServletRequest req, HttpServletResponse resp)
{
Connection con;
PreparedStatement ps=null;
PrintWriter pw=null;
int rs=0;
try
{
pw=resp.getWriter();
resp.setContentType("text/html");
int eid=Integer.parseInt(req.getParameter("eid"));
String ename=req.getParameter("opwd");
String ename1=req.getParameter("npwd");
pw.println("eid");
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@ipoghome:1521:orcl","scott","tiger");

pw.println("connected");
ps=con.prepareStatement("update emp set ename=? where empno=? and ename=?");
ps.setString(1,ename1);

ps.setInt(2,eid);
ps.setString(3,ename);
rs=ps.executeUpdate();
if(rs!=1)
	pw.println("problem in delete operation");
else
pw.println("delete operation");
pw.close();
con.close();
}//try
catch(Exception e){

	pw.println("errors");

e.printStackTrace();
}//catch
}//doget

public void doPost(HttpServletRequest req, HttpServletResponse resp)
{
	doGet(req,resp);
}

}//class