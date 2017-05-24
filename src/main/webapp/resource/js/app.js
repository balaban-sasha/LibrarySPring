/**
 * Created by Саша on 23.05.2017.
 */
var app = angular.module('app', []);
app.controller('NewsController', function NewsController($scope, $http) {
    $scope.newsList = [];
    $http({
        url: '/library/getData',
        method: "GET",
        params: {action: 'getLimitedNumber', tableName: 'News', limit: 10},
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).then(function (response) {
        $scope.newsList = response.data;
    }, function (error) {

    });
});
app.controller('MainController', function ($scope) {

    $scope.ifMainActive = false;
    $scope.ifChatActive = false;
    $scope.ifNewsActive = false;
    $scope.ifAuthorActive = false;
    $scope.ifBooksActive = false;
    $scope.mainDown = function (event) {
        event.preventDefault();
        $scope.ifMainActive = 'my-params-for-nav';
    };
    $scope.mainOut = function (event) {
        event.preventDefault();
        $scope.ifMainActive = false;
    };
    $scope.newsDown = function (event) {
        event.preventDefault();
        $scope.ifNewsActive = 'my-params-for-nav';
    };
    $scope.newsOut = function (event) {
        event.preventDefault();
        $scope.ifNewsActive = false;
    };
    $scope.chatDown = function (event) {
        event.preventDefault();
        $scope.ifChatActive = 'my-params-for-nav';
    };
    $scope.chatOut = function (event) {
        event.preventDefault();
        $scope.ifChatActive = false;
    };
    $scope.booksDown = function (event) {
        event.preventDefault();
        $scope.ifBooksActive = 'my-params-for-nav';
    };
    $scope.booksOut = function (event) {
        event.preventDefault();
        $scope.ifBooksActive = false;
    };
    $scope.authorDown = function (event) {
        event.preventDefault();
        $scope.ifAuthorActive = 'my-params-for-nav';
    };
    $scope.authorOut = function (event) {
        event.preventDefault();
        $scope.ifAuthorActive = false;
    };
})