package com.niit.dao;

import com.niit.dto.ProfilePicture;

public interface ProfilePictureDao {

	 public void save(ProfilePicture profilePicture);
	    public ProfilePicture getProfilePicture(String loginName);
}
