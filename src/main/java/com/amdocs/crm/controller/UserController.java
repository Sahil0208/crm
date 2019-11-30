package com.amdocs.crm.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.crm.beans.LoginBean;
import com.amdocs.crm.beans.RegistrationBean;
import com.amdocs.crm.constants.CrmConstans;
import com.amdocs.crm.model.User;
import com.amdocs.crm.service.UserService;
import com.amdocs.crm.utils.ResponseHandler;

@RestController
@RequestMapping("/crm")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Object> register(@Valid @RequestBody RegistrationBean registrationBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = ResponseHandler.errorFields(bindingResult.getFieldErrors());
			return ResponseHandler.doErrorResponse(errors, HttpStatus.OK);
		}
		logger.info("User registration is in progress......");
		if (userService.isUserExists(registrationBean.getUserName())) {
			return ResponseHandler.doErrorResponse(CrmConstans.USER_NAME_ALREADY_TAKEN, HttpStatus.OK);
		}
		User user = userService.save(registrationBean);
		if (user != null && user.getUserId() > 0) {
			logger.info("User registration is completed.");
			return ResponseHandler.doSuccessResponse(CrmConstans.USER_REGISTRATION_IS_SUCCESSFULL, HttpStatus.OK);
		}
		logger.info("User registration is failed.");
		return ResponseHandler.doErrorResponse(CrmConstans.SOMETHING_WENT_WRONG, HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<Object> users() {
		logger.info("Fetching users......");
		return ResponseHandler.doSuccessResponse(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Object> getUser(@PathVariable long userId) {
		logger.info("Fetching user......");
		if (userId < 0) {
			return ResponseHandler.doErrorResponse(CrmConstans.INVALID_USER_ID, HttpStatus.OK);
		}
		User user = userService.getUser(userId);
		if (user == null) {
			logger.info(CrmConstans.USER_NOT_FOUND);
			return ResponseHandler.doErrorResponse(CrmConstans.USER_NOT_FOUND, HttpStatus.OK);
		}
		logger.info("User successfully loaded");
		return ResponseHandler.doSuccessResponse(user, HttpStatus.OK);
	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable long userId) {
		logger.info("Deleting user......");
		if (userId < 0) {
			return ResponseHandler.doErrorResponse(CrmConstans.INVALID_USER_ID, HttpStatus.OK);
		}
		boolean isUserDeletd = userService.deleteUser(userId);
		if (isUserDeletd) {
			logger.info("Deleting user......");
			return ResponseHandler.doSuccessResponse(CrmConstans.USER_SUCCESSFULLY_DELETD, HttpStatus.OK);
		}
		logger.info("Failed to delete user");
		return ResponseHandler.doSuccessResponse(CrmConstans.USER_NOT_FOUND_OR_ALREADY_DELETD, HttpStatus.OK);
	}

	@PostMapping("/login.do")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginBean loginBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = ResponseHandler.errorFields(bindingResult.getFieldErrors());
			return ResponseHandler.doErrorResponse(errors, HttpStatus.OK);
		}
		return ResponseHandler.doSuccessResponse(userService.login(loginBean), HttpStatus.OK);
	}
}
