<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form name="forms.prestitoListForm" novalidate>
	<div class="box box-framework">
		<div class="box-header with-border">
			<div class="qrow" style="margin: 1px 10px 10px 6px;display: inline-block;" ng-cloak>
				<label for="qt"  class="control-label text-yellow cursor" ng-click="filtriEspansi = !filtriEspansi">
					<span  class="fa fa-filter fa-lg collapse-plus text-framework">
						<i style="font-size: 12px;" ng-show="filtriEspansi != true" class="fa fa-lg fa-plus"></i>
						<i style="font-size: 12px;" ng-show="filtriEspansi == true" class="fa fa-lg fa-minus"></i>
					</span>
				</label>
			</div>
  			<h3 class="qh3 box-title">&nbsp;&nbsp;<s:text name="prestito.search.title"/></h3>
		</div>
		<div class="box-body" ng-show="!filtriEspansi" ng-cloak>
			<span class="filterDiv" ng-if="scopeController.search.dataPrestitoFrom != null && scopeController.search.dataPrestitoFrom != ''">
				<s:text name="prestito.search.dataPrestitoFrom"/>: 
				<b>
					{{scopeController.search.dataPrestitoFrom | date:"<s:text name="format.date4" />"}}
				</b>
				&nbsp;&nbsp;<i class="fa fa-times-circle" style="cursor: pointer;" ng-click="removeFilter('dataPrestitoFrom')"></i>
			</span>
			<span class="filterDiv" ng-if="scopeController.search.dataPrestitoTo != null && scopeController.search.dataPrestitoTo != ''">
				<s:text name="prestito.search.dataPrestitoTo"/>: 
				<b>
					{{scopeController.search.dataPrestitoTo | date:"<s:text name="format.date4" />"}}
				</b>
				&nbsp;&nbsp;<i class="fa fa-times-circle" style="cursor: pointer;" ng-click="removeFilter('dataPrestitoTo')"></i>
			</span>
			<span class="filterDiv" ng-if="scopeController.search.dataScadenzaFrom != null && scopeController.search.dataScadenzaFrom != ''">
				<s:text name="prestito.search.dataScadenzaFrom"/>: 
				<b>
					{{scopeController.search.dataScadenzaFrom | date:"<s:text name="format.date4" />"}}
				</b>
				&nbsp;&nbsp;<i class="fa fa-times-circle" style="cursor: pointer;" ng-click="removeFilter('dataScadenzaFrom')"></i>
			</span>
			<span class="filterDiv" ng-if="scopeController.search.dataScadenzaTo != null && scopeController.search.dataScadenzaTo != ''">
				<s:text name="prestito.search.dataScadenzaTo"/>: 
				<b>
					{{scopeController.search.dataScadenzaTo | date:"<s:text name="format.date4" />"}}
				</b>
				&nbsp;&nbsp;<i class="fa fa-times-circle" style="cursor: pointer;" ng-click="removeFilter('dataScadenzaTo')"></i>
			</span>
		</div>
		<div class="box-body qfade " ng-show="filtriEspansi" ng-cloak>
			<div class="qrow">
				<div class="qcol-xs-6 qcol-sm-6 qcol-md-3" ng-class="{'qhas-error': forms.prestitoListForm.dataPrestitoFrom.$invalid}">
					<label for="dataPrestitoFrom"><s:text name="prestito.search.dataPrestitoFrom"/>:</label>
					<div>

		              		<div class="qdropdown">
								<a class="qdropdown-toggle" id="dropdown2" role="qbutton" data-toggle="qdropdown" data-target="#" href="#">
									<div class="qinput-group">
							    		<input type="text" name="dataPrestitoFrom" id="dataPrestitoFrom" class="qform-control box-input-calendar" data-ng-model="scopeController.search.dataPrestitoFrom" presetDate="true" dateformat="DD/MM/YYYY">
							    		<span class="qinput-group-addon"><i class="fa fa-calendar"></i></span>
							    	</div>
							  	</a>
							  	<ul class="qdropdown-menu" role="qmenu" aria-labelledby="dLabel">
							    	<datetimepicker data-ng-model="scopeController.search.dataPrestitoFrom" data-datetimepicker-config="{ minView:'day', modelType: 'Date' }"/>
							  	</ul>
							</div>
						
		              	
						<div ng-messages="forms.prestitoListForm.dataPrestitoFrom.$error" role="alert">
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
				<div class="qcol-xs-6 qcol-sm-6 qcol-md-3" ng-class="{'qhas-error': forms.prestitoListForm.dataPrestitoTo.$invalid}">
					<label for="dataPrestitoTo"><s:text name="prestito.search.dataPrestitoTo"/>:</label>
					<div>

		              		<div class="qdropdown">
								<a class="qdropdown-toggle" id="dropdown2" role="qbutton" data-toggle="qdropdown" data-target="#" href="#">
									<div class="qinput-group">
							    		<input type="text" name="dataPrestitoTo" id="dataPrestitoTo" class="qform-control box-input-calendar" data-ng-model="scopeController.search.dataPrestitoTo" presetDate="true" dateformat="DD/MM/YYYY">
							    		<span class="qinput-group-addon"><i class="fa fa-calendar"></i></span>
							    	</div>
							  	</a>
							  	<ul class="qdropdown-menu" role="qmenu" aria-labelledby="dLabel">
							    	<datetimepicker data-ng-model="scopeController.search.dataPrestitoTo" data-datetimepicker-config="{ minView:'day', modelType: 'Date' }"/>
							  	</ul>
							</div>
						
		              	
						<div ng-messages="forms.prestitoListForm.dataPrestitoTo.$error" role="alert">
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
				<div class="qcol-xs-6 qcol-sm-6 qcol-md-3" ng-class="{'qhas-error': forms.prestitoListForm.dataScadenzaFrom.$invalid}">
					<label for="dataScadenzaFrom"><s:text name="prestito.search.dataScadenzaFrom"/>:</label>
					<div>

		              		<div class="qdropdown">
								<a class="qdropdown-toggle" id="dropdown2" role="qbutton" data-toggle="qdropdown" data-target="#" href="#">
									<div class="qinput-group">
							    		<input type="text" name="dataScadenzaFrom" id="dataScadenzaFrom" class="qform-control box-input-calendar" data-ng-model="scopeController.search.dataScadenzaFrom" presetDate="true" dateformat="DD/MM/YYYY">
							    		<span class="qinput-group-addon"><i class="fa fa-calendar"></i></span>
							    	</div>
							  	</a>
							  	<ul class="qdropdown-menu" role="qmenu" aria-labelledby="dLabel">
							    	<datetimepicker data-ng-model="scopeController.search.dataScadenzaFrom" data-datetimepicker-config="{ minView:'day', modelType: 'Date' }"/>
							  	</ul>
							</div>
						
		              	
						<div ng-messages="forms.prestitoListForm.dataScadenzaFrom.$error" role="alert">
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
				<div class="qcol-xs-6 qcol-sm-6 qcol-md-3" ng-class="{'qhas-error': forms.prestitoListForm.dataScadenzaTo.$invalid}">
					<label for="dataScadenzaTo"><s:text name="prestito.search.dataScadenzaTo"/>:</label>
					<div>

		              		<div class="qdropdown">
								<a class="qdropdown-toggle" id="dropdown2" role="qbutton" data-toggle="qdropdown" data-target="#" href="#">
									<div class="qinput-group">
							    		<input type="text" name="dataScadenzaTo" id="dataScadenzaTo" class="qform-control box-input-calendar" data-ng-model="scopeController.search.dataScadenzaTo" presetDate="true" dateformat="DD/MM/YYYY">
							    		<span class="qinput-group-addon"><i class="fa fa-calendar"></i></span>
							    	</div>
							  	</a>
							  	<ul class="qdropdown-menu" role="qmenu" aria-labelledby="dLabel">
							    	<datetimepicker data-ng-model="scopeController.search.dataScadenzaTo" data-datetimepicker-config="{ minView:'day', modelType: 'Date' }"/>
							  	</ul>
							</div>
						
		              	
						<div ng-messages="forms.prestitoListForm.dataScadenzaTo.$error" role="alert">
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
			</div>	                		
		</div>
		<div class="box-footer qtext-center" ng-show="filtriEspansi">
			<button ng-click="filtriEspansi = false;search();" class="qbtn btn-framework-color"><i class="fa fa-search"></i>&nbsp;<s:text name="button.search"/></button>
			<button ng-click="resetSearch()" class="qbtn btn-framework-color"><i class="fa fa-undo"></i>&nbsp;<s:text name="button.reset"/></button>
		</div>
	</div>
</form>

<div class="box box-framework" >
	<div class="box-body qtext-center" ng-if="!(scopeController.result.list!= null && scopeController.result.list.length>0)" >
		<div class="qpull-right" style="padding: 10px;" ng-hide="popup">
        	<button class="qbtn btn-framework-color" ng-click="create()" type="button"><i class="fa fa-plus-circle"></i>&nbsp;<s:text name="button.new" /></button>
		</div>
    	<s:text name="prestito.list.noResults"></s:text>
   	</div>
	<div class="box-header" ng-if="scopeController.result.list!= null && scopeController.result.list.length>0" ng-cloak>
	  	<span><span class="qbadge bg-framework">{{ scopeController.result.count }}</span>&nbsp;<s:text name="prestito.list.numRecord"/></span>
		<div class="qpull-right">
			<span class="dropdown" style="display:table-cell;height:40px;width:40px;text-align: center; vertical-align: middle; margin: 2px;">
				<a class="action-icon" ng-click="openOrderMenu = !openOrderMenu" style="color: #000;">
					<i class="fa fa-sort fa-2x" ng-class="{'active-order-icon':scopeController.search.order > 0}"></i>
				</a>
				<ul class="qdropdown-menu qdropdown-menu-right" ng-show="openOrderMenu" style="display: block;"> 											
					<li style="padding: 10px;">
						<s:text name="prestito.list.order.dataPrestito"/>
						<span class="pull-right">
							<i class="fa fa-sort-amount-asc" style="cursor: pointer;" ng-class="{'active-order-icon': scopeController.search.order == 1}" ng-click="orderBy(1); openOrderMenu = false;"></i>
							<i class="fa fa-sort-amount-desc" style="cursor: pointer;" ng-class="{'active-order-icon': scopeController.search.order == 2}" ng-click="orderBy(2); openOrderMenu = false;"></i>
						</span>
					</li> 											
					<li style="padding: 10px;">
						<s:text name="prestito.list.order.dataScadenza"/>
						<span class="pull-right">
							<i class="fa fa-sort-amount-asc" style="cursor: pointer;" ng-class="{'active-order-icon': scopeController.search.order == 3}" ng-click="orderBy(3); openOrderMenu = false;"></i>
							<i class="fa fa-sort-amount-desc" style="cursor: pointer;" ng-class="{'active-order-icon': scopeController.search.order == 4}" ng-click="orderBy(4); openOrderMenu = false;"></i>
						</span>
					</li>											
				</ul>
			</span>
			<div class="qbtn-group" ng-hide="popup" ng-hide="popup">
                 <button class="qbtn btn-framework-color" ng-click="create()" type="button"><i class="fa fa-plus-circle"></i>&nbsp;<s:text name="button.new" /></button>
                 <button data-toggle="qdropdown" class="qbtn btn-framework-color qdropdown-toggle" type="button" aria-expanded="false">
                   <span class="qcaret"></span>
                   <span class="qsr-only"></span>
                 </button>
                 <ul role="qmenu" class="qdropdown-menu qdropdown-menu-right">
                   	<li ng-click="exportXLS()"><a><i class="fa fa-file-excel-o"></i>&nbsp;<s:text name="button.excel" /></a></li>
                 </ul>
          	</div>		
		</div>
	</div>
  	<div class="box-body" ng-if="scopeController.result.list!= null && scopeController.result.list.length>0" ng-cloak>
    	<table class="qtable qtable-hover">
			<thead>
				<tr>
					<th class="qtext-left"><s:text name="prestito.list.soggettoBeneficiario"/></th>
					<th class="qtext-left"><s:text name="prestito.list.oggettoPrestato"/></th>
					<th class="qtext-center"><s:text name="prestito.list.dataPrestito"/></th>
					<th class="qtext-center"><s:text name="prestito.list.dataScadenza"/></th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="row in scopeController.result.list">
					<td>{{ row.soggettoBeneficiario.nome }} {{ row.soggettoBeneficiario.cognome }}</td>
					<td>{{ row.oggettoPrestato.titolo }}</td>
					<td class="qtext-center" nowrap="nowrap">{{ row.dataPrestito  | date:"<s:text name="format.date3" />" }}</td>
					<td class="qtext-center" nowrap="nowrap">{{ row.dataScadenza  | date:"<s:text name="format.date3" />" }}</td>
					<td class="qtext-right">
						<div class="qbtn-group" ng-hide="popup">
		                  	<button class="qbtn btn-framework-color" ng-click="edit(row)" type="button"><i class="fa fa-pencil"></i>&nbsp;<s:text name="button.edit" /></button>
		                   	<button data-toggle="qdropdown" class="qbtn btn-framework-color qdropdown-toggle" type="button" aria-expanded="false">
		                       	<span class="qcaret"></span>
		                       	<span class="qsr-only"></span>
		                    </button>
		                    <ul role="qmenu" class="qdropdown-menu qdropdown-menu-right">
		                       	<li ng-click="delete(row)"><a><i class="fa fa-trash-o"></i>&nbsp;<s:text name="button.delete" /></a></li>
		                    </ul>
		              	</div>
		              	<div ng-show="popup">		 	
							<button class="qbtn btn-framework-color" ng-click="select(row)" type="button"><i class="fa fa-check-circle"></i>&nbsp;<s:text name="button.select" /></button>		 	
						</div>		
					</td>
				</tr>
			</tbody>
		</table>
		<div class="qtext-center">
			<pagination 
				ng-model="scopeController.search.page" 
				total-items="scopeController.result.count" 
				max-size="5" 
				direction-links="false" 
				boundary-links="true" 
				first-text="&lt;" 
				last-text="&gt;" 
				rotate="false" 
				ng-change="list()"
				style="margin: 0px;"
			/>
		</div>
		<div class="qtext-center" ng-show="popup">
			<button class="qbtn btn-framework-color" ng-click="close()" type="button"><i class="fa fa-undo"></i>&nbsp;<s:text name="button.close" /></button>		 	
		</div>	
	</div>
</div>					
				
