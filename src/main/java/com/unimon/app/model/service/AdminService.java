package com.unimon.app.model.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	/**
	 * 계정 조회
	 * @return	게정 객체
	 */
	List<Map<String, Object>> searchAccount(Map<String, Object> param);

	/**
	 * 유저 권한 부여
	 */
	void grantRole(Map<String, Object> param);

	/**
	 * 해당 유저번호의 보유 권한을 조회
	 * @return	보유한 권한 객체
	 */
	List<Map<String, Object>> searchUserAuth(long userNo);

	/**
	 * 유저번호의 계정을 비활성화
	 */
	void deleteUser(long userNo);

	/**
	 * 유저권한 제거 
	 */
	void revokeRole(Map<String, Object> param);

}
