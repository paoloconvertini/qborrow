<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form name="forms.oggettoListForm" novalidate ng-init="listMieiOggetti();">
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
  			<h3 class="qh3 box-title">&nbsp;&nbsp;<s:text name="oggetto.search.title"/></h3>
		</div>
		<div class="box-body" ng-show="!filtriEspansi" ng-cloak>
			<span class="filterDiv" ng-if="scopeController.search.titolo != null && scopeController.search.titolo != ''">
				<s:text name="oggetto.search.titolo"/>: 
				<b>
					{{scopeController.search.titolo}}
				</b>
				&nbsp;&nbsp;<i class="fa fa-times-circle" style="cursor: pointer;" ng-click="removeFilter('titolo')"></i>
			</span>
			<span class="filterDiv" ng-if="scopeController.search.categoria != null && scopeController.search.categoria != ''">
				<s:text name="oggetto.search.categoria"/>: 
				<b>
					{{ scopeController.search.categoria | sysattribute:'QBO001_categoria' }}
				</b>
				&nbsp;&nbsp;<i class="fa fa-times-circle" style="cursor: pointer;" ng-click="removeFilter('categoria')"></i>
			</span>
			<span class="filterDiv" ng-if="scopeController.search.proprietario != null && scopeController.search.proprietario != ''">
				<s:text name="oggetto.search.proprietario"/>: 
				<b>
					{{scopeController.search.proprietario_description}}
				</b>
				&nbsp;&nbsp;<i class="fa fa-times-circle" style="cursor: pointer;" ng-click="removeFilter('proprietario')"></i>
			</span>
		</div>
		<div class="box-body qfade " ng-show="filtriEspansi" ng-cloak>
			<div class="qrow">
				<div class="qcol-xs-12 qcol-sm-12 qcol-md-6" ng-class="{'qhas-error': forms.oggettoListForm.titolo.$invalid}">
					<label for="titolo"><s:text name="oggetto.search.titolo"/>:</label>
					<div>
						<input type="text" ng-model="scopeController.search.titolo" id="titolo" class="qform-control" />
						<div ng-messages="forms.oggettoListForm.titolo.$error" role="alert">
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
					<div class="qcol-xs-12 qcol-sm-12 qcol-md-6" ng-class="{'qhas-error': forms.oggettoListForm.isInPrsetito.$invalid}">
						<label for="isInPrestito"><s:text name="oggetto.search.isInPrestito"/>:</label>
						<select id="isInPrestito" name="isInPrestito" class="qform-control" ng-model="scopeController.search.isInPrestito">
							<option></option>
							<option value="true">In prestito</option>
							<option value="false">Non in prestito</option>
						</select>
					</div>
				<div class="qcol-xs-12 qcol-sm-12 qcol-md-6" ng-class="{'qhas-error': forms.oggettoListForm.categoria.$invalid}">
					<label for="categoria"><s:text name="oggetto.search.categoria"/>:</label>
					<div>
						<qs2:attribute2Input 
							id="categoria" 
							cssClass="qform-control" 
							emptyOption="true" 
							onlyActive="true" 
							name="categoria" 
							ng-model="scopeController.search.categoria"
							type="QBO001_categoria" />
						<div ng-messages="forms.oggettoListForm.categoria.$error" role="alert">
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
			<button ng-click="filtriEspansi = false;searchMieiOggetti();" class="qbtn btn-framework-color"><i class="fa fa-search"></i>&nbsp;<s:text name="button.search"/></button>
			<button ng-click="resetSearch()" class="qbtn btn-framework-color"><i class="fa fa-undo"></i>&nbsp;<s:text name="button.reset"/></button>
		</div>
	</div>
</form>

<div class="box box-framework" >
	<div class="box-body qtext-center" ng-if="!(scopeController.result.list!= null && scopeController.result.list.length>0)" >
		<div class="qpull-right" style="padding: 10px;" ng-hide="popup">
        	<button class="qbtn btn-framework-color" ng-click="create()" type="button"><i class="fa fa-plus-circle"></i>&nbsp;<s:text name="button.new" /></button>
		</div>
    	<s:text name="oggetto.list.noResults"></s:text>
   	</div>
	<div class="box-header" ng-if="scopeController.result.list!= null && scopeController.result.list.length>0" ng-cloak>
	  	<span><span class="qbadge bg-framework">{{ scopeController.result.count }}</span>&nbsp;<s:text name="oggetto.list.numRecord"/></span>
		<div class="qpull-right">
			<span class="dropdown" style="display:table-cell;height:40px;width:40px;text-align: center; vertical-align: middle; margin: 2px;">
				<a class="action-icon" ng-click="openOrderMenu = !openOrderMenu" style="color: #000;">
					<i class="fa fa-sort fa-2x" ng-class="{'active-order-icon':scopeController.search.order > 0}"></i>
				</a>
				<ul class="qdropdown-menu qdropdown-menu-right" ng-show="openOrderMenu" style="display: block;"> 											
					<li style="padding: 10px;">
						<s:text name="oggetto.list.order.titolo"/>
						<span class="pull-right">
							<i class="fa fa-sort-amount-asc" style="cursor: pointer;" ng-class="{'active-order-icon': scopeController.search.order == 1}" ng-click="orderBy(1); openOrderMenu = false;"></i>
							<i class="fa fa-sort-amount-desc" style="cursor: pointer;" ng-class="{'active-order-icon': scopeController.search.order == 2}" ng-click="orderBy(2); openOrderMenu = false;"></i>
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
					<th class="qtext-left"><s:text name="oggetto.list.titolo"/></th>
					<th class="qtext-left"><s:text name="oggetto.list.descrizione"/></th>
					<th class="qtext-left"><s:text name="oggetto.list.categoria"/></th>
					<th class="qtext-left"><s:text name="oggetto.list.inPrestito"/></th>
					<th class="qtext-left"><s:text name="oggetto.list.beneficiario"/></th>
					<th class="qtext-left"><s:text name="oggetto.list.dataScadenza"/></th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="row in scopeController.result.list">
					<td>{{ row.titolo }}</td>
					<td>{{ row.descrizione }}</td>
					<td>{{ row.categoria | sysattribute:'QBO001_categoria' }}</td>
					<td ng-if="row.oggettoPrestato == true">
						<i class="fa fa-check qtext-success" aria-hidden="true"></i>
					</td>
						<td ng-if="row.oggettoPrestato == false">
						<i class="fa fa-times qtext-danger" aria-hidden="true"></i>
					</td>
						<td>{{ row.prestito.soggettoBeneficiario.nome }} {{ row.prestito.soggettoBeneficiario.cognome }}</td>
					
					<td class="qtext-left" >{{ row.prestito.dataScadenza  | date:"<s:text name="format.date4" />"}}</td>
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
				
