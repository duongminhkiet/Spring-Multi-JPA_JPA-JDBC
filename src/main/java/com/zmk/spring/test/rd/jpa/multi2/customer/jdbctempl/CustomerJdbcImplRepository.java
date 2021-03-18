package com.zmk.spring.test.rd.jpa.multi2.customer.jdbctempl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zmk.spring.test.rd.jpa.multi2.customer.data.CustomerModel;

@Repository
public class CustomerJdbcImplRepository implements CustomerJdbcRepository{

    @Autowired
    @Qualifier("sqlServer1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	@Override
	public int count() {
		return jdbcTemplate
                .queryForObject("select count(*) from CustomerModel", Integer.class);
	}

	@Override
	public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete CustomerModel where id = ?",
                id);
	}

	@Override
	public List<CustomerModel> findAll() {
        return jdbcTemplate.query(
                "select * from CustomerModel",
                (rs, rowNum) ->
                        new CustomerModel(
                                rs.getString("email"),
                                rs.getString("firstName"),
                                rs.getString("lastName")
                        )
        );
	}

	@Override
	public int save(CustomerModel book) {
        return jdbcTemplate.update(
                "insert into CustomerModel (email, firstName, lastName) values(?,?,?)",
                book.getEmail(), book.getFirstName(), book.getLastName());
	}

}
