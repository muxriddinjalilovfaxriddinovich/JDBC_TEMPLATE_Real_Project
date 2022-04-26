package com.example.ServiceJDBC;

import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.Category;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
@Repository
public class JDBCBook implements BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public int save(Book book) {
        try {

        return jdbcTemplate.update("INSERT into book(name,price,categoryId,authorId ) values (?,?,?,?)",
                new Object[] {book.getName(),book.getPrice(),book.getCategoryId(),book.getAuthorId()});
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int update(Book book,Integer id) {
        return jdbcTemplate.update("Update book SET name =? , price=? , categoryId=?,authorId=? WHERE id = ?",
                book.getName(),book.getPrice(),book.getCategoryId(),book.getAuthorId(),id);
    }

    @Override
    public Book findById(Integer id) {
        Book book = jdbcTemplate.queryForObject("SELECT * FROM book WHERE id=?",
                BeanPropertyRowMapper.newInstance(Book.class), id);
        return book;
    }

    @Override
    public int deletById(Integer id) {
        return jdbcTemplate.update("DELETE FROM book WHERE id=?",id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book",BeanPropertyRowMapper.newInstance(Book.class));

    }

    @Override
    public int deletAll() {
        return jdbcTemplate.update("DELETE FROM book");
    }
}
