package com.test.technical.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = { "com.test.technical" })
@EnableJpaRepositories(basePackages = { "com.test.technical" })
@ComponentScan(basePackages = { "com.test.technical" })
@EnableTransactionManagement
public class BaseConfiguration {
}
