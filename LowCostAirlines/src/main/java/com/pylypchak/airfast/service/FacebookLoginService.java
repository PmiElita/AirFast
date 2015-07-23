package com.pylypchak.airfast.service;

import java.sql.SQLException;

import com.pylypchak.airfast.dao.FacebookLoginDAO;
import com.pylypchak.airfast.model.User;

public class FacebookLoginService {
	private FacebookLoginDAO facebookLoginDao;

	public FacebookLoginService() {
		facebookLoginDao = new FacebookLoginDAO();
	}

	public void getAccessToken(String code) {
		facebookLoginDao.getAccessToken(code);
	}

	public boolean isRegistrated() throws SQLException,
			ReflectiveOperationException {
		return facebookLoginDao.isRegistrated();
	}

	public User getUserFromSocilanetwork() {
		return facebookLoginDao.getUserFromSocNet();
	}

	public User getUserForLogin() throws SQLException,
			ReflectiveOperationException {
		return facebookLoginDao.getUserForLogIn();
	}
}
