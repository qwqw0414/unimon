package com.unimon.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String adminView() throws Exception {
		return "admin/admin";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userListView() throws Exception {
		return "admin/userList";
	}

}
