package com.unimon.app.common.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/api/user/*")
public class AccountFilter implements Filter {
	
//	영문 숫자 6이상 20이하
	private static final String REG_USER_ID = "ll[a-z]+[a-z0-9]{5,19}$";
	
//	영어 한글 2이상 8이하
	private static final String REG_USER_NAME = "^[a-z가-힣]{2,8}$";
	
//	최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함
	private static final String REG_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
//		유효성 검증 파라미터 
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
//		유효성 검증
		if(userId != null && !Pattern.matches(REG_USER_ID, userId)) {
			log.error("Invalid UserId !!");
			return;
		}
		
		if(userName != null && !Pattern.matches(REG_USER_NAME, userName)) {
			log.error("Invalid UserName !!");
			return;
		}
		
		if(password != null && !Pattern.matches(REG_PASSWORD, password)) {
			log.error("Invalid Password !!");
			return;
		}
		
		chain.doFilter(request, response);
		
	}

}
