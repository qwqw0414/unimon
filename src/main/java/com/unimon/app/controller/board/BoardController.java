package com.unimon.app.controller.board;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unimon.app.service.board.BoardService;
import com.unimon.app.service.board.BoardServiceImpl;
import com.unimon.app.vo.Role;

@Controller()
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/**
	 * 게시글 페이지 매핑
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardListView() throws Exception {
		return "board/boardList";
	}
	
	/**
	 * 글쓰기 페이지 매핑
	 */
	@Role("ROLE_USER")
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String boardWriteView() throws Exception {
		return "board/boardWrite";
	}
	
	/**
	 * 게시글 상세보기 페이지 매핑
	 */
	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public ModelAndView boardReadVeiw(@PathVariable("no") long boardNo, ModelAndView mav) {
		
		Map<String, Object> result = boardService.readBoard(boardNo);
		
		mav.addObject("result", result);
		mav.setViewName("board/boardView");
		
		return mav;
	}
	
	
}
