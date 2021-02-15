package com.unimon.app.controller.poke;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.unimon.app.model.service.PokeService;
import com.unimon.app.model.vo.Pagination;
import com.unimon.app.model.vo.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/poke", produces = "application/json; charset=utf8")
public class PokeRestController {

	private Gson gson = new Gson();
	
	@Autowired
	private PokeService pokeService;
	
//	###############################################################################
//									GET
//	###############################################################################
	
	@Role("ROLE_USER")
	@GetMapping(value = "/list")
	public String searchPoke(@RequestParam(value = "page") int cPage,
							 @RequestParam(value = "totalContents", defaultValue = "0") int totalContents,
							 @RequestParam(value = "keyword", defaultValue = "") String keyword) throws Exception {

//		파라미터 대입
		Map<String, Object> param = new HashMap<>();
		param.put("keyword", keyword);
		
//		총 컨텐츠 수
		if(totalContents == 0)
			totalContents = pokeService.countPokeByKeword(param);
		
//		페이징 처리
		Pagination page = new Pagination(cPage, totalContents);
		page.setNumPerPage(24);
		param.put("page", page);
		
//		검색 리스트 조회
		List<Map<String, Object>> list = pokeService.searchPoke(param);

//		리턴 값 대입
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("page", page);
		
		return gson.toJson(result);
	}
	
	
//  ###############################################################################
//									POST
//	###############################################################################
	
	
//	###############################################################################
//									PUT
//	###############################################################################
	
	
//	###############################################################################
//									DELETE
//	###############################################################################
	
}
