<%@ page language="java" %>
<%@page import="ChartDirector.*,java.sql.*" %>
<%@ page session="true" import="java.sql.*,java.io.*,java.util.*"%>
<% String uid=(String)session.getAttribute("user");
if(uid==null)
{%>
<jsp:forward page="login.jsp"/>
<%}%>

<%!Connection con=null;%>
<%!Statement stmt=null,stmt1=null;%>
<%!ResultSet rs,rsl;%>
<% con=DBConnection.getConnection();
//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
//		test","root","root"); 
 stmt= con.createStatement(); 
rs=stmt.executeQuery("select item_name from o_item");
%>
<form action="simplebar.jsp">
<table border='0' align='center'><tr><td><b>Select Item</td><td><select name="item">
  <%while(rs.next())
  {%>
  <option value='<%=rs.getString(1)%>'><%=rs.getString(1)%></option>
  <%}
  %>
  </select></td>
   <td><input type="submit" value="Generate"></td></tr>
   </table>
<%
String item=request.getParameter("item");
//Class.forName("oracle.jdbc.driver.OracleDriver");
//con=DriverManager.getConnection("jdbc:oracle:thin:@ipoghome:1521:orcl","scott","tiger");
String sql="select i.item_price,i.item_quantity from o_item i,o_bill b,o_item_backup ib where b.bill_id=ib.bill_id and i.item_code=ib.item_code and i.item_name='"+item+"'";
  rsl=stmt.executeQuery(sql);
System.out.println("SQL:"+sql);

// The data for the bar chart
double[] data =new double[15];
String[] labels=new String[20];
int i=0;
while(rsl.next())
{
labels[i]=rsl.getString(1);
data[i]=rsl.getDouble(2);
i++;
System.out.println("value:"+data[i]+i);
System.out.println("value:"+labels[i]);
}

// The labels for the bar chart

// Create a XYChart object of size 250 x 250 pixels
XYChart c = new XYChart(600, 300, 0xeeeeee, 0x000000, 1);
c.setRoundedFrame();

// Set the plotarea at (30, 20) and of size 200 x 200 pixels
c.setPlotArea(60, 60, 520, 200, 0xffffff, -1, 0xcccccc, 0xccccccc);

// Add a bar chart layer using the given data
c.addBarLayer(data);

// Set the labels on the x axis.
c.xAxis().setLabels(labels);

// output the chart
String chart1URL = c.makeSession(request, "chart1");

// Include tool tip for the chart
String imageMap1 = c.getHTMLImageMap("", "", "title='{xLabel}: US${value}K'");
%>
<html><head> <link rel="stylesheet" style="text/css" href="cascadess.css"/></head>
<body topmargin="5" leftmargin="5" rightmargin="0" background="images/bg6.jpg" >
<div style="font-size:18pt; font-family:verdana; font-weight:bold color:white">
  <center>  A Simple Bar Chart for&nbsp&nbsp<font color="#FFFFFF"><%=item%>
<div>
<hr color="#000080">
<!-- <a href="viewsource.jsp?file=<%=request.getServletPath()%>">
    <font size="2" face="Verdana">View Chart Source Code</font>
</a>-->
</div>
<br>
<img src='<%=response.encodeURL("getchart.jsp?"+chart1URL)%>'
    usemap="#map1" border="0">
<map name="map1"><%=imageMap1%></map>
</body>
</html>