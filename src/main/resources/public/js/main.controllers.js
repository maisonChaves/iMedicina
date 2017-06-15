(function () {

    'use strict';

    angular.module('pessoaApp').controller('MainController', MainController);

    MainController.$inject = ['PessoaService', '$routeParams', '$location'];

    /**
     * @namespace MainController
     * @desc Main Controller of Challenge App
     * @memberOf PessoaApp
     */
    function MainController(PessoaService, $routeParams, $location) {

        var main = this;

        main.pessoa = {};
        main.salvaPessoas = salvaPessoas;
        main.removePessoas = removePessoas;

        listaPessoas();

        if ($routeParams.id) {
            carregaPessoa();
        }

        function carregaPessoa() {
            var teste = PessoaService.get({ id: $routeParams.id }, function (pessoa) {
                console.log(pessoa);
                main.pessoa = pessoa;
            });

            main.pessoa = teste;
        }

        function listaPessoas() {
            PessoaService.query(function (pessoas) {
                main.pessoas = pessoas;
            });
        }

        function removePessoas(index) {

            var id = main.pessoas[index].id;

            PessoaService.remove({ id: id }, function (pessoa) {
                main.pessoas.splice(index);
            });
        }

        function salvaPessoas() {
            PessoaService.save(main.pessoa, function (pessoa) {
                main.pessoas.push(pessoa);
                $location.path('/');
            });
        }

    }

}());