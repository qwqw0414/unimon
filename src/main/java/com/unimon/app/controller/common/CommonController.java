package com.unimon.app.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommonController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeView() throws Exception {
		return "common/main";
	}

	@RequestMapping(value = "/boot")
	public String themView() throws Exception {
		return "boot";
	}

	@RequestMapping(value = "/test/error", method = RequestMethod.GET)
	public String error() throws Exception {
		throw new Exception();
	}

}
