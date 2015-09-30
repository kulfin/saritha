<%@page import="ChartDirector.*,java.sql.*" %>
<%!Connection con=null;%>
<%@ page import="com.DBConnection"%>
<%!Statement stmt=null,stmt1=null;%>
<%!ResultSet rs;%>
<%!ResultSet rs1;%>
<%  con=DBConnection.getConnection();
//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/
//		test","root","root"); 
 stmt= con.createStatement(); 
String item=request.getParameter("item");
int month1=Integer.parseInt(request.getParameter("month1"));
System.out.println("item and month"+item+month1);
int year1=Integer.parseInt(request.getParameter("year1"));
System.out.println("year1:"+year1);
String s1="select sum(item_quantity)from o_item_backup where item_code=(select item_code from o_item where item_name='item3') and bill_id in(select bill_id from o_bill where to_char(bill_date,'mm')=10 and to_char(bill_date,'yyyy')=2008)";

String sql="select sum(item_quantity) from o_item_backup where item_code="+
"(select item_code from o_item where item_name='"+item+"') and bill_id in("+
"select bill_id from o_bill where to_char(bill_date,'mm')="+month1+"and to_char(bill_date,'yyyy')="+year1+")";
System.out.println("SQL:"+sql);
rs=stmt.executeQuery(sql);
System.out.println("SQL1:"+s1);

// The data for the bar chart
double[] data =new double[15];
String[] labels=new String[20];
int i=0;
while(rs.next())
{
labels[i]=month1+""+year1;
data[i]=Double.parseDouble(rs.getString(1));
i++;
System.out.println("value:"+data[i]+i);
System.out.println("value:"+labels[i]);
}
int month2=Integer.parseInt(request.getParameter("month2"));
System.out.println("item and month"+item+month2);
int year2=Integer.parseInt(request.getParameter("year2"));
System.out.println("year2:"+year2);
String s2="select sum(item_quantity) from o_item_backup where item_code=(select item_code from o_item where item_name='item3') and bill_id in(select bill_id from o_bill where to_char(bill_date,'mm')=10 and to_char(bill_date,'yyyy')=2008)";
/*
String str="select sum(item_quantity) from o_item_backup where item_code="+
"(select item_code from o_item where item_name='"+item+"') and bill_id in("+
"select bill_id from o_bill where to_char(bill_date,'mm')="+month2+" and to_char(bill_date,'yyyy')="+year2+")";
stmt1 =  con.createStatement();
rs1=stmt1.executeQuery(str);
System.out.println("SQL:"+str);

while(rs1.next())
{System.out.println("in while2");
labels[i]=month2+""+year2;
data[i]=Double.parseDouble(rs1.getString(1));
System.out.println("value1:"+data[i]+i);
System.out.println("value1:"+labels[i]);
}
*/
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