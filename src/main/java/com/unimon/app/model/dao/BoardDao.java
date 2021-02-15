package com.unimon.app.model.dao;

import java.util.List;
import java.util.Map;

public interface BoardDao {

	int insertBoard(Map<String, Object> param);

	Map<String, Object> selectOneBoardByNo(long boardNo);

	int deleteBoardByNo(long boardNo);

	List<Map<String, Object>> searchBoardByKeyword(Map<String, Object> param);

	int countBoardByKeyword(Map<String, Object> param);

}
