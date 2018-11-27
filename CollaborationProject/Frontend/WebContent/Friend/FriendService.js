myApp.factory('FriendService',function($http){
    var friendService={}
     
    friendService.getAllSuggestedUsers=function(){
        return $http.get("http://localhost:8081/Middleware/suggestedusers")
    }
    friendService.addFriend=function(toId){
        return $http.post("http://localhost:8081/Middleware/addfriend",toId)
    }
     
    friendService.getPendingRequests=function(){
        return $http.get("http://localhost:8081/Middleware/pendingrequests")
    }
     
    friendService.acceptRequest=function(request){
        return $http.put("http://localhost:8081/Middleware/acceptrequest",request)
    }
     
    friendService.deleteRequest=function(request){
        return $http.put("http://localhost:8081/Middleware/deleterequest",request)
    }
     
    friendService.getAllFriends=function(){
        return $http.get("http://localhost:8081/Middleware/friends")
    }
     
    return friendService;
})