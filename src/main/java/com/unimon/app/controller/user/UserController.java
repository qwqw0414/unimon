package com.unimon.app.controller.user;

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
	
	
}
