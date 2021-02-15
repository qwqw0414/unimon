package com.unimon.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unimon.app.common.exception.AppException;
import com.unimon.app.model.dao.UserDao;
import com.unimon.app.model.dao.UserDaoImpl;
import com.unimon.app.model.vo.Account;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, Throwable.class })
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	public void signup(Account user) throws RuntimeException {

//		아이디 중복 검사
		if (userDao.countAccountById(user.getUserId()) > 0)
			throw new AppException("Duplicate ID");
		
		if (userDao.insertUser(user) == 0)
			throw new AppException("Failed Insert User");
		
		if (userDao.insertAuthUser(user) == 0)
			throw new AppException("Failed Insert Auth");
		
	}

	public Account getAccountById(String userId) throws RuntimeException{
		
		Account account = userDao.selectOneUserById(userId);

		if(account != null)
			account.setRole(userDao.selectAllAuthByNo(account.getUserNo()));
			
		return account;
	}
	
}
