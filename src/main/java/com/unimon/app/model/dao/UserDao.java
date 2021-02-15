package com.unimon.app.model.dao;

import java.util.List;

import com.unimon.app.model.vo.Account;
import com.unimon.app.model.vo.Role.UserRole;

public interface UserDao {

	/**
	 * 해당 아이디 수 조회
	 * @param userId
	 * @return
	 */
	int countAccountById(String userId);

	/**
	 * 유저 추가
	 * @param user
	 * @return
	 */
	int insertUser(Account user);

	/**
	 * 권한 추가
	 * @param user
	 * @return
	 */
	int insertAuthUser(Account user);

	/**
	 * 아이디를 이용해 유저 조회
	 * @param userId
	 * @return 계정 정보 객체
	 */
	Account selectOneUserById(String userId);

	/**
	 * 유저번호를 이용해 보유한 권한 전부 조회
	 * @param userNo
	 * @return 권한 리스트
	 */
	List<UserRole> selectAllAuthByNo(long userNo);

}
