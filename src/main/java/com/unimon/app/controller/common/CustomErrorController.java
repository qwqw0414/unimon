package com.unimon.app.controller.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping(value = "/error")
	public String error(HttpServletRequest request) {
//    	
		String status = String.valueOf(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		request.setAttribute("errorCode", status);
		
		if("404".equals(status))
			request.setAttribute("errorStatus", "Page Not Found");
		
		log.error("Error Status Code : {}", status);

//		String code = (String) request.getAttribute("errorCode");
//		String status = (String) request.getAttribute("errorStatus");

		return "common/error";
	}

}
