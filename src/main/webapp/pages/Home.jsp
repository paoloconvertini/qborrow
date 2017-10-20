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
					<div class="qpage-header qtext-warning">
						<h1>
							***ATTENZIONE!<br>Questa pagina di HOME &egrave; stata
							generata automaticamente.<br> Occorre effetuare un restyling
							grafico per renderla conforme allo scopo per cui &egrave; stato
							creato il progetto.
						</h1>
					</div>
					<div class="qrow">
						<div class="qcol-xs-12">
							<ul class="qnav qnav-pills qnav-stacked">
								<s:url id="oggettoUrl" action="oggetto" escapeAmp="false"
									includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url>
								<li><s:a href="%{#oggettoUrl}">Oggetto</s:a></li>
								<s:url id="prestitoUrl" action="prestito" escapeAmp="false"
									includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url>
								<li><s:a href="%{#prestitoUrl}">Prestito</s:a></li>
								<s:url id="soggettoUrl" action="soggetto" escapeAmp="false"
									includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url>
								<li><s:a href="%{#soggettoUrl}">Soggetto</s:a></li>
								<s:url id="frmkUrl" namespace="/framework" action="admin"
									escapeAmp="false" includeParams="none">
									<s:param name="version">2</s:param>
								</s:url>
								<li><s:a href="%{#frmkUrlUrl}">Amministrazione Framework</s:a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
				</div>
			<jsp:include page="_footer.jsp" />
		</div>
</body>
</html>
