package com.unimon.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unimon.app.component.SessionComp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private SessionComp sessionComp;
	
	/**
	 * 마이 페이지 매핑
	 */
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypageView() {
		return "user/mypage";
	}
	
	/**
	 * 회원 가입 페이지 매핑
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupView() throws Exception {
		return "user/signup";
	}

	/**
	 * 로그인 페이지 매핑
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signinView() throws Exception {
		return "user/signin";
	}
}
