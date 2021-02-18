package com.unimon.app.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unimon.app.vo.Account;
import com.unimon.app.vo.Role.UserRole;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public int insertUser(Account user) throws RuntimeException{
		return sst.insert("user.insertUser", user);
	}

	@Override
	public Account selectOneUserById(String userId) throws RuntimeException {
		return sst.selectOne("user.selectOneUserById", userId);
	}

	@Override
	public int countAccountById(String userId) throws RuntimeException {
		return sst.selectOne("user.countAccountById", userId);
	}

	@Override
	public List<UserRole> selectAllAuthByNo(long userNo) {
		return sst.selectList("user.selectAllAuthByNo", userNo);
	}

	@Override
	public int insertAuthUser(Account user) {
		return sst.insert("user.insertAuthUser", user);
	}
	
}
