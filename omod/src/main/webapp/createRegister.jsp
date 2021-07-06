<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">

            <form:form modelAttribute="registerForm" action="" method="post" id="form">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-md-5"><h5>Registre de d&eacute;pistage</h5></div>
                            <div class="col-md-7 text-right">
                                <button class="btn btn-sm btn-primary">Saisir les d&eacute;pistages</button>
                                <c:url value="/module/hivscreening/manage.form" var="listUrl"/>
                                <a class="btn btn-sm btn-info mr-2" href="${listUrl}" style="text-decoration: none; color: white">
                                    <i class="fa fa-eject"></i> Annuler
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form:hidden path="id"/>
                        <form:hidden path="screeningSiteType"/>
                        <form:hidden path="testingKit1Id"/>
                        <form:hidden path="testingKit2Id"/>
                        <form:hidden path="labTestingKitId"/>
                        <div class="row">
                            <div class="col-md-5">
                                <h5>Informations du registre</h5>
                                <div class="card">
                                    <div class="card-body">
                                        <div class="form-row mb-2">
                                            <div class="col-md-12">
                                                <label>Type de site</label>
                                                <div class="form-control-sm form-control text-white bg-info">
                                                        ${registerForm.screeningSiteType}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-row mb-2">
                                            <div class="col-md-6">
                                                <label>Code du registre <span class="required">*</span></label>
                                                <form:input path="registerCode" cssClass="form-control form-control-sm" readonly="${registerForm.id != null}" />
                                                <form:errors path="registerCode" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-row mb-2">
                                            <div class="col-md-6">
                                                <label>Poste de d&eacute;pistage <span class="required">*</span></label>
                                                <form:input path="screeningPost" cssClass="form-control form-control-sm" readonly="${registerForm.id != null}" />
                                                <form:errors path="screeningPost" cssClass="error"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <h5>Kit de test 1</h5>
                                <div class="card">
                                    <div class="card-body">
                                        <div class="form-row mb-2">
                                            <div class="col-md-12">
                                                <label>Nom du kit <span class="required">*</span></label>
                                                <c:if test="${registerForm.testingKit1Id == null && registerForm.id == null}">
                                                    <form:select path="testingKit1Name" cssClass="form-control form-control-sm s2" >
                                                        <form:option value="" label=""/>
                                                        <c:forEach var="name" items="${testingKit1Names}">
                                                            <option value="${name}"
                                                                    <c:if test="${registerForm.testingKit1Name == name}">selected="selected"</c:if>
                                                            >${name}</option>
                                                        </c:forEach>
                                                    </form:select>
                                                    <form:errors path="testingKit1Name" cssClass="error"/>
                                                </c:if>
                                                <c:if test="${registerForm.testingKit1Id != null}">
                                                    <div class="form-control-sm form-control">
                                                            ${registerForm.testingKit1Name}
                                                        <form:hidden path="testingKit2Name"/>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="form-row mb-2">
                                            <div class="col-md-12">
                                                <label>Num&eacute;ro de lot <span class="required">*</span></label>
                                                <form:input path="testingKit1BatchNumber" cssClass="form-control form-control-sm" readonly="${registerForm.testingKit1Id != null}" />
                                                <form:errors path="testingKit1BatchNumber" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-row mb-2">
                                            <div class="col-md-12">
                                                <label>Date de p&eacute;remption <span class="required">*</span></label>
                                                <form:input path="testingKit1ExpiryDate" cssClass="form-control form-control-sm picker" readonly="${registerForm.testingKit1Id != null}" />
                                                <form:errors path="testingKit1ExpiryDate" cssClass="error"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${registerForm.screeningSiteType == 'Laboratoire'}">
                                <div class="col">
                                    <h5>Kit de test 2 Labo</h5>
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="form-row mb-2">
                                                <div class="col-md-12">
                                                    <label>Nom du kit <span class="required">*</span></label>
                                                    <c:if test="${registerForm.labTestingKitId == null && registerForm.id == null}">
                                                        <form:select path="labTestingKitName" cssClass="form-control form-control-sm s2" >
                                                            <form:option value="" label=""/>
                                                            <c:forEach var="name" items="${labTestingKitNames}">
                                                                <option value="${name}"
                                                                        <c:if test="${registerForm.labTestingKitName == name}">selected="selected"</c:if>>${name}</option>
                                                            </c:forEach>
                                                        </form:select>
                                                        <form:errors path="labTestingKitName" cssClass="error"/>
                                                    </c:if>
                                                    <c:if test="${registerForm.labTestingKitId != null}">
                                                        <div class="form-control-sm form-control">
                                                                ${registerForm.labTestingKitName}
                                                            <form:hidden path="labTestingKitName"/>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="form-row mb-2">
                                                <div class="col-md-12">
                                                    <label>Num&eacute;ro de lot <span class="required">*</span></label>
                                                    <form:input path="labTestingKitBatchNumber" cssClass="form-control form-control-sm" readonly="${registerForm.labTestingKitId != null}" />
                                                    <form:errors path="labTestingKitBatchNumber" cssClass="error"/>
                                                </div>
                                            </div>
                                            <div class="form-row mb-2">
                                                <div class="col-md-12">
                                                    <label>Date de p&eacute;remption <span class="required">*</span></label>
                                                    <form:input path="labTestingKitExpiryDate" cssClass="form-control form-control-sm picker" readonly="${registerForm.labTestingKitId != null}" />
                                                    <form:errors path="labTestingKitExpiryDate" cssClass="error"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <div class="col">
                                <h5>Kit de test ${registerForm.screeningSiteType == 'Laboratoire' ? '3' : '2'}</h5>
                                <div class="card">
                                    <div class="card-body">
                                        <div class="form-row mb-2">
                                            <div class="col-md-12">
                                                <label>Nom du kit <span class="required">*</span></label>
                                                <c:if test="${registerForm.testingKit2Id == null && registerForm.id == null}">
                                                    <form:select path="testingKit2Name" cssClass="form-control form-control-sm s2" >
                                                        <form:option value="" label=""/>
                                                        <c:forEach var="name" items="${testingKit2Names}">
                                                            <option value="${name}"
                                                                    <c:if test="${registerForm.testingKit2Name == name}">selected="selected"</c:if>
                                                            >${name}</option>
                                                        </c:forEach>
                                                    </form:select>
                                                    <form:errors path="testingKit2Name" cssClass="error"/>
                                                </c:if>
                                                <c:if test="${registerForm.testingKit2Id != null}">
                                                    <div class="form-control-sm form-control">
                                                            ${registerForm.testingKit2Name}
                                                        <form:hidden path="testingKit2Name"/>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="form-row mb-2">
                                            <div class="col-md-12">
                                                <label>Num&eacute;ro de lot <span class="required">*</span></label>
                                                <form:input path="testingKit2BatchNumber" cssClass="form-control form-control-sm" readonly="${registerForm.testingKit2Id != null}" />
                                                <form:errors path="testingKit2BatchNumber" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-row mb-2">
                                            <div class="col-md-12">
                                                <label>Date de p&eacute;remption <span class="required">*</span></label>
                                                <form:input path="testingKit2ExpiryDate" cssClass="form-control form-control-sm picker" readonly="${registerForm.testingKit2Id != null}"/>
                                                <form:errors path="testingKit2ExpiryDate" cssClass="error"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${registerForm.screeningSiteType != 'Laboratoire'}">
                                <div class="col"></div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<%@include file="template/localFooter.jsp"%>

<%@ include file="/WEB-INF/template/footer.jsp"%>
