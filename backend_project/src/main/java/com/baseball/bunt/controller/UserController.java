package com.baseball.bunt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.bunt.model.dto.common.User;
import com.baseball.bunt.model.service.UserService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@ApiModel(value="User Controller")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	@ApiOperation(value = "로그인", notes = "id, password를 받아서 로그인 처리")
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user, HttpSession session) {
		User loginUser = userService.login(user.getId(), user.getPassword());
		if(loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
		}
		return new ResponseEntity<>("로그인 실패", HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "회원가입")
	@PostMapping("/join")
	public ResponseEntity<?> signup(@RequestBody User user) {

		try {
			int result = userService.createUser(user);

			if(result != 0)
				return new ResponseEntity<>(result, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("[ERROR] " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
