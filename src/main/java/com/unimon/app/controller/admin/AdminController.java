package com.unimon.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unimon.app.model.vo.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	/**
	 * 관리자 테스트 페이지 매핑
	 */
	@Role("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String adminView() throws Exception {
		return "admin/admin";
	}

	/**
	 * 계정 관리 페이지 매핑
	 */
	@Role("ROLE_ADMIN")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userListView() throws Exception {
		return "admin/userList";
	}

}
