'use strict'
var pizzaCookerApp = angular.module('pizzaCooker', ['ui.bootstrap', 'PizzaCookerController', 'CookerMachineService', 'RecipeService']);
pizzaCookerApp.constant("CONSTANTS", {
    getCookerMachines: "/cooker-machines/",
    getCookerMachineCleanUp: "/cooker-machines/",
    getPizza: "/cooker-machines/",
    getRecipes: "/recipes/"
});