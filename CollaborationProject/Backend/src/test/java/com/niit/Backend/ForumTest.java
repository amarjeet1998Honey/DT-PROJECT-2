package com.niit.Backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.dao.ForumDao;
import com.niit.dto.Blog;
import com.niit.dto.BlogComment;
import com.niit.dto.Forum;
import com.niit.dto.ForumComment;
import com.niit.dto.User;

public class ForumTest {

	static ForumDao forumDAO;
	@BeforeClass
	public static void initialize(){
	System.out.println("Initializing Test Case");
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit");
	context.refresh();
	forumDAO=(ForumDao)context.getBean("forumDAO");
	System.out.println("forum DAO : "+forumDAO);
	}
	
	@Test
	@Ignore
	public void addForum(){
	Forum forum =new Forum();
	forum.setForumName("Divya");
	forum.setForumContent("Form for blog and user");
	
	Date date=new Date();
	forum.setCreateDate(date);
	
	forum.setLoginName("divyag");
	forum.setStatus("Pending");
	
	assertTrue("Problem in Inserting forum",forumDAO.addForum(forum));
	}
	
	@Test
	@Ignore
    public void updateForum() {
        Forum forum=forumDAO.getForum(52);
        forum.setForumName("AngularJS");
        forum.setForumContent("AngularJS is java framework ");
        assertEquals("Succesfully updated the loginname of the User", true,
                forumDAO.updateForum(forum));
    }
	
	@Test
	@Ignore
	public void deleteForum(){
	 Forum forum=forumDAO.getForum(1);
	assertEquals("User Deleted Succesfully", true,forumDAO.deleteForum(forum));
	}
	
	@Test

	public void approveForum(){
		Forum forum=forumDAO.getForum(52);
		System.out.println("Forum -"+forum);
		
	assertTrue("Forum Approved Sucessfully",forumDAO.approveForum(forum));	
	}
	
	@Test
	@Ignore
	public void rejectForum(){
		Forum forum=forumDAO.getForum(52);
		System.out.println("Forum -"+forum);
		
	assertTrue("Blog Rejected Sucessfully",forumDAO.rejectForum(forum));	
	}
	
	 @Test
	    @Ignore
	    public void addForumComment() {
	        ForumComment forum =new ForumComment();
	        forum.setCommentText("nice blog");
	        Date date=new Date();
	        forum.setCommentDate(date);
	        forum.setLoginName("amar");
	        forum.setForumId(1);
	         assertTrue("Comment Add Succesfully",forumDAO.addForumComment(forum));
	    }
	    @Test
	    @Ignore
	    public void deleteForumComment() {
	    ForumComment forum=forumDAO.getForumComment(50);
	    forum.setLoginName("janvi");
	    forum.setCommentText("nice blog");
	    forum.setForumId(50);
	    assertEquals("Succesfully Comment Deleted",true,forumDAO.deleteForumComment(forum));
	    
	        
	    }
}
