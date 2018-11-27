package com.niit.controllers;

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

import com.niit.dao.UserDao;
import com.niit.dto.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDAO;
	

	@GetMapping(value="/demo")
	public ResponseEntity<String> demoPurpose(){
		return new ResponseEntity<String>("Demo Data",HttpStatus.OK);
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<String> registerUser(@RequestBody User user){
			user.setOnlineStatus("Offline");
			user.setRole("ROLE_USER");
			
			if(userDAO.registerUser(user)){
				return new ResponseEntity<String>("User Registered Succesfully ",HttpStatus.OK);
			}
			else{
				return new ResponseEntity<String>("Error in Registered user .Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			}
	
	@PostMapping(value="/login")
	public ResponseEntity<User> checkLogin(@RequestBody User user,HttpSession session){
		System.out.println(user.getLoginName()+" "+user.getPassword());
			if(userDAO.checkLogin(user)){
				
				System.out.println("I am Vaild User");
				User user1=(User)userDAO.getUser(user.getLoginName());
				userDAO.updateOnlineStatus("Online", user.getLoginName());
				
				session.setAttribute("userObj", user1);
				System.out.println("Attribute Added in session");
				
				return new ResponseEntity<User>(user1,HttpStatus.OK);
			}
			else{
				System.out.println("Invaild user");
				return new ResponseEntity<User>(user,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			}
	
	@GetMapping(value="/updateOnlineStatus/{status}/{loginName}")
	public ResponseEntity<String> updateOnlineStatus(@PathVariable String status,@PathVariable String loginName){
		System.out.println("Status : "+status);
		System.out.println("Loginname : "+loginName);
		if(userDAO.updateOnlineStatus(status, loginName)){
			return new ResponseEntity<String>("Status Updated Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Not able to update status succesfully",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PostMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestBody User user){
		User userObj=userDAO.getUser(user.getLoginName());
		if(userDAO.deleteUser(userObj)){
			return new ResponseEntity<String>("User deleted succesfully...",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Problem in deleting User...",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user){
		
		if(userDAO.updateUser(user)){
			System.out.println("In IF");
			return new ResponseEntity<String>("User updated succesfully...",HttpStatus.OK);
		}
		else {
			System.out.println("In ELSE");
			return new ResponseEntity<String>("Problem in updating User...",HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value="/getListOfUsers")
	public ResponseEntity<List<User>> getUsersList(){
		List<User> list=userDAO.getUserDetails();
		if(list.size()==0){
			return new ResponseEntity<List<User>>(list,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<User>>(list,HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping(value="getUser/{loginName}")
	public ResponseEntity<User> getUserByLoginName(@PathVariable String loginName){
		
		System.out.println("In get user function"+loginName);
		User user=userDAO.getUser(loginName);
		if(user!=null){
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="checkStatus")
	public ResponseEntity<String> checkStatus(){
		return  new ResponseEntity<String>("Checking Status",HttpStatus.OK);
	}
	
	
	}
 