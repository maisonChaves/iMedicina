(function () {

    'use strict';

    angular.module('pessoaApp').config(RouterConfig);

    RouterConfig.$inject = ['$routeProvider', '$locationProvider'];

    function RouterConfig($routeProvider, $locationProvider) {

        $locationProvider.hashPrefix('');

        $routeProvider.when('/', {
            templateUrl: 'views/list.html',
            controller: 'MainController'
        });
        $routeProvider.when('/form', {
            templateUrl: 'views/form.html',
            controller: 'MainController'
        });
        $routeProvider.when('/form/:id', {
            templateUrl: 'views/form.html',
            controller: 'MainController'
        });
        $routeProvider.otherwise({
            redirectTo: '/'
        });
    }

}());