(function () {

    'use strict';

    angular.module('pessoaApp').controller('MainController', MainController);

    MainController.$inject = ['PessoaService', '$routeParams', '$location', 'Upload', 'notify', '$scope'];

    /**
     * @namespace MainController
     * @desc Main Controller of Challenge App
     * @memberOf PessoaApp
     */
    function MainController(PessoaService, $routeParams, $location, Upload, notify, $scope) {

        var main = this;

        main.salvaPessoas = salvaPessoas;
        main.removePessoas = removePessoas;
        main.uploadFile = uploadFile;

        listaPessoas();

        function carregaPessoa() {
            PessoaService.get({ id: $routeParams.id }, function (pessoa) {
                main.pessoa = pessoa;
            });
        }

        function listaPessoas() {
            PessoaService.query(function (pessoas) {
                main.pessoas = pessoas;
            });
        }

        function removePessoas(index) {

            var id = main.pessoas[index].id;

            PessoaService.remove({ id: id }, function (pessoa) {
                main.pessoas.splice(index, 1);
                notify({
                    message: 'Pessoa removida com sucesso!',
                    classes: 'notification is-success',
                    position: 'right'
                });
            });
        }

        function salvaPessoas() {
            if (main.pessoaForm.$valid) {
                PessoaService.save(main.pessoa, function (pessoa) {
                    main.pessoas.push(pessoa);
                    notify({
                        message: 'Pessoa salva com sucesso!',
                        classes: 'notification is-success',
                        position: 'right'
                    });
                    main.pessoa = {};
                    $location.path('/');
                });
            } else {
                notify({
                    message: 'Verifique o formulario',
                    classes: 'notification is-danger',
                    position: 'right'
                });
            }
        }

        function uploadFile(file, errFiles) {
            main.f = file;
            main.errFile = errFiles && errFiles[0];
            if (file) {
                file.upload = Upload.upload({
                    url: '/pessoa/upload',
                    data: { file: file }
                });

                file.upload.then(function (response) {
                    main.pessoas = main.pessoas.concat(response.data);
                    notify({
                        message: 'Arquivo processado com sucesso!',
                        classes: 'notification is-success',
                        position: 'right'
                    });
                }, function (response) {
                    if (response.status > 0)
                        main.errorMsg = response.status + ': ' + response.data;
                }, function (evt) {
                    main.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
                });
            }
        }

        $scope.$on('$viewContentLoaded', function () {

            main.pessoa = {};

            if ($routeParams.id) {
                carregaPessoa();
            }
        });

    }

}());