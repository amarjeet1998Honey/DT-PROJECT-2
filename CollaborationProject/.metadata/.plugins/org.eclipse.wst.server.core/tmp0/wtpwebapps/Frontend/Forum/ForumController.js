myApp.controller("forumController",function($scope,$http, $window,$location,$rootScope,$cookieStore){
	
$scope.forum={forumName:'',forumContent:'',createDate:'',likes:'',status:'',loginName:''};
	
	$scope.addForum=function(){
		console.log('Enter into Forum function');
		
		
		$http({
		    url : "http://localhost:8081/Middleware/addForum",
		    method : 'POST',
		    data: $scope.forum,
		    transformResponse: [
		        function (data) {
		        	$window.location.reload();
		        	alert('Add Forum Succesfully');
		            return data;
		            
		        }
		    ]
		}) 
	}
	
	 $scope.listAllForums=function(){
	        console.log('Entered into listAllForums function');
	        $http.get("http://localhost:8081/Middleware/listForums")
	        .then(function(response){
	            console.log('Data found');
	            $scope.allForums=response.data;
	        },
	        function(error){
	            console.log('No Forum found');
	            $scope.allForums=[];
	            $scope.viewMessage="No Forum Found...!!!";
	        });
	    };
	    
	    $scope.listAllPendingForums=function(){
	        console.log('Entered into Pending listAllForums function');
	        $http.get("http://localhost:8081/Middleware/listPendingForum")
	        .then(function(response){
	            console.log('Data found');
	            $scope.allForums=response.data;
	        },
	        function(error){
	            console.log('No Forums found');
	            $scope.allForums=[];
	            $scope.viewMessage="No forums Found...!!!";
	        });
	    };
	    
	    

	    $scope.approveForum=function(fid){
	        console.log('Entered into approve forum function function');
	        $http({
			    url : "http://localhost:8081/Middleware/approveForum/"+fid,
			    method : 'GET',
			    transformResponse: [
			        function (data) {
			        	$window.location.reload();
			        	alert('Approved Forum Succesfully');
			            return data;
			            
			        }
			    ]
			}) 
		
		};
		
	    $scope.rejectForum=function(fid){
	        console.log('Entered into reject forum function function');
	        $http({
			    url : "http://localhost:8081/Middleware/rejectForum/"+bid,
			    method : 'GET',
			    transformResponse: [
			        function (data) {
			        	$window.location.reload();
			        	alert('Rejected Forum Succesfully');
			            return data;
			            
			        }
			    ]
			}) 
		
		};
});