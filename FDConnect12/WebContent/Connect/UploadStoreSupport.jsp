<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.sql.*"%>

<%	String contentType = request.getContentType();
	if ((contentType != null)
			&& (contentType.indexOf("multipart/form-data") >= 0)) {
		DataInputStream in = new DataInputStream(request.getInputStream());
		int formDataLength = request.getContentLength();
		System.out.println(formDataLength);
		byte dataBytes[] = new byte[formDataLength];
		System.out.println(dataBytes[0]);
		int byteRead = 0;
		int totalBytesRead = 0;

		while (totalBytesRead < formDataLength) {
			byteRead = in.read(dataBytes, totalBytesRead,formDataLength);
			totalBytesRead += byteRead;
		}
		String file = new String(dataBytes);
		String saveFile = file
				.substring(file.indexOf("filename=\"") + 10);
		System.out.println("saveFile=" + saveFile);
		saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
				saveFile.indexOf("\""));
		System.out.println("saveFile" + saveFile);
		saveFile = file.substring(file.indexOf("filename=\"") + 10);
		saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
		saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
				saveFile.indexOf("\""));
		int lastIndex = contentType.lastIndexOf("=");
		String boundary = contentType.substring(lastIndex + 1,contentType.length());
		int pos;

		pos = file.indexOf("filename=\"");
		pos = file.indexOf("\n", pos) + 1;
		pos = file.indexOf("\n", pos) + 1;
		pos = file.indexOf("\n", pos) + 1;
		int boundaryLocation = file.indexOf(boundary, pos) - 4;
		int startPos = ((file.substring(0, pos)).getBytes()).length;
		int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
		System.out.println("startPos"+startPos);
		System.out.println("endPos"+endPos);
		FileOutputStream fileOut = new FileOutputStream(saveFile);
		fileOut.write(dataBytes, startPos, (endPos - startPos));
		Connection con = null;
		Statement pst = null;
		String line = null;
		String value = null;
		int n=0;
		try {
			StringBuilder contents = new StringBuilder();
			BufferedReader input = new BufferedReader(new FileReader(
					saveFile));
			while ((line = input.readLine()) != null) {
				System.out.println("files"+line);
			String[] args=line.split(",");	
			
			System.out.println("Value:" + args[0]);
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/terrier", "root", "root");
			PreparedStatement ps=con.prepareStatement("insert into terrier.units(UnitCode,UnitName,Location,EmpCode,Designation) values('"+args[0]+ "','"+args[1]+"','"+args[2]+"','"+args[3]+"','"+args[4]+"')");
			 n=ps.executeUpdate();
			
			}
			if(n>0)
			{
				System.out.println("Inserted");
				session.setAttribute("uploadunit","uploaded");
				response.sendRedirect("AddUnit.jsp");
				
			}
			else
			{	session.setAttribute("uploadunit","notuploaded");
				response.sendRedirect("AddUnit.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(n>0)
			{
				System.out.println("Inserted");
				session.setAttribute("uploadunit","uploaded");
				response.sendRedirect("AddUnit.jsp");
				
			}
			else
			{	session.setAttribute("uploadunit","notuploaded");
				response.sendRedirect("AddUnit.jsp");
			}
		}
	}
%>
</html>