package com.amdocs.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amdocs.crm.model.User;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Find by email.
	 *
	 * @param userName the user name
	 * @return the user
	 */
	public User findByUserName(String userName);
}
