'use strict'
var module = angular.module('PizzaCookerController', []);
module.controller('PizzaCookerController', ['$scope', '$timeout', 'CookerMachineService', 'RecipeService', function ($scope, $timeout, CookerMachineService, RecipeService) {
    $scope.cookerMachines = null;
    $scope.pizzaDetails = null;
    $scope.recipes = null;
    CookerMachineService.getCookerMachines().then(function (response) {
        $scope.cookerMachines = response.data;
    }, function (reason) {
        $scope.errorMessage = reason.data.message;
    });

    RecipeService.getRecipes().then(function (response) {
        $scope.recipes = response.data;
    }, function (reason) {
        $scope.errorMessage = reason.data.message;
    });

    $scope.makePizza = function (cookerMachineId, recipeId, size) {
        CookerMachineService.getPizza(cookerMachineId, recipeId, size).then(function (response) {
            $scope.pizzaDetails = response.data;

        }, function (reason) {
            $scope.pizzaDetails.message = reason.data.message;
        });
        CookerMachineService.getCookerMachines().then(function (response) {
            $scope.cookerMachines = response.data;
        }, function (reason) {
            $scope.errorMessage = reason.data.message;
        });
    };

    $scope.cleanCookerMachine = function (cookerMachineId) {
        CookerMachineService.getCookerMachineCleanUp(cookerMachineId).then(function (response) {
            $scope.pizzaDetails = response.data;
            CookerMachineService.getCookerMachines().then(function (response) {
                $scope.cookerMachines = response.data;
            }, function (reason) {
                $scope.errorMessage = reason.data.message;
            });
        }, function (reason) {
            $scope.errorMessage = reason.data.message;
        });
    }
}
]);