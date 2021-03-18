package com.zmk.spring.test.rd.jpa.multi2.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zmk.spring.test.rd.jpa.multi2.customer.data.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,Integer> {
}
