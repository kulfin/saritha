<%@page import="com.connect.dbConnect"%>
<%@page import="java.sql.*"%>
<%String name=(String)session.getAttribute("username");

if(name==null)
{
    response.sendRedirect("index.jsp");
}
else
{
	if(name.equals("admin"))
{
%>

<div id="container">
<link href="css.css" rel="stylesheet" type="text/css"/>

<div id="header">
<img src="images/logo.jpg"/>

</div>

	<div id="navigation">
		<ul>
                    
			<li><a href="index.jsp">Home</a></li>
			<li><a href="allaccounts.jsp">Show All Accounts</a></li>
			<li><a href="register.jsp">Open Account</a></li>
			<li><a href="deposit.jsp">Deposit</a></li>
			<li><a href="delete.jsp">Close Account</a></li>
            <li> <a href="logout.jsp">Logout</a></li>
 
		</ul>   
   </div>


<div id="content-container">
<div id="content">
<center><br/><h2>All Accounts Details:-</h2><br/><br/>
  
     <%
    try
    {
    Connection con=dbConnect.getConnect();
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery("select * from record");%>
    
	<table cellpadding="10" border="1" >
        <tr><td><b>Username</b></td><td><b>Amount</b></td><td><b>Address</b></td><td><b>Phone No.</b></td></b></tr>
       <% while(rs.next())
       {%>
        <tr><td><%=rs.getString(1)%></td><td><%=rs.getString(3)%></td><td><%=rs.getString(4)%></td><td><%=rs.getString(5)%></td></tr>       	
        <%} %>
	</table>
		         
    <%}
    catch(Exception e)
    {
    	response.sendRedirect("error.jsp");
    }
    %>
  
  </center>
</div>
<div id="aside">
<p>* Online banking is the practice of making bank transactions or paying bills via the Internet.
<br/><br/>* Banking online allows a customer to make deposits, withdrawals, transfers with the click of a mouse.
<br/><br/>* Online banking also eliminates paper waste, which is a plus not only for those who have to handle all the paper work, but also for the environment.
<br/><br/>* Security is always an issue with Internet transactions. Although information is encrypted , and the chances of an account being hacked are slim, it happen.
</p>
</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</div>

<%}
else
	response.sendRedirect("index.jsp");
}
%>