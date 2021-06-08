package com.zmk.multijpa.testapp.app2.repo;

import java.util.List;

import com.zmk.multijpa.testapp.app2.object.entity.Book;

public interface BookJDBCRepository {
	int count();
	int deleteById(Long id);
	int save(Book student);
    List<Book> findAll();
}
