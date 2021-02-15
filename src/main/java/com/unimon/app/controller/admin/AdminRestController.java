package com.unimon.app.controller.admin;

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
import com.unimon.app.common.exception.AppException;
import com.unimon.app.common.exception.ForbiddenException;
import com.unimon.app.model.service.AdminService;
import com.unimon.app.model.service.AdminServiceImpl;
import com.unimon.app.model.vo.Account;
import com.unimon.app.model.vo.Role.UserRole;
import com.unimon.app.model.vo.Pagination;
import com.unimon.app.model.vo.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin", produces = "application/json; charset=utf8")
public class AdminRestController {

	private Gson gson = new Gson();

	@Autowired
	private AdminService adminService;

//	###############################################################################
//										GET
//	###############################################################################

	/**
	 * 유저번호를 이용해 해당 유저가 보유한 모든 궈한을 리턴
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_ADMIN")
	@GetMapping(value = "/auth/{no}")
	public String searchUserAuth(@PathVariable(value = "no") long userNo) throws Exception {

		List<Map<String, Object>> result = adminService.searchUserAuth(userNo);

		return gson.toJson(result);
	}

	/**
	 * 키워드를 이용해 매칭된 유저 리스트를 리턴
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_ADMIN")
	@GetMapping(value = "/user")
	public String searchUserList(@RequestParam(value = "keyword", defaultValue = "") String keyword,
								 @RequestParam(value = "cPage", defaultValue = "1") int cPage) throws Exception {

		Map<String, Object> param = new HashMap<>();
		param.put("keyword", keyword);
		
		Pagination page = new Pagination(cPage, 20);
		
		param.put("page", page);

		log.debug("param : {}", param);

		List<Map<String, Object>> result = adminService.searchAccount(param);

		return gson.toJson(result);
	}

//	###############################################################################
//									    POST
//	###############################################################################

	/**
	 * 해당 계정에 ROLE_USER 부여
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_ADMIN")
	@PostMapping(value = "/auth/user/{no}")
	public HttpStatus grantRoleUser(@PathVariable("no") long userNo) throws Exception {

		Map<String, Object> param = new HashMap<>();
		param.put("userNo", userNo);
		param.put("role", UserRole.ROLE_USER);

		adminService.grantRole(param);

		return HttpStatus.OK;
	}

	/**
	 * 해당 계정에 ROLE_ADMIN 부여
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_SUPER")
	@PostMapping(value = "/auth/admin/{no}")
	public HttpStatus grantRoleAdmin(@PathVariable("no") long userNo, HttpSession session) throws Exception {

		Map<String, Object> param = new HashMap<>();
		param.put("userNo", userNo);
		param.put("role", UserRole.ROLE_ADMIN);

		adminService.grantRole(param);

		return HttpStatus.OK;
	}
//	###############################################################################
//										PUT
//	###############################################################################

//	###############################################################################
//									    DELETE
//	###############################################################################

	/**
	 * 유저 비활성화
	 * @return
	 */
	@Role("ROLE_ADMIN")
	@DeleteMapping(value = "/user/{no}")
	public HttpStatus deleteUser(@PathVariable(value = "no") long userNo, HttpSession session) {

		List<Map<String, Object>> auth = adminService.searchUserAuth(userNo);
		Account excuteUser = (Account) session.getAttribute("account");

//		삭제 권한 검증
		for (Map<String, Object> map : auth) {

			String role = (String) map.get("auth_code");

//			suadmin 접근 방지
			if (role.equals(UserRole.ROLE_SUPER.name())) {
				throw new ForbiddenException("invalied suadmin");
			}
//			amdin 유저 제거시 suadmin 권한 필요
			else if (role.equals(UserRole.ROLE_ADMIN.name()) && !excuteUser.hasRole(UserRole.ROLE_SUPER)) {
				throw new ForbiddenException("neet to suadmin");
			}
		}

		adminService.deleteUser(userNo);

		return HttpStatus.OK;
	}

	/**
	 * 해당 계정에 ROLE_USER 권한 제거
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_ADMIN")
	@DeleteMapping(value = "/auth/user/{no}")
	public HttpStatus rovokeRoleUser(@PathVariable("no") long userNo) throws Exception {

		Map<String, Object> param = new HashMap<>();
		param.put("userNo", userNo);
		param.put("role", UserRole.ROLE_USER);

		adminService.revokeRole(param);

		return HttpStatus.OK;
	}

	/**
	 * 해당 계정에 ROLE_ADMIN 권한 제거
	 * @return
	 * @throws Exception
	 */
	@Role("ROLE_SUPER")
	@DeleteMapping(value = "/auth/admin/{no}")
	public HttpStatus rovokeRoleAdmin(@PathVariable("no") long userNo, HttpSession session) throws Exception {

		Map<String, Object> param = new HashMap<>();
		param.put("userNo", userNo);
		param.put("role", UserRole.ROLE_ADMIN);

		adminService.revokeRole(param);

		return HttpStatus.OK;
	}

}