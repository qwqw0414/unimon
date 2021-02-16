package com.unimon.app.dao.admin;

import java.util.List;
import java.util.Map;

public interface AdminDao {

	/**
	 * 유저가 해당 권한에 보유 여부를 조회
	 * @return	권한 보유 수
	 */
	int countRole(Map<String, Object> param);

	/**
	 * 권한 추가
	 * @param param
	 * @return
	 */
	int insertAuth(Map<String, Object> param);

	/**
	 * 권한 삭제
	 * @param param
	 * @return
	 */
	int deleteAuth(Map<String, Object> param);

	/**
	 * 키워드 매칭된 유저 리스트 조회
	 * @param param
	 * @return 매칭된 유저 리스트
	 */
	List<Map<String, Object>> searchUserByKeyword(Map<String, Object> param);

	/**
	 * 유저가 보유한 권한 전부 조회
	 * @param userNo
	 * @return 보유한 권한 리스트
	 */
	List<Map<String, Object>> selectAllAuthByNo(long userNo);

	/**
	 * 번호를 이용해 해당 유저 비활성화
	 * @param userNo
	 * @return
	 */
	int deleteUserByNo(long userNo);

}
