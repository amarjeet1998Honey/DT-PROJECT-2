package com.niit.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.UserDao;
import com.niit.dto.User;


@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean registerUser(User user) {
		try{
			sessionFactory.getCurrentSession().save(user);
			return true;
			}
			catch(Exception e){
			e.printStackTrace();
		return false;
			}
	}

	public boolean checkLogin(User user) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from User where loginName=:x and password=:y");
			query.setParameter("x",user.getLoginName());
			query.setParameter("y",user.getPassword());
			List<User> list=query.list();
			System.out.println("List = "+list);
			if(list.size()>0){
			User userDetailsObj=list.get(0);
			return true;
			}
			else {
			return false;
			}
			}
			catch(Exception e){
			e.printStackTrace();
			}
			return false;
	}

	public boolean updateOnlineStatus(String status, String loginName) {
		try{
			Session session=sessionFactory.getCurrentSession();
			User user=(User)session.get(User.class,loginName);
			user.setOnlineStatus(status);
			sessionFactory.getCurrentSession().merge(user);
			return true;
			}
			catch(Exception e){
			e.printStackTrace();
			return false;
			}
		}

	public User getUser(String loginName) {
		try{
			Session session=sessionFactory.getCurrentSession();
			User user=(User)session.get(User.class, loginName);
			return user;
			}
			catch(Exception e){
			e.printStackTrace();
			return null;
			}
	}

	public List<User> getUserDetails() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery
		("from com.niit.dto.User");
		List<User> list= query.list();
		return list;
	}

	public boolean deleteUser(User user) {
		try{
			sessionFactory.getCurrentSession().delete(user);
			return true;
			}
			catch(Exception e){
			e.printStackTrace();
			return false;
			}
	}

	public boolean updateUser(User user) {
		try{
			sessionFactory.getCurrentSession().update(user);
			return true;
			}
			catch(Exception e){
			e.printStackTrace();
			return false;
			}
	}

}
