<%@page import="com.connect.dbConnect"%>
<%@page import="java.sql.*"%>

<%String u=request.getParameter("username");

    try
    {
    Connection con=dbConnect.getConnect();
    Statement st=con.createStatement();
    int x =st.executeUpdate("delete from record where username='"+u+"'");
	    
	if(x>0)
		session.setAttribute("delete_status","deleted");
	else
		session.setAttribute("delete_status","not_found");
				
	response.sendRedirect("delete.jsp");
    }catch(Exception e)
    {
    	response.sendRedirect("error.jsp");
    }
    %>