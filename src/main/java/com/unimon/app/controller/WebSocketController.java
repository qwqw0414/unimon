package com.unimon.app.controller;


import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WebSocketController {

	private Gson gson = new Gson();
	
	
	@RequestMapping("/ws/public")
	public String chatView() {
		return "ws/public";
	}
	
	
	@MessageMapping("/public")
	@SendTo("/sub/public")
	public String wsTest(@RequestBody String jsonData , 
						 @DestinationVariable("id") String id) {
		
		log.debug("id : {}", id);
		log.debug("result : {}", jsonData);
		
		return jsonData;
	}
	
}
