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

import com.niit.dao.BlogDao;
import com.niit.dao.UserDao;
import com.niit.dto.Blog;
import com.niit.dto.BlogComment;
import com.niit.dto.User;

@RestController
public class BlogController {
	
	@Autowired
	BlogDao blogDAO;
	
	@Autowired
	UserDao userDAO;
	
	
	@Autowired
	HttpSession session;
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog,HttpSession session){
		
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setStatus("Pending");
		
		User user=(User)session.getAttribute("userObj");
		System.out.println("User = "+user.getLoginName());
		blog.setLoginName(user.getLoginName());
		
		if(blogDAO.addBlog(blog)){
			return new ResponseEntity<String>("Blog Added Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable int blogId){
		
		
		System.out.println("Delete Blog in Rest Controller : "+blogId);
		Blog blog=blogDAO.getBlog(blogId);
		
		if(blogDAO.deleteBlog(blog)){
			return new ResponseEntity<String>("Blog Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Blog",HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/updateBlog")
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog){
		System.out.println(blog);
		if(blogDAO.updateBlog(blog)){
			return new ResponseEntity<String>("Blog Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/getBlog/{blogId}")
   public ResponseEntity<Blog> getBlog(@PathVariable int blogId){
		
		Blog blog=blogDAO.getBlog(blogId);
		if(blog==null){
			System.out.println("Blog Not Found");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		else{
			System.out.println("Blog Found "+blog.getBlogName());
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable int blogId){
		
		Blog blog=blogDAO.getBlog(blogId);
		if(blogDAO.approveBlog(blog)){
			return new ResponseEntity<String>("Blog Approved Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable int blogId){
		Blog blog=blogDAO.getBlog(blogId);
		if(blogDAO.rejectBlog(blog)){
			return new ResponseEntity<String>("Blog Rejected Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in rejecting blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/listBlog")
	public ResponseEntity<List<Blog>> getlistBlogs(){
		List<Blog> listBlog=null;
		User user=(User)session.getAttribute("userObj");
		
		if(user!=null){
			System.out.println("Role : "+user.getRole());
			if(user.getRole().equals("ROLE_USER")){
			System.out.println("Role is User"+user.getRole());	
			listBlog=blogDAO.listBlogs(user.getLoginName());
			}
			
			else {
			System.out.println("Role in  Admin" + user.getRole());
			listBlog=blogDAO.listBlogs(null);	
			}
			
            if(listBlog.size()>0) {
            return new ResponseEntity<List<Blog>>(listBlog,HttpStatus.OK);
            }
        else {
            return new ResponseEntity<List<Blog>>(listBlog,HttpStatus.NOT_FOUND);
        }
        }
        else {
            listBlog=blogDAO.listAllApprovedBlogs();
            return new ResponseEntity<List<Blog>>(listBlog,HttpStatus.OK);
            
        }
	}
	
	@GetMapping(value="/listPendingBlog")
	public ResponseEntity<List<Blog>> getlistPendingBlogs(){
			List<Blog> listBlog=blogDAO.listPendingBlogs();
			if(listBlog.size()>0)
			return new ResponseEntity<List<Blog>>(listBlog,HttpStatus.OK);
			else
				return new ResponseEntity<List<Blog>>(listBlog,HttpStatus.NOT_FOUND);	
	}
	
	@GetMapping(value="/incrementLikes/{blogId}")
	public ResponseEntity<String> incrementLikes(@PathVariable int blogId){
		
		System.out.println("Increment Blog in Rest Controller : "+blogId);
		Blog blog=blogDAO.getBlog(blogId);
		
		if(blogDAO.incrementLikes(blog)){
			return new ResponseEntity<String>("Blog's likes incremented",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Not able to increment blog likes",HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/addBlogComment")
	public ResponseEntity<String> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		
		blogComment.setCommentDate(new Date());
		blogComment.setBlogId(blogComment.getBlogId());
		
		User user=(User)session.getAttribute("userObj");
		System.out.println("User = "+user.getLoginName());
		blogComment.setLoginname(user.getLoginName());
		
		
		System.out.println(userDAO.getUser(blogComment.getLoginname()));
		
		if(userDAO.getUser(blogComment.getLoginname())!=null)
		{
			if(blogDAO.addBlogComment(blogComment)){
				return new ResponseEntity<String>("Blog Comment Added Succesfully",HttpStatus.OK);
			}
			else{
				System.out.println("I m in else");
				return new ResponseEntity<String>("Error in Adding blog Comments",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			System.out.println("User doesnt exist");
			System.out.println("I m in else 2");
			return new ResponseEntity<String>("User doesnt exist",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		}
}
