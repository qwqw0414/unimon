package com.unimon.app.dao.poke;

import java.util.List;
import java.util.Map;

import com.unimon.app.vo.PickPoint;

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

	/**
	 * 
	 * @param code
	 * @return
	 */
	List<PickPoint> selectAllPickPointByCode(String code);

	/**
	 * 
	 * @param rare
	 * @return
	 */
	List<Map<String, Object>> selectListPokeByRare(String rare);

}
