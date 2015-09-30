import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Delete extends HttpServlet
{
public void doGet(HttpServletRequest req, HttpServletResponse resp)
{
Connection con;
PreparedStatement ps=null;
int rs=0;
try
{
PrintWriter pw=resp.getWriter();
resp.setContentType("text/html");
int eid=Integer.parseInt(req.getParameter("eid"));
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@ipoghome:1521:orcl","scott","tiger");

System.out.println("connected");
ps=con.prepareStatement("  delete from emp where empno=?");
ps.setInt(1,eid);
rs=ps.executeUpdate();
if(rs!=1)
	pw.println("problem in delete operation");
else
pw.println("delete operation");
pw.close();
con.close();
}//try
catch(Exception e){
e.printStackTrace();
}//catch
}//doget
public void doPost(HttpServletRequest req, HttpServletResponse resp)
{
	doGet(req,resp);
}
}//class