package com.zmk.spring.test.rd.jpa.multi2.customer.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "customerEntityManagerFactory",
        transactionManagerRef = "customerTransactionManager",
        basePackages = {"com.zmk.spring.test.rd.jpa.multi2.customer.repo"}
)
public class CustomerConfig {

    @Primary
    @Bean(name = "customerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource customerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "customerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("customerDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.zmk.spring.test.rd.jpa.multi2.customer.data")
//                .packages("com.zmk.spring.test.rd.jpa.multi2.customer.data2")
                .persistenceUnit("db1")
                .build();
    }

//    @Primary
//    @Bean(name = "customerTransactionManager")
//    public PlatformTransactionManager customerTransactionManager(
//            @Qualifier("customerEntityManagerFactory") EntityManagerFactory
//                    customerEntityManagerFactory
//    ) {
//        return new JpaTransactionManager(customerEntityManagerFactory);
//    }
    //
    
	@Bean(name = "customerTransactionManager")
	@Autowired
	@Primary
	DataSourceTransactionManager tm1(@Qualifier("customerDataSource") final DataSource datasource) {
		DataSourceTransactionManager txm = new DataSourceTransactionManager(datasource);
		return txm;
	}
	@Bean(name = "sqlServer1JdbcTemplate")
	public JdbcTemplate sqlServer1JdbcTemplate(@Qualifier("customerDataSource") DataSource sqlServer1) {
		return new JdbcTemplate(sqlServer1);
	}
	
	
//    @Bean(name="sqlServer1Transaction")
//    @Autowired
//    @Primary
//    DataSourceTransactionManager tm1(@Qualifier ("sqlServer1") DataSource datasource) {
//        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
//        return txm;
//    }
	
	//mybatis 
//	private static final String EMPLOYEE1_SESSION_FACTORY = "employee1SessionFactory";
//	@Bean(name = EMPLOYEE1_SESSION_FACTORY, destroyMethod = "")
//	@Primary
//	public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("customerDataSource") final DataSource oneDataSource)
//			throws Exception {
//		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(oneDataSource);
//		SqlSessionFactory sqlSessionFactory;
//		sqlSessionFactory = sqlSessionFactoryBean.getObject();
//		sqlSessionFactory.getConfiguration().addMapper(Employee1MyBatisRepository.class);
//		// Various other SqlSessionFactory settings
//		return sqlSessionFactoryBean;
//	}
//	@Bean
//	public MapperFactoryBean<Employee1MyBatisRepository> etrMapper(
//			@Qualifier(EMPLOYEE1_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
//		MapperFactoryBean<Employee1MyBatisRepository> factoryBean = new MapperFactoryBean<>(
//				Employee1MyBatisRepository.class);
//		factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
//		return factoryBean;
//	}
}
