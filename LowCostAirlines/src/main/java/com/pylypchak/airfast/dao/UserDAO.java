package com.pylypchak.airfast.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pylypchak.airfast.model.Ticket;
import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.statements.UserSQLStatements;
import com.pylypchak.airfast.util.ConnectionManager;
import com.pylypchak.airfast.util.Transformer;

public class UserDAO {

	public void saveUser(User user) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(UserSQLStatements.INSERT_USER_SQL);
		Transformer.valueIntoPreparedStatement(statement, user,
				UserSQLStatements.INSERT_USER_SQL_ORDER);
		statement.executeUpdate();
	}
	}

	public List<User> getAllUsers() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(UserSQLStatements.SELECT_ALL_SQL);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, User.class);
	}
	}

	public List<User> getAllAdmins() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(UserSQLStatements.SELECT_ADMINS_SQL);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, User.class);
	}
	}

	public User getUserByEmail(String email)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(UserSQLStatements.SELECT_USER_BY_EMAIL);
		User user = new User();
		user.setEmail(email);
		Transformer.valueIntoPreparedStatement(statement, user,
				UserSQLStatements.SELECT_USER_BY_EMAIL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoObject(resultSet, User.class);
	}
	}

	public User getUserByConfirmCode(String confirmCode)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(UserSQLStatements.SELECT_USER_BY_CONFIRM_CODE_SQL);
		User user = new User();
		user.setConfirmCode(confirmCode);
		Transformer.valueIntoPreparedStatement(statement, user,
				UserSQLStatements.SELECT_USER_BY_CONFIRM_CODE_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoObject(resultSet, User.class);
	}
	}
	
	public void updateUser(User user) throws ReflectiveOperationException,
			SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(UserSQLStatements.UPDATE_USER_SQL);
		Transformer.valueIntoPreparedStatement(statement, user,
				UserSQLStatements.UPDATE_USER_SQL_ORDER);
		statement.executeUpdate();
	}
	}

	public boolean isEmailFree(String email)
			throws ReflectiveOperationException, SQLException {
		if (getUserByEmail(email) == null) {
			return true;
		}
		return false;
	}

	public User getUserBySocialId(String socialId) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(
						UserSQLStatements.SELECT_USER_BY_SOCIAL_ID_SQL);
		User user = new User();
		user.setSocialId(socialId);
		Transformer.valueIntoPreparedStatement(statement, user,
				UserSQLStatements.SELECT_USER_BY_SOCIAL_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoObject(resultSet, User.class);
	}
	}

	public User getUserById(Integer id) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(UserSQLStatements.SELECT_USER_BY_ID_SQL);
		User user = new User();
		user.setId(id);
		Transformer.valueIntoPreparedStatement(statement, user,
				UserSQLStatements.SELECT_USER_BY_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoObject(resultSet, User.class);
	}
		
	
	}
	public Map<Ticket,User> getUsersAndTicketsByflightId(Integer flightId) throws SQLException{
		try (Connection connection = ConnectionManager.getConnection()) {
			Map<Ticket, User> result = new HashMap<Ticket, User>();
			CallableStatement statement =connection.prepareCall(UserSQLStatements.CALL_GET_USERS_BY_FLIGHT_ID);
			statement.setInt(1, flightId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()){
				User user = new User();
				Ticket ticket = new Ticket();
				user .setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setEmail(resultSet.getString("email"));
				ticket.setPrice(resultSet.getDouble("price"));
				ticket.setDate(resultSet.getTimestamp("date"));
				ticket.setStatus(resultSet.getString("status"));
				result.put(ticket, user);
			}
			
			return result;
		}
		
	}
}
