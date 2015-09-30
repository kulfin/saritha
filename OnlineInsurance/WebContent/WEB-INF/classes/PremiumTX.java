// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PremiumTX.java

import database.DBConn;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class PremiumTX extends HttpServlet
{

    public PremiumTX()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        javax.servlet.http.HttpSession sess = req.getSession();
        try
        {
            System.out.println("before  attribute  this is premiumtx servlet.");
            String cobj = req.getParameter("cupid");
            int cpid = Integer.parseInt(cobj);
            System.out.println((new StringBuilder("this is premiumtx servlet and customerpoliocy id is....")).append(cpid).toString());
            int pamt = Integer.parseInt(req.getParameter("paidamt").trim());
            Date d2 = Date.valueOf(req.getParameter("bdate").trim());
            con = DBConn.getConnection();
            rs = DBConn.executeQuery("select max(custpremiumtxid) from custpremiumtx");
            System.out.println((new StringBuilder("resultsetttttt :::  ")).append(rs).toString());
            if(rs.next())
            {
                pid = rs.getInt(1);
                if(pid == 0)
                    pid = 0x30d3f;
                System.out.println((new StringBuilder("if 1loop")).append(pid).toString());
                pid++;
                System.out.println((new StringBuilder("if loop")).append(pid).toString());
            } else
            {
                pid = 0x30d40;
                System.out.println((new StringBuilder("else loop")).append(pid).toString());
            }
            pstmt = DBConn.prepareStatement("insert into custpremiumtx values(?,?,?,?)");
            pstmt.setInt(1, pid);
            pstmt.setDate(4, d2);
            pstmt.setInt(2, cpid);
            pstmt.setInt(3, pamt);
            pstmt.execute();
            pstmt.close();
            out.println((new StringBuilder("<body bgcolor='#A3A3D1'><center><h1><B><I>Successful Policy Premium Payment: CustPremiumTXID is </I>")).append(pid).toString());
            out.print("</B></h1></center></body>");
            System.out.println("***************************************************************************************");
            con1 = DBConn.getConnection();
            rs1 = DBConn.executeQuery((new StringBuilder("select * from custpolicies where custpolicyid='")).append(cpid).append("' ").toString());
            System.out.println((new StringBuilder("resultsetttttt11111111 :::  ")).append(rs1).toString());
            System.out.println("***OK***");
            while(rs1.next()) 
            {
                custid = rs1.getInt(2);
                agid = rs1.getInt(9);
            }
            System.out.println((new StringBuilder("cust id is")).append(custid).toString());
            agcommision = pamt / 10;
            System.out.println(agcommision);
            con2 = DBConn.getConnection();
            for(rs2 = DBConn.executeQuery((new StringBuilder("select * from agentcommtx where agentid=")).append(agid).append(" ").toString()); rs2.next();)
                agamount = rs2.getInt(3);

            agamount += agcommision;
            System.out.println(agamount);
            System.out.println("before pstmt1....");
            System.out.println((new StringBuilder("pid=")).append(pid).toString());
            System.out.println((new StringBuilder("agamount=")).append(agamount).toString());
            System.out.println((new StringBuilder("agentComm=")).append(agcommision).toString());
            System.out.println((new StringBuilder("todays date=")).append(d2).toString());
            pstmt1 = DBConn.prepareStatement("insert into agentcommtx values(?,?,?,?,?)");
            pstmt1.setInt(1, pid);
            pstmt1.setInt(2, agid);
            pstmt1.setInt(3, agamount);
            pstmt1.setInt(4, agcommision);
            pstmt1.setDate(5, null);
            System.out.println("after pstmt1....");
            pstmt1.execute();
            pstmt1.close();
            System.out.println("***************************************************************************************");
            DBConn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("in premiumtx insert reg");
            out.println("<body bgcolor='#A3A3D1'><center><h1><B><I>UNSuccessful Policy Premium Payment Process:</I>");
            out.print("</B></h1></center></body>");
        }
    }

    Connection con;
    Connection con1;
    Connection con2;
    ResultSet rs;
    ResultSet rs1;
    ResultSet rs2;
    ResultSet rs3;
    PreparedStatement pstmt;
    PreparedStatement pstmt1;
    Statement stmt;
    int pid;
    int agid;
    int agcommision;
    int agamount;
    int custid;
}
