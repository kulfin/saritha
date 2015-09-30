<%@ include file="./inc/_taglibs.jsp"%>

<struct:htmlWrapper navi="timesheet">


<script type="text/javascript">
var selectedReportUsers = [];
<c:forEach items="${selectedUsers}" var="selu">
  selectedReportUsers.push(${selu.id});
</c:forEach>
$(document).ready(function() {
    var chooserDiv = $("#selectBacklogs");
    var userSel = $("#userSelect");

    $("#showOnlyOngoingBacklogs").change(function() {
        if($(this).is(":checked")) {
            chooserDiv.backlogChooser("setDateLimit");
        } else {
            chooserDiv.backlogChooser("unsetDateLimit");
        }
    });
    chooserDiv.backlogChooser({
        useDateLimit: $("#showOnlyOngoingBacklogs").is(":checked"),
        selectedProducts: ${JSONProducts},
        selectedProjects: ${JSONProjects},
        selectedIterations: ${JSONIterations}
    });

    $('#userChooserLink-createStory').click(function() {
      $(window).autocompleteDialog({
        dataType: "usersAndTeams",
        callback: function(ids, objs) {
          var contain = $("#userListContainer-createStory").empty();
          var names = [];
          selectedReportUsers = [];
          $.each(objs, function() {
            $('<input type="hidden" name="userIds" />').val(this.id).appendTo(contain);
            names.push(this.initials);
            selectedReportUsers.push(this.id);
          });
          $('<span />').text(names.join(", ")).appendTo(contain);
        },
        selected: selectedReportUsers
      });
    });
});

function addZero($string) {
    var str = '0'+$string;
    //return last two chars
    return str.substr(str.length-2);
}

function change_selected_interval(value) {
    var startDate = document.getElementById('effStartDate');
    var endDate = document.getElementById('effEndDate');

	var today = new Date();
	today.zeroTime();
	var yesterday = new Date();
	yesterday.zeroTime();
	yesterday.addDays(-1);
	var thisweek = new Date();
	thisweek.zeroTime();
	thisweek.addDays(1 - thisweek.getDay());
	var lastweek = new Date();
	lastweek.zeroTime();
	lastweek.addDays(1 - lastweek.getDay());
	lastweek.addDays(-7);
	var thismonth = new Date();
	thismonth.zeroTime();
	thismonth.setDate(1);
	var lastmonth = new Date();
	lastmonth.zeroTime();
	lastmonth.addMonths(-1);
	lastmonth.setDate(1);
	var thisyear = new Date();
	thisyear.zeroTime();
	thisyear.setMonth(0);
	thisyear.setDate(1);
	var lastyear = new Date();
	lastyear.zeroTime();
	lastyear.setDate(1);
	lastyear.setMonth(0);
	lastyear.addYears(-1);
    $(".dateSelectRow").hide();
    if (value == 'TODAY') {
        startDate.value = today.asString();
        today.setHours(23);
        today.setMinutes(59);
        endDate.value   = today.asString();
    } else if (value == 'YESTERDAY') {
        startDate.value = yesterday.asString();
        yesterday.setHours(23);
        yesterday.setMinutes(59);
        endDate.value   = yesterday.asString();
    } else if (value == 'THIS_WEEK') {
        startDate.value = thisweek.asString();
        thisweek.addDays(6);
        thisweek.setHours(23);
        thisweek.setMinutes(59);
        endDate.value   = thisweek.asString();
    } else if (value == 'LAST_WEEK') {
        startDate.value = lastweek.asString();
        lastweek.addDays(6);
        lastweek.setHours(23);
        lastweek.setMinutes(59);
        endDate.value   = lastweek.asString();
    } else if (value == 'THIS_MONTH') {
        startDate.value = thismonth.asString();
        thismonth.addMonths(1);
        thismonth.addMinutes(-1);
        endDate.value   = thismonth.asString();
    } else if (value == 'LAST_MONTH') {
        startDate.value = lastmonth.asString();
        lastmonth.addMonths(1);
        lastmonth.addMinutes(-1);
        endDate.value   = lastmonth.asString();
    } else if (value == 'THIS_YEAR') {
        startDate.value = thisyear.asString();
        thisyear.addYears(1);
        thisyear.addMinutes(-1);
        endDate.value   = thisyear.asString();
    } else if (value == 'LAST_YEAR') {
        startDate.value = lastyear.asString();
        lastyear.addYears(1);
        lastyear.addMinutes(-1);
        endDate.value   = lastyear.asString();
    } else if (value == 'NO_INTERVAL') {
        startDate.value = '';
        endDate.value   = '';
    } else {
		$(".dateSelectRow").show();
    }
}
function getUserTimeZone() {
	return new Date().getUserTimeZone() - 12;
}
function getUserTimeZoneString() {
	var userTimeZoneOffset = Math.abs(new Date().getTimezoneOffset());
	var minutes = userTimeZoneOffset % 60;
	var hours = (userTimeZoneOffset - minutes) / 60;
	var minutesString = "" + minutes;
	var hoursString = "" + hours;
	if (minutes < 10) {
		minutesString = "0" + minutes;
	}
	userTimeZoneString = "";
	var userTimeZone = getUserTimeZone();
    if(userTimeZone >= 0) {
    	userTimeZoneString = userTimeZoneString +  "+";
    } else {
    	userTimeZoneString = userTimeZoneString +  "-";
    }
	userTimeZoneString = userTimeZoneString + hoursString + ":" + minutesString;
	return userTimeZoneString;
}
$(document).ready( function() {
    var interval = document.getElementById('interval');
    <ww:set name="currently" value="#attr.interval" />
    var current = "${interval}";
    if (current) {
        change_selected_interval(current);
        $("#interval").find("[value='"+current+"']").attr("selected","selected");
    } else {
      $(".dateSelectRow").show();
    }

    var userTimeZone = getUserTimeZone();
    var userTimeZoneString = getUserTimeZoneString();
    var userTimeZoneDecimalString = "" + userTimeZone;
    if (userTimeZoneDecimalString.indexOf(".") == -1) {
    	userTimeZoneDecimalString = userTimeZoneDecimalString + ".0";
    }
    if ($('#timeZone option[value='+userTimeZoneDecimalString+']').length == 0) {
    	var myText = "UTC";
        myText = myText + userTimeZoneString + " (your time zone)";
        var myOption = new Option(myText, userTimeZone);
        $(myOption).html(myText);
        $("#timeZone").prepend(myOption);
    }
    var initTimeZone = "${timeZoneString}";
   	if (initTimeZone) {
   		$("#timeZone").val(initTimeZone);
   	} else {
   		$("#timeZone").val(userTimeZoneDecimalString);
   	}
});
function tsToggle(caller) {
	var curRow = $(caller).closest("tr");
	var nextRow = curRow.next();
	nextRow.find("> td.innerTable:not(.noToggle)").toggle();
	var nextRow = nextRow.next();
	nextRow.find("> td.innerTable:not(.noToggle)").toggle();
}




/* Bind the open/close links*/
$(document).ready(function() {
  $('.timesheetOpenListLink').click(function() {
    var me = $(this);
    var target = $('#' + me.attr('rel'));
    me.toggleClass('timesheetOpenList-open');
    target.toggle();
    return false;
  });
});





</script>

<div class="structure-main-block">
<div class="dynamictable ui-widget-content ui-corner-all" id="changingPassword">

<div class="ui-widget-header dynamictable-caption dynamictable-caption-block ui-corner-all">
Reporting
</div>

<table>
	<tbody>
		<tr>
			<td><ww:form action="generateTree" method="post">
				<div id="subItems" style="margin-top: 0pt;"
					id="subItems_timesheetGenerateReport">
				<div id="subItemHeader">
				<table cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="header">Generate a report</td>
						</tr>
					</tbody>
				</table>
				</div>
				<table class="formTable">
					<tbody>

						<tr>
							<td>Backlogs</td>
							<td><input type="hidden" name="backlogSelectionType"
								value="0" />
							<div id="advancedBacklogs"><c:choose>
								<c:when test="${onlyOngoing}">
									<input id="showOnlyOngoingBacklogs" name="onlyOngoing"
										type="checkbox" value="true" checked="checked" />Hide past projects and iterations.<br />
								</c:when>
								<c:otherwise>
									<input id="showOnlyOngoingBacklogs" name="onlyOngoing"
										type="checkbox" value="true" />Hide past projects and iterations.<br />
								</c:otherwise>
							</c:choose>
							<div id="selectBacklogs"></div>
							</div>
							</td>
						</tr>
						<!-- Interval selection -->
						<tr>
							<td>Interval</td>

							<td colspan="2"><select name="interval" id="interval"
								onchange="change_selected_interval(this.value);">
								<option value="">Custom</option>
								<option value="TODAY">Today</option>
								<option value="YESTERDAY">Yesterday</option>
								<option value="THIS_WEEK">This week</option>
								<option value="LAST_WEEK">Last week</option>
								<option value="THIS_MONTH">This month</option>
								<option value="LAST_MONTH">Last month</option>
								<option value="THIS_YEAR">This year</option>
								<option value="LAST_YEAR">Last year</option>
								<option value="NO_INTERVAL">(All past entries)</option>
							</select></td>
						</tr>
						<!--  Start date -->
						<tr class="dateSelectRow" style="display: none;">
							<td>Start date</td>
							<td><aef:datepicker value="${startDate}" id="effStartDate"
								name="startDate"
								format="yyyy-MM-dd HH:mm" /></td>
						</tr>
						<!--  End date -->
						<tr class="dateSelectRow" style="display: none;">
							<td>End date</td>
							<td><aef:datepicker value="${endDate}" id="effEndDate"
								name="endDate"
								format="yyyy-MM-dd HH:mm" /></td>
						</tr>
						<!-- Time zone selection -->
						<tr>
							<td>Time zone</td>

							<td colspan="2"><select name="timeZoneString" id="timeZone">
									<option value="-12.0">(UTC-12:00) Eniwetok, Kwajalein</option>
									<option value="-11.0">(UTC-11:00) Midway Island, Samoa</option>
									<option value="-10.0">(UTC-10:00) Hawaii</option>
									<option value="-9.0">(UTC-9:00) Alaska</option>
									<option value="-8.0">(UTC-8:00) Pacific Time (US &amp; Canada)</option>
									<option value="-7.0">(UTC-7:00) Mountain Time (US &amp; Canada)</option>
									<option value="-6.0">(UTC-6:00) Central Time (US &amp; Canada), Mexico City</option>
									<option value="-5.0">(UTC-5:00) Eastern Time (US &amp; Canada), Bogota, Lima</option>
									<option value="-4.0">(UTC-4:00) Atlantic Time (Canada), Caracas, La Paz</option>
									<option value="-3.5">(UTC-3:30) Newfoundland</option>
									<option value="-3.0">(UTC-3:00) Brazil, Buenos Aires, Georgetown</option>
									<option value="-2.0">(UTC-2:00) Mid-Atlantic</option>
									<option value="-1.0">(UTC-1:00) Azores, Cape Verde Islands</option>
									<option value="0.0">(UTC) Western Europe Time, London, Lisbon, Casablanca</option>
									<option value="1.0">(UTC+1:00) Brussels, Copenhagen, Madrid, Paris</option>
									<option value="2.0">(UTC+2:00) Kaliningrad, South Africa</option>
									<option value="3.0">(UTC+3:00) Baghdad, Riyadh, Moscow, St. Petersburg</option>
									<option value="3.5">(UTC+3:30) Tehran</option>
									<option value="4.0">(UTC+4:00) Abu Dhabi, Muscat, Baku, Tbilisi</option>
									<option value="4.5">(UTC+4:30) Kabul</option>
									<option value="5.0">(UTC+5:00) Ekaterinburg, Islamabad, Karachi, Tashkent</option>
									<option value="5.5">(UTC+5:30) Bombay, Calcutta, Madras, New Delhi</option>
									<option value="5.75">(UTC+5:45) Kathmandu</option>
									<option value="6.0">(UTC+6:00) Almaty, Dhaka, Colombo</option>
									<option value="7.0">(UTC+7:00) Bangkok, Hanoi, Jakarta</option>
									<option value="8.0">(UTC+8:00) Beijing, Perth, Singapore, Hong Kong</option>
									<option value="9.0">(UTC+9:00) Tokyo, Seoul, Osaka, Sapporo, Yakutsk</option>
									<option value="9.5">(UTC+9:30) Adelaide, Darwin</option>
									<option value="10.0">(UTC+10:00) Eastern Australia, Guam, Vladivostok</option>
									<option value="11.0">(UTC+11:00) Magadan, Solomon Islands, New Caledonia</option>
									<option value="12.0">(UTC+12:00) Auckland, Wellington, Fiji, Kamchatka</option>
							</select></td>
						</tr>
						<!--  User selection -->
						<tr id="userSelect">
							<td>Users</td>
							<td>
							<div><a id="userChooserLink-createStory" href="#" class="assigneeLink"> <img src="static/img/users.png" />
							 <span id="userListContainer-createStory">
								<c:set var="userCount" value="${fn:length(selectedUsers)}" />
								<c:set var="curUserNo" value="0" />
								<c:if test="${userCount == 0}"> (all) </c:if>
								<c:forEach items="${selectedUsers}" var="selu">
								  <input type="hidden" name="userIds" value="${selu.id}" />
								  <c:set var="curUserNo" value="${curUserNo + 1}" />
                  ${selu.initials}
                  <c:if test="${curUserNo !=  userCount}">,</c:if>
							   </c:forEach>
							  </span>
							 </a></div>
							</td>
						</tr>
						<!-- Submit button -->
						<tr>
							<td></td>
							<td><ww:submit value="Calculate" /> <ww:submit
								value="Export to Excel" action="generateExcel" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			</ww:form>
			</td>
		</tr>
	</tbody>
</table>

</div>





<c:if test="${!empty products}">

<h2>Results</h2>

<p>Total spent effort: ${aef:minutesToString(effortSum)}</p>

<aef:timesheetBacklogNode nodes="${products}" />

</c:if>



<c:if test="${empty products && !empty selectedBacklogs}">

<h2>Results </h2>

<p>No matching effort entries were found.</p>

</c:if>

</div>

</struct:htmlWrapper>

