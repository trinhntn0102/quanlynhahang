const app= angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http){
	$scope.cart = {
			items: [],
			
			add(id){
				var item = this.items.find(item => item.id == id);
				if(item){
					item.qty++;
					this.saveToLocalStorage();
				}
				else{
					$http.get(`/rest/products/${id}`).then(resp => {
						resp.data.qty = 1;
						this.items.push(resp.data);
						this.saveToLocalStorage();
					})
				}
			},
			
			remove(id){
				var index = this.items.findIndex(item => item.id == id);
				this.items.splice(index, 1);
				this.saveToLocalStorage();
			},
			
			clear(){
				this.items = []
				this.saveToLocalStorage();
			},
			
			amt_of(item){},
			
			get count(){
				return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
			},
			
			amount(){
				return this.items
				.map(item => item.qty * item.price)
				.reduce((total, qty) => total += qty, 0);
			},
			
			saveToLocalStorage(){
				var json = JSON.stringify(angular.copy(this.items));
				localStorage.setItem("cart", json);
			},
			
			loadFromLocalStorage(){
				var json = localStorage.getItem("cart");
				this.items = json ? JSON.parse(json) : [];
			}
	}
	
	$scope.cart.loadFromLocalStorage();
	
	$scope.order = {
			createDate: new Date(),
			address:"",
			account: { username: $("#username").text() },
			total: $scope.cart.amount(),

			get orderDetails(){
				return $scope.cart.items.map(item => {
					return{
						product:{id: item.id},
						price:item.price,
						quantity:item.qty
					}
				});
			},
			purchase(){
				var order = angular.copy(this);
				$http.post(`/rest/orders`, order).then(resp => {
					alert("Đặt hàng thành công");
					$scope.cart.clear();
					location.href = "/order/detail/" + resp.data.id;
				}).catch(error => {
					alert("Đặt hàng thất bại")
					console.log(error)
				})
				
			}
	}
})

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
		$scope.form.createDate = currentDate.getDate();
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