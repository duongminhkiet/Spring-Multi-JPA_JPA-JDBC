package com.zmk.multijpa.testapp.app1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmk.multijpa.testapp.app1.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
    @GetMapping("/api/app1/insertStudent")
    public void insertStudent() {
    	studentService.createData1Test();
    }
    // ==========> JPA 
    @GetMapping("/api/app1/insertJPA_Transaction_Test_ok")
    public void insertJPA_Transaction_Test_ok() {
    	studentService.insertJPA_Transaction_Test_ok();
    }
    
    @GetMapping("/api/app1/insertJPA_Transaction_Test_failAll")
    public void insertJPA_Transaction_Test_failAll() {
    	try {
    		studentService.insertJPA_Transaction_Test_failAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    
    @GetMapping("/api/app1/insertJPA_NOTransaction_Test_failSome")
    public void insertJPA_NOTransaction_Test_failSome() {
    	try {
    		studentService.insertJPA_NOTransaction_Test_failSome();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
    // ==========> JDBC 
    @GetMapping("/api/app1/insertJDBC_Transaction_Test_ok")
    public void insertJDBC_Transaction_Test_ok() {
    	studentService.insertJDBC_Transaction_Test_ok();
    }
    
    @GetMapping("/api/app1/insertJDBC_Transaction_Test_failAll")
    public void insertJDBC_Transaction_Test_failAll() {
    	try {
    		studentService.insertJDBC_Transaction_Test_failAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
    
    @GetMapping("/api/app1/insertJDBC_NOTransaction_Test_failSome")
    public void insertJDBC_NOTransaction_Test_failSome() {
    	
    	try {
    		studentService.insertJDBC_NOTransaction_Test_failSome();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
