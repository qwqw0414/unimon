package com.unimon.app.controller.poke;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unimon.app.model.service.PokeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/poke")
public class PokeController {
	
	@Autowired
	private PokeService pokeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pokeListView() throws Exception {
		return "poke/pokeList";
	}
	
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public String pokeDetailView(@PathVariable("no") long monNo) {
		
		log.debug("monNo : {}", monNo);
		
		return null;
	}
	
	@RequestMapping(value = "/pick", method = RequestMethod.GET)
	public String pokePickView() {
		
		return "poke/pick";
	}

	@RequestMapping(value = "/search/{searchContent}", method = RequestMethod.GET)
	public ModelAndView SearchingPoke(@PathVariable("searchContent") String searchContent, ModelAndView mav) {
		
		Map<String, Object> param = new HashMap<>();
		param.put("searchContent", searchContent);
		
		List<Map<String, Object>> result = pokeService.searchingPoke(param);
		
		System.out.println("dddddddddddddd"+result);
		
		mav.addObject("searchList", result);
		mav.addObject("searchContent", searchContent);
		mav.setViewName("poke/pokeSearchList");
		
		return mav;
	}
	
	
	
	
}