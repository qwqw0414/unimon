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

	/**
	 * 로그아웃 처리
	 */
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpSession session) throws Exception {
		session.removeAttribute("account");
		session.invalidate();
		return "common/main";
	}
}
