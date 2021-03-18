package com.zmk.spring.test.rd.jpa.multi2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zmk.spring.test.rd.jpa.multi2.customer.data.CustomerModel;
import com.zmk.spring.test.rd.jpa.multi2.customer.jdbctempl.CustomerJdbcRepository;
import com.zmk.spring.test.rd.jpa.multi2.customer.repo.CustomerRepository;
import com.zmk.spring.test.rd.jpa.multi2.product.data.ProductModel;
import com.zmk.spring.test.rd.jpa.multi2.product.repo.ProductRepository;

@SpringBootApplication
public class SpringMultiDbjpa2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringMultiDbjpa2Application.class, args);
	}

	@Autowired
	private CustomerRepository crepo;
	@Autowired
	private ProductRepository prepo;
//	@Autowired
//	@Qualifier("sqlServer1JdbcTemplate")
//	JdbcTemplate jdbcTemplate1;
	@Autowired 
	private CustomerJdbcRepository customerJdbcRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
//		CustomerModel cm = new CustomerModel("emailz1@gmail.com","Văn Việt","Nguyễn");
//		crepo.save(cm);
//		
//		CustomerModel cm1 = new CustomerModel("emailzx1@gmail.com",null,"Nguyễn");
//		crepo.save(cm1);
//		
		ProductModel pm = new ProductModel("123","Product name1",2.7);
		prepo.save(pm);
		

		
		System.out.print("COunt: "+customerJdbcRepository.count());
		
		
	}

}
