myApp.controller("blogController",function($scope,$http, $window,$location,$rootScope,$cookieStore){
	
$scope.blog={blogName:'',blogContent:'',createDate:'',likes:'',status:'',loginName:''};
	
	$scope.addBlog=function(){
		console.log('Enter into Blog function');
		
		
		$http({
		    url : "http://localhost:8081/Middleware/addBlog",
		    method : 'POST',
		    data: $scope.blog,
		    transformResponse: [
		        function (data) {
		        	$window.location.reload();
		        	alert('Add Blog Succesfully');
		            return data;
		            
		        }
		    ]
		}) 
	
	};
	

	 $scope.listAllBlogs=function(){
	        console.log('Entered into listAllBlogs function');
	        $http.get("http://localhost:8081/Middleware/listBlog")
	        .then(function(response){
	            console.log('Data found');
	            $scope.allBlogs=response.data;
	        },
	        function(error){
	            console.log('No Blogs found');
	            $scope.allBlogs=[];
	            $scope.viewMessage="No Blogs Found...!!!";
	        });
	    };
	    
		 $scope.listAllPendingBlogs=function(){
		        console.log('Entered into listAllBlogs function');
		        $http.get("http://localhost:8081/Middleware/listPendingBlog")
		        .then(function(response){
		            console.log('Data found');
		            $scope.allBlogs=response.data;
		        },
		        function(error){
		            console.log('No Blogs found');
		            $scope.allBlogs=[];
		            $scope.viewMessage="No Blogs Found...!!!";
		        });
		    };
		    
		    $scope.listAllApprovedBlogs=function(){
		        console.log('Entered into listAllBlogs function');
		        $http.get("http://localhost:8081/Middleware/listPendingBlog")
		        .then(function(response){
		            console.log('Data found');
		            $scope.allBlogs=response.data;
		        },
		        function(error){
		            console.log('No Blogs found');
		            $scope.allBlogs=[];
		            $scope.viewMessage="No Blogs Found...!!!";
		        });
		    };
		    
	
		    $scope.approveBlog=function(bid){
		        console.log('Entered into approve blog function function');
		        $http({
				    url : "http://localhost:8081/Middleware/approveBlog/"+bid,
				    method : 'GET',
				    transformResponse: [
				        function (data) {
				        	$window.location.reload();
				        	alert('Approved Blog Succesfully');
				            return data;
				            
				        }
				    ]
				}) 
			
			};
			
		    $scope.rejectBlog=function(bid){
		        console.log('Entered into reject blog function function');
		        $http({
				    url : "http://localhost:8081/Middleware/rejectBlog/"+bid,
				    method : 'GET',
				    transformResponse: [
				        function (data) {
				        	$window.location.reload();
				        	alert('Rejected Blog Succesfully');
				            return data;
				            
				        }
				    ]
				}) 
			
			};
			
			$scope.blogComment={blogId:'',commentText:'',commentDate:'',loginName:''};
			
			$scope.addBlogComment=function(bid){
				console.log('Enter into comment function : '+bid);
				$scope.blogComment.blogId=bid;
				$rootScope.blogComment=$scope.blogComment;
				
				console.log($rootScope.blogComment.blogId);
				console.log($scope.blogComment.blogId);
				
				$location.path("BlogComment");
			
			};
			
			$scope.addBlogCommentProcess=function(bid){
				$scope.blogComment.blogId=$rootScope.blogComment.blogId;
			$http({
				    url : "http://localhost:8081/Middleware/addBlogComment",
				    method : 'POST',
				    data: $scope.blogComment,
				    transformResponse: [
				        function (data) {
				        	$window.location.reload();
				        	alert('Comment Added Succesfully');
				            return data;
				            
				        }
				    ]
				})
			
			};
			
			 $scope.incrementLikes=function(bid){
			        console.log('Entered into incrementLikes function function');
			        $http({
					    url : "http://localhost:8081/Middleware/incrementLikes/"+bid,
					    method : 'GET',
					    transformResponse: [
					        function (data) {
					        	$window.location.reload();
					        	alert('Increment Likes Succesfully');
					            return data;
					            
					        }
					    ]
					}) 
				
				};
				
});