package com.baseball.bunt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.baseball.bunt.model.dao")
public class DBConfig {
}
