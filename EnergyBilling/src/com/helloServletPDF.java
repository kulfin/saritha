package com;

import java.io.*; 
import java.awt.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import com.lowagie.text.*; 
import com.lowagie.text.pdf.*; 
import com.lowagie.text.rtf.*; 
import com.lowagie.text.html.*; 


public class helloServletPDF extends HttpServlet 
{ 
public void doGet(HttpServletRequest req, HttpServletResponse res) 
throws IOException, ServletException { 
String str=req.getParameter("Text1"); 

Document document=new Document(); 

try 
{ 
if(str.equals("pdf")) 
{ 
res.setContentType("application/pdf"); 
PdfWriter.getInstance(document,res.getOutputStream()); 
} 
if(str.equals("rtf")) 
{ 
res.setContentType("text/rtf"); 
RtfWriter2.getInstance(document,res.getOutputStream()); 
} 
if(str.equals("html")) 
{ 
res.setContentType("text/html"); 
HtmlWriter.getInstance(document,res.getOutputStream()); 
} 
document.open(); 
document.add(new Paragraph("Hello World")); 
} 
catch(DocumentException de) 
{ 
de.printStackTrace(); 
System.err.println("document: " + de.getMessage()); 
} 
document.close(); 

} 
} 
