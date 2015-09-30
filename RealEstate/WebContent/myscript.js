function fnPopUp(strUrl)
{ 
var intLeft=0;
var strTitle ='Details';
	intLeft = screen.availWidth - 550;
var strOptions= 'menubar=no,toolbar=no,scrollbars=yes,resizable=No,height=500,width=350,left=' + (intLeft)  + ',top=0';
fnNewDetailsPopUp(strUrl,strTitle,strOptions);
}	
function fnNewDetailsPopUp(strUrl,strTitle,strOptions)
{
	var win = window.open(strUrl,strTitle,strOptions).focus();
}
