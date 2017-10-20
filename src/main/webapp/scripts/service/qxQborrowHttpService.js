var qborrowApp = angular.module('qborrow');

var qxQborrowHttpService = function($http, qborrowConfig, $timeout) {
	this.getOggettoList = function(scopeController, form) {
		_getOggettoList(scopeController, form);
	}
	
	function _getOggettoList(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        		scopeController.result = {};
        	} else {
        		scopeController.result = data;
        	}
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/oggetto.action?task=list&reset=true', 
        		data: quixParamSerializer(scopeController.search, 'oggettoSearch.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
	this.getMieiOggettiList = function(scopeController, form) {
		_getMieiOggettiList(scopeController, form);
	}
	
	function _getMieiOggettiList(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        		scopeController.result = {};
        	} else {
        		scopeController.result = data;
        	}
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/oggetto.action?task=listMieiOggetti', 
        		data: quixParamSerializer(scopeController.search, 'oggettoSearch.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
	
    this.editOggetto = function(scopeController){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	scopeController.selectedRow = data;
        	scopeController.selectedPage = "edit";
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/oggetto.action?task=edit', 
        		data: quixParamSerializer(scopeController.selectedRow, 'oggetto.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
     this.deleteOggetto = function(scopeController, labelService){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	sweetAlert(labelService.swalSuccDeleteTitle, labelService.swalSuccDeleteMessage, "success");
        	_getOggettoList(scopeController, null);
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/oggetto.action?task=delete', 
        		data: quixParamSerializer(scopeController.selectedRow, 'oggetto.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.saveOggetto = function(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        	} else {
        		_getOggettoList(scopeController, null);
        		scopeController.selectedPage = 'list';
        	}
        };
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/oggetto.action?task=save&reset=true', 
        		data: quixParamSerializer(scopeController.selectedRow, 'oggetto.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    							
	this.getPrestitoList = function(scopeController, form) {
		_getPrestitoList(scopeController, form);
	}
	
	function _getPrestitoList(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        		scopeController.result = {};
        	} else {
        		scopeController.result = data;
        	}
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/prestito.action?task=list&reset=true', 
        		data: quixParamSerializer(scopeController.search, 'prestitoSearch.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.editPrestito = function(scopeController){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	scopeController.selectedRow = data;
        	scopeController.selectedPage = "edit";
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/prestito.action?task=edit', 
        		data: quixParamSerializer(scopeController.selectedRow, 'prestito.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
     this.deletePrestito = function(scopeController, labelService){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	sweetAlert(labelService.swalSuccDeleteTitle, labelService.swalSuccDeleteMessage, "success");
        	_getPrestitoList(scopeController, null);
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/prestito.action?task=delete', 
        		data: quixParamSerializer(scopeController.selectedRow, 'prestito.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.savePrestito = function(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        	} else {
        		_getPrestitoList(scopeController, null);
        		scopeController.selectedPage = 'list';
        	}
        };
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/prestito.action?task=save&reset=true', 
        		data: quixParamSerializer(scopeController.selectedRow, 'prestito.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    							
	this.getSoggettoList = function(scopeController, form) {
		_getSoggettoList(scopeController, form);
	}
	
	function _getSoggettoList(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        		scopeController.result = {};
        	} else {
        		scopeController.result = data;
        	}
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/soggetto.action?task=list&reset=true', 
        		data: quixParamSerializer(scopeController.search, 'soggettoSearch.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.editSoggetto = function(scopeController){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	scopeController.selectedRow = data;
        	scopeController.selectedPage = "edit";
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/soggetto.action?task=edit', 
        		data: quixParamSerializer(scopeController.selectedRow, 'soggetto.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.editSoggettoWithCompleanno = function(scopeController){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	scopeController.selectedRow = data;
        	scopeController.selectedPage = "edit";
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/soggetto.action?task=editWithCompleanno', 
        		data: quixParamSerializer(scopeController.selectedRow, 'soggetto.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
     this.deleteSoggetto = function(scopeController, labelService){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	sweetAlert(labelService.swalSuccDeleteTitle, labelService.swalSuccDeleteMessage, "success");
        	_getSoggettoList(scopeController, null);
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/soggetto.action?task=delete', 
        		data: quixParamSerializer(scopeController.selectedRow, 'soggetto.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.saveSoggetto = function(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        	} else {
        		_getSoggettoList(scopeController, null);
        		scopeController.selectedPage = 'list';
        	}
        };
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/soggetto.action?task=save&reset=true', 
        		data: quixParamSerializer(scopeController.selectedRow, 'soggetto.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    	
    this.saveSoggettoWithCompleanno = function(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        	} else {
        		_getSoggettoList(scopeController, null);
        		scopeController.selectedPage = 'list';
        	}
        };
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/soggetto.action?task=saveWithCompleanno&reset=true', 
        		data: quixParamSerializer(scopeController.selectedRow, 'soggetto.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }

	this.getCombo = function(scopeController, name){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	scopeController[name + 'List'] = data.list;
        };
    
    	 scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrowConfig.baseUrl + '/'+name+'.action?task=list&reset=true&'+name+'Search.page=-1', 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }					
};

qborrowApp.service('qxQborrowHttpService', ['$http', 'qborrowConfig', '$timeout', qxQborrowHttpService]);								
