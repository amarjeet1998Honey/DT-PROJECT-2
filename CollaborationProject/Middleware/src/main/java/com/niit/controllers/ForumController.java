
package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.ForumDao;
import com.niit.dao.UserDao;
import com.niit.dto.Blog;
import com.niit.dto.BlogComment;
import com.niit.dto.Forum;
import com.niit.dto.ForumComment;
import com.niit.dto.User;

@RestController
public class ForumController {

	@Autowired
	ForumDao forumDAO;
	
	@Autowired
	UserDao userDAO;
	
	@Autowired
	HttpSession session;
	
	@PostMapping(value="/addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum,HttpSession session){
		
		forum.setCreateDate(new java.util.Date());
		forum.setStatus("Pending");
		if(forumDAO.addForum(forum)){
			return new ResponseEntity<String>("Forum Added Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteForum/{forumId}")
	public ResponseEntity<String> deleteForum(@PathVariable int forumId){
		
		
		System.out.println("Delete Blog in Rest Controller : "+forumId);
		Forum forum=forumDAO.getForum(forumId);
		
		if(forumDAO.deleteForum(forum)){
			return new ResponseEntity<String>("Forum Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Blog",HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/updateForum")
	public ResponseEntity<String> updateForum(@RequestBody Forum forum){
		System.out.println(forum);
		if(forumDAO.updateForum(forum)){
			return new ResponseEntity<String>("Forum Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/getForum/{forumId}")
	   public ResponseEntity<Forum> getForum(@PathVariable int forumId){
			
			Forum forum=forumDAO.getForum(forumId);
			if(forum==null){
				System.out.println("Forum Not Found");
				return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
			}
			else{
				System.out.println("Forum Found "+forum.getForumName());
				return new ResponseEntity<Forum>(forum,HttpStatus.OK);
			}
		}
	
	@GetMapping(value="/approveForum/{forumId}")
	public ResponseEntity<String> approveForum(@PathVariable int forumId){
		
		Forum forum=forumDAO.getForum(forumId);
		if(forumDAO.approveForum(forum)){
			return new ResponseEntity<String>("Forum Approved Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in updating fourm",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/rejectForum/{forumId}")
	public ResponseEntity<String> rejectForum(@PathVariable int forumId){
		Forum forum=forumDAO.getForum(forumId);
		if(forumDAO.rejectForum(forum)){
			return new ResponseEntity<String>("Forum Rejected Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in rejecting Forum",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/listForums")
	public ResponseEntity<List<Forum>> getlistForums(){
		List<Forum> listForum=null;
		User user=(User)session.getAttribute("userObj");
		
		if(user!=null){
			
			if(user.getRole().equals("ROLE_USER")){
				listForum=forumDAO.listForums(user.getLoginName());
			}
			else {
				listForum=forumDAO.listForums(null);
			}
			if(listForum.size()>0) {
            return new ResponseEntity<List<Forum>>(listForum,HttpStatus.OK);
            }
        else {
            return new ResponseEntity<List<Forum>>(listForum,HttpStatus.NOT_FOUND);
        }
        }
        else {
            listForum=forumDAO.listAllApprovedForums();
            return new ResponseEntity<List<Forum>>(listForum,HttpStatus.OK);
            
        }
	}
	
	@GetMapping(value="/listPendingForum")
	public ResponseEntity<List<Forum>> getlistPendingForums(){
			List<Forum> listForum=forumDAO.listPendingForums();
			if(listForum.size()>0)
			return new ResponseEntity<List<Forum>>(listForum,HttpStatus.OK);
			else
				return new ResponseEntity<List<Forum>>(listForum,HttpStatus.NOT_FOUND);	
	}
	
	@PostMapping(value="/addForumComment")
	public ResponseEntity<String> addFourmComment(@RequestBody ForumComment forumComment,HttpSession session){
		
		forumComment.setCommentDate(new Date());
		forumComment.setForumId(forumComment.getForumId());
		
		System.out.println(userDAO.getUser(forumComment.getLoginName()));
		
		if(userDAO.getUser(forumComment.getLoginName())!=null)
		{
			if(forumDAO.addForumComment(forumComment)){
				return new ResponseEntity<String>("Forum Comment Added Succesfully",HttpStatus.OK);
			}
			else{
				System.out.println("I m in else");
				return new ResponseEntity<String>("Error in Adding forum Comments",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			System.out.println("User doesnt exist");
			System.out.println("I m in else 2");
			return new ResponseEntity<String>("User doesnt exist",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		}
	
}

