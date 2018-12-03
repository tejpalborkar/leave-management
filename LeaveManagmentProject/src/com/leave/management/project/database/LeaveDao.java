package com.leave.management.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.leave.managment.usermodel.Leave;
import com.leave.managment.usermodel.LeaveStatus;
import com.leave.managment.usermodel.User;
import com.leave.management.project.database.DatabaseConnection;

public class LeaveDao {

	public int save(Leave leave) {
		Connection connection;
		int rowsAffected = 0;
		try {
			connection = DatabaseConnection.getConnection();
			String query = "INSERT INTO leaves (user_id,from_date,to_date,reason,hod_user_id,status) values ( '"
					+ leave.getUserId() + "','" + leave.getFromDate() + "','" + leave.getToDate() + "','"
					+ leave.getReason() + "','" + leave.getHodUserId() + "','" + leave.getStatus() + "')";
			System.out.println(query);

			Statement stmt = connection.createStatement();
			rowsAffected = stmt.executeUpdate(query);

			System.out.println("Rows inserted: " + rowsAffected);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public User getuser(String userName, String password) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT * FROM user_login where user_name = '" + userName + "' AND password ='" + password
					+ "'";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			User user = null;
			while (rs.next()) {
				user = new User();
				Integer userIdFromDb = rs.getInt("id");
				String userNameFromDb = rs.getString("user_name");
				String passwordFromDb = rs.getString("password");

				user.setId(userIdFromDb);
				user.setUserName(userNameFromDb);
				user.setPassword(passwordFromDb);
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

	public List<Leave> getAllleaves() {
		System.out.println("Executing get All leaves");
		List<Leave> leaves = Collections.emptyList();
		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "SELECT l.* FROM leaves l JOIN user_detail ud ON l.user_id = ud.user_id";

			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery(sqlQuery);

			leaves = iterateResultSet(resultset);

			return leaves;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaves;
	}

	public List<Leave> getAllLeavesByUserId(Integer userId) {
		System.out.println("Executing get All leaves");
		List<Leave> leaves = Collections.emptyList();
		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "SELECT * FROM user_detail where user_id ='" + userId + "'";

			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery(sqlQuery);

			leaves = iterateResultSet(resultset);

			return leaves;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaves;
	}

	public List<Leave> getLeavesByStatusAndHodUser(Integer hodUserId, String leaveStatus) {
		System.out.println("Executing getLeavesByStatusAndHodUser");
		List<Leave> leaves = Collections.emptyList();
		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "SELECT l.*, ud.first_name, ud.last_name FROM leaves l JOIN user_detail ud ON l.user_id = ud.user_id where hod_user_id ='" + hodUserId + "' AND status = '" + leaveStatus
					+ "'";

			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery(sqlQuery);

			leaves = iterateResultSet(resultset);
			for (Leave leave : leaves) {
				System.out.println(leave);
			}
			return leaves;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaves;
	}
	public List<Leave> searchLeaves(String leaveFrom, String leaveTo,Integer hodUserId) {
		System.out.println("Executing searchLeaves for fromDate: "+leaveFrom+" To Date: "+leaveTo );
		List<Leave> leaves = Collections.emptyList();
		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "SELECT l.*, ud.first_name, ud.last_name FROM leaves l JOIN user_detail ud ON l.user_id = ud.user_id where hod_user_id ='"
					+ hodUserId + "' AND from_date >='" + leaveFrom + "' AND to_date <='" + leaveTo
					+ "' ORDER by from_date desc";
			System.out.println(sqlQuery);
			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery(sqlQuery);

			leaves = iterateResultSet(resultset);
			for (Leave leave : leaves) {
				System.out.println(leave);
			}
			return leaves;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Executed searchLeaves");
		return leaves;
	}

	public List<Leave> getApprovedAndRejectedAndHodUser(Integer hodUserId) {
		System.out.println("Executing getApprovedAndRejectedAndHodUser");
		List<Leave> leaves = Collections.emptyList();
		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "SELECT l.*, ud.first_name, ud.last_name FROM leaves l JOIN user_detail ud ON l.user_id = ud.user_id where hod_user_id ='" + hodUserId + "' AND status != '"
					+ LeaveStatus.NEW + "'";

			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery(sqlQuery);

			leaves = iterateResultSet(resultset);
			for (Leave leave : leaves) {
				System.out.println(leave);
			}

			return leaves;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaves;
	}

	private List<Leave> iterateResultSet(ResultSet resultset) {
		List<Leave> leaves = new ArrayList<>();

		try {
			while (resultset.next()) {
				Integer leaveId = resultset.getInt("id");
				Integer userId = resultset.getInt("user_id");
				String fromDate = resultset.getString("from_date");
				String toDate = resultset.getString("to_date");
				String reason = resultset.getString("reason");
				Integer hodUserId = resultset.getInt("hod_user_id");
				String status = resultset.getString("status");
				String appliedDate = resultset.getString("applied_date");
				String type = resultset.getString("type");
				String firstName = resultset.getString("first_name");
				String lastName = resultset.getString("last_name");
				
				Leave leave = new Leave();
				leave.setLeaveType(type);
				leave.setFromDate(fromDate);
				leave.setToDate(toDate);

				leave.setReason(reason);
				leave.setUserId(userId);
				leave.setStatus(LeaveStatus.NEW);
				leave.setHodUserId(hodUserId);
				leave.setStatus(status);
				leave.setAppliedDate(appliedDate);
				leave.setLeaveId(leaveId);
				System.out.println("leave from DB: " + leave);

				leave.applicantName = firstName + " " +lastName;
				leaves.add(leave);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return leaves;
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

	public List<Leave> getLeavesByUserId(Integer UserId) {
		System.out.println("Executing get User by Id : " + UserId);
		List<Leave> leaves = Collections.emptyList();
		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "SELECT l.*, ud.first_name, ud.last_name FROM leaves l JOIN user_detail ud ON l.user_id = ud.user_id where l.user_id = '" + UserId + "'";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			leaves = iterateResultSet(rs);

			return leaves;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return leaves;

	}

	public int updateUser(User emp) {
		System.out.println("Executing update User");
		try {
			Connection connection = DatabaseConnection.getConnection();
			String query = "UPDATE User SET first_name ='" + emp.getFirstName() + "', last_name= '" + emp.getLastName()
					+ "',user_name ='" + emp.getUserName() + "',password='" + emp.getPassword() + "' where id ='"
					+ emp.getId() + "'";
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

	public void deleteLeave(Integer leaveId) {

		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "DELETE from leaves where id = '" + leaveId + "'";

			Statement statement = connection.createStatement();
			@SuppressWarnings("unused")
			int rowsAffected = statement.executeUpdate(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateStatus(String status, Integer leaveId) {
		try {

			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "UPDATE leaves SET status = '" + status + "' where id = '" + leaveId + "'";

			Statement statement = connection.createStatement();
			@SuppressWarnings("unused")
			int rowsAffected = statement.executeUpdate(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
