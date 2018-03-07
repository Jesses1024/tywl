package com.puxintech.tywl.controller.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.puxintech.tywl.model.exception.TywlException;
import com.puxintech.tywl.util.ProfileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yanhai
 */
@Slf4j
@ControllerAdvice(basePackages = "com.puxintech.tywl.controller", annotations = RestController.class)
public class RestControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public @ResponseBody Map<String, Object> handleException(Exception exception, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		int status = 500;
		String message = exception.getMessage() == null ? "unknow" : exception.getMessage();

		if (exception instanceof TywlException) {
			status = ((TywlException) exception).getStatusCode();
		} else if (message != null && message.contains("duplicate key")) {
			status = 400;
		}

		result.put("status", status);
		result.put("message", exception.getMessage());
		result.put("timestamp", System.currentTimeMillis());
		if (ProfileUtils.isDevelopment()) {
			result.put("stack", ExceptionUtils.getStackTrace(exception));
		}

		response.setStatus(status);

		log.error(message, exception.getCause());

		return result;
	}

}
