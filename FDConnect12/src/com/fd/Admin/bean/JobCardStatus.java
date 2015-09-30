package com.fd.Admin.bean;

public class JobCardStatus {

	private int jobCardStatusId;
	private String jobCardStatusName;
	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	// Setter
	public void setJobCardStatusId(int jobCardStatusId) {
		this.jobCardStatusId = jobCardStatusId;
	}

	public void setJobCardStatusName(String jobCardStatusName) {
		this.jobCardStatusName = jobCardStatusName;
	}

	// Getter
	public int getJobCardStatusId() {
		return jobCardStatusId;
	}

	public String getJobCardStatusName() {
		return jobCardStatusName;
	}

}
