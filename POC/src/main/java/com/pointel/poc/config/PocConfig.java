package com.pointel.poc.config;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pointel.poc.session.LoginFilter;
import com.pointel.poc.session.SessionFilter;

@Configuration
public class PocConfig implements WebMvcConfigurer {
	@Bean
	public FilterRegistrationBean<SessionFilter> sessionFilter() {
		final FilterRegistrationBean<SessionFilter> sessionFilter = new FilterRegistrationBean<>();
		sessionFilter.setFilter(new SessionFilter());
		sessionFilter.addUrlPatterns("/Logout", "/UserManagement", "/Add", "/Trace", "/GetUsers",
				"/Home", "/DeleteUserInfo");
		return sessionFilter;
	}

	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter() {
		final FilterRegistrationBean<LoginFilter> loginFilter = new FilterRegistrationBean<>();
		loginFilter.setFilter(new LoginFilter());
		loginFilter.addUrlPatterns("/", "/ValidateUser", "/Login");
		return loginFilter;

	}

	@Bean
	public DataSource pocDatasource() {
		DriverManagerDataSource dataSourceBuilder = new DriverManagerDataSource();
		ResourceBundle bundle = ResourceBundle.getBundle("DB");
		dataSourceBuilder.setUsername(bundle.getString("UserName"));
		dataSourceBuilder.setPassword(bundle.getString("Password"));
		dataSourceBuilder.setUrl(bundle.getString("DatabaseUrl") + bundle.getString("HostName") + ":"
				+ bundle.getString("Port") + ";databaseName=" + bundle.getString("Database"));
		dataSourceBuilder.setDriverClassName(bundle.getString("DriverClassName"));
		return dataSourceBuilder;

	}

	@Bean
	public JpaTransactionManager jpaTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	private HibernateJpaVendorAdapter vendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		return vendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
		entityManagerFactoryBean.setDataSource(pocDatasource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan("com.pointel.poc");
		entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
		return entityManagerFactoryBean;
	}

	Properties jpaHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "validate");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		return properties;
	}

}
