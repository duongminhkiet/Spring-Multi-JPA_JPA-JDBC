package com.zmk.multijpa.testapp.app2.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zmk.multijpa.testapp.app2.object.entity.Book;
import com.zmk.multijpa.testapp.helper.GlobalVariable;

@Repository
public class BookJDBCRepositoryImpl implements BookJDBCRepository{
    @Autowired
    @Qualifier("app2JdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	@Override
	public int count() {
		return jdbcTemplate
                .queryForObject("select count(*) from "+GlobalVariable.TBL_BOOK_APP2, Integer.class);
	}

	@Override
	public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete "+GlobalVariable.TBL_BOOK_APP2+" where id = ?",
                id);
	}

	@Override
	public List<Book> findAll() {
        return jdbcTemplate.query(
                "select * from "+GlobalVariable.TBL_BOOK_APP2,
                (rs, rowNum) ->
                        new Book(
                        		rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDate("created"),
                                rs.getDate("modified")
                        )
        );
	}

	@Override
	public int save(Book book) {
        return jdbcTemplate.update(
                "insert into "+GlobalVariable.TBL_BOOK_APP2+" (name, description, modified) values(?,?,?)",
                book.getName(), book.getDescription(), book.getModified());
	}
}
