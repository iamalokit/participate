package com.alokit.participate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.mybatis.spring.annotation.MapperScan;

@Configuration
@EnableTransactionManagement
@MapperScan({ "com.alokit.participate.mapper", "com.alokit.participate.dao" })
public class MybatisConfig {
	
}
