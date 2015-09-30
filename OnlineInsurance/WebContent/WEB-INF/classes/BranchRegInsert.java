// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BranchRegInsert.java

import database.DBConn;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class BranchRegInsert extends HttpServlet
{

    public BranchRegInsert()
    {
    }

    public void service(HttpServletRequest request, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try
        {
            String bname = request.getParameter("bname").trim();
            String location = request.getParameter("location").trim();
            int phone = Integer.parseInt(request.getParameter("phone").trim());
            String state = request.getParameter("state").trim();
            Date d2 = Date.valueOf(request.getParameter("bdate").trim());
            con = DBConn.getConnection();
            rs = DBConn.executeQuery("select max(branchid) from branch");
            if(rs.next())
            {
                id = rs.getInt(1);
                if(id == 0)
                    id = 499;
                System.out.println((new StringBuilder("if 1loop")).append(id).toString());
                id++;
                System.out.println((new StringBuilder("if loop")).append(id).toString());
            } else
            {
                id = 500;
                System.out.println((new StringBuilder("else loop")).append(id).toString());
            }
            pstmt = DBConn.prepareStatement("insert into branch values(?,?,?,?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, bname);
            pstmt.setString(3, location);
            pstmt.setDate(4, d2);
            pstmt.setInt(5, phone);
            pstmt.setString(6, state);
            pstmt.execute();
            out.println((new StringBuilder("<html> <body bgcolor='#A3A3D1'><center><h1><B><I>Successfully Branch Registered : Branch ID is </I>")).append(id).toString());
            out.print("</B></h1></center></body></html>");
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
