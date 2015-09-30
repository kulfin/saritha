<html>
  <head><link rel="stylesheet" style="text/css" href="cascadess.css"/><title>billing  details jsp</title>
<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>
</head>
<body background="images/bg6.jpg">
<%!double bill_amt;%>
<%! double price;%>
<%
System.out.println("hello");
 Connection con=null;
 Statement stmt=null;
 ResultSet rs=null;
 Statement stmt2=null;
 ResultSet rs2=null;
 Statement stmt3=null;
 Statement stmt4=null;
  Statement stmt5=null;
  Statement stmt6=null;
 //ResultSet rs3=null;
String billid=(String)session.getAttribute("billid");
String[] items=(String[])session.getAttribute("items");
for(int i=0;i<items.length;i++)
{
	System.out.println(items[i]);
}

try
{

   // int j=0;
	double price=0.0;
	double bill_amt=0.0;
	/*Getting the connection variable from session*/
   con=DBConnection.getConnection();
	//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
	//		test","root","root"); 
	 stmt= con.createStatement(); 
	// stmt =  con.createStatement();
    stmt2 =  con.createStatement();
	stmt3 =  con.createStatement();
    stmt4 =  con.createStatement(); 
    stmt5 =  con.createStatement();
	stmt6 =  con.createStatement();
	//String selq1="select item_price from o_item where item_code=\'"+items[j]+"\'";

	//String selq2="update o_item set item_quantity=item_quantity-1 where item_code=\'"+items[j]+"\'";
	//String selq3="insert into o_item_backup values(\'"+items[j]+"\',"+price+","+1+",\'"+billid+"\')";
    //String selq6="select item_code from o_item_backup where item_code=\'"+items[j]+"\'"; 
	//String selq4="update o_item_backup set item_quantity=item_quantity+1 where item_code=\'"+items[j]+"\'";
	//String Query5=" update  o_bill set bill_amount=bill_amount+"+bill_amt+"where bill_id=\'"+billid+"\'";
	
   // String selq6=select 
for(int j=0;j<items.length;j++)
	{
	String selq4="select item_quantity from o_item where item_code=\'"+items[j]+"\'";
	rs2=stmt4.executeQuery(selq4);
	if(rs2.next())
		{
	if(rs2.getInt(1)==1)
		continue;
		}
	String selq1="select item_price from o_item where item_code=\'"+items[j]+"\'";
	rs=stmt.executeQuery(selq1);
	if(rs.next())
		{
		price=rs.getDouble(1);
	String selq2="update o_item set item_quantity=item_quantity-1 where item_code=\'"+items[j]+"\'";
	String selq3="insert into o_item_backup values(\'"+items[j]+"\',"+price+","+1+",\'"+billid+"\')";
		int row=stmt3.executeUpdate(selq3);

		System.out.println("SQL:"+selq3+":::"+price);
	System.out.println("Before"+bill_amt);
	System.out.println("Price:"+price);
		bill_amt=bill_amt+price;
		System.out.println("After"+bill_amt);
		stmt2.executeUpdate(selq2);
		}
		
	}
	
if(bill_amt!=0)
	{
	String Query5=" update  o_bill set bill_amount=bill_amount+"+bill_amt+"where bill_id=\'"+billid+"\'";
	
	stmt5.executeUpdate(Query5);
	}%>
<script>
		for(i=1;i<=3;i++) document.write("<br>");
	</script><H3 align="center">Success Billing.............</h3>
		<H3 align="center">Ur Bill Amount is:=<%=bill_amt%>  </H3>
	<BR>
	<center>
		<A href="items_details.jsp"> Back </A>
	</center>
	
<%}
catch(Exception e)
{
	System.out.println("Exception"+e);
}
finally{
	con.close();
	stmt.close();
	stmt2.close();
	stmt3.close();
	stmt4.close();
	stmt5.close();
	stmt6.close();
	rs.close();
	rs2.close();
}


%>

</body>
</html>