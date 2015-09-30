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
try 
{
	 con=com.DBConnection.getConnection();
		
String cname = request.getParameter("cname").trim();
System.out.println(cname);
String dname = request.getParameter("dname").trim();
System.out.println(dname);
String mno = request.getParameter("mno").trim();
System.out.println(mno);
String mcname = request.getParameter("mcname").trim();
System.out.println(mcname);
String name = request.getParameter("name").trim();
System.out.println(name);
String age = request.getParameter("age").trim();
System.out.println(age);
String sex = request.getParameter("sex");
System.out.println(sex);
String cno = request.getParameter("cno").trim();
System.out.println(cno);
String address = request.getParameter("address").trim();
System.out.println(address);
String type = request.getParameter("type").trim();
System.out.println(type);
String uname = request.getParameter("uname");
System.out.println(uname);
String pass = request.getParameter("pass");
System.out.println(pass);
	
		
		st1=con.createStatement();
		
		String Query = 	"Update consumer  set cname='"+cname+"',dname='"+dname+"',mcname='"+mcname+"',name='"+name+"',age="+age+",sex='"+sex+"',cno="+cno+",address='"+address+"',type='"+type+"',uname='"+uname+"',pass='"+pass+"'where mno ="+mno+"";

		System.out.println("statement is:::"   +st1);


int i = st1.executeUpdate(Query);

System.out.println("int for update is:"   +i);
if(i==1)
	{
	out.println("<font color=blue size=4><b><center><b><h1>consumer Updated ");
			out.println("<p><h1><font color=blue><b><center><b><center>Thanks</p>");
	}




		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>	