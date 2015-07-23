package com.pylypchak.airfast.statements;

public class UserSQLStatements {
	public static final String INSERT_USER_SQL = "INSERT INTO user (first_name,last_name,email,password,role,is_enabled,social_id, confirm_code)VALUES(?,?,?,?,?,?,?,?)";
	public static final String SELECT_ALL_SQL = "SELECT * FROM user"; 
	public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user  WHERE email = ?";
	public static final String UPDATE_USER_SQL = "UPDATE user SET first_name=?,last_name=?,email=?,password=?,role=?,is_enabled=?,social_id=?, confirm_code=? WHERE id= ?";
	public static final String SELECT_USER_BY_SOCIAL_ID_SQL = "SELECT *FROM user WHERE social_id=?";
	public static final String SELECT_USER_BY_ID_SQL = "SELECT *FROM user WHERE id=?";
	public static final String SELECT_ADMINS_SQL = "SELECT * FROM user WHERE role='ROLE_ADMIN'";
	public static final String SELECT_USER_BY_CONFIRM_CODE_SQL ="SELECT * FROM user WHERE confirm_code =?";
	public static final String CALL_GET_USERS_BY_FLIGHT_ID = "CALL getUsersAndTicketsByflightId(?)";
	
	public static final String[] INSERT_USER_SQL_ORDER = { "first_name", "last_name",
			"email", "password","role","is_enabled", "social_id" ,"confirm_code"};
	public static final String[] SELECT_USER_BY_EMAIL_ORDER = {"email"}; 
	public static final String[] UPDATE_USER_SQL_ORDER = { "first_name", "last_name",
		"email", "password","role","is_enabled", "social_id","confirm_code", "id"};
	public static final String[] SELECT_USER_BY_SOCIAL_ID_SQL_ORDER ={"social_id"};
	public static final String[] SELECT_USER_BY_ID_SQL_ORDER ={"id"};
	public static final String[] SELECT_USER_BY_CONFIRM_CODE_SQL_ORDER ={"confirm_code"};
}
