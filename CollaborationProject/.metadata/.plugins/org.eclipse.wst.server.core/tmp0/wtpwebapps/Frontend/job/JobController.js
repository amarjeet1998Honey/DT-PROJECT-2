myApp.controller("jobController",function($scope,$http, $window,$location,$rootScope,$cookieStore){
	
$scope.job={jobDesignation:'',jobDesc:'',company:'',salary:'',location:'',lastDateApply:''};
	
	$scope.addJob=function(){
		console.log('Enter into Job function');
		
		
		$http({
		    url : "http://localhost:8081/Middleware/addJob",
		    method : 'POST',
		    data: $scope.job,
		    transformResponse: [
		        function (data) {
		        	$window.location.reload();
		        	alert('Add Job Succesfully');
		            return data;
		            
		        }
		    ]
		}) 
	
	};
	
	 $scope.listAllJobs=function(){
	        console.log('Entered into listAllJobs function');
	        $http.get("http://localhost:8081/Middleware/listAllJobs")
	        .then(function(response){
	            console.log('Data found');
	            $scope.allJobs=response.data;
	        },
	        function(error){
	            console.log('No Jobs found');
	            $scope.allJobs=[];
	            $scope.viewMessage="No Jobs Found...!!!";
	        });
	    };
	    
	    $scope.applyJob=function(jid){
	        console.log('Entered into apply job function function');
	        $http({
			    url : "http://localhost:8081/Middleware/applyJob/"+jid,
			    method : 'GET',
			    transformResponse: [
			        function (data) {
			        	$window.location.reload();
			        	alert('Apply Job Succesfully');
			            return data;
			            
			        }
			    ]
			}) 
		
		};
		
		 $scope.listSortedJobs=function(){
		        console.log('Entered into listSortedJobs function');
		        $http.get("http://localhost:8081/Middleware/listSortedJobs")
		        .then(function(response){
		            console.log('Data found');
		            $scope.allJobs=response.data;
		        },
		        function(error){
		            console.log('No Jobs found');
		            $scope.allJobs=[];
		            $scope.viewMessage="No Jobs Found...!!!";
		        });
		    };
	    
});