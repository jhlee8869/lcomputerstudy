package com.lcomputer.testmvc.service;

import java.util.ArrayList;
import com.lcomputer.testmvc.dao.UserDAO;
import com.lcomputer.testmvc.vo.Pagination;
import com.lcomputer.testmvc.vo.User;

public class UserService {
	
	private static UserService service = null;
	private static UserDAO dao = null;
    
	private UserService() {
		
	}

	public static UserService getInstance() {
		if(service == null) {
			service = new UserService();
			dao = UserDAO.getInstance();
		}
		return service;
	}

	public ArrayList<User> getUsers() {
		return dao.getUsers();
	}
	public void insertUser(User user) {
		dao.insertUser(user);
	}
	
	public int getUsersCount() {
		return dao.getUsersCount();
	}
	public ArrayList<User> getUsers(Pagination pagination) {
		return dao.getUsers(pagination);
	}
	
	public User loginUser(String idx, String pw) {
		return dao.loginUser(idx,pw);
	}
	
	public User detailUser(User user) {
		return dao.detailUser(user);
	}
	
	public User editUser(User user) {
		return dao.editUser(user);
	}
	
	public void editprocessUser(User user) {
		dao.editprocessUser(user);
	}
	
	public void deleteUser(User user) {
		dao.deleteUser(user);
	}
	
	public void changeTypeUser(User user) {
		dao.deleteUser(user);
	}

}

