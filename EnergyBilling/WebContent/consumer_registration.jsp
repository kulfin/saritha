<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">




<BODY bgColor=#FFFFFF>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font face="AC"><b><a style="text-decoration: none" href="consumer_details.jsp">
<font color="#000000" size="7">Back</font></a></a></b></font></p>


<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
%>
<%
con=com.DBConnection.getConnection();
		
	
String cname = request.getParameter("cname").trim();
String dname = request.getParameter("dname").trim();
String mno = request.getParameter("mno").trim();
String mcname = request.getParameter("mcname").trim();
String name = request.getParameter("name").trim();
String age = request.getParameter("age").trim();
String sex = request.getParameter("sex").trim();
String cno = request.getParameter("cno").trim();
String address = request.getParameter("address").trim();
String type = request.getParameter("type").trim();
String uname = request.getParameter("uname");
String pass = request.getParameter("pass");

	st=con.createStatement();
	st1 = con.createStatement();

rs=st.executeQuery("select * from consumer where mno="+mno+"");
			
	if(!rs.next())
		{	
			

		st.executeUpdate("insert into consumer values('"+cname+"','"+dname+"',"+mno+",'"+mcname+"','"+name+"','"+age+"','"+sex+"','"+cno+"','"+address+"','"+type+"','"+uname+"','"+pass+"')");

out.println("<font color=blue size=4><b><center><b><h1>Consumer Added");
	out.println("<p><h1><font color=blue><b><center><b><center>Thanks</p>");
%>
  
    
<%
		}
		else
		{
			out.println("<h1><font color=red><b><center><b> Please Check Meter No ");
		}
	%>