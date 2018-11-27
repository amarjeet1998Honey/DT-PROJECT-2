package com.niit.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Job")
@SequenceGenerator(name="jobidseq",sequenceName="job_id_sequence")
public class Job {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="jobidseq")
	private int jobId;
	private String jobDesignation;
	private String company;
	private int salary;
	private String location;
	private String jobDesc;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date lastDateApply;
	public int getJobId() {
		return jobId;
		
	}
	
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobDesignation=" + jobDesignation + ", company=" + company + ", salary="
				+ salary + ", location=" + location + ", jobDesc=" + jobDesc + ", lastDateApply=" + lastDateApply + "]";
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobDesignation() {
		return jobDesignation;
	}
	public void setJobDesignation(String jobDesignation) {
		this.jobDesignation = jobDesignation;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public Date getLastDateApply() {
		return lastDateApply;
	}
	public void setLastDateApply(Date lastDateApply) {
		this.lastDateApply = lastDateApply;
	}
	
	
}
