package com.unimon.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.unimon.app.component.SudokuComp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/play")
public class PlaybleRestController {

	private Gson gson = new Gson();
	
	@Autowired
	private SudokuComp sc;
	
	
//	###############################################################################
//										GET
//	###############################################################################
	
//	###############################################################################
//										POST
//	###############################################################################
	
	@PostMapping(value = "/s")
	public String generateS(@RequestParam(value = "low", defaultValue = "2") int freq) {
		
		int[][] result = sc.create(freq);
		sc.print(result);
		log.debug("empty : {}", sc.countEmpty(result));
		sc.print(sc.solve(result));
		
		return gson.toJson(result);
	}
	
//	###############################################################################
//										UPDATE
//	###############################################################################
	
//	###############################################################################
//										DELETE
//	###############################################################################
	

	
	
}
