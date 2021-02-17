package com.unimon.app.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.unimon.app.common.exception.ForbiddenException;
import com.unimon.app.common.exception.UnauthorizedException;
import com.unimon.app.component.SessionComp;
import com.unimon.app.vo.Account;
import com.unimon.app.vo.Role;
import com.unimon.app.vo.Role.UserRole;

import lombok.extern.slf4j.Slf4j;


/**
 * 해당 서비스에 사용 권한을 판단하기 위한 인터셉터
 * @author jang
 *
 */
@Slf4j
@Component(value = "AuthInterceptor")
public class AuthInterceptor implements HandlerInterceptor {

	private static final String ADMIN_URL = "/admin";

	@Autowired
	private SessionComp sessionComp;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		백도어
		if ("T".equals((String) request.getParameter("test")))
			return HandlerInterceptor.super.preHandle(request, response, handler);

//		어노테이션 체크
		if(handler instanceof HandlerMethod == false) {
			log.debug("Unmapping Method");
			return true;
		}
		
//		ROLE 어노테이션 체크
		Role methodRole = ((HandlerMethod) handler).getMethodAnnotation(Role.class);
		
		if(methodRole == null) {
			log.debug("methodRole : {}", methodRole);
			return true;
		}
		
		log.debug("Method Role : {}", methodRole.value());
		
//		세션 데이터 가져오기
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");

//		로그인 여부 
		log.debug("account : {}", account);
		if (account == null)
			throw new UnauthorizedException("Unauthorized");

//		권한 가져오기
		List<UserRole> role = account.getRole();

//		권한 보유 여부
		if (role == null || role.size() == 0)
			throw new ForbiddenException("Forbidden");

		log.debug("hasRole : {}", role);

//		SUDO 권한 여부
		if (account.hasRole(UserRole.ROLE_SUPER))
			return HandlerInterceptor.super.preHandle(request, response, handler);

//		권한 체크
		if(!account.hasRole(methodRole.value()))
			throw new ForbiddenException("Forbidden");

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
