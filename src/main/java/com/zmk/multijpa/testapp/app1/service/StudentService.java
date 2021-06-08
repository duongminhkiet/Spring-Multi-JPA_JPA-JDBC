package com.zmk.multijpa.testapp.app1.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmk.multijpa.testapp.app1.object.entity.Student;
import com.zmk.multijpa.testapp.app1.repo.StudentJDBCRepository;
import com.zmk.multijpa.testapp.app1.repo.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentJDBCRepository studentJDBCRepository;
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public Student save(Student student) {
		return studentRepository.save(student);
	}
	public void createData1Test() {
		List<Student> lStudents = new ArrayList<>();
		for(int i = 0; i<100; i++) {
			Student student = new Student("Họ và tên sinh viên thứ "+i, "099999999"+i);
			lStudents.add(student);
		}
		studentRepository.saveAll(lStudents);
		
	}
	// success all or fail all -> expect success all
	@Transactional("app1TransactionManager")
    public void insertJPA_Transaction_Test_ok(){
    	List<Student> books = Arrays.asList(
                new Student("Sinh Viên 1","0999999991"),
                new Student("Sinh Viên 2","0999999992"),
                new Student("Sinh Viên 3","0999999993"),
                new Student("Sinh Viên 4","0999999994")
        );

//        books.forEach(book -> {
//        	studentRepository.save(book);
//        });
        studentRepository.saveAll(books);
        
    }
	
	//fail all 
	@Transactional("app1TransactionManager")
    public void insertJPA_Transaction_Test_failAll(){
    	List<Student> books = Arrays.asList(
                new Student("Sinh Viên 5","0999999991"),
                new Student("Sinh Viên 6","0999999992"),
                new Student(null,"0999999993"),
                new Student("Sinh Viên 8","0999999994")
        );

//    	try {
//            books.forEach(book -> {
//            	studentRepository.save(book);
//            });
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

        studentRepository.saveAll(books);
        
    }
	// just fail all objects from the third object
    public void insertJPA_NOTransaction_Test_failSome(){
    	List<Student> books = Arrays.asList(
                new Student("Sinh Viên 9","0999999991"),
                new Student("Sinh Viên 10","0999999992"),
                new Student(null,"0999999993"),
                new Student("Sinh Viên 12","0999999994")
        );

        books.forEach(book -> {
        	studentRepository.save(book);
        });

//        studentRepository.saveAll(books);
    }
    
    /// ================== FOR JDBC ==================
	@Transactional("app1TransactionManager")
    public void insertJDBC_Transaction_Test_ok(){
    	List<Student> books = Arrays.asList(
                new Student("Sinh Viên JDBC 1","0999999991"),
                new Student("Sinh Viên JDBC 2","0999999992"),
                new Student("Sinh Viên JDBC 3","0999999993"),
                new Student("Sinh Viên JDBC 4","0999999994")
        );

        books.forEach(book -> {
        	studentJDBCRepository.save(book);
        });

    }
	//fail all 
		@Transactional("app1TransactionManager")
	    public void insertJDBC_Transaction_Test_failAll(){
	    	List<Student> books = Arrays.asList(
	                new Student("Sinh Viên JDBC 5","0999999991"),
	                new Student("Sinh Viên JDBC 6","0999999992"),
	                new Student(null,"0999999993"),
	                new Student("Sinh Viên JDBC 8","0999999994")
	        );

	        books.forEach(book -> {
	        	studentJDBCRepository.save(book);
	        });
	        
	    }
		// just fail all objects from the third object
	    public void insertJDBC_NOTransaction_Test_failSome(){
	    	List<Student> books = Arrays.asList(
	                new Student("Sinh Viên JDBC 9","0999999991"),
	                new Student("Sinh Viên JDBC 10","0999999992"),
	                new Student(null,"0999999993"),
	                new Student("Sinh Viên JDBC 12","0999999994")
	        );

	        books.forEach(book -> {
	        	studentJDBCRepository.save(book);
	        });
	        
	    }
}
