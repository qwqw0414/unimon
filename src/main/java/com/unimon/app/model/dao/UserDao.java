package com.unimon.app.model.dao;

import java.util.List;

import com.unimon.app.model.vo.Account;
import com.unimon.app.model.vo.Role.UserRole;

public interface UserDao {

	int countAccountById(String userId);

	int insertUser(Account user);

	int insertAuthUser(Account user);

	Account selectOneUserById(String userId);

	List<UserRole> selectAllAuthByNo(long userNo);

}
