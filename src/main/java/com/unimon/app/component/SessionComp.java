package com.unimon.app.component;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "SessionComp")
public class SessionComp {

	private static final Map<String, HttpSession> sessionList = new HashMap<>();

	public void set(HttpSession session) {

		sessionList.put(session.getId(), session);
		log.debug("Set Session Id : {}", session.getId());
		log.debug("Session List : {}", sessionList);
	}

	public HttpSession get(HttpSession session) {

		HttpSession result = sessionList.get(session.getId());
		log.debug("Get Session Id : {}", session.getId());
		log.debug("Result Session : {}", result);

		return result;
	}

	public boolean remove(HttpSession session) {
		return sessionList.remove(session.getId()) != null;
	}

	public boolean isVaild(HttpSession session) throws Exception {
		return sessionList.containsKey(session.getId());
	}
	
}
