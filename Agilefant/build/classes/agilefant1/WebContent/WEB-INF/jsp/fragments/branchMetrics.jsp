<%@ include file="/WEB-INF/jsp/inc/_taglibs.jsp" %>

<table>
  <tr>
    <td style="font-weight: bold;" colspan="2">
      Branch completion
    </td>
  </tr>
    <c:if test="${settings.branchMetricsType == 'both' || settings.branchMetricsType == 'estimate'}">
  <tr>
    <td><span style="color: green;" title="Estimated done points">${branchMetrics.estimatedDonePoints} done</span> of <span title="Total estimated points">${branchMetrics.estimatedPoints}</span> points in the branch</td>
    <td style="color: #666;">(${fn:substringBefore(branchMetrics.estimatedDonePoints / branchMetrics.estimatedPoints * 100, '.')}%)</td>
  </tr>
  </c:if>
  
  <c:if test="${settings.branchMetricsType == 'both' || settings.branchMetricsType == 'leaf'}">
  <tr>
    <td><span style="color: green;" title="Done leaf points">${branchMetrics.doneLeafPoints} done</span> of <span title="Total leaf points">${branchMetrics.leafPoints}</span> points in leaf stories</td>
    <td style="color: #666;">(${fn:substringBefore(branchMetrics.doneLeafPoints / branchMetrics.leafPoints * 100, '.')}%)</td>
  </tr>
  </c:if>

  <tr>
    <td colspan="2">
      
    </td>
  </tr>
</table>

<table>
  <tr>
    <td style="font-weight: bold;" colspan="2">
      Branch effort
    </td>
  </tr>

  <c:if test="${settings.branchMetricsType == 'both' || settings.branchMetricsType == 'leaf' || settings.branchMetricsType == 'estimate'}">
  <tr>
    <td>Total spent <span title="Total spent effort">${branchMetrics.spentEffort}</span>h  <!--,  <span title="Effort left ">${branchMetrics.effortLeft}</span> hours effort left--> </td>
  </tr>
  </c:if>

  <tr>
    <td colspan="2">
      
    </td>
  </tr>
</table>