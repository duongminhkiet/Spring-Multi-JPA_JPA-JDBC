package com.zmk.multijpa.testapp.app1.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zmk.multijpa.testapp.app1.object.entity.Student;
import com.zmk.multijpa.testapp.helper.GlobalVariable;

@Repository
public class StudentJDBCRepositoryImpl implements StudentJDBCRepository{
    @Autowired
    @Qualifier("app1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	@Override
	public int count() {
		return jdbcTemplate
                .queryForObject("select count(*) from "+GlobalVariable.TBL_STUDENT_APP1, Integer.class);
	}

	@Override
	public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete "+GlobalVariable.TBL_STUDENT_APP1+" where id = ?",
                id);
	}

	@Override
	public List<Student> findAll() {
        return jdbcTemplate.query(
                "select * from "+GlobalVariable.TBL_STUDENT_APP1,
                (rs, rowNum) ->
                        new Student(
                        		rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("phone"),
                                rs.getDate("created"),
                                rs.getDate("modified")
                        )
        );
	}

	@Override
	public int save(Student book) {
        return jdbcTemplate.update(
                "insert into "+GlobalVariable.TBL_STUDENT_APP1+" (name, phone, modified) values(?,?,?)",
                book.getName(), book.getPhone(), book.getModified());
	}

}
