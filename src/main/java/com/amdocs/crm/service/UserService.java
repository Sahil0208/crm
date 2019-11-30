package com.amdocs.crm.service;

import java.util.List;
import java.util.Map;

import com.amdocs.crm.beans.LoginBean;
import com.amdocs.crm.beans.RegistrationBean;
import com.amdocs.crm.model.User;

public interface UserService {

	public User save(RegistrationBean registrationBean);

	public User findByUserName(String userName);

	public boolean isUserExists(String userName);

	public List<User> getAllUsers();

	public User getUser(long userId);

	public boolean deleteUser(long userId);

	public Map<String, Object> login(LoginBean loginBean);
}
