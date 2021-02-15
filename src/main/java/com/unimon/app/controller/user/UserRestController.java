package com.unimon.app.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.unimon.app.common.exception.AppException;
import com.unimon.app.model.service.UserService;
import com.unimon.app.model.service.UserServiceImpl;
import com.unimon.app.model.vo.Account;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/user", produces = "application/json; charset=utf8")
public class UserRestController {

	private Gson gson = new Gson();
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
//	###############################################################################
//										GET
//	###############################################################################
	
	
//	###############################################################################
//										POST
//	###############################################################################
	
	@PostMapping(value = "/signup")
	public HttpStatus signup(@RequestParam("userId") String userId,
							 @RequestParam("userName") String userName,
							 @RequestParam("password") String password ) throws Exception {
//		파라미터 대입
		Account param = new Account();
		param.setUserId(userId);
		param.setUserName(userName);
		param.setPassword(passwordEncoder.encode(password));

		userService.signup(param);

		return HttpStatus.OK;
	}
	
	@PostMapping(value = "/signin")
	public HttpStatus signin(@RequestParam("userId") String userId,
						 	 @RequestParam("password") String password,
						 	 HttpSession session) throws Exception {
//		파라미터 대입
		Account param = new Account();
		param.setUserId(userId);
		param.setPassword(password);

		Account user = userService.getAccountById(param.getUserId());

		if (user == null)
			throw new AppException("Failed getAccount");

		if (!passwordEncoder.matches(param.getPassword(), user.getPassword()))
			throw new AppException("Invalid Password");

		session.setAttribute("account", user);

		log.info("<< Login : [{}] >>", user.getUserName());
		log.info(session.getId());

		return HttpStatus.OK;
	}
	
//	###############################################################################
//										PUT
//	###############################################################################

	
//	###############################################################################
//										DELETE
//	###############################################################################
	
	

	

}
