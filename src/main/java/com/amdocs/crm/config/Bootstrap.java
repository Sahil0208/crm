package com.amdocs.crm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.amdocs.crm.model.User;
import com.amdocs.crm.model.enums.Role;
import com.amdocs.crm.repository.UserRepository;

/**
 * The Class Bootstrap.
 */
@Component
public class Bootstrap implements ApplicationListener<ApplicationReadyEvent> {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.
	 * springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		saveSuperAdmin();
		saveAdmin();
	}

	/**
	 * Save super admin.
	 */
	private void saveSuperAdmin() {
		logger.info("Super admin creation is in progress.........");
		if (userRepository.findByUserName("superadmin") == null) {
			User user = new User();
			user.setUserName("superadmin");
			user.setPassword("admin");
			user.setRole(Role.ROLE_SUPER_ADMIN);
			user.setRoleName(Role.ROLE_SUPER_ADMIN.getName());
			user.setEmail("superadmin@gmail.com");
			user.setFullName("Super Admin");
			user.setContactNumber("123456789");
			userRepository.save(user);
			logger.info("Super admin created.");
		} else {
			logger.info("Super Admin already exists.");
		}
	}

	/**
	 * Save admin.
	 */
	private void saveAdmin() {
		logger.info("Admin creation is in progress.........");
		if (userRepository.findByUserName("admin") == null) {
			User user = new User();
			user.setUserName("admin");
			user.setPassword("admin");
			user.setRole(Role.ROLE_ADMIN);
			user.setRoleName(Role.ROLE_ADMIN.getName());
			user.setEmail("admin@gmail.com");
			user.setFullName("Admin");
			user.setContactNumber("987456321");
			userRepository.save(user);
			logger.info("Admin created.");
		} else {
			logger.info("Admin already exists.");
		}
	}
}
