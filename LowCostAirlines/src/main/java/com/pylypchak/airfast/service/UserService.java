package com.pylypchak.airfast.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pylypchak.airfast.dao.UserDAO;
import com.pylypchak.airfast.model.Ticket;
import com.pylypchak.airfast.model.User;

public class UserService {
	private UserDAO userDao;

	public UserService() {
		userDao = new UserDAO();
	}

	public void saveUser(User user) throws SQLException,
			ReflectiveOperationException {
		userDao.saveUser(user);

	}

	public List<User> getAllUsers() throws SQLException,
			ReflectiveOperationException {
		return userDao.getAllUsers();
	}

	public User getUserByEmail(String email)
			throws ReflectiveOperationException, SQLException {
		return userDao.getUserByEmail(email);
	}

	public boolean isEmailFree(String email)
			throws ReflectiveOperationException, SQLException {
		return userDao.isEmailFree(email);
	}
	
    public User getUserById(Integer id) throws SQLException, ReflectiveOperationException{
    	return userDao.getUserById(id);
    }
    
    public void updateUser(User user) throws ReflectiveOperationException, SQLException {
    	userDao.updateUser(user);
    }
    
	public List<User> getAllAdmins() throws SQLException,
	ReflectiveOperationException {
		return userDao.getAllAdmins();
	}
	
	public User getUserByConfirmCode(String confirmCode)
			throws ReflectiveOperationException, SQLException {
		return userDao.getUserByConfirmCode(confirmCode);
	}
	
	public Map<Ticket,User> getUsersAndTicketsByflightId(Integer flightId) throws SQLException{
		return userDao.getUsersAndTicketsByflightId(flightId);
	}
}
