package com.unimon.app.controller.poke;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.unimon.app.model.service.PokeService;
import com.unimon.app.model.vo.Pagination;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/poke", produces = "application/json; charset=utf8")
public class PokeRestController {

	private Gson gson = new Gson();
	
	@Autowired
	private PokeService pokeService;
	
	@GetMapping(value = "/list")
	public String searchPoke(@RequestParam("page") int cPage, 
							 @RequestParam(value = "keyword", defaultValue = "") String keyword) throws Exception {
		
		Pagination page = new Pagination(cPage, 500);
		page.setNumPerPage(24);
		
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("keyword", keyword);
		
		List<Map<String, Object>> result = pokeService.searchPoke(param);

		return gson.toJson(result);
	}
	
}
