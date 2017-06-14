(function () {

    'use strict';

    angular.module('pessoaApp').controller('MainController', MainController);

    MainController.$inject = ['PessoaService','$routeParams'];

    /**
     * @namespace MainController
     * @desc Main Controller of Challenge App
     * @memberOf PessoaApp
     */
    function MainController(PessoaService, $routeParams) {

        var main = this;

        listaPessoas();

        if($routeParams.id) {
            carregaPessoa();
        }
        
        main.salvaPessoas = salvaPessoas;

        function carregaPessoa() {
            PessoaService.get({id: $routeParams.id}, function(pessoa) {
                main.pessoa = pessoa;
            });
        }

        function listaPessoas() {
            PessoaService.query(function (pessoas) {
                main.pessoas = pessoas;
            });
        }

        function salvaPessoas() {
            PessoaService.save(main.pessoa, function(pessoa){
                main.pessoas.push(pessoa);
            });
        }

    }

}());