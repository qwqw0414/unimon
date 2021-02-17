package com.unimon.app.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/api/board/*")
public class BoardFilter implements Filter {
	
	private static final String REG_TITLE = "";
	
	private static final String REG_CONTENTS = "";
	
	/**
	 * Board 관련 파라미터 유효성 검사 필터
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		if(title != null && title.length() == 0) {
			log.error("Invalid title");
		}
		
		if(contents != null && contents.length() == 0) {
			log.error("Invalid contents");
		}
		
	}

}
