package com.niit.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.JobDao;
import com.niit.dto.ApplyJob;
import com.niit.dto.Job;

@Repository("jobDAO")
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addJob(Job job) {
		try{
			sessionFactory.getCurrentSession().save(job);
			return true;
			}
		
			catch(Exception e){
			e.printStackTrace();
			
		return false;
			}
	}

	public boolean deleteJob(Job job) {
		try{
			sessionFactory.getCurrentSession().delete(job);
			return true;
			}
		
			catch(Exception e){
			e.printStackTrace();
			
			return false;
			}
	}

	public boolean updateJob(Job job) {
		try{
			sessionFactory.getCurrentSession().update(job);
			return true;
			}
		
			catch(Exception e){
			e.printStackTrace();
			return false;
			}
	}

	public Job getJob(int jobId) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Job job=(Job)session.get(Job.class, jobId);
			return job;
			}
			catch(Exception e){
			e.printStackTrace();
			return null;
			}
	}

	public List<Job> listAllJobs() {
		Session session =sessionFactory.getCurrentSession();
        Criteria cr=session.createCriteria(Job.class);
        List<Job> JobList=cr.list();
            return JobList;
	}

	public boolean applyJob(ApplyJob applyJob) {
		try{
			sessionFactory.getCurrentSession().save(applyJob);
			return true;
			}
		
			catch(Exception e){
			e.printStackTrace();
			return false;
			}
	}

	public List<ApplyJob> getAllAppliedJobDetails() {
		Session session =sessionFactory.getCurrentSession();
        Criteria cr=session.createCriteria(Job.class);
        cr.add(Restrictions.eq("status", "Applied"));
        List<ApplyJob> JobList=cr.list();
            return JobList;
	}

	public List<Job> listSortedJobs() {
		Session session =sessionFactory.getCurrentSession();
        Query q=session.createQuery("from com.niit.dto.Job order by lastDateApply");
        List<Job> JobList1=q.list();
            return JobList1;
	}
}
