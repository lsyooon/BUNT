package com.baseball.bunt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.bunt.model.dto.common.User;
import com.baseball.bunt.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Tag(name = "User Controller", description = "회원을 관리하는 컨트롤러")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
	RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	@Operation(summary = "로그인", description = "id, password를 받아서 로그인 처리")
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user, HttpSession session) {
		User loginUser = userService.login(user.getId(), user.getPassword());
		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
		}
		return new ResponseEntity<>("로그인 실패", HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "로그아웃")
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		try {
			session.removeAttribute("loginUser");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "회원가입")
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody User user) {
		try {
			int result = userService.createUser(user);
			if (result != 0) {
				return new ResponseEntity<>(result, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "회원 정보 수정", description = "비밀번호, 이메일, 이름, 닉네임, 나이, 전화번호, 주소 입력")
	@PutMapping("/modify")
	public ResponseEntity<?> modifyUser(@RequestBody User user) {
		try {
			int result = userService.modifyUser(user);
			if (result != 0) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "회원 탈퇴")
	@DeleteMapping("/quit/{id}")
	public ResponseEntity<?> quit(@PathVariable("id") String id) {
		try {
			int result = userService.removeUserById(id);
			if (result != 0) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "회원 아이디로 특정 회원 조회")
	@GetMapping("/read/{userId}")
	public ResponseEntity<?> selectUserByUserId(@PathVariable("userId") int userId) {
		try {
			User user = userService.readUserByUserId(userId);
			if (user != null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "유저 목록 리스트 조회")
	@GetMapping("/read/list")
	public ResponseEntity<?> getUserList() {
		try {
			List<User> userList = userService.readUserList();
			if (userList != null) {
				return new ResponseEntity<>(userList, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("[ERROR] " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
