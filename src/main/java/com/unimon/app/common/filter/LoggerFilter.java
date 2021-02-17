package com.unimon.app.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * 요청 로깅을 위한 필터
 * @author jang
 *
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoggerFilter implements Filter {

	private final static String[] EXCLUDE_PATHS = { "assets" };
	private Gson gson = new Gson();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String url = httpRequest.getRequestURI();
		String method = httpRequest.getMethod();
		
		if (isInclude(url)) {
			// Request Logging
			log.info("=================== >> {} START {} >> ===================", method, url);
			log.debug("ContentType : {}", gson.toJson(request.getContentType()));
			log.debug("ParameterMap : {}", gson.toJson(request.getParameterMap()));
			
			chain.doFilter(request, response);

			log.debug("=================== << {} END {} << ===================\n", method, url);
		} else {
			chain.doFilter(request, response);
		}
	}

//	Exclude URL 유효성 검사
	public boolean isInclude(String url) {
		for (String i : EXCLUDE_PATHS) {
			if (url.contains(i))
				return false;
		}
		return true;
	}

}
