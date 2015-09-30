// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AgentRegInsert.java

import database.DBConn;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AgentRegInsert extends HttpServlet
{

    public AgentRegInsert()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try
        {
            String agentname = req.getParameter("agentname").trim();
            String agentfname = req.getParameter("agentfname").trim();
            int agentage = Integer.parseInt(req.getParameter("agentage").trim());
            String asex = req.getParameter("asex").trim();
            String agentqual = req.getParameter("agentqual").trim();
            String agentoccup = req.getParameter("agentoccup").trim();
            String agentaddr = req.getParameter("agentaddr").trim();
            String agentpwd = req.getParameter("agentpwd").trim();
            String mbname = req.getParameter("mbname").trim();
            int agentsecurity = Integer.parseInt(req.getParameter("agentsecurity").trim());
            Date d2 = Date.valueOf(req.getParameter("bdate").trim());
            con = DBConn.getConnection();
            rs1 = DBConn.executeQuery((new StringBuilder("select branchmgrid from branchmgr where branchmgrname='")).append(mbname).append("'").toString());
            if(rs1.next())
            {
                branchid = rs1.getInt(1);
                System.out.println((new StringBuilder("branchmgrid :::::")).append(branchid).toString());
            } else
            {
                System.out.println("branchmgrid exception");
            }
            rs1.close();
            rs = DBConn.executeQuery("select max(agentid) from agents");
            if(rs.next())
            {
                bid = rs.getInt(1);
                if(bid == 0)
                    bid = 2999;
                System.out.println((new StringBuilder("if 1loop")).append(bid).toString());
                bid++;
                System.out.println((new StringBuilder("if loop")).append(bid).toString());
            } else
            {
                bid = 3000;
                System.out.println((new StringBuilder("else loop")).append(bid).toString());
            }
            pstmt = DBConn.prepareStatement("insert into agents values(?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, bid);
            pstmt.setString(2, agentname);
            pstmt.setString(3, agentfname);
            pstmt.setInt(4, agentage);
            pstmt.setString(5, asex);
            pstmt.setString(6, agentqual);
            pstmt.setString(7, agentoccup);
            pstmt.setString(8, agentaddr);
            pstmt.setDate(9, d2);
            pstmt.setInt(10, agentsecurity);
            pstmt.setInt(11, branchid);
            pstmt.execute();
            pstmt.close();
            pstmt = DBConn.prepareStatement("insert into login values(?,?,?)");
            pstmt.setInt(1, bid);
            pstmt.setString(2, agentpwd);
            pstmt.setString(3, "AGENT");
            pstmt.execute();
            pstmt.close();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successfully Agent Registered : AgentID is </I>")).append(bid).toString());
            out.print("</B></h1></center></body>");
            DBConn.close();
        }
        catch(Exception e)
        {
            System.out.println("in agentreg");
            e.printStackTrace();
            System.out.println("in agentreg");
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
