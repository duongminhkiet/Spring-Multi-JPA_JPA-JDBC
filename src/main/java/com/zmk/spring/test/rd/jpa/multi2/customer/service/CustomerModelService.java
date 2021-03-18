package com.zmk.spring.test.rd.jpa.multi2.customer.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmk.spring.test.rd.jpa.multi2.customer.data.CustomerModel;
import com.zmk.spring.test.rd.jpa.multi2.customer.jdbctempl.CustomerJdbcRepository;
import com.zmk.spring.test.rd.jpa.multi2.customer.repo.CustomerRepository;

@Service
public class CustomerModelService {
	@Autowired
	private CustomerRepository crepo;
	@Autowired
	private CustomerModelService customerModelService;
	@Autowired 
	private CustomerJdbcRepository customerJdbcRepository;
	@Transactional("customerTransactionManager")
    public void insertCustomerModelOkTS(){
    	List<CustomerModel> books = Arrays.asList(
                new CustomerModel("email1@email.com","firstname1","lastname1"),
                new CustomerModel("email2@email.com","firstname2","lastname2"),
                new CustomerModel("email3@email.com","firstname3","lastname3"),
                new CustomerModel("email4@email.com","firstname4","lastname4")
        );

        books.forEach(book -> {
            //System.out.print("saved book: "+book.getName());
        	crepo.save(book);
        });
        
    }
	
	@Transactional("customerTransactionManager")
    public void insertCustomerModelFailTS(){
    	List<CustomerModel> books = Arrays.asList(
                new CustomerModel("email11@email.com","firstname1","lastname1"),
                new CustomerModel("email21@email.com","firstname2","lastname2"),
                new CustomerModel("email31@email.com","firstname3","lastname3"),
                new CustomerModel("email41@email.com",null,"lastname4")
        );

        books.forEach(book -> {
            //System.out.print("saved book: "+book.getName());
        	crepo.save(book);
        });
        
    }
    public void insertCustomerModelFailNoTS(){
    	List<CustomerModel> books = Arrays.asList(
                new CustomerModel("email11@email.com","firstname1","lastname1"),
                new CustomerModel("email21@email.com","firstname2","lastname2"),
                new CustomerModel("email31@email.com","firstname3","lastname3"),
                new CustomerModel("email41@email.com",null,"lastname4")
        );

        books.forEach(book -> {
            //System.out.print("saved book: "+book.getName());
        	crepo.save(book);
        });
        
    }
	
	@Transactional("customerTransactionManager")
    public void insertCustomerModelOkTSJdbc(){
    	List<CustomerModel> books = Arrays.asList(
                new CustomerModel("emailx1@email.com","firstname1","lastname1"),
                new CustomerModel("emailx2@email.com","firstname2","lastname2"),
                new CustomerModel("emailx3@email.com","firstname3","lastname3"),
                new CustomerModel("emailx4@email.com","firstname4","lastname4")
        );

        books.forEach(book -> {
            //System.out.print("saved book: "+book.getName());
        	customerJdbcRepository.save(book);
        });
        
    }
	@Transactional("customerTransactionManager")
    public void insertCustomerModelFailTSJdbc(){
    	List<CustomerModel> books = Arrays.asList(
                new CustomerModel("emaily11@email.com","firstname1","lastname1"),
                new CustomerModel("emaily21@email.com","firstname2","lastname2"),
                new CustomerModel("emaily31@email.com","firstname3","lastname3"),
                new CustomerModel("emaily41@email.com",null,"lastname4")
        );

        books.forEach(book -> {
            //System.out.print("saved book: "+book.getName());
        	customerJdbcRepository.save(book);
        });
        
    }
}
