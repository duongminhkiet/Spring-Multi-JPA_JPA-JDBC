package com.zmk.multijpa.testapp.app2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zmk.multijpa.testapp.app2.object.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

}
