package com.niit.dao;

import java.util.List;

import com.niit.dto.Blog;
import com.niit.dto.Forum;
import com.niit.dto.ForumComment;

public interface ForumDao {

	public boolean addForum(Forum forum);
	public boolean deleteForum(Forum forum);
	public boolean updateForum(Forum forum);
	public Forum getForum(int forumId);
	public boolean approveForum(Forum forum);
	public boolean rejectForum(Forum forum);
	public List<Forum> listForums(String userName);
	public List<Forum> listAllApprovedForums();
	public boolean addForumComment(ForumComment ForumComment);
	public boolean deleteForumComment(ForumComment ForumComment);
	public ForumComment getForumComment(int commentId);
	public List<ForumComment> listForumComments(int ForumId);
	public List<Forum> listPendingForums();
	
}
