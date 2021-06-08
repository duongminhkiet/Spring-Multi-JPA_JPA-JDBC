package com.zmk.multijpa.testapp.app2.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "app2EntityManagerFactory",
        transactionManagerRef = "app2TransactionManager",
        basePackages = {"com.zmk.multijpa.testapp.app2.repo"}
)
public class App2Config {

	
    @Bean(name = "app2DataSource")
    @ConfigurationProperties(prefix = "app2.datasource")
    public DataSource app2DataSource() {
        return DataSourceBuilder.create()
        		.build();
    }

    @Bean(name = "app2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("app2DataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.zmk.multijpa.testapp.app2.object.entity")
                .persistenceUnit("db2")
                .properties(jpaProperties())
                .build();
    }
    private Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2012Dialect");
//      props.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
      props.put(Environment.HBM2DDL_AUTO, "update");
//      props.put("hibernate.hbm2ddl.auto", "create-drop");
      props.put(Environment.SHOW_SQL, true);
//    props.put("hibernate.show_sql", true);
      props.put(Environment.IMPLICIT_NAMING_STRATEGY, "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
//    props.put("hibernate.naming.implicit-strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
      props.put(Environment.PHYSICAL_NAMING_STRATEGY, "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
//    props.put("hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
      
//      props.put("hibernate.generate_ddl", true);
//      props.put("hibernate.connection.characterEncoding", "utf-8");
//      props.put("hibernate.connection.CharSet", "utf-8");
//      props.put("hibernate.connection.useUnicode", "true");
//      props.put("hibernate.database", "default");
        return props;
    }
    @Bean(name = "app2TransactionManager")
    public PlatformTransactionManager productTransactionManager(
            @Qualifier("app2EntityManagerFactory") EntityManagerFactory
                    productEntityManagerFactory
    ) {
        return new JpaTransactionManager(productEntityManagerFactory);
    }
    
//	@Bean(name = "app2TransactionManager")
//	@Autowired
//	DataSourceTransactionManager tm2(@Qualifier("app2DataSource") final DataSource datasource2) {
//		DataSourceTransactionManager txm2 = new DataSourceTransactionManager(datasource2);
//		return txm2;
//	}
	@Bean(name = "app2JdbcTemplate")
	public JdbcTemplate app2JdbcTemplate(@Qualifier("app2DataSource") DataSource app2DataSourceX) {
		return new JdbcTemplate(app2DataSourceX);
	}
}
