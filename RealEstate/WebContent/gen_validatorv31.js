/*
  -------------------------------------------------------------------------
		      JavaScript Form Validator (gen_validatorv31.js)
              Version 3.1.2
	Copyright (C) 2003-2008 JavaScript-Coder.com. All rights reserved.
	You can freely use this script in your Web pages.
	You may adapt this script for your own needs, provided these opening credit
    lines are kept intact.
		
	The Form validation script is distributed free from JavaScript-Coder.com
	For updates, please visit:
	http://www.javascript-coder.com/html-form/javascript-form-validation.phtml
	
	Questions & comments please send to form.val at javascript-coder.com
  -------------------------------------------------------------------------  
 */
function Validator(frmname) {
	this.formobj = document.forms[frmname];
	if (!this.formobj) {
		alert("Error: couldnot get Form object " + frmname);
		return;
	}
	if (this.formobj.onsubmit) {
		this.formobj.old_onsubmit = this.formobj.onsubmit;
		this.formobj.onsubmit = null;
	} else {
		this.formobj.old_onsubmit = null;
	}
	this.formobj._sfm_form_name = frmname;
	this.formobj.onsubmit = form_submit_handler;
	this.addValidation = add_validation;
	this.setAddnlValidationFunction = set_addnl_vfunction;
	this.clearAllValidations = clear_all_validations;
	this.disable_validations = false;//new
	document.error_disp_handler = new sfm_ErrorDisplayHandler();
	this.EnableOnPageErrorDisplay = validator_enable_OPED;
	this.EnableOnPageErrorDisplaySingleBox = validator_enable_OPED_SB;
	this.show_errors_together = true;
	this.EnableMsgsTogether = sfm_enable_show_msgs_together;
	document.set_focus_onerror = true;
	this.EnableFocusOnError = sfm_validator_enable_focus;

}

function sfm_validator_enable_focus(enable) {
	document.set_focus_onerror = enable;
}

function set_addnl_vfunction(functionname) {
	this.formobj.addnlvalidation = functionname;
}

function sfm_set_focus(objInput) {
	if (document.set_focus_onerror) {
		objInput.focus();
	}
}

function sfm_enable_show_msgs_together() {
	this.show_errors_together = true;
	this.formobj.show_errors_together = true;
}
function clear_all_validations() {
	for ( var itr = 0; itr < this.formobj.elements.length; itr++) {
		this.formobj.elements[itr].validationset = null;
	}
}

function form_submit_handler() {
	var bRet = true;
	document.error_disp_handler.clear_msgs();
	for ( var itr = 0; itr < this.elements.length; itr++) {
		if (this.elements[itr].validationset
				&& !this.elements[itr].validationset.validate()) {
			bRet = false;
		}
		if (!bRet && !this.show_errors_together) {
			break;

		}
	}

	if (this.addnlvalidation) {
		str = " var ret = " + this.addnlvalidation + "()";
		eval(str);

		if (!ret) {
			bRet = false;
		}

	}

	if (!bRet) {
		document.error_disp_handler.FinalShowMsg();
		return false;
	}
	return true;
}

function add_validation(itemname, descriptor, errstr) {
	var condition = null;
	if (arguments.length > 3) {
		condition = arguments[3];
	}
	if (!this.formobj) {
		alert("Error: The form object is not set properly");
		return;
	}//if
	var itemobj = this.formobj[itemname];
	if (itemobj.length && isNaN(itemobj.selectedIndex))
	//for radio button; don't do for 'select' item
	{
		itemobj = itemobj[0];
	}
	if (!itemobj) {
		alert("Error: Couldnot get the input object named: " + itemname);
		return;
	}
	if (!itemobj.validationset) {
		itemobj.validationset = new ValidationSet(itemobj,
				this.show_errors_together);
	}
	itemobj.validationset.add(descriptor, errstr, condition);
	itemobj.validatorobj = this;
}
function validator_enable_OPED() {
	document.error_disp_handler.EnableOnPageDisplay(false);
}

function validator_enable_OPED_SB() {
	document.error_disp_handler.EnableOnPageDisplay(true);
}
function sfm_ErrorDisplayHandler() {
	this.msgdisplay = new AlertMsgDisplayer();
	this.EnableOnPageDisplay = edh_EnableOnPageDisplay;
	this.ShowMsg = edh_ShowMsg;
	this.FinalShowMsg = edh_FinalShowMsg;
	this.all_msgs = new Array();
	this.clear_msgs = edh_clear_msgs;
}
function edh_clear_msgs() {
	this.msgdisplay.clearmsg(this.all_msgs);
	this.all_msgs = new Array();
}
function edh_FinalShowMsg() {
	this.msgdisplay.showmsg(this.all_msgs);
}
function edh_EnableOnPageDisplay(single_box) {
	if (true == single_box) {
		this.msgdisplay = new SingleBoxErrorDisplay();
	} else {
		this.msgdisplay = new DivMsgDisplayer();
	}
}
function edh_ShowMsg(msg, input_element) {

	var objmsg = new Array();
	objmsg["input_element"] = input_element;
	objmsg["msg"] = msg;
	this.all_msgs.push(objmsg);
}
function AlertMsgDisplayer() {
	this.showmsg = alert_showmsg;
	this.clearmsg = alert_clearmsg;
}
function alert_clearmsg(msgs) {

}
function alert_showmsg(msgs) {
	var whole_msg = "";
	var first_elmnt = null;
	for ( var m = 0; m < msgs.length; m++) {
		if (null == first_elmnt) {
			first_elmnt = msgs[m]["input_element"];
		}
		whole_msg += msgs[m]["msg"] + "\n";
	}

	alert(whole_msg);

	if (null != first_elmnt) {
		sfm_set_focus(first_elmnt);
	}
}
function sfm_show_error_msg(msg, input_elmt) {
	document.error_disp_handler.ShowMsg(msg, input_elmt);
}
function SingleBoxErrorDisplay() {
	this.showmsg = sb_div_showmsg;
	this.clearmsg = sb_div_clearmsg;
}

function sb_div_clearmsg(msgs) {
	var divname = form_error_div_name(msgs);
	show_div_msg(divname, "");
}

function sb_div_showmsg(msgs) {
	var whole_msg = "<ul>\n";
	for ( var m = 0; m < msgs.length; m++) {
		whole_msg += "<li>" + msgs[m]["msg"] + "</li>\n";
	}
	whole_msg += "</ul>";
	var divname = form_error_div_name(msgs);
	show_div_msg(divname, whole_msg);
}
function form_error_div_name(msgs) {
	var input_element = null;

	for ( var m in msgs) {
		input_element = msgs[m]["input_element"];
		if (input_element) {
			break;
		}
	}

	var divname = "";
	if (input_element) {
		divname = input_element.form._sfm_form_name + "_errorloc";
	}

	return divname;
}
function DivMsgDisplayer() {
	this.showmsg = div_showmsg;
	this.clearmsg = div_clearmsg;
}
function div_clearmsg(msgs) {
	for ( var m in msgs) {
		var divname = element_div_name(msgs[m]["input_element"]);
		show_div_msg(divname, "");
	}
}
function element_div_name(input_element) {
	var divname = input_element.form._sfm_form_name + "_" + input_element.name
			+ "_errorloc";

	divname = divname.replace(/[\[\]]/gi, "");

	return divname;
}
function div_showmsg(msgs) {
	var whole_msg;
	var first_elmnt = null;
	for ( var m in msgs) {
		if (null == first_elmnt) {
			first_elmnt = msgs[m]["input_element"];
		}
		var divname = element_div_name(msgs[m]["input_element"]);
		show_div_msg(divname, msgs[m]["msg"]);
	}
	if (null != first_elmnt) {
		sfm_set_focus(first_elmnt);
	}
}
function show_div_msg(divname, msgstring) {
	if (divname.length <= 0)
		return false;

	if (document.layers) {
		divlayer = document.layers[divname];
		if (!divlayer) {
			return;
		}
		divlayer.document.open();
		divlayer.document.write(msgstring);
		divlayer.document.close();
	} else if (document.all) {
		divlayer = document.all[divname];
		if (!divlayer) {
			return;
		}
		divlayer.innerHTML = msgstring;
	} else if (document.getElementById) {
		divlayer = document.getElementById(divname);
		if (!divlayer) {
			return;
		}
		divlayer.innerHTML = msgstring;
	}
	divlayer.style.visibility = "visible";
}

function ValidationDesc(inputitem,desc,error,condition)
{
  this.desc=desc;
	this.error=error;
	this.itemobj = inputitem;
	this.condition = condition;
	this.validate=vdesc_validate;
}
function vdesc_validate()
{
	if(this.condition != null )
	{
		if(!eval(this.condition))
		{
			return true;
		}
	}
	if(!validateInput(this.desc,this.itemobj,this.error))
	{
		this.itemobj.validatorobj.disable_validations=true;

		sfm_set_focus(this.itemobj);

		return false;
	}
	return true;
}
function ValidationSet(inputitem,msgs_together)
{
    this.vSet=new Array();
	this.add= add_validationdesc;
	this.validate= vset_validate;
	this.itemobj = inputitem;
    this.msgs_together = msgs_together;
}
function add_validationdesc(desc,error,condition)
{
  this.vSet[this.vSet.length]= 
  new ValidationDesc(this.itemobj,desc,error,condition);
}
function vset_validate()
{
    var bRet = true;
    for(var itr=0;itr<this.vSet.length;itr++)
    {
        bRet = bRet && this.vSet[itr].validate();
        if(!bRet && !this.msgs_together)
        {
            break;
        }
    }
    return bRet;
}
function validateEmail(email)
{
    var splitted = email.match("^(.+)@(.+)$");
    if(splitted == null) return false;
    if(splitted[1] != null )
    {
      var regexp_user=/^\"?[\w-_\.]*\"?$/;
      if(splitted[1].match(regexp_user) == null) return false;
    }
    if(splitted[2] != null)
    {
      var regexp_domain=/^[\w-\.]*\.[A-Za-z]{2,4}$/;
      if(splitted[2].match(regexp_domain) == null) 
      {
	    var regexp_ip =/^\[\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\]$/;
	    if(splitted[2].match(regexp_ip) == null) return false;
      }// if
      return true;
    }
return false;
}

function IsCheckSelected(objValue,chkValue)
{
    var selected=false;
	var objcheck = objValue.form.elements[objValue.name];
    if(objcheck.length)
	{
		var idxchk=-1;
		for(var c=0;c < objcheck.length;c++)
		{
		   if(objcheck[c].value == chkValue)
		   {
		     idxchk=c;
			 break;
		   }//if
		}//for
		if(idxchk>= 0)
		{
		  if(objcheck[idxchk].checked=="1")
		  {
		    selected=true;
		  }
		}//if
	}
	else
	{
		if(objValue.checked == "1")
		{
			selected=true;
		}//if
	}//else	

	return selected;
}
function TestDontSelectChk(objValue,chkValue,strError)
{
	var pass = true;
	pass = IsCheckSelected(objValue,chkValue)?false:true;

	if(pass==false)
	{
     if(!strError || strError.length ==0) 
        { 
        	strError = "Can't Proceed as you selected "+objValue.name;  
        }//if			  
	  sfm_show_error_msg(strError,objValue);
	  
	}
    return pass;
}
function TestShouldSelectChk(objValue,chkValue,strError)
{
	var pass = true;

	pass = IsCheckSelected(objValue,chkValue)?true:false;

	if(pass==false)
	{
     if(!strError || strError.length ==0) 
        { 
        	strError = "You should select "+objValue.name;  
        }//if			  
	  sfm_show_error_msg(strError,objValue);
	  
	}
    return pass;
}
function TestRequiredInput(objValue,strError)
{
 var ret = true;
 var val = objValue.value;
 val = val.replace(/^\s+|\s+$/g,"");//trim
    if(eval(val.length) == 0) 
    { 
       if(!strError || strError.length ==0) 
       { 
         strError = objValue.name + " : Required Field"; 
       }//if 
       sfm_show_error_msg(strError,objValue); 
       ret=false; 
    }//if 
return ret;
}
function TestMaxLen(objValue,strMaxLen,strError)
{
 var ret = true;
    if(eval(objValue.value.length) > eval(strMaxLen)) 
    { 
      if(!strError || strError.length ==0) 
      { 
        strError = objValue.name + " : "+ strMaxLen +" characters maximum "; 
      }//if 
      sfm_show_error_msg(strError,objValue); 
      ret = false; 
    }//if 
return ret;
}
function TestMinLen(objValue,strMinLen,strError)
{
 var ret = true;
    if(eval(objValue.value.length) <  eval(strMinLen)) 
    { 
      if(!strError || strError.length ==0) 
      { 
        strError = objValue.name + " : " + strMinLen + " characters minimum  "; 
      }//if               
      sfm_show_error_msg(strError,objValue); 
      ret = false;   
    }//if 
return ret;
}
function TestInputType(objValue,strRegExp,strError,strDefaultError)
{
   var ret = true;

    var charpos = objValue.value.search(strRegExp); 
    if(objValue.value.length > 0 &&  charpos >= 0) 
    { 
     if(!strError || strError.length ==0) 
      { 
        strError = strDefaultError;
      }//if 
      sfm_show_error_msg(strError,objValue); 
      ret = false; 
    }//if 
 return ret;
}
function TestEmail(objValue,strError)
{
var ret = true;
     if(objValue.value.length > 0 && !validateEmail(objValue.value)	 ) 
     { 
       if(!strError || strError.length ==0) 
       { 
          strError = objValue.name+": Enter a valid Email address "; 
       }//if                                               
       sfm_show_error_msg(strError,objValue); 
       ret = false; 
     }//if 
return ret;
}
function TestLessThan(objValue,strLessThan,strError)
{
var ret = true;
	  if(isNaN(objValue.value)) 
	  { 
	    sfm_show_error_msg(objValue.name +": Should be a number ",objValue); 
	    ret = false; 
	  }//if 
	  else
	  if(eval(objValue.value) >=  eval(strLessThan)) 
	  { 
	    if(!strError || strError.length ==0) 
	    { 
	      strError = objValue.name + " : value should be less than "+ strLessThan; 
	    }//if               
	    sfm_show_error_msg(strError,objValue); 
	    ret = false;                 
	   }//if   
return ret;          
}
function TestGreaterThan(objValue,strGreaterThan,strError)
{
var ret = true;
     if(isNaN(objValue.value)) 
     { 
       sfm_show_error_msg(objValue.name+": Should be a number ",objValue); 
       ret = false; 
     }//if 
	 else
     if(eval(objValue.value) <=  eval(strGreaterThan)) 
      { 
        if(!strError || strError.length ==0) 
        { 
          strError = objValue.name + " : value should be greater than "+ strGreaterThan; 
        }//if               
        sfm_show_error_msg(strError,objValue);  
        ret = false;
      }//if  
return ret;           
}
function TestRegExp(objValue,strRegExp,strError)
{
var ret = true;
    if( objValue.value.length > 0 && 
        !objValue.value.match(strRegExp) ) 
    { 
      if(!strError || strError.length ==0) 
      { 
        strError = objValue.name+": Invalid characters found "; 
      }//if                                                               
      sfm_show_error_msg(strError,objValue); 
      ret = false;                   
    }//if 
return ret;
}
function TestDontSelect(objValue,dont_sel_index,strError)
{
var ret = true;
    if(objValue.selectedIndex == null) 
    { 
      sfm_show_error_msg("ERROR: dontselect command for non-select Item"); 
      ret =  false; 
    } 
    if(objValue.selectedIndex == eval(dont_sel_index)) 
    { 
     if(!strError || strError.length ==0) 
      { 
      strError = objValue.name+": Please Select one option "; 
      }//if                                                               
      sfm_show_error_msg(strError,objValue); 
      ret =  false;                                   
     } 
return ret;
}
function TestSelectOneRadio(objValue,strError)
{
	var objradio = objValue.form.elements[objValue.name];
	var one_selected=false;
	for(var r=0;r < objradio.length;r++)
	{
	  if(objradio[r].checked)
	  {
	  	one_selected=true;
		break;
	  }
	}
	if(false == one_selected)
	{
      if(!strError || strError.length ==0) 
       {
	    strError = "Please select one option from "+objValue.name;
	   }	
	  sfm_show_error_msg(strError,objValue);
	}
return one_selected;
}

function validateInput(strValidateStr,objValue,strError) 
{ 
    var ret = true;
    var epos = strValidateStr.search("="); 
    var  command  = ""; 
    var  cmdvalue = ""; 
    if(epos >= 0) 
    { 
     command  = strValidateStr.substring(0,epos); 
     cmdvalue = strValidateStr.substr(epos+1); 
    } 
    else 
    { 
     command = strValidateStr; 
    } 
    switch(command) 
    { 
        case "req": 
        case "required": 
         { 
		   ret = TestRequiredInput(objValue,strError)
           break;             
         }//case required 
        case "maxlength": 
        case "maxlen": 
          { 
			 ret = TestMaxLen(objValue,cmdvalue,strError)
             break; 
          }//case maxlen 
        case "minlength": 
        case "minlen": 
           { 
			 ret = TestMinLen(objValue,cmdvalue,strError)
             break; 
            }//case minlen 
        case "alnum": 
        case "alphanumeric": 
           { 
				ret = TestInputType(objValue,"[^A-Za-z0-9]",strError, 
						objValue.name+": Only alpha-numeric characters allowed ");
				break; 
           }
        case "alnum_s": 
        case "alphanumeric_space": 
           { 
				ret = TestInputType(objValue,"[^A-Za-z0-9\\s]",strError, 
						objValue.name+": Only alpha-numeric characters and space allowed ");
				break; 
           }		   
        case "num": 
        case "numeric": 
           { 
                ret = TestInputType(objValue,"[^0-9]",strError, 
						objValue.name+": Only digits allowed ");
                break;               
           }
        case "dec": 
        case "decimal": 
           { 
                ret = TestInputType(objValue,"[^0-9\.]",strError, 
						objValue.name+": Only numbers allowed ");
                break;               
           }
        case "alphabetic": 
        case "alpha": 
           { 
                ret = TestInputType(objValue,"[^A-Za-z]",strError, 
						objValue.name+": Only alphabetic characters allowed ");
                break; 
           }
        case "alphabetic_space": 
        case "alpha_s": 
           { 
                ret = TestInputType(objValue,"[^A-Za-z\\s]",strError, 
						objValue.name+": Only alphabetic characters and space allowed ");
                break; 
           }
        case "email": 
          { 
			   ret = TestEmail(objValue,strError);
               break; 
          }
        case "lt": 
        case "lessthan": 
         { 
    	      ret = TestLessThan(objValue,cmdvalue,strError);
              break; 
         }
        case "gt": 
        case "greaterthan": 
         { 
			ret = TestGreaterThan(objValue,cmdvalue,strError);
            break; 
         }//case greaterthan 
        case "regexp": 
         { 
			ret = TestRegExp(objValue,cmdvalue,strError);
           break; 
         }
        case "dontselect": 
         { 
			 ret = TestDontSelect(objValue,cmdvalue,strError)
             break; 
         }
		case "dontselectchk":
		{
			ret = TestDontSelectChk(objValue,cmdvalue,strError)
			break;
		}
		case "shouldselchk":
		{
			ret = TestShouldSelectChk(objValue,cmdvalue,strError)
			break;
		}
		case "selone_radio":
		{
			ret = TestSelectOneRadio(objValue,strError);
		    break;
		}		 
    }//switch 
	return ret;
}
function VWZ_IsListItemSelected(listname,value)
{
 for(var i=0;i < listname.options.length;i++)
 {
  if(listname.options[i].selected == true &&
   listname.options[i].value == value) 
   {
     return true;
   }
 }
 return false;
}
function VWZ_IsChecked(objcheck,value)
{
 if(objcheck.length)
 {
     for(var c=0;c < objcheck.length;c++)
     {
       if(objcheck[c].checked == "1" && 
	     objcheck[c].value == value)
       {
        return true; 
       }
     }
 }
 else
 {
  if(objcheck.checked == "1" )
   {
    return true; 
   }    
 }
 return false;
}
/*
	Copyright (C) 2003-2009 JavaScript-Coder.com . All rights reserved.
*/

/**
 * Calendar Script
 * Creates a calendar widget which can be used to select the date more easily than using just a text box
 * http://www.openjs.com/scripts/ui/calendar/
 *
 * Example:
 * <input type="text" name="date" id="date" />
 * <script type="text/javascript">
 * 		calendar.set("date");
 * </script>
 */
calendar = {
	month_names: ["January","February","March","April","May","June","July","Augest","September","October","November","December"],
	weekdays: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
	month_days: [31,28,31,30,31,30,31,31,30,31,30,31],
	//Get today's date - year, month, day and date
	today : new Date(),
	opt : {},
	data: [],

	//Functions
	/// Used to create HTML in a optimized way.
	wrt:function(txt) {
		this.data.push(txt);
	},
	
	/* Inspired by http://www.quirksmode.org/dom/getstyles.html */
	getStyle: function(ele, property){
		if (ele.currentStyle) {
			var alt_property_name = property.replace(/\-(\w)/g,function(m,c){return c.toUpperCase();});//background-color becomes backgroundColor
			var value = ele.currentStyle[property]||ele.currentStyle[alt_property_name];
		
		} else if (window.getComputedStyle) {
			property = property.replace(/([A-Z])/g,"-$1").toLowerCase();//backgroundColor becomes background-color

			var value = document.defaultView.getComputedStyle(ele,null).getPropertyValue(property);
		}
		
		//Some properties are special cases
		if(property == "opacity" && ele.filter) value = (parseFloat( ele.filter.match(/opacity\=([^)]*)/)[1] ) / 100);
		else if(property == "width" && isNaN(value)) value = ele.clientWidth || ele.offsetWidth;
		else if(property == "height" && isNaN(value)) value = ele.clientHeight || ele.offsetHeight;
		return value;
	},
	getPosition:function(ele) {
		var x = 0;
		var y = 0;
		while (ele) {
			x += ele.offsetLeft;
			y += ele.offsetTop;
			ele = ele.offsetParent;
		}
		if (navigator.userAgent.indexOf("Mac") != -1 && typeof document.body.leftMargin != "undefined") {
			x += document.body.leftMargin;
			offsetTop += document.body.topMargin;
		}
	
		var xy = new Array(x,y);
		return xy;
	},
	/// Called when the user clicks on a date in the calendar.
	selectDate:function(year,month,day) {
		var ths = _calendar_active_instance;
		document.getElementById(ths.opt["input"]).value = year + "-" + month + "-" + day; // Date format is :HARDCODE:
		ths.hideCalendar();
	},
	/// Creates a calendar with the date given in the argument as the selected date.
	makeCalendar:function(year, month, day) {
		year = parseInt(year);
		month= parseInt(month);
		day	 = parseInt(day);
		
		//Display the table
		var next_month = month+1;
		var next_month_year = year;
		if(next_month>=12) {
			next_month = 0;
			next_month_year++;
		}
		
		var previous_month = month-1;
		var previous_month_year = year;
		if(previous_month< 0) {
			previous_month = 11;
			previous_month_year--;
		}
		
		this.wrt("<table>");
		this.wrt("<tr><th><a href='javascript:calendar.makeCalendar("+(previous_month_year)+","+(previous_month)+");' title='"+this.month_names[previous_month]+" "+(previous_month_year)+"'>&lt;</a></th>");
		this.wrt("<th colspan='5' class='calendar-title'><select name='calendar-month' class='calendar-month' onChange='calendar.makeCalendar("+year+",this.value);'>");
		for(var i in this.month_names) {
			this.wrt("<option value='"+i+"'");
			if(i == month) this.wrt(" selected='selected'");
			this.wrt(">"+this.month_names[i]+"</option>");
		}
		this.wrt("</select>");
		this.wrt("<select name='calendar-year' class='calendar-year' onChange='calendar.makeCalendar(this.value, "+month+");'>");
		var current_year = this.today.getYear();
		if(current_year < 1900) current_year += 1900;
		
		for(var i=current_year-70; i<current_year+10; i++) {
			this.wrt("<option value='"+i+"'")
			if(i == year) this.wrt(" selected='selected'");
			this.wrt(">"+i+"</option>");
		}
		this.wrt("</select></th>");
		this.wrt("<th><a href='javascript:calendar.makeCalendar("+(next_month_year)+","+(next_month)+");' title='"+this.month_names[next_month]+" "+(next_month_year)+"'>&gt;</a></th></tr>");
		this.wrt("<tr class='header'>");
		for(var weekday=0; weekday<7; weekday++) this.wrt("<td>"+this.weekdays[weekday]+"</td>");
		this.wrt("</tr>");
		
		//Get the first day of this month
		var first_day = new Date(year,month,1);
		var start_day = first_day.getDay();
		
		var d = 1;
		var flag = 0;
		
		//Leap year support
		if(year % 4 == 0) this.month_days[1] = 29;
		else this.month_days[1] = 28;
		
		var days_in_this_month = this.month_days[month];

		//Create the calender
		for(var i=0;i<=5;i++) {
			if(w >= days_in_this_month) break;
			this.wrt("<tr>");
			for(var j=0;j<7;j++) {
				if(d > days_in_this_month) flag=0; //If the days has overshooted the number of days in this month, stop writing
				else if(j >= start_day && !flag) flag=1;//If the first day of this month has come, start the date writing

				if(flag) {
					var w = d, mon = month+1;
					if(w < 10)	w	= "0" + w;
					if(mon < 10)mon = "0" + mon;

					//Is it today?
					var class_name = '';
					var yea = this.today.getYear();
					if(yea < 1900) yea += 1900;

					if(yea == year && this.today.getMonth() == month && this.today.getDate() == d) class_name = " today";
					if(day == d) class_name += " selected";
					
					class_name += " " + this.weekdays[j].toLowerCase();

					this.wrt("<td class='days"+class_name+"'><a href='javascript:calendar.selectDate(\""+year+"\",\""+mon+"\",\""+w+"\")'>"+w+"</a></td>");
					d++;
				} else {
					this.wrt("<td class='days'>&nbsp;</td>");
				}
			}
			this.wrt("</tr>");
		}
		this.wrt("</table>");
		this.wrt("<input type='button' value='Cancel' class='calendar-cancel' onclick='calendar.hideCalendar();' />");

		document.getElementById(this.opt['calendar']).innerHTML = this.data.join("");
		this.data = [];
	},
	
	/// Display the calendar - if a date exists in the input box, that will be selected in the calendar.
	showCalendar: function() {
		var input = document.getElementById(this.opt['input']);
		
		//Position the div in the correct location...
		var div = document.getElementById(this.opt['calendar']);
		var xy = this.getPosition(input);
		var width = parseInt(this.getStyle(input,'width'));
		div.style.left=(xy[0]+width+10)+"px";
		div.style.top=xy[1]+"px";

		// Show the calendar with the date in the input as the selected date
		var existing_date = new Date();
		var date_in_input = input.value;
		if(date_in_input) {
			var selected_date = false;
			var date_parts = date_in_input.split("-");
			if(date_parts.length == 3) {
				date_parts[1]--; //Month starts with 0
				selected_date = new Date(date_parts[0], date_parts[1], date_parts[2]);
			}
			if(selected_date && !isNaN(selected_date.getYear())) { //Valid date.
				existing_date = selected_date;
			}
		}
		
		var the_year = existing_date.getYear();
		if(the_year < 1900) the_year += 1900;
		this.makeCalendar(the_year, existing_date.getMonth(), existing_date.getDate());
		document.getElementById(this.opt['calendar']).style.display = "block";
		_calendar_active_instance = this;
	},
	
	/// Hides the currently show calendar.
	hideCalendar: function(instance) {
		var active_calendar_id = "";
		if(instance) active_calendar_id = instance.opt['calendar'];
		else active_calendar_id = _calendar_active_instance.opt['calendar'];
		
		if(active_calendar_id) document.getElementById(active_calendar_id).style.display = "none";
		_calendar_active_instance = {};
	},
	
	/// Setup a text input box to be a calendar box.
	set: function(input_id) {
		var input = document.getElementById(input_id);
		if(!input) return; //If the input field is not there, exit.
		
		if(!this.opt['calendar']) this.init();
		
		var ths = this;
		input.onclick=function(){
			ths.opt['input'] = this.id;
			ths.showCalendar();
		};
	},
	
	/// Will be called once when the first input is set.
	init: function() {
		if(!this.opt['calendar'] || !document.getElementById(this.opt['calendar'])) {
			var div = document.createElement('div');
			if(!this.opt['calendar']) this.opt['calendar'] = 'calender_div_'+ Math.round(Math.random() * 100);

			div.setAttribute('id',this.opt['calendar']);
			div.className="calendar-box";

			document.getElementsByTagName("body")[0].insertBefore(div,document.getElementsByTagName("body")[0].firstChild);
		}
	}
}
