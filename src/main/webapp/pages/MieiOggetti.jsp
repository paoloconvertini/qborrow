<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
  	<head>
  		<jsp:include page="_head.jsp"></jsp:include>
 		<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/controller/qxOggettoController.js?_<jsp:include page='_version.jsp' />"></script>
		<qs2:attributeMapAngularFilter type="QBO001_categoria" />
  		<script type="text/javascript">
	var module = angular.module('qborrow');
	module.constant('labelService', {
	  	'swalWarnDeleteTitle':'<s:property value="getText('label.swalWarnDeleteTitle')" escapeJavaScript="true" escapeHtml="false"/>',
		'swalWarnDeleteMessage':'<s:property value="getText('label.swalWarnDeleteMessage')" escapeJavaScript="true" escapeHtml="false"/>',
		'swalWarnDeleteButton':'<s:property value="getText('label.swalWarnDeleteButton')" escapeJavaScript="true" escapeHtml="false"/>',
		'swalSuccDeleteTitle':'<s:property value="getText('label.swalSuccDeleteTitle')" escapeJavaScript="true" escapeHtml="false"/>',
		'swalSuccDeleteMessage':'<s:property value="getText('label.swalSuccDeleteMessage')" escapeJavaScript="true" escapeHtml="false"/>'
	});
		</script>
  	</head>
  	<body ng-app="qborrow"> 
  		<div class="qcontainer-fluid"> 
  			<jsp:include page="_header.jsp" />
	  		<div class="qcontainer"  >
		  		<%-- <div class="frameworkLeftMenuCell">
					<jsp:include page="_left.jsp">
						<jsp:param name="menuActive" value="oggetto"/>
					</jsp:include>
				</div> --%>
  				<div class="frameworkMainCell" ng-controller="qxOggettoController" ng-include="scopeController.selectedPage">
					
  				</div>
  				<script type="text/ng-template" id="list"><jsp:include page="_mieiOggetti.jsp" /></script>
  				<script type="text/ng-template" id="edit"><jsp:include page="_mieiOggettiEdit.jsp" /></script>		
			</div>
  		 	<jsp:include page="_footer.jsp" />
	  	</div>	
  	</body>
</html>
