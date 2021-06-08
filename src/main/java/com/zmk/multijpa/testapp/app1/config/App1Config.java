package com.zmk.multijpa.testapp.app1.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "app1EntityManagerFactory",
        transactionManagerRef = "app1TransactionManager",
        basePackages = {"com.zmk.multijpa.testapp.app1.repo"}
)
public class App1Config {

//    @Primary
//    @Bean(name = "app1DataSource")
//    @ConfigurationProperties(prefix = "app1.datasource")
//    public DataSource app1DataSource() {
//        return DataSourceBuilder.create()
//        		.build();
//    }
    @Primary
    @Bean(name = "app1DataSource")
    public DataSource dnDataSource() {
    	HikariDataSource mainDatasource = new HikariDataSource();
    	mainDatasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	mainDatasource.setJdbcUrl("jdbc:sqlserver://10.72.99.14;databaseName=test3");
    	mainDatasource.setUsername("test1");
    	mainDatasource.setPassword("test1");
        return mainDatasource;
    }
    
    @Primary
    @Bean(name = "app1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("app1DataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.zmk.multijpa.testapp.app1.object.entity")
                .persistenceUnit("db1")
                .properties(jpaProperties())
                .build();
    }
    private Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2012Dialect");
//        props.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
        props.put(Environment.HBM2DDL_AUTO, "update");
//        props.put("hibernate.hbm2ddl.auto", "create-drop");
        props.put(Environment.SHOW_SQL, true);
//      props.put("hibernate.show_sql", true);
        props.put(Environment.IMPLICIT_NAMING_STRATEGY, "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
//      props.put("hibernate.naming.implicit-strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
        props.put(Environment.PHYSICAL_NAMING_STRATEGY, "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
//      props.put("hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        
//        props.put("hibernate.generate_ddl", true);
//        props.put("hibernate.connection.characterEncoding", "utf-8");
//        props.put("hibernate.connection.CharSet", "utf-8");
//        props.put("hibernate.connection.useUnicode", "true");
//        props.put("hibernate.database", "default");
        return props;
    }
//	@Bean(name = "app1TransactionManager")
////	@Autowired
//	@Primary
//	DataSourceTransactionManager tm1(@Qualifier("app1DataSource") final DataSource datasource) {
//		DataSourceTransactionManager txm = new DataSourceTransactionManager(datasource);
//		return txm;
//	}
    @Bean(name = "app1TransactionManager")
    public PlatformTransactionManager productTransactionManager(
            @Qualifier("app1EntityManagerFactory") EntityManagerFactory
                    productEntityManagerFactory
    ) {
        return new JpaTransactionManager(productEntityManagerFactory);
    }
    
	@Bean(name = "app1JdbcTemplate")
	public JdbcTemplate app1JdbcTemplate(@Qualifier("app1DataSource") DataSource app1DataSourceX) {
		return new JdbcTemplate(app1DataSourceX);
	}
}
