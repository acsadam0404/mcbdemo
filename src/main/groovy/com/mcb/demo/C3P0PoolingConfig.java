package com.mcb.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.beans.PropertyVetoException;

@Configuration
@EnableJpaAuditing
public class C3P0PoolingConfig {

	@Value("${c3p0.max_size:100}")
	private int maxSize; // max pool size

	@Value("${c3p0.min_size:10}")
	private int minSize; // min pool size

	@Value("${c3p0.acquire_increment:1}")
	private int acquireIncrement;

	@Value("${c3p0.idle_test_period:120}")
	private int idleTestPeriod;

	@Value("${c3p0.max_statements:0}")
	private int maxStatements;

	@Value("${c3p0.max_idle_time:3600}")
	private int maxIdleTime;

	@Value("${c3p0.url}")
	private String url;

	@Value("${c3p0.username}")
	private String username;

	@Value("${c3p0.password}")
	private String password;

	@Value("${c3p0.driverClassName}")
	private String driverClassName;
	@Value("${c3p0.testConnectionOnCheckout:true}")
	private boolean testConnectionOnCheckout;

	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setMaxPoolSize(maxSize);
		dataSource.setMinPoolSize(minSize);
		dataSource.setAcquireIncrement(acquireIncrement);
		dataSource.setIdleConnectionTestPeriod(idleTestPeriod);
		dataSource.setMaxStatements(maxStatements);
		dataSource.setMaxIdleTime(maxIdleTime);
		dataSource.setJdbcUrl(url);
		dataSource.setPassword(password);
		dataSource.setUser(username);
		dataSource.setTestConnectionOnCheckin(false);
		dataSource.setTestConnectionOnCheckout(true);
		dataSource.setDriverClass(driverClassName);
		dataSource.setTestConnectionOnCheckin(testConnectionOnCheckout);
		return dataSource;
	}
}
