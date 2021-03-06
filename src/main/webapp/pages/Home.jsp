<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="_head.jsp"></jsp:include>
</head>
<body ng-app="qborrow">
	<div class="qcontainer-fluid">
		<jsp:include page="_header.jsp" />

		<div class="qcontainer">
			<div class="qrow">
				<!-- <div class="frameworkLeftMenuCell"> -->
				<%-- <jsp:include page="_left.jsp">
						<jsp:param name="menuActive" value="Home"/>
					</jsp:include> --%>
				<!-- 				</div> -->
				<div class="frameworkMainCell">
					<div class="qpage-header">
						<h1>
							Benveuto,
							<s:property value="userContext.realUserDn" />
						</h1>
					</div>
					<div class="qrow">
						<div class="qcol-md-3">
							<div class="conteneir-fast-menu"><s:url id="soggettoUrl" action="soggetto" escapeAmp="false"
								includeParams="none">
								<s:param name="task">mainPage</s:param>
							</s:url><i class="fa fa-male" aria-hidden="true"></i>
							<s:a href="%{#soggettoUrl}">Soggetto</s:a></div>
						</div>
						
						<div class="qcol-md-3">
							<div class="conteneir-fast-menu"><s:url id="oggettoUrl" action="oggetto" escapeAmp="false"
								includeParams="none">
								<s:param name="task">mainPage</s:param>
							</s:url><i class="fa fa-archive" aria-hidden="true"></i>
							<s:a href="%{#oggettoUrl}">Oggetto</s:a></div>
						</div>
						
						<div class="qcol-md-3">
							<div class="conteneir-fast-menu"><s:url id="prestitoUrl" action="prestito" escapeAmp="false"
								includeParams="none">
								<s:param name="task">mainPage</s:param>
							</s:url><i class="fa fa-university" aria-hidden="true"></i>
							<s:a href="%{#prestitoUrl}">Prestito</s:a></div>
						</div>
						
						<div class="qcol-md-3">
							<div class="conteneir-fast-menu"><s:url id="frmkUrl" namespace="/framework" action="admin"
								escapeAmp="false" includeParams="none">
								<s:param name="version">2</s:param>
							</s:url><i class="fa fa-briefcase" aria-hidden="true"></i>
							<s:a href="%{#frmkUrl}">Amministrazione</s:a></div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="_footer.jsp" />
	</div>
</body>
</html>
