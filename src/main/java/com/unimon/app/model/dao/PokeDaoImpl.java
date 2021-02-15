package com.unimon.app.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unimon.app.model.vo.Pagination;

@Repository
public class PokeDaoImpl implements PokeDao{

	@Autowired
	private SqlSessionTemplate sst;

	public List<Map<String, Object>> searchPokeByKeyword(Map<String, Object> param) throws RuntimeException {
		return sst.selectList("poke.searchPokeByKeyword", param, (RowBounds) ((Pagination) param.get("page")).getRowBounds());
	}

	public int countPokeByKeword(Map<String, Object> param) throws RuntimeException {
		return sst.selectOne("poke.countPokeByKeword", param);
	}

	
}
