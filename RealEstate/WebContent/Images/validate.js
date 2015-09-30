
function validateText(x){
 var ed=x.value;
 var pattern = /^([a-zA-Z0-9\_\. ]{4,25})$/;
 if(pattern.test(ed)) 
  return true;
  else
    return false;
 }

function validateNumber(x){
  	var ed=x.value;
 var pattern = /^([0-9]{5,7})$/;
 if(pattern.test(ed)) 
  return true;
  else
    return false;
 }
function validateEmail(x){
  	var ed=x.value;
 var pattern = /^[a-zA-Z0-9\_\.]+\@[a-zA-Z\.]+\.([a-z]{2,4})$/;
 if(pattern.test(ed)) 
  return true;
  else
    return false;
 }
// Checking wether starting date is lessthan ending date or not
function validatePeriod(x,y){
  var sd=x.value;
  var yy=sd.substr(0,4);
  var mm=sd.substr(5,2);
  var dd=sd.substr(8,2);
  var sdobj = new Date(yy,mm,dd);

  var ed=y.value;
  var yy=ed.substr(0,4);
  var mm=ed.substr(5,2);
  var dd=ed.substr(8,2);
  var edobj = new Date(yy,mm,dd);

  if(sdobj<edobj) 
    return true;
  else{
	alert("Warning : Start date should not exceed end date");
    return false;
	}
 }
//Validating date format (yyyy-mm-dd)
function validateDate(x,y,z){
  	var yyyy=x.value;
	var mm=y.value;
	var dd=z.value;
	var ed=yyyy+"-"+mm+"-"+dd;

 var pattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
 if(!pattern.test(ed)) 
  alert("Invalid Format");
  }
function check(){
	  var name,empno,email,loc,jdateY,jdateM,jdateD,bsdateY,bsdateM,bsdateD,bage,rcap,entry;
	  var domain,pskill,sskill,visa,status,rem,adate,pblkd,pstatus;
	  var F = document.AddEmpForm; 
	  name=F.EMPName;
	  empno=F.EMPNO;
	  email=F.EmpEmailID;
	  
	  jdateY=F.JoiningDateYYYY;
	  jdateM=F.JoiningDateMM;
	  jdateD=F.JoiningDateDD;

		bname=validateText(name);
		bemail=validateEmail(email);
		bempno=validateNumber(empno);
	
		var str="Either [ ";		 
		if(bname == false) str+=" 'Name' ";
		if(bempno==false) str+=" 'Emp Number' ";
		if(bemail==false) str+=" 'email' ";
		 str+=" ] fields are empty or not entered properly "
	if(bname==true && bempno==true && bemail==true)
		return true;
	else
		{
			alert(str);
		    return false;
		}
	}
