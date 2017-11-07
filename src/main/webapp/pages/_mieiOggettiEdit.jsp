<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form name="forms.oggettoEditForm" novalidate>
	<div class="box box-framework" >
		<div class="box-header with-border">
			<h3 class="qh3 box-title"><s:text name="oggetto.edit.title"/></h3>
		</div>
		
		<div class="box-body">
			<div class="qrow">		
				<div class="qcol-md-6 qcol-sm-6 qcol-xs-12" ng-class="{'qhas-error': forms.oggettoEditForm.titolo.$invalid}">
					<label for="titolo"><s:text name="oggetto.edit.titolo"/></label>
					<input type="text" 
						ng-model="scopeController.selectedRow.titolo" 
						id="titolo" 
						name="titolo"
						class="qform-control" />
					<div ng-messages="forms.oggettoEditForm.titolo.$error" role="alert">
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
				</div>
			</div>
			<div class="qrow">		
				<div class="qcol-md-6 qcol-sm-6 qcol-xs-12" ng-class="{'qhas-error': forms.oggettoEditForm.descrizione.$invalid}">
					<label for="descrizione"><s:text name="oggetto.edit.descrizione"/></label>
					<input type="text" 
						ng-model="scopeController.selectedRow.descrizione" 
						id="descrizione" 
						name="descrizione"
						class="qform-control" />
					<div ng-messages="forms.oggettoEditForm.descrizione.$error" role="alert">
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
				</div>		
			</div>
			<div class="qrow">		
				<div class="qcol-md-6 qcol-sm-6 qcol-xs-12" ng-class="{'qhas-error': forms.oggettoEditForm.categoria.$invalid}">
					<label for="categoria"><s:text name="oggetto.edit.categoria"/></label>
					<qs2:attribute2Input 
						id="categoria" 
						cssClass="qform-control" 
						emptyOption="true" 
						onlyActive="true" 
						name="categoria" 
						ng-model="scopeController.selectedRow.categoria"
						type="QBO001_categoria" />
					<div ng-messages="forms.oggettoEditForm.categoria.$error" role="alert">
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
				</div>		
			</div>
			
			
			<div class="qrow">		
				<div class="qcol-md-6 qcol-sm-6 qcol-xs-12" ng-class="{'qhas-error': forms.oggettoEditForm.soggettoBeneficiario.$invalid}">
					<label for="soggettoBeneficiario">In prestito a</label>
				<!-- 	<input id="soggettoBeneficiario" name="soggettoBeneficiario"
						ng-model="scopeController.selectedRow.prestito.soggettoBeneficiario"
						ng-options="option as option.username for option in scopeController.soggettoList"
						class="qform-control" /> -->
						
						<input id="soggettoBeneficiario" name="soggettoBeneficiario"
						ng-model="scopeController.selectedRow.prestito.soggettoBeneficiario"
						ng-disabled="{{scopeController.selectedRow.oggettoPrestato == true}}"
						typeahead="state.username for state in scopeController.result.list | filter:$viewValue" typeahead-min-length='3'
						class="qform-control" />
						
						
					<div ng-messages="forms.oggettoEditForm.soggettoBeneficiario.$error" role="alert">
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
				</div>
			</div>
							
				<div class="qrow"  ng-class="{'qhas-error': forms.oggettoEditForm.dataPrestito.$invalid}">
								<div class="qcol-md-6 qcol-sm-6 qcol-xs-12">
									Data prestito
										<div class="qdropdown" >
											<a class="qdropdown-toggle" id="dropdown2" role="qbutton" data-toggle="qdropdown" data-target="#" href="#">
												<div class="qinput-group">
													<input type="text" name="dataPrestito" id="dataPrestito"
													ng-disabled="{{scopeController.selectedRow.oggettoPrestato == true}}"
														class="qform-control box-input-calendar"
														data-ng-model="scopeController.selectedRow.prestito.dataPrestito"
														presetDate="false" dateformat="DD/MM/YYYY"> <span
														class="qinput-group-addon"><i class="fa fa-calendar"></i></span>
												</div>
											</a>
											<ul class="qdropdown-menu" role="qmenu" aria-labelledby="dLabel">
												<datetimepicker data-ng-model="scopeController.selectedRow.prestito.dataPrestito" data-datetimepicker-config="{ minView:'day', modelType: 'Date' }" />
											</ul>
										</div>
										<div ng-messages="forms.oggettoEditForm.datadataPrestito.$error" role="alert">
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
										</div>
								</div> <!-- fine qcol -->
							</div> <!-- fine qrow -->
							
			<div class="qrow"  ng-class="{'qhas-error': forms.oggettoEditForm.dataScadenza.$invalid}">
								<div class="qcol-md-6 qcol-sm-6 qcol-xs-12">
									Data scadenza
								
										<div class="qdropdown" >
											<a class="qdropdown-toggle" id="dropdown2" role="qbutton" data-toggle="qdropdown" data-target="#" href="#">
												<div class="qinput-group">
													<input type="text" name="dataScadenza" id="dataScadenza"
													ng-disabled="{{scopeController.selectedRow.oggettoPrestato == true}}"
														class="qform-control box-input-calendar"
														data-ng-model="scopeController.selectedRow.prestito.dataScadenza"
														presetDate="false" dateformat="DD/MM/YYYY"> <span
														class="qinput-group-addon"><i class="fa fa-calendar"></i></span>
												</div>
											</a>
											<ul class="qdropdown-menu" role="qmenu" aria-labelledby="dLabel">
												<datetimepicker data-ng-model="scopeController.selectedRow.prestito.dataScadenza" data-datetimepicker-config="{ minView:'day', modelType: 'Date' }" />
											</ul>
										</div>
										<div ng-messages="forms.oggettoEditForm.dataScadenza.$error" role="alert">
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
										</div>
								
							</div> <!-- fine qrow -->
					</div>		
		</div>
		<div class="box-footer qtext-center">
			<button ng-click="save()" class="qbtn btn-framework-color"><i class="fa fa-floppy-o"></i>&nbsp;<s:text name="button.save"/></button>
			<button ng-click="back()" class="qbtn btn-framework-color"><i class="fa fa-arrow-circle-left"></i>&nbsp;<s:text name="button.back"/></button>
		</div>
	</div>
</form>