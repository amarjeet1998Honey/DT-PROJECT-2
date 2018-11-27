package com.niit.dao;

import java.util.List;

import com.niit.dto.ApplyJob;
import com.niit.dto.Job;

public interface JobDao {

	public boolean addJob(Job job);
	public boolean deleteJob(Job job);
	public boolean updateJob(Job job);
	public Job getJob(int jobId);
	public List<Job> listAllJobs();
	public List<Job> listSortedJobs();
	public boolean applyJob(ApplyJob applyJob);
	public List<ApplyJob> getAllAppliedJobDetails();
}
