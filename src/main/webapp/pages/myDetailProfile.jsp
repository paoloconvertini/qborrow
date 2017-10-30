<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:url id="homeUrl" namespace="/" action="home" escapeAmp="false" includeParams="none">
	<s:param name="version">2</s:param>
</s:url>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="_head.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/controller/qxSoggettoController.js?_<jsp:include page='_version.jsp' />"></script>
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
<body ng-app="qborrow" ng-controller="qxSoggettoController" ng-init="editMyProfile();">
	<div class="qcontainer-fluid">
		<jsp:include page="_header.jsp" />

		<div class="qcontainer">
			<form name="forms.soggettoEditForm">
						<div class="qrow">
							<div class="qcol-md-12">
								<img src="{{scopeController.selectedRow.immagine}}" width="100" height="100"></img>
							</div> <!-- fine qcol -->
						</div><!-- fine qrow -->
						<div class="qrow">
							<div class="qcol-md-2">
								Ragione Sociale
							</div> <!-- fine qcol -->
							<div class="qcol-md-4" ng-class="{'qhas-error': forms.soggettoEditForm.email.$invalid}">
										
										<input type="text" 
											ng-model="scopeController.selectedRow.ragioneSociale" 
											id="ragioneSociale" 
											name="ragioneSociale"
											class="qform-control" />
										<div ng-messages="forms.soggettoEditForm.ragioneSociale.$error" role="alert">
										  	<div ng-message="notNull"><s:text name="error.notNull"/></div>
										  	<div ng-message="invalidAK"><s:text name="error.invalidAK"/></div>
										  	<div ng-message="notValid"><s:text name="error.notValid"/></div>
										  	<div ng-message="lenght"><s:text name="error.lenght"/></div>
										  	<div ng-message="dateToBeforeDateFrom"><s:text name="error.dateToBeforeDateFrom"/></div>
										  	<div ng-message="fieldToBeforeFieldFrom"><s:text name="error.fieldToBeforeFieldFrom"/></div>
										  	<div ng-message="notUnique"><s:text name="error.notUnique"/></div>
										  	<div ng-message="min"><s:text name="error.min"/></div>
										  	<div ng-message="max"><s:text name="error.max"/></div>
										  	<div ng-message="ognl"><s:text name="error.ognl"/></div>
										  	<div ng-message="pattern"><s:text name="error.pattern"/></div>
										  	<div ng-message="notBlank"><s:text name="error.notBlank"/></div>
										  	<div ng-message="qvpattern.message"><s:text name="error.qvpattern.message"/></div>
										  	<div ng-message="string.length"><s:text name="error.string.length"/></div>
										</div>
									
							</div> <!-- fine qcol -->
						</div> <!-- fine qrow -->
						
						<div class="qrow">
							<div class="qcol-md-2">
								Nome
							</div>  <!-- fine qcol -->
							<div class="qcol-md-4">
									<input type="text" ng-model="scopeController.selectedRow.nome" id="nome" name="nome" class="qform-control" />
									<div ng-messages="forms.soggettoEditForm.nome.$error" role="alert">
								  	<div ng-message="notNull"><s:text name="error.notNull"/></div>
								  	<div ng-message="invalidAK"><s:text name="error.invalidAK"/></div>
								  	<div ng-message="notValid"><s:text name="error.notValid"/></div>
								  	<div ng-message="lenght"><s:text name="error.lenght"/></div>
								  	<div ng-message="dateToBeforeDateFrom"><s:text name="error.dateToBeforeDateFrom"/></div>
								  	<div ng-message="fieldToBeforeFieldFrom"><s:text name="error.fieldToBeforeFieldFrom"/></div>
								  	<div ng-message="notUnique"><s:text name="error.notUnique"/></div>
								  	<div ng-message="min"><s:text name="error.min"/></div>
								  	<div ng-message="max"><s:text name="error.max"/></div>
								  	<div ng-message="ognl"><s:text name="error.ognl"/></div>
								  	<div ng-message="pattern"><s:text name="error.pattern"/></div>
								  	<div ng-message="notBlank"><s:text name="error.notBlank"/></div>
								  	<div ng-message="qvpattern.message"><s:text name="error.qvpattern.message"/></div>
								  	<div ng-message="string.length"><s:text name="error.string.length"/></div>
									</div>
							</div> <!-- fine qcol -->
						</div> <!-- fine qrow -->
						
						<div class="qrow">
							<div class="qcol-md-2"> 
								Cognome
							</div>  <!-- fine qcol -->
							<div class="qcol-md-4">	
								<input type="text" ng-model="scopeController.selectedRow.cognome" id="cognome" name="cognome" class="qform-control" />
									<div ng-messages="forms.soggettoEditForm.cognome.$error" role="alert">
									  	<div ng-message="notNull"><s:text name="error.notNull"/></div>
									  	<div ng-message="invalidAK"><s:text name="error.invalidAK"/></div>
									  	<div ng-message="notValid"><s:text name="error.notValid"/></div>
									  	<div ng-message="lenght"><s:text name="error.lenght"/></div>
									  	<div ng-message="dateToBeforeDateFrom"><s:text name="error.dateToBeforeDateFrom"/></div>
									  	<div ng-message="fieldToBeforeFieldFrom"><s:text name="error.fieldToBeforeFieldFrom"/></div>
									  	<div ng-message="notUnique"><s:text name="error.notUnique"/></div>
									  	<div ng-message="min"><s:text name="error.min"/></div>
									  	<div ng-message="max"><s:text name="error.max"/></div>
									  	<div ng-message="ognl"><s:text name="error.ognl"/></div>
									  	<div ng-message="pattern"><s:text name="error.pattern"/></div>
									  	<div ng-message="notBlank"><s:text name="error.notBlank"/></div>
									  	<div ng-message="qvpattern.message"><s:text name="error.qvpattern.message"/></div>
									  	<div ng-message="string.length"><s:text name="error.string.length"/></div>
									</div>
								</div> <!-- fine qcol -->
						</div> <!-- fine qrow -->
							
						<div class="qrow">
							<div class="qcol-md-2"> 
								Email
							</div> <!-- fine qcol -->
							<div class="qcol-md-4">
									<input type="text" ng-model="scopeController.selectedRow.email" id="email" name="email" ng-pattern="emailFormat" class="qform-control" />
									<div ng-messages="forms.soggettoEditForm.email.$error" role="alert">
									  	<div ng-message="notNull"><s:text name="error.notNull"/></div>
									  	<div ng-message="invalidAK"><s:text name="error.invalidAK"/></div>
									  	<div ng-message="notValid"><s:text name="error.notValid"/></div>
									  	<div ng-message="lenght"><s:text name="error.lenght"/></div>
									  	<div ng-message="dateToBeforeDateFrom"><s:text name="error.dateToBeforeDateFrom"/></div>
									  	<div ng-message="fieldToBeforeFieldFrom"><s:text name="error.fieldToBeforeFieldFrom"/></div>
									  	<div ng-message="notUnique"><s:text name="error.notUnique"/></div>
									  	<div ng-message="min"><s:text name="error.min"/></div>
									  	<div ng-message="max"><s:text name="error.max"/></div>
									  	<div ng-message="ognl"><s:text name="error.ognl"/></div>
									  	<div ng-message="pattern"><s:text name="error.pattern"/></div>
									  	<div ng-message="notBlank"><s:text name="error.notBlank"/></div>
									  	<div ng-message="qvpattern.message"><s:text name="error.qvpattern.message"/></div>
									  	<div ng-message="string.length"><s:text name="error.string.length"/></div>
									</div>
							</div> <!-- fine qcol -->
						</div> <!-- fine qrow -->	
						
						<div class="qrow">
							<div class="qcol-md-2">
								Data di nascita
							</div> <!-- fine qcol -->
							<div class="qcol-md-4">
									<div class="qdropdown" ng-class="{'qhas-error': forms.soggettoEditForm.dataCompleanno.$invalid}">
										<a class="qdropdown-toggle" id="dropdown2" role="qbutton" data-toggle="qdropdown" data-target="#" href="#">
											<div class="qinput-group">
												<input type="text" name="dataCompleanno" id="dataCompleanno"
													class="qform-control box-input-calendar"
													data-ng-model="scopeController.selectedRow.dataCompleanno"
													presetDate="true" dateformat="DD/MM/YYYY"> <span
													class="qinput-group-addon"><i class="fa fa-calendar"></i></span>
											</div>
										</a>
										<ul class="qdropdown-menu" role="qmenu" aria-labelledby="dLabel">
											<datetimepicker data-ng-model="scopeController.selectedRow.dataCompleanno" data-datetimepicker-config="{ minView:'day', modelType: 'Date' }" />
										</ul>
									</div>
									<div ng-messages="forms.soggettoEditForm.dataCompleanno.$error" role="alert">
										<div ng-message="notNull"> <s:text name="error.notNull" /></div>
										<div ng-message="invalidAK"> <s:text name="error.invalidAK" /></div>
										<div ng-message="notValid"><s:text name="error.notValid" /></div>
										<div ng-message="lenght"><s:text name="error.lenght" /></div>
										<div ng-message="dateToBeforeDateFrom"><s:text name="error.dateToBeforeDateFrom" /></div>
										<div ng-message="fieldToBeforeFieldFrom"><s:text name="error.fieldToBeforeFieldFrom" /></div>
										<div ng-message="notUnique"><s:text name="error.notUnique" /></div>
										<div ng-message="min"><s:text name="error.min" /></div>
										<div ng-message="max"><s:text name="error.max" /></div>
										<div ng-message="ognl"><s:text name="error.ognl" /></div>
										<div ng-message="pattern"><s:text name="error.pattern" /></div>
										<div ng-message="notBlank"><s:text name="error.notBlank" /></div>
										<div ng-message="qvpattern.message"><s:text name="error.qvpattern.message" /></div>
										<div ng-message="string.length"><s:text name="error.string.length" /></div>
										<div ng-message="dataNelFuturo"><s:text name="error.dataNelFuturo" /></div>
										<div ng-message="NonMaggiorenne"><s:text name="error.NonMaggiorenne" /></div>
									</div>
							</div> <!-- fine qcol -->
						</div> <!-- fine qrow -->		
							
				</form>
				<div class="box-footer qtext-center">
				<qbutton class="qbtn qbtn-default qbtn-flat">
										<a href="<s:property value="#homeUrl" escape="false" />"><i class="fa fa-arrow-circle-left"></i>
						<s:text name="button.back" /></a>
									</qbutton>
					<button ng-click="saveWithCompleanno()" class="qbtn qbtn-default qbtn-flat">
						<i class="fa fa-floppy-o"></i>&nbsp;
						<s:text name="button.save" />
					</button>
				</div>

			</div>
		
	
	<jsp:include page="_footer.jsp" />
	</div>
</body>
</html>
