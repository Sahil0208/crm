package com.amdocs.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.amdocs.crm.beans.LoginBean;
import com.amdocs.crm.beans.RegistrationBean;
import com.amdocs.crm.constants.CrmConstans;
import com.amdocs.crm.constants.ResponseConstants;
import com.amdocs.crm.model.Address;
import com.amdocs.crm.model.User;
import com.amdocs.crm.model.enums.Role;
import com.amdocs.crm.repository.AddressRepository;
import com.amdocs.crm.repository.UserRepository;

/**
 * The Class ImplUserService.
 */
@Service
public class ImplUserService implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public User save(RegistrationBean registrationBean) {
		User user = new User();
		user.setFullName(registrationBean.getFullName());
		user.setEmail(registrationBean.getEmail());
		user.setContactNumber(registrationBean.getContactNumber());
		user.setUserName(registrationBean.getUserName());
		user.setPassword(registrationBean.getPassword());
		user.setRole(Role.ROLE_USER);
		user.setRoleName(Role.ROLE_USER.getName());
		user = userRepository.save(user);
		Address userAddress = registrationBean.getAddress();
		userAddress.setUserId(user.getUserId());
		addressRepository.save(userAddress);
		return user;
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public boolean isUserExists(String userName) {
		return findByUserName(userName) != null ? true : false;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll(new Sort(Sort.Direction.DESC, "creationDate"));
	}

	@Override
	public User getUser(long userId) {
		Optional<User> user = userRepository.findById(userId);
		return user != null && user.isPresent() ? user.get() : null;
	}

	@Override
	public boolean deleteUser(long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user != null && user.isPresent()) {
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> login(LoginBean loginBean) {
		Map<String, Object> loginResponse = new HashMap<>();
		User user = findByUserName(loginBean.getUserName());
		if (user != null && user.getPassword().equals(loginBean.getPassword())) {
			if (user.isActive()) {
				loginResponse.put(ResponseConstants.IS_SUCCESS, true);
				loginResponse.put("userId", user.getUserId());
				loginResponse.put("role", user.getRole());
			} else {
				loginResponse.put(ResponseConstants.IS_SUCCESS, false);
				loginResponse.put("errorMessage", CrmConstans.YOU_ARE_BLOCKED_BY_ADMIN);
			}
		} else {
			loginResponse.put(ResponseConstants.IS_SUCCESS, false);
			loginResponse.put("errorMessage", CrmConstans.INVALID_USERNAME_OR_PASSWORD);
		}
		return loginResponse;
	}

}
