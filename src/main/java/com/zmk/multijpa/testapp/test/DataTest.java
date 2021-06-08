package com.zmk.multijpa.testapp.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zmk.multijpa.testapp.app1.object.entity.Student;
import com.zmk.multijpa.testapp.app1.repo.StudentRepository;
import com.zmk.multijpa.testapp.app1.service.StudentService;
import com.zmk.multijpa.testapp.app2.object.entity.Book;
import com.zmk.multijpa.testapp.app2.repo.BookRepository;


@Component
public class DataTest {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	StudentService studentService;
	public void createDataTest() {
//		createDataStudentTest();
		studentService.createData1Test();
		createDataBookTest();
	}
	private void createDataStudentTest() {
		List<Student> lStudents = new ArrayList<>();
		for(int i = 0; i<100; i++) {
			Student student = new Student("Họ và tên sinh viên thứ "+i, "099999999"+i);
			lStudents.add(student);
			//studentRepository.save(student);
		}
		studentRepository.saveAll(lStudents);
		
	}
	private void createDataBookTest() {
		List<Book> lBooksList = new ArrayList<>();
		for(int i = 0; i<100;i++) {
			Book aBook = new Book("Tên sách thứ "+i,"Nội dung miêu tả bằng tiếng Việt bên trong quyển sách thứ "+i);
			lBooksList.add(aBook);
//			bookRepository.save(aBook);
		}
		bookRepository.saveAll(lBooksList);
	}
}
