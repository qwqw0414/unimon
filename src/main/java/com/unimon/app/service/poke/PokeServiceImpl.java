package com.unimon.app.service.poke;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unimon.app.common.exception.AppException;
import com.unimon.app.dao.poke.PokeDao;
import com.unimon.app.dao.poke.PokeDaoImpl;
import com.unimon.app.vo.PickPoint;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, Throwable.class })
public class PokeServiceImpl implements PokeService {

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
	public List<PickPoint> getPickList(String code) throws RuntimeException{
		
		List<PickPoint> result = pokeDao.selectAllPickPointByCode(code);
		
		if(result == null)
			throw new AppException("Not Found Picklist");
			
		return result;
	}

	
}
