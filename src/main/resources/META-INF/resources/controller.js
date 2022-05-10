var app = angular.module("CashbackModule", []);

//Controller Part
var controller = app.controller('CashbackController', ['$scope', '$http', function($scope, $http) {
    //Initialize page with default data which is blank in this example
    $scope.cashback = [];

    $scope.page=0;
    $scope.size=3;
    $scope.totalCashback;

    $scope.form = {
        id: -1,
        name: ""
    };

    //Now load the data from server
    _refreshPageData();

    $scope.previousPage = function(){
        if($scope.page>0)
            _changePage($scope.page-1);
    }
    $scope.nextPage = function(){
        if($scope.page<$scope.totalCashback-1)
        _changePage($scope.page+1);
    }
    _changePage =function (newPage){
        $scope.page=newPage;
        _refreshPageData();
    }

    /* Private Methods */
    //HTTP GET- get paginated list of cashback
    function _refreshPageData() {
        $http({
            method: 'GET',
            url: '/customer?page='+$scope.page+'&size='+$scope.size
        }).then(function successCallback(response) {
            $scope.customers = response.data;
            $scope.totalCashback= response.headers(['x-total-count']);
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    function _success(response) {
        _refreshPageData();
    }

    function _error(response) {
        alert(response.data.message || response.statusText);
    }
}]);



