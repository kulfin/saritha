// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AgentModify.java

import database.DBConn;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AgentModify extends HttpServlet
{

    public AgentModify()
    {
    }

    public void service(HttpServletRequest request, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try
        {
            id = Integer.parseInt(request.getParameter("agentid").trim());
            String aname = request.getParameter("agentname").trim();
            String afname = request.getParameter("agentfname").trim();
            int aage = Integer.parseInt(request.getParameter("agentage").trim());
            String asex = request.getParameter("asex").trim();
            String aaddr = request.getParameter("agentaddr").trim();
            String aqual = request.getParameter("agentqual").trim();
            String apwd = request.getParameter("agentpwd").trim();
            String mbname = request.getParameter("mbname").trim();
            String aoccup = request.getParameter("agentoccup").trim();
            int asecurity = Integer.parseInt(request.getParameter("agentsecurity").trim());
            Date d2 = Date.valueOf(request.getParameter("bdate").trim());
            con = DBConn.getConnection();
            
          //  Statement st=con.createStatement();
            pstmt = con.prepareStatement((new StringBuilder("update agents set agentname=?,agentfname=?,agentage=?,agentsex=?,agentqual=?,agentoccupation=?,agentaddress=?,agentsecuritydeposit=?,branchmgrid=? where agentid=")).append(id).toString());
            PreparedStatement pstmt1 = con.prepareStatement((new StringBuilder("update login set password=? where userid=")).append(id).toString());
            pstmt.setString(1, aname);
            pstmt.setString(2, afname);
            pstmt.setInt(3, aage);
            pstmt.setString(4, asex);
            pstmt.setString(5, aqual);
            pstmt.setString(6, aoccup);
            pstmt.setString(7, aaddr);
            pstmt.setInt(8, asecurity);
            
            rs = pstmt.executeQuery((new StringBuilder("select branchmgrid from branchmgr where branchmgrname='")).append(mbname).append("'").toString());
            if(rs.next())
            {
                mgrid = rs.getInt(1);
                System.out.println((new StringBuilder(" agnet modify mgrid :::")).append(mgrid).toString());
            } else
            {
                System.out.println(" agnet modify mgrid else part");
            }
            pstmt.setInt(9, mgrid);
            pstmt.execute();
            pstmt1.setString(1, apwd);
            pstmt1.execute();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successful Agent Modification : AgentID is </I>")).append(id).toString());
            out.print("</B></h1></center></body>");
           // DBConn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    int id;
    int mgrid;
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
}
