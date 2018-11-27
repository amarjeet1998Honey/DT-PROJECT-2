package com.niit.Backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDao;
import com.niit.dto.Blog;
import com.niit.dto.BlogComment;
import com.niit.dto.Forum;

public class BlogTest {

	static BlogDao blogDAO;
	@BeforeClass
	public static void initialize(){
	System.out.println("Initializing Test Case");
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit");
	context.refresh();
	blogDAO=(BlogDao)context.getBean("blogDAO");
	System.out.println("Blog DAO : "+blogDAO);
	}
	
	@Test
	@Ignore
	public void addBlogTest(){
	Blog blog=new Blog();
	blog.setBlogName("divy");
	blog.setBlogContent("Angular js Class");
	
	Date date=new Date();
	blog.setCreateDate(date);
	
	blog.setLoginName("divyag");
	blog.setStatus("NA");
	blog.setLikes(0);
	
	assertTrue("Problem in Inserting Blog",blogDAO.addBlog(blog));
	}
	
	@Test
	public void approveBlog(){
		Blog blog=blogDAO.getBlog(52);
		System.out.println("Blog -"+blog);
		
	assertTrue("Blog Approved Sucessfully",blogDAO.approveBlog(blog));	
	}
	
	@Test
	@Ignore
	public void rejectBlog(){
		Blog blog=blogDAO.getBlog(52);
		System.out.println("Blog -"+blog);
		
	assertTrue("Blog Rejected Sucessfully",blogDAO.rejectBlog(blog));	
	}
	
	@Test
	@Ignore
	public void updateBlog() {
        Blog blog=blogDAO.getBlog(952);
        blog.setBlogName("AngularJS_1");
        blog.setBlogContent("AngularJS is java framework _1");
        assertEquals("Succesfully updated the blog", true,
                blogDAO.updateBlog(blog));
    }
    @Test
    @Ignore
	public void deleteBlog(){
	 Blog blog=blogDAO.getBlog(1);
	assertEquals("User Deleted Succesfully", true,blogDAO.deleteBlog(blog));
	}
    
    @Test
    @Ignore
    public void addBlogComment() {
        BlogComment blog4 =new BlogComment();
        blog4.setCommentText("nice blog");
        Date date=new Date();
        blog4.setCommentDate(date);
        blog4.setLoginname("amar");
        blog4.setBlogId(1);
         assertTrue("Comment Add Succesfully",blogDAO.addBlogComment(blog4));
    }
    @Test
    @Ignore
    public void deleteBlogComment() {
    BlogComment blog5=blogDAO.getBlogComment(50);
    blog5.setLoginname("janvi");
    blog5.setCommentText("nice blog");
    blog5.setBlogId(50);
    assertEquals("Succesfully Comment Deleted",true,blogDAO.deleteBlogComment(blog5));
    
        
    }
    
    @Test
    @Ignore
    public void incrementLikes() {
        Blog blog=blogDAO.getBlog(150);
        System.out.println("Blog Obj = "+blog);
    //blog6.setLikes());
        assertEquals("Succesfully increment likes",true,blogDAO.incrementLikes(blog));
        
}
}

