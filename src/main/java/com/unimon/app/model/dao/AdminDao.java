package com.unimon.app.model.dao;

import java.util.List;
import java.util.Map;

public interface AdminDao {

	int countRole(Map<String, Object> param);

	int insertAuth(Map<String, Object> param);

	int deleteAuth(Map<String, Object> param);

	List<Map<String, Object>> searchUserByKeyword(Map<String, Object> param);

	List<Map<String, Object>> selectAllAuthByNo(long userNo);

	int deleteUserByNo(long userNo);

}
