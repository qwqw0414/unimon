package com.unimon.app.controller;

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
import com.unimon.app.component.SessionComp;
import com.unimon.app.service.UserService;
import com.unimon.app.service.UserServiceImpl;
import com.unimon.app.vo.Account;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/user", produces = "application/json; charset=utf8")
public class UserRestController {

	private Gson gson = new Gson();
	
	@Autowired
	private SessionComp sessionComp;
	
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
	
	/**
	 * 회원 가입
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 로그인
	 * @return
	 * @throws Exception
	 */
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

//		세션 데이터 저장
		session.setAttribute("account", user);
		sessionComp.set(session);
		
		log.info("<< Login : [{}] >>", user.getUserName());

		return HttpStatus.OK;
	}
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/signout")
	public HttpStatus signout(HttpSession session) throws Exception {
		
		sessionComp.remove(session);
		
		session.removeAttribute("account");
		session.invalidate();
		
		return HttpStatus.OK;
	}
	
//	###############################################################################
//										PUT
//	###############################################################################

	
//	###############################################################################
//										DELETE
//	###############################################################################
	
	

	

}
