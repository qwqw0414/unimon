package com.unimon.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.unimon.app.common.exception.AppException;
import com.unimon.app.common.exception.ForbiddenException;
import com.unimon.app.common.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionController {

	/**
	 * 커스텀 예외 핸들링
	 * @return HTTP 400
	 */
	@ExceptionHandler({ AppException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Exception message")
	public HttpStatus customError(final AppException e, HttpServletRequest request) {

		request.setAttribute("errorCode", "400");
		request.setAttribute("errorStatus", HttpStatus.BAD_REQUEST);
		log.error(e.getMessage());
		log.error("Context Path : {}", request.getContextPath());
		
		return HttpStatus.BAD_REQUEST;
	}

	/**
	 * BAD_REQUEST 예외 핸들링 
	 * @return HTTP 400
	 */
	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Exception message")
	public String badRequest(final RuntimeException e, HttpServletRequest request) {

		request.setAttribute("errorCode", "400");
		request.setAttribute("errorStatus", HttpStatus.BAD_REQUEST);
		log.error(e.getMessage());
		e.printStackTrace();

		return "error/error";
	}

	/**
	 * UNAUTHORIZED 예외 핸들링 
	 * @return HTTP 401
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Exception message")
	public String unauthorized(final UnauthorizedException e, HttpServletRequest request) {

		request.setAttribute("errorCode", "401");
		request.setAttribute("errorStatus", HttpStatus.UNAUTHORIZED);
		log.error(e.getMessage());

		return "error/error";
	}

	/**
	 * FORBIDDEN 예외 핸들링
	 * @return HTTP 403
	 */
	@ExceptionHandler({ ForbiddenException.class })
	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Exception message")
	public String forbidden(final ForbiddenException e, HttpServletRequest request) {

		request.setAttribute("errorCode", "403");
		request.setAttribute("errorStatus", HttpStatus.FORBIDDEN);
		log.error(e.getMessage());

		return "error/error";
	}

	/**
	 * INTERNAL_SERVER_ERROR 예외 핸들링
	 * @return HTTP 500
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Exception message")
	public String handleAll(final Exception e, HttpServletRequest request) {

		request.setAttribute("errorCode", "500");
		request.setAttribute("errorStatus", HttpStatus.INTERNAL_SERVER_ERROR);
		e.printStackTrace();
		return "error/error";
	}

//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<?> handleAll(final Exception e, HttpServletRequest request) throws Exception{
//    	
//		request.setAttribute("errorCode", "500");
//		request.setAttribute("errorStatus", HttpStatus.INTERNAL_SERVER_ERROR);
//		e.printStackTrace();
//    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
