<%@include file="/WEB-INF/jsp/inc/_taglibs.jsp" %>
<c:choose>
<c:when test="${access}">
<struct:widget name="Workload of: ${user.fullName}" widgetId="${widgetId}">
  <div class="widget-top-info">
    <a href="dailyWork.action?userId=${user.id}">View work of ${user.fullName}</a>
  </div>
  <div style="padding: 1em;">
    <div style="height: 100px;" id="userLoadWidget_${widgetId}"></div>
  </div>
  <script type="text/javascript">
  $(document).ready(function() {

    new UserLoadPlotWidget(${objectId},{
      total:{
            element: $("#userLoadWidget_${widgetId}")
        }
    }, 3);
  });
  </script>
</struct:widget>
</c:when>
<c:otherwise>
  <div>Missing user metric - You do not have access rights to the user</div>
</c:otherwise>
</c:choose>
