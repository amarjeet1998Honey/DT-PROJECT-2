package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dto.ApplyJob;
import com.niit.dto.Blog;
import com.niit.dto.BlogComment;
import com.niit.dto.Forum;
import com.niit.dto.ForumComment;
import com.niit.dto.Friend;
import com.niit.dto.Job;
import com.niit.dto.ProfilePicture;
import com.niit.dto.User;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DBConfig {
	
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("amar");
		dataSource.setPassword("amar");
		return dataSource;
	}

	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(){
	Properties hibernateProperties=new Properties();
	hibernateProperties.put("hibernate.hbm2ddl.auto","update");
	hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.Oracle8iDialect");
	hibernateProperties.put("hibernate.show_sql",true);
	LocalSessionFactoryBuilder sessionFactoryBuilder=new LocalSessionFactoryBuilder(getDataSource());
	sessionFactoryBuilder.addProperties(hibernateProperties);
	sessionFactoryBuilder.addAnnotatedClass(User.class);
	sessionFactoryBuilder.addAnnotatedClass(Blog.class);
	sessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
	sessionFactoryBuilder.addAnnotatedClass(Forum.class);
	sessionFactoryBuilder.addAnnotatedClass(ForumComment.class);
	sessionFactoryBuilder.addAnnotatedClass(Job.class);
	sessionFactoryBuilder.addAnnotatedClass(ApplyJob.class);
	sessionFactoryBuilder.addAnnotatedClass(ProfilePicture.class);
	sessionFactoryBuilder.addAnnotatedClass(Friend.class);
	/*buildSessionFactory() method of LocalSessionFactoryBuilder will
	help to create SessionFactory . In the LocalSessionFactoryBuilder
	we will pass all the information required .*/
	SessionFactory sessionFactory=sessionFactoryBuilder.buildSessionFactory();
	System.out.println("====SessionFactory Object======");
	return sessionFactory;
	}
	
	@Bean(name="transactionManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory){
	System.out.println("---Creating Transaction Manager---");
	return new HibernateTransactionManager(sessionFactory);
	}
	/*@Bean(name="blogDAO")
	public BlogDAO getBlogDAO(){
	System.out.println("Creating Blog DAO");
	return new BlogDAOImpl();
	}
	@Bean(name="forumDAO")
	public ForumDAO getForumDAO(){
	System.out.println("Creating Forum DAO");
	return new ForumDAOImpl();
	}
	@Bean(name="profilePictureDAO")
	public ProfilePictureUpload getProfilePictureDAO(){
	System.out.println("Creating Profile Picture DAO");
	return new ProfilePictureUploadImpl();
	}*/
}

