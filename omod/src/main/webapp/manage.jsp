<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div class="container-fluid">
<%--    <div class="text-center h1">D&Eacute;PISTAGE VIH DES PATIENTS</div>--%>
    <div class="row">
        <div class="col-md-12">
            <fieldset>
                <legend class="h5 text-info">Registres en cours de saisie</legend>
                <div class="row">
                    <table class="table table-striped table-bordered table-sm">
                        <thead>
                        <tr>
                            <th>Type de site</th>
                            <th>Code registre</th>
                            <th>Poste</th>
                            <th>Date de cr&eacute;ation</th>
                            <%--                    <th>Nombre de kits</th>--%>
                            <%--                    <th>Nombre d'intrants</th>--%>
                            <%--                    <th>Nombre de tests</th>--%>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="register" items="${registers}">
                            <tr>
                                <td>${register.screeningSiteType}</td>
                                <td>${fct:length(register.registerCode) == 1 ? '0' + register.registerCode : register.registerCode}</td>
                                <td>${fct:length(register.screeningPost) == 1 ? '0' + register.screeningPost : register.screeningPost}</td>
                                <td>
                                    <fmt:formatDate value="${register.dateCreated}" pattern="dd/MM/yyyy" type="DATE"/>
                                </td>
                                <td class="text-right align-middle" style="width: 150px">
                                    <c:url value="/module/hivscreening/screening.form" var="editRegisterUrl">
                                        <c:param name="registerId" value="${register.id}"/>
                                    </c:url>
                                    <a href="${editRegisterUrl}" class="btn btn-sm btn-primary mr-2"
                                       style="text-decoration: none; color: white">
                                        <i class="fa fa-edit"></i> Saisir
                                    </a>
                                    <c:if test="${fct:length(register.hivScreenings) == 0}">
                                        <c:url value="/module/hivscreening/registerDelete.form" var="deleteUrl">
                                            <c:param name="registerId" value="${register.id}"/>
                                        </c:url>
                                        <a href="${deleteUrl}" class="btn btn-sm btn-danger"
                                           onclick="confirm('Voulez-vous ')"
                                           style="text-decoration: none; color: white">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </fieldset>
        </div>
    </div>


    <div class="row border border-light p-2 bg-light">
        <c:forEach var="service" items="${services}">
            <div class="col-md-3 mb-2">
                <div class="card p-1">
                    <div class="card-body m-0 p-1">
                        <a href="${pageContext.request.contextPath}/module/hivscreening/createRegister.form?serviceId=${service.key}">${service.value}</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@include file="template/localFooter.jsp"%>

<%@ include file="/WEB-INF/template/footer.jsp"%>
