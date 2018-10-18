package com.leave.management.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.leave.managment.usermodel.Department;

public class DepartmentDao {

	public List<Department> getDepartmentsByHodUserId(String hodUserId) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT d.department_id, d.department_name, d.hod_user_id, ud.first_name, ud.last_name\r\n"
					+ " FROM department d JOIN user_detail ud ON ud.user_id = d.hod_user_id AND d.hod_user_id = '"
					+ hodUserId + "'";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			return iterateResultSet(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Department> getAllDepartments() {
		System.out.println("Executing get All users");
		List<Department> users = Collections.emptyList();
		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "SELECT d.department_id, d.department_name, d.hod_user_id, ud.first_name, ud.last_name\r\n"
					+ " FROM department d JOIN user_detail ud ON ud.user_id = d.hod_user_id ";

			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery(sqlQuery);

			users = iterateResultSet(resultset);

			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	private List<Department> iterateResultSet(ResultSet resultset) {
		List<Department> departments = new ArrayList<>();

		try {
			while (resultset.next()) {

				Integer department_id = resultset.getInt("department_id");
				String departmentName = resultset.getString("department_name");
				Integer hodUserId = resultset.getInt("hod_user_id");
				String hodFirstName = resultset.getString("first_name");
				String hodLastName = resultset.getString("last_name");
				Department department = new Department();

				department.setDepartmentId(department_id);
				department.setDepartmentName(departmentName);
				department.setHodUserId(hodUserId);
				department.setHodName(hodFirstName + " " + hodLastName);

				System.out.println("Department from DB: " + department);
				departments.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return departments;
	}

	public void deleteUser(Integer UserId) {
		System.out.println("Executing delete User method");

		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "DELETE from User where id = '" + UserId + "'";

			Statement statement = connection.createStatement();
			@SuppressWarnings("unused")
			int rowsAffected = statement.executeUpdate(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Department getDepartmentByDeptId(Integer DepartmentId) {
		System.out.println("Executing get Department by Id : " + DepartmentId);
		Department department = null;
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			String query = "SELECT d.department_id, d.department_name, d.hod_user_id, ud.first_name, ud.last_name\r\n"
					+ " FROM department d JOIN user_detail ud ON ud.user_id = d.hod_user_id where d.department_id = '" + DepartmentId + "'";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			List<Department> users = iterateResultSet(rs);
			if (users.size() > 0) {
				department = users.get(0);
			} else {
				System.out.println("Department not found in db with Id : " + DepartmentId);
			}
			return department;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return department;

	}

	public int updateDepartmentHOD(Integer departmentId, Integer userId) {
		System.out.println("Executing department hod");
		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "UPDATE department SET hod_user_id ='" + userId + "' where department_id ='" + departmentId
					+ "'";
			System.out.println(query);

			Statement stmt = connection.createStatement();
			int rowsAffected = stmt.executeUpdate(query);

			System.out.println("Rows inserted: " + rowsAffected);
			return rowsAffected;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

}
