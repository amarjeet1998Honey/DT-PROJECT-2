package com.niit.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ProfilePictureDao;
import com.niit.dto.ProfilePicture;

@Repository("profilePictureDAO")
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {

	
	 @Autowired
	    private SessionFactory sessionFactory;
	 
	public void save(ProfilePicture profilePicture) {
		Session session=sessionFactory.getCurrentSession();
        Object obj=session.get(ProfilePicture.class,profilePicture.getLoginName());
        System.out.println(obj);
        if(obj==null){
        session.save(profilePicture);
        }
        else {
            session.merge(profilePicture);
        }

	}

	public ProfilePicture getProfilePicture(String loginName) {
		 Session session=sessionFactory.getCurrentSession();
	        ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, loginName);
	        return profilePicture;
	    }
	}
