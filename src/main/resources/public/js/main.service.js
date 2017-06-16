(function () {

    'use strict';

    angular.module('pessoaApp').factory('PessoaService', PessoaService);

    PessoaService.$inject = ['$resource'];

    function PessoaService($resource) {
        return $resource('/pessoa/:id', null, { 'update': { method: 'PUT' } });
    };

}());