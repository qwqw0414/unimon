package com.unimon.app.model.dao;

import java.util.List;
import java.util.Map;

public interface PokeDao {

	int countPokeByKeword(Map<String, Object> param);

	List<Map<String, Object>> searchPokeByKeyword(Map<String, Object> param);

}
