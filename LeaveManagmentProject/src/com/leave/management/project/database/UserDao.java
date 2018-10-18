package com.leave.management.project.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.leave.managment.usermodel.Department;
import com.leave.managment.usermodel.User;
import com.leave.management.project.database.DatabaseConnection;

public class UserDao {

	public int save(User user) {
		Connection connection;
		int rowsAffected = 0;
		try {
			connection = DatabaseConnection.getConnection();
			String query = "INSERT INTO user_login (user_name, password,role) values ( '" + user.getUserName() + "','"
					+ user.getPassword() + "','" + user.getRole() + "')";
			System.out.println(query);

			Statement stmt = connection.createStatement();
			rowsAffected = stmt.executeUpdate(query);

			System.out.println("Rows inserted: " + rowsAffected);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			connection = DatabaseConnection.getConnection();
			User users = getuser(user.getUserName(), user.getPassword());

			String query = "INSERT INTO user_detail (first_name, last_name,department_id,user_id) values ( '"
					+ user.getFirstName() + "','" + user.getLastName() + "','" + user.getDepartment() + "','"
					+ users.getId() + "')";
			System.out.println(query);

			Statement stmt = connection.createStatement();
			rowsAffected = stmt.executeUpdate(query);

			connection.close();
			return rowsAffected;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public User getuser(String userName, String password) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT * FROM user_login where is_deleted = 0 AND user_name = '" + userName + "' AND password ='" + password
					+ "'";

			// Connection connection = DatabaseConnection.getConnection();
			// String query = "SELECT * FROM user where user_detail = '" + userName + "' AND
			// password ='" + password+ "'";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			User user = null;
			while (rs.next()) {
				user = new User();
				Integer userIdFromDb = rs.getInt("id");
				String userNameFromDb = rs.getString("user_name");
				String passwordFromDb = rs.getString("password");
				String roleFromDb = rs.getString("role");

				user.setId(userIdFromDb);
				user.setUserName(userNameFromDb);
				user.setPassword(passwordFromDb);
				user.setRole(roleFromDb);
				//
			}

			if (user == null) {
				System.out.println("user not exist in db with userName : " + userName);
			} else {
				System.out.println("user exist in db with userName : " + userName);
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAllusers() {
		System.out.println("Executing get All users");
		List<User> users = Collections.emptyList();
		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "SELECT ul.id as user_id, ud.first_name, ud.last_name, ul.user_name, d.department_name, d.department_id, ul.role\r\n"
					+ "  FROM user_detail ud JOIN user_login ul ON ud.user_id = ul.id\r\n"
					+ "JOIN department d ON ud.department_id = d.department_id\r\n" + " where ul.role <> 'ADMIN' AND ud.is_deleted = 0";

			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery(sqlQuery);

			users = iterateResultSet(resultset);

			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	private List<User> iterateResultSet(ResultSet resultset) {
		List<User> users = new ArrayList<>();

		try {
			while (resultset.next()) {
				String firstName = resultset.getString("first_name");
				String lastName = resultset.getString("last_name");
				Integer departmentId = resultset.getInt("department_id");
				String departmentName = resultset.getString("department_name");
				String role = resultset.getString("role");
				Integer userId = resultset.getInt("user_id");

				User user = new User();

				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setDepartment(departmentId);
				user.setId(userId);

				user.setRole(role);
				user.setDepartmentName(departmentName);
				System.out.println("user from DB: " + user);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public void deleteUser(Integer UserId) {
		System.out.println("Executing delete User method");

		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "update user_login set is_deleted = 1 where id = '" + UserId + "'";

			Statement statement = connection.createStatement();
			@SuppressWarnings("unused")
			int rowsAffected = statement.executeUpdate(sqlQuery);

			sqlQuery = "update user_detail set is_deleted = 1 where user_id = '" + UserId + "'";

			statement = connection.createStatement();
			rowsAffected = statement.executeUpdate(sqlQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int updateUser(User emp) {
		System.out.println("Executing update User");
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			
			String query = "UPDATE user_detail SET first_name ='" + emp.getFirstName() + "', last_name= '"	+ emp.getLastName() + "',"
					+ "department='"+emp.getDepartmentId()+"'"
					+ " where id ='" + emp.getId() + "'";
			System.out.println(query);

			Statement stmt = connection.createStatement();
			int rowsAffected = stmt.executeUpdate(query);

			System.out.println("Rows inserted: " + rowsAffected);

			query = "UPDATE user_login SET user_name ='" + emp.getUserName() + "', password= '" + emp.getPassword()	+ "' where id='"+emp.getId()+"'" ;
			System.out.println(query);

			stmt = connection.createStatement();
			rowsAffected = stmt.executeUpdate(query);

			return rowsAffected;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public User getUserDetailsByUserId(Integer UserId) {
		System.out.println("Executing get User by Id : " + UserId);
		User User = null;
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			String query = "SELECT ul.id as user_id, ud.first_name, ud.last_name, ul.user_name, d.department_name, d.department_id, ul.role\r\n"
					+ "  FROM user_detail ud JOIN user_login ul ON ud.user_id = ul.id\r\n"
					+ "LEFT OUTER JOIN department d ON ud.department_id = d.department_id\r\n" + " where ul.is_deleted = 0 AND ud.user_id ='" + UserId + "'";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			List<User> Users = iterateResultSet(rs);
			if (Users.size() > 0) {
				User = Users.get(0);
			} else {
				System.out.println("User not found in db with Id : " + UserId);
			}
			return User;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return User;
	}

	
	public int updateRole(Integer userId, String role) {

		System.out.println("Executing update User Role");
		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "UPDATE user_login SET role ='" + role + "' where id ='" + userId + "'";
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
