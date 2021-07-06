<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fct" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="includeStyle.jsp"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<script type="application/javascript" >
    if (jQuery) {
        jQuery(document).ready(function (){
            jQuery('.s2').select2();

            jQuery.datepicker.setDefaults({
                showOn: "both",
                buttonImageOnly: false,
                //buttonImage: "${pageContext.request.contextPath}/moduleResources/ptme/images/calendar.gif",
                //buttonText: "Calendar"
            });

            jQuery.datepicker.setDefaults( $.datepicker.regional[ "fr" ] );

            jQuery(".picker").datepicker({
                dateFormat: 'dd/mm/yy',
                dayNamesShort: [ "Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam" ],
                monthNamesShort: [ "Jan", "Fev", "Mar", "Avr", "Mai", "Jui", "Juil", "Aou", "Sep", "Oct", "Nov", "Dec" ],
                changeMonth: true,
                changeYear: true,
            });

            jQuery('.ui-datepicker-trigger').css("display","none");
        });
    }
</script>
<div class="alert alert-info text-center h4" style="border-radius: 0">D&Eacute;PISTAGE VIH DES PATIENTS</div>
<%--<ul id="menu">--%>
<%--	<li class="first"><a--%>
<%--		href="${pageContext.request.contextPath}/admin"><spring:message--%>
<%--				code="admin.title.short" /></a></li>--%>

<%--	<li--%>
<%--		<c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="active"</c:if>>--%>
<%--		<a--%>
<%--		href="${pageContext.request.contextPath}/module/hivscreening/manage.form"><spring:message--%>
<%--				code="hivscreening.manage" /></a>--%>
<%--	</li>--%>
<%--	--%>
<%--	<!-- Add further links here -->--%>
<%--</ul>--%>
<%--<h2>--%>
<%--	<spring:message code="hivscreening.title" />--%>
<%--</h2>--%>
