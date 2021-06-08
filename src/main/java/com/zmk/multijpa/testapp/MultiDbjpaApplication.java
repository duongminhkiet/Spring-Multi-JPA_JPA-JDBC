package com.zmk.multijpa.testapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zmk.multijpa.testapp.test.DataTest;

@SpringBootApplication
public class MultiDbjpaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MultiDbjpaApplication.class, args);
	}

	@Autowired
	DataTest dataTest;
//	@Autowired
//	StudentService studentService;
	@Override
	public void run(String... args) throws Exception {
		dataTest.createDataTest();
//		studentService.createData1Test();
	}


}
