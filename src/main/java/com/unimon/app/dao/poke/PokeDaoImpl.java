package com.unimon.app.dao.poke;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unimon.app.vo.Pagination;
import com.unimon.app.vo.PickPoint;

@Repository
public class PokeDaoImpl implements PokeDao{

	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public List<Map<String, Object>> searchPokeByKeyword(Map<String, Object> param) throws RuntimeException {
		return sst.selectList("poke.searchPokeByKeyword", param, (RowBounds) ((Pagination) param.get("page")).getRowBounds());
	}

	@Override
	public int countPokeByKeword(Map<String, Object> param) throws RuntimeException {
		return sst.selectOne("poke.countPokeByKeword", param);
	}

	@Override
	public List<PickPoint> selectAllPickPointByCode(String code) throws RuntimeException {
		return sst.selectList("poke.selectAllPickPointByCode", code);
	}

	@Override
	public List<Map<String, Object>> selectListPokeByRare(String rare) throws RuntimeException {
		return sst.selectList("poke.selectListPokeByRare", rare);
	}

	
}
