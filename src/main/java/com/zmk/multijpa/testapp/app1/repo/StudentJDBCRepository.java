package com.zmk.multijpa.testapp.app1.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zmk.multijpa.testapp.app1.object.entity.Student;
//@Repository
public interface StudentJDBCRepository {
	int count();
	int deleteById(Long id);
	int save(Student student);
    List<Student> findAll();
}
