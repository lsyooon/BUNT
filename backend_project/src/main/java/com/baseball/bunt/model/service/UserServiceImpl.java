package com.baseball.bunt.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baseball.bunt.model.dao.UserDao;
import com.baseball.bunt.model.dto.common.User;

import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;

@ApiModel(value = "User Service Implementation")
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	@Override
	public int createUser(final User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User readUserById(final String userId) {
		return userDao.selectUserById(userId);
	}

	@Override
	public User readUserByUserId(final int userId) {
		return userDao.selectUserByUserId(userId);
	}

	@Override
	public List<User> readUserList() {
		return userDao.selectUserList();
	}

	@Override
	public int modifyUser(final User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int removeUserById(final String id) {
		return userDao.deleteUserById(id);
	}

	@Override
	public int removeUserByUserId(final int userId) {
		return userDao.deleteUserByUserId(userId);
	}

	@Override
	public User login(final String id, final String password) {
		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("id", id);
		userInfo.put("password", password);
		return userDao.login(userInfo);
	}
}
