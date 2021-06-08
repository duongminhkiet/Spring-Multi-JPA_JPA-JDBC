package com.zmk.multijpa.testapp.app2.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmk.multijpa.testapp.app1.object.entity.Student;
import com.zmk.multijpa.testapp.app1.service.StudentService;
import com.zmk.multijpa.testapp.app2.object.entity.Book;
import com.zmk.multijpa.testapp.app2.repo.BookJDBCRepository;
import com.zmk.multijpa.testapp.app2.repo.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookJDBCRepository bookJDBCRepository;
	
	@Autowired
	StudentService studentService;
	
	public void createData2Test() {
		List<Book> lBooksList = new ArrayList<>();
		for(int i = 0; i<100;i++) {
			Book aBook = new Book("Tên sách thứ "+i,"Nội dung miêu tả bằng tiếng Việt bên trong quyển sách thứ "+i);
			lBooksList.add(aBook);
//			bookRepository.save(aBook);
		}
		bookRepository.saveAll(lBooksList);
	}
	///////////////////////////////////////
	// success all or fail all -> expect success all
		public void insertListBookWithStudent() {
			List<Student> listStudents = studentService.getAllStudents();
			List<Book> books = new ArrayList<>();
			listStudents.forEach(student->{
				Book book = new Book("Sach nay cua: "+student.getName(), "Nội dung cua sách "+student.getPhone());
				books.add(book);
			});
			bookRepository.saveAll(books);
		}
	
		@Transactional("app2TransactionManager")
	    public void insertJPA_Transaction_Test_ok(){
	    	List<Book> books = Arrays.asList(
	                new Book("Tên sách thứ 1","Nội dung miêu tả bằng tiếng Việt bên trong quyển sách thứ 1"),
	                new Book("Tên sách thứ 2","Nội dung miêu tả bằng tiếng Việt bên trong quyển sách thứ 2"),
	                new Book("Tên sách thứ 3","Nội dung miêu tả bằng tiếng Việt bên trong quyển sách thứ 3"),
	                new Book("Tên sách thứ 4","Nội dung miêu tả bằng tiếng Việt bên trong quyển sách thứ 4")
	        );

//	        books.forEach(book -> {
//	        	studentRepository.save(book);
//	        });
	    	bookRepository.saveAll(books);
	        
	    }
		
		//fail all 
		@Transactional("app2TransactionManager")
	    public void insertJPA_Transaction_Test_failAll(){
	    	List<Book> books = Arrays.asList(
	                new Book("Book Sinh Viên 5","0999999991"),
	                new Book("Book Sinh Viên 6","0999999992"),
	                new Book(null,"0999999993"),
	                new Book("Book Sinh Viên 8","0999999994")
	        );

//	    	try {
//	            books.forEach(book -> {
//	            	studentRepository.save(book);
//	            });
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}

	    	bookRepository.saveAll(books);
	        
	    }
		// just fail all objects from the third object
	    public void insertJPA_NOTransaction_Test_failSome(){
	    	List<Book> books = Arrays.asList(
	                new Book("Sinh Viên 9","0999999991"),
	                new Book("Sinh Viên 10","0999999992"),
	                new Book(null,"0999999993"),
	                new Book("Sinh Viên 12","0999999994")
	        );

	        books.forEach(book -> {
	        	bookRepository.save(book);
	        });

//	    	bookRepository.saveAll(books);
	    }
	    
	    /// ================== FOR JDBC ==================
		@Transactional("app2TransactionManager")
	    public void insertJDBC_Transaction_Test_ok(){
	    	List<Book> books = Arrays.asList(
	                new Book("Sinh Viên JDBC 1","0999999991"),
	                new Book("Sinh Viên JDBC 2","0999999992"),
	                new Book("Sinh Viên JDBC 3","0999999993"),
	                new Book("Sinh Viên JDBC 4","0999999994")
	        );

	        books.forEach(book -> {
	        	bookJDBCRepository.save(book);
	        });

	    }
		//fail all 
			@Transactional("app2TransactionManager")
		    public void insertJDBC_Transaction_Test_failAll(){
		    	List<Book> books = Arrays.asList(
		                new Book("Sinh Viên JDBC 5","0999999991"),
		                new Book("Sinh Viên JDBC 6","0999999992"),
		                new Book(null,"0999999993"),
		                new Book("Sinh Viên JDBC 8","0999999994")
		        );

		        books.forEach(book -> {
		        	bookJDBCRepository.save(book);
		        });
		        
		    }
			// not got transaction -> like without transaction 
			@Transactional
		    public void insertJDBC_Transaction_Test_failAll_default(){
		    	List<Book> books = Arrays.asList(
		                new Book("Sinh Viên JDBC 5","0999999991"),
		                new Book("Sinh Viên JDBC 6","0999999992"),
		                new Book(null,"0999999993"),
		                new Book("Sinh Viên JDBC 8","0999999994")
		        );

		        books.forEach(book -> {
		        	bookJDBCRepository.save(book);
		        });
		        
		    }
			// just fail all objects from the third object
		    public void insertJDBC_NOTransaction_Test_failSome(){
		    	List<Book> books = Arrays.asList(
		                new Book("Sinh Viên JDBC 9","0999999991"),
		                new Book("Sinh Viên JDBC 10","0999999992"),
		                new Book(null,"0999999993"),
		                new Book("Sinh Viên JDBC 12","0999999994")
		        );

		        books.forEach(book -> {
		        	bookJDBCRepository.save(book);
		        });
		        
		    }
}
