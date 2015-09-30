// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustPolyRegInsert.java

import database.DBConn;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CustPolyRegInsert extends HttpServlet
{

    public CustPolyRegInsert()
    {
        amt = 0;
    }

    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try
        {
            int cid = Integer.parseInt(req.getParameter("cid").trim());
            int pid = Integer.parseInt(req.getParameter("pid").trim());
            int aid = Integer.parseInt(req.getParameter("aid").trim());
            String ptype = req.getParameter("ptype").trim();
            String nomi = req.getParameter("nominee").trim();
            String rel = req.getParameter("relation").trim();
            Date d2 = Date.valueOf(req.getParameter("bdate").trim());
            con = DBConn.getConnection();
            System.out.println((new StringBuilder("connection in custpoly")).append(con).toString());
            rs = DBConn.executeQuery("select max(custpolicyid) from custpolicies");
            if(rs.next())
            {
                bid = rs.getInt(1);
                if(bid == 0)
                    bid = 49999;
                System.out.println((new StringBuilder("if 1loop")).append(bid).toString());
                bid++;
                System.out.println((new StringBuilder("if loop")).append(bid).toString());
            } else
            {
                bid = 50000;
                System.out.println((new StringBuilder("else loop")).append(bid).toString());
            }
            pstmt = DBConn.prepareStatement("insert into custpolicies values(?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, bid);
            pstmt.setInt(2, cid);
            pstmt.setInt(3, pid);
            pstmt.setDate(4, d2);
            pstmt.setString(5, ptype);
            pstmt.setInt(6, amt);
            pstmt.setString(7, nomi);
            pstmt.setString(8, rel);
            pstmt.setInt(9, aid);
            pstmt.setDate(10, null);
            pstmt.execute();
            System.out.println((new StringBuilder("pstmt in custpoly ")).append(pstmt).toString());
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successfully Customer Policy Registered : CustomerPolicyID is </I>")).append(bid).toString());
            out.print("</B></h1></center></body>");
        }
        catch(Exception e)
        {
            System.out.println("in custpolyreg");
            e.printStackTrace();
            System.out.println("in custpolyreg");
        }
    }

    Connection con;
    ResultSet rs;
    Statement stmt;
    PreparedStatement pstmt;
    int bid;
    int amt;
}
