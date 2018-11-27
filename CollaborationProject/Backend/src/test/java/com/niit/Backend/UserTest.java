package com.niit.Backend;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.dao.UserDao;
import com.niit.dto.User;

public class UserTest {

	static UserDao userDAO;
	@BeforeClass
	public static void initialize(){
	System.out.println("Initializing Test Case");
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit");
	context.refresh();
	userDAO=(UserDao)context.getBean("userDAO");
	System.out.println("User DAO : "+userDAO);
	}

	/*assertTrue is a method to checks whether the expected value is true or not.*/
	
	@Test
	@Ignore
	public void registerUserTest(){
	User user=new User();
	user.setLoginName("amar");
	user.setFirstName("Divya");
	user.setLastName("Garg");
	user.setEmail("d@gmail.com");
	user.setMobileNumber("9739768899");
	user.setOnlineStatus("N");
	user.setPassword("divya123");
	user.setRole("Role_User");
	assertTrue("Problem in Inserting User",userDAO.registerUser(user));
	}

	@Test
	@Ignore
	public void testUpdateOnlineStatus(){
		User userDetail=userDAO.getUser("amar");
		assertTrue("Problem in Updating",userDAO.updateOnlineStatus("Y", userDetail.getLoginName()));
		}
	
	@Test
	@Ignore
	public void testDeleteUser(){
	User User=userDAO.getUser("sk");
	assertEquals("User Deleted Succesfully", true,userDAO.deleteUser(User));
	}
	
	@Test
	@Ignore
	public void checkLoginTest(){
	User obj=new User();
	obj.setLoginName("divyag");
	obj.setPassword("divya123");
	assertTrue("Check User Fail",userDAO.checkLogin(obj));
	}
	
	@Test
	@Ignore
	public void listUsersTest(){
	List<User> users=userDAO.getUserDetails();
	for(User u:users){
	System.out.println(u.getFirstName()+" "+u.getLastName());
	}
	/*
	* assertTrue will fail if the second parameter evaluates to false 
	* (in other words, it ensures that the value is true). 
	*/
	assertTrue("Users doesnt exist",users.size()!=0);
	}
	
	@Test
	@Ignore
	public void testUpdateUser(){
	User user=userDAO.getUser("amar");
	user.setEmail("vasu@gmail.com");
	assertEquals("Succesfully updated the loginname of the User", true,
	userDAO.updateUser(user));
	}
}
