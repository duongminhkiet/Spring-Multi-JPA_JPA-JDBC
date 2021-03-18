package com.zmk.spring.test.rd.jpa.multi2.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zmk.spring.test.rd.jpa.multi2.product.data.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel,Integer> {
}
