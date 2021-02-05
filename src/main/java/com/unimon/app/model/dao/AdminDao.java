package com.unimon.app.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unimon.app.model.vo.Pagination;

@Repository
public class AdminDao {

	@Autowired
	private SqlSessionTemplate sst;

	public int insertAuth(Map<String, Object> param) throws RuntimeException {
		return sst.insert("admin.insertAuth", param);
	}

	public int countRole(Map<String, Object> param) throws RuntimeException {
		return sst.selectOne("admin.countRole", param);
	}

	public int deleteAuth(Map<String, Object> param) throws RuntimeException {
		return sst.delete("admin.deleteAuth", param);
	}

	public List<Map<String, Object>> searchUserByKeyword(Map<String, Object> param) throws RuntimeException {
		return sst.selectList("admin.searchUserByKeyword", param, ((Pagination)param.get("page")).getRowBounds());
	}

	public List<Map<String, Object>> selectAllAuthByNo(long userNo) throws RuntimeException {
		return sst.selectList("admin.selectAllAuthByNo", userNo);
	}

	public int deleteUserByNo(long userNo) {
		return sst.update("admin.deleteUserByNo", userNo);
	}

}
