package com.unimon.app.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unimon.app.common.exception.AppException;
import com.unimon.app.model.dao.BoardDao;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, Throwable.class })
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public void boardWrite(Map<String, Object> param) throws RuntimeException {
		
		param.put("attachment_cnt", 0);
		
		if(boardDao.insertBoard(param) == 0)
			throw new AppException("Failed Board Insert");
		
	}

	public Map<String, Object> readBoard(long boardNo) throws RuntimeException {
		return boardDao.selectOneBoardByNo(boardNo);
	}

	public void removeBoard(long boardNo) throws RuntimeException{
		if(boardDao.deleteBoardByNo(boardNo) == 0)
			throw new AppException("Failed Board Delete");
	}

	public List<Map<String, Object>> searchBoard(Map<String, Object> param) throws RuntimeException {
		return boardDao.searchBoardByKeyword(param);
	}

	public int getTotalContents(Map<String, Object> param) throws RuntimeException {
		return boardDao.countBoardByKeyword(param);
	}
	
	
}
