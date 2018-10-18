package com.leave.managment.usermodel;

public class Department {

	private Integer departmentId;
	private String departmentName;
	private Integer hodUserId;
	
	private String hodName;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	
	public String getHodName() {
		return hodName;
	}

	public void setHodName(String hodName) {
		this.hodName = hodName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getHodUserId() {
		return hodUserId;
	}

	public void setHodUserId(Integer hodUserId) {
		this.hodUserId = hodUserId;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", hodUserId="
				+ hodUserId + "]";
	}

}
