<html>
<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>

<head><link rel="stylesheet" style="text/css" href="cascadess.css"/>
<title>
	To Search An Item.......
</title>
</head>
<body background="images/bg6.jpg">
<%

/*Declaration of variables*/

Connection con=null;
Statement stmt=null;
ResultSet rs=null;

String ItemName = request.getParameter("itemname");
try
{

	/*Getting the connection variable from session*/
    con=DBConnection.getConnection();
	//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
	//		test","root","root"); 
	 stmt= con.createStatement(); 
	System.out.println("hello");
	String SelQ = "Select * from o_item where item_name like \'"+ItemName+"%"+"\'";
	System.out.println(SelQ);
	rs=stmt.executeQuery(SelQ);
	System.out.println(SelQ);
	boolean b=true;
	boolean c=false;%>
	<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
<%while(rs.next()){%>
<table>
<tr>
<td>id</td>
<td>name</td>
<td>price</td>
<td>bill</td>
<td>quantity</td>
</tr>
<tr>
<td><%=rs.getString(1)%></ol></h3></td>
<td><%=rs.getString(2)%></td>
<td><%=rs.getString(3)%></td>
<td><%=rs.getString(4)%></td>
<td><%=rs.getString(5)%></td>
</tr>
</table>
	
	<% b=false;c=true;}%><br>
	<%if(c){%>
	<H3 align="center">Above Items are Related To Search  </H3>
	<BR>
	<center>
	<A href="search_item.html"> Back</A> 
	</center>
	<%}%>
	<%if(b){%>
   <script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
		<H3 align="center">Sorry Items Are Not Available Related to Search </H3>
	<BR>
	<center>
	<A href="search_item.html"> Back</A> 
	</center>
   <%}%>


	
	<%
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
}
%>

</body>
</html>