package com.unimon.app.controller.poke;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unimon.app.vo.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/poke")
public class PokeController {

	@Role("ROLE_USER")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pokeListView() throws Exception {
		return "poke/pokeList";
	}

	@Role("ROLE_USER")
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public String pokeDetailView(@PathVariable("no") long monNo) {

		log.debug("monNo : {}", monNo);

		return null;
	}

	@Role("ROLE_USER")
	@RequestMapping(value = "/pick", method = RequestMethod.GET)
	public String pokePickView() {

		return "poke/pick";
	}

}