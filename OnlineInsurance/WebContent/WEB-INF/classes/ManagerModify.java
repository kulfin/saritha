// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ManagerModify.java

import database.DBConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ManagerModify extends HttpServlet
{

    public ManagerModify()
    {
    }

    public void service(HttpServletRequest request, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try
        {
            id = Integer.parseInt(request.getParameter("bid").trim());
            String mname = request.getParameter("mname").trim();
            String mfname = request.getParameter("mfname").trim();
            int mage = Integer.parseInt(request.getParameter("mage").trim());
            String msex = request.getParameter("msex").trim();
            String maddr = request.getParameter("maddr").trim();
            String mqual = request.getParameter("mqual").trim();
            String mpwd = request.getParameter("mpwd").trim();
            String mbname = request.getParameter("mbname").trim();
            Date d2 = Date.valueOf(request.getParameter("bdate").trim());
            con = DBConn.getConnection();
            pstmt = DBConn.prepareStatement((new StringBuilder("update branchmgr set branchmgrname=?,branchmgrfname=?,branchmgrage=?,branchmgrsex=?,branchmgrqual=?,branchmgraddress=?,branchname=?,branchmgrjoindate=? where  branchmgrid=")).append(id).toString());
            PreparedStatement pstmt1 = con.prepareStatement((new StringBuilder("update login set password=? where userid=")).append(id).toString());
            pstmt.setString(1, mname);
            pstmt.setString(2, mfname);
            pstmt.setInt(3, mage);
            pstmt.setString(4, msex);
            pstmt.setString(5, mqual);
            pstmt.setString(6, maddr);
            pstmt.setString(7, mbname);
            pstmt.setDate(8, d2);
            pstmt.execute();
            pstmt1.setString(1, mpwd);
            pstmt1.execute();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successful Manager Modification : Branch  ManagerID is </I>")).append(id).toString());
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
}
