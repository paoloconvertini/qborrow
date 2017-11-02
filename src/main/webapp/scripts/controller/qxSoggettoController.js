var qborrowApp = angular.module('qborrow');

qborrowApp.controller('qxSoggettoController', ['$scope', 'qxQborrowHttpService', '$modal', 'labelService', 'SweetAlert', 'qborrowConfig', function ($scope, qxQborrowHttpService, $modal, labelService, SweetAlert, qborrowConfig){
	
	$scope.forms = {};
	$scope.scopeController = {}; 
	$scope.scopeController.selectedPage = 'list';
	$scope.scopeController.search= {};
	$scope.scopeController.search.page = 1;
	$scope.emailFormat = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
	
	
	$scope.search = function () {
		$scope.scopeController.search.order = 0;
		$scope.scopeController.search.page = 1;
		$scope.list();
	}
	
	$scope.orderBy = function(orderBy) {
		$scope.scopeController.search.order = orderBy;
		$scope.scopeController.search.page = 1;
		$scope.list();
	}
	
	$scope.removeFilter = function(filtername) {
		$scope.scopeController.search[filtername] = null;
		if($scope.scopeController.search[filtername + '_description'] != undefined) {
			$scope.scopeController.search[filtername + '_description'] = null;
		}
		$scope.scopeController.search.page = 1;
		$scope.list();
	}
	
	$scope.list = function () {
		qxQborrowHttpService.getSoggettoList($scope.scopeController, $scope.forms.soggettoListForm);
	}
	
	$scope.search = function () {
		qxQborrowHttpService.getSoggettoList($scope.scopeController, $scope.forms.soggettoListForm);
	}
	
	$scope.edit = function(row){
		$scope.scopeController.selectedRow = row;
		qxQborrowHttpService.editSoggetto($scope.scopeController);
	}
	
	$scope.editMyProfile = function(){
		qxQborrowHttpService.editMyProfile($scope.scopeController);	
	}
	
	$scope.editWithCompleanno = function(row){
		$scope.scopeController.selectedRow = row;
		qxQborrowHttpService.editSoggettoWithCompleanno($scope.scopeController);
	}
	
	$scope.exportXLS = function() {
		document.location.href =  qborrowConfig.baseUrl + '/soggetto.action?task=exportXls&reset=true&' + quixParamSerializer($scope.scopeController.search, 'soggettoSearch.');
	}
	
	$scope.delete = function(row){
		 SweetAlert.swal({
	        title: labelService.swalWarnDeleteTitle,
	        text: labelService.swalWarnDeleteMessage,
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: labelService.swalWarnDeleteButton,
	        closeOnConfirm: false,
	        closeOnCancel: true,
	        html: false
	    }, function(isConfirm){
			if(isConfirm){
				$scope.scopeController.selectedRow = row;
				qxQborrowHttpService.deleteSoggetto($scope.scopeController, labelService);
			}
		});
	}
	
	$scope.scopeController.editProfileAlert = function() {
		SweetAlert.swal("", "Profilo modificato", "success");
	}
	
	$scope.create = function(){
		$scope.scopeController.selectedRow = {};
		$scope.scopeController.selectedPage = "edit";
	}
	
	$scope.back = function(){
		$scope.scopeController.selectedRow = {};
		$scope.scopeController.selectedPage = "list";
	}
	
	$scope.save = function(row){
		qxQborrowHttpService.saveSoggetto($scope.scopeController, $scope.forms.soggettoEditForm);
	}
	
	$scope.saveWithCompleanno = function(row){
	/*	var isValidForm = $scope.forms.soggettoEditForm.$valid;
		if (isValidForm) {*/
		
			qxQborrowHttpService.saveSoggettoWithCompleanno($scope.scopeController, $scope.forms.soggettoEditForm);
		/*} else {
			SweetAlert.swal("", "Errore nel form", "error");
		}*/
	}
	
	$scope.resetSearch = function () {
		$scope.scopeController.search= {};
		$scope.scopeController.search.page = 1;
	}
	
	$scope.loadCombo = function(name) {
		qxQborrowHttpService.getCombo($scope.scopeController, name);
	}
	
	$scope.list();
	
    $scope.initCalendar = function(obj, id) {
    	if ( $('#' + id)[0].type != 'date' )  {
    		obj.oldCalendar = true;
    	}
    }
    
    $scope.openCalendar = function(obj, $event) {
        $event.preventDefault();
        $event.stopPropagation();
    	obj.opened = true;
    	return obj.opened;
    }	
    
    $scope.openPopupField = function(externalModelName, object, baseProp, idProp, descrProp) {
    	$scope.scopeController.modalInstance = $modal.open({
            template: '<div ng-include="\''+externalModelName+'\'"></div>',
            windowTemplateUrl: 'template/modal/window-quix.html',
            controller: 'qx' + externalModelName + 'PopupController',
            size: 'lg'
      	});
		$scope.scopeController.modalInstance.result.then(function (selectedElement) {
			if(selectedElement != undefined) {
	    		object[baseProp] = selectedElement[idProp];
	    		object[baseProp + '_description'] =  selectedElement[descrProp];
	    	}	
   		}, function () {
    	 	//Ok, modal dismissed senza cambiare i filtri
   		});
    }
}]);

qborrowApp.controller('qxSoggettoPopupController', ['$scope', 'qxQborrowHttpService', '$modal', '$modalInstance', '$controller', function ($scope, qxQborrowHttpService, $modal, $modalInstance, $controller) {
	angular.extend(this, $controller('qxSoggettoController', {$scope: $scope}));
	$scope.popup = true;
	
	$scope.select = function(row) {
    	$modalInstance.close(row);
    }
    
    $scope.close = function () {
    	$modalInstance.close();
	}
	
}]);

