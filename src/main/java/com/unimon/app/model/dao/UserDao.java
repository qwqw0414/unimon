package com.unimon.app.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unimon.app.model.vo.Account;
import com.unimon.app.model.vo.UserRole;

@Repository
public class UserDao {

	@Autowired
	private SqlSessionTemplate sst;

	public int insertUser(Account user) throws RuntimeException{
		return sst.insert("user.insertUser", user);
	}

	public Account selectOneUserById(String userId) throws RuntimeException {
		return sst.selectOne("user.selectOneUserById", userId);
	}

	public int countAccountById(String userId) throws RuntimeException {
		return sst.selectOne("user.countAccountById", userId);
	}

	public List<UserRole> selectAllAuthByNo(long userNo) {
		return sst.selectList("user.selectAllAuthByNo", userNo);
	}

	public int insertAuthUser(Account user) {
		return sst.insert("user.insertAuthUser", user);
	}
	
}
