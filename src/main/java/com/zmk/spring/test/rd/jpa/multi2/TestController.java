package com.zmk.spring.test.rd.jpa.multi2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmk.spring.test.rd.jpa.multi2.customer.data.CustomerModel;
import com.zmk.spring.test.rd.jpa.multi2.customer.jdbctempl.CustomerJdbcRepository;
import com.zmk.spring.test.rd.jpa.multi2.customer.service.CustomerModelService;

@RestController
public class TestController {
//	@Autowired
//	private Book1Service bookService1;
	@Autowired 
	private CustomerJdbcRepository customerJdbcRepository;
	@Autowired
	private CustomerModelService customerModelService;
    @GetMapping("/api/db1/insertCustomerModelFailNoTS")
    public void insertCustomerModelFailNoTS() {
//         bookService1.insertBook1();
    	customerModelService.insertCustomerModelFailNoTS();
    }
    @GetMapping("/api/db1/insertCustomerModelOkTS")
    public void insertCustomerModelOkTS() {
//         bookService1.insertBook1();
    	customerModelService.insertCustomerModelOkTS();
    }
    @GetMapping("/api/db1/insertCustomerModelFailTS")
    public void insertCustomerModelFailTS() {
//         bookService1.insertBook1();
    	customerModelService.insertCustomerModelFailTS();
    }
    @GetMapping("/api/db1/countJdbc")
    public int countJdbc() {
//         bookService1.insertBook1();
    	return customerJdbcRepository.count();
    }
    @GetMapping("/api/db1/listAllJdbc")
    public List<CustomerModel> listAllJdbc() {
//         bookService1.insertBook1();
    	return customerJdbcRepository.findAll();
    }
    @GetMapping("/api/db1/insertCustomerModelOkTSJdbc")
    public void insertCustomerModelOkTSJdbc() {
//         bookService1.insertBook1();
    	 customerModelService.insertCustomerModelOkTSJdbc();
    }
    @GetMapping("/api/db1/insertCustomerModelFailTSJdbc")
    public void insertCustomerModelFailTSJdbc() {
//         bookService1.insertBook1();
    	 customerModelService.insertCustomerModelFailTSJdbc();
    }
    
	
    @GetMapping("/api/db1/insertCustomerModelFailTSJdbcSaveDirect")
    public void insertCustomerModelFailTSJdbcSaveDirect() {
    	CustomerModel cm2 = new CustomerModel("email1jdbc@gmail.com","Văn Việt","Nguyễn");
    	CustomerModel cm3 = new CustomerModel("email1jdbc@gmail.com",null,"Nguyễn");
    	customerJdbcRepository.save(cm2);
    	customerJdbcRepository.save(cm3);
    }
	
}
