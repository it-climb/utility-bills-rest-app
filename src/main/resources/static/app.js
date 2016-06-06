var appModule = angular.module('myApp', ['ngCookies']);
//var appModule = angular.module('myApp', []);

appModule.controller('MainCtrl', ['mainService','$scope','$http', '$cookieStore',
        function(mainService, $scope, $http, $cookieStore) {
            $scope.greeting = 'Welcome to UBR';
            $scope.token = null;
            $scope.error = null;
            $scope.roleUser = false;
            $scope.roleAdmin = false;
            $scope.roleFoo = false;
            $scope.usingCookieStore = null;

            $scope.login = function() {
                $scope.error = null;
                mainService.login($scope.userName, $scope.userPassword).then(function(token) {
                    $cookieStore.put('token', token);
                    $scope.token = $cookieStore.get('token');
                    $scope.usingCookieStore = $cookieStore.get('token');
                    $http.defaults.headers.common.Authorization = 'Bearer ' + $cookieStore.get('token');
                    $scope.checkRoles();
                },
                function(error){
                    $scope.error = error;
                    $scope.userName = '';
                    $scope.userPassword = '';
                    $cookieStore = null;
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
                $cookieStore = null;
                $http.defaults.headers.common.Authorization = '';
            };

            $scope.loggedIn = function() {
                return $scope.token !== null;
            }
        } ]);



appModule.service('mainService', function($http) {
    return {
        login : function(username, userpassword) {
            return $http.post('/user/login', {name: username, password: userpassword}).then(function(response) {
                //return response.data.usingCookieStore;
                return response.data.token;
            });
        },

        hasRole : function(role) {
            return $http.get('/api/role/' + role).then(function(response){
                console.log(response);
                return response.data;
            });
        }
    };
});
