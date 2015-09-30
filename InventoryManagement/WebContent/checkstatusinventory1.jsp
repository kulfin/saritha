<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection" %>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
<HEAD> <link rel="stylesheet" style="text/css" href="cascadess.css"/>
</HEAD>
<BODY background="images/bg6.jpg">
<center><h2>ITEMS INFORMATION</h2></center>
<%
	Connection con=null;;
	ResultSet rs=null;
	Statement stmt=null;
	try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection("jdbc:oracle:thin:@ipoghome:1521:orcl","scott","tiger");
			stmt =  con.createStatement();
			String Query = "Select * from o_item";
			rs = stmt.executeQuery(Query);
%>
<table align="center" border="0">
<tr align="center"><th >ITEM_CODE</th><th>ITEM_NAME </th><th>ITEM_PRICE</th><th>ITEM_QUANTITY</th><th>ITEM_QUALITY</th><th>CATEGORY</th>
</tr>

<%		while(rs.next()){					%>
					
<tr align="center"><td>
<a href='validate_items.jsp?info=<%=rs.getString(1)%>'><%=rs.getString(1)%></td><td><%=rs.getString(2)%></td><td><%=rs.getString(3)%></td><td><%=rs.getString(4)%></td>
   <TD><%=rs.getString(5)%></td><td><%=rs.getString(7)%></td></tr>

<%   	}       %>


<% con.close();}
		
   catch(Exception e){
%>
	<%=e%>
	
	<%   } %>
</table></BODY>


