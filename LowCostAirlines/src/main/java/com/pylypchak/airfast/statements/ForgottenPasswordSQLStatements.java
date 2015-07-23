package com.pylypchak.airfast.statements;

public class ForgottenPasswordSQLStatements {
public static final String CALL_INSERT_FORGOTTEN_PASSWORD_SQL ="CALL inserFrogottenPassword(?,?,?,?)";
public static final String UPDATE_FORGOTTEN_PASSWORD_SQL = "UPDATE forgotten_password SET user_id=?, code=?, is_enabled=?, creation_date=? WHERE id =?";
public static final String SELECT_FORGOTTEN_PASSWORD_BY_CODE_SQL = "SELECT * FROM forgotten_password WHERE code =?";

public static final String[] CALL_INSERT_FORGOTTEN_PASSWORD_SQL_ORDER ={"user_id","code","is_enabled", "creation_date" };
public static final String[] UPDATE_FORGOTTEN_PASSWORD_SQL_ORDER ={"user_id","code","is_enabled", "creation_date", "id" };
public static final String[] SELECT_FORGOTTEN_PASSWORD_BY_CODE_SQL_ORDER ={"code"};
}
