package com.baseball.bunt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTest {
	public static void main(String[] args) {
		Map<String, String> headers = new HashMap<>();
		headers.put("typ", "JWT");

		String key = "THE_BASEBALL_FAN_COMMUNITY_BUNT_ADMIN_SECRET_KEY";
		SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

		//header, payload, sign
		String token = Jwts.builder() //JWT를 만들 수 있는 빌더 인스턴스
			.header()
			.add("name", "prao").add(headers).and() //and() 메서드를 JWT 빌더로 들어간다
			.subject("bunt").expiration(new Date(System.currentTimeMillis() + 3000))
			.signWith(secretKey).compact();

		System.out.println(token);

		Jws<Claims> jwsClaims = Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token);

		System.out.println(jwsClaims.getHeader());
		System.out.println(jwsClaims.getPayload());
	}
}
