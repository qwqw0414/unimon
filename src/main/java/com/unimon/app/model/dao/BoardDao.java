package com.unimon.app.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unimon.app.model.vo.Pagination;

@Repository
public class BoardDao {

	@Autowired
	private SqlSessionTemplate sst;

	public int insertBoard(Map<String, Object> param) throws RuntimeException{
		return sst.insert("board.insertBoard", param);
	}

	public Map<String, Object> selectOneBoardByNo(long boardNo) throws RuntimeException{
		return sst.selectOne("board.selectOneBoardByNo", boardNo);
	}

	public int deleteBoardByNo(long boardNo) throws RuntimeException {
		return sst.update("board.deleteBoardByNo", boardNo);
	}

	public List<Map<String, Object>> searchBoardByKeyword(Map<String, Object> param) throws RuntimeException {
		return sst.selectList("board.searchBoardByKeyword", param, ((Pagination)param.get("pagination")).getRowBounds());
	}

	public int countBoardByKeyword(Map<String, Object> param) throws RuntimeException {
		return sst.selectOne("board.countBoardByKeyword", param);
	}
	
}
