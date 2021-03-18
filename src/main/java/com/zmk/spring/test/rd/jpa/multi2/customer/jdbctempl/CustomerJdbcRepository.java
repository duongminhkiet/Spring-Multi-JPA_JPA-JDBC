package com.zmk.spring.test.rd.jpa.multi2.customer.jdbctempl;

import java.util.List;

import com.zmk.spring.test.rd.jpa.multi2.customer.data.CustomerModel;

public interface CustomerJdbcRepository {
	int count();
	int deleteById(Long id);
	int save(CustomerModel book);
    List<CustomerModel> findAll();
}
