package com.unimon.app.dao;

import java.util.List;
import java.util.Map;

public interface BoardDao {

	/**
	 * 게시글 추가
	 * @param param
	 * @return
	 */
	int insertBoard(Map<String, Object> param);

	/**
	 * 게시글 조회
	 * @param boardNo
	 * @return
	 */
	Map<String, Object> selectOneBoardByNo(long boardNo);

	/**
	 * 게시글 제거
	 * @param boardNo
	 * @return
	 */
	int deleteBoardByNo(long boardNo);

	/**
	 * 키워드 매칭된 게시글 전부 조회
	 * @param param
	 * @return 매칭된 게시글 리스트
	 */
	List<Map<String, Object>> searchBoardByKeyword(Map<String, Object> param);

	/**
	 * 키워드 매칭된 게시글 수 조회
	 * @param param
	 * @return 매칭된 게시글 수
	 */
	int countBoardByKeyword(Map<String, Object> param);

}
