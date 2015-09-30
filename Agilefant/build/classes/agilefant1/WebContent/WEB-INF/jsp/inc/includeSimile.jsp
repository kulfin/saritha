<%@taglib uri="/WEB-INF/tlds/aef.tld" prefix="aef"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${aef:releaseMode()}">
		<aef:javascript path="simile" minify="always" />
	</c:when>
	<c:otherwise>
		<aef:javascript path="simile/init" />

		<aef:javascript path="simile/ajax/platform" />
		<aef:javascript path="simile/ajax/debug" />
		<aef:javascript path="simile/ajax/dom" />
		<aef:javascript path="simile/ajax/graphics" />
		<aef:javascript path="simile/ajax/date-time" />
		<aef:javascript path="simile/ajax/string" />
		<aef:javascript path="simile/ajax/html" />
		<aef:javascript path="simile/ajax/data-structure" />
		<aef:javascript path="simile/ajax/units" />
		<aef:javascript path="simile/ajax/ajax" />
		<aef:javascript path="simile/ajax/history" />
		<aef:javascript path="simile/ajax/window-manager" />

		<aef:javascript path="simile/timeline/timeline" />
		<aef:javascript path="simile/timeline/themes" />
		<aef:javascript path="simile/timeline/ethers" />
		<aef:javascript path="simile/timeline/event-utils" />
		<aef:javascript path="simile/timeline/band" />
		<aef:javascript path="simile/timeline/sources" />
		<aef:javascript path="simile/timeline/original-painter" />
		<aef:javascript path="simile/timeline/detailed-painter" />
		<aef:javascript path="simile/timeline/overview-painter" />
		<aef:javascript path="simile/timeline/ether-painters" />
		<aef:javascript path="simile/timeline/compact-painter" />
		<aef:javascript path="simile/timeline/decorators" />
		<aef:javascript path="simile/timeline/units" />
		<aef:javascript path="simile/timeline/labellers" />

		<aef:javascript path="simile/timeplot/timeplot" />
		<aef:javascript path="simile/timeplot/plot" />
		<aef:javascript path="simile/timeplot/sources" />
		<aef:javascript path="simile/timeplot/geometry" />
		<aef:javascript path="simile/timeplot/color" />
		<aef:javascript path="simile/timeplot/math" />
		<aef:javascript path="simile/timeplot/processor" />
	</c:otherwise>
</c:choose>