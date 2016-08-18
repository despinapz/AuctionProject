myApp.controller('navCtrl', ['$rootScope', '$scope', '$stateParams', 'notify', 'pollingFactory', function($rootScope, $scope, $stateParams, notify, pollingFactory) {
	pollingFactory.callFnOnInterval(function () {
        notify.callServer();
    }, 5);
}]);

myApp.controller('mainCtrl', ['$rootScope', '$scope', '$stateParams', function($rootScope, $scope, $stateParams) {
    $scope.isAdmin = false;
	$scope.user_id = parseInt($stateParams.id);
	if ($scope.user_id == 1)
		$scope.isAdmin = true;
}]);

myApp.controller('adminPageCtrl', ['$scope', '$http', '$state', function($scope, $http, $state) {
    var res = $http.get('/ws/user/getAll');
    res.success(function(response) {
        $scope.userList = response;
    });
    $scope.viewProfile = function(userid) {
        $state.go('app.editprofile', {id: userid});
    };
}]);

myApp.controller('searchCtrl', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $scope.currentTab = "search";
    
    $scope.$watch('currentTab', function() {
        $scope.categoryPathList = [];
        $scope.categoryPathList.push({name: "All", id: 1});
        $http.get('/ws/category/parent/1')
        .success(function(response) {
            $scope.categoryList = response;
        });
    });
    
    $scope.findSubCat = function(name, id) {
        var pos = -1, i = 0;
        while (i < $scope.categoryPathList.length) {
            if ($scope.categoryPathList[i].id == id){
                pos = i;
                break;
            }
            i++;
        }
        if (pos == -1)
            $scope.categoryPathList.push({name, id});
        else {
            while ($scope.categoryPathList.length > pos + 1)
                $scope.categoryPathList.pop();
        }
        $http.get('/ws/category/parent/' + id)
        .success(function(response) {
            $scope.categoryList = response;
        });
    };
    
}]);