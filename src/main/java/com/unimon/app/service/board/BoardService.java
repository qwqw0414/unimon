package com.unimon.app.service.board;

import java.util.Map;

public interface BoardService {

	/**
	 * 게시글 번호를 이용해 조회
	 * @return	게시글 객체
	 */
	Map<String, Object> readBoard(long boardNo);

	/**
	 * 게시글의 총 컨텐츠 수 조회
	 * @return	총 컨텐츠 수
	 */
	int getTotalContents(Map<String, Object> param);

	/**
	 * 게시글 서칭 
	 * @return	키워드 매칭된 개시글 리스트
	 */
	Object searchBoard(Map<String, Object> param);

	/**
	 * 게시글 작성 
	 */
	void boardWrite(Map<String, Object> param);

	/**
	 * 게시글 삭제
	 */
	void removeBoard(long boardNo);

}
