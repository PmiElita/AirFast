package com.pylypchak.airfast.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pylypchak.airfast.model.ForgottenPassword;
import com.pylypchak.airfast.statements.ForgottenPasswordSQLStatements;
import com.pylypchak.airfast.util.ConnectionManager;
import com.pylypchak.airfast.util.Transformer;

public class ForgottenPasswordDAO {

	public ForgottenPassword saveForgottenPassword(
			ForgottenPassword forgottenPassword) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		CallableStatement statement = connection
				.prepareCall(
						ForgottenPasswordSQLStatements.CALL_INSERT_FORGOTTEN_PASSWORD_SQL);
		Transformer
				.valueIntoPreparedStatement(
						statement,
						forgottenPassword,
						ForgottenPasswordSQLStatements.CALL_INSERT_FORGOTTEN_PASSWORD_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoObject(resultSet,
				ForgottenPassword.class);
		}
	}

	public void updateFrogottenPassword(ForgottenPassword forgottenPassword)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
	
		PreparedStatement statement = connection
				.prepareStatement(
						ForgottenPasswordSQLStatements.UPDATE_FORGOTTEN_PASSWORD_SQL);
		Transformer
				.valueIntoPreparedStatement(
						statement,
						forgottenPassword,
						ForgottenPasswordSQLStatements.UPDATE_FORGOTTEN_PASSWORD_SQL_ORDER);
		statement.executeUpdate();
		}
	}

	public ForgottenPassword getForgottenPasswordByCode(String code)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement =connection
				.prepareStatement(
						ForgottenPasswordSQLStatements.SELECT_FORGOTTEN_PASSWORD_BY_CODE_SQL);
		ForgottenPassword forgottenPassword = new ForgottenPassword();
		forgottenPassword.setCode(code);
		Transformer
				.valueIntoPreparedStatement(
						statement,
						forgottenPassword,
						ForgottenPasswordSQLStatements.SELECT_FORGOTTEN_PASSWORD_BY_CODE_SQL_ORDER);
       ResultSet resultSet = statement.executeQuery();
       return Transformer.transformResultSetIntoObject(resultSet, ForgottenPassword.class);
		}
	}
}
