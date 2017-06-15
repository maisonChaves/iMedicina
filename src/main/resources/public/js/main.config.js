(function () {

    'use strict';

    angular.module('pessoaApp').config(CustomDateConverter);

    CustomDateConverter.$inject = ['$httpProvider'];

    function CustomDateConverter($httpProvider) {
        $httpProvider.defaults.transformResponse.push(function (responseData) {
            convertDateStringsToDates(responseData);
            return responseData;
        });
    };

    var regexData = /\d{2}\/\d{2}\/\d{4}/;

    function convertDateStringsToDates(data) {
        if (typeof data !== "object") return data;

        for (var key in data) {
            if (!data.hasOwnProperty(key)) continue;

            var value = data[key];
            var match;
            // Check for string properties which look like dates.
            if (typeof value === "string" && (match = value.match(regexData))) {
                data[key] = moment(match[0], "DD-MM-YYYY").toDate();
            } else if (typeof value === "object") {
                // Recurse into object
                convertDateStringsToDates(value);
            }
        }
    }

}());