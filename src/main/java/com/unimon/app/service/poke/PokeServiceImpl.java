package com.unimon.app.service.poke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unimon.app.common.exception.AppException;
import com.unimon.app.component.CashDataComp;
import com.unimon.app.dao.poke.PokeDao;
import com.unimon.app.dao.poke.PokeDaoImpl;
import com.unimon.app.vo.PickPoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, Throwable.class })
public class PokeServiceImpl implements PokeService {

	@Autowired
	private CashDataComp cdc;
	
	private static Map<String, List<Map<String, Object>>> pokeRareMap = new HashMap<>();

	@Autowired
	private PokeDao pokeDao;

	@Override
	public List<Map<String, Object>> searchPoke(Map<String, Object> param) throws RuntimeException {
		return pokeDao.searchPokeByKeyword(param);
	}

	@Override
	public int countPokeByKeword(Map<String, Object> param) throws RuntimeException {
		return pokeDao.countPokeByKeword(param);
	}

	@Override
	public List<PickPoint> getPickList(String code) throws RuntimeException {

		if(!cdc.containsKey(code))
			cdc.set(code, pokeDao.selectAllPickPointByCode(code));
		
		List<PickPoint> result = (List<PickPoint>)cdc.getObject(code);

		if (result == null)
			throw new AppException("Not Found Picklist");

		return result;
	}

	@Override
	public List<Map<String, Object>> getListPokeByRare(String rare) throws RuntimeException {
		
		if(!cdc.containsKey(rare)) {
			cdc.set(rare, pokeDao.selectListPokeByRare(rare));
		}
		
//		if (!pokeRareMap.containsKey(rare)) {
//			pokeRareMap.put(rare, pokeDao.selectListPokeByRare(rare));
//		}
		
		return cdc.getList(rare);
	}

}
