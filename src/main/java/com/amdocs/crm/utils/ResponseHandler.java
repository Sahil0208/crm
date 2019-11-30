package com.amdocs.crm.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import com.amdocs.crm.constants.ResponseConstants;

public class ResponseHandler {

	private static final Logger logger = LoggerFactory.getLogger(ResponseHandler.class);

	private static Map<String, Object> response = new HashMap<>();

	public static ResponseEntity<Object> doSuccessResponse(final Object data, final HttpStatus status) {
		logger.info("Preparing response......");
		response.clear();
		response.put(ResponseConstants.DATA, data);
		response.put(ResponseConstants.IS_SUCCESS, true);
		response.put(ResponseConstants.TIME_STAMP, new Date().getTime());
		logger.info("Response generation completed.");
		return new ResponseEntity<Object>(response, status);
	}

	public static ResponseEntity<Object> doErrorResponse(final String errorMessage, final HttpStatus status) {
		logger.info("Preparing response......");
		response.clear();
		response.put(ResponseConstants.ERROR_MESSAGE, errorMessage);
		response.put(ResponseConstants.IS_SUCCESS, false);
		response.put(ResponseConstants.TIME_STAMP, new Date().getTime());
		logger.info("Response generation completed.");
		return new ResponseEntity<Object>(response, status);
	}
	
	public static ResponseEntity<Object> doErrorResponse(final Object data, final HttpStatus status) {
		logger.info("Preparing response......");
		response.clear();
		response.put(ResponseConstants.DATA, data);
		response.put(ResponseConstants.IS_SUCCESS, false);
		response.put(ResponseConstants.TIME_STAMP, new Date().getTime());
		logger.info("Response generation completed.");
		return new ResponseEntity<Object>(response, status);
	}
	
	public static List<String> errorFields(List<FieldError> errors) {
		return errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
	}
}
