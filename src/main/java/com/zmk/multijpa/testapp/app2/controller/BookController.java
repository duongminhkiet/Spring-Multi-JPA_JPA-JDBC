package com.zmk.multijpa.testapp.app2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmk.multijpa.testapp.app2.service.BookService;

@RestController
public class BookController {
	@Autowired
	BookService bookService;
	
    @GetMapping("/api/app2/insertBook")
    public void insertBook() {
    	bookService.createData2Test();
    }
 // ==========> JPA 
    @GetMapping("/api/app2/insertListBookWithStudent")
    public void insertListBookWithStudent() {
    	bookService.insertListBookWithStudent();
    }
    @GetMapping("/api/app2/insertJPA_Transaction_Test_ok")
    public void insertJPA_Transaction_Test_ok() {
    	bookService.insertJPA_Transaction_Test_ok();
    }
    
    @GetMapping("/api/app2/insertJPA_Transaction_Test_failAll")
    public void insertJPA_Transaction_Test_failAll() {
    	try {
    		bookService.insertJPA_Transaction_Test_failAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    
    @GetMapping("/api/app2/insertJPA_NOTransaction_Test_failSome")
    public void insertJPA_NOTransaction_Test_failSome() {
    	try {
    		bookService.insertJPA_NOTransaction_Test_failSome();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
    // ==========> JDBC 
    @GetMapping("/api/app2/insertJDBC_Transaction_Test_ok")
    public void insertJDBC_Transaction_Test_ok() {
    	bookService.insertJDBC_Transaction_Test_ok();
    }
    
    @GetMapping("/api/app2/insertJDBC_Transaction_Test_failAll")
    public void insertJDBC_Transaction_Test_failAll() {
    	try {
    		bookService.insertJDBC_Transaction_Test_failAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
    @GetMapping("/api/app2/insertJDBC_Transaction_Test_failAll_default")
    public void insertJDBC_Transaction_Test_failAll_default() {
    	try {
    		bookService.insertJDBC_Transaction_Test_failAll_default();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
    @GetMapping("/api/app2/insertJDBC_NOTransaction_Test_failSome")
    public void insertJDBC_NOTransaction_Test_failSome() {
    	
    	try {
    		bookService.insertJDBC_NOTransaction_Test_failSome();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
