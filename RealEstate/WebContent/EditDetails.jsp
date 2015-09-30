<!--
	File : EditDetails.jsp
	Purpose : This jsp is modify the details of Employes by Authorised users.Only Authorised 
			  Users can Edit or Modify the details.

-->
<HTML>

<%@ page language="java" %>
<%@ page import="com.DBConnection"%>
<%@ page session="true" %>
<%@ page import="java.sql.*,java.io.*,java.util.Random"%>

<HEAD>
	<LINK href="styles.css" type="text/css" rel="stylesheet">

<TITLE></TITLE>

<Script language="JavaScript">
var i;

var PriSkillFlag=false;
var remarksFlag=false;


/* Functions to set Flags if any of the details get modified */
function setPriSkillFlag()
{
	PriSkillFlag=true;

}
function setRemarksFlag()
{
	remarksFlag=true;
}

/*Checking wether any fields get modified or not.*/
function Check(){
//  modification in condition
if(PriSkillFlag==true||remarksFlag==true)
	{
	  if(remarksFlag==false){
		window.alert("Remarks Field is mandatory...while modifying details");
		return false;
	  }
	  else
		return true;
	}
	else{
		window.alert("Not Modified any Fields...Press Cancel to exit");
		return false;
	}
}
</script>
</HEAD>
<body class= SC>
<%

/*Declaration of variables*/

Connection con=null;
Statement stmt=null;
ResultSet rs=null;

String filedata="";
String filestr="";
File file=null;
FileWriter fr=null;
Random rand = new Random(100000l);

String EmpName,EmpMailId,EmpBaseCity,Role;
String PrimarySkill,Remarks;
String strEmpNo = request.getParameter("txtEmpNo");
String strEmpBaseCity=request.getParameter("txtEmpBaseCity");
//System.out.println(strEmpBaseCity);
int EmpNo= Integer.parseInt(strEmpNo);
//System.out.println(EmpNo);
Date JoiningDate;
int Auth = ((Integer)session.getAttribute("auth")).intValue();

try
{

	/*Getting the connection variable from session*/
	con=DBConnection.getConnection();
	stmt =  con.createStatement();


	String Query = "SELECT * from empmaster where EMPNO="+EmpNo;


	//System.out.println(Query);
	rs = stmt.executeQuery(Query);
		
}
catch(Exception e)
{
	//System.out.println("Exception"+e);
}

if(rs!=null)
{
	/*Getting the values from the database*/

	while(rs.next())
	{
	  //System.out.println("----------");
      EmpName=rs.getString(1);
	  EmpNo=rs.getInt(2);
	  EmpMailId=rs.getString(3);
  	  EmpBaseCity=rs.getString(4);
	  JoiningDate=rs.getDate(5);
	  Role=rs.getString(6);
	  PrimarySkill=rs.getString(7);
	  Remarks=rs.getString(8);
	  	String RemarksBKUP = Remarks;
	if(Remarks==null) Remarks="";
	if(Remarks.equals("null")) Remarks="";
	  session.setAttribute("RemarksBKUP2",RemarksBKUP);
%>

<!-- If data in any fields get modified then control goes to modifydetails.jsp -->

<FORM action="ModifyDetails.jsp" method="post" name="ModForm" 
onSubmit='return Check()'>
	<H3 CLASS=Top> <%=EmpName%>(<%=EmpNo%>)</H3>
<TABLE>
	<TR class=row_odd><TD>EMPName</TD><TD><%if(EmpName==null)EmpName="-";%><%=EmpName%></TD>			
	<TR class=row_even><TD>Emp Number</TD><TD><%=EmpNo%></TD>
	<TR class=row_odd><TD>Email ID</TD><TD><%if(EmpMailId==null)EmpMailId="-";%><%=EmpMailId%></TD>
	<TR class=row_even><TD>CurrentLocation</TD><TD><%if(EmpBaseCity==null)EmpBaseCity="-";%><%=EmpBaseCity%></TD>
  	<TR class=row_odd><TD>JoiningDate</TD><TD><%=JoiningDate%></TD>
  	<TR class=row_even><TD>Role</TD><TD><%if(Role==null)Role="-";%><%=Role%></TD>
  	<TR class=row_odd>
		<TD>Primary Skill</TD>
		<TD><INPUT name=PrimarySkill type=text width="30" value="<%=PrimarySkill%>" onchange="setPriSkillFlag()"></TD>
	</TR>
	<TR class=row_even>
		<TD>Remarks</TD>
		<TD><INPUT name=Remarks type= text width="50" value="" onchange="setRemarksFlag()">(Enter new Remarks)
		<!--	<INPUT name=Remarks1 type= text width="30" value="<%=Remarks%>" DISABLED>  -->
			<TEXTAREA  WIDTH = "25" onchange='this.value="<%=Remarks%>"'> <%=Remarks%> </TEXTAREA>(Existing Remarks)
		</TD>
	</TR>
	<TR class=row_odd>
	<td>
		<input type="Submit" value="Modify" id="sid" >
	</td>
	<td>
		<input type="Button" value="Cancel" onclick='window.close()' >
	</td>
	</tr>

</TABLE>
		<INPUT name=EmpNo type=hidden width="30" value="<%=EmpNo%>">
		<INPUT name=EmpBaseCity type=hidden width="30" value="<%=EmpBaseCity%>">
</FORM></P>

<%
	}
	rs.close();
}
else
{
	/*To write to the server if the resultset is null*/
	//System.out.println("Result set is null\n");

}

%>
</BODY>
</HTML>
