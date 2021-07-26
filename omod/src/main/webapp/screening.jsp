<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<script>
    if (jQuery) {
        jQuery(document).ready(function () {
            jQuery('input:radio').on('click', function (e) {
                if (e.ctrlKey) {
                    jQuery(this).prop('checked', false);
                }
            });

            // jQuery('#screeningTable').dataTable();

            const maritalStatus = jQuery('input[name=maritalStatus]');
            const otherMaritalStatus = jQuery('input[name=otherMaritalStatus]');
            const populationType = jQuery('input[name=populationType]');
            const otherPopulationType = jQuery('input[name=otherPopulationType]');
            const screeningReason = jQuery('input[name=screeningReason]');
            const otherScreeningReason = jQuery('input[name=otherScreeningReason]');
            const invalidatedTest1 = jQuery('input[name=invalidatedTest1]');
            const test1Reaction = jQuery('input[name=test1Reaction]');
            const invalidatedTest2 = jQuery('input[name=invalidatedTest2]');
            const test2Reaction = jQuery('input[name=test2Reaction]');
            const invalidatedTest3 = jQuery('input[name=invalidatedTest3]');
            const test3Reaction = jQuery('input[name=test3Reaction]');
            const finalResult = jQuery('input[name=finalResult]');
            const resultAnnouncingDate = jQuery('input[name=resultAnnouncingDate]');
            const retesting = jQuery('input[name=retesting]');
            const sampling = jQuery('input[name=sampling]');
            const screeningCode = jQuery('input[name=screeningCode]');

            if (sampling.is(':checked')) {
                screeningCode.hide();
            }

            // if (jQuery('input[name=sampling]:checked').val())
            // sampling.on('click', function () {
            //
            // });
            otherMaritalStatus.hide();
            otherScreeningReason.hide();
            otherPopulationType.hide();
            if (maritalStatus.val() === 'OTHER') {
                otherMaritalStatus.show();
            }
            if (screeningReason.val() === 'OTHER') {
                otherScreeningReason.show();
            }
            if (screeningReason.val() === 'OTHER') {
                otherScreeningReason.show();
            }

            if (populationType.val() === 'OTHER'){
                otherPopulationType.show();
            }

            maritalStatus.on('click', function () {
                if (jQuery(this).val() === 'OTHER') {
                    otherMaritalStatus.show();
                } else {
                    otherMaritalStatus.val('');
                    otherMaritalStatus.hide();
                }
            });

            sampling.on('click', function () {
                if (jQuery(this).is(':checked')) {
                    screeningCode.val('');
                    screeningCode.hide();
                } else {
                    screeningCode.show();
                }
            });

            screeningReason.on('click', function () {
                if (jQuery(this).val() === 'OTHER') {
                    otherScreeningReason.show();
                } else {
                    otherScreeningReason.val('');
                    otherScreeningReason.hide();
                }
            });

            test1Reaction.on('change', function () {
                console.log(test1Reaction.val())
                if (test1Reaction.val() === 'REACTIVE' && test3Reaction === 'REACTIVE') {
                    finalResult.val('POS');
                    jQuery('#pos').prop('checked', true);
                }
            });

            test3Reaction.on('change', function () {
                console.log(test3Reaction.val())
                if (test1Reaction.val() === 'REACTIVE' && test3Reaction === 'REACTIVE') {
                    finalResult.val('POS');
                    jQuery('#pos').prop('checked', true);
                }
            });

            populationType.on('change', function () {
                if (jQuery(this).val() === 'OTHER') {
                    otherPopulationType.show();
                } else {
                    otherPopulationType.val('');
                    otherPopulationType.hide();
                }
            });
        });
        function checkFinalResult() {
            jQuery(':radio[readonly]:not(:checked)').attr('disabled', true);
        }
    }
</script>

<div class="container-fluid">
    <div class="row">
        <table class="table table-borderless table-sm" id="screeningTable">
            <tr>
                <td>Type de Site</td>
                <td class="text-info">${registerInfo.screeningSiteType}</td>
                <td>Registre num&eacute;ro</td>
                <td class="text-info text-left" style="width: 20px;">${registerInfo.registerCode}</td>
                <td>Poste Nnum&eacute;ro</td>
                <td class="text-info text-left" style="width: 20px;">${registerInfo.screeningPost}</td>
                <td class="text-right">
                    <c:url value="/module/hivscreening/createRegister.form" var="editRegisterUrl">
                        <c:param name="registerId" value="${registerInfo.id}"/>
                        <c:param name="action" value="register"/>
                    </c:url>
                    <a href="${editRegisterUrl}" class="btn btn-sm btn-primary mr-2" style="text-decoration: none; color: white">
                        <i class="fa fa-book"></i> Nouveau Registre
                    </a>
                    <c:if test="${finishedKit == true || expiryKit == true}">
                        <c:url value="/module/hivscreening/createRegister.form" var="editKitUrl">
                            <c:param name="registerId" value="${registerInfo.id}"/>
                            <c:param name="action" value="kit"/>
                        </c:url>
                        <a href="${editKitUrl}" class="btn btn-sm btn-secondary mr-2" style="text-decoration: none; color: white">
                            <i class="fa fa-medkit"></i> Nouveau Kit
                        </a>
                    </c:if>
                    <c:url value="/module/hivscreening/manage.form" var="listUrl"/>
                    <a class="btn btn-sm btn-info mr-2" href="${listUrl}" style="text-decoration: none; color: white">
                        <i class="fa fa-eject"></i> Terminer
                    </a>
                </td>
            </tr>
        </table>
        <form:form modelAttribute="screeningForm" action="" method="post" id="form">
            <form:hidden path="id"/>
            <form:hidden path="registerInfoId"/>
            <table class="table table-bordered table-sm">
                <thead style="font-size: 11px" class="bg-light">
                <tr class="align-middle align-content-between">
                    <th rowspan="3" class="align-middle" style="width: 115px">Date</th>
                    <th rowspan="3" class="align-middle" style="width: 150px">Code d&eacute;pistage</th>
                    <th rowspan="3" class="align-middle">Profession</th>
                    <th rowspan="3" class="align-middle" style="width: 55px">Age</th>
                    <th rowspan="3" class="align-middle" style="width: 70px">Sexe</th>
                    <th rowspan="3" class="align-middle">R&eacute;sidence <br>Habituelle</th>
                    <th rowspan="3" class="align-middle">
                        Situation <br>matrimoniale<br>1= C&eacute;libataire <br>2= Couple <br>
                        3= Autre &agrave; pr&eacute;ciser
                    </th>
                    <th rowspan="3" class="align-middle">
                        Type de population <br>
                        1=Population G&eacute;n&eacute;rale <br>
                        2=UD <br>
                        3=TS <br>
                        4=HSH <br>
                        5=PC <br>
                        6=Autre population <br> &grave; haut risque <br> &agrave; pr&eacute;ciser
                    </th>
                    <th rowspan="3" class="align-middle">
                        Motif <br>
                        0=Volontaire <br>
                        1=IST <br>
                        2=Confirmation <br>apr&egrave;s auto-test <br>
                        3=Contact-index <br>
                        4=Femme enceinte <br>
                        5=Femme allaitante <br>
                        6=D&eacute;pistage en couple <br>
                        7=AES <br>
                        8=PrEP <br>
                        9=Autres (&agrave; pr&eacute;ciser)
                    </th>
                    <th class="text-center align-middle">
                        Test-1* (Poste <br> et laboratoire) <br> Nom de kit <br>
                        <span class="font-weight-bold text-info">${registerInfo.testing1Kit.name}</span>
                    </th>
                    <c:if test="${registerInfo.screeningSiteType == 'Laboratoire'}">
                        <th class="text-center align-middle">
                            Test-2* <br>(Laboratoire) <br> Nom de kit <br>
                            <span class="font-weight-bold text-info">${registerInfo.labTesting2Kit.name}</span>
                        </th>
                    </c:if>
                    <th class="text-center align-middle">
                        Test-2* Poste ou Test 3 <br>(Tie-breaker <br> laboratoire) <br> Nom de kit <br>
                        <span class="text-info font-weight-bold">${registerInfo.testing2Kit.name}</span>
                    </th>
                    <th rowspan="3" class="align-middle">
                        R&eacute;sultat final <br>Donn&eacute; au client <br>
                        Date annonce du r&eacute;sultat
                    </th>
                    <th rowspan="3" class="align-middle">
                        Retesting <br>
                        1 Oui <br>
                        2 Non
                    </th>
                    <th rowspan="3" class="align-middle">
                        Commentaires
                    </th>
                    <th rowspan="3"></th>
                </tr>
                <tr>
                    <th class="align-middle text-center">
                        No de Lot <br>
                        <span class="font-weight-bold text-info">${registerInfo.testing1Kit.batchNumber}</span>
                    </th>
                    <c:if test="${registerInfo.screeningSiteType == 'Laboratoire'}">
                        <th class="align-middle">
                            No de Lot <br>
                            <span class="font-weight-bold text-info">${registerInfo.labTesting2Kit.batchNumber}</span>
                        </th>
                    </c:if>
                    <th class="align-middle text-center">
                        No de Lot <br>
                        <span class="font-weight-bold text-info">${registerInfo.testing2Kit.batchNumber}</span>
                    </th>
                </tr>
                <tr>
                    <th class="align-middle text-center">
                        Date <br> d'expiration <br>
                        <span class="font-weight-bold text-info">
                                <fmt:formatDate value="${registerInfo.testing1Kit.expiryDate}" pattern="dd/MM/yyyy" type="DATE"/>
                            </span>
                            <%--                        <br>--%>
                            <%--                        <span class="badge badge-success">${registerInfo.testing1Kit.countUsage} / ${registerInfo.testing1Kit.kitCountMax}</span>--%>
                    </th>
                    <c:if test="${registerInfo.screeningSiteType == 'Laboratoire'}">
                        <th class="align-middle text-center">
                            Date <br> d'expiration <br>
                            <span class="font-weight-bold text-info">
                                    <fmt:formatDate value="${registerInfo.labTesting2Kit.expiryDate}" pattern="dd/MM/yyyy" type="DATE"/>
                                </span>
                                <%--                            <br>--%>
                                <%--                            <span class="badge badge-success">${registerInfo.labTesting2Kit.countUsage} / ${registerInfo.labTesting2Kit.kitCountMax}</span>--%>
                        </th>
                    </c:if>
                    <th class="align-middle text-center">
                        Date <br> d'expiration <br>
                        <span class="font-weight-bold text-info">
                                <fmt:formatDate value="${registerInfo.testing2Kit.expiryDate}" pattern="dd/MM/yyyy" type="DATE"/>
                            </span>
                            <%--                        <br>--%>
                            <%--                        <span class="badge badge-success">${registerInfo.testing2Kit.countUsage} / ${registerInfo.testing2Kit.kitCountMax}</span>--%>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="align-middle text-center">
                        <form:input path="screeningDate" cssClass="form-control form-control-sm picker" />
                        <form:errors path="screeningDate" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <form:input path="screeningCode" cssClass="form-control form-control-sm" />
                        <form:errors path="screeningCode" cssClass="error"/>
                        <form:radiobutton path="sampling" cssClass="mt-2" value="EEQ" id="EEQ" label="EEQ"/>
                        <form:radiobutton path="sampling" cssClass="mt-2" value="CQI" id="CQI" label="CQI"/>
                    </td>
                    <td class="align-middle text-center">
                        <form:input path="profession" cssClass="form-control form-control-sm" />
                        <form:errors path="profession" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <form:input path="age" cssClass="form-control form-control-sm" />
                        <form:errors path="age" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <ul class="ks-cboxtags">
                            <li>
                                <form:radiobutton path="gender" cssClass="" value="M" id="M" />
                                <label for="M">M</label>
                            </li>
                            <li>
                                <form:radiobutton path="gender" cssClass="" value="F" id="F" />
                                <label for="F">F</label>
                            </li>
                        </ul>
                        <form:errors path="gender" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <form:input path="residence" cssClass="form-control form-control-sm" />
                        <form:errors path="residence" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <ul class="ks-cboxtags">
                            <li>
                                <form:radiobutton path="maritalStatus" cssClass="" value="ALONE" id="alone" />
                                <label for="alone">1</label>
                            </li>
                            <li>
                                <form:radiobutton path="maritalStatus" cssClass="" value="COUPLE" id="couple" />
                                <label for="couple">2</label>
                            </li>
                            <li>
                                <form:radiobutton path="maritalStatus" cssClass="" value="OTHER" id="otherStatus" />
                                <label for="otherStatus">3</label>
                            </li>
                        </ul>
                        <form:errors path="maritalStatus" cssClass="error"/>
                        <form:input path="otherMaritalStatus" cssClass="form-control form-control-sm" />
                        <form:errors path="otherMaritalStatus" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <ul class="ks-cboxtags">
                            <li>
                                <form:radiobutton path="populationType" cssClass="" value="GENERAL" id="PG" />
                                <label for="PG">1</label>
                            </li>
                            <li>
                                <form:radiobutton path="populationType" cssClass="" value="UD" id="UD" />
                                <label for="UD">2</label>
                            </li>
                            <li>
                                <form:radiobutton path="populationType" cssClass="" value="TS" id="TS" />
                                <label for="TS">3</label>
                            </li>
                            <li>
                                <form:radiobutton path="populationType" cssClass="" value="HSH" id="HSH" />
                                <label for="HSH">4</label>
                            </li>
                            <li>
                                <form:radiobutton path="populationType" cssClass="" value="PC" id="PC" />
                                <label for="PC">5</label>
                            </li>
                            <li>
                                <form:radiobutton path="populationType" cssClass="" value="OTHER" id="otherTypePop" />
                                <label for="otherTypePop">6</label>
                            </li>
                        </ul>
                        <form:errors path="populationType" cssClass="error"/>
                        <form:input path="otherPopulationType" cssClass="form-control form-control-sm" />
                        <form:errors path="otherPopulationType" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <ul class="ks-cboxtags">
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="VOL" id="vol" />
                                <label for="vol">0</label>
                            </li>
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="IST" id="IST" />
                                <label for="IST">1</label>
                            </li>
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="CONF" id="conf" />
                                <label for="conf">2</label>
                            </li>
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="INDEX" id="index" />
                                <label for="index">3</label>
                            </li>
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="FE" id="FE" />
                                <label for="FE">4</label>
                            </li>
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="FA" id="FA" />
                                <label for="FA">5</label>
                            </li>
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="DEP_COUPLE" id="coupleScreen" />
                                <label for="coupleScreen">6</label>
                            </li>
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="AES" id="AES" />
                                <label for="AES">7</label>
                            </li>
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="PREP" id="PrEP" />
                                <label for="PrEP">8</label>
                            </li>
                            <li>
                                <form:radiobutton path="screeningReason" cssClass="" value="OTHER" id="otherReason" />
                                <label for="otherReason">9</label>
                            </li>
                        </ul>
                        <form:errors path="screeningReason" cssClass="error"/>
                        <form:input path="otherScreeningReason" cssClass="form-control form-control-sm" />
                        <form:errors path="otherScreeningReason" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <ul class="ks-cboxtags">
                            <li>
                                <form:radiobutton path="invalidatedTest1" cssClass="" value="true"  id="test1INV" />
                                <label for="test1INV">INV</label>
                            </li>
                            <li>
                                <form:radiobutton path="test1Reaction" cssClass="" value="NON_REACTIVE" id="test1NR" />
                                <label for="test1NR">NR</label>
                            </li>
                            <li>
                                <form:radiobutton path="test1Reaction" cssClass="" value="REACTIVE" id="test1R" />
                                <label for="test1R">R</label>
                            </li>
                        </ul>
                        <form:errors path="test1Reaction" cssClass="error"/>
                    </td>
                    <c:if test="${registerInfo.screeningSiteType == 'Laboratoire'}">
                        <td class="align-middle text-center">
                            <ul class="ks-cboxtags">
                                <li>
                                    <form:radiobutton path="invalidatedTest2" cssClass="" value="true" id="labTestINV"/>
                                    <label for="labTestINV">INV</label>
                                </li>
                                <li>
                                    <form:radiobutton path="test2Reaction" cssClass="" value="NON_REACTIVE" id="labTestNR"/>
                                    <label for="test1NR">NR</label>
                                </li>
                                <li>
                                    <form:radiobutton path="test2Reaction" cssClass="" value="REACTIVE_1" id="labTestR1"/>
                                    <label for="labTestR1">R1</label>
                                </li>
                                <li>
                                    <form:radiobutton path="test2Reaction" cssClass="" value="REACTIVE_2" id="labTestR2"/>
                                    <label for="labTestR2">R2</label>
                                </li>
                                <li>
                                    <form:radiobutton path="test2Reaction" cssClass="" value="REACTIVE_1_2" id="labTestR12"/>
                                    <label for="labTestR12">R1+2</label>
                                </li>
                            </ul>
                            <form:errors path="test2Reaction" cssClass="error"/>

                        </td>
                    </c:if>
                    <td class="align-middle text-center">
                        <ul class="ks-cboxtags">
                            <li>
                                <form:radiobutton path="invalidatedTest3" cssClass="" id="test3INV" />
                                <label for="test3INV">INV</label>
                            </li>
                            <li>
                                <form:radiobutton path="test3Reaction" cssClass="" value="NON_REACTIVE" id="test3NR" />
                                <label for="test3NR">NR</label>
                            </li>
                            <li>
                                <form:radiobutton path="test3Reaction" cssClass="" value="REACTIVE" id="test3R" />
                                <label for="test3R">R</label>
                            </li>
                        </ul>
                        <form:errors path="test3Reaction" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <ul class="ks-cboxtags">
                            <li>
                                <form:radiobutton path="finalResult" cssClass="" value="NEG" id="neg" />
                                <label for="neg">NEG</label>
                            </li>
                            <li>
                                <form:radiobutton path="finalResult" cssClass="" value="POS" id="pos" />
                                <label for="pos">POS</label>
                            </li>
                            <c:if test="${registerInfo.screeningSiteType == 'Laboratoire'}">
                                <li>
                                    <form:radiobutton path="finalResult" cssClass="" value="POS1" id="pos1" />
                                    <label for="pos1">POS 1</label>
                                </li>
                                <li>
                                    <form:radiobutton path="finalResult" cssClass="" value="POS2" id="pos2" />
                                    <label for="pos2">POS 2</label>
                                </li>
                                <li>
                                    <form:radiobutton path="finalResult" cssClass="" value="POS1_2" id="pos12" />
                                    <label for="pos12">POS 1+2</label>
                                </li>
                            </c:if>
                            <li>
                                <form:radiobutton path="finalResult" cssClass="" value="IND" id="IND" />
                                <label for="IND">IND</label>
                            </li>
                        </ul>
                        <form:errors path="finalResult" cssClass="error"/>
                        <form:input path="resultAnnouncingDate" cssClass="form-control form-control-sm picker" />
                        <form:errors path="resultAnnouncingDate" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <ul class="ks-cboxtags">
                            <li>
                                <form:radiobutton path="retesting" cssClass="" value="1" id="yes" />
                                <label for="yes">1</label>
                            </li>
                            <li>
                                <form:radiobutton path="retesting" cssClass="" value="0" id="no" />
                                <label for="no">2</label>
                            </li>
                        </ul>
                        <form:errors path="retesting" cssClass="error"/>
                    </td>
                    <td class="align-middle text-center">
                        <form:input path="comment" cssClass="form-control form-control-sm" />
                    </td>
                    <td class="align-middle text-center">
                        <button class="btn btn-sm btn-success">
                            <i class="fa fa-plus"></i>
                        </button>
                    </td>
                </tr>
                <c:forEach var="screening" items="${hivScreenings}">
                    <tr>
                        <td class="text-center align-middle text-info font-weight-bold">
                            <fmt:formatDate value="${screening.screeningDate}" pattern="dd/MM/yyyy" type="DATE"/>
                        </td>
                        <td class="text-center align-middle text-info font-weight-bold">${screening.screeningCode}${screening.sampling}</td>
                        <td class="text-center align-middle text-info font-weight-bold">${screening.profession}</td>
                        <td class="text-center align-middle text-info font-weight-bold">${screening.age}</td>
                        <td class="text-center align-middle text-info font-weight-bold">${screening.gender}</td>
                        <td class="text-center align-middle text-info font-weight-bold">${screening.residence}</td>
                        <td class="text-center align-middle text-info font-weight-bold">
                            <c:choose>
                                <c:when test="${screening.maritalStatus == 'ALONE'}">
                                    1
                                </c:when>
                                <c:when test="${screening.maritalStatus == 'COUPLE'}">
                                    2
                                </c:when>
                                <c:when test="${screening.maritalStatus == 'OTHER'}">
                                    3 (${screening.otherMaritalStatus})
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="text-center align-middle text-info font-weight-bold">
                            <c:choose>
                                <c:when test="${screening.populationType == 'GENERAL'}">
                                    1
                                </c:when>
                                <c:when test="${screening.populationType == 'UD'}">
                                    2
                                </c:when>
                                <c:when test="${screening.populationType == 'TS'}">
                                    2
                                </c:when>
                                <c:when test="${screening.populationType == 'HSH'}">
                                    2
                                </c:when>
                                <c:when test="${screening.populationType == 'PC'}">
                                    2
                                </c:when>
                                <c:when test="${screening.populationType == 'OTHER'}">
                                    3 (${screening.otherPopulationType})
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="text-center align-middle text-info font-weight-bold">
                            <c:choose>
                                <c:when test="${screening.screeningReason == 'VOL'}">
                                    0
                                </c:when>
                                <c:when test="${screening.screeningReason == 'IST'}">
                                    1
                                </c:when>
                                <c:when test="${screening.screeningReason == 'CONF'}">
                                    2
                                </c:when>
                                <c:when test="${screening.screeningReason == 'INDEX'}">
                                    3
                                </c:when>
                                <c:when test="${screening.screeningReason == 'FE'}">
                                    4
                                </c:when>
                                <c:when test="${screening.screeningReason == 'FA'}">
                                    5
                                </c:when>
                                <c:when test="${screening.screeningReason == 'DEP_COUPLE'}">
                                    6
                                </c:when>
                                <c:when test="${screening.screeningReason == 'AES'}">
                                    7
                                </c:when>
                                <c:when test="${screening.screeningReason == 'PREP'}">
                                    8
                                </c:when>
                                <c:when test="${screening.screeningReason == 'OTHER'}">
                                    9 (${screening.otherScreeningReason})
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="text-center align-middle text-info font-weight-bold">
                                ${screening.invalidatedTest1 ? 'INV' : ''} &nbsp;&nbsp;
                            <c:choose>
                                <c:when test="${screening.test1Reaction == 'NON_REACTIVE'}">
                                    NR
                                </c:when>
                                <c:when test="${screening.test1Reaction == 'REACTIVE'}">
                                    R
                                </c:when>
                            </c:choose>
                        </td>
                        <c:if test="${registerInfo.screeningSiteType == 'Laboratoire'}">
                            <td class="text-center align-middle text-info font-weight-bold">
                                    ${screening.invalidatedTest2 ? 'INV' : ''} &nbsp;&nbsp;
                                <c:choose>
                                    <c:when test="${screening.test2Reaction == 'NON_REACTIVER'}">
                                        NR
                                    </c:when>
                                    <c:when test="${screening.test2Reaction == 'REACTIVE_1'}">
                                        R1
                                    </c:when>
                                    <c:when test="${screening.test2Reaction == 'REACTIVE_2'}">
                                        R2
                                    </c:when>
                                    <c:when test="${screening.test2Reaction == 'REACTIVE_1_2'}">
                                        R1+2
                                    </c:when>
                                </c:choose>
                            </td>
                        </c:if>
                        <td class="text-center align-middle text-info font-weight-bold">
                                ${screening.invalidatedTest3 ? 'INV' : ''} &nbsp;&nbsp;
                            <c:choose>
                                <c:when test="${screening.test3Reaction == 'NON_REACTIVE'}">
                                    NR
                                </c:when>
                                <c:when test="${screening.test3Reaction == 'REACTIVE'}">
                                    R
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="text-center align-middle text-info font-weight-bold">
                            <c:choose>
                                <c:when test="${screening.finalResult == 'NEG'}">
                                    NEG
                                </c:when>
                                <c:when test="${screening.finalResult == 'POS'}">
                                    POS
                                </c:when>
                                <c:when test="${screening.finalResult == 'POS1'}">
                                    POS 1
                                </c:when>
                                <c:when test="${screening.finalResult == 'POS2'}">
                                    POS 2
                                </c:when>
                                <c:when test="${screening.finalResult == 'POS1_2'}">
                                    POS 1+2
                                </c:when>
                                <c:when test="${screening.finalResult == 'IND'}">
                                    IND
                                </c:when>
                            </c:choose> <br>
                            <fmt:formatDate value="${screening.resultAnnouncingDate}" pattern="dd/MM/yyyy" type="DATE"/>
                        </td>
                        <td class="text-center align-middle text-info font-weight-bold">${screening.retesting == true ? 'Oui ' : 'Non'}</td>
                        <td class="text-center align-middle text-info font-weight-bold">${screening.comment}</td>
                        <td class="text-center align-middle text-info font-weight-bold">
                            <c:url value="/module/hivscreening/screening.form" var="editUrl">
                                <c:param name="registerId" value="${registerInfo.id}"/>
                                <c:param name="screeningId" value="${screening.id}"/>
                            </c:url>
                            <a href="${editUrl}" class="text-primary" style="text-decoration: none; color: white">
                                <i class="fa fa-edit"></i>
                            </a>
                            <c:url value="/module/hivscreening/deleteScreening.form" var="delUrl">
                                <c:param name="registerId" value="${registerInfo.id}"/>
                                <c:param name="screeningId" value="${screening.id}"/>
                            </c:url>
                            <a href="${delUrl}"
                               onclick="return confirm('Voulez-vous supprimer la ligne ?')"
                               class="text-danger" style="text-decoration: none; color: white">
                                <i class="fa fa-trash"></i>
                            </a>
                        </td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <div class="d-flex justify-content-between align-items-center">
                <div class="h4 mb-0">
                    TOTAL = ${numberOfScreening}
                </div>
                <c:if test="${numberOfScreening > 10}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                                <%--                                <c:if test="${first != 0}">--%>
                                <%--                                </c:if>--%>
                            <c:url value="/module/hivscreening/screening.form" var="previousUrl">
                                <c:param name="registerId" value="${registerInfo.id}"/>
                                <c:param name="first" value="${first - 1}"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${first != 0 ? previousUrl : '#'}"><i class="fa fa-arrow-left fa-2x"></i></a></li>

                            <c:if test="${last != page}">
                            </c:if>
                            <c:url value="/module/hivscreening/screening.form" var="nextUrl">
                                <c:param name="registerId" value="${registerInfo.id}"/>
                                <c:param name="first" value="${first + 1}"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${last != page ? nextUrl : '#'}"><i class="fa fa-arrow-right fa-2x"></i> </a></li>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </form:form>
    </div>
</div>

<%@include file="template/localFooter.jsp"%>

<%@ include file="/WEB-INF/template/footer.jsp"%>
