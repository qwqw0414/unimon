package com.unimon.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unimon.app.common.exception.AppException;
import com.unimon.app.dao.AdminDao;
import com.unimon.app.dao.AdminDaoImpl;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, Throwable.class })
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public void grantRole(Map<String, Object> param) throws RuntimeException {

		if (adminDao.countRole(param) != 0)
			throw new AppException("Duplicate Role");
		
		if(adminDao.insertAuth(param) == 0)
			throw new AppException("Failed Insert Auth");

	}

	@Override
	public void revokeRole(Map<String, Object> param) throws RuntimeException {
		
		if (adminDao.deleteAuth(param) == 0)
			throw new AppException("Failed Delete Auth");
	}

	@Override
	public List<Map<String, Object>> searchAccount(Map<String, Object> param) throws RuntimeException {
		return adminDao.searchUserByKeyword(param);
	}

	@Override
	public List<Map<String, Object>> searchUserAuth(long userNo) throws RuntimeException {
		return adminDao.selectAllAuthByNo(userNo);
	}

	@Override
	public void deleteUser(long userNo) throws RuntimeException{
		if (adminDao.deleteUserByNo(userNo) == 0)
			throw new AppException("Failed Delete user");
	}

}
