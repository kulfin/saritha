<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
%>
<%
try 
{
	
	
	 con=com.DBConnection.getConnection();
		//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
		//		test","root","root"); 
	
String mno = request.getParameter("mno");
String due = request.getParameter("due");
String cno = request.getParameter("cno");



st1=con.createStatement();

int a = cno.length();
if(a == 16)
	{
	due = "paid";

int i = st1.executeUpdate("update billing set cno="+cno+" , due='"+due+"' where mno="+mno+"");

if(i==1)
	{
	out.println(" Bill Paid ");
	}
		}

	else
	{
		out.println("please check your Card No ");
	}
}
catch(Exception e)
{
	out.println(e);
}

%>