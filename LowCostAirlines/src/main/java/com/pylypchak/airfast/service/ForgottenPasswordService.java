package com.pylypchak.airfast.service;

import java.sql.SQLException;

import com.pylypchak.airfast.dao.ForgottenPasswordDAO;
import com.pylypchak.airfast.model.ForgottenPassword;

public class ForgottenPasswordService {
	private ForgottenPasswordDAO forgottenPasswordDAO;

	public ForgottenPasswordService() {
		forgottenPasswordDAO = new ForgottenPasswordDAO();
	}

	public ForgottenPassword saveForgottenPassword(ForgottenPassword forgottenPassword)
			throws SQLException, ReflectiveOperationException {
		return forgottenPasswordDAO.saveForgottenPassword(forgottenPassword);
	}

	public void updateFrogottenPassword(ForgottenPassword forgottenPassword)
			throws ReflectiveOperationException, SQLException {
		forgottenPasswordDAO.updateFrogottenPassword(forgottenPassword);
	}
	
	public ForgottenPassword getForgottenPasswordByCode(String code)
			throws ReflectiveOperationException, SQLException {
		return forgottenPasswordDAO.getForgottenPasswordByCode(code);
	}

}
