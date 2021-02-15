package com.unimon.app.model.service;

import java.util.List;
import java.util.Map;

public interface PokeService {

	int countPokeByKeword(Map<String, Object> param);

	List<Map<String, Object>> searchPoke(Map<String, Object> param);

}
