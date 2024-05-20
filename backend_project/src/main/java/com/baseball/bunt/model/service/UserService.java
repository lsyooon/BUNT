package com.baseball.bunt.model.service;

import java.util.List;

import com.baseball.bunt.model.dto.common.User;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "회원 서비스")
public interface UserService {

	int createUser(User user);

	User readUserById(String userId);

	List<User> readUserList();

	int modifyUser(User user);

	int removeUserById(String id);

	int removeUserByUserId(int userId);

	User login(String id, String password);
}
