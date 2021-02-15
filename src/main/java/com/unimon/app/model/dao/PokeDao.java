package com.unimon.app.model.dao;

import java.util.List;
import java.util.Map;

public interface PokeDao {

	/**
	 * 
	 * @param param
	 * @return
	 */
	int countPokeByKeword(Map<String, Object> param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> searchPokeByKeyword(Map<String, Object> param);

}
