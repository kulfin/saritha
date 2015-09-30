<%@page import="com.connect.dbConnect"%>
<%@page import="java.sql.*"%>

<%int a=Integer.parseInt(request.getParameter("amount"));
String name=(String)session.getAttribute("username");


    try
    {
    Connection con=dbConnect.getConnect();
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery("select * from record where username='"+name+"'");
	
	if(rs.next())
	{
		if(rs.getInt("amount")>a)
		{
			st.executeUpdate("update record set amount=amount-"+a+" where username='"+name+"'");
			session.setAttribute("withdraw_status","true");
		}
		else
			session.setAttribute("withdraw_status","false");
	}
	else
		response.sendRedirect("error.jsp");
				
	response.sendRedirect("withdraw.jsp");
    }catch(Exception e)
    {
    	response.sendRedirect("error.jsp");
    }
    %>