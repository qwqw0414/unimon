package com.unimon.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommonController {

	/**
	 * 메인 페이지 매핑
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeView() throws Exception {
		return "common/main";
	}

	/**
	 * 부트스트랩 테스트 페이지 매핑
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boot")
	public String themView() throws Exception {
		return "boot";
	}

	/**
	 * 테스트 에러 페이지 매핑
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/test/error", method = RequestMethod.GET)
	public String error() throws Exception {
		throw new Exception();
	}

}
