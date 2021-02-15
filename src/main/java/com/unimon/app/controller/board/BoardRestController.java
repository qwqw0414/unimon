package com.unimon.app.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.unimon.app.common.exception.ForbiddenException;
import com.unimon.app.model.service.BoardService;
import com.unimon.app.model.service.BoardServiceImpl;
import com.unimon.app.model.vo.Account;
import com.unimon.app.model.vo.Role.UserRole;
import com.unimon.app.model.vo.Pagination;
import com.unimon.app.model.vo.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping(value = "/api/board", produces = "application/json; charset=utf8")
public class BoardRestController {

	private Gson gson = new Gson();

	@Autowired
	private BoardService boardService;

//	###############################################################################
//										GET
//  ###############################################################################
	
	/**
	 * 게시글 상세 정보
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_USER")
	@GetMapping(value = "/view/{no}")
	public String readBoard(@PathVariable("no") long boardNo) throws Exception {

		Map<String, Object> result = boardService.readBoard(boardNo);

		return gson.toJson(result);
	}

	/**
	 * 게시글 키워드 매칭 조회
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_USER")
	@GetMapping(value = "/list")
	public String searchBoard( @RequestParam(value = "keyword", defaultValue = "") String keyword,
							   @RequestParam(value = "cPage", defaultValue = "1") int cPage) throws Exception {
		
		
		Map<String, Object> param = new HashMap<>();
		param.put("keyword", keyword);

//		Paging
		Pagination page = new Pagination(cPage, boardService.getTotalContents(param));
		param.put("pagination", page);
		
		log.debug("param : {}", param);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", boardService.searchBoard(param));
		result.put("pageBar", page.getPageBar(null));
		
		return gson.toJson(result);
	}
	
//  ###############################################################################
//    									POST
//  ###############################################################################

	/**
	 * 게시글 작성 
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_USER")
	@PostMapping(value = "/write")
	public HttpStatus boardWrite(@RequestParam("title") String title, @RequestParam("contents") String contents,
			HttpSession session) throws Exception {

//		작성자 번호
		long userNo = ((Account) session.getAttribute("account")).getUserNo();

//		파라미터 값 대입
		Map<String, Object> param = new HashMap<>();
		param.put("userNo", userNo);
		param.put("title", title);
		param.put("contents", contents);

		log.debug("write : {}", param);

		boardService.boardWrite(param);

		return HttpStatus.OK;
	}

//	###############################################################################
//										PUT
//  ###############################################################################

//  ###############################################################################
//    									DELETE
//  ###############################################################################

	/**
	 * 게시글 삭제
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_USER")
	@DeleteMapping(value = "/board/{no}")
	public HttpStatus removeBoard(@PathVariable("no") long boardNo, HttpSession session) throws Exception{
		
		Account account = (Account)session.getAttribute("account");
		Map<String, Object> board = boardService.readBoard(boardNo);

//		권한
		if (!(account.hasRole(UserRole.ROLE_ADMIN) || account.hasRole(UserRole.ROLE_SUPER))) {
			if(account.getUserNo() != (Integer)board.get("user_no")) {
				throw new ForbiddenException("ForbiddenException : 권한 없음");
			}
		}

		boardService.removeBoard(boardNo);
		
		return HttpStatus.OK;
	}

}
