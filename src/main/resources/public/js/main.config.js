(function () {

    'use strict';

    angular.module('pessoaApp').config(CustomDateConverter);

    CustomDateConverter.$inject = ['$httpProvider'];

    /**
     * @namespace CustomDateConverter
     * @desc Converter String data from http request in Data object.
     * @memberOf PessoaApp
     */
    function CustomDateConverter($httpProvider) {
        $httpProvider.defaults.transformResponse.push(function (responseData) {
            convertDate(responseData);
            return responseData;
        });
    };

    var regexData = /\d{2}\/\d{2}\/\d{4}/;

    /**
     * @name convertDate
     * @desc Convert date strings to Date Object
     * @param {Object} data objeto com campos data em String no formato dd/MM/yyyy
     * @returns {Object} objeto com campos data em Data object
     * @memberOf PessoaApp.CustomDateConverter
     */
    function convertDate(data) {
        if (typeof data !== "object") return data;

        for (var key in data) {
            if (!data.hasOwnProperty(key)) continue;

            var value = data[key];
            var match;
            if (typeof value === "string" && (match = value.match(regexData))) {
                data[key] = moment(match[0], "DD-MM-YYYY").toDate();
            } else if (typeof value === "object") {
                convertDate(value);
            }
        }
    }

}());