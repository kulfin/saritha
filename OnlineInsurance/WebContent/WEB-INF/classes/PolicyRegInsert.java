// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PolicyRegInsert.java

import database.DBConn;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class PolicyRegInsert extends HttpServlet
{

    public PolicyRegInsert()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try
        {
            String pname = req.getParameter("pname").trim();
            int pterm = Integer.parseInt(req.getParameter("pterm").trim());
            int pamt = Integer.parseInt(req.getParameter("pamt").trim());
            int pfaceamt = Integer.parseInt(req.getParameter("pfaceamt").trim());
            int pinterest = Integer.parseInt(req.getParameter("pinterest").trim());
            int pbonusperiod = Integer.parseInt(req.getParameter("pbonusperiod").trim());
            int pbonusrate = Integer.parseInt(req.getParameter("pbonusrate").trim());
            Date d2 = Date.valueOf(req.getParameter("bdate").trim());
            con = DBConn.getConnection();
            rs = DBConn.executeQuery("select max(policyid) from policies");
            if(rs.next())
            {
                pid = rs.getInt(1);
                if(pid == 0)
                    pid = 9999;
                System.out.println((new StringBuilder("if 1loop")).append(pid).toString());
                pid++;
                System.out.println((new StringBuilder("if loop")).append(pid).toString());
            } else
            {
                pid = 10000;
                System.out.println((new StringBuilder("else loop")).append(pid).toString());
            }
            pstmt = DBConn.prepareStatement("insert into policies values(?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pid);
            pstmt.setString(2, pname);
            pstmt.setInt(3, pterm);
            pstmt.setInt(4, pamt);
            pstmt.setInt(5, pfaceamt);
            pstmt.setInt(6, pinterest);
            pstmt.setDate(7, d2);
            pstmt.setInt(8, pbonusperiod);
            pstmt.setInt(9, pbonusrate);
            pstmt.execute();
            pstmt.close();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successfully Policy Registered : PolicyID is </I>")).append(pid).toString());
            out.print("</B></h1></center></body>");
            DBConn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("in policyreg");
        }
    }

    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement stmt;
    int pid;
}
