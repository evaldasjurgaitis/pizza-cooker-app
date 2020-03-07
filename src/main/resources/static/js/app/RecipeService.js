'use strict'
angular.module('RecipeService', []).factory('RecipeService', ["$http", "CONSTANTS", function ($http, CONSTANTS) {
    var service = {};
    service.getRecipes = function () {
        var url = CONSTANTS.getRecipes;
        return $http.get(url);
    }
    return service;
}]);