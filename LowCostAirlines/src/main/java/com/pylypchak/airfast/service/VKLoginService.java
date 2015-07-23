package com.pylypchak.airfast.service;

import java.sql.SQLException;

import com.pylypchak.airfast.dao.VKLoginDAO;
import com.pylypchak.airfast.model.User;

public class VKLoginService {
	private VKLoginDAO vkLoginDao;

	public VKLoginService(){
		vkLoginDao= new VKLoginDAO();
	}
	
	public void getAccessToken(String code) {
		vkLoginDao.getAccessToken(code);
	}

	public boolean isRegistrated() throws SQLException,
			ReflectiveOperationException {
		return vkLoginDao.isRegistrated();
	}

	public User getUserFromSocilanetwork() {
		return vkLoginDao.getUserFromSocNet();
	}

	public User getUserForLogin() throws SQLException,
			ReflectiveOperationException {
		return vkLoginDao.getUserForLogIn();
	}
}
