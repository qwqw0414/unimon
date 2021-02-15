package com.unimon.app.model.service;

import com.unimon.app.model.vo.Account;

public interface UserService {

	/**
	 * 로그인 서비스
	 */
	void signup(Account param);

	/**
	 * 아이디를 이용해 해당 계정 정보 조회
	 * @return	계정 정보 객체
	 */
	Account getAccountById(String userId);

}
