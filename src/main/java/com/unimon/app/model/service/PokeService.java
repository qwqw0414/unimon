package com.unimon.app.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unimon.app.model.dao.PokeDao;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, Throwable.class })
public class PokeService {

	@Autowired
	private PokeDao pokeDao;

	public List<Map<String, Object>> searchPoke(Map<String, Object> param) throws RuntimeException {
		return pokeDao.searchPokeByKeyword(param);
	}

	
}
