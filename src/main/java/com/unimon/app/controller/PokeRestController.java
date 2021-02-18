package com.unimon.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.unimon.app.common.exception.AppException;
import com.unimon.app.component.RandomComp;
import com.unimon.app.service.PokeService;
import com.unimon.app.vo.Pagination;
import com.unimon.app.vo.PickPoint;
import com.unimon.app.vo.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/poke", produces = "application/json; charset=utf8")
public class PokeRestController {

	private Gson gson = new Gson();
	
	@Autowired
	private RandomComp randomComp;
	
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
	
	@Role("ROLE_USER")
	@PostMapping("/pick")
	public String pick(@RequestParam(value = "type") String type, 
					   @RequestParam(value = "amount", defaultValue = "1") int amount,
					   HttpSession session) throws Exception {
		
//		long userNo = ((Account)session.getAttribute("account")).getUserNo();
		
		Map<String, Object> param = new HashMap<>();
//		param.put("userNo", userNo);
		param.put("type", type);
		param.put("amount", amount);
		
//		확률 테이블 조회
		List<PickPoint> pointList = pokeService.getPickList(type);
		
		if(amount < 1)
			throw new AppException("Invalid Value : amount < 1");
		
		
		List<String> pickList = randomComp.get(pointList, amount);
		String[] rareArr = {"R1", "R2", "R3", "R4"};

		Map<String, List<Map<String, Object>>> pokeList = new HashMap<>();

		for (int i = 0; i < rareArr.length; i++) {
			
			String rare = rareArr[i];
			
			if(pickList.contains(rare))
				pokeList.put(rare, pokeService.getListPokeByRare(rare));
		}
		
		List<Map<String, Object>> result = new ArrayList<>();
		
		for(String rare : pickList) {
			result.add(randomComp.getList(pokeList.get(rare)));
		}
		
		return gson.toJson(result);
	}

	
//	###############################################################################
//									PUT
//	###############################################################################
	
	
//	###############################################################################
//									DELETE
//	###############################################################################
	
}
