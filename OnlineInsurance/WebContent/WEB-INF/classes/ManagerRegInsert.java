// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ManagerRegInsert.java

import database.DBConn;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ManagerRegInsert extends HttpServlet
{

    public ManagerRegInsert()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try
        {
            String mname = req.getParameter("mname").trim();
            String mfname = req.getParameter("mfname").trim();
            int mage = Integer.parseInt(req.getParameter("mage").trim());
            String msex = req.getParameter("msex").trim();
            String mqual = req.getParameter("mqual").trim();
            String maddr = req.getParameter("maddr").trim();
            String mbname = req.getParameter("mbname").trim();
            String mpwd = req.getParameter("mpwd").trim();
            Date d2 = Date.valueOf(req.getParameter("bdate").trim());
            con = DBConn.getConnection();
            rs = DBConn.executeQuery("select max(branchmgrid) from branchmgr");
            if(rs.next())
            {
                bid = rs.getInt(1);
                if(bid == 0)
                    bid = 1999;
                System.out.println((new StringBuilder("if 1loop")).append(bid).toString());
                bid++;
                System.out.println((new StringBuilder("if loop")).append(bid).toString());
            } else
            {
                bid = 2000;
                System.out.println((new StringBuilder("else loop")).append(bid).toString());
            }
            pstmt = DBConn.prepareStatement("insert into branchmgr values(?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, bid);
            pstmt.setString(2, mname);
            pstmt.setString(3, mfname);
            pstmt.setInt(4, mage);
            pstmt.setString(5, msex);
            pstmt.setString(6, mqual);
            pstmt.setString(7, maddr);
            pstmt.setString(8, mbname);
            pstmt.setDate(9, d2);
            pstmt.execute();
            pstmt.close();
            pstmt = DBConn.prepareStatement("insert into login values(?,?,?)");
            pstmt.setInt(1, bid);
            pstmt.setString(2, mpwd);
            pstmt.setString(3, "MANAGER");
            pstmt.execute();
            pstmt.close();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successfully Branch Manager Registered : Branch ManagerID is </I>")).append(bid).toString());
            out.print("</B></h1></center></body>");
            DBConn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("in mgrreg");
        }
    }

    Connection con;
    ResultSet rs;
    Statement stmt;
    PreparedStatement pstmt;
    int bid;
}
