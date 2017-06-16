(function () {

    'use strict';

    angular.module('pessoaApp').factory('PessoaService', PessoaService);

    PessoaService.$inject = ['$resource'];

    /**
     * @namespace PessoaService
     * @desc Service for pessoa API
     * @memberOf PessoaApp
     */
    function PessoaService($resource) {
        return $resource('/pessoa/:id', null, { 'update': { method: 'PUT' } });
    };

}());