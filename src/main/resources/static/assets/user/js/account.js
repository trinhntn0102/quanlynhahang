app.controller("accountctrl", function($scope, $http) {
    $scope.items = [];
    $scope.form = {};

    $scope.initialize = function() {
        $http.get("/rest/accounts").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        });
    }
    $scope.initialize();
    $scope.reset = function() {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true
        };
    }
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')
    }
    $scope.create = function() {
        var item = angular.copy($scope.form);
        $http.post(`/rest/accounts`, item).then(resp => {
	$scope.form = {
            createDate: new Date(),
            image: "",
            available: true
        };
            resp.data.createDate = new Date(resp.data.createDate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Tạo tài khoản thành công");
        }).catch(error => {
            alert("Lỗi tạo tài khoản");
            console.log("Error", error);
        });
    }
    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put(`/rest/accounts/${item.username}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhật sản phẩm thành công");
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm thất bại");
            console.log("Error", error);
        });
    }
    $scope.delete = function(item) {
        $http.delete(`/rest/accounts/${item.username}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("Xoá sản phẩm thành công");
        }).catch(error => {
            alert("Lỗi xoá sản phẩm thất bại");
            console.log("Error", error);
        });
    }
    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/image', data, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi upload hình ảnh");
            console.log("Error", error);
        })
    }
});

