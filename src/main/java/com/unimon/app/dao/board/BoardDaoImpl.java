package com.unimon.app.dao.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unimon.app.vo.Pagination;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	private SqlSessionTemplate sst;
	
	@Override
	public int insertBoard(Map<String, Object> param) throws RuntimeException{
		return sst.insert("board.insertBoard", param);
	}

	@Override
	public Map<String, Object> selectOneBoardByNo(long boardNo) throws RuntimeException{
		return sst.selectOne("board.selectOneBoardByNo", boardNo);
	}

	@Override
	public int deleteBoardByNo(long boardNo) throws RuntimeException {
		return sst.update("board.deleteBoardByNo", boardNo);
	}

	@Override
	public List<Map<String, Object>> searchBoardByKeyword(Map<String, Object> param) throws RuntimeException {
		return sst.selectList("board.searchBoardByKeyword", param, ((Pagination)param.get("pagination")).getRowBounds());
	}

	@Override
	public int countBoardByKeyword(Map<String, Object> param) throws RuntimeException {
		return sst.selectOne("board.countBoardByKeyword", param);
	}
	
}
