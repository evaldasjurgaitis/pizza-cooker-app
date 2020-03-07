'use strict'
angular.module('CookerMachineService', []).factory('CookerMachineService', ["$http", "CONSTANTS", function ($http, CONSTANTS) {
    var service = {};
    service.getCookerMachines = function () {
        var url = CONSTANTS.getCookerMachines;
        return $http.get(url);
    };
    service.getCookerMachineCleanUp = function (cookerMachineId) {
        var url = CONSTANTS.getCookerMachineCleanUp + cookerMachineId + "/cleanup";
        return $http.get(url);
    };

    service.getPizza = function (cookerMachineId, recipeId, size) {
        var url = CONSTANTS.getPizza + cookerMachineId + "/pizza/" + recipeId + "/size/" + size + "";
        return $http.get(url);
    };
    return service;
}]);
