<html>
<%@ page language="java" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page import="com.DBConnection"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>

<head>
<title><link rel="stylesheet" style="text/css" href="cascadess.css"/>
	To Find Status Of The Inventory.......
</title>
</head>
<body background="images/bg6.jpg">
<%

/*Declaration of variables*/

Connection con=null;
Statement stmt=null;
ResultSet rs=null;

String ItemCode = request.getParameter("itemcode");
try
{

	/*Getting the connection variable from session*/
   con=DBConnection.getConnection();
	//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
	//		test","root","root"); 
	 stmt= con.createStatement(); 
	System.out.println("hello");
	String SelQ = "Select count(*) from o_item where item_code=\'"+ItemCode+"\'";
	System.out.println(SelQ);
	rs=stmt.executeQuery(SelQ);
	System.out.println(SelQ);
if(rs.next()){
	int q=rs.getInt(1);
	if(q!=0){
//System.out.println("hello");
	%>
	
	<script>
		for(i=1;i<=10;i++) document.write("<br>");
	</script>
		<H3 align="center"><%=rs.getInt(1)%>&nbsp;&nbsp;Items Are Available </H3>
	<BR>
	<center>
		<A href="status_inventory.html"> Back</A> 
	</center>
	<%}else
	{%><script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
	<H3 align="center">Sorry Items Are Not Available</H3>
	<BR>
	<center>
		<A href="status_inventory.html"> Back</A>
	</center>
	<%}}
	else{%>
<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
	<H3 align="center">Sorry Items Are Not Available</H3>
	<BR>
	<center>
		<A href="status_inventory.html"> Back</A>
	</center>
	<%}	
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
}
%>

</body>
</html>