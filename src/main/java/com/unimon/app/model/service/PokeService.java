package com.unimon.app.model.service;

import java.util.List;
import java.util.Map;

public interface PokeService {

	/**
	 * 키워드에 매칭된 컨텐츠 수 조회
	 * @param param
	 * @return
	 */
	int countPokeByKeword(Map<String, Object> param);

	/**
	 * 키워드 매칭 조회
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> searchPoke(Map<String, Object> param);

}
