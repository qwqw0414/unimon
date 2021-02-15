package com.unimon.app.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.unimon.app.common.exception.ForbiddenException;
import com.unimon.app.common.exception.UnauthorizedException;
import com.unimon.app.model.vo.Account;
import com.unimon.app.model.vo.Role;
import com.unimon.app.model.vo.Role.UserRole;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

	private static final String ADMIN_URL = "/admin";

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
		
//		어드민 권한 보유 여부
//		if (isRolePath(url, ADMIN_URL) && !account.hasRole(UserRole.ROLE_ADMIN))
//			throw new ForbiddenException("Forbidden : Admin");

//		유저 권한 보유 여부
//		if (!account.hasRole(UserRole.ROLE_USER))
//			throw new ForbiddenException("Forbidden : User");

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

//	URL 확인
	private boolean isRolePath(String url, String role) {
		try {

			if (url.contains("/api"))
				url = url.substring(4, 4 + role.length());
			else
				url = url.substring(0, role.length());
			return role.equals(url);
			
		} catch (Exception e) {
			return false;
		}
	}

}
