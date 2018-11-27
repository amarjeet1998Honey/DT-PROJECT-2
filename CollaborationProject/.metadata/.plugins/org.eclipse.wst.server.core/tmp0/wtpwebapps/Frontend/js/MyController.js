
var myApp = angular.module("myApp", ["ngRoute",,"ngCookies"]);
myApp.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "Home.html"
    })
    .when("/Login", {
        templateUrl : "user/Login.html"
    })
    .when("/GetContactPage", {
        templateUrl : "Contact.html"
    })
    .when("/About", {
        templateUrl : "About.html"
    })
    .when("/Register", {
        templateUrl : "user/Register.html"
    })
    .when("/UserHome", {
        templateUrl : "user/UserHome.html"
    })
     .when("/UploadProfilePicture", {
        templateUrl : "user/UploadProfilePicture.html"
    })
     .when("/UpdateProfile", {
        templateUrl : "user/UpdateProfile.html"
    })
    .when("/ViewProfile", {
        templateUrl : "user/ViewProfile.html"
    })
    .when("/addBlog", {
        templateUrl : "Blog/AddBlog.html"
    })
    
    .when("/FriendList", {
        templateUrl : "Friend/FriendList.html"
    })
    
    .when("/SuggestedUser", {
        templateUrl : "Friend/SuggestedUser.html"
    })
    
    .when("/PendingRequest", {
        templateUrl : "Friend/PendingRequest.html"
    })
    
     .when("/addJob", {
        templateUrl : "job/AddJob.html"
    })
     .when("/ViewJob", {
        templateUrl : "job/ViewJob.html"
    })
     .when("/ViewBlog", {
        templateUrl : "Blog/ViewBlog.html"
    })
    .when("/ViewAllComments", {
        templateUrl : "Blog/ViewAllComments.html"
    })
    .when("/ViewPendingBlogs", {
        templateUrl : "Blog/ViewPendingBlogs.html"
    })
    .when("/BlogComment", {
        templateUrl : "Blog/BlogComment.html"
    })
    .when("/AddForum", {
        templateUrl : "Forum/AddForum.html"
    })
     .when("/ViewForum", {
        templateUrl : "Forum/ViewForum.html"
    })
    .when("/ViewPendingForums", {
        templateUrl : "Forum/ViewPendingForums.html"
    })
    .when("/logout", {
        templateUrl : "user/Logout.html"
    });
});
myApp.run(function($rootScope,$cookieStore){
	console.log('I m m in run function');
	console.log($rootScope.currentUser);
	
	if($rootScope.currentUser==undefined){
		console.log('I m in if of run function');
		$rootScope.currentUser=$cookieStore.get('userDetails');
		$rootScope.currentBlog=$cookieStore.get('blogDetails');
		$rootScope.currentForum=$cookieStore.get('forumDetails');
	}
	else {
		console.log('I m in else of run function');
		console.log($rootScope.currentUser.userName);
		console.log($rootScope.currentUser.role);
	}
});