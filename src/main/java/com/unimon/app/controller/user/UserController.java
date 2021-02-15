package com.unimon.app.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypageView() {
		return "user/mypage";
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
		return "common/main";
	}
}
