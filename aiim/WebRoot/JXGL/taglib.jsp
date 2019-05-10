<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<script type="text/javascript" src="${contextPath}/dwr/util.js"></script>
<script type="text/javascript" src="${contextPath}/dwr/engine.js"></script>

<script type='text/javascript' src='${contextPath}/dwr/interface/PerformanceManageDWR.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/interface/ArchiveAppraisalDWR.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/interface/ArchivesCertificateManageDWR.js'></script>
<script type="text/javascript" src="${contextPath}/dwr/interface/attachedFileManageAction.js"></script>

