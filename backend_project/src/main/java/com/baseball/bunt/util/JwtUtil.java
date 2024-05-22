package com.baseball.bunt.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "THE_BASEBALL_FAN_COMMUNITY_BUNT_ADMIN_SECRET_KEY";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

	//다양한 데이터를 Map으로 받아서 처리할 수 있지만 심플하게 Id만 받아서 토큰을 만들어봄
	public String createToken(String id) {
		Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
		return Jwts.builder()
			.header()
			.add("typ", "JWT")
			.and()
			.claim("id", id)
			.expiration(exp)
			.signWith(secretKey)
			.compact();
	}

	public Jws<Claims> validate(String token) {
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token);
	}

}
