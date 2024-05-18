package com.baseball.bunt.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baseball.bunt.model.dto.common.User;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "회원 DAO")
@Repository
public interface UserDao {

	int insertUser(User user);

	User selectUserByUserId(int userId); //유저 번호로 조회

	User selectUserById(String id); //가입한 ID로 조회

	List<User> selectUserList();

	int updateUser(User user);

	int deleteUserById(String id);

	int deleteUserByUserId(int userId);

	User login(Map<String, String> user);
}
