/**
 * Set up application controllers and paths
 */

var app = angular.module('pizzaapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).when('/list', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/v1/orders').success(function (data) {
        $scope.orders = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
});

app.controller('CreateCtrl', function ($scope, $http, $location) {

    $scope.createPizzaOrder = function () {
        console.log($scope.order);
        $http.post('/api/v1/order', $scope.order).success(function (data) {
            $location.path('/list');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});