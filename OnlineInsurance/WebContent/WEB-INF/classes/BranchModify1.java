// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BranchModify1.java

import database.DBConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class BranchModify1 extends HttpServlet
{

    public BranchModify1()
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
            String bname = request.getParameter("bname").trim();
            String location = request.getParameter("location").trim();
            int phone = Integer.parseInt(request.getParameter("phone").trim());
            String state = request.getParameter("state").trim();
            Date d2 = Date.valueOf(request.getParameter("bdate").trim());
            con = DBConn.getConnection();
            pstmt = DBConn.prepareStatement((new StringBuilder("update branch set branchname=?,location=?,branchdate=?,phone=?,state=? where branchid=")).append(id).toString());
            pstmt.setString(1, bname);
            pstmt.setString(2, location);
            pstmt.setDate(3, d2);
            pstmt.setInt(4, phone);
            pstmt.setString(5, state);
            pstmt.execute();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successful Branch Modification: Branch ID is </I>")).append(id).toString());
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
