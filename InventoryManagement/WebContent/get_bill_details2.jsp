  <html>
  <head><head><link rel="stylesheet" style="text/css" href="cascadess.css"/><title>Do billing jsp</title>
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*"%>
<%@ page import="com.DBConnection" %>
</head>
<body background="images/bg6.jpg">
<%
Connection con=null;
 Statement stmt1=null;
 Statement stmt2=null;
 Statement stmt3=null;
 ResultSet rs1=null;
 ResultSet rs2=null;
 ResultSet rs3=null;
 
String billid=request.getParameter("bill_id");
//System.out.println("billid is"+billid);
try
{
	 con=DBConnection.getConnection();
		//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
		//		test","root","root"); 
		 stmt1= con.createStatement(); 
// stmt1 =  con.createStatement();
stmt2 =  con.createStatement();
stmt3 =  con.createStatement();
    String selq1="Select item_code  from o_item_backup where bill_id=\'"+billid+"\'";

	String selq2="select i.item_name, ib.item_quantity,ib.item_price,ib.item_price*ib.item_quantity amount from o_item i,o_item_backup ib where i.item_code=ib.item_code and bill_id=\'"+billid+"\'";
	
	String selq3="Select bill_amount  from o_bill where bill_id=\'"+billid+"\'";

	rs1=stmt1.executeQuery(selq1);
	
	if(!(rs1.next())){%>
<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script>
	<H3 align="center">Sorry bill id is not exists in the databases</H3>
	<BR>
	<center>
		<A href="get_bill_details1.jsp"> Back </A>
	</center>
 <%}else{%>
 <center>
	<H3 align="center">Bill Details Are </H3>
	<BR>
	<form name="form1">
	<table>
	<tr>
	<th>Item Name</th>
	<th>Item Quanity</th>
	<th>Item Price</th>
	<th>Amount</th>
	</tr>
		<%rs2=stmt2.executeQuery(selq2);
		while(rs2.next())
		{%>
		<tr>
		<td><%=rs2.getString(1)%></td>
		<td><%=rs2.getInt(2)%></td>
		<td><%=rs2.getDouble(3)%></td>
	    <td><%=rs2.getDouble(4)%></td>
	 </tr>
	 	 <%}
		rs3=stmt3.executeQuery(selq3);
		while(rs3.next())
		{%>
		<tr>
		<th>Total</th>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	    <td><%=rs3.getDouble(1)%></td>
	 </tr>
	 <%}%>
	 </table>
	 </form>
   
   <A href="get_bill_details1.jsp"> Back </A>
	</center>
<%}
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
}
finally
{   
	con.close();
	stmt1.close();
	stmt2.close();
	stmt3.close();
}%>

</body>
</html>