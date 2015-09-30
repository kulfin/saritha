// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PolicyModify.java

import database.DBConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class PolicyModify extends HttpServlet
{

    public PolicyModify()
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
            String pname = request.getParameter("pname").trim();
            int pterm = Integer.parseInt(request.getParameter("pterm").trim());
            int pamt = Integer.parseInt(request.getParameter("pamt").trim());
            int pfamt = Integer.parseInt(request.getParameter("pfamt").trim());
            int pint = Integer.parseInt(request.getParameter("pint").trim());
            int pbonperiod = Integer.parseInt(request.getParameter("pbonperiod").trim());
            int pbonrate = Integer.parseInt(request.getParameter("pbonrate").trim());
            Date d2 = Date.valueOf(request.getParameter("bdate").trim());
            con = DBConn.getConnection();
            pstmt = DBConn.prepareStatement((new StringBuilder("update policies set policyname=?,policyterm=?,policyamount=?,policyfaceamount=?,policyinterest=?, bonusperiod=?, bonusrate=? where policyid=")).append(id).toString());
            pstmt.setString(1, pname);
            pstmt.setInt(2, pterm);
            pstmt.setInt(3, pamt);
            pstmt.setInt(4, pfamt);
            pstmt.setInt(5, pint);
            pstmt.setInt(6, pbonperiod);
            pstmt.setInt(7, pbonrate);
            pstmt.execute();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successful Policy Modification : Policy ID is </I>")).append(id).toString());
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
