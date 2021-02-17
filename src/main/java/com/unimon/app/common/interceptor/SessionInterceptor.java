package com.unimon.app.common.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.unimon.app.component.SessionComp;
import com.unimon.app.vo.Account;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "SessionInterceptor")
public class SessionInterceptor implements HandlerInterceptor {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	
	private static SessionComp sessionComp = new SessionComp();
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response, 
							 Object handler) throws Exception {

		HttpSession session = (HttpSession)request.getSession();
		Account account = (Account)session.getAttribute("account");
		
		if(account == null)
			return true;

		if(!sessionComp.isVaild(session)) {
			session.invalidate();
			log.info("Session Invalidate");
		}
		else {
			session.setMaxInactiveInterval(60 * 60);
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
	
	
	
	
}
