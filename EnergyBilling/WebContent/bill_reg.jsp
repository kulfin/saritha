<%@ page import="java.sql.*,java.util.*" %>

<%! 
	Connection con;
	ResultSet rs,rs1;
	Statement st,st1;
	int no = 0;
	int cno =0;
	int amount =0;
	String due;
%>
<%
con=com.DBConnection.getConnection();

String cname = request.getParameter("cname").trim();
String dname= request.getParameter("dname").trim();
String mno= request.getParameter("mno").trim();
String mcname= request.getParameter("mcname").trim();
String name= request.getParameter("name").trim();
String type= request.getParameter("type").trim();
String used= request.getParameter("used").trim();


st=con.createStatement();
st1=con.createStatement();

rs=st.executeQuery("select * from billing where mno="+mno+"");

if(!rs.next())
{
	rs1 = st1.executeQuery("select max(bno) from billing");

	if(rs1.next())
	{
		no = rs1.getInt(1);
		no = no+1;

		int units = Integer.parseInt(used);
		if(units >=0 && units<=50)
		{
			amount = units * 4;
		}
		else
			if(units >= 51 && units <=100)
		{
			amount = units * 6;
		}
		else
		{
			amount = units * 8;
		}
	due = "balance";
	int i = st.executeUpdate("insert into billing values("+no+",'"+cname+"','"+dname+"',"+mno+",'"+mcname+"','"+name+"','"+type+"',"+used+","+amount+","+cno+",'"+due+"')");
	if(i==1)
		response.sendRedirect("commercial_bill.jsp");
	}
}
else
{
	out.println("Sorry");
}
%>