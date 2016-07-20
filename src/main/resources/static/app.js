var appModule = angular.module('myApp', ['ngCookies']);
//var appModule = angular.module('myApp', []);

appModule.controller('MainCtrl', ['mainService','$scope','$http', '$cookies',
        function(mainService, $scope, $http, $cookies) {
            $scope.greeting = 'Welcome to UBR';
            $scope.token = null;
            $scope.error = null;
            $scope.roleUser = false;
            $scope.roleAdmin = false;
            $scope.roleFoo = false;

            $scope.login = function() {
                $cookies.obj = null;
                $scope.error = null;
                mainService.login($scope.userName, $scope.userPassword).then(function(token) {

                    $cookies.obj = token;

                    $scope.token = $cookies.obj;
                    $http.defaults.headers.common.Authorization = 'Bearer ' + $scope.token;
                    $scope.checkRoles();
                },
                function(error){
                    $scope.error = error;
                    $scope.userName = '';
                    $scope.userPassword = '';
                    $cookies.obj = null;
                });
            };



            $scope.checkRoles = function() {
                mainService.hasRole('user').then(function(user) {$scope.roleUser = user});
                mainService.hasRole('admin').then(function(admin) {$scope.roleAdmin = admin});
                mainService.hasRole('foo').then(function(foo) {$scope.roleFoo = foo});
            };

            $scope.logout = function() {
                $scope.userName = '';
                $scope.userPassword = '';
                $scope.token = null;
                $cookies.obj = null;
                $http.defaults.headers.common.Authorization = '';
            };

            $scope.loggedIn = function() {
                return $scope.token !== null;

        };
            if ($cookies.obj !== "null"){

                $scope.init = function() {
                    $scope.token = $cookies.obj;
                    $http.defaults.headers.common.Authorization = 'Bearer ' + $scope.token;
                    $scope.checkRoles();
                } ;
            }

        } ]);


appModule.service('mainService', function($http) {
    return {
        login : function(username, userpassword) {
            return $http.post('/user/login', {name: username, password: userpassword}).then(function(response) {
                return response.data.token;
            });
        },

        hasRole : function(role) {
            return $http.get('/api/role/' + role).then(function(response){
                return response.data;
            });
        }
    };
});
