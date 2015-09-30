// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustRegInsert.java

import database.DBConn;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CustRegInsert extends HttpServlet
{

    public CustRegInsert()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try
        {
            String cname = req.getParameter("cname").trim();
            String cfname = req.getParameter("cfname").trim();
            int cage = Integer.parseInt(req.getParameter("cage").trim());
            String csex = req.getParameter("csex").trim();
            String cqual = req.getParameter("cqual").trim();
            String coccup = req.getParameter("coccup").trim();
            String caddr = req.getParameter("caddr").trim();
            String cpwd = req.getParameter("cpwd").trim();
            Date d2 = Date.valueOf(req.getParameter("bdate").trim());
            con = DBConn.getConnection();
            rs = DBConn.executeQuery("select max(custid) from customer");
            if(rs.next())
            {
                bid = rs.getInt(1);
                if(bid == 0)
                    bid = 4999;
                System.out.println((new StringBuilder("if 1loop")).append(bid).toString());
                bid++;
                System.out.println((new StringBuilder("if loop")).append(bid).toString());
            } else
            {
                bid = 5000;
                System.out.println((new StringBuilder("else loop")).append(bid).toString());
            }
            pstmt = DBConn.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, bid);
            pstmt.setString(2, cname);
            pstmt.setString(3, cfname);
            pstmt.setInt(4, cage);
            pstmt.setString(5, csex);
            pstmt.setString(6, cqual);
            pstmt.setString(8, coccup);
            pstmt.setString(7, caddr);
            pstmt.setDate(9, d2);
            pstmt.execute();
            pstmt.close();
            pstmt = DBConn.prepareStatement("insert into login values(?,?,?)");
            pstmt.setInt(1, bid);
            pstmt.setString(2, cpwd);
            pstmt.setString(3, "CUSTOMER");
            pstmt.execute();
            pstmt.close();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successfully Customer Registered : CustomerID is </I>")).append(bid).toString());
            out.print("</B></h1></center></body>");
            DBConn.close();
        }
        catch(Exception e)
        {
            System.out.println("in custreg");
            e.printStackTrace();
            System.out.println("in custreg");
        }
    }

    Connection con;
    ResultSet rs;
    ResultSet rs1;
    Statement stmt;
    PreparedStatement pstmt;
    int bid;
    int branchid;
}
