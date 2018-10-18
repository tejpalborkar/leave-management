package com.leave.managment.usermodel;

public class Leave {

	private String leaveType;
	private String fromDate;
	private String toDate;
	private String reason;

	private Integer leaveId;
	private Integer hodUserId;
	private Integer userId;
	private String status;
	private String appliedDate;
	public String applicantName;

	
	
	public Integer getLeaveId() {
		return leaveId;
	}


	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}


	public String getAppliedDate() {
		return appliedDate;
	}

	
	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getHodUserId() {
		return hodUserId;
	}

	public void setHodUserId(Integer hodUserId) {
		this.hodUserId = hodUserId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getApplicantName() {
		return applicantName;
	}


	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}


	@Override
	public String toString() {
		return "Leave [leaveType=" + leaveType + ", fromDate=" + fromDate + ", toDate=" + toDate + ", reason=" + reason
				+ ", hodUserId=" + hodUserId + ", userId=" + userId + ", status=" + status + "]";
	}

}
