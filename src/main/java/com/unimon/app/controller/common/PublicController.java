package com.unimon.app.controller.common;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.unimon.app.model.vo.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PublicController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeView() throws Exception {
		return "main/main";
	}

	@RequestMapping(value = "/boot")
	public String themView() throws Exception {
		return "boot";
	}
	
	@RequestMapping(value = "/test/error", method = RequestMethod.GET)
	public String error() throws Exception {
		throw new Exception();
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupView() throws Exception {
		return "user/signup";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signinView() throws Exception {
		return "user/signin";
	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpSession session) throws Exception {
		session.removeAttribute("account");
		session.invalidate();
		return "main/main";
	}
}
