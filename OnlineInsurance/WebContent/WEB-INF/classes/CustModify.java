// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustModify.java

import database.DBConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CustModify extends HttpServlet
{

    public CustModify()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try
        {
            id = Integer.parseInt(req.getParameter("bid").trim());
            String cname = req.getParameter("cname").trim();
            String cfname = req.getParameter("cfname").trim();
            int cage = Integer.parseInt(req.getParameter("cage").trim());
            String cqual = req.getParameter("cqual").trim();
            String coccup = req.getParameter("coccup").trim();
            String caddr = req.getParameter("caddr").trim();
            String cpwd = req.getParameter("cpwd").trim();
            Date d2 = Date.valueOf(req.getParameter("bdate").trim());
            con = DBConn.getConnection();
            pstmt = DBConn.prepareStatement((new StringBuilder("update customer set custname=?,custfname=?,custage=?,custqual=?,custaddress=?,custoccupation=? where custid=")).append(id).toString());
            pstmt.setString(1, cname);
            pstmt.setString(2, cfname);
            pstmt.setInt(3, cage);
            pstmt.setString(4, cqual);
            pstmt.setString(5, caddr);
            pstmt.setString(6, coccup);
            pstmt.execute();
            pstmt1 = con.prepareStatement((new StringBuilder("update login set password=? where userid=")).append(id).toString());
            pstmt1.setString(1, cpwd);
            pstmt1.execute();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successful Customer Modification: CustomerID is </I>")).append(id).toString());
            out.print("</B></h1></center></body>");
            DBConn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    int id;
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    PreparedStatement pstmt1;
}
