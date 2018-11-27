package com.niit.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table

public class ProfilePicture {
 
	    @Id
	    private String loginName;
	     
	    @Lob
	    private byte[] image;
	 
	    public String getLoginName() {
	        return loginName;
	    }
	 
	    public void setLoginName(String loginName) {
	        this.loginName = loginName;
	    }
	 
	    public byte[] getImage() {
	        return image;
	    }
	 
	    public void setImage(byte[] image) {
	        this.image = image;
	    }
	     
	     
	}
